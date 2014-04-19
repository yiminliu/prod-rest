package com.bedrosians.bedlogic.test.product;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.product.ProductService;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/Bedlogic-test-context.xml")
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ProductServiceTest {
		
	//@Mock
	//ItemDao ItemDaoMock;
	
	//@InjectMocks
	//productServiceImpl productService;
	
	@Autowired
	ProductService productService;
	
	
	private static String testItemId = null;
	private static String testNewItemId = null;
	private static String testDescription = null;
	private static String testFullDescription = null;
	private static String testColor = null;
	private static String testColorCategory = null;
    private static String testSeriesName = null;
    private static String testItemTypeCode = null;
    private static String testCategory = null;
    private static String testMaterialCategory = null;
    private static String testMaterialStyle = null;
    private static String testMaterialClass = null;
    private static String testMaterialFeature = null;
    private static String testOrigin = null;
	
    static private String itemcode = "AECBUB217NR";
    static private String testSeriesname = "builder";
 
	
	@Before
	public void setup(){
		testItemId = "TCRBRE33B-2"; 
		testDescription = "13X13 Breccia Beige";
	    testFullDescription = "Field Tile 13x13 Breccia Beige";
	    testColor = "Beige";
	    testColorCategory = "CLEAR";
	    testSeriesName = "Sky";
	    testItemTypeCode = "#";
	    testCategory = "BRECCIA";
	    testMaterialCategory = "Tool";
	    testMaterialFeature = "LE";
	    testMaterialStyle = "FL";
	    testMaterialClass ="DECOR";
	    testOrigin = "China";
	    testNewItemId = "TEST1";
	}
	
	
	@Test
	public void testGetItemById(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		Item item = null;
		try{
		   item = productService.getProductById(testItemId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		String jsonStr = null;
        Products result = new Products(item);
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    System.out.println("items   = " + jsonStr);
		//System.out.println("Item = " + item);
		assertNotNull("should not be null", item);
		assertEquals("Item id should be " + testItemId, testItemId, item.getItemcode());
		//assertEquals("Item name for Item id = 26818 is STONE AGE TILE", "STONE AGE TILE", Item.getItemName());
	}
	
	@Test
	public void testGetItemByIdPatternMatch(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
        params.put("itemcd", Arrays.asList(new String[]{"TCRD"}));
        //params.put("exactMatch", Arrays.asList(new String[]{"false"}));
        List<Item> items = null;
        try{
           items = productService.getProductsByQueryParameters(params);
        }
        catch(Exception e) {
           e.printStackTrace();
        }   
        String jsonStr = null;
        
        System.out.println("number of Items retrieved: "+items.size());
       
        Products result = new Products(items);
        
        try{
            jsonStr = result.toJSONStringWithJackson();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
       
        System.out.println("items   = " + jsonStr);
    }
	
	@Test
    public void testGetItemByColor() throws Exception {
	        System.out.println("testGetItemByColor: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("color", Arrays.asList(new String[]{testColor}));
	        //Items result = rpcDao.createItem(userType, userCode, params);
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and color = %s;  ", prod.getItemcode(), prod.getColor());
	       	    System.out.println();
	           	//assertTrue(testColor.equalsIgnoreCase(prod.getColor().trim()));
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByColorCategory() throws Exception {
	        System.out.println("testGetItemByColorCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("colorcategory", Arrays.asList(new String[]{testColorCategory}));
	        //Items result = rpcDao.createItem(userType, userCode, params);
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and colorcategory = %s;  ", prod.getItemcode(), prod.getColorcategory());
	       	    System.out.println();
	           	//assertTrue(testColor.equalsIgnoreCase(prod.getColor().trim()));
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByOrigin() throws Exception {
	        System.out.println("testGetItemByColor: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("origin", Arrays.asList(new String[]{testOrigin}));
	        //Items result = rpcDao.createItem(userType, userCode, params);
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and origin = %s;  ", prod.getItemcode(), prod.getOrigin());
	       	    System.out.println();
	           	assertTrue(testOrigin.equalsIgnoreCase(prod.getOrigin().trim()));
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByCategory() throws Exception {
	        System.out.println("testGetItemByCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("category", Arrays.asList(new String[]{testCategory}));
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	   // System.out.printf("item code = %s, and Category = %s. ", prod.getItemcd(), prod.getCategory());
	           	assertEquals("The category should be " + testCategory, testCategory, prod.getCategory().trim());
	        }
            Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByMaterialCategory() throws Exception {
	        System.out.println("testGetItemByMaterialCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("materialCategory", Arrays.asList(new String[]{testMaterialCategory}));
	        List<Item> items = productService.getProductsByQueryParameters(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and matCategory = %s. ", prod.getItemcode(), prod.getMaterialcategory());
	       	    System.out.println();
	           	assertEquals(testMaterialCategory, prod.getMaterialcategory().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	       System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialStyle() throws Exception {
	        System.out.println("testGetItemBytestMaterialStyle: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("materialstyle", Arrays.asList(new String[]{testMaterialStyle}));
	        List<Item> items = productService.getProductsByQueryParameters(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and matStyle = %s. ", prod.getItemcode(), prod.getMaterialstyle());
	       	    System.out.println();
	           	assertEquals(testMaterialStyle, prod.getMaterialstyle().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialClass() throws Exception {
	        System.out.println("testGetItemBytestMaterialClass: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("materialclass", Arrays.asList(new String[]{testMaterialClass}));
	        List<Item> items = productService.getProductsByQueryParameters(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and material class = %s. ", prod.getItemcode(), prod.getMaterialclass());
	       	    System.out.println();
	           	assertEquals(testMaterialClass, prod.getMaterialclass().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialFeature() throws Exception {
	        System.out.println("testGetItemBytestMaterialFeature: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("materialfeature", Arrays.asList(new String[]{testMaterialFeature}));
	        List<Item> items = productService.getProductsByQueryParameters(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and material feaature = %s. ", prod.getItemcode(), prod.getMaterialfeature());
	       	    System.out.println();
	           	assertEquals(testMaterialFeature, prod.getMaterialfeature().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	 }
	
	
	@Test
    public void testGetItemBySeriesName() throws Exception {
	        System.out.println("testGetItemBySeriesName: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("seriesname", Arrays.asList(new String[]{testSeriesName}));
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s and SeriesName = %s. ", prod.getItemcd(), prod.getSeriesname());
	           	assertEquals(testSeriesName, prod.getSeriesname().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	 }
	
	 @Test
	 public void testGetItemBySize() throws Exception {
	        System.out.println("testGetItemBySize: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("size", Arrays.asList(new String[]{"12X9", "10X8",  "4X8"}));
		    List<Item> items = productService.getProductsByQueryParameters(params);
		    String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 System.out.printf("itemcd = %s , length =  %1f and width = %1f . ", prod.getItemcode(), prod.getNmLength(), prod.getNmWidth());
	        	 System.out.println();
	        	//assertNotNull(testColor.toUpperCase(), prod.getColor());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("resulting jsonStr:");
	        System.out.println("items   = " + jsonStr);
	    }
	 
	 @Test
	    public void testGetItemByLengthAndWidth() throws Exception {
	        System.out.println("testGetItemByLengthAndWidth: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("length", Arrays.asList(new String[]{"12"}));
	        params.put("width", Arrays.asList(new String[]{"9"}));
		    List<Item> items = productService.getProductsByQueryParameters(params);
		    String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 System.out.println("itemcd:  " + prod.getItemcode() +"   " + " length X width = " +prod.getLength() + " X " + prod.getWidth());
	        	 assertTrue(prod.getNmLength() <= 12);
	        	 assertTrue(prod.getNmWidth() <= 9);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	    }
	 
	 @Test
	 public void testGetItemByMinLength() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     	params.put("lengthmin", Arrays.asList(new String[]{"120"}));
		
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	            System.out.println("length X width = " + prod.getNmLength() + " X " + prod.getNmWidth());
	        	assertTrue(prod.getNmLength() >= 120.0);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	    }
	 
	 @Test
	 public void testGetItemByMaxLength() throws Exception {
	        System.out.println("testGetItemByMaxLength: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     	params.put("lengthmax", Arrays.asList(new String[]{"2"}));
		
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	//System.out.printf("itemcd = %s, length X width = %f, %f", prod.getItemcd(), prod.getNmLength(), prod.getNmWidth());
	        	assertTrue(prod.getNmLength() <= 2.0);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	    }
	 
	 @Test
	    public void testGetItemByMaxLengthAndMaxWidth() throws Exception {
	        System.out.println("testGetItemByMaxLength: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     	params.put("lengthmax", Arrays.asList(new String[]{"2"}));
	     	params.put("widthmax", Arrays.asList(new String[]{"1"}));
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	// System.out.printf("itemcd = %s, length X width = %f, %f", prod.getItemcd(), prod.getNmLength(), prod.getNmWidth());
	        	assertTrue(prod.getNmLength() <= 2.0);
	        	assertTrue(prod.getNmWidth() <= 1.0);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	    }
	
	 @Test
	    public void testGetItemByMaxAndMinLength() throws Exception {
	        System.out.println("testGetItemByMaxLength: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     	params.put("lengthmax", Arrays.asList(new String[]{"2"}));
	     	params.put("lengthMin", Arrays.asList(new String[]{"1"}));
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	System.out.printf("itemcd = %s, length X width = %f, %f", prod.getItemcode(), prod.getNmLength(), prod.getNmWidth());
	        	System.out.println();
	        	assertTrue(prod.getNmLength() <= 2.0);
	        	assertTrue(prod.getNmLength() >= 1.0);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	    }
	 
	
	 @Test
     public void testGetItemByMultivaluedMap() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       // params.put("ItemId", Arrays.asList(new String[]{"Test7"}));
	       
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("color", Arrays.asList(new String[]{testColor}));
			params.put("category", Arrays.asList(new String[]{testCategory}));
			//params.put("waterAbsorption", Arrays.asList(new String[]{"0.05"}));
			//params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			//params.put("ItemManager", Arrays.asList(new String[]{"TestManager"}));
			//params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
  	        //params.put("length", Arrays.asList(new String[]{"4"}));
		    //params.put("width", Arrays.asList(new String[]{"4"}));
			params.put("origin", Arrays.asList(new String[]{testOrigin}));
		    //params.put("grade", Arrays.asList(new String[]{"First"}));
				
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());

	        for(Item prod : items){
	        	 System.out.println("item code = " + prod.getItemcode());
	        	 System.out.println("color = " + prod.getColor());
	        	 System.out.println("category = " + prod.getCategory());
	        	 System.out.println("origin = " + prod.getOrigin());
	        	 
	        	 assertEquals(testCategory, prod.getCategory().trim());
	        	 assertEquals(testOrigin, prod.getOrigin().trim());

	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	    }
	
	 
	 @Test
	 public void testGetItemByMultivaluedMapWithOneMultiValues() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcode", Arrays.asList(new String[]{"AECBUB217NR","CRDBARBRU440"}));
	       
	       // params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        //params.put("color", Arrays.asList(new String[]{testColor, "red"}));
			//params.put("category", Arrays.asList(new String[]{testCategory}));
			//params.put("waterAbsorption", Arrays.asList(new String[]{"0.05"}));
			//params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			//params.put("ItemManager", Arrays.asList(new String[]{"TestManager"}));
			//params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
			
		//		params.put("length", Arrays.asList(new String[]{"4"}));
		//		params.put("width", Arrays.asList(new String[]{"4"}));
			//	params.put("origin", Arrays.asList(new String[]{"China"}));
		//		params.put("grade", Arrays.asList(new String[]{"First"}));
				
					
		
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());

	        for(Item item : items){
	        	System.out.println("item = "+item);
	        	//System.out.println("Notes = "+item.getNotes());
	        	//System.out.println("Icons = "+item.getIcons());
	        	//System.out.println("Icon = "+ item.getIcon() == null? null : (new Products(item.getIcon()).toJSONStringWithJackson()));
	        	//System.out.println("Icon = "+(new Products(item.getIcon().getAdaAccessibility()).toJSONStringWithJackson()));
	        	
	        }

	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	 }
	       
	 @Test
	 public void testGetItemByNewFeature() throws Exception {
	        System.out.println("testGetItemBynewFeature: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       	params.put("grade", Arrays.asList(new String[]{"First"}));
	    	params.put("status", Arrays.asList(new String[]{"Good"}));
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        List<Item> items = productService.getProductsByQueryParameters(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 //System.out.println("item = " + prod);
	        	 //System.out.println("item new feature = " + prod.getImsNewFeature());
	        	 assertEquals(prod.getImsNewFeature().getGrade(), Grade.First);
	        	 assertEquals(prod.getImsNewFeature().getStatus(), Status.Good);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);
	  }
	
	 //@Test
	 public void testGetItemByPurchaser() throws Exception {
	    System.out.println("testGetItemByPurchaser: ");
	    MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	   	params.put("purchaser", Arrays.asList(new String[]{"LANA"}));
	   	params.put("status", Arrays.asList(new String[]{"Good"}));
	    // Items result = rpcDao.createItem(userType, userCode, params);
	    List<Item> items = productService.getProductsByQueryParameters(params);
	    String jsonStr = null;
	    System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	    for(Item prod : items){
	      	 System.out.println("item = " + prod);
	      	 System.out.println("item new feature = " + prod.getImsNewFeature());
	    }
	    Products result = new Products(items);
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    System.out.println("items   = " + jsonStr);
	  }
	// @Test
	    public void testGetItemByVendorId() throws Exception {
	        System.out.println("testGetItemByVEndorId: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       	params.put("vendorId", Arrays.asList(new String[]{"800001"}));		
	        List<Item> items = productService.getProductsByQueryParameters(params);
	       
	        System.out.println("number of Items retrieved: "+items.size());
	        for(Item prod : items){
	        	 System.out.println("item = " + prod);
	        	 System.out.println("item new feature = " + prod.getImsNewFeature());
	        }
	       
            ObjectMapper mapper = new ObjectMapper();
            Writer strWriter = new StringWriter();
            mapper.writeValue(strWriter, items);
            String jsonStr = strWriter.toString();
            
            // Return json reponse
            //String     jsonStr = result.toJSONString();
	        
	        System.out.println("number of Items retrieved: "+items.size());
	        System.out.println("Output json string = " + jsonStr);
	        
	      	//assertNotNull(testColor.toUpperCase(), prod.getColor());
	   
	       
	       // assertNotNull(id);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	
	 @Test
	 //@Transactional
	 public void testCreateItemWithItem(){
			System.out.println("test create Item ...");
			Item item = new Item();
			item.setItemcode("TEST16");
			item.setItemdesc1("test2");
			try{
	           productService.createProduct(item);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println("Item = "+ item.toString());
			//assertEquals(testNewItemId, item.getItemcode());
			
		}
	 
	
	 @Test
	 public void testCreateItemWithJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcd","TEST17");
	        params.put("description", "This is a test");
	        params.put("color", testColor);
			params.put("category", testCategory);
			params.put("seriesname", "test");
			params.put("type", "test");
			params.put("itemtypecd", "F");
		    params.put("origin", "China");
			params.put("inactivecd", "N");
			params.put("showonwebsite", "Y");
			params.put("taxClass", "Tax");
			
			params.put("itemcd2", "TEST1-1");
			params.put("abccd", "P");
	        params.put("lottype", "S");
	        params.put("updatecd", "Test");
			params.put("icons", "NYYNYNYNYNYNYNYNYNYN");
			params.put("seriesname", "test");
			params.put("subtype", "test");
			params.put("itemtypecd", "F");
		    params.put("pricegroup", "1");
			//params.put("application", "AA");
			params.put("productline", "Test");
			params.put("directship", "N");
			params.put("dropdate", "01/01/2015");
			params.put("itemgroupnbr", "12");
	    	
			//-----cost ----//
			params.put("cost", "12.0");
			params.put("priorCost", "21.0");
			params.put("priorCost1", "12.0");
			params.put("futureCost", "12.0");
			params.put("futureCost1", "12.0");
			params.put("poIncludeinVendorCost", "12.0");
			//params.put("costRangePct", "12.0");
			params.put("nonstockcostpct", "12.0");
		
		    
			
			params.put("residential", "FR:WR:CR");
			params.put("commercial", "FC:WC:CC");
			params.put("productManager", "Manager");
			params.put("buyer", "TestBuyer");
		
			params.put("grade", "First");//Grade.FIRST.getDescription());
			params.put("status", "Good");	
			params.put("mpsCode", "Drop");//Grade.FIRST.getDescription()}))
			params.put("designStyle", "Modern");
			params.put("designLook", "WOOD");
			params.put("body", "Red Body");
			params.put("edge", "Tumbled");
			params.put("surfaceApplication", "Silk");
			params.put("surfaceType", "Cross Cut");
			params.put("surfaceFinish", "Antiquated");
			params.put("warranty", "3");
			params.put("recommendedGroutJointMin", "1");
			params.put("recommendedGroutJointMax", "2");
			
			//----- dimension ------//
			params.put("length", "14");
			params.put("width", "4");
			params.put("thickness", "2 1/2");
			params.put("nmLength", "14.0");
			params.put("nmWidth", "4.0");
			params.put("nmThickness", "2.5");
			params.put("sizeunits", "E");
			params.put("thicknessunit", "E");
			
			//----- material info -------//
			params.put("mattype", "test");
			params.put("matcategory", "test");
			params.put("matstyle", "test");
			params.put("mfeature", "test");
			params.put("materialclassCd", "test");
			
			
			//------- price info --------//	
			params.put("price", "5.5");
			params.put("futurePrice", "4.0");
			params.put("priorPrice", "5.5");
			params.put("tempPrice", "4.0");
			params.put("tempdatefrom", new Date().toString());
			//params.put("tempdatethru", (new Date()).toString());
			params.put("pricemarginpct", "0.435");
			params.put("priceroundaccuracy", "2");
			
			//------- test info -------//
			params.put("waterAbsorption", "0.05");
			params.put("scratchResistance", "0.05");
			params.put("frostResistance", "Y");
			params.put("chemicalResistance", "Y");
			params.put("peiAbrasion", "0.05");
			params.put("scofWet", "0.05");
			params.put("breakingStrength", "5");
			params.put("scofDry", "0.05");
	
			//------ vendors ------//
			params.put("vendorId", "800001");
			params.put("vendorxrefcd", "43535ff");
			params.put("vendorpriceunit", "PCS");
			params.put("vendorfob", "test");
			params.put("vendorlistprice", "45");
			
			params.put("vendorlistprice", "0.3");
			params.put("vendordiscpct1", "0.435");
			params.put("vendorroundaccuracy", "2");
			params.put("vendornetprice", "45");
				
			params.put("vendormarkuppct", "2");
			params.put("vendorfreightratecwt", "45");
			
			params.put("vendorlandedbasecost", "0.3");
			params.put("dutypct", "0.435");
			params.put("leadtime", "2");
			
			params.put("v2_vendorId", "800002");
			params.put("v2_vendorxrefcd", "43535ff");
			params.put("v2_vendorpriceunit", "PCS");
			params.put("v2_vendorfob", "test");
			params.put("v2_vendorlistprice", "43.3");
			params.put("v2_vendordiscpct1", "0.435");
			params.put("v2_vendorroundaccuracy", "2");
			params.put("v2_vendornetprice", "45");
			params.put("v2_vendormarkuppct", "2");
			params.put("v2_vendorfreightratecwt", "45");
			params.put("v2_vendorlandedbasecost", "0.3");
			params.put("v2_dutypct", "0.435");
			params.put("v2_leadtime", "2");
			
			params.put("v3_vendorId", "800003");
			params.put("v3_vendorxrefcd", "4y6f");
			params.put("v3_vendorpriceunit", "PCS");
			params.put("v3_vendorfob", "testr");
			params.put("v3_vendorlistprice", "66.3");
			params.put("v3_vendordiscpct1", "0.735");
			params.put("v3_vendorroundaccuracy", "3");
			params.put("v3_vendornetprice", "54");
			params.put("v3_vendormarkuppct", "3");
			params.put("v3_vendorfreightratecwt", "44");
			params.put("v3_vendorlandedbasecost", "0.3");
			params.put("v3_dutypct", "0.435");
			params.put("v3_leadtime", "4");
		   
			//prior vendor
			params.put("priorvendorpriceunit", "PCS");
			params.put("priorvendorfob", "test");
			params.put("priorvendorlistprice", "45.44");
			params.put("priorvendordiscpct1", "0.435");
			params.put("priorvendorroundaccuracy", "2");
			params.put("priorvendornetprice", "45");
			params.put("priorvendormarkuppct", "2");
			params.put("priorvendorfreightratecwt", "45");
			params.put("priorvendorlandedbasecost", "0.3");
			    
			//--------- units ----------//
			params.put("stdunit", "PCS");
			params.put("stdratio", "1.0");
			params.put("ordratio", "1.0");
			
			params.put("baseunit", "PCS");
			params.put("baseisstdsell", "Y");
			params.put("baseisstdord", "N");
			params.put("baseisfractqty", "N");
			params.put("baseispackunit", "Y");
			params.put("basevolperunit", "765");
			params.put("basewgtperunit", "115");
			params.put("baseupc", "878775");
			
			params.put("unit1unit", "CTN");
			params.put("unit1ratio", "10");
			params.put("unit1isstdsell", "N");
			params.put("unit1isstdord", "Y");
			params.put("unit1isfractqty", "N");
			params.put("unit1ispackunit", "Y");
			params.put("unit1upc", "8787750");
			params.put("unit1wgtperunit", "110");
			
			params.put("unit2unit", "PLA");
			params.put("unit2ratio", "100");
			params.put("unit2isstdsell", "N");
			params.put("unit2isstdord", "Y");
			params.put("unit2isfractqty", "N");
			params.put("unit2ispackunit", "Y");
			params.put("unit2upc", "878775");
			params.put("unit2wgtperunit", "120");
			
			params.put("unit3unit", "PLA");
			params.put("unit3ratio", "10");
			params.put("unit3isstdsell", "N");
			params.put("unit3isstdord", "Y");
			params.put("unit3isfractqty", "N");
			params.put("unit3ispackunit", "Y");
			params.put("unit3upc", "878775");
			params.put("unit3wgtperunit", "130");
			
			params.put("unit4unit", "CTN");
			params.put("unit4ratio", "100");
			params.put("unit4isstdsell", "N");
			params.put("unit4isstdord", "Y");
			params.put("unit4isfractqty", "N");
			params.put("unit4ispackunit", "Y");
			params.put("unit4upc", "878775");
			params.put("unit4wgtperunit", "140");
			
		
		// Icon
			params.put("originCountry", "China");
			params.put("exteriorProduct", "True");
			params.put("adaAccessibility", "N");
			params.put("throughColor", "True");
			params.put("colorBody", "Y");
			params.put("inkJet", "N");
			params.put("glazed", "Y");
			params.put("unglazed", "Y");
			params.put("rectifiedEdge", "N");
			params.put("chiseledEdge", "True");
			params.put("versaillesPattern", "Y");
			params.put("recycled", "N");
			params.put("postRecycled", "Y");
			params.put("preRecycled", "True");
			params.put("icon_greenFriendly", "Y");
			params.put("icon_leadPoint", "True");
			params.put("recycled", "True");
			params.put("coefficientOfFriction", "Y");
		
			//notes
			params.put("poNote", "test Po note");
			params.put("buyerNote", "test buyer note");
			params.put("invoiceNote", "test invoice note");
			params.put("internalNote", "test internal note");
		
			//params.put("designLook", "Wood");
			//System.out.println("Transaact is completed = " + TransactionAspectSupport.currentTransactionStatus().isCompleted());
			//System.out.println("Transaact sRollbackOnly = " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	   
	
	 @Test
	 @Transactional
	 public void testCreateItemWithMultivalues() throws Exception {
	        System.out.println("testCreateItem: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcd", Arrays.asList(new String[]{"TEST18"}));
	        params.put("description", Arrays.asList(new String[]{"This is a test"}));
	        params.put("color", Arrays.asList(new String[]{testColor}));
			params.put("category", Arrays.asList(new String[]{testCategory}));
			params.put("seriesname", Arrays.asList(new String[]{"test"}));
			params.put("type", Arrays.asList(new String[]{"test"}));
			params.put("itemtypecd", Arrays.asList(new String[]{"F"}));
		    params.put("origin", Arrays.asList(new String[]{"China"}));
			params.put("inactivecd", Arrays.asList(new String[]{"N"}));
			params.put("showonwebsite", Arrays.asList(new String[]{"Y"}));
			params.put("taxClass", Arrays.asList(new String[]{"Tax"}));
			
			params.put("itemcd2", Arrays.asList(new String[]{"TEST1-1"}));
			params.put("abccd", Arrays.asList(new String[]{"P"}));
	        params.put("lottype", Arrays.asList(new String[]{"S"}));
	        params.put("updatecd", Arrays.asList(new String[]{"Test"}));
			params.put("icons", Arrays.asList(new String[]{"NYYNYNYNYNYNYNYNYNYN"}));
			params.put("seriesname", Arrays.asList(new String[]{"test"}));
			params.put("subtype", Arrays.asList(new String[]{"test"}));
			params.put("itemtypecd", Arrays.asList(new String[]{"F"}));
		    params.put("pricegroup", Arrays.asList(new String[]{"1"}));
			//params.put("application", Arrays.asList(new String[]{"AA"}));
			params.put("productline", Arrays.asList(new String[]{"Test"}));
			params.put("directship", Arrays.asList(new String[]{"N"}));
			params.put("dropdate", Arrays.asList(new String[]{"01/01/2015"}));
			params.put("itemgroupnbr", Arrays.asList(new String[]{"12"}));
	    	params.put("nonstockcostpct", Arrays.asList(new String[]{"12.0"}));
			
			params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			params.put("commercial", Arrays.asList(new String[]{"FC:WC:CC"}));
			params.put("productManager", Arrays.asList(new String[]{"Manager"}));
			params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
		
			params.put("grade", Arrays.asList(new String[]{"First"}));//Grade.FIRST.getDescription()}));
			params.put("status", Arrays.asList(new String[]{"Good"}));	
			params.put("mpsCode", Arrays.asList(new String[]{"Drop"}));//Grade.FIRST.getDescription()}))
			params.put("designStyle", Arrays.asList(new String[]{"Modern"}));
			params.put("designLook", Arrays.asList(new String[]{"WOOD"}));
			params.put("body", Arrays.asList(new String[]{"Red Body"}));
			params.put("edge", Arrays.asList(new String[]{"Tumbled"}));
			params.put("surfaceApplication", Arrays.asList(new String[]{"Silk"}));
			params.put("surfaceType", Arrays.asList(new String[]{"Cross Cut"}));
			params.put("surfaceFinish", Arrays.asList(new String[]{"Antiquated"}));
			params.put("warranty", Arrays.asList(new String[]{"3"}));
			params.put("recommendedGroutJointMin", Arrays.asList(new String[]{"1"}));
			params.put("recommendedGroutJointMax", Arrays.asList(new String[]{"2"}));
			
			//----- dimension ------//
			params.put("length", Arrays.asList(new String[]{"14"}));
			params.put("width", Arrays.asList(new String[]{"4"}));
			params.put("thickness", Arrays.asList(new String[]{"2 1/2"}));
			params.put("nmLength", Arrays.asList(new String[]{"14.0"}));
			params.put("nmWidth", Arrays.asList(new String[]{"4.0"}));
			params.put("nmThickness", Arrays.asList(new String[]{"2.5"}));
			params.put("sizeunits", Arrays.asList(new String[]{"E"}));
			params.put("thicknessunit", Arrays.asList(new String[]{"E"}));
			
			//----- material info -------//
			params.put("mattype", Arrays.asList(new String[]{"test"}));
			params.put("matcategory", Arrays.asList(new String[]{"test"}));
			params.put("matstyle", Arrays.asList(new String[]{"test"}));
			params.put("mfeature", Arrays.asList(new String[]{"test"}));
			params.put("materialclassCd", Arrays.asList(new String[]{"test"}));
			
			
			//------- price info --------//	
			params.put("price", Arrays.asList(new String[]{"5.5"}));
			params.put("futurePrice", Arrays.asList(new String[]{"4.0"}));
			params.put("priorPrice", Arrays.asList(new String[]{"5.5"}));
			params.put("tempPrice", Arrays.asList(new String[]{"4.0"}));
			params.put("tempdatefrom", Arrays.asList(new String[]{new Date().toString()}));
			//params.put("tempdatethru", Arrays.asList(new String[]{(new Date()).toString()}));
			params.put("pricemarginpct", Arrays.asList(new String[]{"0.435"}));
			params.put("priceroundaccuracy", Arrays.asList(new String[]{"2"}));
			
			//------- test info -------//
			params.put("waterAbsorption", Arrays.asList(new String[]{"0.05"}));
			params.put("scratchResistance", Arrays.asList(new String[]{"0.05"}));
			params.put("frostResistance", Arrays.asList(new String[]{"Y"}));
			params.put("chemicalResistance", Arrays.asList(new String[]{"Y"}));
			params.put("peiAbrasion", Arrays.asList(new String[]{"0.05"}));
			params.put("scofWet", Arrays.asList(new String[]{"0.05"}));
			params.put("breakingStrength", Arrays.asList(new String[]{"5"}));
			params.put("scofDry", Arrays.asList(new String[]{"0.05"}));
	
			//------ vendors ------//
			params.put("vendorId", Arrays.asList(new String[]{"800001"}));
			params.put("vendorxrefcd", Arrays.asList(new String[]{"43535ff"}));
			params.put("vendorpriceunit", Arrays.asList(new String[]{"PCS"}));
			params.put("vendorfob", Arrays.asList(new String[]{"test"}));
			params.put("vendorlistprice", Arrays.asList(new String[]{"45"}));
			
			params.put("vendorlistprice", Arrays.asList(new String[]{"0.3"}));
			params.put("vendordiscpct1", Arrays.asList(new String[]{"0.435"}));
			params.put("vendorroundaccuracy", Arrays.asList(new String[]{"2"}));
			params.put("vendornetprice", Arrays.asList(new String[]{"45"}));
				
			params.put("vendormarkuppct", Arrays.asList(new String[]{"2"}));
			params.put("vendorfreightratecwt", Arrays.asList(new String[]{"45"}));
			
			params.put("vendorlandedbasecost", Arrays.asList(new String[]{"0.3"}));
			params.put("dutypct", Arrays.asList(new String[]{"0.435"}));
			params.put("leadtime", Arrays.asList(new String[]{"2"}));
			
			params.put("v2_vendorId", Arrays.asList(new String[]{"800002"}));
			params.put("v2_vendorxrefcd", Arrays.asList(new String[]{"43535ff"}));
			params.put("v2_vendorpriceunit", Arrays.asList(new String[]{"PCS"}));
			params.put("v2_vendorfob", Arrays.asList(new String[]{"test"}));
			params.put("v2_vendorlistprice", Arrays.asList(new String[]{"43.3"}));
			params.put("v2_vendordiscpct1", Arrays.asList(new String[]{"0.435"}));
			params.put("v2_vendorroundaccuracy", Arrays.asList(new String[]{"2"}));
			params.put("v2_vendornetprice", Arrays.asList(new String[]{"45"}));
			params.put("v2_vendormarkuppct", Arrays.asList(new String[]{"2"}));
			params.put("v2_vendorfreightratecwt", Arrays.asList(new String[]{"45"}));
			params.put("v2_vendorlandedbasecost", Arrays.asList(new String[]{"0.3"}));
			params.put("v2_dutypct", Arrays.asList(new String[]{"0.435"}));
			params.put("v2_leadtime", Arrays.asList(new String[]{"2"}));
			
			params.put("v3_vendorId", Arrays.asList(new String[]{"800003"}));
			params.put("v3_vendorxrefcd", Arrays.asList(new String[]{"4y6f"}));
			params.put("v3_vendorpriceunit", Arrays.asList(new String[]{"PCS"}));
			params.put("v3_vendorfob", Arrays.asList(new String[]{"testr"}));
			params.put("v3_vendorlistprice", Arrays.asList(new String[]{"66.3"}));
			params.put("v3_vendordiscpct1", Arrays.asList(new String[]{"0.735"}));
			params.put("v3_vendorroundaccuracy", Arrays.asList(new String[]{"3"}));
			params.put("v3_vendornetprice", Arrays.asList(new String[]{"54"}));
			params.put("v3_vendormarkuppct", Arrays.asList(new String[]{"3"}));
			params.put("v3_vendorfreightratecwt", Arrays.asList(new String[]{"44"}));
			params.put("v3_vendorlandedbasecost", Arrays.asList(new String[]{"0.3"}));
			params.put("v3_dutypct", Arrays.asList(new String[]{"0.435"}));
			params.put("v3_leadtime", Arrays.asList(new String[]{"4"}));
		   
			//prior vendor
			params.put("priorvendorpriceunit", Arrays.asList(new String[]{"PCS"}));
			params.put("priorvendorfob", Arrays.asList(new String[]{"test"}));
			params.put("priorvendorlistprice", Arrays.asList(new String[]{"45.44"}));
			params.put("priorvendordiscpct1", Arrays.asList(new String[]{"0.435"}));
			params.put("priorvendorroundaccuracy", Arrays.asList(new String[]{"2"}));
			params.put("priorvendornetprice", Arrays.asList(new String[]{"45"}));
			params.put("priorvendormarkuppct", Arrays.asList(new String[]{"2"}));
			params.put("priorvendorfreightratecwt", Arrays.asList(new String[]{"45"}));
			params.put("priorvendorlandedbasecost", Arrays.asList(new String[]{"0.3"}));
			    
			//--------- units ----------//
			params.put("stdunit", Arrays.asList(new String[]{"PCS"}));
			params.put("stdratio", Arrays.asList(new String[]{"1.0"}));
			params.put("ordratio", Arrays.asList(new String[]{"1.0"}));
			
			params.put("baseunit", Arrays.asList(new String[]{"PCS"}));
			params.put("baseisstdsell", Arrays.asList(new String[]{"Y"}));
			params.put("baseisstdord", Arrays.asList(new String[]{"N"}));
			params.put("baseisfractqty", Arrays.asList(new String[]{"N"}));
			params.put("baseispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("basevolperunit", Arrays.asList(new String[]{"765"}));
			params.put("basewgtperunit", Arrays.asList(new String[]{"115"}));
			params.put("baseupc", Arrays.asList(new String[]{"878775"}));
			
			params.put("unit1unit", Arrays.asList(new String[]{"CTN"}));
			params.put("unit1ratio", Arrays.asList(new String[]{"10"}));
			params.put("unit1isstdsell", Arrays.asList(new String[]{"N"}));
			params.put("unit1isstdord", Arrays.asList(new String[]{"Y"}));
			params.put("unit1isfractqty", Arrays.asList(new String[]{"N"}));
			params.put("unit1ispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("unit1upc", Arrays.asList(new String[]{"8787750"}));
			params.put("unit1wgtperunit", Arrays.asList(new String[]{"110"}));
			
			params.put("unit2unit", Arrays.asList(new String[]{"PLA"}));
			params.put("unit2ratio", Arrays.asList(new String[]{"100"}));
			params.put("unit2isstdsell", Arrays.asList(new String[]{"N"}));
			params.put("unit2isstdord", Arrays.asList(new String[]{"Y"}));
			params.put("unit2isfractqty", Arrays.asList(new String[]{"N"}));
			params.put("unit2ispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("unit2upc", Arrays.asList(new String[]{"878775"}));
			params.put("unit2wgtperunit", Arrays.asList(new String[]{"120"}));
			
			params.put("unit3unit", Arrays.asList(new String[]{"PLA"}));
			params.put("unit3ratio", Arrays.asList(new String[]{"10"}));
			params.put("unit3isstdsell", Arrays.asList(new String[]{"N"}));
			params.put("unit3isstdord", Arrays.asList(new String[]{"Y"}));
			params.put("unit3isfractqty", Arrays.asList(new String[]{"N"}));
			params.put("unit3ispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("unit3upc", Arrays.asList(new String[]{"878775"}));
			params.put("unit3wgtperunit", Arrays.asList(new String[]{"130"}));
			
			params.put("unit4unit", Arrays.asList(new String[]{"CTN"}));
			params.put("unit4ratio", Arrays.asList(new String[]{"100"}));
			params.put("unit4isstdsell", Arrays.asList(new String[]{"N"}));
			params.put("unit4isstdord", Arrays.asList(new String[]{"Y"}));
			params.put("unit4isfractqty", Arrays.asList(new String[]{"N"}));
			params.put("unit4ispackunit", Arrays.asList(new String[]{"Y"}));
			params.put("unit4upc", Arrays.asList(new String[]{"878775"}));
			params.put("unit4wgtperunit", Arrays.asList(new String[]{"140"}));
			
		
		// Icon
			params.put("originCountry", Arrays.asList(new String[]{"China"}));
			params.put("exteriorProduct", Arrays.asList(new String[]{"True"}));
			params.put("adaAccessibility", Arrays.asList(new String[]{"N"}));
			params.put("throughColor", Arrays.asList(new String[]{"True"}));
			params.put("colorBody", Arrays.asList(new String[]{"Y"}));
			params.put("inkJet", Arrays.asList(new String[]{"N"}));
			params.put("glazed", Arrays.asList(new String[]{"Y"}));
			params.put("unglazed", Arrays.asList(new String[]{"Y"}));
			params.put("rectifiedEdge", Arrays.asList(new String[]{"N"}));
			params.put("chiseledEdge", Arrays.asList(new String[]{"True"}));
			params.put("versaillesPattern", Arrays.asList(new String[]{"Y"}));
			params.put("recycled", Arrays.asList(new String[]{"N"}));
			params.put("postRecycled", Arrays.asList(new String[]{"Y"}));
			params.put("preRecycled", Arrays.asList(new String[]{"True"}));
			params.put("icon_greenFriendly", Arrays.asList(new String[]{"Y"}));
			params.put("icon_leadPoint", Arrays.asList(new String[]{"True"}));
			params.put("recycled", Arrays.asList(new String[]{"True"}));
			params.put("coefficientOfFriction", Arrays.asList(new String[]{"Y"}));
		
			//notes
			params.put("poNote", Arrays.asList(new String[]{"test Po note"}));
			params.put("buyerNote", Arrays.asList(new String[]{"test buyer note"}));
			params.put("invoiceNote", Arrays.asList(new String[]{"test invoice note"}));
			params.put("internalNote", Arrays.asList(new String[]{"test internal note"}));
		
			//params.put("designLook", Arrays.asList(new String[]{"Wood"}));
			//System.out.println("Transaact is completed = " + TransactionAspectSupport.currentTransactionStatus().isCompleted());
			//System.out.println("Transaact sRollbackOnly = " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		
	       // Items result = rpcDao.createItem(userType, userCode, params);
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	   
	 
	 @Test
	 public void testUpdateItem(){
			System.out.println("test update Item ...");
			Item item = null;
			try {
				item = (Item)productService.getProductById(testItemId);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("An existing Item retrieved");
			System.out.println("Item = "+ item.toString());
		
			item.setItemdesc1("update-test");
			try {
	            productService.updateProduct(item);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			//item = (Item)productService.getProductById(item.getItemcd());
			System.out.println("Retrieved the upated Item");
			System.out.println("Item = "+ item.toString());
			
		}
		 
     @Test
	 public void testUpdateItemByJsonObject() throws Exception {
	        System.out.println("testUpdateItemByJsonBoject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcd", testItemId);
	        params.put("itemdesc1", "test desc");
	        /*params.put("price", Arrays.asList(new String[]{"20.2"}));
	        params.put("color", Arrays.asList(new String[]{testColor}));
			params.put("category", Arrays.asList(new String[]{testCategory}));
			params.put("waterAbsorption", Arrays.asList(new String[]{"0.05"}));
			params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			params.put("productManager", Arrays.asList(new String[]{"Manager"}));
			params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
			
			params.put("length", Arrays.asList(new String[]{"4"}));
			params.put("width", Arrays.asList(new String[]{"4"}));
			params.put("origin", Arrays.asList(new String[]{"China"}));
		    params.put("grade", Arrays.asList(new String[]{"Second"}));
			params.put("status", Arrays.asList(new String[]{"Good"}));
		    */		
	        // Items result = rpcDao.createItem(userType, userCode, params);
	        productService.updateProduct(params);
	        System.out.println("testUpdateItem Done");
	 }
     
	 @Test
	 public void testUpdateItemByMultivaluedMap() throws Exception {
	        System.out.println("testUpdateItem: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcd", Arrays.asList(new String[]{"TEST"}));
	        params.put("icons", Arrays.asList(new String[]{"YYNYNYNYNYNYNYNYNNYN"}));
	        params.put("description", Arrays.asList(new String[]{"a test desc"}));
	        params.put("itemdesc2", Arrays.asList(new String[]{"update desc"}));
	        /*params.put("price", Arrays.asList(new String[]{"20.2"}));
	        params.put("color", Arrays.asList(new String[]{testColor}));
			params.put("category", Arrays.asList(new String[]{testCategory}));
			params.put("waterAbsorption", Arrays.asList(new String[]{"0.05"}));
			params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			params.put("productManager", Arrays.asList(new String[]{"Manager"}));
			params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
			
			params.put("length", Arrays.asList(new String[]{"4"}));
			params.put("width", Arrays.asList(new String[]{"4"}));
			params.put("origin", Arrays.asList(new String[]{"China"}));
		    params.put("grade", Arrays.asList(new String[]{"Second"}));
			params.put("status", Arrays.asList(new String[]{"Good"}));
		    */		
	        // Items result = rpcDao.createItem(userType, userCode, params);
	        productService.updateProduct(params);
	        System.out.println("testUpdateItem Done");
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	
}
