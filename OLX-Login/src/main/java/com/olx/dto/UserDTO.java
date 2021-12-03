package com.olx.dto;


import com.olx.utility.UserActiveStatus;

public class UserDTO {

	

	private int id; 
    private String username;
	private String role;
    private String password;
	private UserActiveStatus active = UserActiveStatus.FALSE;
	private String email;
	private String firstName;
	private String lastName;
	
	
	
	
	
	public UserDTO(String username, String role, String password, UserActiveStatus active, String email,
			String firstName, String lastName) {
		super();
		this.username = username;
		this.role = role;
		this.password = password;
		this.active = active;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public UserActiveStatus getActive() {
		return active;
	}



	public void setActive(UserActiveStatus active) {
		this.active = active;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public UserDTO() {
		super();
	}



	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", role=" + role + ", password=" + password
				+ ", active=" + active + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

	
	
	
	
}
