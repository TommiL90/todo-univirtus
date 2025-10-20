package com.univirtus.todo.demo.shared.api;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
		var error = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), OffsetDateTime.now(), List.of());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		List<ApiFieldError> fieldErrors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(this::mapFieldError)
				.toList();
		var error = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação", OffsetDateTime.now(),
				fieldErrors);
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
		List<ApiFieldError> fieldErrors = ex.getConstraintViolations()
				.stream()
				.map(violation -> new ApiFieldError(violation.getPropertyPath().toString(), violation.getMessage()))
				.toList();
		var error = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação", OffsetDateTime.now(),
				fieldErrors);
		return ResponseEntity.badRequest().body(error);
	}

	private ApiFieldError mapFieldError(FieldError fieldError) {
		return new ApiFieldError(fieldError.getField(), fieldError.getDefaultMessage());
	}

	public record ApiErrorResponse(int status, String message, OffsetDateTime timestamp, List<ApiFieldError> errors) {
	}

	public record ApiFieldError(String field, String error) {
	}

}
