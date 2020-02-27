package main.java.com;

public class SignupController {
	private User user;
	
	public SignupController() {
		this.user = new User();
	}
	
	/**
	 * Created a new user based on values obtained from front-end.
	 * Attribute values should already be checked to be in correct format before being passed to back-end.
	 * @param email attribute
	 * @param password attribute
	 * @param firstName attribute
	 * @param lastName attribute
	 * @param dob
	 * @return true if user was successfully created, otherwise false
	 */
	public boolean createUser(String email, String password, String firstName, String lastName, String dob) {
		// Use User class setters to set user attributes based on value obtained from front-end.
		this.user.setEmail(email);
		this.user.setPassword(password);
		this.user.setName(firstName, lastName);
		this.user.setDob(dob);
		
		return this.storeUser(this.getUser());
	}
	
	/**
	 * Tries to store the given user in the database.
	 * @param user to be stored
	 * @return true if user was successfully stored, otherwise false
	 */
	public boolean storeUser(User user) {	
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
