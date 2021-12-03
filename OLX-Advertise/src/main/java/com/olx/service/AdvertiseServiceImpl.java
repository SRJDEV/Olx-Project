package com.olx.service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.olx.dto.AdvertiseDetails;
import com.olx.entity.AdvertisementEntity;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.exception.InvalidStatusIdException;
import com.olx.repository.AdvertiseRepo;




@Service(value = "JPA_SERVICE")
public class AdvertiseServiceImpl implements AdvertiseService {
	
	
	@Autowired
	AdvertiseRepo adRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	MasterDataDelegate masterDataDelegate;


	@Autowired
	LoginDelegate loginDelegate;
	
	@Autowired
	EntityManager entityManager;
	
	

	
	
	//Create New Advertise
	@Override
	public ResponseEntity<AdvertiseDetails> createNewAdvertise(String token,AdvertiseDetails newAdvertiseDetails) {
	
	try {
	if(loginDelegate.validateToken(token)) {
	
		
	   Boolean isCategoryIdValid =	masterDataDelegate.validateCategoryId(newAdvertiseDetails.getCategoryId());
	 
		
	     if(isCategoryIdValid)
	     {
	    	  Boolean isStatusIdValid =	masterDataDelegate.validateStatusId(newAdvertiseDetails.getStatus());
	       if(isStatusIdValid)
	       {
	    	
	    	 String  userName = loginDelegate.getUserName(token);
	    	newAdvertiseDetails.setUsername(userName);
	    	newAdvertiseDetails.setPosted_by(userName);
			newAdvertiseDetails.setCreated_date(LocalDate.now());
			newAdvertiseDetails.setModified_date(LocalDate.now());
		
			AdvertisementEntity advertentity = this.modelMapper.map(newAdvertiseDetails, AdvertisementEntity.class);
			
			advertentity = adRepository.save(advertentity);
			
			AdvertiseDetails adDetails = this.modelMapper.map(advertentity, AdvertiseDetails.class);
			
			
			  String categoryName =	masterDataDelegate.getCategoryNameById(adDetails.getCategoryId()).getBody();
			  String statusName =	masterDataDelegate.getStatusNameById(adDetails.getStatus()).getBody();
			  
			  adDetails.setCategoryName(categoryName);
			  adDetails.setStatusName(statusName);
			
			return new ResponseEntity<>(adDetails,HttpStatus.OK);
		    
	       }
	    	 else {
	    		 throw new InvalidStatusIdException();
	    	 }
	    }
	    
	     else {
	    		throw new InvalidCategoryIdException();
	     }
	
	}else {
		throw new InvalidAuthTokenException();
		}
	}catch (Exception e) {
		throw e;
	}
}
	
	//Update  Advertise
	@Override
	public ResponseEntity<AdvertiseDetails> updateAdvirtise(String token,int advertiseId, AdvertiseDetails advertiseDetails) {
		
		
		if(loginDelegate.validateToken(token))
		{
        Optional<AdvertisementEntity> optionalAdvertiseEntity = adRepository.findById(advertiseId);
		try {
        if(optionalAdvertiseEntity.isPresent()) {
        
    
            Boolean isCategoryIdValid =	masterDataDelegate.validateCategoryId(advertiseDetails.getCategoryId());
        	 
    	     if(isCategoryIdValid)
    	     {
    	    	  Boolean isStatusIdValid =	masterDataDelegate.validateStatusId(advertiseDetails.getStatus());
    	       if(isStatusIdValid)
    	       {
    	
        	
			        AdvertisementEntity adEntity = optionalAdvertiseEntity.get();
			      
			        advertiseDetails.setAdvertiseId(advertiseId);
			        advertiseDetails.setCreated_date(adEntity.getCreated_date());
			        advertiseDetails.setModified_date(LocalDate.now());
			        advertiseDetails.setCategoryId(adEntity.getCategoryId());
			        advertiseDetails.setActive(adEntity.getActive());
			        advertiseDetails.setPosted_by(adEntity.getPosted_by());
			        advertiseDetails.setUsername(adEntity.getUsername());
			  
			        adEntity= this.modelMapper.map(advertiseDetails, AdvertisementEntity.class);
					
			        adEntity = adRepository.save(adEntity);
			        
			        
					AdvertiseDetails adDetails = this.modelMapper.map(adEntity, AdvertiseDetails.class);
					
					
					  String categoryName =	masterDataDelegate.getCategoryNameById(adDetails.getCategoryId()).getBody();
					  String statusName =	masterDataDelegate.getStatusNameById(adDetails.getStatus()).getBody();
					  
					  adDetails.setCategoryName(categoryName);
					  adDetails.setStatusName(statusName);
					
					  return new ResponseEntity<>(adDetails,HttpStatus.OK);
    	       }else {
    	    	   
    	    	   throw new InvalidStatusIdException();
    	       }
    	     }else {
  	    	      throw new InvalidCategoryIdException();
  	       }
        }
        else {
        	throw new InvalidAdvertiseIdException();
        }
        
	}catch (Exception e) {
		 throw new InvalidAdvertiseIdException();
	}
		}
		
		throw new InvalidAuthTokenException();
	}
	
