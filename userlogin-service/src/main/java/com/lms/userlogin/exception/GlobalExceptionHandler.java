package com.lms.userlogin.exception;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	public static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private ProblemDetail createProblem(HttpStatus status, String detail) {
		
		ProblemDetail problem=ProblemDetail.forStatusAndDetail(status, detail);
		
		problem.setTitle(status.getReasonPhrase());
		problem.setProperty("timestamp", OffsetDateTime.now());
		return problem;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ProblemDetail> handleUserNotFound(UserNotFoundException exception){
		
		ProblemDetail problem= createProblem(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ProblemDetail> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception){
		
		ProblemDetail problem=createProblem(HttpStatus.CONFLICT, exception.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
	}
	
	 // User Account Disabled
    @ExceptionHandler(UserAccountDisabledException.class)
    public ResponseEntity<ProblemDetail> handleUserAccountDisabled(
            UserAccountDisabledException ex) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problem.setTitle("User Account Disabled");

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(problem);
    }
    
	//DTO validation fail भएको
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
			
			Map<String , String> errors= new LinkedHashMap<>();
			exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
			
			ProblemDetail problem= createProblem(HttpStatus.BAD_REQUEST, "Validation failed. Please check the input fields.");
			problem.setProperty("errors", errors);
			return ResponseEntity.badRequest().body(problem);
		}
		
		 // Unexpected Errors
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ProblemDetail> handleGeneralException(
	            Exception ex) {

	        log.error("Unexpected application error occurred.", ex);

	        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
	                HttpStatus.INTERNAL_SERVER_ERROR,
	                "An unexpected error occurred. Please try again later.");

	        problem.setTitle("Internal Server Error");

	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(problem);
	    }
	    
	    // Database Constraint Errors
	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public ResponseEntity<ProblemDetail> handleDataIntegrityViolation(
	            DataIntegrityViolationException ex) {

	        log.warn("Database constraint violation occurred.");

	        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
	                HttpStatus.CONFLICT,
	                "The request conflicts with existing data."
	        );

	        problem.setTitle("Data Conflict");

	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body(problem);
	    }
}
