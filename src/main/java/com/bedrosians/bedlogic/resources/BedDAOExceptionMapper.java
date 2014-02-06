package com.bedrosians.bedlogic.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bedrosians.bedlogic.exception.*;

public class BedDAOExceptionMapper
{
    public static Response MapToResponse(BedDAOException theException)
    {
        Response.ResponseBuilder    responseBuilder;
                
        if (theException instanceof BedDAOUnAuthorizedException)
        {
            responseBuilder = Response.status(Status.UNAUTHORIZED);
        }
        else if (theException instanceof BedDAOBadResultException)
        {
            responseBuilder = Response.status(Status.NOT_FOUND);
        }
        else if (theException instanceof BedDAOBadParamException)
        {
            responseBuilder = Response.status(Status.BAD_REQUEST);
        }
        else
        {
            responseBuilder = Response.serverError();
        }
        
        return responseBuilder.build();
    }
}
