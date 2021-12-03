package com.olx.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="blacklisted_tokens")
public class UserDocument {

	
	@Id
	private String id;
	
	private String token;

	public UserDocument(String id, String token) {
		super();
		this.id = id;
		this.token = token;
	}

	public UserDocument(String token) {
		super();
		this.token = token;
	}

	public UserDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserDocument [id=" + id + ", token=" + token + "]";
	}
	
	
	
	
	
}
