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

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedDAOExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.exception.BedResExceptionMapper;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.service.security.KeymarkUcUserSecurityService;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.enums.ApiName;
import com.bedrosians.bedlogic.util.enums.DBOperation;


/**
* This restful web service resource class acts as the logical resource of ims Service to provide database CRUD operations on ims.
* This web service resource is used via HTTP request method (GET, POST, PUT, DELETE). JSON is the only format supported for message exchange by this resource.
* This class uses "/ims" as its endpoint.
*
*/

@Controller
@Path("/ims")
public class ImsResource
{
    private static final ApiName APINAME = ApiName.IMS;
    @Autowired
    private ImsService imsService;
    @Autowired
    private KeymarkUcUserSecurityService keymarkUcUserSecurityService;

    /**
      * This method retrieves a list of items for the given query condition, or a list of all active items if no query condition is specified .
      * @param UriInfo represents query condition in the form of name/value pairs. If no query is specified, all active items will be returned.
      * Number of resulting records can be specified by setting a value for "maxResults" and if "exactmatch" is set to true, no pattern matching will be performed for all queries.
      * @return Response object contains the status and a json object.
      * @exception BedResUnAuthorizedException, BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
     */
     @GET
     @Produces({MediaType.APPLICATION_JSON})
     public Response getItems(@Context HttpHeaders requestHeaders, @Context UriInfo uriInfo)
     {
        Response response = null;

        try
        {
            //Check user security
            keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.SEARCH);

            //Retrieve data from database based on the given query parameters
            List<ItemWrapper> wrappedItemList = imsService.getWrappedItems(uriInfo.getQueryParameters());//new ArrayList<ProductWrapper>();

            //Convert the data to Json string
            Products result = new Products(wrappedItemList);
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

     
     /** This method retrieves an item for the given item code.
       * @param itemcode string.
       * @return Response object to include the status and a json object.
       * @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
     */
     @GET
     @Path("{itemcode}")
     @Produces({MediaType.APPLICATION_JSON})
     public Response getItemsByItemCode(@Context HttpHeaders requestHeaders, @PathParam("itemcode") final String itemCode)
     {

    	 Response response;

    	 try
         {
            //Check user security
            keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.SEARCH);

            //Retrieve data from database based on the give id
            Ims ims = imsService.getItemByItemCode(itemCode);
            Products result = new Products(ims);
            String jsonStr = result.toJSONStringWithJacksonWithRootName();

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
       * This method creates a item based on the given information.
       * @param Json object containing the information to create a new item.
       * @return Json object representing the status and created item code.
       * @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
       */
       @POST
       @Produces(MediaType.APPLICATION_JSON)
       @Consumes(MediaType.APPLICATION_JSON)
       public Response createItem(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
       {
          Response response;

          try
          {
             //Check user security
             keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.CREATE);

             //Create a new item using the given data in json format, and save it into database
             String itemCode = imsService.createItem(inputJsonObj);

             //Wrape the newly created item code into json
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
         * This method updates an item based on the given item info.
         * @param A Json object containing item information to update.
         * @return Response object to include the status.
         * @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
         */
         @PUT
         @Produces(MediaType.APPLICATION_JSON)
         @Consumes(MediaType.APPLICATION_JSON)
         public Response updateItem(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
         {
            Response response;

            try
            {
               //Check user security
               keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.UPDATE);

               //Update an item based on the input json data
               imsService.updateItem(inputJsonObj);

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
           * This method deletes an item based on the given item code.
           * @param A Json object containing product information to update.
           * @return Json object representing the status.
           * @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
           */
           @DELETE
           @Produces({MediaType.APPLICATION_JSON})
           @Consumes(MediaType.APPLICATION_JSON)
           public Response deleteItem(@Context HttpHeaders requestHeaders, JSONObject inputJsonObj)
           {
              Response response;

              try
              {
                 //Check user security
                 keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.DELETE);

                 //delete an item from database with the given item info
                 imsService.deleteItem(inputJsonObj);

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
             * This method deletes an item based on the given item code.
             * @param Item code string.
             * @return Json object representing the status.
             * @exception BedDAOBadParamException, BedDAOBadException and BedResException on input error and server side condition errors as well.
             */
             @DELETE
             @Path("{itemCode}")
             @Produces({MediaType.APPLICATION_JSON})
             public Response deleteItemByItemCode(@Context HttpHeaders requestHeaders, @PathParam("itemcode") final String itemCode)
             {
                Response response;

                try
                {
                   //Check user security
                   keymarkUcUserSecurityService.doUserSecurityCheck(requestHeaders, APINAME, DBOperation.DELETE);

                   //delete an item from database based on the given item code
                   imsService.deleteItemByItemCode(itemCode);

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

}