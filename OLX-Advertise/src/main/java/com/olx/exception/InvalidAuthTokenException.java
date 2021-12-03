package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidAuthTokenException extends RuntimeException {

	private String message =" Invalid Auth Token";
	public InvalidAuthTokenException()
	{
		super();
	}
	public InvalidAuthTokenException(String message)
	{
		super();
		this.message=message;
	}
	@Override
	public String toString() {
		return "InvalidAuthTokenException: "+this.message;
	}
}
