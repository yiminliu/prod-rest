package com.bedrosians.bedlogic.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.bedrosians.bedlogic.exception.*;

public class BedResExceptionMapper
{
    public static Response MapToResponse(BedResException theException)
    {
        int     code;
        String  message;
                
        if (theException instanceof BedResUnAuthorizedException)
        {
            code = 401;
            message = "Authentication Failed";
        }
        else
        {
            code = 500;
            message = "Internal Error";
        }
        
        String                      jsonStr = String.format("{ \"error\" : { \"status\" : %1$s, \"message\" : \"%2$s\" } }", code, message);
        Response.ResponseBuilder    responseBuilder = Response.status(code).entity(jsonStr).type(MediaType.APPLICATION_JSON);
        
        return responseBuilder.build();
    }
}
