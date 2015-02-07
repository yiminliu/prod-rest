package com.bedrosians.bedlogic.resources.v1;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bedrosians.bedlogic.service.security.UserCodeParser;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedDAOExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.bedDataAccessDAO.LocationsDAO;
import com.bedrosians.bedlogic.models.Locations;

@Path("/locations")
public class LocationsResource
{
    /**
     * Locations resource
     * Query Params
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
            
            // Get query params
            locationCodes = (locationCodes == null) ? "" : locationCodes;
            locationRegion = (locationRegion == null) ? "" : locationRegion;
            branchName = (branchName == null) ? "" : branchName;            
            
            // Retrieve DAO object
            LocationsDAO    locationsDAO = new LocationsDAO();
            Locations       result = locationsDAO.readLocations(userType, userCode, locationCodes, locationRegion, branchName);
            
            // Return json reponse
            String  jsonStr = result.toJSONString();
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedDAOException e)
        {
            response = BedDAOExceptionMapper.MapToResponse(e);
        }
        catch (BedResException e)
        {
            response = BedResExceptionMapper.MapToResponse(e);
        }
        
        return response;
    }
}
