package com.bedrosians.bedlogic.resources;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.WebApplicationException;

import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.bedDataAccessDAO.ProductPromosDAO;
import com.bedrosians.bedlogic.models.ProductPromos;

@Path("/productpromos")
public class ProductPromosResource
{
    /**
     * ProductPromos resource
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProductPromos(@Context HttpHeaders requestHeaders
                                    , @Context UriInfo uriInfo)
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
            
            // Get queryParams
            MultivaluedMap queryParams = uriInfo.getQueryParameters();
                        
            // Retrieve DAO object
            ProductPromosDAO productPromosDAO = new ProductPromosDAO();
            ProductPromos    result = productPromosDAO.readProductPromosByQueryParams(userType, userCode, queryParams);
            
            // Return json reponse
            String     jsonStr = result.toJSONString();
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedDAOException e)
        {
            response = BedDAOExceptionMapper.MapToResponse(e);
        }
        
        return response;
    }
}
