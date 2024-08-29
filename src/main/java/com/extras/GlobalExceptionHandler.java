package com.extras;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		StringBuilder errorMessage = new StringBuilder();
		for (FieldError fieldError : result.getFieldErrors()) {
			errorMessage.append(fieldError.getDefaultMessage()).append("\n");
		}
		return ResponseEntity.badRequest().body(errorMessage.toString());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("An error occurred: " + ex.getMessage() + "\n");
	}
}
