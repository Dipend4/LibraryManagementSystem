package com.lms.userlogin.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long  serialVersionUID= 1L;
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		
		super("%s not found with %s: '%s'" .formatted(resourceName, fieldName, fieldValue));

	}
	
}
