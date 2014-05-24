package com.bedrosians.bedlogic.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


import com.bedrosians.bedlogic.exception.*;

public class BedDAOExceptionMapper
{
    public static Response MapToResponse(BedDAOException theException)
    {
        int     code;
        String  message;
                
        if (theException instanceof BedDAOUnAuthorizedException)
        {
            code = 401;
            message = "Authentication Failed";
        }
        else if (theException instanceof BedDAOBadResultException)
        {
            code = 404;
            message = "Resource Not Found";
        }
        else if (theException instanceof BedDAOBadParamException)
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
