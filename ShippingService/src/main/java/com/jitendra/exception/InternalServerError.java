package com.jitendra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
public class InternalServerError extends RuntimeException  {
String message;

public InternalServerError(String message) {
	super();
	this.message = message;
}

}
