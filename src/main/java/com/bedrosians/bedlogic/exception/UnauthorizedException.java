package com.bedrosians.bedlogic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Authorizatication Failed") 
public class UnauthorizedException extends BedException {
	private static final long serialVersionUID = -347886273251377L;
    public UnauthorizedException() { 
    	super(); 
    	this.errorMessage = "Authorizatication Failed"; 
    	if(errorType == null)
    	   errorType = "Authorizatication"; 	
    }
    
    public UnauthorizedException(String errorMessage) {
    	super("Authorizatication Failed. " + errorMessage);
    }
    
    public UnauthorizedException(String errorMessage, Throwable cause) { 
    	super("Authorizatication Failed: " + errorMessage, cause);
    }

}
