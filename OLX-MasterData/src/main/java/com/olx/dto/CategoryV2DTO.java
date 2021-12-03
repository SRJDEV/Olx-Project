package com.olx.dto;



public class CategoryV2DTO {

	
	private int id;
	private String name;

	
	
	
	public CategoryV2DTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryV2DTO(String name, String description) {
		super();
		this.name = name;
	
	}

	public CategoryV2DTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + "]";
	}

	
	
}
