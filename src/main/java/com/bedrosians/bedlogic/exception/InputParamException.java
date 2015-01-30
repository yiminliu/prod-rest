package com.bedrosians.bedlogic.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Input Parameter Error") 
public class InputParamException extends BedException {
	private static final long serialVersionUID = -3447273251377L;
    public InputParamException() {
    	super();
    	httpErrorCode = 400;
        httpMessage = "Bad Request";
    	message = "Input Parameter Error";
    	if(errorType == null)
    	   errorType = "Input Parameter Error";
    }
    
    public InputParamException(String message) { 
       super("Input Parameter Error: " + message);
    }
    
    public InputParamException(String message, Throwable cause) { 
       super(message, cause);
    }
}
