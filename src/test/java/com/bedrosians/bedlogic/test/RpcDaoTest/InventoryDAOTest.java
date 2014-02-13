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

import com.bedrosians.bedlogic.bedDataAccessDAO.InventoryDAO;
import com.bedrosians.bedlogic.models.Inventory;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class InventoryDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    InventoryDAO rpcDao;
    
    static private String userType = "guest";
    static private String userCode = "";
    static private String itemCode = "AECBUB217NR";

	
    static{
    	System.out.println("******* InventoryDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetInventoryByItemCode() throws Exception {
		System.out.println("testGetInventoryByItemCode: " + itemCode);
	    Inventory result = rpcDao.getInventory(userType, userCode, itemCode, "");
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	
}
