package com.bedrosians.bedlogic.exception;

import java.io.Serializable;

public class BedException extends RuntimeException  implements Serializable
{
	protected static final long serialVersionUID = -34724706273251377L;
	protected String errorCode;
	protected String errorType;
	protected String message;
	protected String rootErrorMessage;
	protected Throwable rootError;
    
    public BedException() {
    	super();
    }
    
    public BedException(String message) { 
       message = (errorType == null) ? "" : errorType + ": " + message + "\n\r";
    }
    
    public BedException(String message, Throwable rootError) { 
    	if(rootError != null)
    	   this.message = message + " Cause: " + rootError.getMessage();
    	else
    	   this.message = message + "\n\r";
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
