package com;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthenticationAppEngine {
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private Gson gson = new Gson();
	private static String kind = "user";

	@GetMapping
	public String goodByeGet(HttpServletRequest rq, HttpServletResponse rp, @RequestBody String body) throws IOException, JSONException {
		User authingUser = gson.fromJson(body, User.class);

		//check that the email/password match
		//Building the query
		Filter emailFilter = new FilterPredicate("email", FilterOperator.EQUAL, authingUser.getEmail());
		Filter passwordFilter = new FilterPredicate("password", FilterOperator.EQUAL, authingUser.getPassword());
		CompositeFilter filter = CompositeFilterOperator.and(emailFilter, passwordFilter);
		Query q = new Query(kind).setFilter(filter);

		//run the query
		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

		//it had better be just one result
		if (results.size() == 0){
			//If there's no user, there's nothing to give.
			return "";
		} else if (results.size() == 1){
			//if there is exactly one user, we will give them their authtoken :)

			//make a new uuid
			String uuid = getUUID();
			//commit to DB
			Entity user = results.get(0);
			user.setIndexedProperty("authToken", uuid);
			//put is equivalent to an update AND new for some reason...
			datastore.put(user);

			//return the JSON to the user
			return kvJSON("Token", uuid);
		} else {
			return "Yeah uhh the server is bad times rn lmao";
		}

//		System.out.println(results.size() + " results = \n" + results);
		//make a new authtoken, update that row
		//return the authtoken to frontend
//		return "unimplemented sozz";
	}

	@PostMapping
	public String doPost(HttpServletRequest rq, HttpServletResponse rp, @RequestBody String body) throws JSONException {
		System.out.println("POST - BODY\n" + body);

		User newUser = gson.fromJson(body, User.class);
		String uuid = getUUID();

		System.out.println("newUser = " + newUser);

		//TODO Check if the user already exists in the Datastore

		Entity user = new Entity(kind);  // Key will be assigned once written
		user.setProperty("email", newUser.getEmail());
		user.setProperty("password", newUser.getPassword());
		user.setIndexedProperty("authToken", uuid);

		Key bookKey = datastore.put(user); // Save the Entity
		return kvJSON("Token", uuid);
	}

	@PutMapping
	public String doPut(HttpServletRequest rq, HttpServletResponse rp, @RequestBody String body){
		//Building the query
//		Filter emailFilter = new FilterPredicate("email", FilterOperator.EQUAL, authingUser.getEmail());
//		Filter passwordFilter = new FilterPredicate("password", FilterOperator.EQUAL, authingUser.getPassword());
//		CompositeFilter filter = CompositeFilterOperator.and(emailFilter, passwordFilter);
//		Query q = new Query(kind).setFilter(filter);


		return "";
	}

	private boolean emailExists(String email){
		Filter emailEqualFilter =
				new FilterPredicate("email", FilterOperator.EQUAL, email);
		Query emailEqualQuery = new Query(kind).setFilter(emailEqualFilter);

		List<Entity> results = datastore.prepare(emailEqualQuery).asList(FetchOptions.Builder.withDefaults());

		System.out.println(results.size() + " results = \n" + results);
		return results.size() > 0;
	}

	public static String getUUID(){
		return UUID.randomUUID().toString();
	}

	public static String kvJSON(String key, String value){
		return "{ \n  \"" + key + "\": \"" + value + "\"\n}";
	}


}
