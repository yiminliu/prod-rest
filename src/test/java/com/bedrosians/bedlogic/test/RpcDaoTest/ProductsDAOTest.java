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
	
	
}
