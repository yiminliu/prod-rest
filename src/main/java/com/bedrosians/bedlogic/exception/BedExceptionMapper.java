package com.bedrosians.bedlogic.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Component
@Provider
public class BedExceptionMapper implements ExceptionMapper<BedException>
{
    public Response toResponse(BedException theException)
    {
        int     code;
        String  message;
              
        if (theException instanceof InputParamException || theException instanceof DataMappingException)
        {
            code = 400;
            message = "Bad Request";
        }
        else if (theException instanceof UnauthenticatedException)
        {
            code = 401;
            message = "Authentication Failed";
        }
        else if (theException instanceof UnauthorizedException)
        {
            code = 403;
            message = "Forbidden";
        }
        else if (theException instanceof DataNotFoundException)
        {
            code = 404;
            message = "Object Not Found";
        }        
        else if (theException instanceof UnsupportedMediaTypeException)
        {
            code = 415;
            message = "Unsupported Media Type";
        }
        else
        {
            code = 500;
            message = "Internal Error";
        }
        
        String                      jsonStr = String.format("{ \"error\" : { \"status\" : %1$s, \"message\" : \"%2$s\", \"detail message\" : \"%3$s\" } }", code, message, theException.getMessage());
        Response.ResponseBuilder    responseBuilder = Response.status(code).entity(jsonStr).type(MediaType.APPLICATION_JSON);
        
        return responseBuilder.build();
    }
}
