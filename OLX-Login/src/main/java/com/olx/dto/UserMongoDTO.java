package com.olx.dto;

public class UserMongoDTO {

	private int id;
	private String token;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserMongoDTO(int id, String token) {
		super();
		this.id = id;
		this.token = token;
	}
	public UserMongoDTO(String token) {
		super();
		this.token = token;
	}
	public UserMongoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserMongoDTO [id=" + id + ", token=" + token + "]";
	}
	
	
	


}

