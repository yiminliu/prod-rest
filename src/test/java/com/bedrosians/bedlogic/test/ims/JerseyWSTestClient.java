package com.bedrosians.bedlogic.test.ims;


import java.net.URI;
import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;



public class JerseyWSTestClient {

	private static final String mediaTypeJson = MediaType.APPLICATION_JSON;
	private static final String basePath = "bedlogic/v2";
	private static final String rootResource = "ims";
	private static final String testItemCode = "AECBUB218NR".toUpperCase();
	private static final String colorHue = "RED";
	private static final String originCountry = "USA";
	private static WebTarget service =null;
	private static Client client = null;
	
	private static final String username = "keymark"; //"SCOT";
	private static final String password = "JBED"; //"SCOTT10";
	//private static final HTTPBasicAuthFilter authFilter = new HTTPBasicAuthFilter(username, password);
	//private static final HttpBasicAuthFilter authFilter = new HttpBasicAuthFilter(username, password);
		 
	  
	public static void main(String[] args) {
	   long startTime = System.currentTimeMillis();
		init();
	   
		/******** Item search test *********/	
	   //testGetItemByItemCode(testItemCode);
	   //testGetItemByMultipleItemCodes();
	   //testGetItemByColorHues(colorHue);
	   //testGetItemByMultipleColorCategories(new String[]{colorHue, "GREEN", "YELLOW"});
	   //testGetItemByMultipleColorHues(new String[]{colorHue, "GREEN", "YELLOW"});
	   //testGetItemByOriginCountry(originCountry);
	   //testGetItemsWIthMultipleMaterialStyles();
	   //testGetAllItems();
		//testGetAllActiveItems();
	   
	   /******** Item creation test *********/
	   //testCreateItemWithJsonString();
	   
	   /******** Item update test *********/
	   //testUpdateItemWithJsonString();
	   
	   /******** Item deletion test *********/
	   testDeleteItemByItemCode();
	   
	   /******** Load test ********/
	   //for(int i = 0; i < 100; i++){
	 		//	testGetItemByOriginCountry(originCountry);
	 		//}
	   
	   long totalTime = (System.currentTimeMillis() - startTime);
	   System.out.println("Done test. Total time = " + totalTime + " ms.");
 	}
	
	/*********************** static initiation *********************/
	private static void init(){
	   ClientConfig config = new ClientConfig();
	   //config.getClasses().add(JacksonJaxbJsonProvider.class);
	   client = ClientBuilder.newClient(config); 
	}
	
	/*********************** Individual test cases ****************************/

	private static void testGetItemByItemCode(String itemCode){
	   System.out.println("testGetItemByItemCode");
	   //service = client.resource(getByIdTestURI(""));
	   HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic("guest", "");
	   client.register(authFeature);
	   service = client.target(getTestBaseURI());
	   service.queryParam("itemcode", itemCode);
	   service.request(mediaTypeJson);
	   service.path("/TEST");
	   //service.accept(mediaTypeJson);
	   //System.out.println("Resource URL = " + service.getURI().toASCIIString());
	  
	   Response response = service.request().accept(MediaType.APPLICATION_JSON).get();
	     
	   System.out.println("Response status =" + response.getStatus());
	   //System.out.println("Response type = " + response.getType());
	   System.out.println("Response data : "+ response.toString());
	   String s = response.readEntity(String.class);
	   System.out.println("Output = " + s);
	}
	
	private static void testGetItemByMultipleItemCodes(){
		   System.out.println("testGetItemByMultipleItemCode");
		   HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic("guest", "");
		   client.register(authFeature);
		   service = client.target(getTestBaseURI());
		   //service.queryParam("itemcode", "{NEWITEMCODE1,CRDBARBRU440}");
		   
		   //service.request(mediaTypeJson);
		   //service.accept(mediaTypeJson);
		   //System.out.println("Resource URL = " + service.getURI().toASCIIString());
			   
		   Response response = service.queryParam("itemcode", "CRDBARBRU440").request(mediaTypeJson).get();
		   //Response response = service.get(ClientResponse.class);
			  
		   System.out.println("Response status =" + response.getStatus());
		   //System.out.println("Response type = " + response.getType());
		   System.out.println("Response data : "+ response.toString());
		   String s = response.readEntity(String.class);
		   response.close();
		   System.out.println("Output = " + s);
		}
	
