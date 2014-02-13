package com.bedrosians.bedlogic.test.RpcDaoTest;


	import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

	






















import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import junit.framework.TestCase;
import net.minidev.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.bedrosians.bedlogic.bedDataAccessDAO.ProductSeriesDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.ProductsDAO;
import com.bedrosians.bedlogic.models.ProductSeries;
import com.bedrosians.bedlogic.models.Products;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class ProductSeriesDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    ProductSeriesDAO rpcDao;
    
    static private String userName = "guest";
    static private String password = "";
    
	
    static{
    	System.out.println("******* ProductSeriesDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetProductSeries() throws Exception {
		System.out.println("testGetProductSeries");
	    ProductSeries result = rpcDao.getProductSeries(userName, password);
	    assertNotNull(result);
	    System.out.println("ProductSeries = " + result.toJSONString());
	}
	
}
