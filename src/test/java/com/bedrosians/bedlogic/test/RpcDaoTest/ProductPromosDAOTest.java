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

import com.bedrosians.bedlogic.bedDataAccessDAO.ProductPromosDAO;
import com.bedrosians.bedlogic.models.ProductPromos;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class ProductPromosDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    ProductPromosDAO rpcDao;
    
    static private String userType = "guest";
    static private String userCode = "";
    static private String itemcode = "AECBUB217NR";
    static private String seriesname = "builder";
    static private String promocode = "CL";
    static private String promoregion = "WC";
    static private String materialtype = "Porcelain";
	
    static{
    	System.out.println("******* ProductsDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetProductPromosByPromoCode() throws Exception {
		System.out.println("testGetProductPromosByPromoCode: " + promocode);
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
    	queryParams.put("promocode", Arrays.asList(new String[]{promocode}));
    	ProductPromos result = rpcDao.readProductPromosByQueryParams(userType, userCode, queryParams);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetProductPromosByPromoRegion() throws Exception {
		System.out.println("testGetProductPromosByPromoRegion: " + promoregion);
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
    	queryParams.put("promoregion", Arrays.asList(new String[]{promoregion}));
    	ProductPromos result = rpcDao.readProductPromosByQueryParams(userType, userCode, queryParams);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetProductMaterialtype() throws Exception {
		System.out.println("testGetProductMaterialtype: " + materialtype);
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
    	queryParams.put("materialtype", Arrays.asList(new String[]{materialtype}));
    	ProductPromos result = rpcDao.readProductPromosByQueryParams(userType, userCode, queryParams);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetProductPromosByPromoCodeAndPromoRegion() throws Exception {
		System.out.printf("testGetProductPromosByPromoCode: %s, %s", promocode, promoregion);
		System.out.println();
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
    	queryParams.put("promocode", Arrays.asList(new String[]{promocode}));
    	queryParams.put("promoregion", Arrays.asList(new String[]{promoregion}));
    	ProductPromos result = rpcDao.readProductPromosByQueryParams(userType, userCode, queryParams);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetProductPromosByPromoCodeAndMeterialType() throws Exception {
		System.out.printf("testGetProductPromosByPromoCodeAndMeterialType: %s, %s", promocode, materialtype);
		System.out.println();
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
    	queryParams.put("promocode", Arrays.asList(new String[]{promocode}));
    	queryParams.put("materialtype", Arrays.asList(new String[]{materialtype}));
    	ProductPromos result = rpcDao.readProductPromosByQueryParams(userType, userCode, queryParams);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetProductPromosByPromoRegionAndMeterialType() throws Exception {
		System.out.printf("testGetProductPromosByPromoRegionAndMeterialType: %s, %s", promoregion,materialtype);
		System.out.println();
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
    	queryParams.put("promoregion", Arrays.asList(new String[]{promoregion}));
    	queryParams.put("materialtype", Arrays.asList(new String[]{materialtype}));
    	ProductPromos result = rpcDao.readProductPromosByQueryParams(userType, userCode, queryParams);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetProductPromosByPromoCodeAndPromoRegionAndMeterialType() throws Exception {
		System.out.printf("testGetProductPromosByPromoCodeAndPromoRegionAndMeterialType: %s, %s, %s", promocode, promoregion,materialtype);
		System.out.println();
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
    	queryParams.put("promocode", Arrays.asList(new String[]{promocode}));
    	queryParams.put("promoregion", Arrays.asList(new String[]{promoregion}));
    	queryParams.put("materialtype", Arrays.asList(new String[]{materialtype}));
    	ProductPromos result = rpcDao.readProductPromosByQueryParams(userType, userCode, queryParams);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	

}
