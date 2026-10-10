package com.lms.studentservice.exception;

public class DuplicateResourceException extends RuntimeException{
	
	private static final long serialVersionUID= 1L;
	
	public DuplicateResourceException(String message) {
		
		super(message);
	}
	
	public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
		
		super("%s already exists with %s: '%s'" .formatted(resourceName, fieldName, fieldValue));
	}
}
