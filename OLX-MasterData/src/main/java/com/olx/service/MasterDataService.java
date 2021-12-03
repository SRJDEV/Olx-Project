package com.olx.service;

import java.util.List;

import org.springframework.http.ResponseEntity;


import com.olx.dto.CategoryDTO;
import com.olx.dto.CategoryV2DTO;
import com.olx.dto.StatusDTO;
import com.olx.entity.CategoryEntity;

import ch.qos.logback.core.status.Status;

public interface MasterDataService {
	
	public ResponseEntity<List<CategoryDTO>> getCategories();
	
	public ResponseEntity<List<CategoryV2DTO>> getCategoriesV2();
	
	public ResponseEntity<Boolean> validateCategoryId(int categoryId);
	public ResponseEntity<Boolean> validateStatusId(int statusId);

	public ResponseEntity<String> getCategoryNameById(int categoryId);
	
	public ResponseEntity<String> getStatusNameById(int statusId);
	
	public ResponseEntity<List<StatusDTO>> getStatus();
	
	public ResponseEntity<CategoryDTO> createCategory(String token,CategoryDTO category);

}
