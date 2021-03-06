package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Status")
public class StatusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	@Column(name = "status")
	private String status;
	
	
	
	public StatusEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatusEntity(String status) {
		super();
		this.status = status;
	}
	public StatusEntity(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "StatusEntity [id=" + id + ", status=" + status + "]";
	}
	
	
	
	
}
