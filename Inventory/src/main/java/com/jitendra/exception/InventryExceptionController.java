package com.jitendra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InventryExceptionController {
   @ExceptionHandler(InventryNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> InventryNotFoundException(InventryNotFoundException e) {
	   return new ResponseEntity<>("Item Not present: " + e.getMessage(), HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(BadRequestException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
       return new ResponseEntity<>("Bad Request: " + e.getMessage(), HttpStatus.BAD_REQUEST);
   }
	}

