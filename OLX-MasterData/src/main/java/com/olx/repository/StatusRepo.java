package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.dto.StatusDTO;
import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;

public interface StatusRepo extends JpaRepository<StatusEntity,Integer> {

	

	
}
