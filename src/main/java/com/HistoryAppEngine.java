package com;


import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/history")
public class HistoryAppEngine {
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private Gson gson = new Gson();
	private static String kind = "history";
	private static String user = "user";

	@GetMapping()
	public String getMePlease(HttpServletRequest request, HttpServletResponse response) throws JSONException {
		//get user Key from authtoken
		//get the authtoken from the header
		String token = request.getHeader("Authorization");
		System.out.println("token = " + token);

		//remove that bearer shit
//		System.out.println("(pre replace) token = '" + token + "'");
		if (token.matches("^(Bearer )")){
			token = token.replaceAll("^(Bearer )", "");
			System.out.println("token ='" + token + "'");
		}

		//Build a query
		Query q = new Query(user).setFilter(new FilterPredicate("authToken", FilterOperator.EQUAL, token));

		//run the query
		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

		//if unauthorised, return 401(unauthorized)
		if (token.equals("") || results.isEmpty()){
			response.setStatus(401);
			return "";
		}

		//else add user key, lat, lon as row of kind history
		if (results.size() == 1){
			//get the user
			Entity user = results.get(0);
			System.out.println("user = " + user);
			//get their key
			String userKey = user.getKey().toString();
			System.out.println("userKey = " + userKey);

			//do a query to find all the rows with information
//			Query historyQuery = new Query("history").setFilter(new FilterPredicate("userKey", FilterOperator.EQUAL, userKey));
			Query historyQuery = new Query("history");
			List<Entity> historicalResults = datastore.prepare(historyQuery).asList(FetchOptions.Builder.withDefaults());

			System.out.println("historicalResults = " + historicalResults);

			JSONArray arr = new JSONArray();
			for (Entity historicalResult : historicalResults) {
				if (historicalResult.getProperty("userKey").toString().equals(userKey)){
					JSONObject location = new JSONObject();
					location.put("latitude", historicalResult.getProperty("latitude"));
					location.put("longitude", historicalResult.getProperty("longitude"));
					arr.put(location);


				}
			}


			//return that as a JSON array
			return arr.toString();
		} else {
			response.setStatus(500);
			return "more than 1 user with that authToken my dude";
		}

	}

	@PostMapping()
	public String postMePlease(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		//get user Key from authtoken
		//get the authtoken from the header
		String token = request.getHeader("Authorization");
		System.out.println("token = " + token);

		//remove that bearer shit

		if (token.matches("^(Bearer )")){
			token = token.replaceAll("^(Bearer )", "");
			System.out.println("(post replace) token ='" + token + "'");
		}

		//Build a query
		Query q = new Query(user).setFilter(new FilterPredicate("authToken", FilterOperator.EQUAL, token));

		//run the query
		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

		//if unauthorised, return 401(unauthorized)
		if (token.equals("") || results.isEmpty()){
			response.setStatus(401);
			return "";
		}

		//else add user key, lat, lon as row of kind history
		if (results.size() == 1){
			//get the user
			Entity user = results.get(0);
			//get their key
			Key userKey = user.getKey();

			//make a locationSearch object from the body
			LocationSearch row = gson.fromJson(body, LocationSearch.class);

			//make a history row
			Entity history = new Entity(kind);  // Key will be assigned once written
			history.setIndexedProperty("userKey", userKey);
			history.setProperty("latitude", row.getLatitude());
			history.setProperty("longitude", row.getLongitude());
			datastore.put(history); // commit it
		} else {
			response.setStatus(500);
			return "more than 1 user with that authToken my dude";
		}

		return "";
	}

	@DeleteMapping()
	public String deleteMePlease(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		return "TODO";
	}

}