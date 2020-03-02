package com;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

@RestController
@RequestMapping("/auth")
public class AuthenticationAppEngine {

  /**
   *
   * @param request
   * @param response
   * @return The JSON string to be passed to the frontend
   * @throws IOException - In the case of malformed JSON
   * @author mransby3
   */
  @GetMapping(value="",
          produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public String goodByeGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    return  "/auth - GET " + request.toString();

  }

  /**
   *
   * @param rq - All of the attributes sent with the request
   * @param rp
   * @throws IOException - In the case of malformed JSON
   * @return The JSON string to be passed to the frontend
   * @author mransby3
   */
  @PostMapping(value="",
          produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public String doPost(HttpServletRequest rq, HttpServletResponse rp, @RequestBody String body) throws IOException {
//    Gson request = rq.get

      Gson gson = new Gson();



//	  return "/auth - POST " + rq.getHeader("Authorization") +
//              rq.getParameter("Email") + ":" + rq.getParameter("Password");
    return "auth";
  }
  
  
  
}
