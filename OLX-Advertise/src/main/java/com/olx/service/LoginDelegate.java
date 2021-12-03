package com.olx.service;

public interface LoginDelegate {

	
	public boolean validateToken(String token);
	public String getUserName(String token);
	
}
