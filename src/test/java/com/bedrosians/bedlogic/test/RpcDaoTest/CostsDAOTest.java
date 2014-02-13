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

import com.bedrosians.bedlogic.bedDataAccessDAO.CostsDAO;
import com.bedrosians.bedlogic.exception.BedDAOUnAuthorizedException;
import com.bedrosians.bedlogic.models.Costs;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class CostsDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    CostsDAO rpcDao;
    
    static private String userType = "keymark";
    static private String userCode = "JACKH";
    static private String userTypeGuest = "guest";
    static private String userCodeGuest = "";
    static private String itemcode = "GLSECP5858-ET";//"AECBUB217NR";
    static private String customercode = "26815";
    static private String branchCode = "125";
    static private String locationCode = "125"; 
    static private String unit ="";
	
    static{
    	System.out.println("******* CostsDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetCostsByItemCode() throws Exception {	
		System.out.println("testGetCostsByItemCode: " + itemcode);
	    Costs result = rpcDao.readCosts(userType, userCode, itemcode, "", unit);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetCostsByItemCodeAndLocationCode() throws Exception {	
	    System.out.printf("testGetCostsByItemCodeAndLocationCode: %s, %s", itemcode, locationCode);
	    System.out.println();
		Costs result = rpcDao.readCosts(userType, userCode, itemcode, locationCode, unit);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test(expected=BedDAOUnAuthorizedException.class)  
	public void testGetPricesWithGuestUserType() throws Exception {	
		System.out.println("testGetPricesWithGuestUserType: BedDAOUnAuthorizedException is expected");
	    Costs result = rpcDao.readCosts(userTypeGuest, userCodeGuest, itemcode, locationCode, unit);
	    assertNotNull(result);
	}
	
}
