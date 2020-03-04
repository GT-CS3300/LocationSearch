package com;


import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import com.google.gson.Gson;
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
	public String getMePlease(@RequestBody String body) {
		return "";
	}

	@PostMapping()
	public String postMePlease(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		//get user Key from authtoken
		//get the authtoken from the header
		String token = request.getHeader("Authorization");

		//remove that bearer shit
		System.out.println("(pre replace) token = '" + token + "'");
		token = token.replaceAll("^(Bearer )", "");
		System.out.println("(post replace) token ='" + token + "'");

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
			history.setProperty("lat", row.getLatitude());
			history.setProperty("lon", row.getLongitude());
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