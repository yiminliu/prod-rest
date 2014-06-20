package com.bedrosians.bedlogic.test.product;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.ItemVendor;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.product.ProductService;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.JsonWrapper.ListWrapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/Bedlogic-test-context.xml")
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ProductServiceLookupByMultiValuedMapTest {
		
	//@Mock
	//ItemDao ItemDaoMock;
	
	//@InjectMocks
	//productServiceImpl productService;
	
	@Autowired
	private ProductService productService;
	
	
	private static String testItemId = null;
	private static String testItemId2 = null;
	private static String testNewItemId = null;
	private static String testDescription1 = null;
	private static String testFullDescription = null;
	private static String testFullDescription2 = null;
	private static String testColor = null;
	private static String testColorCategory = null;
	private static String testColorHue = null;
	private static String testColorHue2 = null;
	private static String testColorHue3 = null;
	private static String testColorHue4 = null;
	private static String testColorHue5 = null;
    private static String testSeriesName = null;
    private static String testSeriesName2 = null;
    private static String testSeriesName3 = null;
    private static String testItemTypeCode = null;
    private static String testCategory = null;
    private static String testCategory2 = null;
    private static String testMaterialCategory = null;
    private static String testMaterialStyle = null;
    private static String testMaterialClass = null;
    private static String testMaterialFeature = null;
    private static String testMaterialCategory2 = null;
    private static String testMaterialStyle2 = null;
    private static String testMaterialClass2 = null;
    private static String testMaterialFeature2 = null;
    private static String testMaterialType = null;
    private static String testMaterialType2 = null;
    private static String testOrigin = null;
    private static String testOrigin2 = null;
    static private String testItem3 = "AECBUB217NR";
    static private String testSeriesname = "builder";
    static private String testVendorId = "285500";
    static private String testPurchaser = "LANA";

    static private long startTime = 0l;
	static private long totalTime = 0l;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		testItemId = "TCRPET459N"; 
		testItemId2 = "AECBUB217NR"; 
		testDescription1 = "13X13 Breccia Beige";
	    testFullDescription = "Diamonds Are a Girls Best Friend";
	    testFullDescription2 = "test";
	    //testColor = "Beige";
	    testColor = "White";
	    testColorCategory = "CLEAR";
	    testColorHue = "ALMOND";
	    testColorHue2 = "RED";
	    testColorHue3 = "GREEN";
	    testColorHue4 = "YELLOW";
	    testColorHue5 = "BEIGE";
	    testSeriesName = "Sky";
	    testSeriesName2 = "Copper Silk";
	    testSeriesName3 = "Samoa";
	    testItemTypeCode = "#";
	    testCategory = "BRECCIA";
	    testCategory2 = "RUNWAY";
	    testMaterialCategory = "Tool";
	    testMaterialCategory2 = "Deco";
	    testMaterialFeature = "LE";
	    testMaterialFeature2 = "AB";
	    testMaterialStyle = "FL";
	    testMaterialStyle2 = "SFCR";
	    testMaterialClass ="DECOR";
	    testMaterialClass2 ="FRT";
	    testMaterialType ="Slate";
	    testMaterialType2 ="Brick";
	    testOrigin = "USA";
	    testOrigin2 = "Italy";
	    testNewItemId = "TEST1";
	   
	    startTime = System.currentTimeMillis();
	}
	
	@AfterClass
	public static void tearDown( ){
		totalTime = System.currentTimeMillis() - startTime;
		//System.out.println("Total execution time = " + totalTime + " sec");
	}
	
	@Test
	public void testGetAactiveAndShownOnWebProducts(){
		List<Item> items = null;
		try{
		  // productService.initializeIndex();
		   items = productService.getActiveAndShownOnWebsiteProducts();
		}
		catch(Exception e){
				e.printStackTrace();
		}   
		assertTrue("should not be null", items != null && !items.isEmpty());
		
		System.out.println(items.size() + " items retrieved.");
	}
	
	@Test
	public void testGetItemById(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		Item item = null;
		try{
		 //  productService.initializeIndex();
		   item = productService.getProductById(testItemId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		String jsonStr = null;
        Products result = new Products(new ItemWrapper(item));
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    System.out.println("items   = " + jsonStr);
		assertNotNull("should not be null", item);
		assertEquals("Item id should be " + testItemId, testItemId, item.getItemcode());
	}
	
	@Test
	public void testGetItemByIdPatternMatch(){	
		System.out.println("test if the Item is returned by searching its ID...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
        params.put("itemcode", Arrays.asList(new String[]{"tcrd"}));
       // params.put("exactMatch", Arrays.asList(new String[]{"true"}));
        List<Item> items = null;
        try{
           items = productService.getProducts(params);
        }
        catch(Exception e) {
           e.printStackTrace();
        }   
        assertTrue("should not be null", items != null && !items.isEmpty());
        String jsonStr = null;
        System.out.println("number of Items retrieved: "+items.size());
        System.out.println("searching item code  = TRCD");
        for(Item item : items){
	    	System.out.println("item code   = " + item.getItemcode());
	    	assertTrue(item.getItemcode().startsWith("TCRD"));
	    }
        Products result = new Products(items); 
        try{
            jsonStr = result.toJSONStringWithJackson();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
       // System.out.println("items   = " + jsonStr);
    }
	
	@Test
	public void testGetItemByIdWithEaxctMatch(){	
		System.out.println("test if the Item is returned by searching its ID...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
        params.put("itemcode", Arrays.asList(new String[]{"TCRD"}));
        params.put("exactMatch", Arrays.asList(new String[]{"True"}));
        List<Item> items = null;
        assertNull(items);
        try{
           items = productService.getProducts(params);
        }
        catch(Exception e) {
           e.printStackTrace();
        }   
        String jsonStr = null;
        System.out.println("number of Items retrieved: "+items.size());
        System.out.println("searching item code  = TRCD");
        assertTrue(items.isEmpty());
	 
        Products result = new Products(items); 
        try{
            jsonStr = result.toJSONStringWithJackson();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
       // System.out.println("items   = " + jsonStr);
    }
	
	@Test
	public void testGetItemByMultipleIds(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.put("itemcode", Arrays.asList(new String[]{testItemId, testItemId2}));
		List<Item> items = null;
        try{
           items = productService.getProducts(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number od items   = " + items.size());
        
        for(Item item : items){
	    	System.out.println("item code   = " + item.getItemcode());
	    	assertTrue(testItemId.equalsIgnoreCase(item.getItemcode()) || testItemId2.equalsIgnoreCase(item.getItemcode()));
	    }
	    
		String jsonStr = null;
        Products result = new Products(items);
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    
	}
	
	@Test
	public void testGetAllActiveItems(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		List<Item> items = null;
        try{
           items = productService.getProducts(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number of items   = " + items.size());
	 
        for(Item item : items){
	    	//System.out.println("item code   = " + item.getItemcode());
        	assertEquals("N", item.getInactivecode());
	    } 
		String jsonStr = null;
        Products result = new Products(items);
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    
	   // System.out.println("items   = " + jsonStr);
	}
	
	@Test
	public void testGetAllActiveAndShowOnWebsiteItems(){
		
		System.out.println("testGetAllActiveAndShowOnWebsiteItems...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		params.put("showonwebsite", Arrays.asList(new String[]{"Y"}));
		List<Item> items = null;
        try{
           items = productService.getProducts(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number od items   = " + items.size());
	    
        for(Item item : items){
	    	//System.out.println("item code   = " + item.getItemcode());
        	assertEquals("N", item.getInactivecode());
        	assertEquals("Y", Character.toString(item.getShowonwebsite()));
	    }
	    
		String jsonStr = null;
        Products result = new Products(items);
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }    
	   // System.out.println("items   = " + jsonStr);
	}
	
	
	@Test
	public void testGetActiveItemsWithMaxResult(){
		
		System.out.println("test if the Item is returned by ActiveItemsWithMaxResultSetting...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		params.put("maxResults", Arrays.asList(new String[]{"500"}));
		List<Item> items = null;
        try{
           items = productService.getProducts(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number od items   = " + items.size());
	    assertTrue(items.size() <= 500);
        for(Item item : items){
	    	//System.out.println("item code   = " + item.getItemcode());
        	assertEquals("N", item.getInactivecode());
	    }
       
		String jsonStr = null;
        Products result = new Products(items);
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    
	   // System.out.println("items   = " + jsonStr);
	}
	
	@Test
	public void testDistinctItems(){
		
		System.out.println("test if the Item Are Distinct...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		//params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		params.put("maxResults", Arrays.asList(new String[]{"500"}));
		List<Item> items = null;
        try{
           items = productService.getProducts(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number of items   = " + items.size());
	    Item itemI, itemJ = null;
        for(int i = 0; i < items.size() - 1; i++){
        	itemI = items.get(i);
        	for(int j = i+1; j < items.size(); j++){
          		itemJ = items.get(j);
        		//System.out.println("itemI code / ItemJ code = " + itemI.getItemcode() + "/" + itemJ.getItemcode());
          		if( itemI.getItemcode().equals(itemJ.getItemcode())){
          			try{
          		       //System.out.println("itemI = " + new Products(itemI).toJSONStringWithJackson());
          		       //System.out.println("itemJ = " + new Products(itemJ).toJSONStringWithJackson());
          			}
          		    catch(Exception e){
          		      	e.printStackTrace();
          		    }
          		}   
        		assertNotEquals(itemI.getItemcode(), itemJ.getItemcode());
	        }
        } 
		String jsonStr = null;
        Products result = new Products(items);
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    
	   // System.out.println("items   = " + jsonStr);
	}
	
	@Test
	public void testGetAllInActiveItems(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.put("inactivecode", Arrays.asList(new String[]{"Y", "D"}));
		List<Item> items = null;
        try{
           items = productService.getProducts(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number od items   = " + items.size());
	    
        for(Item item : items){
	    	//System.out.println("item code   = " + item.getItemcode());
        	assertTrue(item.getInactivecode().equalsIgnoreCase("Y") || item.getInactivecode().equalsIgnoreCase("D"));
	    }
	    
		String jsonStr = null;
        Products result = new Products(items);
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    
	   // System.out.println("items   = " + jsonStr);
	}
	
	
	@Test
    public void testGetItemByDesciption() throws Exception {
	        System.out.println("testGetItemByDescription: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("fulldesc", Arrays.asList(new String[]{testFullDescription}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and description = %s;  ", prod.getItemcode(), prod.getItemdesc().getFulldesc());
	       	    System.out.println();
	           	assertTrue(prod.getItemdesc().getFulldesc().contains(testFullDescription));
	        }
	       // ListWraper lWraper = new ListWraper();
	        //lWraper.setList(items);
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByMultiDesciptions() throws Exception {
	        System.out.println("testGetItemByDescription: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("fulldesc", Arrays.asList(new String[]{testFullDescription, testFullDescription2}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and description = %s;  ", prod.getItemcode(), prod.getItemdesc().getFulldesc());
	       	    System.out.println();
	           	assertTrue(prod.getItemdesc().getFulldesc().contains(testFullDescription) || prod.getItemdesc().getFulldesc().contains(testFullDescription2));
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByItemDesc1() throws Exception {
	        System.out.println("testGetItemBytestDescription1: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("itemdesc1", Arrays.asList(new String[]{testDescription1}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and color = %s;  ", prod.getItemcode(), prod.getItemdesc().getItemdesc1());
	       	    System.out.println();
	           	assertTrue(prod.getItemdesc().getItemdesc1().contains(testDescription1));
	        }
	       // ListWraper lWraper = new ListWraper();
	        //lWraper.setList(items);
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	        
	}
	
	@Test
    public void testGetItemByColor() throws Exception {
	        System.out.println("testGetItemByColor: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("color", Arrays.asList(new String[]{"Red"}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	       // System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and color = %s;  ", prod.getItemcode(), prod.getSeries().getSeriescolor());
	       	    System.out.println();
	           	assertTrue("Red".equalsIgnoreCase(prod.getSeries().getSeriescolor().trim()) || prod.getSeries().getSeriescolor().contains("Red") || prod.getSeries().getSeriescolor().contains("RED"));
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        System.out.println("items   = " + jsonStr);    
	}
	
	@Test
    public void testGetItemByMultiColors() throws Exception {
	        System.out.println("testGetItemByColor: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("color", Arrays.asList(new String[]{"Red", "White"}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and color = %s;  ", prod.getItemcode(), prod.getSeries().getSeriescolor());
	       	    System.out.println();
	           	assertTrue("Red".equalsIgnoreCase(prod.getSeries().getSeriescolor().trim()) || "White".equalsIgnoreCase(prod.getSeries().getSeriescolor().trim()));
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);        
	}
	
	@Test
    public void testGetItemByColorCategory() throws Exception {
	        System.out.println("testGetItemByColorCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("colorcategory", Arrays.asList(new String[]{testColorCategory}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	//       	    System.out.printf("item code = %s, and colorcategory = %s;  ", prod.getItemcode(), prod.getColorhues());
	//       	    System.out.println();
	           	//assertTrue(prod.getColorhues().trim().startsWith(testColorCategory));
	       	    assertTrue(prod.getColorhues().contains(testColorCategory));
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByMultipleColorCategory() throws Exception {
	        System.out.println("testGetItemByColorCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("colorcategory", Arrays.asList(new String[]{testColorCategory, "Red"}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	 //      	    System.out.printf("item code = %s, and colorcategory = %s;  ", prod.getItemcode(), prod.getColorhues());
	       	    System.out.println();
	           	assertTrue(prod.getColorhues().contains(testColorCategory) || prod.getColorhues().contains("RED"));
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByOrigin() throws Exception {
	        System.out.println("testGetItemByOrigin: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("countryorigin", Arrays.asList(new String[]{testOrigin}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and origin = %s;  ", prod.getItemcode(), prod.getCountryorigin());
	       	    System.out.println();
	           	assertTrue(testOrigin.equalsIgnoreCase(prod.getCountryorigin().trim()));
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByMultiOrigins() throws Exception {
	        System.out.println("testGetItemByColor: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("countryorigin", Arrays.asList(new String[]{testOrigin, testOrigin2}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and origin = %s;  ", prod.getItemcode(), prod.getCountryorigin());
	       	    System.out.println();
	           	assertTrue(testOrigin.equalsIgnoreCase(prod.getCountryorigin().trim()) || testOrigin2.equalsIgnoreCase(prod.getCountryorigin().trim()));
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	       // System.out.println("items   = " + jsonStr);
	}
	
	
	@Test
    public void testGetItemByCategory() throws Exception {
	        System.out.println("testGetItemByCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("itemcategory", Arrays.asList(new String[]{testCategory}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    System.out.printf("item code = %s, and Category = %s. ", prod.getItemcode(), prod.getItemcategory());
	           	assertEquals("The category should be " + testCategory, testCategory, prod.getItemcategory().trim());
	        }
            Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	       // System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemsByMultiCategories() throws Exception {
	        System.out.println("testGetItemByCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("itemcategory", Arrays.asList(new String[]{testCategory, testCategory2}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
		    
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and Category = %s. ", prod.getItemcode(), prod.getItemcategory());
	           	assertTrue(testCategory.equalsIgnoreCase(prod.getItemcategory().trim()) || testCategory2.equalsIgnoreCase(prod.getItemcategory().trim()));
	        }
            Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	       // System.out.println("items   = " + jsonStr);
	}
	
	@Test
    public void testGetItemByMaterialCategory() throws Exception {
	        System.out.println("testGetItemByMaterialCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("materialCategory", Arrays.asList(new String[]{testMaterialCategory}));
	        List<Item> items = productService.getProducts(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and matCategory = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialcategory());
	       	    //System.out.println();
	           	assertEquals(testMaterialCategory, prod.getMaterial().getMaterialcategory().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	      // System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMultiMaterialCategory() throws Exception {
	        System.out.println("testGetItemByMaterialCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("materialCategory", Arrays.asList(new String[]{testMaterialCategory, testMaterialCategory2}));
	        List<Item> items = productService.getProducts(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());    
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and matCategory = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialcategory());
	       	    //System.out.println();
	           	assertTrue(testMaterialCategory.equalsIgnoreCase(prod.getMaterial().getMaterialcategory().trim()) || testMaterialCategory2.equalsIgnoreCase(prod.getMaterial().getMaterialcategory().trim()));
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	       //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialStyle() throws Exception {
	        System.out.println("testGetItemBytestMaterialStyle: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("materialstyle", Arrays.asList(new String[]{testMaterialStyle}));
	        List<Item> items = productService.getProducts(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and matStyle = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialstyle());
	       	    //System.out.println();
	           	assertEquals(testMaterialStyle, prod.getMaterial().getMaterialstyle().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialStyle2() throws Exception {
	        System.out.println("testGetItemBytestMaterialStyle: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("materialstyle", Arrays.asList(new String[]{testMaterialStyle2}));
	        List<Item> items = productService.getProducts(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and matStyle = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialstyle());
	       	    //System.out.println();
	           	assertEquals(testMaterialStyle2, prod.getMaterial().getMaterialstyle().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMultiMaterialStyles() throws Exception {
	        System.out.println("testGetItemBytestMaterialStyle: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("materialstyle", Arrays.asList(new String[]{testMaterialStyle, testMaterialStyle2}));
	        List<Item> items = productService.getProducts(params); 
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and matStyle = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialstyle());
	       	    //System.out.println();
	           	assertTrue(prod.getMaterial().getMaterialstyle().trim().contains(testMaterialStyle) || prod.getMaterial().getMaterialstyle().trim().contains(testMaterialStyle2));
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialClass() throws Exception {
	        System.out.println("testGetItemBytestMaterialClass: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("materialclass", Arrays.asList(new String[]{testMaterialClass}));
	        List<Item> items = productService.getProducts(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and material class = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialclass());
	       	    //System.out.println();
	           	assertEquals(testMaterialClass, prod.getMaterial().getMaterialclass().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialClass2() throws Exception {
	        System.out.println("testGetItemBytestMaterialClass: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("materialclass", Arrays.asList(new String[]{testMaterialClass2}));
	        List<Item> items = productService.getProducts(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and material class = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialclass());
	       	    System.out.println();
	           	assertEquals(testMaterialClass2, prod.getMaterial().getMaterialclass().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMultipleMaterialClasses() throws Exception {
	        System.out.println("testGetItemBytestMaterialClass: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("materialclass", Arrays.asList(new String[]{testMaterialClass, testMaterialClass2}));
	        //params.put("maxResults", Arrays.asList(new String[]{"2000"}));
	        List<Item> items = productService.getProducts(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and material class = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialclass());
	       	    //System.out.println();
	           	assertTrue(testMaterialClass.equals(prod.getMaterial().getMaterialclass().trim()) || testMaterialClass2.equals(prod.getMaterial().getMaterialclass().trim()));
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialFeature() throws Exception {
	        System.out.println("testGetItemBytestMaterialFeature: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("materialfeature", Arrays.asList(new String[]{testMaterialFeature}));
	        List<Item> items = productService.getProducts(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and material feaature = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialfeature());
	       	    //System.out.println();
	           	assertEquals(testMaterialFeature, prod.getMaterial().getMaterialfeature().trim());
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialFeature2() throws Exception {
	        System.out.println("testGetItemBytestMaterialFeature: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("materialfeature", Arrays.asList(new String[]{testMaterialFeature2}));
	        List<Item> items = productService.getProducts(params); 
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertNotNull(items);
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and material feaature = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialfeature());
	       	    //System.out.println();
	           	assertEquals(testMaterialFeature2, prod.getMaterial().getMaterialfeature().trim());
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMultipleMaterialFeatures() throws Exception {
	        System.out.println("testGetItemBytestMaterialFeature: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("materialfeature", Arrays.asList(new String[]{testMaterialFeature, testMaterialFeature2}));
	        List<Item> items = productService.getProducts(params); 
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertNotNull(items);
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and material feaature = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialfeature());
	       	    System.out.println();
	           	assertTrue(testMaterialFeature.equals(prod.getMaterial().getMaterialfeature().trim()) || testMaterialFeature2.equals(prod.getMaterial().getMaterialfeature().trim()));
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialType() throws Exception {
	        System.out.println("testGetItemBytestMaterialType: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("materialtype", Arrays.asList(new String[]{testMaterialType}));
	        List<Item> items = productService.getProducts(params); 
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and material type = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialtype());
	       	    System.out.println();
	           	assertEquals(testMaterialType, prod.getMaterial().getMaterialtype().trim());
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMaterialType2() throws Exception {
	        System.out.println("testGetItemBytestMaterialType: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("materialtype", Arrays.asList(new String[]{testMaterialType2}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and material type = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialtype());
	       	    System.out.println();
	           	assertEquals(testMaterialType2, prod.getMaterial().getMaterialtype().trim());
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMultipleMaterialTypes() throws Exception {
	        System.out.println("testGetItemBytestMaterialType: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("materialtype", Arrays.asList(new String[]{testMaterialType, testMaterialType2}));
	        List<Item> items = productService.getProducts(params); 
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s, and material type = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialtype());
	       	    //System.out.println();
	           	assertTrue(testMaterialType.equals(prod.getMaterial().getMaterialtype().trim()) || testMaterialType2.equals(prod.getMaterial().getMaterialtype().trim()));
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemBySeriesName() throws Exception {
	        System.out.println("testGetItemBySeriesName: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("seriesname", Arrays.asList(new String[]{testSeriesName}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s and SeriesName = %s. ", prod.getItemcd(), prod.getSeriesname());
	           	assertEquals(testSeriesName, prod.getSeries().getSeriesname().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
    public void testGetItemByMultipleSeriesName() throws Exception {
	        System.out.println("testGetItemMultipleBySeriesName: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("seriesname", Arrays.asList(new String[]{testSeriesName, testSeriesName2, testSeriesName3,}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	       	    //System.out.printf("item code = %s and SeriesName = %s. ", prod.getItemcd(), prod.getSeriesname());
	           	assertTrue(testSeriesName.equals(prod.getSeries().getSeriesname().trim()) || testSeriesName2.equals(prod.getSeries().getSeriesname().trim()) || testSeriesName3.equals(prod.getSeries().getSeriesname().trim()));
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	 }
	
	@Test
	 public void testGetItemBySize() throws Exception {
	        System.out.println("testGetItemBySize: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("size", Arrays.asList(new String[]{"10X8"}));
		    List<Item> items = productService.getProducts(params);
		    assertTrue("should not be null", items != null && !items.isEmpty());
		    String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 //System.out.printf("itemcd = %s , length =  %1f and width = %1f . ", prod.getItemcode(), prod.getDimensions().getNominallength(), prod.getDimensions().getNominalwidth());
	        	 //System.out.println();
	        	 assertEquals(10, prod.getDimensions().getNominallength().intValue());
	        	 assertEquals(8, prod.getDimensions().getNominalwidth().intValue());
	         }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("resulting jsonStr:");
	        //System.out.println("items   = " + jsonStr);
	    }
	
	 @Test
	 public void testGetItemByMultipleSize() throws Exception {
	        System.out.println("testGetItemBySize: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("size", Arrays.asList(new String[]{"12X9", "10X8",  "4X8"}));
		    List<Item> items = productService.getProducts(params);
		    assertTrue("should not be null", items != null && !items.isEmpty());
		    String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 //System.out.printf("itemcd = %s , length =  %1f and width = %1f . ", prod.getItemcode(), prod.getDimensions().getNominallength(), prod.getDimensions().getNominalwidth());
	        	 //System.out.println();
	        	assertTrue(prod.getDimensions().getNominallength().equals(Float.valueOf("12.0")) || prod.getDimensions().getNominallength().equals(Float.valueOf("10.0")) || prod.getDimensions().getNominallength().equals(Float.valueOf("4.0")));
		        assertTrue(prod.getDimensions().getNominalwidth().equals(Float.valueOf("9.0")) || prod.getDimensions().getNominalwidth().equals(Float.valueOf("8.0")));
	        	//assertNotNull(testColor.toUpperCase(), prod.getColor());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("resulting jsonStr:");
	        //System.out.println("items   = " + jsonStr);
	    }
	 
	 @Test
	    public void testGetItemByLengthAndWidth() throws Exception {
	        System.out.println("testGetItemByLengthAndWidth: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("length", Arrays.asList(new String[]{"12"}));
	        params.put("width", Arrays.asList(new String[]{"9"}));
		    List<Item> items = productService.getProducts(params);
		    assertTrue("should not be null", items != null && !items.isEmpty());
		    String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 //System.out.println("itemcd:  " + prod.getItemcode() +"   " + " length X width = " +prod.getDimensions().getNominallength() + " X " + prod.getDimensions().getNominalwidth());
	        	 assertTrue(prod.getDimensions().getNominallength() <= 12);
	        	 assertTrue(prod.getDimensions().getNominalwidth() <= 9);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	       // System.out.println("items   = " + jsonStr);
	    }
	 
	 @Test
	 public void testGetItemByMinLength() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     	params.put("lengthmin", Arrays.asList(new String[]{"120"}));
		
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	            //System.out.println("length X width = " + prod.getDimensions().getNominallength() + " X " + prod.getDimensions().getNominalwidth());
	        	assertTrue(prod.getDimensions().getNominallength() >= 120.0);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	    }
	 
	 @Test
	 public void testGetItemByMaxLength() throws Exception {
	        System.out.println("testGetItemByMaxLength: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     	params.put("lengthmax", Arrays.asList(new String[]{"2"}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	//System.out.printf("itemcd = %s, length X width = %f, %f", prod.getItemcd(), prod.getNmLength(), prod.getNmWidth());
	        	assertTrue(prod.getDimensions().getNominallength() <= 2.0);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	       // System.out.println("items   = " + jsonStr);
	    }
	 
	 @Test
	    public void testGetItemByMaxLengthAndMaxWidth() throws Exception {
	        System.out.println("testGetItemByMaxLength: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     	params.put("lengthmax", Arrays.asList(new String[]{"2"}));
	     	params.put("widthmax", Arrays.asList(new String[]{"1"}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	// System.out.printf("itemcd = %s, length X width = %f, %f", prod.getItemcd(), prod.getNmLength(), prod.getNmWidth());
	        	assertTrue(prod.getDimensions().getNominallength() <= 2.0);
	        	assertTrue(prod.getDimensions().getNominalwidth() <= 1.0);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	       // System.out.println("items   = " + jsonStr);
	    }
	
	 @Test
	    public void testGetItemByMaxAndMinLength() throws Exception {
	        System.out.println("testGetItemByMaxLength: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     	params.put("lengthmax", Arrays.asList(new String[]{"2"}));
	     	params.put("lengthMin", Arrays.asList(new String[]{"1"}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	//System.out.printf("itemcd = %s, length X width = %f, %f", prod.getItemcode(), prod.getDimensions().getNominallength(), prod.getDimensions().getNominalwidth());
	        	//System.out.println();
	        	assertTrue(prod.getDimensions().getNominallength() <= 2.0);
	        	assertTrue(prod.getDimensions().getNominallength() >= 1.0);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	    }
	 
	
	 @Test
     public void testGetItemByMultiPrameters() throws Exception {
	        System.out.println("testGetItemByMultivaluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("colorhues", Arrays.asList(new String[]{testColorHue}));
			//params.put("category", Arrays.asList(new String[]{testCategory}));
		    params.put("materialtype", Arrays.asList(new String[]{testMaterialType}));
			//params.put("countryorigin", Arrays.asList(new String[]{testOrigin, testOrigin2}));
		    //params.put("grade", Arrays.asList(new String[]{"First"}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 System.out.println("item code = " + prod.getItemcode());
	        	 System.out.println("color = " + prod.getSeries().getSeriescolor());
	        	 System.out.println("category = " + prod.getItemcategory());
	        	 System.out.println("origin = " + prod.getCountryorigin());
	        	 
	        	 //assertEquals(testColorHue, prod.getColorhues().contains(o));
	        	 //assertEquals(testOrigin, prod.getCountryorigin().trim());
	        	 assertEquals(testMaterialType, prod.getMaterial().getMaterialtype().trim());
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	       // System.out.println("items   = " + jsonStr);
	    }
	
	 
	 @Test
	 public void testGetItemByGrade() throws Exception {
	        System.out.println("testGetItemBynewFeature: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       	params.put("grade", Arrays.asList(new String[]{"First"}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 //System.out.println("item = " + prod);
	        	 //System.out.println("item new feature = " + prod.getImsNewFeature());
	        	 assertEquals(prod.getImsNewFeature().getGrade(), Grade.First);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	  }
	 
	 @Test
	 public void testGetItemByStatus() throws Exception {
	        System.out.println("testGetItemBynewFeature: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	      	params.put("status", Arrays.asList(new String[]{"Good"}));
	        List<Item> items = productService.getProducts(params);
	        
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 //System.out.println("item = " + prod);
	        	 //System.out.println("item new feature = " + prod.getImsNewFeature());
	           	 assertEquals(prod.getImsNewFeature().getStatus(), Status.Good);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	  }
	 
	 @Test
	 public void testGetItemByMpsCode() throws Exception {
	        System.out.println("testGetItemBynewFeature: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	      	params.put("mpsCode", Arrays.asList(new String[]{"Active_Product"}));
	        List<Item> items = productService.getProducts(params);
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	        	 //System.out.println("item = " + prod);
	        	 System.out.println("item new feature getMpsCode() = " + prod.getImsNewFeature().getMpsCode());
	           	 assertEquals(prod.getImsNewFeature().getMpsCode(), MpsCode.Active_Product);
	        }
	        Products result = new Products(items);
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	  }
	 
	 @Test
	 public void testGetItemByColorHue() throws Exception {
	        System.out.println("testGetItemByColorHue: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       	params.put("colorhues", Arrays.asList(new String[]{testColorHue2}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue(items.size() > 0);
	        for(Item prod : items){
	        	//ColorHue colorHue = new ColorHue(testColorHue, prod);
	            assertTrue(prod.getColorhues().contains(testColorHue2));
	            assertTrue(prod.getColorcategory().contains(testColorHue2));
	       //    	for(ColorHue ch : prod.getColorhues()){
	            for(String ch : prod.getColorhues()){
	            	//System.out.println("item code = " + prod.getItemcode() + ". and color hue  = " + ch.getColorHue());
	       //     	assertTrue(prod.getColorhues().contains(ch));
	            	//if this is valid, it means that the migratted colorCategory data from ims table to the new ims_item_color is correct
	           	//assertTrue(prod.getColorcategory().contains(ch.getColorHue()));
	            	assertTrue(prod.getColorcategory().contains(ch));
	           	    //assertTrue(ch.getColorHue().contains(testColorHue));
		        	//assertTrue(ch.getColorHue().equalsIgnoreCase(testColorHue) || ch.getColorHue().contains(testColorHue.toLowerCase()));
	        	}
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	  
	        }
	       // System.out.println("items   = " + jsonStr);
	  }
	 
	 @Test
	 public void testGetItemByMultipleColorHues() throws Exception {
	        System.out.println("testGetItemByColorHue: ");      
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	    	params.put("inactivecd", Arrays.asList(new String[]{"N"}));
	       	params.put("colorhues", Arrays.asList(new String[]{testColorHue, testColorHue2, testColorHue3, testColorHue4, testColorHue5}));
	        List<Item> items = productService.getProducts(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Item prod : items){
	     //   	for(ColorHue ch : prod.getColorhues()){
	            	//System.out.println("item code = " + prod.getItemcode() + ". and color hue  = " + ch.getColorHue());
	       //     	assertTrue(prod.getColorhues().contains(ch));
	           	   // assertTrue(ch.getColorHue().contains(testColorHue) || ch.getColorHue().contains(testColorHue2) || ch.getColorHue().contains(testColorHue3) || ch.getColorHue().contains(testColorHue4) || ch.getColorHue().contains(testColorHue5));
		        	//assertTrue(ch.getColorHue().equalsIgnoreCase(testColorHue) || ch.getColorHue().contains(testColorHue.toLowerCase()));
	      //  	}
	        }
	        Products result = new Products(new ListWrapper(items));
	        try{
	            jsonStr = result.toJSONStringWithJackson();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        //System.out.println("items   = " + jsonStr);
	  }

	 @Test
	 public void testGetItemByPurchaser() throws Exception {
	    System.out.println("testGetItemByPurchaser: ");
	    MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	   	params.put("purchaser", Arrays.asList(new String[]{testPurchaser}));
	   	//params.put("status", Arrays.asList(new String[]{"Good"}));
        List<Item> items = productService.getProducts(params);
        assertTrue("should not be null", items != null && !items.isEmpty());
	    String jsonStr = null;
	    System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	    for(Item prod : items){
	      	 System.out.println("item = " + prod);
	      	 assertEquals(testPurchaser, prod.getPurchasers().getPurchaser());
	    }
	    Products result = new Products(new ListWrapper(items));
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    //System.out.println("items   = " + jsonStr);
	  }
	 
	 //test the equality between the vendor id in the new vendor table and the vendornb1/vendornbr2 in the ims table.
	 //if they match, it means the migratted vendor info from ims table to the new ims_vendors table valid
	 @Test
	 public void testGetItemByVendorId() throws Exception {
	        System.out.println("testGetItemByVendorId: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	       	params.put("vendorId", Arrays.asList(new String[]{testVendorId}));		
	        List<Item> items = productService.getProducts(params);
	       
	        System.out.println("number of Items retrieved: "+items.size());
	        for(Item prod : items){
	           	 //System.out.println("vendor = " + prod.getNewVendorSystem());
	        	 for( ItemVendor vendor: prod.getNewVendorSystem()){
	        		 System.out.printf("item code =%s, vendorId = %s, vendrnbr1 =%s ", prod.getItemcode(), prod.getVendors().getVendornbr1(), vendor.getVendorId());
	        	     assertTrue(prod.getNewVendorSystem().contains(vendor));
	        	     assertTrue(vendor.getVendorId().equals(prod.getVendors().getVendornbr1()) || vendor.getVendorId().equals(prod.getVendors().getVendornbr2())); 
	        	 }    
	        }
	       
            ObjectMapper mapper = new ObjectMapper();
            Writer strWriter = new StringWriter();
            mapper.writeValue(strWriter, items);
            String jsonStr = strWriter.toString();
            
            // Return json reponse
            //String     jsonStr = result.toJSONString();
	        
	        System.out.println("number of Items retrieved: "+items.size());
	       // System.out.println("Output json string = " + jsonStr);
	        
	    }
	 
		
		/* //@Test
		 public void testGetItemByNote() throws Exception {
		        System.out.println("testGetItemByNote: ");
		        String noteType = "additional";
		        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		       	params.put("noteType", Arrays.asList(new String[]{noteType}));
		        List<Item> items = productService.getProducts(params);
		        String jsonStr = null;
		        //System.out.println("size of notes = "+ items.get(0).getNewNoteSystem().size());
		       
		        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
		        for(Item prod : items){
		        	 //System.out.println("item = " + prod);
		        	for(Note note : prod.getNewNoteSystem()) {
		        		System.out.println("noteType = " + note.getNoteType());
		        	     //assertEquals(noteType, note.getNoteType());
		        	 }    
		        }
		        Products result = new Products(items);
		        try{
		            jsonStr = result.toJSONStringWithJackson();
		        }
		        catch(Exception e){
		        	e.printStackTrace();
		        }
		 //       System.out.println(jsonStr);
		  }
		 */
	
	 @Test
		public void testNothing(){
	}
}
