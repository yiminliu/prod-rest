package com.bedrosians.bedlogic.exception;

import java.io.Serializable;

public class BedException extends RuntimeException  implements Serializable
{
	protected static final long serialVersionUID = -34724706273251377L;
	protected String errorType;
	protected String errorMessage;
	protected Throwable rootError;
    
    public BedException() {
    	super();
    }
    
    public BedException(String message) { 
       errorMessage = (errorType == null) ? "" : errorType + ": " + message + "\n\r";
    }
    
    public BedException(String message, Throwable rootError) { 
    	if(rootError != null)
    	  errorMessage = errorType + ": " + message + "\n\r" + "Cause: " + rootError.getMessage();
    	else
    	  errorMessage = errorType + ": " + message + "\n\r";
    	this.rootError = rootError;
    }

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Throwable getRootError() {
		return rootError;
	}

	public void setRootError(Throwable rootError) {
		this.rootError = rootError;
	}
    
}
