package com.bedrosians.bedlogic.resources;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MultivaluedMap;

import net.minidev.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.bedrosians.bedlogic.domain.item.Item;
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
    public Response getProducts(@Context HttpHeaders requestHeaders
                                    , @Context UriInfo uriInfo)
    {
        Response    response;

        //try
        //{
            // Check usercode
           // UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            //if (!userCodeParser.isValidFormat())
            //{
              //  throw new BedResUnAuthorizedException();
            //}
            //String userType = userCodeParser.getUserType();
            //String userCode = userCodeParser.getUserCode();
            
            // Get queryParams
            MultivaluedMap queryParams = uriInfo.getQueryParameters();
                        
            // Retrieve DAO object
            //ProductsDAO productsDAO = new ProductsDAO();
            //Products    result = productsDAO.readProductsByQueryParams(userType, userCode, queryParams);
            
            List<Item> items = productService.getByQueryParameters(queryParams);
            //Map<String, List<Item>> map = new HashMap<String, List<Item>>();
            //map.put("items", items);
            //JSONObject  jsonObject = new JSONObject(map);
            //Products result = new Products(jsonObject);
            //String     jsonStr = result.toJSONString();
            
            ObjectMapper mapper = new ObjectMapper();
            Writer strWriter = new StringWriter();
            try{
               mapper.writeValue(strWriter, items);
            }
            catch(Exception e){
            	e.printStackTrace();
            }
               String jsonStr = strWriter.toString();
            // Return json reponse
            
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
       // }
       // catch (BedDAOException e)
        //{
         //   response = BedDAOExceptionMapper.MapToResponse(e);
        //}
        //catch (BedResException e)
        //{
         //   response = BedResExceptionMapper.MapToResponse(e);
       // }
        
        return response;
    }
	
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response createProduct(@Context HttpHeaders requestHeaders
                                    , @Context UriInfo uriInfo)
    {
        Response    response;

        //try
        //{
            // Check usercode
          //  UserCodeParser  userCodeParser = new UserCodeParser(requestHeaders);
            //if (!userCodeParser.isValidFormat())
            //{
              //  throw new BedResUnAuthorizedException();
            //}
            //String userType = userCodeParser.getUserType();
            //String userCode = userCodeParser.getUserCode();
            
            // Get queryParams
            MultivaluedMap queryParams = uriInfo.getQueryParameters();
                        
            // Retrieve DAO object
            //ProductsDAO productsDAO = new ProductsDAO();
            //Products    result = productsDAO.readProductsByQueryParams(userType, userCode, queryParams);
            
            //Item item = new Item();
    		//item.setItemcd("TEST3");
    		//item.setDescription("a test");
    		//item.set("TestItemName");
    		//item.setOrigin("CHINA");
    		 //String itemId = productService.createProduct(item);
            String itemId = productService.createProduct(queryParams);
            Map<String, String> map = new HashMap<String, String>();
            map.put("itemCode", itemId);
            JSONObject  jsonObject = new JSONObject(map);

            Products result = new Products(jsonObject);

            
            
            // Return json reponse
            String     jsonStr = result.toJSONString();
            response = Response.ok(jsonStr, MediaType.APPLICATION_JSON).build();
        //}
        //catch (BedDAOException e)
        //{
         //   response = BedDAOExceptionMapper.MapToResponse(e);
        //}
        //catch (BedResException e)
        //{
          //  response = BedResExceptionMapper.MapToResponse(e);
        //}
        
        return response;
    }
	
}
