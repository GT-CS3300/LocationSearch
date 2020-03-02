package com;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

@RestController
@RequestMapping("/hello")
public class HistoryAppEngine {

	@GetMapping("getItMan")
	public String getMePlease(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return "Hello App Engine - Class SE!\r\n";

	}

	@RequestMapping(value="postItLocalSearch",method = RequestMethod.POST)
	public String postMePlease(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		return gson.toJson("Post it Local Search");

	}

}