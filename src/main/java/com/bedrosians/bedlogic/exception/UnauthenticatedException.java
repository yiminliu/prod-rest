package com.bedrosians.bedlogic.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Authentication Failed") 
public class UnauthenticatedException extends RuntimeException  implements Serializable
{
	private static final long serialVersionUID = -34724706273251377L;
	
    private String message;
    
    public UnauthenticatedException() { 
    	super(); 
    }
    
    public UnauthenticatedException(String id) {
    	message = "Authentication Failed";
    }
    
    public UnauthenticatedException(String id, Throwable cause) { 
    	message = "Authentication Failed " + "\n\r" + "Cause: " + cause.getMessage();
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
    	
    
}
