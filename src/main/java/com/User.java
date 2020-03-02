package com;

import java.io.Serializable;

public class User implements Serializable {
	// Serial ID
	private static final long serialVersionUID = -8224583530000882248L;
	
	// User attributes
	private String email;
	private String hash;
	/*More attributes if needed*/

	/**
	 * Generic user class, really just a tuple...
	 * @param email the email of the user
	 * @param password the input password of the user... needs to be hashed + salted before storage...
	 */
	public User(String email, String password) {
		this.email = email;
		// TODO the hashing
		this.hash = password;
	}

	public User(){
		this.email = null;
		this.hash = null;
	}
	
	// Generic Getters and setters
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Vulnerable to reflection attacks...
	 * @param str the hash to check
	 * @return true if the input is equal to the hash of this object
	 */
	public boolean isCorrectHash(String str){
		return hash.equals(str);
	}

	
	public void setHash(String password) {
		//TODO gonna need to hash this before setting it
		this.hash = password;
	}

	public static String hashString(String str){
		//TODO write the correct hashing function here
		return "Unimplemented";
	}
	
	/*More attribute getters and setters if needed*/
	
}
