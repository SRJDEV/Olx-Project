package com.olx.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.UserEntity;



public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	List<UserEntity>findUserByUsername(String userName);
	int findIdByUsername(String username);
}
