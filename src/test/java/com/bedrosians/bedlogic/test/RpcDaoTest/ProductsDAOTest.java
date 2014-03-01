package com.bedrosians.bedlogic.test.RpcDaoTest;

import java.util.Arrays;
import javax.ws.rs.core.MultivaluedMap;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.bedrosians.bedlogic.bedDataAccessDAO.ProductsDAO;
import com.bedrosians.bedlogic.models.Products;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class ProductsDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    ProductsDAO rpcDao;
    
    static private String userType = "guest";
    static private String userCode = "";
    static private String itemcode = "AECBUB217NR";
    static private String seriesname = "builder";
	
    static{
    	System.out.println("******* ProductsDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetProductsByItemCode() throws Exception {
		System.out.println("testGetProductsByItemCode: " + itemcode);
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
    	queryParams.put("itemcode", Arrays.asList(new String[]{itemcode}));
	    Products result = rpcDao.readProductsByQueryParams(userType, userCode, queryParams);
	    assertNotNull(result);
	    System.out.println("Products = " + result.toJSONString());
	}
	
	@Test
	public void testGetProductsBySeriesName() throws Exception {
		 System.out.println("testGetProductsBySeriesName: " + seriesname);
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
		queryParams.put("seriesname", Arrays.asList(new String[]{seriesname}));
		Products result = rpcDao.readProductsByQueryParams(userType, userCode, queryParams);
    	assertNotNull(result);
	    System.out.println("Products = " + result.toJSONString());
	}
	
	@Test
	public void testGetSimplifiedProducts() throws Exception {
		System.out.println("testGetSimplifiedProducts: ");
		Products result = rpcDao.readSimplifiedProducts(userType, userCode);
    	assertNotNull(result);
	    System.out.println("Products = " + result.toJSONString());
	}
	
	@Test
	public void testCreateProduct() throws Exception {
		System.out.println("testCreateProduct: ");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();;
    	params.put("inactivecd", Arrays.asList(new String[]{"Y"}));
		params.put("seriesname", Arrays.asList(new String[]{"test"}));
		params.put("itemcd", Arrays.asList(new String[]{"TEST"}));
	
	    Products result = rpcDao.createProduct(userType, userCode, params);
	    
	    assertNotNull(result);
	    System.out.println("Test Result = " + result.toJSONString());
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		System.out.println("testUpdateProduct: " + itemcode);
		MultivaluedMap<String,String> updateParams = new MultivaluedMapImpl();;
		MultivaluedMap<String,String> condition = new MultivaluedMapImpl();;
		updateParams.put("inactivecd", Arrays.asList(new String[]{"Y"}));
		updateParams.put("seriesname", Arrays.asList(new String[]{"test"}));
		updateParams.put("itemcd", Arrays.asList(new String[]{"AECBUB217NR"}));
		condition.put("itemcd", Arrays.asList(new String[]{"AECBUB217NR"}));
	    Products result = rpcDao.updateProduct(userType, userCode, updateParams, condition);
	    
	    assertNotNull(result);
	    System.out.println("Test Result = " + result.toJSONString());
	}
	
	
	
}
