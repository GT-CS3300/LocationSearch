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

  @GetMapping(value="")
  public String goodByeGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    return "/auth";

  }
  
  @PostMapping(value="/postItGoodBye", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public String doPost(HttpServletRequest request, HttpServletResponse response) {
	  
	  return "";
  }
  
  
  
}
