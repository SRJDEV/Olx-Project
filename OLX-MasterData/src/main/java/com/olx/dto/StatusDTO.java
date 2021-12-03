package com.olx.dto;

public class StatusDTO {
	
	private int id;
	private String status;
	public StatusDTO(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public StatusDTO() {
		super();
		// TODO Auto-generated constructor stub
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
		return "StatusDTO [id=" + id + ", status=" + status + "]";
	}
	
	
	

}
