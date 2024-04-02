package com.jitendra.exception;

public class CustomerNotFoundException extends RuntimeException{
	String message;
	   private static final long serialVersionUID = 1L;
	public CustomerNotFoundException(String message) {
		super();
		this.message = message;
	}

}
