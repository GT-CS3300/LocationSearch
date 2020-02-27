package main.java.com;

public class LoginController {
	private String email;
	private String password;
	
	public LoginController(String email, String password) {
		// Set email and password based on input given from front-end
		this.setEmail(email);
		this.setPassword(password);
	}
	
	/**
	 * Authenticate user based on their login input.
	 * @return true if email and password are correct, otherwise false
	 */
	public boolean verifyPassword() {
		// Get the password associated with the given email from database
		
		// If password is correct return true, else return false
		
		// If no result is returned from the get password query i.e. email is not in database, throw exception
		
		return true;
	}
	
	// Getters and Setters for email and password
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
