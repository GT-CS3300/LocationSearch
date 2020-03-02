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
	
<<<<<<< HEAD
//	@RequestMapping(value="/result", method=RequestMethod.POST)
//	public String postMePlease(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		LocationSearch locationSearch = new LocationSearch(Float.valueOf(request.getParameter("Latitude")),
//				Float.valueOf(request.getParameter("Longitude")), Integer.valueOf(request.getParameter("Radius")));
//
//
//		return locationSearch.search();
//	}
=======
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String post(HttpServletRequest request, HttpServletResponse response) throws IOException { 
		LocationSearch locationSearch = new LocationSearch(Float.valueOf(request.getParameter("Latitude")), 
				Float.valueOf(request.getParameter("Longitude")), Integer.valueOf(request.getParameter("Radius")));
	  
		
		return locationSearch.search();  
	}
>>>>>>> 95c75573e4cc5c43cd8bec2f2e9d8d79e4b40045
}
