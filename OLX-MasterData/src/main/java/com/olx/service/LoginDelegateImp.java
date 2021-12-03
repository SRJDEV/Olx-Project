package com.olx.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginDelegateImp implements LoginDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	
	
	
	
	@Override
	public boolean validateToken(String token) {
	   
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//	  
//		params.add("Authorization",token );
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setBearerAuth(token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<Boolean> response = restTemplate.exchange("http://login-service/olx/login/user/validate/token",HttpMethod.GET,
				request,
				Boolean.class);
		return response.getBody();
		}

	
	

	



}

