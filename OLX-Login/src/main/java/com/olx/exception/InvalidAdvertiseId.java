package com.olx.exception;

public class InvalidAdvertiseId extends RuntimeException{

	

	private String message;
	
	 @Override
	public String toString() {
		return "InvalidAdvertiseIdException " + message ;
	}

	 public InvalidAdvertiseId(String message) {
			super();
			this.message = message;
		}

	public InvalidAdvertiseId() {
		super();
	}
	
	
	 
	 
}
