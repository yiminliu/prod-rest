package com.bedrosians.bedlogic.test.product;


import java.net.URI;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.hibernate.Session;

import com.bedrosians.bedlogic.domain.item.Item;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class LoadTestWithJerseyWSTestClient {

	private static final String mediaTypeJson = MediaType.APPLICATION_JSON;
	private static final String rootPath = "bedlogic/v2";
	private static final String appPath = "product";
	private static final String testItemCode = "AECBUB218NR".toUpperCase();
	private static final String colorHue = "RED";
	private static final String originCountry = "USA";
	private static WebResource service =null;
	private static Client client = null;
	
	public static void main(String[] args) {
	   long startTime = System.currentTimeMillis();
		init();
	   
	   //testGetProductById(testItemCode);
		//testGetProductByMultipleIds();
	  //testGetProductByColorHues(colorHue);
		//testGetProductByMultipleColorHues(new String[]{colorHue, "GREEN", "YELLOW"});
	
		for(int i = 0; i < 100; i++){
			testGetProductByOriginCountry(originCountry);
		}
	   //testGetProductByOriginCountry(originCountry);
	   //testGetProductsWIthMultipleMaterialStyles();
	   //testGetAllProducts();
		//testGetAllActiveProducts();
	   //testCreate();
	   //testUpdateWithJsonString();
	   long totalTime = (System.currentTimeMillis() - startTime);
	   System.out.println("Done test. Total time = " + totalTime + " ms.");
 	}
	
	private static void init(){
	   ClientConfig config = new DefaultClientConfig();
	   config.getClasses().add(JacksonJsonProvider.class);
	   client = Client.create(config);
	}
	
	private static void testGetProductById(String itemCode){
	   System.out.println("testGetProductById");
	   service = client.resource(getByIdTestURI(""));
	   //service.path(rootPath).path(appPath).accept(mediaTypeJson);
	   service.queryParam("itemcode", itemCode);
	   service.type(mediaTypeJson);
	   service.accept(mediaTypeJson);
	   System.out.println("Resource URL = " + service.getURI().toASCIIString());
	   //service.method("GET");
	   //ClientResponse response = service.accept("application/json").get(ClientResponse.class);
	   ClientResponse response = service.get(ClientResponse.class);
		  
	   System.out.printf("Response status %s, type %s ", response.getStatus(),response.getType());
	   System.out.println("Response data : "+ response.toString());
	   
	   String s = response.getEntity(String.class);
	   System.out.println("Output = " + s);
	}
	
	private static void testGetProductByMultipleIds(){
		   System.out.println("testGetProductById");
		   service = client.resource(getTestBaseURI());
		   //service.path(rootPath).path(appPath).accept(mediaTypeJson);
		   service.queryParam("itemcode", "{AECBUB217NR,CRDBARBRU440}");
		   service.type(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   //service.method("GET");
		   //ClientResponse response = service.accept("application/json").get(ClientResponse.class);
		   ClientResponse response = service.get(ClientResponse.class);
			  
		   System.out.printf("Response status %s, type %s ", response.getStatus(),response.getType());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		   System.out.println("Output = " + s);
		}
	
	private static void testGetProductByOriginCountry(String country){
	  	   System.out.println("testGetProductByOriginCountry");
	  	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("origin", Arrays.asList(new String[]{country}));
	    
		   service = client.resource(getTestBaseURI());
		   service.type(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
			  
		   System.out.println("Response header : "+ response.getHeaders());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		   //System.out.println("Output = " + s);
	}
	
	private static void testGetProductByColorHues(String colorHue){
	  	   System.out.println("testGetProductByColorHues");
	  	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("colorhues", Arrays.asList(new String[]{colorHue}));
	    
		   service = client.resource(getTestBaseURI());
		   client.addFilter(new HTTPBasicAuthFilter("guest", ""));
		   service.type(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
		
		   System.out.println("Response header : "+ response.getHeaders());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		   //System.out.println("Output = " + s);
	 }
	
	private static void testGetProductByMultipleColorHues(String[] colorHues){
	  	   System.out.println("testGetProductByColorHues");
	  	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("colorhues", Arrays.asList(colorHues));
	    
		   service = client.resource(getTestBaseURI());
		   service.type(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
			  
		   System.out.println("Response header : "+ response.getHeaders());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		   //System.out.println("Output = " + s);
	 }
	 
	 private static void testGetProductsWIthMultipleMaterialStyles(){
	  	   System.out.println("testGetProductsWIthMultipleMaterialStyles");
	  	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       params.put("materialstyle", Arrays.asList(new String[]{"SFCR", "FL"}));
		   service = client.resource(getTestBaseURI());
		   service.type(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
		   
		   System.out.println("Response status : "+ response.getStatus());
		   System.out.println("Response type : "+ response.getType());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		  // System.out.println("Output = " + s);
	}
	 
	private static void testGetAllActiveProducts(){
	  	   System.out.println("testGetActiveAllProducts");
	  	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	      
		   service = client.resource(getTestBaseURI());
		   service.type(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
		   
		   System.out.println("Response status : "+ response.getStatus());
		   System.out.println("Response type : "+ response.getType());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		  // System.out.println("Output = " + s);
		}
	
	private static void testGetAllProducts(){
	  	   System.out.println("testGetAllProducts");
	  	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       //params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       //params.put("colorhues", Arrays.asList(new String[]{colorHue, "GREEN"}));
	    
		   service = client.resource(getTestBaseURI());
		   //service.path(rootPath).path(appPath).accept(mediaTypeJson);
		   //service.queryParam("colorhue", colorHue);
		   service.type(mediaTypeJson);
		   service.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   //service.method("GET");
		   //ClientResponse response = service.accept("application/json").get(ClientResponse.class);
		   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
			  
		//   ClientResponse clientResponse = webResource .path("/listar")
      //          .queryParams(params)
      //          .header(HttpHeaders.AUTHORIZATION, AuthenticationHelper.getBasicAuthHeader())
      //          .get(ClientResponse.class);

		   
		   System.out.println("Response status : "+ response.getStatus());
		   System.out.println("Response type : "+ response.getType());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		  // System.out.println("Output = " + s);
		}
	private static void testCreate(){
	
	    String username = "SCOT";
	    String password = "SCOTT10";

	    final HTTPBasicAuthFilter authFilter = new HTTPBasicAuthFilter(username, password);
	    client.addFilter(authFilter);
	    client.addFilter(new LoggingFilter());
	    
	    service = client.resource(getTestBaseURI());
	    //client.addFilter(new HTTPBasicAuthFilter("guest", ""));
	    client.addFilter(new HTTPBasicAuthFilter("keymark", "JBED"));
	    //service.header("Authorization", "Basic " + "base64encoded_SCOT:SCOTT10");
		service.type(mediaTypeJson);
		service.accept(mediaTypeJson);
		System.out.println("Resource URL = " + service.getURI().toASCIIString());
		
		String input = "{ \"itemCode\" : \"TEST\", "
				+ "\"description\" : \"a test desc\", "
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
		   ClientResponse response = service.header("Authorization", "Basic " + "base64encoded_userid:password").type("application/json").accept("application/json").post(ClientResponse.class, input);
		  
		   System.out.println("Response status : "+ response.getStatus());
		   System.out.println("Response type : "+ response.getType());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		   System.out.println("Output = " + s);
		}	
	
	private static void testUpdateWithJsonString(){
		service = client.resource(getTestBaseURI());
	    //service.path(rootPath).path(appPath).accept(mediaTypeJson);
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
		   System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   //service.method("PUT");
		   ClientResponse response = service.type("application/json").accept("application/json").put(ClientResponse.class, input);
		  
		   System.out.println("Response status : "+ response.getStatus());
		   System.out.println("Response type : "+ response.getType());
		   System.out.println("Response data : "+ response.toString());
		   
		   String s = response.getEntity(String.class);
		   System.out.println("Output = " + s);
		}	
	
	
	
	/*
	private static void testGetProducts(){
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
	//service.path(rootPath).path(appPath).accept(mediaTypeJson);
	//service.path("accountId/" + accountId);
	//setMediaType();
	service = client.resource(UriBuilder.fromUri("http://localhost:8080/" + rootPath + "/" + appPath).build());	
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
	private static URI getTestBaseURI() {
	   return UriBuilder.fromUri("http://localhost:8080/" + rootPath + "/" + appPath).build();
	  // return UriBuilder.fromUri("http://beta.bedrosians.com/api" + "/" + appPath).build();
	}
	
	private static URI getByIdTestURI(String id) {
		return UriBuilder.fromUri("http://localhost:8080/" + rootPath + "/" + appPath + "?itemcode=" + testItemCode).build();
		//return UriBuilder.fromUri("http://beta.bedrosians.com/api" + "/" + appPath + "?itemcode=" + testItemCode).build();
	}
	
	
	/*
	private static URI getTestURI(String query) {
	return UriBuilder.fromUri("http://localhost:8080/" + rootPath + "/" + appPath + "/" + testAccountId).build();
	// return UriBuilder.fromUri("http://localhost:8080/" + rootPath).build();
	}
	private static URI getTestService() {
	return UriBuilder.fromUri("http://localhost:8080/" + rootPath + "/" + appPath + "/accountId/" + testAccountId).build();
	// return UriBuilder.fromUri("http://localhost:8080/" + rootPath).build();
	}
	//private static WebResource getTestBaseURI2() {
	// return UriBuilder.fromUri("http://localhost:8080/" + rootPath + "/" + appPath + "/accountId/" + testAccountId).build().accept(mediaTypeJson);
	// }
	private static URI getProdBaseURI() {
	return UriBuilder.fromUri("http://localhost:8080/" + appPath).build();
	}
     */
	} 