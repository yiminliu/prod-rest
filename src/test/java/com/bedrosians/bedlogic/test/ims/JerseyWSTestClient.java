package com.bedrosians.bedlogic.test.ims;

import java.net.URI;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class JerseyWSTestClient {

	private static final String mediaTypeJson = MediaType.APPLICATION_JSON;
	private static final String rootPath = "bedlogic/v2";
	private static final String appPath = "product";
	private static final String testItemCode = "AECBUB218NR".toUpperCase();
	private static final String colorHue = "RED";
	private static final String originCountry = "USA";
	private static WebResource service =null;
	private static Client client = null;

	private static String userType = "keymark";
	private static String userId = "JBED";
	
	public static void main(String[] args) {
	    long startTime = System.currentTimeMillis();
		init();
		JerseyWSTestClient testClient = new JerseyWSTestClient();
		
	    //--------- Search Tests ----------//
		//testGetProductById(testItemCode);
		//testGetProductByMultipleIds();
	    //testGetProductByColorHues(colorHue);
		//testGetProductByMultipleColorHues(new String[]{colorHue, "GREEN", "YELLOW"});
	    //testClient.testGetProductByOriginCountry(originCountry);
	    //testGetProductsWIthMultipleMaterialStyles();
	    //testGetAllProducts();
		//testClient.testGetAllActiveProducts();
		
		 //--------- Search Tests ----------//
		//testClient.testCreateProduct();
		
		 //--------- Search Tests ----------//
		testClient.testUpdateWithJsonString();
		
	    long totalTime = (System.currentTimeMillis() - startTime);
	    System.out.println("Done test. Total time = " + totalTime + " ms.");
 	}
	
	private static void init(){
	   ClientConfig config = new DefaultClientConfig();
	   config.getClasses().add(JacksonJsonProvider.class);
	   client = Client.create(config);
	   client.addFilter(new HTTPBasicAuthFilter(userType, userId));
	}
	
	private static void testGetProductById(String itemCode){
	   System.out.println("testGetProductById");
	   service = client.resource(getByIdTestURI(""));
	   //service.path(rootPath).path(appPath).accept(mediaTypeJson);
	   service.queryParam("itemcode", itemCode);
	   service.type(mediaTypeJson);
	   service.accept(mediaTypeJson);
	   System.out.println("Resource URL = " + service.getURI().toASCIIString());
	   ClientResponse response = service.get(ClientResponse.class);
		  
	   System.out.printf("Response status %s, type %s ", response.getStatus(),response.getType());
	   System.out.println("Response data : "+ response.toString());
	   
	   String s = response.getEntity(String.class);
	   System.out.println("Output = " + s);
	}
	
	private  void testGetProductByMultipleIds(){
	   System.out.println("testGetProductById");
	   service = getRestWebServiceResource();
	   ClientResponse response = service.get(ClientResponse.class);
	   printResult(response);
	}
	
	private void testGetProductByOriginCountry(String country){
  	   System.out.println("testGetProductByOriginCountry");
  	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
       params.put("origin", Arrays.asList(new String[]{country}));
    
	   service = getRestWebServiceResource();
	   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
  
	   printResult(response);
	}
	
	private void testGetProductByColorHues(String colorHue){
  	   System.out.println("testGetProductByColorHues");
  	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
       params.put("inactivecd", Arrays.asList(new String[]{"N"}));
       params.put("colorhues", Arrays.asList(new String[]{colorHue}));
    
       service = getRestWebServiceResource();
	   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
 
	   printResult(response);
    }
	
	private void testGetProductByMultipleColorHues(String[] colorHues){
	   System.out.println("testGetProductByColorHues");
	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	   params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	   params.put("colorhues", Arrays.asList(colorHues));
	   
	   service = getRestWebServiceResource();
	   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
	   printResult(response);
	}
	 
	private void testGetProductsWIthMultipleMaterialStyles(){
	   System.out.println("testGetProductsWIthMultipleMaterialStyles");
	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	   params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	   params.put("materialstyle", Arrays.asList(new String[]{"SFCR", "FL"}));
	   service = getRestWebServiceResource();
	   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
	   printResult(response);
	}
	 
	private void testGetAllActiveProducts(){
	   System.out.println("testGetActiveAllProducts");
	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	   params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	  
	   service = getRestWebServiceResource();
	   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
		  
	   printResult(response);
	}
	
	private void testGetAllProducts(){
	   System.out.println("testGetAllProducts");
	   MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	   //params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	   
	   service = getRestWebServiceResource();
	   ClientResponse response = service.queryParams(params).get(ClientResponse.class);
			  
	   printResult(response);
	}

	private void testCreateProduct(){

	    service = getRestWebServiceResource();
		ClientResponse response = service.type("application/json").accept("application/json").post(ClientResponse.class, basicInfoString);
		printResult( response );
		String s = response.getEntity(String.class);
		System.out.println("Output = " + s);
	}	
	
	private  void testUpdateWithJsonString(){
		service = getRestWebServiceResource();
		ClientResponse response = service.type("application/json").accept("application/json").put(ClientResponse.class, basicInfoString);
		printResult( response );
		String s = response.getEntity(String.class);
		System.out.println("Output = " + s);
	}	
	
	String basicInfoString = 
			       "{\"itemcode\":\"newItemcode9\","
				    + "\"itemcategory\":\"ATHENA\","
		 		    + "\"countryorigin\":\"Italy\","
		 		    + "\"inactivecode\":\"N\","
		 			+ "\"showonwebsite\":\"Y\","
		 			+ "\"itemtypecd\":\"F\","
		 			+ "\"abccd\":\"C\","
		 			+ "\"itemcd2\":\"\","
		 			//+ "\"inventoryitemcd\":\"F\","
		 			+ "\"showonalysedwards\":\"N\","
		 			+ "\"offshade\":\"N\","
		 			+ "\"printlabel\":\" \","
		 			+ "\"taxclass\":\"T\","
		 			+ "\"lottype\":\"\","
		 			+ "\"updatecd\":\"CERA-CRD\",\"directship\":\" \",\"dropdate\":null,\"itemgroupnbr\":0,"
		 			+ "\"priorlastupdated\":\"2014-03-31\","
		 	      	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
		    		+ "\"series\":{\"seriesname\":\"Athena\",\"seriescolor\":\"Ash\"},"
		    		+ "\"material\":{\"materialtype\":\"Porcelain\",\"materialcategory\":\"Trim\",\"materialclass\":\"CTSRC\",\"materialstyle\":\"FW\",\"materialfeature\":\"\"},"
		    		+ "\"shadevariation\":\"V2\","
		    		+ "\"colorcategory\":\"BEIGE\","
		     		+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0},"
		    		+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":0.0000,\"priorsellprice\":14.7000,\"tempprice\":0.0000,\"tempdatefrom\":null,\"tempdatethru\":null,\"priorlistprice\":0.0000},"
		    		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
		    		+ "\"testSpecification\":{\"waterabsorption\":0.1,\"scratchresistance\":0.2,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.3,\"scofwet\":0.4,\"scofdry\":0.5,\"breakingstrength\":6,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.7,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.8,\"leadpoint\":\"N\",\"preconsummer\":0.9,\"posconsummer\":0.11},"
		    		+ "\"relateditemcodes\":null,"
		    		+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
		    		+ "\"packaginginfo\":{\"boxPieces\":4.0,\"boxSF\":0.0,\"boxWeight\":16.8,\"palletBox\":60.0,\"palletSF\":0.0,\"palletWeight\":1007.99994},"
		     		+ "\"productline\":\"\","
		    		//+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
		       		+ "\"cost\":{\"cost1\":0.0000,\"priorcost\":0.0000},"
		       	    + "\"notes\":{\"ponotes\":\"test po note\",\"buyernotes\":\"test note1\",\"internalnotes\":\"test note2\",\"invoicenotes\":\"test note3\"},"
	    			+ "}";
	
	private static URI getTestBaseURI() {
	   return UriBuilder.fromUri("http://localhost:8080/" + rootPath + "/" + appPath).build();
	  // return UriBuilder.fromUri("http://beta.bedrosians.com/api" + "/" + appPath).build();
	}
	
	private static URI getByIdTestURI(String id) {
		return UriBuilder.fromUri("http://localhost:8080/" + rootPath + "/" + appPath + "?itemcode=" + testItemCode).build();
		//return UriBuilder.fromUri("http://beta.bedrosians.com/api" + "/" + appPath + "?itemcode=" + testItemCode).build();
	}
	
	private WebResource getRestWebServiceResource(){
		   WebResource webService = client.resource(getTestBaseURI());
		   webService.type(mediaTypeJson);
		   webService.accept(mediaTypeJson);
		   System.out.println("Resource URL = " + webService.getURI().toASCIIString());
		   return webService;
	}
	
	private void printResult(ClientResponse response){
	   System.out.println("Response header : "+ response.getHeaders());
	   System.out.println("Response data : "+ response.toString());
	   //String s = response.getEntity(String.class);
	   //System.out.println("Output = " + s);
	}
}	