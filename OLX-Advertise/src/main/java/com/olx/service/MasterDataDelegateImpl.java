package com.olx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



@Service
public class MasterDataDelegateImpl implements MasterDataDelegate{

	@Autowired
	RestTemplate restTemplate;
	
//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}
	
	@Override
	public List<Map> getCategories() {
		//List list= this.restTemplate.getForObject("http://localhost:9001/olx/advertise/category", List.class);
		List list= this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/category", List.class);
		return list;
	}
	

	
	
	@CircuitBreaker(name ="STATUS-CIRCUIT-BREAKER", fallbackMethod = "fallBack")
	@Override
	public List<Map> getStatus() {

		//List list= this.restTemplate.getForObject("http://localhost:9001/olx/masterdata/advertise/status", List.class);
		List list= this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/status", List.class);
		return list;
	}
	@Override
	public List<Map> getCategoryById() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Map> fallBack(Throwable th){


		System.out.println("Fallback Method Hit: "+th );
		return null;
	}

	@Override
	public Boolean validateCategoryId(int categoryId) {


		String url = "http://API-GATEWAY/olx/masterdata/advertise/category/validate";
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("categoryId",categoryId)
		        .encode()
		        .toUriString();
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity request = new HttpEntity(headers);
		
		
	  ResponseEntity<Boolean> isValid = this.restTemplate.exchange(urlTemplate,HttpMethod.GET,
				request,
				Boolean.class);
		 
	  
		 return isValid.getBody();
	}

	@Override
	public Boolean validateStatusId(int statusId) {
	String url = "http://API-GATEWAY/olx/masterdata/advertise/status/validate";
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("statusId",statusId)
		        .encode()
		        .toUriString();
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity request = new HttpEntity(headers);
		
		
	  ResponseEntity<Boolean> isValid = this.restTemplate.exchange(urlTemplate,HttpMethod.GET,
				request,
				Boolean.class);
		 
	  
		 return isValid.getBody();

	}


	
	@Override
	public ResponseEntity<String> getCategoryNameById(int categoryId) {

		

	String url = "http://API-GATEWAY/olx/masterdata/advertise/category/name";
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("categoryId",categoryId)
		        .encode()
		        .toUriString();
		
		HttpEntity request = new HttpEntity(headers);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<String> response = restTemplate.exchange(urlTemplate,HttpMethod.GET,
				request,
				String.class);
		
		return response;
		
		
		
	}

	@Override
	public ResponseEntity<String> getStatusNameById(int statusId) {
	
		String url = "http://API-GATEWAY/olx/masterdata/advertise/status/name";
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("statusId",statusId)
		        .encode()
		        .toUriString();
		
		HttpEntity request = new HttpEntity(headers);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<String> response = restTemplate.exchange(urlTemplate,HttpMethod.GET,
				request,
				String.class);
		
		return response;
	}

}
