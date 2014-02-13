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
import com.bedrosians.bedlogic.exception.BedDAOUnAuthorizedException;
import com.bedrosians.bedlogic.models.Costs;
import com.bedrosians.bedlogic.models.Prices;
import com.bedrosians.bedlogic.models.SlabCosts;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class SlabCostsDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    SlabCostsDAO rpcDao;
    
    static private String userType = "keymark";
    static private String userCode = "JACKH";
    static private String userTypeGuest = "guest";
    static private String userCodeGuest = "";
    static private String itemCode = "MARAZDOMARSLAB";//"AECBUB217NR";
    static private String customercode = "26815";
    static private String branchCode = "125";
    static private String freightrate = "0";
    static private String deliverycost = "0";
    		
    static private String  serialNumber ="178557";
    static private String locationCode = "109"; 
    static private String unit ="";
    
    private MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
	
    static{
    	System.out.println("******* SlabCostsDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetSlabCostsByItemCode() throws Exception {	
		System.out.println("testGetProductsByItemCode: " + itemCode);
		queryParams.put("freightrate", Arrays.asList(new String[]{freightrate}));
    	queryParams.put("deliverycost", Arrays.asList(new String[]{deliverycost}));
	    SlabCosts result = rpcDao.getSlabCosts(userType, userCode, itemCode, "", "", queryParams);
	    assertNotNull(result);
	    System.out.println("SlabCosts = " + result.toJSONString());
	}
	
	@Test
	public void testGetSlabCostsByItemCodeAndLocationCode() throws Exception {	
		System.out.printf("testGetPricesByItemCodeAndLocationCode: %s, %s", itemCode, locationCode);
	    System.out.println();
		queryParams.put("freightrate", Arrays.asList(new String[]{freightrate}));
    	queryParams.put("deliverycost", Arrays.asList(new String[]{deliverycost}));
	    SlabCosts result = rpcDao.getSlabCosts(userType, userCode, itemCode, locationCode, "", queryParams);
	    assertNotNull(result);
	    System.out.println("SlabCosts = " + result.toJSONString());
	}
	
	@Test
	public void testGetSlabCostsByItemCodeAndLocationCodeAndSerialNumber() throws Exception {	
		System.out.printf("testGetPricesByItemCodeAndLocationCodeAndSerialNumber: %s, %s, %s", itemCode, locationCode, serialNumber);
	    System.out.println();
	    queryParams.put("freightrate", Arrays.asList(new String[]{freightrate}));
    	queryParams.put("deliverycost", Arrays.asList(new String[]{deliverycost}));
	    SlabCosts result = rpcDao.getSlabCosts(userType, userCode, itemCode, locationCode, serialNumber, queryParams);
	    assertNotNull(result);
	    System.out.println("SlabCosts = " + result.toJSONString());
	}
	
	@Test
	public void testGetSlabCostsByItemCodeAndLocationCodeAndSerialNumberAndfreightrate() throws Exception {	
		System.out.printf("testGetPricesByItemCodeAndLocationCodeAndSerialNumberAndfreightrate: %s, %s, %s, %s", itemCode, locationCode, serialNumber, "100");
	    System.out.println();
		queryParams.put("freightrate", Arrays.asList(new String[]{"100"}));
    	queryParams.put("deliverycost", Arrays.asList(new String[]{deliverycost}));
	    SlabCosts result = rpcDao.getSlabCosts(userType, userCode, itemCode, locationCode, serialNumber, queryParams);
	    assertNotNull(result);
	    System.out.println("SlabCosts = " + result.toJSONString());
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetSlabCostsWithoutQueryParams() throws Exception {	
		System.out.printf("testGetSlabCostsWithoutQueryParams: NullPointerException is expected. %s, %s", itemCode, locationCode);
		System.out.println();
        queryParams.put("freightrate", Arrays.asList(new String[]{freightrate}));
    	queryParams.put("deliverycost", Arrays.asList(new String[]{deliverycost}));
	    SlabCosts result = rpcDao.getSlabCosts(userType, userCode, itemCode, locationCode, "", null);
	    assertNotNull(result);
	    System.out.println("SlabCosts = " + result.toJSONString());
	}
	
	@Test(expected=BedDAOUnAuthorizedException.class)  
	public void testGetSlabCostsWithGuestUserType() throws Exception {	
		System.out.println("testGetSlabCostsWithGuestUserType: BedDAOUnAuthorizedException is expected");
		queryParams.put("freightrate", Arrays.asList(new String[]{freightrate}));
    	queryParams.put("deliverycost", Arrays.asList(new String[]{deliverycost}));
	    SlabCosts result = rpcDao.getSlabCosts(userTypeGuest, userCode, itemCode, locationCode, "", queryParams);
	    assertNotNull(result);
	    System.out.println("SlabCosts = " + result.toJSONString());
	}
	
}