	// Get Single Advertise By Id and Logged in user
	@Override
	public ResponseEntity<AdvertiseDetails> getUserAdvertiseById(int advertiseId, String authToken) {
	
		  String userName = loginDelegate.getUserName(authToken);
			if(!userName.isEmpty()) {
			try {
			AdvertisementEntity advertiseEntity = adRepository.findByAdvertiseIdAndUsername(advertiseId,userName);
		
		   String categoryName =	masterDataDelegate.getCategoryNameById(advertiseEntity.getCategoryId()).getBody();
		   String statusName =	masterDataDelegate.getStatusNameById(advertiseEntity.getStatus()).getBody();
	
			
	        AdvertiseDetails adDetails = this.modelMapper.map(advertiseEntity, AdvertiseDetails.class);
			
			adDetails.setCategoryName(categoryName);
			adDetails.setStatusName(statusName);
			return new ResponseEntity<>(adDetails,HttpStatus.OK);
	        
			
	        
		}catch (Exception e) {
			 throw new InvalidAdvertiseIdException();
		}
	}else {
		 throw new InvalidAuthTokenException();
	}
		 
}
	
	
	//9 All advertisement posted by user
	
		@Override
		public ResponseEntity<List<AdvertiseDetails>> getUserAdvertise(String authToken) {
			if(loginDelegate.validateToken(authToken)) {
				
				try {

					
				String userName = loginDelegate.getUserName(authToken);
				
				if(!userName.isEmpty()) {
					
				List<AdvertisementEntity> advertiseEntityList = adRepository.findByUsernameIgnoreCase(userName);
			   
			    Type listType = new	TypeToken<List<AdvertiseDetails>>(){}.getType();
			   
				List<AdvertiseDetails> adDetails = this.modelMapper.map(advertiseEntityList,listType);
				
				for(AdvertiseDetails advertise :adDetails) {
					
					  String categoryName =	masterDataDelegate.getCategoryNameById(advertise.getCategoryId()).getBody();
					  String statusName =	masterDataDelegate.getStatusNameById(advertise.getStatus()).getBody();
					  
					  advertise.setCategoryName(categoryName);
					  advertise.setStatusName(statusName);
				
				}
				
		
				return new ResponseEntity<List<AdvertiseDetails>>(adDetails,HttpStatus.OK);
				}
			 
		  
		        
			}catch (Exception e) {
				 throw new InvalidAdvertiseIdException();
			}
		}
			throw new InvalidAuthTokenException();
			
			
		}
		
		
		// Get Advertise Details
		@Override
		public ResponseEntity<AdvertiseDetails> getAdvertiseDetails(int advertiseId, String authToken) {
			if(loginDelegate.validateToken(authToken)) {
				try {
			Optional<AdvertisementEntity> advertiseEntity = adRepository.findById(advertiseId);
			
		        if(advertiseEntity.isPresent()) {
		        
		         AdvertisementEntity entity = advertiseEntity.get();
		         
		          String categoryName =	masterDataDelegate.getCategoryNameById(entity.getCategoryId()).getBody();
				  
				  String statusName =	masterDataDelegate.getStatusNameById(entity.getStatus()).getBody();
			
		         
				  AdvertiseDetails adDetails = this.modelMapper.map(entity, AdvertiseDetails.class);
				 
				 adDetails.setCategoryName(categoryName);
				 adDetails.setStatusName(statusName);
				return new ResponseEntity<>(adDetails,HttpStatus.OK);
			   }
		        else {
		        	throw new InvalidAdvertiseIdException();
		        }
		        
				
		        
			}catch (Exception e) {
				 throw new InvalidAdvertiseIdException();
			}
		}else {
			 throw new InvalidAuthTokenException();
		}
				
		
	}

		
	
