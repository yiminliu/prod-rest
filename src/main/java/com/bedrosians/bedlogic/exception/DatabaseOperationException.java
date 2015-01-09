package com.bedrosians.bedlogic.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FAILED_DEPENDENCY, reason="Database Operation Failed") 
public class DatabaseOperationException extends BedException {
	private static final long serialVersionUID = -34724706273251377L;
	
    public DatabaseOperationException() { 
    	super();
    	this.errorMessage = "Database Operation Failed"; 
    	if(errorType == null)
    	   errorType = "Database Operation Exception"; 	
    }
    
    public DatabaseOperationException(String errorMessage) { 
        super("Database Operation Failed: " + errorMessage);
    }
    
    public DatabaseOperationException(String errorMessage, Throwable cause) { 
    	super("Database Operation Failed: " + errorMessage, cause);
    }
    
}
