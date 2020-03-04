package com;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
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
	private Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
	private Gson gson = new Gson();


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

//		Entity userEntity = new Entity("User");
//		userEntity.setProperty("email", newUser.getEmail());
//		userEntity.setProperty("hash", newUser.getPassword()); // use PBKDF2 to hash and salt the passkey
//		datastore.put(userEntity);

		return gson.toJson(newUser);


//    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwidXBkYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiY3JlYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiaWF0IjoxNTgyMzAzODgwLCJleHAiOjE1ODI5MDg2ODB9.CfqN2kPmczXtY98z23yNPFQISSVabbm6LYo2EIqA_Fs";
//    JSONObject json = new JSONObject().put("Token", token);
//    return json.toString();
	}


}
