package com.olx.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.UserDocument;

public interface UserMongoRepo extends MongoRepository<UserDocument, Integer> {

	  List<UserDocument> findByToken(String token);
	 
}
