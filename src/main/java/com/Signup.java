package com;

public class Signup {
	private User user;
	
	public Signup() {
		this.user = new User();
	}
	
	/**
	 * Created a new user based on values obtained from front-end.
	 * Attribute values should already be checked to be in correct format before being passed to back-end.
	 * @param email attribute
	 * @param password attribute
	 * @return true if user was successfully created, otherwise false
	 */
	public boolean createUser(String email, String password, String firstName, String lastName) {
		// Use User class setters to set user attributes based on value obtained from front-end.
		this.user.setEmail(email);
		this.user.setHash(password);
		
		return this.storeUser();
	}
	
	/**
	 * Tries to store the given user in the database.
	 * @return true if user was successfully stored, otherwise false
	 */
	public boolean storeUser() {	
		// TODO: Hard coded values for now, need to replace this with database verification
		if (this.user.getEmail().equals("mahdi@hotmail.com")) {
			return false;
		}
		
		// Check if user's email is already in database, if so return false
		
		// Otherwise, connect to database and store user information
		return true;
	}
	
	/**
	 * Gets the user being created.
	 * @return user made
	 */
	public User getUser() {
		return this.user;
	}
}
