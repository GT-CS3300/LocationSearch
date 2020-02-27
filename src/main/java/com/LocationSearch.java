package com;

public class LocationSearch {
	private float latitude;
	private float longitude;
	private int radius;
	private User user;
	
	public LocationSearch(float latitude, float longitude, int radius, User user) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setRadius(radius);
		this.setUser(user);
	}
	
	// Setters and getters for user input values, they should already be checked by front-end to be correct format
	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Uses Places API to generated results from the given input values.
	 * @param latitude input value
	 * @param longitude input value
	 * @param radius input value
	 */
	public void search(float latitude, float longitude, int radius) {
		// Use Places API to return a list of places for given input values (JSONObject)
		
		// On successful search, store this search with the user
		this.storeHistory(this.user);
	}
	
	/**
	 * Stores search history for given user.
	 * @param user to have history stored for
	 */
	public void storeHistory(User user) {
		// Generates a 'searchID' then stores this along with searched values into database for given user
		
	}
	
	/**
	 * Gets all the search history associated with given user.
	 * @param user to get history for.
	 */
	public void getHistory(User user) {
		// Gets all the search history of given user's email from database
		
	}
	
	/**
	 * Clears all user input so user can begin a new search.
	 */
	public void clear() {
		this.latitude = 0;
		this.longitude = 0;
		this.radius = 0;
	}
}
