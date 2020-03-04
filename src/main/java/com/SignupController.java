package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/signup")
public class SignupController {
	
	@RequestMapping(value="/store", method=RequestMethod.POST)
	public String post(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		Signup signup = new Signup();
		
		signup.createUser(request.getParameter("Email"), request.getParameter("Password"), 
				request.getParameter("Firstname"), request.getParameter("Lastname"));
		
		if (signup.storeUser() == true) {
			return "true";
		}
		
		return "false";
	}
}
