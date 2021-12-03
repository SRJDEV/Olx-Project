package com.olx.exception;



public class InvalidCategoryId extends RuntimeException{

	

	private String message;
	
	 @Override
	public String toString() {
		return "InvalidCategoryIdException " + message ;
	}

	 public InvalidCategoryId(String message) {
			super();
			this.message = message;
		}

	public InvalidCategoryId() {
		super();
	}
	
	
	 
	 
}
