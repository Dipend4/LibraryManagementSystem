package com.lms.studentservice.exception;

public class StudentValidationException extends RuntimeException{

	private static final long serialVersionUID= 1L;
	
	public StudentValidationException(String message) {
		
		super(message);
	}
}
