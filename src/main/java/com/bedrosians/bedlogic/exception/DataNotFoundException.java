package com.bedrosians.bedlogic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No Such Object")  // 404
public class DataNotFoundException extends BedException {
	private static final long serialVersionUID = -3465706273251377L;

	public DataNotFoundException() { 
    	super(); 
    	errorType = "Data Not Found Exception";	
     }
    
    public DataNotFoundException(String message) { 
       errorType = "Data Not Found Exception";	
       errorMessage = "No Data Found: " + message + "\n\r";
    }
    
    public DataNotFoundException(String message, Throwable cause) { 
    	errorType = "Data Not Found Exception";	
    	if(cause.getMessage() != null)
    	  errorMessage = "No Data Found: " + message + "\n\r" + "Cause: " + cause.getCause().getMessage();
    	else
    	  errorMessage = "No Data Found: " + message + "\n\r";
    	rootError = cause;
    }    
}
