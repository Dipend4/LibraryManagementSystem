package com.lms.userdetail.exception;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private ProblemDetail createProblem(HttpStatus status, String detail) {
		
		ProblemDetail problem= ProblemDetail.forStatusAndDetail(status, detail);
		problem.setTitle(status.getReasonPhrase());
		problem.setProperty("timestamp", OffsetDateTime.now());
		return problem;
	}
	
	@ExceptionHandler(PhoneAlreadyExistsException.class)
	public ResponseEntity<ProblemDetail> handlePhoneAlreadyExists(PhoneAlreadyExistsException exception){
		
		ProblemDetail problem= createProblem(HttpStatus.CONFLICT, exception.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
	}
	
	@ExceptionHandler(UserDetailAlreadyExistsException.class)
	public ResponseEntity<ProblemDetail> handleUserDetailAlreadyExists(UserDetailAlreadyExistsException exception){
		
		ProblemDetail problem= createProblem(HttpStatus.CONFLICT, exception.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
	}
	
	@ExceptionHandler(UserDetailNotFoundException.class)
	public ResponseEntity<ProblemDetail> handleUserDetailNotFound(UserDetailNotFoundException exception){
		
		ProblemDetail problem= createProblem(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
	}
	
	//DTO validation fail हुँदा
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
		
		Map<String , String> errors= new LinkedHashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		ProblemDetail problem= createProblem(HttpStatus.BAD_REQUEST, 
				"Validation failed. Please check the input fields.");
		return ResponseEntity.badRequest().body(problem);
				
	}
	
	//JSON गलत हुँदा वा enum value नमिल्दा
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ProblemDetail> handleHttpMessageNotReadable(HttpMessageNotReadableException exception){
		
		log.warn("Invalid request body received");
		
		ProblemDetail problem= createProblem(HttpStatus.BAD_REQUEST, 
				"Invalid request body. Check the JSON format and field values.");
		return ResponseEntity.badRequest().body(problem);
	}
	
	//URL parameter को type नमिल्दा
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ProblemDetail> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception){
		
		String message= "Invalid value for parameter '%s'.".formatted(exception.getName());
		ProblemDetail problem= createProblem(HttpStatus.BAD_REQUEST, message);
		log.warn("Invalid value supplied for parameter '{}'", exception.getName());
		return ResponseEntity.badRequest().body(problem);
	}
	
	//Database को unique, foreign key वा अन्य integrity constraint उल्लङ्घन हुँदा
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ProblemDetail> handleDataIntegrityViolation(DataIntegrityViolationException exception){
		
		log.warn("Database constraint violation occurred.");
		ProblemDetail problem= createProblem(HttpStatus.CONFLICT, "The request conflicts with existing data.");
		problem.setTitle("Data conflict");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
	}
	
	//अपेक्षा नगरिएको server error हुँदा
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleGeneralException(Exception exception){
		
		log.warn("Unexpected application error occurred", exception);
		ProblemDetail problem= createProblem(HttpStatus.INTERNAL_SERVER_ERROR,
				"An unexpected error occurred. Please try again later.");
		problem.setTitle("Internal Server Error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
	}
}
