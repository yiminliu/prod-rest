package com.bedrosians.bedlogic.resources;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.product.ProductService;
import com.bedrosians.bedlogic.service.security.KeymarkUcUserAuthentication;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.JsonWrapper.ListWrapper;


@Controller
@Path("/products")
public class ProductsResource
{
    /**
     * Products resource
     */

	@Autowired
	ProductService productService;
	@Autowired
	KeymarkUcUserAuthentication keymarkUcUserAuthentication;
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	public Response getProducts(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
    {   
        Response response = null;

        try
        {
        	 //Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
            
            //Authenticate the user
            keymarkUcUserAuthentication.authenticate(userType, userCode);
            
            //Retrieve data from database based on the given query parameters
            List<Item> items = productService.getProducts(uriInfo.getQueryParameters());       
            
            //Convert the data to Json string
            Products result = new Products(new ListWrapper(items));
            String jsonStr = result.toJSONStringWithJackson();
       
            //Return json reponse
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedResUnAuthorizedException e)
        {
        	response = BedResExceptionMapper.MapToResponse(e);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProducts(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
    {
        Response response = null;

        try
        {
        	 //Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
            
            //Authenticate the user
            keymarkUcUserAuthentication.authenticate(userType, userCode);
            
            //Retrieve data from database based on the given query parameters
            List<Item> items = productService.getProducts(inputJsonObj);       
            
            //Convert the data to Json string
            Products result = new Products(new ListWrapper(items));
            String jsonStr = result.toJSONStringWithJackson();
       
            //Return json reponse
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedResUnAuthorizedException e)
        {
        	response = BedResExceptionMapper.MapToResponse(e);
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
	@Path("{itemcode}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProductById(@Context HttpHeaders requestHeaders, @PathParam("itemcode") final String itemCode)
    {
        Response    response;

        try
        {
        	//Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
                 
            //Authenticate the user
                 keymarkUcUserAuthentication.authenticate(userType, userCode);
                 
            //Retrieve data from database based on the give id
            Item item = productService.getProductById(itemCode);
                
            Products result = new Products(new ItemWrapper(item));
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
	
	
	@POST
    @Produces({MediaType.APPLICATION_JSON})
	public Response createProducts(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
    {
        Response response = null;

        try
        {
        	//Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
            
            //Authenticate the user
            keymarkUcUserAuthentication.authenticate(userType, userCode);
           
            //Retrieve data from database based on the given query parameters
            String productId = productService.createProduct(uriInfo.getQueryParameters());       
            
            Products result = new Products(productId);
            String jsonStr = result.toJSONStringWithJackson();
       
            //Return json reponse
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        }
        catch (BedResUnAuthorizedException e)
        {
        	response = BedResExceptionMapper.MapToResponse(e);
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
	
	/*
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@Context HttpHeaders requestHeaders, String inputJsonString)
    {
        Response    response;

        try
        {
        	//Update a product based on the input json data
            productService.updateProduct(inputJsonString);    
            
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
	*/
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
    {
        Response    response;

        try
        {
        	//Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
            
            //Authenticate the user
            keymarkUcUserAuthentication.authenticate(userType, userCode);
            
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
	
	@PUT
    @Produces({MediaType.APPLICATION_JSON})
	public Response updateProduct(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
    {
        Response response = null;

        try
        {
        	//Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
            
            //Authenticate the user
            keymarkUcUserAuthentication.authenticate(userType, userCode);
            
            //update data from database with the given query parameters
            productService.updateProduct(uriInfo.getQueryParameters());       
             
            //Return json reponse
            response = Response.ok(MediaType.APPLICATION_JSON).build();
        }
        catch (BedResUnAuthorizedException e)
        {
        	response = BedResExceptionMapper.MapToResponse(e);
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

	@DELETE
	@Path("{itemCode}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteProductById(@Context HttpHeaders requestHeaders, @PathParam("itemcode") final String itemCode)
    {
        Response    response;

        try
        {
         	//Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
                 
            //Authenticate the user
            keymarkUcUserAuthentication.authenticate(userType, userCode);
                 
            //delete data from database with the given id
            productService.deleteProductById(itemCode);
                
            //Return json reponse
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
	
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	public Response deleteProduct(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
    {
        Response response = null;

        try
        {
        	//Check usercode
            UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            if (!userCodeParser.isValidFormat())
            {
                throw new BedResUnAuthorizedException();
            }
            String userType = userCodeParser.getUserType();
            String userCode = userCodeParser.getUserCode();
            
            //Authenticate the user
            keymarkUcUserAuthentication.authenticate(userType, userCode);
            
            //Retrieve data from database based on the given query parameters
            productService.deleteProduct(uriInfo.getQueryParameters());       
               
            //Return json reponse
            response = Response.ok(MediaType.APPLICATION_JSON).build();
        }
        catch (BedResUnAuthorizedException e)
        {
        	response = BedResExceptionMapper.MapToResponse(e);
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
