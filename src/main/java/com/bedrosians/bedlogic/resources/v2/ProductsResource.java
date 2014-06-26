package com.bedrosians.bedlogic.resources.v2;


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
import com.bedrosians.bedlogic.domain.item.enums.DBOperation;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedDAOExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.product.ProductService;
import com.bedrosians.bedlogic.service.security.KeymarkUcUserSecurityService;
import com.bedrosians.bedlogic.usercode.UserCodeParser;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.JsonWrapper.ListWrapper;
import com.bedrosians.bedlogic.util.index.IndexUtil;


/**
* This is a restful web service resource class. It acts as the logical resource of Product Service to provide CRUD operations on products.
* This web service resource is used via HTTP request method (GET, POST, PUT, DELETE). JSON is the only format supported for message exchange by this resource.
* This class uses "/product" as its endpoint.
*
*/

@Controller
@Path("/product")
public class ProductsResource
{
    /**
     * Products resource
     */

	@Autowired
	private ProductService productService;
	@Autowired
	private KeymarkUcUserSecurityService keymarkUcUserSecurityService;
	@Autowired
	private IndexUtil indexUtil;
	
	 /**
	* This method retrieves a list of products for the given query condition, or a list of all active products if no query condition is specified .
	* @param UriInfo represents query condition in the form of name/value pairs. If no query is specified, all active products will be returned.
	* Number of resulting records can be specified by setting a value for "maxResults" and if "exactmatch" is set to true, no pattern matching will be performed for all queries.
	* @return Response object contains the status and a json object.
	* @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
	*/
	@GET
    @Produces({MediaType.APPLICATION_JSON})
	public Response getProducts(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
    {   
        Response response = null;

        try
        {
        	//Check user security
        	doUserSecurityCheck(requestHeaders, DBOperation.SEARCH);
            
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
	
	/** This method retrieves an product for the given item code.
     * @param itemcode string.
     * @return Response object to include the status and a json object.
     * @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
   */
	@GET
	@Path("{itemcode}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProductById(@Context HttpHeaders requestHeaders, @PathParam("itemcode") final String itemCode)
    {
        Response    response;

        try
        {
        	//Check user security
        	doUserSecurityCheck(requestHeaders, DBOperation.SEARCH);
                 
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
	
   /**
	* This method creates a product based on the given information.
	* @param Json object containing the information to create a new product.
	* @return Json object representing the status and created product id.
	* @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
	*/
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProduct(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
    {
        Response response;

        try
        {    
        	//Check user security
        	doUserSecurityCheck(requestHeaders, DBOperation.CREATE);
        	
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
	
	
	/**
	* This method updates a product based on the given product info.
	* @param A Json object containing product information to upate.
	* @return Response object to include the status.
	* @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
	*/
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
    {
        Response    response;

        try
        {
        	//Check user security
        	doUserSecurityCheck(requestHeaders, DBOperation.UPDATE);
            
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
	
	/**
	* This method deletes a product based on the given item code.
	* @param A Json object containing product information to upate.
	* @return Json object representing the status.
	* @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
    */
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
    {
        Response    response;

        try
        {
        	//Check user security
        	doUserSecurityCheck(requestHeaders, DBOperation.DELETE);
                 
            //delete product from database with the given product info
            productService.deleteProduct(inputJsonObj);
                
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
	
	/**
	* This method deletes a product based on the given item code.
	* @param Item code string.
	* @return Json object representing the status.
	* @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
    */
	@DELETE
	@Path("{itemCode}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteProductById(@Context HttpHeaders requestHeaders, @PathParam("itemcode") final String itemCode)
    {
        Response    response;

        try
        {
        	//Check user security
        	doUserSecurityCheck(requestHeaders, DBOperation.DELETE);
                 
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
	
	private void doUserSecurityCheck(HttpHeaders requestHeaders, DBOperation operation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException {
		 //Check usercode
        UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
        if (!userCodeParser.isValidFormat())
        {
            throw new BedResUnAuthorizedException();
        }
        String userType = userCodeParser.getUserType();
        String userCode = userCodeParser.getUserCode();
        
        //Authenticate the user
        keymarkUcUserSecurityService.doSecurityCheck(userType, userCode, operation);
	}
	
	/* This method is used to create Lucene indexes for the existing data in database */
	@GET
	@Path("/index")
	public Response createInitialLuceneIndex(){
		 boolean initialzed =  indexUtil.initializeIndex();
		 if(initialzed)
		    return Response.status(200).entity("Index is created").build();
		else
			return Response.status(500).entity("Failed creating index").build();
	}
}
