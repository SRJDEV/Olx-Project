package com.olx.controller;

import java.awt.Window;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.olx.dto.AuthenticationRequest;
import com.olx.dto.UserDTO;
import com.olx.entity.UserEntity;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.repo.UserMongoRepo;
import com.olx.security.JwtUtil;
import com.olx.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/olx/login")
public class UserController {

	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;
	

	
		@GetMapping(value = "/user/validate/token", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
		public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String autherization) {
		Boolean isTokenValid = false;
		try {
			String token = autherization.replace("Bearer ", "");
			UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
			isTokenValid = jwtUtil.validateToken(token, userDetails);
		
			if(isTokenValid)
			if(userService.isTokenBlacklisted(token)) {
				
				return new ResponseEntity<>(false, HttpStatus.OK);
			}
			
			
			} catch (Exception exception) {
		
				return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
			}
			
			
			
			return new ResponseEntity<>(isTokenValid, HttpStatus.OK);
			
			
	   }
	
	@PostMapping(value ="/user/authenticate",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_XML_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_XML_VALUE})
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authRequest) {
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), 
					authRequest.getPassword()));
		}
		catch(BadCredentialsException e){
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		//login successful
		UserDetails userDetails = 	userDetailsService.loadUserByUsername(authRequest.getUserName());
		String jwtToken = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity<String>(jwtToken,HttpStatus.OK);
		
	}
	
	
	
	@PostMapping(value ="/user",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_XML_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_XML_VALUE})
	public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO user) {
		
		return userService.createUser(user);
	}

	@GetMapping(value ="/user",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_XML_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_XML_VALUE})
	public ResponseEntity<UserEntity>getUser(@RequestHeader("Authorization") String token) {
		
	return	userService.getUser(token);
		
	
	}
	
	@GetMapping(value = "/user/logout",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_XML_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_XML_VALUE})
	public ResponseEntity<String>logout(@RequestHeader("Authorization") String token){
		
		
	    if(validateToken(token).getBody()) {
	 
			if(userService.logout(token)){
				return new ResponseEntity<String>("Logged out successfully",HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Logged out failed",HttpStatus.OK);
			}
		
	   }else {
		    
		      throw new InvalidAuthTokenException();
		   
	   }
		
	}
	
	@GetMapping(value ="/user/username",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_XML_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_XML_VALUE})
	public ResponseEntity<String>getUserName(@RequestHeader("Authorization") String token) {
	String tokenNew = token.replace("Bearer ", "");
	return	userService.getUserName(tokenNew);
		
	
	}
	
	
	
	
}
