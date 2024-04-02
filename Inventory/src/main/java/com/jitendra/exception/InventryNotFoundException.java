package com.jitendra.exception;

public class InventryNotFoundException  extends RuntimeException{
  private String message;

public InventryNotFoundException(String message) {
	super();
	this.message = message;
}
  
}
