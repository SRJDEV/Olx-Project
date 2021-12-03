package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value= {InvalidAdvertiseIdException.class,InvalidStatusIdException.class,
			InvalidStatusIdException.class})
	public ResponseEntity<Object> handleInvalidStockIdError(RuntimeException exception,WebRequest request)
	{
		return handleExceptionInternal(exception,exception.toString(),new HttpHeaders(),
				HttpStatus.BAD_REQUEST,request);
	}
	
	
	@ExceptionHandler(value= {InvalidAuthTokenException.class})
	public ResponseEntity<Object> handleInvalidTokenError(RuntimeException exception,WebRequest request)
	{
		return handleExceptionInternal(exception,exception.toString(),new HttpHeaders(),
				HttpStatus.UNAUTHORIZED,request);
	}
	
	
	@ExceptionHandler(value= {InvalidCategoryIdException.class})
	public ResponseEntity<Object> handleInvalidCategoryIdError(RuntimeException exception,WebRequest request)
	{
		return handleExceptionInternal(exception,exception.toString(),new HttpHeaders(),
				HttpStatus.BAD_REQUEST,request);
	}
	
	
	
	
	
	
}
