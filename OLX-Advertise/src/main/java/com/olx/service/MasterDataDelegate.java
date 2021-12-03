package com.olx.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface MasterDataDelegate {
	
	public List<Map> getCategories();
	public List<Map> getStatus();
	public List<Map> getCategoryById();
	
	public Boolean validateCategoryId(int categoryId);
	public Boolean validateStatusId(int statusId);
	
	public ResponseEntity<String> getCategoryNameById(int categoryId);
	
	public ResponseEntity<String> getStatusNameById(int statusId);

	
}
