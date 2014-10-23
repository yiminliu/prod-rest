package com.bedrosians.bedlogic.test.product;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.domain.ims.enums.Grade;
import com.bedrosians.bedlogic.domain.ims.enums.MpsCode;
import com.bedrosians.bedlogic.domain.ims.enums.Status;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemListWrapper;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ImsServiceLookupByMultiValuedMapTest {
		
	//@Mock
	//ItemDao ItemDaoMock;
	
	//@InjectMocks
	//imsServiceImpl imsService;
	
	@Autowired
	private ImsService imsService;
	@Autowired
	//private IndexUtil indexUtil;
	
	
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
    static private String testSeriesname = null;
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
	    testSeriesName = "builder";
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
//@Ignore
	public void testGetAllactiveAndShownOnWebItemsByLucene(){
		List<Ims> items = null;
		try{
		   //indexUtil.initializeIndex();
		   items = imsService.getActiveAndShownOnWebsiteItems();
		}
		catch(Exception e){
				e.printStackTrace();
		}   
		assertTrue("should not be null", items != null && !items.isEmpty());
		
		System.out.println(items.size() + " items retrieved.");
	}
	
	@Test
	public void testGetItemByItemCode(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		Ims item = null;
		try{
		 //  imsService.initializeIndex();
		   item = imsService.getItemByItemCode(testItemId);
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
        List<Ims> items = null;
        try{
           items = imsService.getItems(params);
        }
        catch(Exception e) {
           e.printStackTrace();
        }   
        assertTrue("should not be null", items != null && !items.isEmpty());
        String jsonStr = null;
        System.out.println("number of Items retrieved: "+items.size());
        System.out.println("searching item code  = TRCD");
        for(Ims item : items){
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
        List<Ims> items = null;
        assertNull(items);
        try{
           items = imsService.getItems(params);
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
	public void testGetAllActiveItems(){
		
		System.out.println("test if the Item is returned by searching its ID...");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		List<Ims> items = null;
        try{
           items = imsService.getItems(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number of items   = " + items.size());
	 
        for(Ims item : items){
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
		List<Ims> items = null;
        try{
           items = imsService.getItems(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number od items   = " + items.size());
	    
        for(Ims item : items){
	    	//System.out.println("item code   = " + item.getItemcode());
        	assertEquals("N", item.getInactivecode());
        	assertEquals("Y", item.getShowonwebsite());
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
		params.put("maxResults", Arrays.asList(new String[]{"5"}));
		List<Ims> items = null;
        try{
           items = imsService.getItems(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number od items   = " + items.size());
	    assertTrue(items.size() <= 500);
        for(Ims item : items){
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
		List<Ims> items = null;
        try{
           items = imsService.getItems(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number of items   = " + items.size());
	    Ims itemI, itemJ = null;
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
		List<Ims> items = null;
        try{
           items = imsService.getItems(params);
        }
		catch(Exception e){
			e.printStackTrace();
		}
        assertTrue("should not be null", items != null && !items.isEmpty());
        System.out.println("Number od items   = " + items.size());
	    
        for(Ims item : items){
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
	        List<ItemWrapper> items = imsService.getWrappedItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	       // for(Ims prod : items){
	       	//    System.out.printf("item code = %s, and description = %s;  ", prod.getItemcode(), prod.getItemdesc().getFulldesc());
	       	//    System.out.println();
	        //   	assertTrue(prod.getItemdesc().getFulldesc().contains(testFullDescription));
	        //}
	       // ListWraper lWraper = new ListWraper();
	        //lWraper.setList(items);
	        Products result = new Products(new ItemListWrapper(items));
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
	        params.put("itemcategory", Arrays.asList(new String[]{testCategory}));
	        List<Ims> items = imsService.getItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
	       	   // System.out.printf("item code = %s, and Category = %s. ", prod.getItemcode(), prod.getItemcategory());
	           	assertEquals("The category should be " + testCategory, testCategory, prod.getItemcategory().trim());
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
    public void testGetItemsByMultiCategories() throws Exception {
	        System.out.println("testGetItemByCategory: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("itemcategory", Arrays.asList(new String[]{testCategory, testCategory2}));
	        List<Ims> items = imsService.getItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
		    
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());    
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params); 
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
	       	    System.out.printf("item code = %s, and matStyle = %s. ", prod.getItemcode(), prod.getMaterial().getMaterialstyle());
	       	    System.out.println();
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
	        List<Ims> items = imsService.getItems(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params); 
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        for(Ims prod : items){
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
    public void testGetItemBySeriesName() throws Exception {
	        System.out.println("testGetItemBySeriesName: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        //params.put(" exactMatch", Arrays.asList(new String[]{"true"}));
	        params.put("seriesname", Arrays.asList(new String[]{testSeriesName}));
	        List<Ims> items = imsService.getItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
	       	    //System.out.printf("item code = %s and SeriesName = %s. ", prod.getItemcd(), prod.getSeriesname());
	           	assertEquals(testSeriesName.toUpperCase(), prod.getSeries().getSeriesname().trim().toUpperCase());
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
	        List<Ims> items = imsService.getItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
	       	    //System.out.printf("item code = %s and SeriesName = %s. ", prod.getItemcode(), prod.getSeries().getSeriesname());
	           	assertTrue(testSeriesName.equalsIgnoreCase(prod.getSeries().getSeriesname().trim()) || testSeriesName2.equalsIgnoreCase(prod.getSeries().getSeriesname().trim()) || testSeriesName3.equalsIgnoreCase(prod.getSeries().getSeriesname().trim()));
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
		    List<Ims> items = imsService.getItems(params);
		    assertTrue("should not be null", items != null && !items.isEmpty());
		    String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
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
		    List<Ims> items = imsService.getItems(params);
		    assertTrue("should not be null", items != null && !items.isEmpty());
		    String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
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
		    List<Ims> items = imsService.getItems(params);
		    assertTrue("should not be null", items != null && !items.isEmpty());
		    String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
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
		
	        List<Ims> items = imsService.getItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
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
	        List<Ims> items = imsService.getItems(params);
	        assertTrue("should not be null", items != null && !items.isEmpty());
	        String jsonStr = null;
	        System.out.println("number of Items retrieved: "+ items == null? 0 :items.size());
	        for(Ims prod : items){
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
		public void testNothing(){
	}
}
