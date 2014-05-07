package com.bedrosians.bedlogic.test.product;


import java.net.URI;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class JerseyWSTestClient {

	private static final String mediaTypeJson = MediaType.APPLICATION_JSON;
	private static final String rootPath = "bedlogic/rest";
	private static final String appPath = "products";
	private static final String testItemCode = "ADJASSETSALE";
	private static WebResource service =null;
	private static Client client = null;
	
	public static void main(String[] args) {
	   init();
	   testGetProductById(testItemCode);
	   //testCreate();
	   //testUpdateWithJsonString();
	   System.out.println("Done test");
 	}
	
	private static void init(){
	   ClientConfig config = new DefaultClientConfig();
	   config.getClasses().add(JacksonJsonProvider.class);
	   client = Client.create(config);
	}
	
	private static void testGetProductById(String itemCode){
	   service = client.resource(getByIdTestURI(""));
	   //service.path(rootPath).path(appPath).accept(mediaTypeJson);
	   service.queryParam("itemcode", itemCode);
	   //service.type(mediaTypeJson);
	   //service.accept(mediaTypeJson);
	   //service.type(mediaTypeJson);
	   System.out.println("Resource URL = " + service.getURI().toASCIIString());
	   //service.method("GET");
	   ClientResponse response = service.accept("application/json").get(ClientResponse.class);
	  
	   System.out.println("Response status : "+ response.getStatus());
	   System.out.println("Response type : "+ response.getType());
	   System.out.println("Response data : "+ response.toString());
	   
	   String s = response.getEntity(String.class);
	   System.out.println("Output = " + s);
	}
	
	private static void testCreate(){
		service = client.resource(getTestBaseURI());
	    //service.path(rootPath).path(appPath).accept(mediaTypeJson);
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
	    
	       //service.type("application/json");
		   //service.accept("application/json");
		   //System.out.println("Resource URL = " + service.getURI().toASCIIString());
		   //service.method("POST");
		   ClientResponse response = service.type("application/json").accept("application/json").post(ClientResponse.class, input);
		  
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
	// return UriBuilder.fromUri("http://localhost:8080/" + rootPath).build();
	}
	
	private static URI getByIdTestURI(String id) {
		return UriBuilder.fromUri("http://localhost:8080/" + rootPath + "/" + appPath + "?itemcode=" + "{" + testItemCode +", AECBUB217NR" + "}").build();
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