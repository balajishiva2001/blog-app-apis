package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.utils.ApiResponse;

@RestControllerAdvice
public class BlogAppExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(exception.getMessage(), false), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgsNotvalidExceptionHandler(
			MethodArgumentNotValidException exception) {
		Map<String, String> resMap = new HashMap<>();

		exception.getBindingResult().getAllErrors().forEach(e -> {
			String fielName = ((FieldError) e).getField();
			String message = e.getDefaultMessage();
			resMap.put(fielName, message);
		});

		return new ResponseEntity<Map<String, String>>(resMap, HttpStatus.BAD_REQUEST);
	}

}
