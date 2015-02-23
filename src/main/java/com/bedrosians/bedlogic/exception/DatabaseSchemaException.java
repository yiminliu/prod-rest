package com.bedrosians.bedlogic.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_IMPLEMENTED, reason="Database Schema Error") 
public class DatabaseSchemaException extends BedException {
	private static final long serialVersionUID = -34724706273251377L;
	
    public DatabaseSchemaException() { 
    	super();
    	this.message = "Database Schema Error"; 
    	if(errorType == null)
    	   errorType = "DatabaseSchemaException"; 	
    }
    
    public DatabaseSchemaException(String errorMessage) { 
        super("Database Schema Error: " + errorMessage);
    }
    
    public DatabaseSchemaException(String errorMessage, Throwable cause) { 
    	super("Database Schema Error: " + errorMessage, cause);
    }
    
}
