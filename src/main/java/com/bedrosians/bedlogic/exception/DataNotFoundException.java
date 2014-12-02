package com.bedrosians.bedlogic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No Such Object")  // 404
public class DataNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = -34724706273251377L;
	
    private String message;
    
    public DataNotFoundException() { 
    	super(); 
    }
    
    public DataNotFoundException(String id) {
    	message = "No data found for "+ id;
    }
    
    public DataNotFoundException(String id, Throwable cause) { 
    	message = "No data found for "+ id + "\n\r" + "Cause: " + cause.getMessage();
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
    	
    
}
