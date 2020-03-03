package com;

public class LocationSearch {
	private float latitude;
	private float longitude;
	private User user;
	
	public LocationSearch(float latitude, float longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
//		this.setUser(new User());
	}

	public LocationSearch(float lat, float lon, User user){
		this.latitude = lat;
		this.longitude = lon;
		this.user = user;
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Uses Places API to generated results from the given input values.
//	 * @param latitude input value
//	 * @param longitude input value
//	 * @param radius input value
//	 */
	public String search() {
		// Use Places API to return a list of places for given input values (JSONObject)
		
		// On successful search, store this search with the user
		this.storeHistory(this.user);
		
		return "results";
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
	}
}
