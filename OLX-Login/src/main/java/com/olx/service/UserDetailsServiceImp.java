package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.dto.UserDTO;
import com.olx.entity.UserDocument;
import com.olx.entity.UserEntity;
import com.olx.repo.UserMongoRepo;
import com.olx.repo.UserRepo;
import com.olx.security.JwtUtil;



@Service(value ="USER_SERVICE")
public class UserDetailsServiceImp implements UserDetailsService,UserService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	UserMongoRepo userMongoRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	List<UserEntity>userEntities = userRepo.findUserByUsername(username);
	
	
		if(userEntities.isEmpty()) {
			
			throw new UsernameNotFoundException(username);
		}else {
			
			UserEntity userEntity = userEntities.get(0);
			List<GrantedAuthority>grantedAuthority = new ArrayList<GrantedAuthority>();
			grantedAuthority.add(new SimpleGrantedAuthority(userEntity.getRole()));
			
			org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
			passwordEncoder.encode(userEntity.getPassword()), 
					grantedAuthority);
			return user;
		}
		
	
	}

	@Override
	public ResponseEntity<UserEntity> createUser(UserDTO user) {
		
		 UserEntity entity = modelMapper.map(user, UserEntity.class);
		 UserEntity	entityNew = userRepo.save(entity);
	  
	     if(entity!=null) {
	    	 return new ResponseEntity<UserEntity>(entityNew,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<UserEntity> getUser(String token) {
	
			//get user name then get user id 
		UserDetails userDetails =	loadUserByUsername(jwtUtil.extractUsername(token));
		
		if(userDetails!=null) {
		
		 UserEntity entity  = userRepo.findUserByUsername(userDetails.getUsername()).get(0);
		 
		 if(entity!=null) {
			 
			 return new ResponseEntity<UserEntity>(entity,HttpStatus.OK);
			 
		 }
		 
		}
		
	
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}

	@Override
	public Boolean logout(String token) {

		
		 UserDocument userDocument = new UserDocument(token);
		 UserDocument userDocumentNew= null;
		 
		if(!isTokenBlacklisted(token)){
			userDocumentNew = userMongoRepo.save(userDocument);
		 }
		
		if(userDocumentNew!=null) {
		 	
			return true;
		}else {
		 
		return false;
		}
	}
		
	

	@Override
	public ResponseEntity<String> getUserName(String token) {
	UserEntity userEntity=	getUser(token).getBody();
		return new  ResponseEntity<String>(userEntity.getUsername(),HttpStatus.OK);
	}

	@Override
	public Boolean isTokenBlacklisted(String token) {
		
		List<UserDocument> documentList = userMongoRepo.findByToken(token);
		if(documentList!=null && documentList.size()>0) {
		
			return true;
		}else {
			return false;
		}
	}
	

}
