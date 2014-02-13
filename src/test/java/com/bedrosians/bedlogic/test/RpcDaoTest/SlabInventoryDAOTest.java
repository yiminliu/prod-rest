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

import com.bedrosians.bedlogic.bedDataAccessDAO.CostsDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.PricesDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.SlabCostsDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.SlabInventoryDAO;
import com.bedrosians.bedlogic.exception.BedDAOUnAuthorizedException;
import com.bedrosians.bedlogic.models.Costs;
import com.bedrosians.bedlogic.models.Prices;
import com.bedrosians.bedlogic.models.SlabCosts;
import com.bedrosians.bedlogic.models.SlabInventory;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class SlabInventoryDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    SlabInventoryDAO rpcDao;
    
    static private String userType = "keymark";
    static private String userCode = "JACKH";
    static private String userTypeGuest = "guest";
    static private String itemCode = "MARAZDOMARSLAB";
    static private String locationCode = "109"; 
   
    static{
    	System.out.println("******* SlabInventoryDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetSlabInventoryByItemCode() throws Exception {	
		System.out.println("testGetSlabInventoryByItemCode: " + itemCode);
		SlabInventory result = rpcDao.getSlabInventory(userType, userCode, itemCode, "", "");
	    assertNotNull(result);
	    System.out.println("SlabInventory = " + result.toJSONString());
	}
	
	@Test
	public void testGetSlabInventoryByItemCodeAndLocationCode() throws Exception {	
		System.out.printf("testGetSlabInventoryByItemCodeAndLocationCode: %s, %s", itemCode, locationCode);
	    System.out.println();
		SlabInventory result = rpcDao.getSlabInventory(userType, userCode, itemCode, locationCode, "");
	    assertNotNull(result);
	    System.out.println("SlabInventory = " + result.toJSONString());
	}
	
	
	@Test(expected=BedDAOUnAuthorizedException.class)  
	public void testGetSlabCostsWithGuestUserType() throws Exception {	
	    System.out.println("testGetProductsByItemCode: BedDAOUnAuthorizedException is expected");
	 	SlabInventory result = rpcDao.getSlabInventory(userTypeGuest, userCode, itemCode, locationCode, "");
	    //assertNotNull(result);
	    System.out.println("SlabInventory = " + result.toJSONString());
	}
	
}
