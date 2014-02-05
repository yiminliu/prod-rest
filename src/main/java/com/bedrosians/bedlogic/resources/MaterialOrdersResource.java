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
import com.bedrosians.bedlogic.bedDataAccessDAO.MaterialOrdersDAO;
import com.bedrosians.bedlogic.models.MaterialOrders;

@Path("/materialorders")
public class MaterialOrdersResource
{
    /**
     * MaterialOrders resource
     * Query Params
     * - unit:
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{itemcode}/{locationcode}")
    public Response getMaterialOrders(@Context HttpHeaders requestHeaders
                                    , @PathParam("itemcode") String itemCode
                                    , @PathParam("locationcode") String locationCode
                                    , @QueryParam("opencode") String openCode)
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
            openCode = (openCode == null) ? "" : openCode;
            
            // Retrieve DAO object
            MaterialOrdersDAO  materialOrdersDAO = new MaterialOrdersDAO();
            MaterialOrders     result = materialOrdersDAO.getMaterialOrders(userType, userCode, itemCode, locationCode, openCode);
            
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
