package com.lms.userdetail.exception;

public class UserDetailAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID= 1L;
	
	public UserDetailAlreadyExistsException(String message) {
		super(message);
	}
	
	public UserDetailAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
		
		super("%s not found with %s: '%s'".formatted(resourceName, fieldName, fieldValue));
	}
}
