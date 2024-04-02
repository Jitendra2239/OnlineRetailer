package com.jitendra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class CustomerAddressNotFoundException extends RuntimeException  {
	public CustomerAddressNotFoundException(String message)   
	{  
	super(message);  
	}
}
