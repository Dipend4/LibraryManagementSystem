package com.lms.userdetail.exception;

public class UserDetailNotFoundException extends RuntimeException{

	private static final long serialVersionUID= 1L;
	
	public UserDetailNotFoundException(String message) {
		
		super(message);
	}
	
	public UserDetailNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		
		super("%s not found with %s: '%s'".formatted(resourceName, fieldName, fieldValue));
	}
}
