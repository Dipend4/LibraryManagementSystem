package com.lms.userlogin.exception;

public class EmailAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID=1L;
	
	public EmailAlreadyExistsException(String message) {
		
		super(message);
	}
	
	public EmailAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
		
		super("%s not found with %s: '%s'".formatted(resourceName, fieldName, fieldValue));
	}
}
