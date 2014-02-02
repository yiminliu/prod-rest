package com.bedrosians.bedlogic.resources;

import java.net.*;
import java.util.*;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.WebApplicationException;

import com.bedrosians.bedlogic.exception.*;
import com.bedrosians.bedlogic.bedDataAccessDAO.LocationsDAO;
import net.minidev.json.JSONObject;

@Path("/locations")
public class LocationsResource
{
    /**
     * Locations resource
     * Query Params
     * - usertype:          required valid values: guest | keymark
     * - usercode:          required non empty usercode for keymark user type
     * - locationcodes:     optional comma separated list of location codes to match against
     * - locationregion:    optional location region name to match against
     * - branchname:        optional branchname to match against
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLocations(@Context HttpHeaders requestHeaders
                                    , @QueryParam("locationcodes") String locationCodes
                                    , @QueryParam("locationregion") String locationRegion
                                    , @QueryParam("branchname") String branchName)    
    {
        Response.ResponseBuilder    responseBuilder = null;

        try
        {
            // Retrieve user type and user code from authorization header
            Map<String,String>  authInfo = BasicAuthParser.parse(requestHeaders);
            String              userType = null;
            String              userCode = null;
            
            if (authInfo != null)
            {
                userType = authInfo.get("user");
                userCode = authInfo.get("password");
            }
            
            // Validate Parameters
            userCode = (userCode == null) ? "" : userCode;
            if (userType == null
                || ("keymark".equals(userType) && userCode.isEmpty()))
            {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
            locationCodes = (locationCodes == null) ? "" : locationCodes;
            locationRegion = (locationRegion == null) ? "" : locationRegion;
            branchName = (branchName == null) ? "" : branchName;            
            
            // Retrieve DAO object
            LocationsDAO    locationsDAO = new LocationsDAO();
            JSONObject      result = locationsDAO.getLocations(userType, userCode, locationCodes, locationRegion, branchName);
            
            // Return json reponse
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
