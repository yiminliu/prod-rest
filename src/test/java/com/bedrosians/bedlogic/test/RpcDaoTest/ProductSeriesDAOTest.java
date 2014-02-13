package com.bedrosians.bedlogic.test.RpcDaoTest;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.bedrosians.bedlogic.bedDataAccessDAO.ProductSeriesDAO;
import com.bedrosians.bedlogic.models.ProductSeries;



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
	    ProductSeries result = rpcDao.readProductSeries(userName, password);
	    assertNotNull(result);
	    System.out.println("ProductSeries = " + result.toJSONString());
	}
	
}
