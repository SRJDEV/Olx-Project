package com.olx.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.CategoryDTO;
import com.olx.dto.CategoryV2DTO;
import com.olx.dto.StatusDTO;
import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;
import com.olx.exception.InvalidStatusIdException;
import com.olx.repository.MasterDataRepo;
import com.olx.repository.StatusRepo;

@Service(value ="JPA_SERVICE_MASTERDATA")
public class MasterDataServiceImpl implements MasterDataService{

	@Autowired
	MasterDataRepo masterRepository;
	
	@Autowired
	StatusRepo statusRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	LoginDelegate loginDlegate;
	

	
	
	
	@Override
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		
		List<CategoryEntity> categoryEntitie = masterRepository.findAll(); 
		
		 Type listType = new	TypeToken<List<CategoryDTO>>(){}.getType();
		
		List<CategoryDTO> categoryDTOList = modelMapper.map(categoryEntitie,listType);
		
		return new ResponseEntity<List<CategoryDTO>>(categoryDTOList,HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<List<CategoryV2DTO>> getCategoriesV2() {
		
		List<CategoryEntity> categoryEntitie = masterRepository.findAll(); 
		
		 Type listType = new	TypeToken<List<CategoryV2DTO>>(){}.getType();
		
		List<CategoryV2DTO> categoryDTOList = modelMapper.map(categoryEntitie,listType);
		
		return new ResponseEntity<List<CategoryV2DTO>>(categoryDTOList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<StatusDTO>> getStatus() {
			
		List<StatusEntity> statusEntities = statusRepo.findAll();
		
		 Type listType = new	TypeToken<List<StatusDTO>>(){}.getType();
		
		List<StatusDTO> statusDTOList = modelMapper.map(statusEntities,listType);
		
		return new ResponseEntity<List<StatusDTO>>(statusDTOList,HttpStatus.OK);
		
	}


	
	@Override
	public ResponseEntity<CategoryDTO> createCategory(String token, CategoryDTO category) {
		
		if(loginDlegate.validateToken(token))
		{
		
		CategoryEntity entity = modelMapper.map(category, CategoryEntity.class);
		
		entity = masterRepository.save(entity);

		CategoryDTO categoryDTO = modelMapper.map(entity, CategoryDTO.class);
		
		return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	

	@Override
	public ResponseEntity<Boolean> validateCategoryId(int categoryId) {
			
		List<CategoryDTO> list =  getCategories().getBody();
		
		for(CategoryDTO category : list) {
			
			if(category.getId() == categoryId) {
				return new  ResponseEntity<Boolean>(true,HttpStatus.OK);
				
			}
		}
		
		return new  ResponseEntity<Boolean>(false,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> validateStatusId(int statusId) {
    
		List<StatusDTO> list =  getStatus().getBody();
		
		for(StatusDTO status : list) {
			
			if(status.getId() == statusId) {
				return new  ResponseEntity<Boolean>(true,HttpStatus.OK);
				
			}
		}
		
		return new  ResponseEntity<Boolean>(false,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getCategoryNameById(int categoryId) {
	
		
		List<CategoryDTO> categoryEntities =getCategories().getBody();
		
		for(CategoryDTO category : categoryEntities) {
			
			if(category.getId() ==  categoryId) {
				
				return new ResponseEntity<String>(category.getName(),HttpStatus.OK);
			}
			
		}
		
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<String> getStatusNameById(int statusId) {
     
		
		List<StatusDTO> statusList =getStatus().getBody();
		
		for(StatusDTO status : statusList) {
			
			if(status.getId() ==  statusId) {
				
				return new ResponseEntity<String>(status.getStatus(),HttpStatus.OK);
			}
			
		}
		
		throw new InvalidStatusIdException();
		
	}


	
	
	}