	/*
	private static void testGetItemByOriginCountry(String country){
	  	   System.out.println("testGetItemByOriginCountry");
	  	   MultivaluedMap<String,String> params = new MultivaluedStringMap();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("origin", Arrays.asList(new String[]{country}));
	    
		   service = client.target(getTestBaseURI());
		   service.request(mediaTypeJson);
		   //service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.universal("guest", "");
		   client.register(authFeature);
		   Response response = service.queryParams(params).get(ClientResponse.class);
			  
		   System.out.println("Response header : "+ response.getHeaders());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.readEntity(String.class);
		   System.out.println("Output = " + s);
	}
	*/
	/*
	private static void testGetItemByColorCategory(String colorHue){
	  	   System.out.println("testGetItemByColorCategory");
	  	   long startTime = System.currentTimeMillis();
	  	   MultivaluedMap<String,String> params = new MultivaluedStringMap();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("colorcategory", Arrays.asList(new String[]{colorHue}));
	    
		   service = client.target(getTestBaseURI());
		   HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.universal("guest", "");
		   client.register(authFeature);
		   service.request(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
		
		   //System.out.println("Response header : "+ response.getHeaders());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.readEntity(String.class);
		   //System.out.println("Output = " + s);
		   long totalTime = System.currentTimeMillis() - startTime;
		   System.out.println("Method execution time = " + totalTime + "\n");
	 }
	*/
	/*
	private static void testGetItemByMultipleColorCategories(String[] colorHues){
	  	   System.out.println("testGetItemByMultipleColorCategories");
	  	   long startTime = System.currentTimeMillis();
	  	   MultivaluedMap<String,String> params = new MultivaluedStringMap();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("colorcategory", Arrays.asList(colorHues));
	    
		   service = client.resource(getTestBaseURI());
		   service.request(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.universal("guest", "");
		   client.register(authFeature);
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
			  
		   //System.out.println("Response header : "+ response.getHeaders());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.readEntity(String.class);
		   //System.out.println("Output = " + s);
		   long totalTime = System.currentTimeMillis() - startTime;
		   System.out.println("Method execution time = " + totalTime + "\n");
	 }
	*/
	/*
	private static void testGetItemByColorHues(String colorHue){
	  	   System.out.println("testGetItemByColorHues");
	  	   long startTime = System.currentTimeMillis();
	  	   MultivaluedMap<String,String> params = new MultivaluedStringMap();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("colorhues", Arrays.asList(new String[]{colorHue}));
	    
		   service = client.target(getTestBaseURI());
		   HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.universal("guest", "");
		   client.register(authFeature);
		   service.request(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
		
		   //System.out.println("Response header : "+ response.getHeaders());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.readEntity(String.class);
		   //System.out.println("Output = " + s);
		   long totalTime = System.currentTimeMillis() - startTime;
		   System.out.println("Method execution time = " + totalTime + "\n");
	 }
	*/
	/*
	private static void testGetItemByMultipleColorHues(String[] colorHues){
	  	   System.out.println("testGetItemByMultipleColorHues");
	  	   long startTime = System.currentTimeMillis();
	  	   MultivaluedMap<String,String> params = new MultivaluedStringMap();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("colorhues", Arrays.asList(colorHues));
	    
		   service = client.target(getTestBaseURI());
		   service.request(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.universal("guest", "");
		   client.register(authFeature);
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
			  
		   //System.out.println("Response header : "+ response.getHeaders());
		   System.out.println("Response data : "+ response.toString());
		   String s = response.readEntity(String.class);
		   //System.out.println("Output = " + s);
		   long totalTime = System.currentTimeMillis() - startTime;
		   System.out.println("Method execution time = " + totalTime + "\n");
	 }
	 */
	/*
	 private static void testGetItemsWIthMultipleMaterialStyles(){
	  	   System.out.println("testGetItemsWIthMultipleMaterialStyles");
	  	 long startTime = System.currentTimeMillis();
	  	   MultivaluedMap<String,String> params = new MultivaluedStringMap();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("materialstyle", Arrays.asList(new String[]{"SFCR", "FL"}));
		   service = client.target(getTestBaseURI());
		   service.request(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.universal("guest", "");
		   client.register(authFeature);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
		   
		   System.out.println("Response status : "+ response.getStatus());
		   System.out.println("Response type : "+ response.getType());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		   System.out.println("Output = " + s);
		   long totalTime = System.currentTimeMillis() - startTime;
		   System.out.println("Method execution time = " + totalTime);
	}
	 */
	/*
	private static void testGetAllActiveItems(){
	  	   System.out.println("testGetActiveAllItems");
	  	   MultivaluedMap<String,String> params = new MultivaluedStringMap();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	      
		   service = client.target(getTestBaseURI());
		   service.type(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
		   
		   System.out.println("Response status : "+ response.getStatus());
		   //System.out.println("Response type : "+ response.getType());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.readEntity(String.class);
		  // System.out.println("Output = " + s);
		}
	*/
	
