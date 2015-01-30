package com.bedrosians.bedlogic.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE, reason="Service Unavailable") 
public class UnsupportedException extends BedException {
	private static final long serialVersionUID = -34724706273251377L;
	
    public UnsupportedException() { 
    	super(); 
    	this.httpErrorCode = 503;
    	this.httpMessage = "Service Unavailable"; 
    	if(errorType == null)
    	   errorType = "Service Unavailable"; 	
    }
    
    public UnsupportedException(String errorMessage) { 
        super("Service Unavailable: " + errorMessage);
    }
    
    public UnsupportedException(String errorMessage, Throwable cause) { 
    	super("Service Unavailable: " + errorMessage, cause);
    }
    
}
