package com.olx.service;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.olx.dto.AdvertiseDetails;



public interface AdvertiseService {

	
	

	 public ResponseEntity<AdvertiseDetails> createNewAdvertise(String token,@RequestBody AdvertiseDetails newAdvertiseDetails);
	 
	 public ResponseEntity<AdvertiseDetails> updateAdvirtise(String token,int advertiseId,AdvertiseDetails advertiseDetails);
	 
	 //8 get single advertise
	 public ResponseEntity<AdvertiseDetails> getUserAdvertiseById(int advertiseId, String authToken);
	 
	 //14 get  advertise details
	 public ResponseEntity<AdvertiseDetails> getAdvertiseDetails(int advertiseId, String authToken);
	 

	
	 public ResponseEntity<Boolean> deleteUserAdvertisementById(String token, int advertiseId);
	 
	//9 All advertisement posted by user
	 public ResponseEntity<List<AdvertiseDetails>> getUserAdvertise(String authToken);
	 
	 //search
	 public ResponseEntity<List<AdvertiseDetails>> getAdvertiseBasedOnSeartchText(String searchText);
	 
	 

	
	 public Collection<AdvertiseDetails> searchAdvertisebasedonFilter(String searchText,int category,String postedBy,String dateCondition, 
			 LocalDate onDate ,LocalDate fromDate,LocalDate toDate,
				 int sortBy, int startIndex,String records);

	
	 

}
