package com.lms.userdetail.exception;

public class PhoneAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID= 1L;
	
	public PhoneAlreadyExistsException(String message) {
		
		super(message);
	}
	
	public PhoneAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
		
		super("%s not found with %s: '%s'".formatted(resourceName, fieldName, fieldValue));
	}
}
