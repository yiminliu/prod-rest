package com.bedrosians.bedlogic.resources;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.product.ProductService;


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
	@PreAuthorize("permitAll")
	public Response getProducts(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
    {
        Response response = null;

        try
        {
            //Retrieve data from database based on the given query parameters
            List<Item> items = productService.getProductsByQueryParameters(uriInfo.getQueryParameters());       
            
            //Convert the data to Json string
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
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_BUYER')") 
	public Response createProduct(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
    {
        Response response;

        try
        {     
        	//Create a new product using the given data in json input, and save it into database  
            String itemCode = productService.createProduct(inputJsonObj);
            
            //Wrape the newly created product id into json
            Products result = new Products(itemCode);
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
	
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_BUYER')") 
    public Response updateProduct(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
    {
        Response    response;

        try
        {
        	//Update a product based on the input json data
            productService.updateProduct(inputJsonObj);    
            
            // Return json reponse
            response = Response.ok(MediaType.APPLICATION_JSON).build();
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
}
