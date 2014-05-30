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
import com.bedrosians.bedlogic.domain.item.enums.DBOperation;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.product.ProductService;
import com.bedrosians.bedlogic.service.security.KeymarkUcUserSecurityService;
import com.bedrosians.bedlogic.util.JsonWrapper.ListWrapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/Bedlogic-test-context.xml")
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ProductServiceDeletionTest {
		
	//@Mock
	//ItemDao ItemDaoMock;
	
	//@InjectMocks
	//productServiceImpl productService;
	
	@Autowired
	ProductService productService;
	@Autowired
	KeymarkUcUserSecurityService keymarkUcUserSecurityService;
	
	
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
 	
	@Before
	public void setup(){
		testItemId = "Test"; 
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
	public void testKeymarkUcUserSecurityService(){
		String userType = "keymark";
		String userCode = "JBED";
		//DBOperation productOperation
		try{
		   keymarkUcUserSecurityService.doSecurityCheck(userType, userCode, DBOperation.SEARCH);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	 @Test
	 public void testDeleteItemById()throws Exception {
	    productService.deleteProductById("NEWITEMCODE");
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
