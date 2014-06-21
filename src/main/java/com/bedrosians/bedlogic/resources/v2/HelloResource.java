package com.bedrosians.bedlogic.resources.v2;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bedrosians.bedlogic.usercode.UserCodeParser;

import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;

@Path("/hello")
public class HelloResource
{
    /**
     * Hello resource
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHello(@Context HttpHeaders requestHeaders)
    {
        Response    response;

        try
        {
            // Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
            
            // Return json reponse
            String     jsonStr = "{ \"hello\": \"world\" }";
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedResException e)
        {
            response = BedResExceptionMapper.MapToResponse(e);
        }
        
        return response;
    }
}
