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

import com.bedrosians.bedlogic.bedDataAccessDAO.ProductPromosDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.ProductsDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.PromoSeriesDAO;
import com.bedrosians.bedlogic.models.ProductPromos;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.models.PromoSeries;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class PromoSeriesDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    PromoSeriesDAO rpcDao;
    
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
	public void testGetPromoSeriesByPromoCode() throws Exception {
		System.out.println("testGetPromoSeriesByPromoCode: " + promocode);
    	PromoSeries result = rpcDao.getPromoSeries(userType, userCode, promocode, "", "");
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetPromoSeriesByPromoRegion() throws Exception {
		System.out.println("testGetPromoSeriesByPromoRegion: " + promoregion);
		PromoSeries result = rpcDao.getPromoSeries(userType, userCode, "", promoregion, "");
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetPromoSeriesByMaterialtype() throws Exception {
		System.out.println("testGetPromoSeriesByMaterialtype: " + materialtype);
	 	PromoSeries result = rpcDao.getPromoSeries(userType, userCode, "", "", materialtype);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetPromoSeriesByPromoCodeAndPromoRegion() throws Exception {
		System.out.printf("testGetPromoSeriesByPromoCodeAndPromoRegion: %s, %s", promocode, promoregion);
		System.out.println();
		PromoSeries result = rpcDao.getPromoSeries(userType, userCode, promocode, promoregion, "");
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetPromoSeriesByPromoCodeAndMeterialType() throws Exception {
		System.out.printf("testGetPromoSeriesByPromoCodeAndMeterialType: %s, %s", promocode, materialtype);
		System.out.println();
		PromoSeries result = rpcDao.getPromoSeries(userType, userCode, promocode, "", materialtype);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetPromoSeriesByPromoRegionAndMeterialType() throws Exception {
		System.out.printf("testGetPromoSeriesByPromoRegionAndMeterialType: %s, %s", promoregion,materialtype);
		System.out.println();
		PromoSeries result = rpcDao.getPromoSeries(userType, userCode, "", promoregion, materialtype);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetPromoSeriesByPromoCodeAndPromoRegionAndMeterialType() throws Exception {
		System.out.printf("testGetPromoSeriesByPromoCodeAndPromoRegionAndMeterialType: %s, %s, %s", promocode, promoregion,materialtype);
		System.out.println();
		MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
		PromoSeries result = rpcDao.getPromoSeries(userType, userCode, promocode, promoregion, materialtype);
        assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	

}
