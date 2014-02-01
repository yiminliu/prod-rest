package com.bedrosians.bedlogic.resources;

import java.net.*;
import java.util.*;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import com.bedrosians.bedlogic.exception.*;
import com.bedrosians.bedlogic.bedDataAccessDAO.LocationsDAO;
import net.minidev.json.JSONObject;

@Path("/locations")
public class LocationsResource
{
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLocations()
    {
        Response.ResponseBuilder    responseBuilder = null;

        try
        {
            LocationsDAO    locationsDAO = new LocationsDAO();
            JSONObject      result = locationsDAO.getLocations();
            String          json = result.toString();
            
            responseBuilder = Response.ok(json, MediaType.APPLICATION_JSON);
        }
        catch (BedDAOUnAuthorizedException e)
        {
            responseBuilder = Response.status(Status.UNAUTHORIZED);
        }
        catch (BedDAOBadResultException e)
        {
            responseBuilder = Response.status(Status.NOT_FOUND);
        }
        catch (BedDAOException e)
        {
            responseBuilder = Response.serverError();
        }
        
        return responseBuilder.build();
    }
}
