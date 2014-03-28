package com.bedrosians.bedlogic.resources;


import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.ProductService;


@Controller
@Path("/products")
public class ProductsResource
{
    /**
     * Products resource
     */

	@Autowired
	ProductService productService;
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProducts(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
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
            
            // Get queryParams
            MultivaluedMap queryParams = uriInfo.getQueryParameters();
                        
            //ProductsDAO productsDAO = new ProductsDAO();
            //Products    result = productsDAO.readProductsByQueryParams(userType, userCode, queryParams);
            
            List<Item> items = productService.getProductsByQueryParameters(queryParams);
                
            Products result = new Products(items);
            String jsonStr = result.toJSONStringWithJackson();
       
            //Return json reponse
             response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedDAOBadParamException e)
        {
        	response = BedDAOExceptionMapper.MapToResponse(e);
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
	
	@GET
	@Path("{itemCode}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProductById(@Context HttpHeaders requestHeaders, @PathParam("itemCode") final String itemCode)
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
                             
            //ProductsDAO productsDAO = new ProductsDAO();
            //Products    result = productsDAO.readProductsByQueryParams(userType, userCode, queryParams);
            
            Item item = productService.getProductById(itemCode);
                
            Products result = new Products(item);
            String jsonStr = result.toJSONStringWithJackson();
       
            //Return json reponse
             response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedDAOBadParamException e)
        {
        	response = BedDAOExceptionMapper.MapToResponse(e);
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
	
	
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response createProduct(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
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
            
            // Get queryParams
            MultivaluedMap queryParams = uriInfo.getQueryParameters();
       
            //ProductsDAO productsDAO = new ProductsDAO();
            //Products    result = productsDAO.readProductsByQueryParams(userType, userCode, queryParams);
            
            String itemId = productService.createProduct(queryParams);
            Products result = new Products(itemId);
            String jsonStr = result.toJSONStringWithJackson();         
            
            // Return json reponse
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedDAOBadParamException e)
        {
        	response = BedDAOExceptionMapper.MapToResponse(e);
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
	
	@PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateProduct(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
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
            
            // Get queryParams
            MultivaluedMap queryParams = uriInfo.getQueryParameters();
       
            //ProductsDAO productsDAO = new ProductsDAO();
            //Products    result = productsDAO.readProductsByQueryParams(userType, userCode, queryParams);
            
            productService.updateProduct(queryParams);     
            // Return json reponse
            response = Response.ok(MediaType.APPLICATION_JSON).build();
        }    
            catch (BedDAOBadParamException e)
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
