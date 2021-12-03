package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidDateFormatException extends RuntimeException {

	private String message;
	public InvalidDateFormatException()
	{
		this.message="";
	}
	public InvalidDateFormatException(String message)
	{
		super();
		this.message=message;
	}
	@Override
	public String toString() {
		return "Invalid date and formatd"+this.message;
	}
}