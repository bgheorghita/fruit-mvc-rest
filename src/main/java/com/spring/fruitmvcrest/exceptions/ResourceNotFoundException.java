package com.spring.fruitmvcrest.exceptions;

public class ResourceNotFoundException extends Exception {
	
	public ResourceNotFoundException() {}
	
	public ResourceNotFoundException(String msg) {super(msg);}
	
	public ResourceNotFoundException(String msg, Throwable cause) {super(msg, cause);}
	
	public ResourceNotFoundException(Throwable cause) {super(cause);}
	
	public ResourceNotFoundException(String msg, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
		super(msg, cause, enableSupression, writableStackTrace);
	}
}
