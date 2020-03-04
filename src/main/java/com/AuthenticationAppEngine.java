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
		//check that the email/password match
		//make a new authtoken, update that row
		//return the authtoken to frontend
		return "unimplemented sozz";
	}

	@PostMapping
	public String doPost(HttpServletRequest rq, HttpServletResponse rp, @RequestBody String body) throws JSONException {
		User newUser = gson.fromJson(body, User.class);
		String uuid = getUUID();

		System.out.println("newUser = " + newUser);

		//TODO Check if the user already exists in the Datastore

		Entity user = new Entity(kind);  // Key will be assigned once written
		user.setProperty("email", newUser.getEmail());
		user.setProperty("password", newUser.getPassword());
		user.setIndexedProperty("authToken", uuid);

		Key bookKey = datastore.put(user); // Save the Entity


		return "{ \n  \"Token\": \"" + uuid + "\"\n}";

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


}
