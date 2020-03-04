package com;

import com.google.appengine.api.datastore.Key;

public class LocationSearch {
	private float latitude;
	private float longitude;
	
	public LocationSearch(float latitude, float longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
//		this.setUser(new User());
	}

	public LocationSearch(float lat, float lon, User user){
		this.latitude = lat;
		this.longitude = lon;
	}
	
	// Setters and getters for user input values, they should already be checked by front-end to be correct format
	public float getLatitude() { return latitude; }

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

}
