package com.olx.entity;

import java.time.LocalDate;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name ="advertise")
public class AdvertisementEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int advertiseId;
	
	@Column(name = "advertise_title")
	private String title;
	
	@Column(name = "advertise_desc")
	private String description;
	
	@Column(name = "advertise_price")
    private Double price;
	
	@Column(name = "advertise_status")
	private int status;

	@Column(name = "advertise_creation_date")
	private LocalDate created_date;
	
	@Column(name = "advertise_modified_date")
	private LocalDate  modified_date;
	
	@Column(name = "advertise_posted_by")
	private String posted_by;
	
	@Column(name = "advertise_username")
	private String username;
	
	@Column(name = "advertise_category_id")
	private int categoryId;
	
	@Column(name = "active")
	private int active;


	public AdvertisementEntity(int advertiseId, String title, String description, Double price, int status,
			LocalDate created_date, LocalDate modified_date, String posted_by, String username,int categoryId,int active) {
		super();
		this.advertiseId = advertiseId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.status = status;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.posted_by = posted_by;
		this.username = username;
		this.categoryId = categoryId;
		this.active = active;
		
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

	public void setPrice(Double price) {
		this.price = price;
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

	public AdvertisementEntity() {
		super();
		// TODO Auto-generated constructor stub
	}





	@Override
	public String toString() {
		return "AdvertisementEntity [advertiseId=" + advertiseId + ", title=" + title + ", description=" + description
				+ ", price=" + price + ", status=" + status + ", created_date=" + created_date + ", modified_date="
				+ modified_date + ", posted_by=" + posted_by + ", username=" + username + ", categoryId=" + categoryId
				+ ", active=" + active + "]";
	}


	
	

}
