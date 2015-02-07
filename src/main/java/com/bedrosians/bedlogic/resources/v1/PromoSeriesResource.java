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
import com.bedrosians.bedlogic.bedDataAccessDAO.PromoSeriesDAO;
import com.bedrosians.bedlogic.models.PromoSeries;

@Path("/promoseries")
public class PromoSeriesResource
{
    /**
     * Locations resource
     * Query Params
     * - promocode:
     * - promoregion:
     * - materialtype:
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPromoSeries(@Context HttpHeaders requestHeaders
                                    , @QueryParam("promocode") String promoCode
                                    , @QueryParam("promoregion") String promoRegion
                                    , @QueryParam("materialtype") String materialType)
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
            promoCode = (promoCode == null) ? "" : promoCode;
            promoRegion = (promoRegion == null) ? "" : promoRegion;
            materialType = (materialType == null) ? "" : materialType;            
            
            // Retrieve DAO object
            PromoSeriesDAO  promoSeriesDAO = new PromoSeriesDAO();
            PromoSeries     result = promoSeriesDAO.readPromoSeries(userType, userCode, promoCode, promoRegion, materialType);
            
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