	private static void testCreateItemWithJsonString(){
	    //client.addFilter(authFilter);
	    //client.addFilter(new HTTPBasicAuthFilter("keymark", "JBED"));
	    //service.header("Authorization", "Basic " + "base64encoded_SCOT:SCOTT10");
	    //client.addFilter(new LoggingFilter());
		 HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic("keymark", "JBED");
		client.register(authFeature);
	    service = client.target(getTestBaseURI());
		//service.type(mediaTypeJson);
		//service.accept(mediaTypeJson);
		//System.out.println("Resource URL = " + service.getURI().toASCIIString());
		
	    MultivaluedMap<String,String> params = new MultivaluedStringMap();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("itemcode", Arrays.asList(new String[]{"TEST4"}));
	       //params.put("materialstyle", Arrays.asList(new String[]{"SFCR", "FL"}));
		 //ClientResponse response = service.header("Authorization", "Basic " + "base64encoded_userid:password").type("application/json").accept("application/json").post(ClientResponse.class, input);
		 Response response = service.request("application/json").accept("application/json").post(Entity.json(jStringWithBasicInfo), Response.class);
		 System.out.println("Response status : "+ response.getStatus());
		 //System.out.println("Response type : "+ response.getType());
		 System.out.println("Response data : "+ response.toString());
		   
		 String s = response.readEntity(String.class);
		 System.out.println("Output = " + s);
    }	
	
	
    private static void testUpdateItemWithJsonString(){
    	HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic("keymark", "JBED");
 		client.register(authFeature);
 	    service = client.target(getTestBaseURI());
	  	//service.type(mediaTypeJson);
		//service.accept(mediaTypeJson);
		//System.out.println("Resource URL = " + service.getURI().toASCIIString());
		
	    //ClientResponse response = service.header("Authorization", "Basic " + "base64encoded_userid:password").type("application/json").accept("application/json").post(ClientResponse.class, input);
		Response response = service.request("application/json").accept("application/json").put(Entity.json(jStringWithBasicInfoForUpdate), Response.class);
		System.out.println("Response status : "+ response.getStatus());
		//System.out.println("Response type : "+ response.getType());
		System.out.println("Response data : "+ response.toString());
		   
		String s = response.readEntity(String.class);
		System.out.println("Output = " + s);
    }	

	
	private static void testDeleteItemByItemCode(){
	    HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic("keymark", "JBED");
 		client.register(authFeature);
 	    service = client.target(getTestBaseURI());
	    //client.addFilter(new LoggingFilter());   
	    service = client.target(getTestBaseURI());
	    //service = client.resource(getByIdTestURI(""));
		//service.type(mediaTypeJson);
		//service.accept(mediaTypeJson);
		service.queryParam("itemcode", "TEST1");
		//ClientResponse response = service.header("Authorization", "Basic " + "base64encoded_userid:password").type("application/json").accept("application/json").post(ClientResponse.class, input);
		//ClientResponse response = service.path("itemCode").path("NEWITEMCODE1").type("application/json").accept("application/json").delete(ClientResponse.class);
		String response = service.path("TEST1").request("application/json").delete(String.class);
		
		//System.out.println("Response status : "+ response.getStatus());
	    //System.out.println("Response type : "+ response.getType());
		System.out.println("Response data : "+ response.toString());
		   
		//String s = response.readEntity(String.class);
		//System.out.println("Output = " + s);
	}	
	
	static String jStringWithBasicInfo = 
		     "{\"itemcode\":\"TEST6\","
		    + "\"itemcategory\":\"ATHENA\","
		    + "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
		    + "\"countryorigin\":\"Italy\","
		    + "\"inactivecode\":\"N\","
			//+ "\"showonwebsite\":\"Y\","
			//+ "\"itemtypecode\":\"F\","
			//+ "\"abccode\":\"C\","
			//+ "\"itemcode2\":\"Test\","
			//+ "\"inventoryitemcode\":\"newItemcode7\","
			//+ "\"showonalysedwards\":\"N\","
			//+ "\"offshade\":\"N\","
			//+ "\"printlabel\":\"N\","
			//+ "\"taxclass\":\"T\","
			//+ "\"lottype\":\"S\","
			//+ "\"updatecode\":\"CERA-CRD\","
			//+ "\"directship\":\"Y\","
			//+ "\"dropdate\":null,"
			//+ "\"itemgroupnbr\":0,"
			//+ "\"priorlastupdated\":\"2014-03-31\","
	      	//+ "\"shadevariation\":\"V2\","
	      	+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"}"
      	+ "}";
	
