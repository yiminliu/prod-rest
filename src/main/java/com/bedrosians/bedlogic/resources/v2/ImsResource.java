package com.bedrosians.bedlogic.resources.v2;


import java.net.URI;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.exception.BedException;
import com.bedrosians.bedlogic.exception.BedExceptionMapper;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.service.security.KeymarkUcUserSecurityService;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.enums.ApiName;
import com.bedrosians.bedlogic.util.enums.DBOperation;
import com.bedrosians.bedlogic.util.ims.ImsQueryUtil;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;


/**
* This is the IMS Restful Web Service resource class. It accepts HTTP requests and delegates to underlying service classes to fulfill database CRUD operations on ims, and then it return a Response to client.
* This web service resource is used via HTTP request method (GET, POST, PUT, DELETE). JSON is the only format supported for message exchange by this resource currently.
* This class uses "/ims" as its root resource.
*
*/

@RequestScoped
@Controller
@Path("/ims")
public class ImsResource
{
    private static final ApiName APINAME = ApiName.IMS;
    @Autowired
    private ImsService imsService;
    @Autowired
    private KeymarkUcUserSecurityService keymarkUcUserSecurityService;
    @Autowired
    private BedExceptionMapper bedExceptionMapper;
   
    /**
      * This method retrieves a list of items for the given query condition, or error message if it occurs. 
      * @param UriInfo represents query parameters in the form of name/value pairs. It returns an input parameter exception if no any query condition is specified. 
      * Number of resulting records can be specified by setting a value for "maxResults" and if "exactmatch" is set to true, no pattern matching will be performed for all queries.
      * @return Response object which contains the status of "200, OK" and a list of items in json format in message body, or error status and message if exception occurs
      */
     @GET
     @Produces({MediaType.APPLICATION_JSON})
     @Loggable(value = LogLevel.INFO)
     public Response get(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
     { 
    	List<?> itemList = null;
        Response response = null;
        try
        {  
            //Check user security
            keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.SEARCH);
            //Retrieve data from database based on the given query parameters
            String wrappedData = ImsQueryUtil.getValue(uriInfo.getQueryParameters(), "wrappedData");
            if("No".equalsIgnoreCase(wrappedData))
            	itemList = imsService.getItems(uriInfo.getQueryParameters(), false);
            else
            	itemList = imsService.getItems(uriInfo.getQueryParameters(), true);
            //Create json response
            response = Response.ok(itemList, MediaType.APPLICATION_JSON).build();
        }
        catch (BedException e)
        {
           response = bedExceptionMapper.toResponse(e);
        }
        return response;
     }

     /** This method retrieves an item for the given item code.
      * @param itemcode string.
      * @return Response object which contains the status of "200, OK" and an item object in json format, or error status and message if exception occurs
    */
    @GET
    @Path("{itemcode}")
    @Produces({MediaType.APPLICATION_JSON})
    @Loggable(value = LogLevel.INFO)
    public Response getByItemCode(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo, @PathParam("itemcode") final String itemCode)
    {
     	Ims item = null;
   	    Response response = null;
   	    try
        {
           //Check user security
           keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.SEARCH);
           //Retrieve data from database based on the given item code
           item = imsService.getItem(itemCode);
            //Create json response
           response = Response.ok(new ItemWrapper(item), MediaType.APPLICATION_JSON).build();
         }
         catch (BedException e)
         {
           response = bedExceptionMapper.toResponse(e);
         }
         return response;
     }
    
     /**
       * This method creates a item based on the given information.
       * @param String Json String containing the information to create a new item.
       * @return Response object which contains the status of "201, created" and an uri with the created item code, or error status and message if exception occurs
       */
       @POST
       @Consumes(MediaType.APPLICATION_JSON)
       @Loggable(value = LogLevel.INFO)
       public Response create(@Context HttpHeaders requestHeaders, String jsonString)
       {
    	  Response response;
          try
          {
             //Check user security
             keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.CREATE);
             //Create a new item using the given data in json format, and save it into database
             String itemCode = imsService.createItem(jsonString);
             //Create response
             response = Response.created(URI.create("/bedlogic/v2/ims/"+itemCode)).entity(itemCode).build();
          }
          catch (BedException e)
          {
             response = bedExceptionMapper.toResponse(e);
          }
          return response;
       }

     /**
       * This method updates an item based on the given item info.
       * @param String Json String containing item information to update.
       * @return Response object which contains a "200, OK" status and a message body including the updated item in json format, or error status and message if exception occurs
       */
       @PUT
       @Consumes(MediaType.APPLICATION_JSON)
       @Produces({MediaType.APPLICATION_JSON})
       @Loggable(value = LogLevel.INFO)
       public Response update(@Context HttpHeaders requestHeaders, String jsonString)
       {
          Response response;
          try
          {
             //Check user security
             keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.UPDATE);
             //Update an item based on the input json data
             Ims item = imsService.updateItem(jsonString);
              //Create json response
             response = Response.ok(new ItemWrapper(item), MediaType.APPLICATION_JSON).build();
          }
          catch (BedException e)
          {
             response = bedExceptionMapper.toResponse(e);
          }
          return response;
       }

     /**
       * This method deletes an item based on the given item code.
       * @param Item code string.
       * @return Response object which contains a "204, No Content" status and no message body, or error status and message if exception occurs
       */
       @DELETE
       @Path("{itemcode}")
       @Loggable(value = LogLevel.INFO)
       public Response delete(@Context HttpHeaders requestHeaders, @PathParam("itemcode") final String itemCode)
       {
          Response response;
          try
          {
             //Check user security
             keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.DELETE);
             //delete an item from database based on the given item code
             imsService.deleteItemByItemCode(itemCode);
             //Create response
             response = Response.noContent().build();
          }
          catch (BedException e)
          {
             response = bedExceptionMapper.toResponse(e);
          }
          return response;
       }
    	   
}