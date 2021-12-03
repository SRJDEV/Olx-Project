package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.AdvertisementEntity;


public interface AdvertiseRepo extends JpaRepository<AdvertisementEntity, Integer>  {

	List<AdvertisementEntity> findByAdvertiseId(int id);
	
	AdvertisementEntity findByAdvertiseIdAndUsername(int advertiseId,String userName);
	
	List<AdvertisementEntity> findByUsernameIgnoreCase(String userName);
	
	//search
	List<AdvertisementEntity> findByTitleContaining(String searchText);
	List<AdvertisementEntity> findByTitleContainingOrDescriptionContaining(String searchText,String searchText1);
	
	
	List<AdvertisementEntity> findByTitleContainingAndDescriptionContaining(String searchText,String searchText1);
	

	
	
	
	
}
