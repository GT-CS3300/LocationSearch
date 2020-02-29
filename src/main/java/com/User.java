package com;

import java.io.Serializable;

public class User implements Serializable {
	// Serial ID
	private static final long serialVersionUID = -8224583530000882248L;
	
	// User attributes
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	/*More attributes if needed*/
	
	public User() {
		this.email = "email";
		this.password = "password";
	}
	
	// Generic Getters and setters
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/*More attribute getters and setters if needed*/
	
}
