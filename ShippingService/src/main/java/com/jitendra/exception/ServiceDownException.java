package com.jitendra.exception;

public class ServiceDownException extends RuntimeException{

	String message;
	   private static final long serialVersionUID = 1L;
	public ServiceDownException(String message) {
		super();
		this.message = message;
	}
}
