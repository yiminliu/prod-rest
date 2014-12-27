package com.bedrosians.bedlogic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No Such Object")  // 404
public class DataNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = -34724706273251377L;
	
	private String errorCode;
    private String errorMessage;
    private Throwable error;
    
    public DataNotFoundException() { 
    	super(); 
        errorMessage = "No Data Found: " + "\n\r";
     }
    
    public DataNotFoundException(String message) { 
       errorMessage = "No Data Found: " + message + "\n\r";
    }
    
    public DataNotFoundException(String message, Throwable cause) { 
    	if(cause.getMessage() != null)
    	  errorMessage = "No Data Found: " + message + "\n\r" + "Cause: " + cause.getCause().getMessage();
    	else
    	  errorMessage = "No Data Found: " + message + "\n\r";
    	error = cause;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Throwable getError() {
		return error;
	}

	public void setError(Throwable error) {
		this.error = error;
	}
    
}
