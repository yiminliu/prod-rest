package com.bedrosians.bedlogic.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE, reason="Database Connection Failed") 
public class DatabaseConnectionException extends BedException {
	private static final long serialVersionUID = -34724706273251377L;
	
    public DatabaseConnectionException() { 
    	super();
    	this.errorMessage = "Database Connection Failed"; 
    	if(errorType == null)
    	   errorType = "DatabaseConnectionException"; 	
    }
    
    public DatabaseConnectionException(String errorMessage) { 
        super("Database Connection Failed: " + errorMessage);
    }
    
    public DatabaseConnectionException(String errorMessage, Throwable cause) { 
    	super("Database Connection Failed: " + errorMessage, cause);
    }
    
}
