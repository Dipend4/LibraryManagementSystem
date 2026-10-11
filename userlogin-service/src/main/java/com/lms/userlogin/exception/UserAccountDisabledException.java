package com.lms.userlogin.exception;

public class UserAccountDisabledException extends RuntimeException{

	private static final long serialVersionUID=1L;
	
	public UserAccountDisabledException(String message) {
		
		super(message);
	}
}
