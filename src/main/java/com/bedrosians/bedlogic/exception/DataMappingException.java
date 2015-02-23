package com.bedrosians.bedlogic.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FAILED_DEPENDENCY, reason="Database Operation Failed") 
public class DataMappingException extends BedException {
	private static final long serialVersionUID = -34724706273251377L;
	
    public DataMappingException() { 
    	super();
    	this.message = "Data mapping error"; 
    	if(errorType == null)
    	   errorType = "Data Mapping Exception"; 	
    }
    
    public DataMappingException(String errorMessage) { 
        super("Data mapping error: " + errorMessage);
    }
    
    public DataMappingException(String errorMessage, Throwable cause) { 
    	super("Data mapping error: " + errorMessage, cause);
    }
    
}
