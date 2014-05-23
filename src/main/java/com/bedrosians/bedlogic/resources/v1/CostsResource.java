package com.bedrosians.bedlogic.resources.v1;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.bedDataAccessDAO.CostsDAO;
import com.bedrosians.bedlogic.models.Costs;

@Path("/costs")
public class CostsResource
{
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{itemcode}")
    public Response getCosts(@Context HttpHeaders requestHeaders
                                    , @PathParam("itemcode") String itemCode
                                    , @QueryParam("unit") String unit)
    {
        return this.getCostsInternal(requestHeaders, itemCode, "", unit);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{itemcode}/{locationcode}")
    public Response getCosts(@Context HttpHeaders requestHeaders
                                    , @PathParam("itemcode") String itemCode
                                    , @PathParam("locationcode") String locationCode
                                    , @QueryParam("unit") String unit)
    {
        return this.getCostsInternal(requestHeaders, itemCode, locationCode, unit);
    }

    /**
     * Costs resource
     * Query Params
     * - unit:         optional.
     */
    private Response getCostsInternal(HttpHeaders requestHeaders
                                    , String itemCode
                                    , String locationCode
                                    , String unit)
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
            locationCode = (locationCode == null) ? "" : locationCode;
            unit = (unit == null) ? "" : unit;
            
            // Retrieve DAO object
            CostsDAO    costsDAO = new CostsDAO();
            Costs       result = costsDAO.readCosts(userType, userCode, itemCode, locationCode, unit);
            
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
