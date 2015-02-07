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

import com.bedrosians.bedlogic.service.security.UserCodeParser;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedDAOExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.bedDataAccessDAO.PricesDAO;
import com.bedrosians.bedlogic.models.Prices;

@Path("/prices")
public class PricesResource
{

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{itemcode}")
    public Response getPrices(@Context HttpHeaders requestHeaders
                                    , @PathParam("itemcode") String itemCode
                                    , @QueryParam("unit") String unit)
    {
        return this.getPricesInternal(requestHeaders, itemCode, "", "", "", unit);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{itemcode}/{customercode}")
    public Response getPrices(@Context HttpHeaders requestHeaders
                                    , @PathParam("itemcode") String itemCode
                                    , @PathParam("customercode") String customerCode
                                    , @QueryParam("unit") String unit)
    {
        return this.getPricesInternal(requestHeaders, itemCode, customerCode, "", "", unit);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{itemcode}/{customercode}/{branchcode}")
    public Response getPrices(@Context HttpHeaders requestHeaders
                                    , @PathParam("itemcode") String itemCode
                                    , @PathParam("customercode") String customerCode
                                    , @PathParam("branchcode") String branchCode
                                    , @QueryParam("unit") String unit)
    {
        return this.getPricesInternal(requestHeaders, itemCode, customerCode, branchCode, "", unit);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{itemcode}/{customercode}/{branchcode}/{locationcode}")
    public Response getPrices(@Context HttpHeaders requestHeaders
                                    , @PathParam("itemcode") String itemCode
                                    , @PathParam("customercode") String customerCode
                                    , @PathParam("branchcode") String branchCode
                                    , @PathParam("locationcode") String locationCode
                                    , @QueryParam("unit") String unit)
    {
        return this.getPricesInternal(requestHeaders, itemCode, customerCode, branchCode, locationCode, unit);
    }
    
    /**
     * Prices resource
     * Query Params
     * - unit: optional.
     */
    private Response getPricesInternal(HttpHeaders requestHeaders
                                    , String itemCode
                                    , String customerCode
                                    , String branchCode
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
            unit = (unit == null) ? "" : unit;
            
            // Retrieve DAO object
            PricesDAO  pricesDAO = new PricesDAO();
            Prices     result = pricesDAO.readPrices(userType, userCode, itemCode, customerCode, branchCode, locationCode, unit);
            
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
