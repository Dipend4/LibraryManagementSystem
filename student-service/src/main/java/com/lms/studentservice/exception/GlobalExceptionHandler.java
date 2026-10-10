package com.lms.studentservice.exception;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//Error response को विवरण राख्ने
	private ProblemDetail createProblem(HttpStatus status, String detail) {
		
		ProblemDetail problem= ProblemDetail.forStatusAndDetail(status, detail);
		problem.setTitle(status.getReasonPhrase());
		problem.setProperty("timestamp", OffsetDateTime.now());
		return problem;
	}
	
	//Student नभेटिएको exception व्यक्त गर्ने
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ProblemDetail> handleNotFound(ResourceNotFoundException exception){
		
		log.warn("Student resource not found.");
		ProblemDetail problem= createProblem(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
	}
	
	//Student business rule उल्लङ्घन भएको व्यक्त गर्ने
	@ExceptionHandler(StudentValidationException.class)
	public ResponseEntity<ProblemDetail> handleStudentValidation(StudentValidationException exception){
		
		log.warn("Student business validation failed.");
		ProblemDetail problem= createProblem(HttpStatus.BAD_REQUEST, exception.getMessage());
		return ResponseEntity.badRequest().body(problem);
	}
	
	//Duplicate resource भएको व्यक्त गर्ने
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ProblemDetail> handleDuplicationResource(DuplicateResourceException exception){
		
		log.warn("Duplicate student resource detected.");
		ProblemDetail problem= createProblem(HttpStatus.CONFLICT, exception.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
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
	
	//Parameter validation fail भएको
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ProblemDetail> handleConstraintViolation(ConstraintViolationException exception){
		
		ProblemDetail problem= createProblem(HttpStatus.BAD_REQUEST, "Request parameter validation failed.");
		return ResponseEntity.badRequest().body(problem);
	}
	
	//गलत parameter type आएको
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ProblemDetail> handleTypeMismatch(MethodArgumentTypeMismatchException exception){
		
		ProblemDetail problem= createProblem(HttpStatus.BAD_REQUEST, 
				"Invalid value provided for parameter:" +exception.getName());
		return ResponseEntity.badRequest().body(problem);
		
	}
	
	//Invalid JSON वा request body
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ProblemDetail> handleInvalidJson(HttpMessageNotReadableException exception){
		
		ProblemDetail problem= createProblem(HttpStatus.BAD_REQUEST, "Invalid request body. Please provide valid JSON");
		return ResponseEntity.badRequest().body(problem);
	}
	
	//Database constraint fail भएको
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ProblemDetail> handleDataIntegrityViolation(DataIntegrityViolationException exception){
		
		log.warn("Database integrity constraint violation");
		ProblemDetail problem= createProblem(HttpStatus.CONFLICT, "The request conflicts with existing data or "
				+ "database constraints.");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
	}
	
	//अप्रत्याशित server error
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleUnexpectedException(Exception exception){
		
		log.error("Unexpected error occured while processing the request.", exception);
		ProblemDetail problem=createProblem(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occured. "
				+ "Please try again later.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
	}
}
