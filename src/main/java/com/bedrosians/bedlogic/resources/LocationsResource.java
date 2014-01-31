package com.bedrosians.bedlogic.resources;

import java.net.*;
import java.util.*;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bedrosians.bedlogic.bedDataAccessDAO.LocationsDAO;
import net.minidev.json.JSONObject;

@Path("/locations")
public class LocationsResource
{
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLocations()
    {
        LocationsDAO                locationsDAO = new LocationsDAO();
        JSONObject                  result = locationsDAO.getLocations();
        Response.ResponseBuilder    responseBuilder = null;
       
        if (result != null)
        {
            String json = result.toString();
            responseBuilder = Response.ok(json, MediaType.APPLICATION_JSON);
        }
        else
        {
            responseBuilder = Response.serverError();
        }
        
        return responseBuilder.build();
    }
}
