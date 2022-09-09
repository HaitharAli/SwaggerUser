package com.user.swagger.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.swagger.exception.UserNotFoundException;

@RestControllerAdvice
public class UserApplicationExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorsMap = new HashMap();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorsMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorsMap;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
		Map<String, String> errorsMap = new HashMap();
		errorsMap.put("error message",ex.getMessage());
		return errorsMap;
	}
	

}
