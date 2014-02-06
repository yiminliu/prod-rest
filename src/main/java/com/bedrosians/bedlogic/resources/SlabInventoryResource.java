package com.bedrosians.bedlogic.resources;

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
import javax.ws.rs.WebApplicationException;

import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.bedDataAccessDAO.SlabInventoryDAO;
import com.bedrosians.bedlogic.models.SlabInventory;

@Path("/slabinventory")
public class SlabInventoryResource
{

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{itemcode}")
    public Response getSlabInventory(@Context HttpHeaders requestHeaders
                                    , @PathParam("itemcode") String itemCode
                                    , @QueryParam("unit") String unit)
    {
        return this.getSlabInventoryInternal(requestHeaders, itemCode, "", unit);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{itemcode}/{locationcode}")
    public Response getSlabInventory(@Context HttpHeaders requestHeaders
                                    , @PathParam("itemcode") String itemCode
                                    , @PathParam("locationcode") String locationCode
                                    , @QueryParam("unit") String unit)
    {
        return this.getSlabInventoryInternal(requestHeaders, itemCode, locationCode, unit);
    }

    /**
     * SlabInventory resource
     * Query Params
     * - unit:         optional.
     */
    private Response getSlabInventoryInternal(HttpHeaders requestHeaders
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
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
            
            // Get query params
            unit = (unit == null) ? "" : unit;
            
            // Retrieve DAO object
            SlabInventoryDAO    slabInventoryDAO = new SlabInventoryDAO();
            SlabInventory       result = slabInventoryDAO.getSlabInventory(userType, userCode, itemCode, locationCode, unit);
            
            // Return json reponse
            String  jsonStr = result.toJSONString();
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedDAOException e)
        {
            response = BedDAOExceptionMapper.MapToResponse(e);
        }
        
        return response;
    }
}

