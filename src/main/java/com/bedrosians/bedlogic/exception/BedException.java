package com.bedrosians.bedlogic.exception;

import java.io.Serializable;

public class BedException extends RuntimeException  implements Serializable
{
	protected static final long serialVersionUID = -34724706273251377L;
	protected String errorCode;
	protected String errorType;
	protected String errorMessage;
	protected String rootErrorMessage;
	protected Throwable rootError;
    
    public BedException() {
    	super();
    }
    
    public BedException(String message) { 
       errorMessage = (errorType == null) ? "" : errorType + ": " + message + "\n\r";
    }
    
    public BedException(String message, Throwable rootError) { 
    	if(rootError != null)
    	   errorMessage = message + " Cause: " + rootError.getMessage();
    	else
    	   errorMessage = message + "\n\r";
    	this.rootError = rootError;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorType() {
		errorType = this.getClass().getName();
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

	public String getRootErrorMessage() {
		rootErrorMessage = (rootError == null)? "" : (rootError.getCause() == null)? "" : rootError.getCause().getMessage();
		return rootErrorMessage;
	}

	public void setRootErrorMessage(String rootErrorMessage) {
		this.rootErrorMessage = rootErrorMessage;
	}

	public Throwable getRootError() {
		return rootError;
	}

	public void setRootError(Throwable rootError) {
		this.rootError = rootError;
	}
    
}
