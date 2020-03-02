package com;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
		  
	@RequestMapping(value="/verify", method=RequestMethod.POST)
//	@PostMapping(value="/verify")
	public String postMePlease(HttpServletRequest request, HttpServletResponse response) throws IOException { 

		Login login = new Login(request.getParameter("Email"), request.getParameter("Password"));
	  
		if (login.verifyPassword()) {
			return "true";
		}
		
		return "false";	  
	}
}
