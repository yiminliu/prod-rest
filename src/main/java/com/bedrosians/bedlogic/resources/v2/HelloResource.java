package com.bedrosians.bedlogic.resources.v2;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.bedrosians.bedlogic.service.system.IndexService;
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
	@Autowired
	private IndexService indexService;
	
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
    
    /* This method is used to create Lucene indexes for the existing data in database */
	@GET
	@Path("/index")
	public Response createInitialLuceneIndex(){
		 boolean initialzed =  indexService.initializeIndex();
		 if(initialzed)
		    return Response.status(200).entity("Index is created").build();
		else
			return Response.status(500).entity("Failed creating index").build();
	}
}
