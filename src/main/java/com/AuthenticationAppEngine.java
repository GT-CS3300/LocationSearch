package com;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;


import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthenticationAppEngine {
	private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private Gson gson = new Gson();
	private static String kind = "user";


	/**
	 * @param rq - The request
	 * @param rp - The response
	 * @return The JSON string to be passed to the frontend
	 * @throws IOException - In the case of malformed JSON
	 * @author mransby3
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String goodByeGet(HttpServletRequest rq, HttpServletResponse rp) throws IOException, JSONException {

		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwidXBkYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiY3JlYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiaWF0IjoxNTgyMzAzODgwLCJleHAiOjE1ODI5MDg2ODB9.CfqN2kPmczXtY98z23yNPFQISSVabbm6LYo2EIqA_Fs";
		JSONObject json = new JSONObject().put("Token", token);
		return json.toString();
	}

	/**
	 * @param rq - All of the attributes sent with the request
	 * @param rp - the response
	 * @return The JSON string to be passed to the frontend
	 * @throws IOException - In the case of malformed JSON
	 * @author mransby3
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String doPost(HttpServletRequest rq, HttpServletResponse rp, @RequestBody String body) throws IOException, JSONException {
		Gson gson = new Gson();
		User newUser = gson.fromJson(body, User.class);

		System.out.println("newUser = " + newUser);

		Entity incBookEntity = new Entity(kind);  // Key will be assigned once written
		incBookEntity.setProperty("email", newUser.getEmail());


		Key bookKey = datastore.put(incBookEntity); // Save the Entity


		return gson.toJson(newUser);

	}


}
