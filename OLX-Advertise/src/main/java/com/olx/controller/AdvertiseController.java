package com.olx.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.el.parser.AstFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvertiseDetails;
import com.olx.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;

@Qualifier("JPA_SERVICE")
@RestController
@RequestMapping("/olx/adv")
public class AdvertiseController {
	
	public static int lastAdvertiseId = 1;
	private static Map<Integer,AdvertiseDetails> advertiseDetails = new HashMap<>();

	@Autowired
	AdvertiseService advertiseService;
	
//Create New Advertise
	
	@PostMapping(value ="/advertise",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_XML_VALUE})
	@ApiOperation(value ="Create New Advertise")
    public ResponseEntity<AdvertiseDetails> createNewAdvertise(@RequestHeader("Authorization") String token,
    		@RequestBody AdvertiseDetails newAdvertiseDetails) {
			return advertiseService.createNewAdvertise(token,newAdvertiseDetails);
	}

	//Update Advertise
	
	@ApiOperation(value ="Update Advertise")
	@PutMapping(value="/update/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_XML_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_XML_VALUE})
	public ResponseEntity<AdvertiseDetails> updateAdvirtise(@RequestHeader("Authorization") String token,  @PathVariable("id") int advertiseId,@RequestBody AdvertiseDetails advertiseDetails) {
		
		return advertiseService.updateAdvirtise(token,advertiseId, advertiseDetails);
	}
	
	
	
	    //9 Get All advertise by logged in user
		@ApiOperation(value ="Get User Advertise")
		@GetMapping(value="/user/advertise",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_PROBLEM_XML_VALUE})
		public ResponseEntity<List<AdvertiseDetails>> getUserAdvertise(@RequestHeader("Authorization") String authToken) {
			return advertiseService.getUserAdvertise(authToken);
		}
	
		

		// 10 Get Advertise By Id and Logged in user
		
		@ApiOperation(value ="Get User Advertise By Id")
		@GetMapping(value="/user/{advertiseId}",produces= MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<AdvertiseDetails> getUserAdvertiseById(@PathVariable("advertiseId") int advertiseId,@RequestHeader("Authorization") String authToken) {
			return advertiseService.getUserAdvertiseById(advertiseId, authToken);
		}
		
		
		// 14 Get Advertise Details By Id
		
		@ApiOperation(value ="Get User Advertise By Id")
		@GetMapping(value="/user/advertise/{advertiseId}",produces= MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<AdvertiseDetails> getAdvertiseDetails(@PathVariable("advertiseId") int advertiseId,@RequestHeader("Authorization") String authToken) {
			return advertiseService.getAdvertiseDetails(advertiseId, authToken);
		}	
	


	//Delete Advertise
	
	
	@ApiOperation(value ="Delete Advertise By Id")
    @DeleteMapping(value="/user/advertise/{advertiseId}",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> DeleteAdvertiseById(@PathVariable("advertiseId") int advertiseId,@RequestHeader("Authorization") String authToken) {
		return advertiseService.deleteUserAdvertisementById(authToken,advertiseId);
	}
	
	
	
	
	
	
	

	
		//Search Advertise by search text

	@ApiOperation(value ="Search Advertise by text")
    @GetMapping(value="/advertise/search/{searchText}",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdvertiseDetails>> searchAdvertisebasedonFilter(@PathVariable(value = "searchText",required = true)
	String searchText) {
		return advertiseService.getAdvertiseBasedOnSeartchText(searchText);
	
	}
		
	
	//13
	
	@ApiOperation(value ="Search Advertise by filter")
    @GetMapping(value="/advertise/search/filtercriteria",produces= MediaType.APPLICATION_JSON_VALUE)
	public Collection<AdvertiseDetails> searchAdvertisebasedonFilter(@RequestParam(value = "searchText",required = false) String searchText
			,@RequestParam(value = "category",required = false ) int category,
			@RequestParam(value = "postedBy",required = false) String postedBy,
			@RequestParam(value = "dateCondition",required = false) String dateCondition, 
			
			@RequestParam(value = "onDate",required = false)
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") LocalDate onDate ,
			
	        @RequestParam(value = "fromDate",required = false)
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") LocalDate fromDate ,
		
			@RequestParam(value = "toDate",required = false)
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") LocalDate toDate ,
			
			@RequestParam(value = "sortBy",required = false) int sortBy,
			@RequestParam(value = "startIndex",required = false) int startIndex,
			@RequestParam(value = "records",required = false) String records) {
		return advertiseService.searchAdvertisebasedonFilter(searchText, category, postedBy, dateCondition, onDate, fromDate, toDate, sortBy, startIndex, records);
	}
	
	

	
	


}
