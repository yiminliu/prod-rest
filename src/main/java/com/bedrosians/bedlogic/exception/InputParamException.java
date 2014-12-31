package com.bedrosians.bedlogic.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Input Parameter Error") 
public class InputParamException extends RuntimeException  implements Serializable
{
	private static final long serialVersionUID = -34724706273251377L;
	private String errorCode;
    private String errorMessage;
    private Throwable error;
    
    public InputParamException() {
    	errorMessage = "Input Parameter Error";
    }
    
    public InputParamException(String message) { 
       errorMessage = "Input Parameter Error: " + message + "\n\r";
    }
    
    public InputParamException(String message, Throwable cause) { 
    	if(cause != null)
    	  errorMessage = "Input Parameter Error: " + message + "\n\r" + "Cause: " + cause.getMessage();
    	else
    	  errorMessage = "Input Parameter Error: " + message + "\n\r";
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
