package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.olx.utility.UserActiveStatus;


@Entity
@Table(name ="USERS")
public class UserEntity {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	@Column(name ="Username")
	private String username;
	@Column(name ="Role")
	private String role;
	@Column(name ="Password")
	private String password;
	
	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "Active")
	private UserActiveStatus active = UserActiveStatus.FALSE;
	
	
	@Column(name = "First_name")
	private String firstName;
	
	@Column(name = "Last_name")
	private String lastName;
	
	@Column(name = "Email")
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserEntity(int id, String username, String role, String password, UserActiveStatus active, String firstName,
			String lastName, String email) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password;
		this.active = active;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	
	
	
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", role=" + role + ", password=" + password
				+ ", active=" + active + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}
	

	
	
	

}
