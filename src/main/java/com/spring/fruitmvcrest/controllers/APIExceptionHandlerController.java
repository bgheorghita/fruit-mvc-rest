package com.spring.fruitmvcrest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.spring.fruitmvcrest.exceptions.ResourceNotFoundException;


@ControllerAdvice
public class APIExceptionHandlerController {
	
	public final static String RESOURCE_NOT_FOUND_MSG = "Resource Not Found";
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handlerNotFoundException(Exception exception, WebRequest request){
		return new ResponseEntity<Object>(RESOURCE_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
	}
}