	static String jStringWithBasicInfoForUpdate = 
		     "{\"itemcode\":\"TEST5\","
		    + "\"itemcategory\":\"ATHENA\","
		    + "\"itemdesc\":{\"fulldesc\":\"Test Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"Test Mosaic on 12x12 SHT Ash\"},"
		    + "\"countryorigin\":\"Italy\","
		    + "\"inactivecode\":\"N\","
			+ "\"showonwebsite\":\"Y\","
			+ "\"itemtypecode\":\"F\","
			+ "\"abccode\":\"C\","
			+ "\"itemcode2\":\"Test\","
			//+ "\"inventoryitemcode\":\"newItemcode7\","
			+ "\"showonalysedwards\":\"N\","
			+ "\"offshade\":\"N\","
			+ "\"printlabel\":\"N\","
			+ "\"taxclass\":\"T\","
			+ "\"lottype\":\"S\","
			+ "\"updatecode\":\"CERA-CRD\","
			+ "\"directship\":\"Y\","
			+ "\"dropdate\":null,"
			+ "\"itemgroupnbr\":0,"
			+ "\"priorlastupdated\":\"2014-03-31\","
	      	+ "\"shadevariation\":\"V2\","
	      	+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
     		+ "\"productline\":\"CERA\""
  		+ "}";
	
	/*
	private static void testUpdateWithJsonString(){
		service = client.target(getTestBaseURI());
	    //service.path(basePath).path(rootResource).accept(mediaTypeJson);
	    String input = "{ \"itemCode\" : \"AECBUB217NR\", "
				+ "\"itemdesc1\" : \"a test desc\", "
				+ "\"itemdesc2\" : \"update test\",  "
				+ "\"color\" : \"Red\",  "
				+ "\"category\" : \"Tool\",  "
				+ "\"seriesName\": \"test\", "
				+ "\"origin\": \"USA\", "
				+ "\"type\" : \"Test\",  "
				+ "\"category\" : \"Tool\",  "
				+ "\"seriesName\": \"test\", "
				+ "\"origin\": \"USA\", "
				+ "\"length\" : 14,  "
				+ "\"width\" : 4  "
				+ "}";
	    
	      // service.type("application/json");
		   //service.accept("application/json");
		  // System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   //service.method("PUT");
		   ClientResponse response = service.type("application/json").accept("application/json").put(ClientResponse.class, input);
		  
		   System.out.println("Response status : "+ response.getStatus());
		   System.out.println("Response type : "+ response.getType());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.readEntity(String.class);
		   System.out.println("Output = " + s);
	}	
	*/
	private static URI getTestBaseURI() {
	   return UriBuilder.fromUri("http://localhost:8888/" + basePath + "/" + rootResource).build();
	  // return UriBuilder.fromUri("http://beta.bedrosians.com/api" + "/" + rootResource).build();
	}
	
	private static URI getByIdTestURI(String id) {
		return UriBuilder.fromUri("http://localhost:8080/" + basePath + "/" + rootResource + "?itemcode=" + testItemCode).build();
		//return UriBuilder.fromUri("http://beta.bedrosians.com/api" + "/" + rootResource + "?itemcode=" + testItemCode).build();
	}
	
	

	
	
	/*
	private static void testGetItems(){
	   service.method("GET");
	   ClientResponse response = service.get(ClientResponse.class);
	   int status = response.getStatus();
	   System.out.println("Response's status : "+ status);
	   Map map = response.getProperties();
	   System.out.println("properties:" +map.size());
	   Set set = map.entrySet();
	   Iterator it = set.iterator();
	   while (it.hasNext()){
	      System.out.println("properties:");
	      Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
	      String	key = (String)entry.getKey();
	      String	value = ((List<String>)entry.getValue()).get(0);//.toString();
	      //if(value != null && value.length() > 0)
	      // value = value.toUpperCase();
	      System.out.printf(" key = %s, value = %s", key, value);
	      System.out.println();
	   }
	   String item = response.getEntity(String.class);
	   System.out.println("Output = " + item.toString());
	}
	private static void testUpdateAccount(){
	service = client.resource(getTestBaseURI());
	//service.path(basePath).path(rootResource).accept(mediaTypeJson);
	//service.path("accountId/" + accountId);
	//setMediaType();
	service = client.resource(UriBuilder.fromUri("http://localhost:8080/" + basePath + "/" + rootResource).build());	
	service.accept(mediaTypeJson);
	service.type(mediaTypeJson);
	//String input = "{\"accountId\":\"398477\", \"state\":\"NC\", \"city\":\"Test\",\"companyName\":\"Test dba\"}";
	String input = "{'accountId':'398477', 'state':'NC','city':'Test','companyName':'Test dba'}";

	AccountDetail account = new AccountDetail();
	account.setAccountId("26818");
	account.setAccountManager("Test");
	// JSONObject post = baseResource.path("login")
	// .queryParam("service", "ABC").queryParam("auth", authParam)
	// .accept(MediaType.APPLICATION_JSON_TYPE).post(JSONObject.class);
	System.out.println("Resource URL = " + service.getURI().toASCIIString());
	service.method("PUT");
	ClientResponse response = service.put(ClientResponse.class, account);
	int status = response.getStatus();
	System.out.println("Response status : "+ status);
	//Account account = response.getEntity(Account.class);
	System.out.println("Output = " + account.toString());
	}
    */
} 