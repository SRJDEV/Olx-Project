package com.olx.dto;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("Advertise Model")
public class AdvertiseDetails {

	@ApiModelProperty("Unique advertise id")
	private int advertiseId;
	@ApiModelProperty("Advertise title")
	private String title;
	@ApiModelProperty("Advertise description")
	private String description;
	private Double price;
	@ApiModelProperty("Advertise Status")
	private int status;
	
	@ApiModelProperty("Advertise Status Name")
	private String statusName;

	



	private LocalDate created_date;
	private LocalDate  modified_date;

	private String posted_by;
	private String username;
	

	private int categoryId;
	@ApiModelProperty("Advertise Category Name")
	private String categoryName;
	
	
	private int active;
	
	
	




	public AdvertiseDetails(String title, String description, Double price, int status, String statusName,
			LocalDate created_date, LocalDate modified_date, String posted_by, String username, int categoryId,
			String categoryName, int active) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.status = status;
		this.statusName = statusName;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.posted_by = posted_by;
		this.username = username;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.active = active;
	}


	public AdvertiseDetails(int advertiseId, String title, String description, Double price, int status,
			String statusName, LocalDate created_date, LocalDate modified_date, String posted_by,
			String username, int categoryId, String categoryName, int active) {
		super();
		this.advertiseId = advertiseId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.status = status;
		this.statusName = statusName;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.posted_by = posted_by;
		this.username = username;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.active = active;
	}

	
	public String getStatusName() {
		return statusName;
	}


	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public int getActive() {
		return active;
	}



	public void setActive(int active) {
		this.active = active;
	}



	public int getAdvertiseId() {
		return advertiseId;
	}



	public void setAdvertiseId(int advertiseId) {
		this.advertiseId = advertiseId;
	}







	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


	public LocalDate getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDate created_date) {
		this.created_date = created_date;
	}
	public LocalDate getModified_date() {
		return modified_date;
	}
	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}
	
	public String getPosted_by() {
		return posted_by;
	}
	public void setPosted_by(String posted_by) {
		this.posted_by = posted_by;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public AdvertiseDetails() {
		super();
		// TODO Auto-generated constructor stub
	}







	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "AdvertiseDetails [advertiseId=" + advertiseId + ", title=" + title + ", description=" + description
				+ ", price=" + price + ", status=" + status + ", statusName=" + statusName + ", created_date="
				+ created_date + ", modified_date=" + modified_date + ", posted_by=" + posted_by + ", username="
				+ username + ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", active=" + active
				+ "]";
	}



	

	



}
