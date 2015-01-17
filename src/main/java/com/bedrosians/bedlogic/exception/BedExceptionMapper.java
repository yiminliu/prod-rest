package com.bedrosians.bedlogic.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


public class BedExceptionMapper
{
    public static Response MapToResponse(Exception theException)
    {
        int     code;
        String  message;
                
        if (theException instanceof BedDAOUnAuthorizedException || theException instanceof BedResUnAuthorizedException || theException instanceof UnauthenticatedException)
        {
            code = 401;
            message = "Authentication Failed";
        }
        else if (theException instanceof BedDAOBadResultException || theException instanceof DataNotFoundException)
        {
            code = 404;
            message = "Resource Not Found";
        }
        else if (theException instanceof BedDAOBadParamException || theException instanceof InputParamException)
        {
            code = 400;
            message = "Bad Request";
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