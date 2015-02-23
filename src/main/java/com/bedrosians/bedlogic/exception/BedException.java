package com.bedrosians.bedlogic.exception;

import java.io.Serializable;

public class BedException extends RuntimeException  implements Serializable
{
	protected static final long serialVersionUID = -34724706273251377L;
	protected Integer httpErrorCode;
	protected String httpMessage;
	protected String appErrorCode;
	protected String errorType;
	protected String message;
	protected String rootErrorMessage;
	protected Throwable rootError;
    
    public BedException() {
    	super();
    }
       
    public BedException(String message) { 
    	this();
        this.message = message;
    }
    
    public BedException(String message, Throwable rootError) {
    	this(message);
    	if(rootError != null)
    	   this.message = message;
     	this.rootError = rootError;
    }
    
	public Integer getHttpErrorCode() {
		return httpErrorCode;
	}

	public void setHttpErrorCode(Integer httpErrorCode) {
		this.httpErrorCode = httpErrorCode;
	}

	public String getHttpMessage() {
		return httpMessage;
	}

	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}

	public String getAppErrorCode() {
		return appErrorCode;
	}

	public void setAppErrorCode(String appErrorCode) {
		this.appErrorCode = appErrorCode;
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
