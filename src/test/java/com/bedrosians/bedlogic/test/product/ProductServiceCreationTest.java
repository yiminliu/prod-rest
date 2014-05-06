package com.bedrosians.bedlogic.test.product;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.bedrosians.bedlogic.util.JsonWrapper.ListWrapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/Bedlogic-test-context.xml")
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ProductServiceCreationTest {
		
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
	private static String testColorCategory2 = null;
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
		testItemId = "Test"; 
		testItemId2 = "AECBUB217NR"; 
		testDescription = "13X13 Breccia Beige";
	    testFullDescription = "Field Tile 13x13 Breccia Beige";
	    //testColor = "Beige";
	    testColor = "White";
	    testColorCategory = "CLEAR";
	    testColorCategory2 = "RED";
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
	 //@Transactional
	 public void testCreateItemWithItem(){
			System.out.println("test create Item ...");
			Item item = new Item();
			item.setItemcode(testItemId + new Random().nextInt(4000));
			item.getItemdesc().setItemdesc1("test2");
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
	 public void testCreateItemWithBasicInfoByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(10000));
	        params.put("itemdesc1", "This is a test");
	        params.put("color", testColor);
			params.put("category", testCategory);
			params.put("seriesname", testSeriesName);
			params.put("type", "test");
			params.put("itemtypecd", "F");
		    params.put("origin", testOrigin);
			params.put("inactivecd", "N");
			params.put("showonwebsite", "Y");
			params.put("taxClass", "Tax");

			String id = productService.createProduct(params);
		       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
		        
		    Item item = productService.getProductById(id);
		    
		    assertEquals(testColor, item.getSeries().getSeriescolor());
		    assertEquals(testCategory, item.getItemcategory());
		    assertEquals(testSeriesName, item.getSeries().getSeriesname());
		    assertEquals(testOrigin, item.getCountryorigin());
		    //assertEquals(String."T", item.getTaxclass());
		    
		    System.out.println("Item = " + item);	
	
    }
				
	 @Test
	 public void testCreateItemWithColorHueByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithColorHueByJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(4000));
	        params.put("itemdesc1", "This is a test");
	        params.put("color", testColor);
			params.put("category", testCategory);
			params.put("seriesname", "test");
			params.put("type", "test");
			params.put("itemtypecd", "F");
		    params.put("origin", "China");
			params.put("inactivecd", "N");
	        params.put("lottype", "S");
			params.put("seriesname", "test");
			params.put("subtype", "test");
			params.put("itemtypecd", "F");
		    params.put("pricegroup", "1");
			params.put("productline", "Test");
			params.put("directship", "N");
			params.put("dropdate", "01/01/2015");
			params.put("itemgroupnbr", "12");
	    	
			//-----color hue ----//
			params.put("colorhue", "RED");
			
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        
	        Item item = productService.getProductById(id);
			System.out.println("Item = " + item);
			
			//for(ColorHue hue : item.getNewColorHueSystem()){
			//	System.out.println("hue.getColorDescription() = " + hue.getColorDescription());
			//	assertEquals("red".toUpperCase(), hue.getColorDescription().getDescription().toUpperCase());
			//}
	        //System.out.println("Test Result = " + result.toJSONString());
	 }
	   
	 @Test
	 public void testCreateItemWithMultipleColorHueByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithColorHueByJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(3000));
	        params.put("itemdesc1", "This is a test");
	        params.put("color", testColor);
			params.put("category", testCategory);
			params.put("seriesname", "test");
			params.put("type", "test");
			params.put("itemtypecd", "F");
		    params.put("origin", "China");
			params.put("inactivecd", "N");
	        params.put("lottype", "S");
			params.put("seriesname", "test");
			params.put("subtype", "test");
			params.put("itemtypecd", "F");
		    params.put("pricegroup", "1");
			params.put("productline", "Test");
			params.put("directship", "N");
			params.put("dropdate", "01/01/2015");
			params.put("itemgroupnbr", "12");
	    	
			//-----color hue ----//
			params.put("colorhue", testColorCategory+":"+testColorCategory2);
			//params.put("colorhue", "blue");
			
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        
	        Item item = productService.getProductById(id);
			System.out.println("Item = " + item);
			
			//for(ColorHue hue : item.getNewColorHueSystem()){
			//	//System.out.println("hue.getColorDescription() = " + hue.getColorDescription());
			//	assertTrue(hue.getColorDescription().getDescription().equalsIgnoreCase(testColorCategory) || hue.getColorDescription().getDescription().equalsIgnoreCase(testColorCategory2));
			//}
	        //System.out.println("Test Result = " + result.toJSONString());
   }
	   
	 
	 @Test
	 public void testCreateItemWithTreeJsonString() throws Exception {
	    System.out.println("testCreateItemWithJson: ");
	    String jString = "{ \"itemcode\" : \"Test188\", "
				+ "\"itemdesc1\" : \"a test desc\", "
				+ "\"color\" : \"Red\",  "
				+ "\"category\" : \"Tool\",  "
				+ "\"seriesname\": \"test\", "
				+ "\"origin\": \"USA\", "
				+ "\"type\" : \"Test\",  "
				+ "\"category\" : \"Tool\",  "
				//+ "\"seriesName\": \"test\", "
				+ "\"countryorigin\": \"USA\", "
				+ "\"length\" : 14,  "
				+ "\"width\" : 4,  "
				+ "\"thickness\": \"4 3/4\", "
				+ "\"nominallength\": 14, "
				+ "\"nominalidth\" : 4,  "
				+ "\"nominalThickness\" : \"3.4\",  "
				+ "\"sizeunits\": \"E\", "
				+ "\"thicknessunit\": \"E\", "
				+ "\"materialtype\" : \"Ceramic\", " 
				+ "\"materialcategory\" : \"Allied\", " 
				//+ "\"materialStyle\": " + "\"" + MaterialStyle.Chair_Rail.getDescription() + "\", " 
				+ "\"materialfeature\": \"Test\", "
				//+ "\"materialClassCode\" : " + "\"" + MaterialClass.Ceramic_Tile_Sourced.getDescription() + "\", " 
						
				//+ "\"taxClass\" : " + "\"" + TaxClass.N.getDescription()+ "\"" +", "
				+ "\"price\" :{\"sellprice\" : 22.4,"
				              +"\"listprice\" : 22.6,"
				              +"\"futuresell\" : 22.4," 
				              +"\"priorsellprice\" : 22.4,"
				              +"\"tempprice\" : 22.4,"
				              //+"\"tempdatefrom\" : new Date()).toString()," 
				             // +"\"tempdatethru\" : new Date()).toString()," 
				              +"\"sellpricemarginpct\" : 22.4,"
				              +"\"sellpriceroundaccuracy\" : 4"
				+ "}, "
				+ "\"imsNewFeature\" :{\"grade\" : " + "\"" + Grade.First.getDescription() + "\"" 
				                  + ", \"status\" : " + "\"" + Status.Better.getDescription() + "\"" 
				                  + ", \"designStyle\" : " + "\"" + DesignStyle.Asian.getDescription() + "\"" 
				                  + ", \"designLook\" : " + "\"" + DesignLook.Wood.getDescription() + "\"" 
				                  ////+ ", \"body\" : " + "\"" + Body.Double_Loaded.getDescription() + "\"" 
				                  + ", \"edge\" : " + "\"" + Edge.Chiseled.getDescription() + "\"" 
				                  + ", \"surfaceApplication\" : " + "\"" + SurfaceApplication.Silk.getDescription() + "\"" 
				                  + ", \"surfaceType\" : " + "\"" + SurfaceType.Abrasive.getDescription() + "\"" 
				                  + ", \"surfaceFinish\" : " + "\"" + SurfaceFinish.Antiquated.getDescription() + "\"" 
				                  + ", \"warranty\" :  3" 
				                  + ", \"recommendedGroutJointMin\" : 1" 
				                  + ", \"recommendedGroutJointMax\" : 2" 
				                   +" }, "
				+ "\"purchasers\" :{ \"purchaser\" : \"Joe\", \"purchaser2\" : \"Tom\" }, "
				+ "\"testSpecification\" :{\"waterabsorption\" : 0.05,"
	                         +"\"scratchresistance\" : 0.05," 
	                         + "\"frostresistance\": \"Y\", "
	                         + "\"chemicalresistance\": \"Y\", "
	                         +"\"peiabrasion\" : 0.05,"
	                         +"\"scofwet\" : 0.05,"
	                         +"\"breakingstrength\" : 5,"
	                         +"\"scofdry\" : 0.05"
	               +" }, "
				+ "\"applications\" :{ \"residential\" : \"FR:WR:CR\", \"commercial\" : \"FC:WC:CC\" } "
			
				+ "}";   
	    
	    JSONObject jObj = new JSONObject(jString);
	    System.out.println("Input = " + jObj.toString());
	    String id = productService.createProduct(jObj);
	    
	    String jsonStr = null;
	     
	    Products result = new Products(id);
	    try{
	        jsonStr = result.toJSONStringWithJackson();
	    }
	    catch(Exception e){
	      	e.printStackTrace();
	    }
	    System.out.println("items   = " + jsonStr);
	 }
	 
	 
	 @Test
	 public void testCreateItemWithMatrialByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(10000));
	        params.put("itemdesc1", "This is a test");
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
			
			//----- material info -------//
			params.put("mattype", "test");
			params.put("matcategory", "test");
			params.put("matstyle", "test");
			params.put("mfeature", "test");
			params.put("materialclassCd", "test");
		
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        Item item = productService.getProductById(id);
	        
	        assertEquals("test", item.getMaterial().getMaterialtype());
	        assertEquals("test", item.getMaterial().getMaterialclass());
	        assertEquals("test", item.getMaterial().getMaterialstyle());
	        assertEquals("test", item.getMaterial().getMaterialcategory());
	 }
	 
	 @Test
	 public void testCreateItemWithDimensionsByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(3500));
	        params.put("itemdesc1", "This is a test");
	        params.put("color", testColor);
			params.put("category", testCategory);
			params.put("seriesname", "test");
			params.put("type", "test");
			params.put("itemtypecd", "F");
		    params.put("origin", "China");
			params.put("inactivecd", "N");
			params.put("showonwebsite", "Y");
			params.put("taxClass", "Tax");
	   	
			//----- dimension ------//
			params.put("length", "14");
			params.put("width", "4");
			params.put("thickness", "2 1/2");
			params.put("nmLength", "14.0");
			params.put("nmWidth", "4.0");
			params.put("nmThickness", "2.5");
			params.put("sizeunits", "E");
			params.put("thicknessunit", "E");
		
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
            Item item = productService.getProductById(id);
	        
	        assertEquals("14", item.getDimensions().getLength());
	        assertEquals("4", item.getDimensions().getWidth());
	        assertEquals("2 1/2", item.getDimensions().getThickness());
	        assertEquals("E", item.getDimensions().getSizeunits());
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	 }
	 
	 @Test
	 public void testCreateItemWithUnitJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(6000));
	        params.put("itemdesc1", "This is a test");
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

	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	 }
	 
	 
	 @Test
	 public void testCreateItemWithVendorsByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + + new Random().nextInt(4300));
	        params.put("itemdesc1", "This is a test");
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

	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	 }
	 
	 @Test
	 public void testCreateItemWithTestsJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(3500));
	        params.put("itemdesc1", "This is a test");
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
			
			//------- test info -------//
			params.put("waterAbsorption", "0.05");
			params.put("scratchResistance", "0.05");
			params.put("frostResistance", "Y");
			params.put("chemicalResistance", "Y");
			params.put("peiAbrasion", "0.05");
			params.put("scofWet", "0.05");
			params.put("breakingStrength", "5");
			params.put("scofDry", "0.05");
	
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	 }
	 
	 
	 @Test
	 public void testCreateItemWithJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(4600));
	        params.put("itemdesc1", "This is a test");
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
		
		/*	//notes
			params.put("poNote", "test Po note");
			params.put("buyerNote", "test buyer note");
			params.put("invoiceNote", "test invoice note");
			params.put("internalNote", "test internal note");
		*/
			//params.put("designLook", "Wood");
			//System.out.println("Transaact is completed = " + TransactionAspectSupport.currentTransactionStatus().isCompleted());
			//System.out.println("Transaact sRollbackOnly = " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		
	        // String id = rpcDao.createItem("guest", "", params);
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	 }
	 
	 
	 @Test
	 public void testCreateItemWithPriceingByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(3600));
	        params.put("itemdesc1", "This is a test");
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
	    	
			//------- price info --------//	
			params.put("price", "5.5");
			params.put("futurePrice", "4.0");
			params.put("priorPrice", "5.5");
			params.put("tempPrice", "4.0");
			params.put("tempdatefrom", new Date().toString());
			//params.put("tempdatethru", (new Date()).toString());
			params.put("pricemarginpct", "0.435");
			params.put("priceroundaccuracy", "2");
			
			//-----cost ----//
			params.put("cost", "12.0");
			params.put("priorCost", "21.0");
			params.put("priorCost1", "12.0");
			params.put("futureCost", "12.0");
			params.put("futureCost1", "12.0");
			params.put("poIncludeinVendorCost", "12.0");
			//params.put("costRangePct", "12.0");
			params.put("nonstockcostpct", "12.0");
			
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	 
	 @Test
	 public void testCreateItemWithUsageByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(6000));
	        params.put("itemdesc1", "This is a test");
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
		
			params.put("residential", "FR:WR:CR");
			params.put("commercial", "FC:WC:CC");
		
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	 
	 @Test
	 public void testCreateItemWithNotesByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(6000));
	        params.put("itemdesc1", "This is a test");
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
	    
			params.put("poNote", "test Po note");
			params.put("buyerNote", "test buyer note");
			params.put("invoiceNote", "test invoice note");
			params.put("internalNote", "test internal note");
			
	        // String id = rpcDao.createItem("guest", "", params);
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	 
	 @Test
	 public void testCreateItemWithIconByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(6000));
	        params.put("itemdesc1", "This is a test");
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
		
		/*	//notes
			params.put("poNote", "test Po note");
			params.put("buyerNote", "test buyer note");
			params.put("invoiceNote", "test invoice note");
			params.put("internalNote", "test internal note");
		*/
			//params.put("designLook", "Wood");
			//System.out.println("Transaact is completed = " + TransactionAspectSupport.currentTransactionStatus().isCompleted());
			//System.out.println("Transaact sRollbackOnly = " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		
	        // String id = rpcDao.createItem("guest", "", params);
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	 
	 @Test
	 public void testCreateItemWithNewFeatureByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode",testItemId + new Random().nextInt(6000));
	        params.put("itemdesc1", "This is a test");
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
			
		
		/*	//notes
			params.put("poNote", "test Po note");
			params.put("buyerNote", "test buyer note");
			params.put("invoiceNote", "test invoice note");
			params.put("internalNote", "test internal note");
		*/
			//params.put("designLook", "Wood");
			//System.out.println("Transaact is completed = " + TransactionAspectSupport.currentTransactionStatus().isCompleted());
			//System.out.println("Transaact sRollbackOnly = " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		
	        // String id = rpcDao.createItem("guest", "", params);
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	 
	   
	 //No note record for the item in the ims_note table
	 @Test
	 public void testCreateItemWithJsonObjectWIthoutNote() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode","Test290");
	        params.put("itemdesc1", "This is a test");
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
			
	     /*	// Icon
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
		*/
			//notes
			params.put("poNote", "test Po note");
			params.put("buyerNote", "test buyer note");
			params.put("invoiceNote", "test invoice note");
			params.put("internalNote", "test internal note");
		
			//params.put("designLook", "Wood");
			//System.out.println("Transaact is completed = " + TransactionAspectSupport.currentTransactionStatus().isCompleted());
			//System.out.println("Transaact sRollbackOnly = " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		
	        // String id = rpcDao.createItem("guest", "", params);
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	 
	 //No record for the item in the ims_note table
	 @Test
	 public void testCreateItemWithJsonObjectWithoutIcon() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        JSONObject params = new JSONObject();
	        params.put("itemcode","Test" + new Random().nextInt(5000));
	        params.put("itemdesc1", "This is a test");
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
			
	     /*	// Icon
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
		*/
			//notes
			params.put("poNote", "test Po note");
			params.put("buyerNote", "test buyer note");
			params.put("invoiceNote", "test invoice note");
			params.put("internalNote", "test internal note");
		
			//params.put("designLook", "Wood");
			//System.out.println("Transaact is completed = " + TransactionAspectSupport.currentTransactionStatus().isCompleted());
			//System.out.println("Transaact sRollbackOnly = " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
		
	        // String id = rpcDao.createItem("guest", "", params);
	        String id = productService.createProduct(params);
	       
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        
	        //Item item = productService.getProductById("Test1");
			//System.out.println("Item = " + item);
	        //System.out.println("Test Result = " + result.toJSONString());
	    }
	  
	
	 
	 @Test
	 public void testCreateItemWithMultivalues() throws Exception {
	        System.out.println("testCreateItem: ");
	        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
	        params.put("itemcode", Arrays.asList(new String[]{testItemId + new Random().nextInt(6000)}));
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
	/*		params.put("originCountry", Arrays.asList(new String[]{"China"}));
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
	*/	
			//notes
	/*		params.put("poNote", Arrays.asList(new String[]{"test Po note"}));
			params.put("buyerNote", Arrays.asList(new String[]{"test buyer note"}));
			params.put("invoiceNote", Arrays.asList(new String[]{"test invoice note"}));
			params.put("internalNote", Arrays.asList(new String[]{"test internal note"}));
		*/
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
	   
}
