package com.lms.studentservice.exception;

public class ResourceNotFoundException extends RuntimeException{

	//यो Java serialization सँग सम्बन्धित identifier हो। Exception class मा serialization compatibility सम्बन्धी 
	//warning रोक्न प्रयोग गरिन्छ। यसको मुख्य business logic सँग प्रत्यक्ष सम्बन्ध छैन।
	private static final long serialVersionUID=1L;
	
	public ResourceNotFoundException(String message) {
		
		super(message);
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		
		super("%s not found with %s: '%s'" .formatted(resourceName, fieldName, fieldValue));
	}
		
	
}
