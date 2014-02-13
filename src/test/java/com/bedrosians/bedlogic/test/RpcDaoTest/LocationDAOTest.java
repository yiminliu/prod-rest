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

import com.bedrosians.bedlogic.bedDataAccessDAO.LocationsDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.ProductsDAO;
import com.bedrosians.bedlogic.models.Locations;
import com.bedrosians.bedlogic.models.Products;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class LocationDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    LocationsDAO rpcDao;
    
    static private String userType = "guest";
    static private String userCode = "";
    static private String itemcode = "AECBUB217NR";
    static private String seriesname = "builder";
    static private String branchCode = "125";
    static private String locationCodes = "125, 102"; 
    static private String locationRegion = "Central%20CA%20Region"; 
    static private String branchName= "Visalia%20Branch"; 
	
    static{
    	System.out.println("******* LocationDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetLocationsByLocationCodes() throws Exception {
		System.out.println("testGetLocationsByLocationCode: " + locationCodes);
	    Locations result = rpcDao.readLocations(userType, userCode, locationCodes, "", "");
	    assertNotNull(result);
	    System.out.println("Products = " + result.toJSONString());
	}
	
	@Test
	public void testGetLocationsByLocationRegion() throws Exception {
		System.out.println("testGetLocationsByLocationRegion: " + locationRegion);
	    Locations result = rpcDao.readLocations(userType, userCode, "", locationRegion, "");
	    assertNotNull(result);
	    System.out.println("Products = " + result.toJSONString());
	}
	
	
	@Test
	public void testGetLocationsByBranchName() throws Exception {
		System.out.println("testGetLocationsByBranchName: " + branchName);
	    Locations result = rpcDao.readLocations(userType, userCode, "", "", branchName);
	    assertNotNull(result);
	    System.out.println("Products = " + result.toJSONString());
	}
	
	@Test
	public void testGetLocationsByLocationCodesAndLocationRegion() throws Exception {
		System.out.printf("testGetLocationsByLocationCodesAndLocationRegion: %s, %s", locationCodes, locationRegion);
	    Locations result = rpcDao.readLocations(userType, userCode, locationCodes, locationRegion, "");
	    assertNotNull(result);
	    System.out.println("Products = " + result.toJSONString());
	}
	
	@Test
	public void testGetLocationsByLocationCodesAndLocationRegionAndBranchName() throws Exception {
		System.out.printf("testGetLocationsByLocationCodesAndLocationRegion: %s, %s, %s", locationCodes, locationRegion, branchName);
	    Locations result = rpcDao.readLocations(userType, userCode, locationCodes, locationRegion, branchName);
	    assertNotNull(result);
	    System.out.println("Products = " + result.toJSONString());
	}
	
}