	//Delete advertisement by ID
	

	@Override
	public ResponseEntity<Boolean> deleteUserAdvertisementById(String token, int advertiseId) {
		
		
		Optional<AdvertisementEntity> optionalAdvertiseEntity = adRepository.findById(advertiseId);
		
		if(optionalAdvertiseEntity.isPresent()) {
		 	 
	    adRepository.deleteById(advertiseId);
		
	    
	  
		return new ResponseEntity<>(!adRepository.findById(advertiseId).isPresent(),HttpStatus.OK);
		}
	
		 throw new InvalidAdvertiseIdException();
		
	}
	
	
	
	//Search advertise by search text
	
	@Override
	public ResponseEntity<List<AdvertiseDetails>> getAdvertiseBasedOnSeartchText(String searchText) {
		
		
		try {
			    //List<AdvertisementEntity> advertiseEntityList = adRepository.findByTitleContaining(searchText);
			
		       List<AdvertisementEntity> advertiseEntityList = adRepository.findByTitleContainingOrDescriptionContaining(searchText,searchText);
		    
			    Type listType = new	TypeToken<List<AdvertiseDetails>>(){}.getType();
			   
				List<AdvertiseDetails> adDetails = this.modelMapper.map(advertiseEntityList,listType);
				
				
				for(AdvertiseDetails advertise :adDetails) {
					
					  String categoryName =	masterDataDelegate.getCategoryNameById(advertise.getCategoryId()).getBody();
					  String statusName =	masterDataDelegate.getStatusNameById(advertise.getStatus()).getBody();
					  
					  advertise.setCategoryName(categoryName);
					  advertise.setStatusName(statusName);
				
				}
				
		
				return new ResponseEntity<List<AdvertiseDetails>>(adDetails,HttpStatus.OK);
			 
		  
		        
			}catch (Exception e) {
				 throw new InvalidAdvertiseIdException();
			}
		
	}

	
	// search by filter criteria


	@Override
	public List<AdvertiseDetails> searchAdvertisebasedonFilter(String searchText, int categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, int sortBy, int startIndex,
			String records) {
		
		List<AdvertiseDetails> advertiseList = new ArrayList<AdvertiseDetails>();
		if (masterDataDelegate.validateCategoryId(categoryId)) {
		
			
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertisementEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertisementEntity.class);
		Root<AdvertisementEntity> rootEntity = criteriaQuery.from(AdvertisementEntity.class);


		Predicate titlePredicate = criteriaBuilder.like(rootEntity.get("title"), "%" + searchText + "%"); //like query
		Predicate categoryPredicate = criteriaBuilder.equal(rootEntity.get("categoryId"), categoryId);
		Predicate postedByPredicate = criteriaBuilder.equal(rootEntity.get("posted_by"), postedBy);


		Predicate dateCondationPredicate = null;
		if (dateCondition.equalsIgnoreCase("Between")) {
		dateCondationPredicate = criteriaBuilder.between(rootEntity.get("created_date"), fromDate, toDate);
		} 
		else if (dateCondition.equalsIgnoreCase("equals")) {
			dateCondationPredicate = criteriaBuilder.equal(rootEntity.get("created_date"), onDate);
		} 
		else if (dateCondition.equalsIgnoreCase("lessthan")) {
		dateCondationPredicate = criteriaBuilder.lessThan(rootEntity.get("created_date"), fromDate);
		} else if (dateCondition.equalsIgnoreCase("greaterThan")) {
		dateCondationPredicate = criteriaBuilder.greaterThan(rootEntity.get("created_date"), fromDate);
		}


		Predicate finalPredicate = criteriaBuilder.and(titlePredicate, categoryPredicate, postedByPredicate,dateCondationPredicate);
		criteriaQuery.where(finalPredicate);
		TypedQuery<AdvertisementEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		List<AdvertisementEntity> advertiseEntityList = typedQuery.getResultList();
		 
		Type listType = new	TypeToken<List<AdvertiseDetails>>(){}.getType();
		   
		advertiseList = modelMapper.map(advertiseEntityList, listType);

		

 		}
		

		return advertiseList;
		
		
	}





}
