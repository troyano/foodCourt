package com.pragma.foodcourt.infrastructure.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pragma.foodcourt.infrastructure.exception.DomainNotificationException;

@ControllerAdvice
public class GlobalExceptionHandler {
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
}
