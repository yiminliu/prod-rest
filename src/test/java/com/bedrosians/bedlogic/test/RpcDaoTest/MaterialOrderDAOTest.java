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
import com.bedrosians.bedlogic.bedDataAccessDAO.MaterialOrdersDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.PricesDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.SlabCostsDAO;
import com.bedrosians.bedlogic.exception.BedDAOUnAuthorizedException;
import com.bedrosians.bedlogic.models.Costs;
import com.bedrosians.bedlogic.models.MaterialOrders;
import com.bedrosians.bedlogic.models.Prices;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class MaterialOrderDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    MaterialOrdersDAO rpcDao;
    
    static private String userType = "keymark";
    static private String userCode = "JACKH";
    static private String userTypeGuest = "guest";
    static private String userCodeGuest = "";
    static private String itemCode = "GLSECP5858-ET";//"AECBUB217NR";
    static private String customercode = "26815";
    static private String branchCode = "125";
    static private String locationCode = "125"; 
    static private String openCode = "Y"; 
    
    static private String unit ="";
	
    static{
    	System.out.println("******* MaterialOrderDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetMaterialOrdersByItemCode() throws Exception {	
		System.out.println("testGetMaterialOrdersByItemCode: " + itemCode);
		MaterialOrders result = rpcDao.readMaterialOrders(userType, userCode, itemCode, "", "");
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetMaterialOrdersByLocationCode() throws Exception {	
		System.out.println("testGetMaterialOrdersByLocationCode: " + locationCode);	
		MaterialOrders result = rpcDao.readMaterialOrders(userType, userCode, "", locationCode, "");
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetMaterialOrdersByOpenCode() throws Exception {	
		System.out.println("testGetMaterialOrdersByOpenCode: " + openCode);
		MaterialOrders result = rpcDao.readMaterialOrders(userType, userCode, "", "", openCode);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetMaterialOrdersByItemCodeAndLoctionCode() throws Exception {	
		System.out.printf("testGetMaterialOrdersByItemCodeAndLoctionCode: %s, %s", itemCode, locationCode);
		System.out.println();
		MaterialOrders result = rpcDao.readMaterialOrders(userType, userCode, itemCode, locationCode, "");
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetMaterialOrdersByLoctionCodeAndOpenCode() throws Exception {	
		System.out.printf("testGetMaterialOrdersByItemCodeAndLoctionCodeAndOpenCode: %s, %s", locationCode, openCode);
		System.out.println();
		MaterialOrders result = rpcDao.readMaterialOrders(userType, userCode, "", locationCode, openCode);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetMaterialOrdersByItemCodeAndLoctionCodeAndOpenCode() throws Exception {	
		System.out.printf("testGetMaterialOrdersByItemCodeAndLoctionCodeAndOpenCode: %s, %s, %s", itemCode, locationCode, openCode);
		MaterialOrders result = rpcDao.readMaterialOrders(userType, userCode, itemCode, locationCode, openCode);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	
}
