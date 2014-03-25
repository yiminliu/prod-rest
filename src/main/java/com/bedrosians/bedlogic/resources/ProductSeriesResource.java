package com.bedrosians.bedlogic.resources;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.bedDataAccessDAO.ProductSeriesDAO;
import com.bedrosians.bedlogic.models.ProductSeries;

@Path("/productseries")
public class ProductSeriesResource
{
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProductSeries(@Context HttpHeaders requestHeaders)
    {
        return this.getProductSeriesInternal(requestHeaders, "");
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{seriesname}")
    public Response getCosts(@Context HttpHeaders requestHeaders
                                    , @PathParam("seriesname") String seriesName)
    {
        return this.getProductSeriesInternal(requestHeaders, seriesName);
    }

   /**
     * Productseries resource
     * -seriesName: optional.
     */
   private Response getProductSeriesInternal(HttpHeaders requestHeaders
                                            , String seriesName)
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

            seriesName = (seriesName == null) ? "" : seriesName;
                                    
            // Retrieve DAO object
            ProductSeriesDAO    productSeriesDAO = new ProductSeriesDAO();
            ProductSeries       result = productSeriesDAO.readProductSeries(userType, userCode, seriesName);
            
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
