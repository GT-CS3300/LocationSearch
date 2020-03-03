package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
