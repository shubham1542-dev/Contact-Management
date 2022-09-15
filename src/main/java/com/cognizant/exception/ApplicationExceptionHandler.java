package com.cognizant.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> UserNotFoundExceptionHandler(UserNotFoundException ex) {
		ErrorResponse er = new ErrorResponse();
		er.setMessage(ex.getMessage());
		er.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}

	@ExceptionHandler(DataAlreayExistException.class)
	public ResponseEntity<ErrorResponse> DataAlreayExistExceptionHandler(DataAlreayExistException ex) {
		ErrorResponse er = new ErrorResponse();
		er.setMessage(ex.getMessage());
		er.setHttpStatusCode(HttpStatus.CONFLICT.value());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(er);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> HttpRequestMethodNotSupportedExceptionHandler(
			HttpRequestMethodNotSupportedException ex) {
		ErrorResponse er = new ErrorResponse();
		er.setMessage(ex.getMessage());
		er.setHttpStatusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(er);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorResponse>> MethodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException ex) {
		List<ErrorResponse> errors = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			ErrorResponse er = new ErrorResponse();
			er.setMessage(error.getDefaultMessage());
			er.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
			errors.add(er);
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> MethodArgumentTypeMismatchExceptionHandler(
			MethodArgumentTypeMismatchException ex) {
		ErrorResponse er = new ErrorResponse();
		er.setMessage("Invalid Argument Type !");
		er.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}

}