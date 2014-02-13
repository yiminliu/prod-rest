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

import com.bedrosians.bedlogic.bedDataAccessDAO.PricesDAO;
import com.bedrosians.bedlogic.exception.BedDAOUnAuthorizedException;
import com.bedrosians.bedlogic.models.Prices;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class PricesDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    PricesDAO rpcDao;
    
    static private String userType = "keymark";
    static private String userCode = "CHRISNU";
    static private String userTypeGuest = "guest";
    static private String userCodeGuest = "";
    static private String itemcode = "GLSECP5858-ET";//"AECBUB217NR";
    static private String customercode = "26815";
    static private String branchCode = "125";
    static private String locationCode = "125"; 
    static private String unit ="";
	
    static{
    	System.out.println("******* PricesDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetPricesByItemCode() throws Exception {	
		System.out.println("testGetPricesByItemCode: " + itemcode);
		Prices result = rpcDao.readPrices(userType, userCode, itemcode, "", "", "", unit);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetPricesByItemCodeAndCustCode() throws Exception {	
		System.out.printf("testGetPricesByItemCodeAndCustCode: %s, %s", itemcode, customercode);
	    System.out.println();
		Prices result = rpcDao.readPrices(userType, userCode, itemcode, customercode, "", "", unit);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetPricesByItemCodeAndCustCodeAndBranchCode() throws Exception {	
		System.out.printf("testGetPricesByItemCodeAndCustCodeAndBranchCode: %s, %s, %s",  itemcode, customercode, branchCode);
	    System.out.println();
		Prices result = rpcDao.readPrices(userType, userCode, itemcode, customercode, branchCode, "", unit);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetPricesByItemCodeAndCustCodeAndBranchCodeAndLocationCode() throws Exception {	
		System.out.printf("testGetPricesByItemCodeAndCustCodeAndBranchCodeAndLocationCode: %s, %s, %s, %s", itemcode, customercode, branchCode, locationCode);
	    System.out.println();
		Prices result = rpcDao.readPrices(userType, userCode, itemcode, customercode, branchCode, locationCode, unit);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	
	@Test(expected=BedDAOUnAuthorizedException.class)  
	public void testGetPricesWithGuestUserType() throws Exception {	
		System.out.println("testGetPricesWithGuestUserType: BedDAOUnAuthorizedException is expected");
		Prices result = rpcDao.readPrices(userTypeGuest, userCodeGuest, itemcode, customercode, branchCode, locationCode, unit);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
}
