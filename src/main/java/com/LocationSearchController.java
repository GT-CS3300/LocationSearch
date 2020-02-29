package com;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class LocationSearchController {
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String postMePlease(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		LocationSearch locationSearch = new LocationSearch(Float.valueOf(request.getParameter("Latitude")), 
				Float.valueOf(request.getParameter("Longitude")), Integer.valueOf(request.getParameter("Radius")));
	  
		
		return locationSearch.search();  
	}
}
