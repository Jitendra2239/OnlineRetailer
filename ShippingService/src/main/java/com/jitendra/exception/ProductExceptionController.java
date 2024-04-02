package com.jitendra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductExceptionController {
	  @ExceptionHandler(value = ProductNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	   public ResponseEntity<Object> exception(ProductNotFoundException exception) {
	      return new ResponseEntity<>(exception.message, HttpStatus.NOT_FOUND);
	   }
	  
	  @ExceptionHandler(value = CustomerNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	   public ResponseEntity<Object> exception1(CustomerNotFoundException exception) {
	      return new ResponseEntity<>(exception.message, HttpStatus.NOT_FOUND);
	   }
	  @ExceptionHandler(value = ServiceDownException.class)
	    @ResponseStatus(HttpStatus.OK)
	   public ResponseEntity<Object> exception2(ServiceDownException exception) {
	      return new ResponseEntity<>(exception.message, HttpStatus.NOT_FOUND);
	   }
}
