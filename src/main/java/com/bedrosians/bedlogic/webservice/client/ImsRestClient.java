package com.bedrosians.bedlogic.webservice.client;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.domain.ims.VendorId;
import com.bedrosians.bedlogic.domain.ims.embeddable.Description;
import com.bedrosians.bedlogic.exception.BedException;
import com.bedrosians.bedlogic.webservice.client.filter.ClientResponseLoggingFilter;
import com.bedrosians.bedlogic.webservice.client.filter.CurrentUserFilter;
import com.bedrosians.bedlogic.webservice.client.filter.RequestIdFilter;
import com.bedrosians.bedlogic.webservice.client.JacksonObjectMapperProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ImsRestClient {
	
	private static final String HOST = "http://localhost";
	private static final String PORT = "8080";
	private static final String BASE_PATH = "bedlogic/v2";
	private static final String RESOURCE_BASE_URI = HOST + ":" + PORT + "/" + BASE_PATH;
	private static final String MEDIATYPE_JSON = MediaType.APPLICATION_JSON;
	private static final String IMS_ROOT_RESOURCE = "ims";
	
	private static final int CONNECT_TIMEOUT = 10000; //(ms)
	private static final int READ_TIMEOUT = 10000; //(ms)
	
	//private static final String username = "keymark"; //"SCOT";
	//private static final String password = "JBED"; //"SCOTT10";
	//private static final HTTPBasicAuthFilter authFilter = new HTTPBasicAuthFilter(username, password);
		 
	public ImsRestClient(){
	}
		
	
	/*********************** Retrieve Items ****************************/
	
	public Ims getItemByItemCode(String itemCode, String username, String password){
		Response response = null;
		Ims item = null;
		try{
		   response = makeRequest(IMS_ROOT_RESOURCE + "/" + itemCode, "GET", null, username, password);
		   item = (Ims)processResponseItem(response, true);
		}
		catch (Exception e ) {
		   e.printStackTrace();
		   throw e;
		}
	    finally{    
		  response.close();
		}
		return item;
	}
	
	@SuppressWarnings("unchecked")	
	public List<Ims> getItems(MultivaluedMap<String,String> params, String username, String password){
	   params.put("wrappedData", Arrays.asList(new String[]{"No"}));
	   Response response = null;
	   List<Ims> items = null;
	   try{
		   response = makeRequest(IMS_ROOT_RESOURCE, "GET", params, username, password);
		   items = (List<Ims>)processResponse(response, true);
	   }
	   catch (Exception e ) {
		   e.printStackTrace();
		   throw e;
	   }
       finally{  
    	   if(response != null)
		      response.close();
	   }
	   return items;
	}
	
	/*********************** Create an Item ****************************/
	public int createItem(Ims item, String username, String password){
	   Response response = null;
	   int statusCode = 500;
	   try{
		   response = makeUpdateRequest(IMS_ROOT_RESOURCE, "POST", item, username, password);
		   statusCode = processResponseStatus(response);
	   }
	   catch (Exception e ) {
		   e.printStackTrace();
		   throw e;
	   }
	   finally{   
		  if(response != null) 
		     response.close();
	   }
	   return statusCode;
	}
		
	/*********************** Update an Item ****************************/
	public Ims updateItem(Ims item, String username, String password){
	    Response response = null;
		List<Ims> items = null;
		try{
		   response = makeUpdateRequest(IMS_ROOT_RESOURCE, "PUT", item, username, password);
		   processResponseItem(response, true);
		}
		catch (Exception e ) {
		   e.printStackTrace();
		   throw e;
		}
	    finally{    
	   	   response.close();
		}
	    return (items != null && !items.isEmpty())? items.get(0) : null;
	}
		
	/*********************** Delete an Item ****************************/
	public int deleteItem(String itemCode, String username, String password){
	 	Response response = null;
	 	int statusCode = 500;
		try{ 
		   response = makeRequest(IMS_ROOT_RESOURCE + "/" + itemCode, "DELETE", null, username, password);
		   statusCode = processResponseStatus(response);
		}
		catch (Exception e ) {
		   e.printStackTrace();
		   throw e;
		}
	    finally{    
		  response.close();
		}
		return statusCode;
	}
	
	/*********************** Create Client *********************/
	
	private static Client createClient(){
	   ClientConfig config = new ClientConfig();
	   Client client = ClientBuilder.newClient(config)
			          .register(JacksonFeature.class)
			          .register(JacksonObjectMapperProvider.class);
	   return client;
	}
	
	private WebTarget getWebTarget(String resourcePath, String username, String password) {
	   String rootResourceUri = RESOURCE_BASE_URI + "/" + resourcePath;
	   Client client = createClient();
	   HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic(username, password);
	   client.register(authFeature);
	   client.register(new CurrentUserFilter(username));
	   client.register(RequestIdFilter.class);
	   client.register(ClientResponseLoggingFilter.class);
 	   WebTarget webTarget = client.target(rootResourceUri);
	   webTarget.register(MEDIATYPE_JSON);
	   //webTarget.property(ClientProperties.CONNECT_TIMEOUT, CONNECT_TIMEOUT);
	   //webTarget.property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
	   return webTarget;
    }
		
	private Response makeRequest(String resourcePath, String requestMethod, MultivaluedMap<String,String> params, String username, String password){
	   Response response = null;
	   WebTarget webTarget = getWebTarget(resourcePath, username, password);
	   if("GET".equalsIgnoreCase(requestMethod)) {
		   if(params != null && !params.isEmpty()) {
			   for (String param : params.keySet()) {
		            System.out.println("parameter=" + param + ",value=" + StringUtils.collectionToCommaDelimitedString(params.get(param)));
		            webTarget = webTarget.queryParam(param, StringUtils.collectionToCommaDelimitedString(params.get(param)));
 	           }
		       response =  webTarget.request(MEDIATYPE_JSON).accept(MEDIATYPE_JSON).get();
		   }
		   else
			   response =  webTarget.request(MEDIATYPE_JSON).get(Response.class);
	   } 	   
	   else if("DELETE".equalsIgnoreCase(requestMethod))
 	       response = webTarget.request(MEDIATYPE_JSON).delete(Response.class);
	   return response;
	}
	
	private Response makeUpdateRequest(String resourcePath, String requestMethod, Ims item, String username, String password){
	   Response response = null;
	   WebTarget webTarget = getWebTarget(resourcePath, username, password);
	   if("POST".equalsIgnoreCase(requestMethod)) {
		   //response = webTarget.request(MEDIATYPE_JSON).post(Entity.json(JsonUtil.pojoToJsonString(item)));
		   response = webTarget.request(MEDIATYPE_JSON).post(Entity.entity(item, MEDIATYPE_JSON), Response.class);
	   }
	   else if("PUT".equalsIgnoreCase(requestMethod))
	       response = webTarget.request(MEDIATYPE_JSON).accept(MEDIATYPE_JSON).put(Entity.entity(item, MEDIATYPE_JSON), Response.class);
	   else if("DELETE".equalsIgnoreCase(requestMethod))
			  response = webTarget.request(MEDIATYPE_JSON).delete(Response.class);
	   return response;
	}
	
	private List<?> processResponse(Response response, boolean deserializ){
  	   if(response == null)
		  return null;
  	   List<Ims> items = null;
  	   //System.out.println("Response status =" + response.getStatus());
	   //System.out.println("Response type = " + response.getMediaType());
	   System.out.println("Response data : "+ response.toString());

	   if(response.getStatus() == 200) {
		  if(response.hasEntity()) {
		     //String jsonString = response.readEntity(String.class);
		     //items = response.readEntity(new TypeReference<List<Ims>>(){});
		     items = response.readEntity(new GenericType<List<Ims>>(){});
		 	// System.out.println("Output = " +jsonString);
			// items = JsonUtil.fromJSON(new TypeReference<List<Ims>>(){}, jsonString);
		     //for(Ims item : items){ System.out.println(item.toString()); }
		  }   
	   }
	   else if(response.getStatus() >= 500) {
		  System.out.println("Response error : "+ response.readEntity(Exception.class));
		  String jsonString = response.readEntity(String.class);
		  System.out.println("error = " + jsonString);

		  throw new BedException(jsonString);
	   }
	   else if(response.getStatus() >= 400) {
		  String jsonString = response.readEntity(String.class);
		  System.out.println("errror = " + jsonString);
		  throw new BedException(jsonString);
	   }
	   return items;
	}
	
	private Ims processResponseItem(Response response, boolean deserializ){
	  	   if(response == null)
			  return null;
	  	   Ims item = null;
	  	   //System.out.println("Response error : "+ response.readEntity(Exception.class));
		   //System.out.println("Response status =" + response.getStatus());
		   //System.out.println("Response type = " + response.getType());
		   System.out.println("Response data : "+ response.toString());

		   if(response.getStatus() == 200) {
			  if(response.hasEntity()) {
			     //String jsonString = response.readEntity(String.class);
				 item = response.readEntity(Ims.class);
				 //System.out.println("Output = " +jsonString);
				 //items = JsonUtil.fromJSON(new TypeReference<List<Ims>>(){}, jsonString);
				 //ObjectMapper mapper = new ObjectMapper();
				    //mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
				 //   mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
				 //   try{
				 //      item = mapper.readValue(jsonString, (new Ims()).getClass());
				 //   }
				  //     catch(Exception e){e.printStackTrace();
				 //   }
		   		    
			     	   System.out.println(item.toString());
			     //   }
			  }   
		   }
		   else if(response.getStatus() >= 500) {
			  String jsonString = response.readEntity(String.class);
			  System.out.println("error = " + jsonString);
			  throw new BedException(jsonString);
		   }
		   else if(response.getStatus() >= 400) {
			  String jsonString = response.readEntity(String.class);
			  System.out.println("errror = " + jsonString);
			  throw new BedException(jsonString);
		   }
		   //if(items != null)
		     return item;
		    
		}
	
	public int processResponseStatus(Response response){
	  	   if(response == null)
			  return 500;
	  	   //System.out.println("Response error : "+ response.readEntity(Exception.class));
		   //System.out.println("Response status =" + response.getStatus());
		   //System.out.println("Response type = " + response.getType());
		   //System.out.println("Response data : "+ response.toString());

    	   int statusCode = response.getStatus();
		   if(statusCode >= 500) {
			  String jsonString = response.readEntity(String.class);
			  System.out.println("error = " + jsonString);
			  throw new BedException(jsonString);
		   }
		   else if(statusCode >= 400) {
			  String jsonString = response.readEntity(String.class);
			  System.out.println("errror = " + jsonString);
			  throw new BedException(jsonString);
		   }
		   return statusCode;
	}
	
	
	/******************* Tests *******************/
	public static void main(String[] args) {
		String username = "keymark"; //"SCOT";
		String password = "JBED"; //"SCOTT10";
		String testItemCode = "AECBUB218NR".toUpperCase(); 
	    long startTime = System.currentTimeMillis();
		ImsRestClient client = new ImsRestClient();
		
		/******** Item search by item code*********/
		//testItemCode = "MKTPCKCRDEPI";
	    //client.getItemByItemCode(testItemCode, username, password);
	    
		/******** Item search *********/
		MultivaluedMap<String,String> params = new MultivaluedStringMap();
	    //params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	    //params.put("materialstyle", Arrays.asList(new String[]{"SFCR", "FL"}));
		params.put("itemcode", Arrays.asList(new String[]{testItemCode}));
	    client.getItems(params, username, password);
	   
	   /******** Item creation  *********/
	    Ims item = new Ims("TEST2");
		item.setItemcategory("test");
		Description d = new Description();
		d.setItemdesc1("test ");
		item.setItemdesc(d);
		VendorId vi1 = new VendorId();
		vi1.setId(544394);
		Vendor v1 = new Vendor();
		v1.setVendorId(vi1);
		List<Vendor> vl = new ArrayList();
		vl.add(v1);
		item.setNewVendorSystem(vl);
		//"vendorId":{"id":544394},"vendorOrder":1,"vendorName":null,"vendorName2":null,"vendorXrefId":"K822751R","vendorListPrice":12.7,"vendorNetPrice":12.7,"vendorPriceUnit":"PCS","vendorFob":"","vendorDiscountPct":0,"vendorPriceRoundAccuracy":2,"vendorMarkupPct":0,"vendorFreightRateCwt":0,"vendorLandedBaseCost":2.2852,"leadTime":0,"dutyPct":0
		//{"vendorId":{"id":null},"vendorOrder":2,"vendorName":null,"vendorName2":null,"vendorXrefId":"","vendorListPrice":0,"vendorNetPrice":null,"vendorPriceUnit":"0","vendorFob":"","vendorDiscountPct":null,"vendorPriceRoundAccuracy":null,"vendorMarkupPct":0,"vendorFreightRateCwt":0,"vendorLandedBaseCost":0,"leadTime":null,"dutyPct":null},
		//{"vendorId":{"id":null},"vendorOrder":3,"vendorName":null,"vendorName2":null,"vendorXrefId":"","vendorListPrice":0,"vendorNetPrice":null,"vendorPriceUnit":"0","vendorFob":"","vendorDiscountPct":null,"vendorPriceRoundAccuracy":null,"vendorMarkupPct":0,"vendorFreightRateCwt":0,"vendorLandedBaseCost":0,"leadTime":null,"dutyPct":null}]
				
		//client.createItem(item, username, password);
	 
	   /******** Item update  *********/
		//client.updateItem(item, username, password);
		
	   /******** Item deletion *********/
	   //client.deleteItem("TEST", username, password);
	   //testDeleteItemByItemCode();
	   
	   /******** Load test ********/
	   //for(int i = 0; i < 100; i++){
	 		//	testGetItemByOriginCountry(originCountry);
	 		//}
	   
	   long totalTime = (System.currentTimeMillis() - startTime);
	   System.out.println("Done test. Total time = " + totalTime + " ms.");
 	}
	
	
} 