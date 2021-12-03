package com.olx.service;

import org.springframework.http.ResponseEntity;

import com.olx.dto.UserDTO;
import com.olx.entity.UserEntity;

public interface UserService {

	
	public ResponseEntity<UserEntity>createUser(UserDTO user);

	public ResponseEntity<UserEntity>getUser(String token);
	
	public ResponseEntity<String>getUserName(String token);
	
	public Boolean logout(String token);
	
	public Boolean isTokenBlacklisted(String token);

}
