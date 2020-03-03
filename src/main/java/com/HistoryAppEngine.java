package com;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/history")
public class HistoryAppEngine {

	@GetMapping()
	public String getMePlease(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String pre = "Bearer ";
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwidXBkYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiY3JlYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiaWF0IjoxNTgyMzAzODgwLCJleHAiOjE1ODI5MDg2ODB9.CfqN2kPmczXtY98z23yNPFQISSVabbm6LYo2EIqA_Fs";
//		return request.getHeader("Authorization");

		if (request.getHeader("Authorization").equals(pre + token)){
			return "Authorized";
		}

		return "Unauthorised";
//		return "/history - GET " + request.toString();

	}

	@PostMapping()
	public String postMePlease(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		return "/history - POST " + request.toString();
	}

	@DeleteMapping()
	public String deleteMePlease(HttpServletRequest request, HttpServletResponse response) throws IOException {

		return "/history - DELETE " + request.toString();
	}

}