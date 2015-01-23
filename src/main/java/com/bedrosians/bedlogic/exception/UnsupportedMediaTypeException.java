package com.bedrosians.bedlogic.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Input Parameter Error") 
public class UnsupportedMediaTypeException extends BedException {
	private static final long serialVersionUID = -3447273251377L;
    public UnsupportedMediaTypeException() {
    	super();
    	this.message = "Unsupported Media Type";
    	if(errorType == null)
    	   errorType = "Unsupported Media Type";
    }
    
    public UnsupportedMediaTypeException(String message) { 
       super("Unsupported Media Type: " + message);
    }
    
    public UnsupportedMediaTypeException(String message, Throwable cause) { 
       super("Unsupported Media Type: " + message, cause);
    }
}
