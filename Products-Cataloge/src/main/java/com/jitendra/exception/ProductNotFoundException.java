package com.jitendra.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class ProductNotFoundException extends RuntimeException{
	String message;
	   private static final long serialVersionUID = 1L;
	public ProductNotFoundException(String message) {
		super();
		this.message = message;
	}
	   
}
