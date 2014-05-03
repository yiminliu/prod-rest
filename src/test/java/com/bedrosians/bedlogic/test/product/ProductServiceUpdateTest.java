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

import com.bedrosians.bedlogic.domain.item.ColorHue;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.enums.DesignLook;
import com.bedrosians.bedlogic.domain.item.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.item.enums.Edge;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.product.ProductService;
import com.bedrosians.bedlogic.util.ListWrapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/Bedlogic-test-context.xml")
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ProductServiceUpdateTest {
		
	//@Mock
	//ItemDao ItemDaoMock;
	
	//@InjectMocks
	//productServiceImpl productService;
	
	@Autowired
	ProductService productService;
	
	
	private static String testItemId = null;
	private static String testItemId2 = null;
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
    static private String testIcons = "YYNYNYNYNYNYNYNYNNYN";
 	
	@Before
	public void setup(){
		testItemId = "TCRPET459N"; 
		testItemId2 = "AECBUB217NR"; 
		testDescription = "13X13 Breccia Beige";
	    testFullDescription = "Field Tile 13x13 Breccia Beige";
	    //testColor = "Beige";
	    testColor = "White";
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
		
			item.getItemdesc().setItemdesc1("update-test");
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
	 public void testUpdateItemBasicInfoByJsonObject() throws Exception {
	        System.out.println("testUpdateItemByJsonBoject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcd", testItemId);
	        params.put("itemdesc1", testDescription);
	        params.put("price", "20.2");
	        params.put("color", testColor);
			params.put("category", testCategory);	
			params.put("length", "4");
			params.put("width", "4");
			params.put("origin", testOrigin);
            productService.updateProduct(params);
            
            Item item = productService.getProductById(testItemId);
	        
  	        assertEquals(testItemId, item.getItemcode());
  	        assertEquals(testDescription, item.getItemdesc().getItemdesc1());
  	        assertEquals(testOrigin, item.getCountryorigin());
	        assertEquals(testColor, item.getColor());
	        
	        //assertEquals(20.2, item.getPrice().getSellprice());
  	        assertEquals("4", item.getLength());
  	        assertEquals("4", item.getWidth());
	           
	        System.out.println("testUpdateItem Done");
	 }
     
     @Test
	 public void testUpdateItemColorHueByJsonObject() throws Exception {
	        System.out.println("testUpdateItemByJsonBoject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode", "TEST1274");
	        params.put("itemdesc1", "test desc");
	        params.put("colorhue", "White");
	        productService.updateProduct(params);
	        System.out.println("testUpdateItem Done");
	        
	        //Item item = productService.getProductById("TEST1274");
	       // assertEquals(testItemId, item.getItemcode());
	        //assertEquals(testIcons, item.getIconsystem());
	 }
     
     @Test
	 public void testUpdateItemIconsByJsonObject() throws Exception {
	        System.out.println("testUpdateItemByJsonBoject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcd", testItemId);
	        params.put("itemdesc1", "test desc");
	        params.put("icons", testIcons);
	        productService.updateProduct(params);
	        System.out.println("testUpdateItem Done");
	        
	        Item item = productService.getProductById(testItemId);
	        assertEquals(testItemId, item.getItemcode());
	        assertEquals(testIcons, item.getIconsystem());
	 }
       
     @Test
	 public void testUpdateItemTestSpecByJsonObject() {
	        System.out.println("testUpdateItemByJsonBoject: ");
	        JSONObject params = new JSONObject();
	        Item item =  null;
	        try{
	           params.put("itemcd", testItemId);
	   		   params.put("waterAbsorption", "0.05");
			
	           productService.updateProduct(params);
	           System.out.println("testUpdateItem Done");
	           item = productService.getProductById(testItemId);
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	        assertEquals(testItemId, item.getItemcode());
	        assertEquals("0.05", String.valueOf(item.getTestSpecification().getWaterabsorption()));
	 }
     
     @Test
	 public void testUpdateItemApplicatonsByJsonObject() throws Exception {
	        System.out.println("testUpdateItemByJsonBoject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcd", testItemId);
	   		params.put("residential", Arrays.asList(new String[]{"FR:WR:CR"}));
			
	        productService.updateProduct(params);
	        System.out.println("testUpdateItem Done");
	 }
     
     @Test
	 public void testUpdateItemPurchaserByJsonObject() throws Exception {
	        System.out.println("testUpdateItemByJsonBoject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcd", testItemId);
	   	
			params.put("productManager", Arrays.asList(new String[]{"Manager"}));
			params.put("buyer", Arrays.asList(new String[]{"TestBuyer"}));
			
			
	        productService.updateProduct(params);
	        System.out.println("testUpdateItem Done");
	 }
     
     
     @Test
	 public void testUpdateItemNewFeatureByJsonObject() throws Exception {
	        System.out.println("testUpdateItemByJsonBoject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcd", testItemId);
	        params.put("itemdesc1", "test desc");
	        //params.put("icons", Arrays.asList(new String[]{"YYNYNYNYNYNYNYNYNNYN"}));
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
	 public void testUpdateItemBasicInfoByMultiVluedMap() throws Exception {
	        System.out.println("testUpdateItemByMultiVluedMap: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcd", Arrays.asList(new String[]{testItemId}));
	        params.put("itemdesc1", Arrays.asList(new String[]{testDescription}));
	        params.put("price", Arrays.asList(new String[]{"20.2"}));
	        params.put("color", Arrays.asList(new String[]{testColor}));
			params.put("category", Arrays.asList(new String[]{testCategory}));	
			params.put("length", Arrays.asList(new String[]{"4"}));
			params.put("width", Arrays.asList(new String[]{"4"}));
			params.put("origin", Arrays.asList(new String[]{testOrigin}));
            productService.updateProduct(params);
            
            Item item = productService.getProductById(testItemId);
	        
  	        assertEquals(testItemId, item.getItemcode());
  	        assertEquals(testDescription, item.getItemdesc().getItemdesc1());
  	        assertEquals(testOrigin, item.getCountryorigin());
	        assertEquals(testColor, item.getColor());
	        
	        //assertEquals(20.2, item.getPrice().getSellprice());
  	        assertEquals("4", item.getLength());
  	        assertEquals("4", item.getWidth());
	           
	        System.out.println("testUpdateItem Done");
	 }
     
     @Test
	 public void testUpdateItemIconsByMultiVluedMap() throws Exception {
    	 MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcd", Arrays.asList(new String[]{testItemId}));
	        params.put("icons", Arrays.asList(new String[]{testIcons}));
	        productService.updateProduct(params);
	        System.out.println("testUpdateItem Done");
	        
	        Item item = productService.getProductById(testItemId);
	        
	        assertEquals(testItemId, item.getItemcode());
	        assertEquals(testIcons, item.getIconsystem());
	        
	 }
     
     
	 @Test
	 public void testUpdateItemByMultivaluedMap() throws Exception {
	        System.out.println("testUpdateItem: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcd", Arrays.asList(new String[]{testItemId}));
	        //params.put("icons", Arrays.asList(new String[]{"YYNYNYNYNYNYNYNYNNYN"}));
	        params.put("itemdesc1", Arrays.asList(new String[]{"a test desc"}));
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
	
	 @Test(expected = BedDAOException.class)
	 public void testUpdateItemByJsonObjectWithFalkItemCode() throws Exception {
	        System.out.println("testUpdateItemByJsonBoject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcd", "XYZXYZ");
	        params.put("itemdesc1", "test desc");
	        params.put("icons", Arrays.asList(new String[]{"YYNYNYNYNYNYNYNYNNYN"}));
	   
	        productService.updateProduct(params);
	        System.out.println("testUpdateItem Done");
	 }
	 
	 @Test
	 public void testDeleteItemById()throws Exception {
	    productService.deleteProductById("TEST5");
	     System.out.println("testUpdateItem Done");
	        
	 }
	 
	 @Test
	 public void testDeleteItem()throws Exception {
		 MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	     params.put("itemcd", Arrays.asList(new String[]{"TEST9"})); 
	     productService.deleteProduct(params);
	     System.out.println("testUpdateItem Done");
	        
	 }
}
