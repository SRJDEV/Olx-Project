package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.olx.dto.CategoryDTO;
import com.olx.dto.CategoryV2DTO;
import com.olx.dto.StatusDTO;
import com.olx.service.MasterDataService;


import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("olx/masterdata")
public class MasterDataController {

	
	@Autowired
	MasterDataService masterDataService;
	
	@ApiOperation("Get All Categories")
	@GetMapping(value = "/advertise/category",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		return masterDataService.getCategories();
	}
	@ApiOperation("Get Status Of Adverisements")
	@GetMapping(value = "/advertise/status",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StatusDTO>> getStatus(){
		return masterDataService.getStatus();
	}
	
	//version v2
	@ApiOperation("Get All Categories without description")
	@GetMapping(value = "v2/advertise/category",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryV2DTO>> getCategoriesV2(){
		return masterDataService.getCategoriesV2();
	}
	
	
	@ApiOperation("Create New   Category")
	@PostMapping(value = "/advertise/category",produces= MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<CategoryDTO> addCategory(@RequestHeader("Authorization") String token,@RequestBody CategoryDTO categoryDTo){
		return masterDataService.createCategory(token,categoryDTo);
	}
	
	
	
	@ApiOperation("Validate Category id")
	@GetMapping(value = "/advertise/category/validate{categoryId}",consumes = MediaType.APPLICATION_JSON_VALUE,  produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> validateCategoryId(@RequestParam("categoryId") int catgeoryId){
		return masterDataService.validateCategoryId(catgeoryId);
	}
	
	
	@ApiOperation("Validate Status id")
	@GetMapping(value = "/advertise/status/validate{statusId}",consumes = MediaType.APPLICATION_JSON_VALUE,  produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> validateStatusId(@RequestParam("statusId") int statusId){
		return masterDataService.validateStatusId(statusId);
	}
	
	
	@ApiOperation("Get Category Name")
	@GetMapping(value = "/advertise/category/name{categoryId}",consumes = MediaType.APPLICATION_JSON_VALUE,  produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCategoryNameById(@RequestParam("categoryId") int catgeoryId){
		return masterDataService.getCategoryNameById(catgeoryId);
	}
	
	
	@ApiOperation("Get Status Name")
	@GetMapping(value = "/advertise/status/name{statusId}",consumes = MediaType.APPLICATION_JSON_VALUE,  produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStatusNameById(@RequestParam("statusId") int statusId){
		return masterDataService.getStatusNameById(statusId);
	}
	
	
	
	
}
