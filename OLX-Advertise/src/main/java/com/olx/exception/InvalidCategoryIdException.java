package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidCategoryIdException extends RuntimeException {

	private String message;
	public InvalidCategoryIdException()
	{
		this.message="";
	}
	public InvalidCategoryIdException(String message)
	{
		super();
		this.message=message;
	}
	@Override
	public String toString() {
		return "Invalid Category Id"+this.message;
	}
}
