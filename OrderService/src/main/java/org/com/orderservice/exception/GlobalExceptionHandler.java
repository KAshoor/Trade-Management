package org.com.orderservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> badRequest(ValidationException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<?> notFound(OrderNotFoundException ex) {
		return ResponseEntity.status(404).body(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> generic(Exception ex) {
	return ResponseEntity.status(500).body("Internal Server Error");
	}
}
