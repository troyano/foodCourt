package com.pragma.foodcourt.infrastructure.exceptionhandler;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pragma.foodcourt.domain.exception.ResourceNotFoundException;
import com.pragma.foodcourt.domain.exception.ValidationException;
import com.pragma.foodcourt.infrastructure.exception.DomainNotificationException;
import com.pragma.foodcourt.infrastructure.exception.NoDataFoundException;

@ControllerAdvice
public class ControllerAdvisor {

	private static final String MESSAGE = "message";

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Map<String, String>> handleNoDataFoundException(
			NoDataFoundException ignoredNoDataFoundException) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
	}

	@ExceptionHandler(DomainNotificationException.class)
	public ResponseEntity<String> handleDomainNotificationException(DomainNotificationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult().getFieldErrors().stream().findFirst()
				.map(error -> error.getDefaultMessage()).orElse("Validation error");
		return ResponseEntity.badRequest().body(errorMessage);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<String> handleValidationException(ValidationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
