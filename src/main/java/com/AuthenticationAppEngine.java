package com;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

@RestController
@RequestMapping("/auth")
public class AuthenticationAppEngine {

  /**
   *
   * @param rq - The request
   * @param rp - The response
   * @return The JSON string to be passed to the frontend
   * @throws IOException - In the case of malformed JSON
   * @author mransby3
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public String goodByeGet(HttpServletRequest rq, HttpServletResponse rp) throws IOException, JSONException {



//    return  "/auth - GET " + rq.toString();
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwidXBkYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiY3JlYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiaWF0IjoxNTgyMzAzODgwLCJleHAiOjE1ODI5MDg2ODB9.CfqN2kPmczXtY98z23yNPFQISSVabbm6LYo2EIqA_Fs";
    JSONObject json = new JSONObject().put("Token", token);
    return json.toString();
  }

  /**
   *
   * @param rq - All of the attributes sent with the request
   * @param rp - the response
   * @throws IOException - In the case of malformed JSON
   * @return The JSON string to be passed to the frontend
   * @author mransby3
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public String doPost(HttpServletRequest rq, HttpServletResponse rp, @RequestBody String body) throws IOException, JSONException {
//    Gson request = rq.get

      Gson gson = new Gson();



//	  return "/auth - POST " + rq.getHeader("Authorization") +
//              rq.getParameter("Email") + ":" + rq.getParameter("Password");
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwidXBkYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiY3JlYXRlZEF0IjoiMjAyMC0wMi0yMVQxNjo1MToyMC44NzFaIiwiaWF0IjoxNTgyMzAzODgwLCJleHAiOjE1ODI5MDg2ODB9.CfqN2kPmczXtY98z23yNPFQISSVabbm6LYo2EIqA_Fs";
    JSONObject json = new JSONObject().put("Token", token);
    return json.toString();
  }
  
  
  
}
