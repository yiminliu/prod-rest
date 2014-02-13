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

import com.bedrosians.bedlogic.bedDataAccessDAO.AccountsDAO;
import com.bedrosians.bedlogic.bedDataAccessDAO.ProductsDAO;
import com.bedrosians.bedlogic.exception.BedDAOUnAuthorizedException;
import com.bedrosians.bedlogic.models.Accounts;
import com.bedrosians.bedlogic.models.Products;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "/WebServiceTest-context.xml")
public class AccountsDAOTest extends TestCase{

    @Autowired
	private WebApplicationContext wac;
    
    @Autowired
    AccountsDAO rpcDao;
    
    static private String userType = "keymark";
    static private String userCode = "JACKH";
    static private String userTypeGuest = "guest";
    static private String userCodeGuest = "";
    static private String itemcode = "AECBUB217NR";
    static private String customercode = "26815";
    static private String customerName = "A";
    static private String branchCode = "125";
    static private String locationCode = "125"; 
    static private String creditstatus = "HOLD";
	
    static{
    	System.out.println("******* AccountsDAOTest ********");
    }
    
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testGetAccountsByCustomerCode() throws Exception {
		System.out.println("testGetAccountsByCustomerCode: " + itemcode);
	    Accounts result = rpcDao.getAccounts(userType, userCode, customercode, "", "", "");
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetAccountsByCustomerCodeAndBranchCode() throws Exception {
		System.out.printf("testGetAccountsByCustomerCodeAndBranchCode: %s, %s",itemcode,branchCode);
	    System.out.println();
	    Accounts result = rpcDao.getAccounts(userType, userCode, customercode, branchCode, "", "");
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetAccountsByCustomerName() throws Exception {
		System.out.printf("testGetAccountsByCustomerName: %s",customerName);
		System.out.println();
	    Accounts result = rpcDao.getAccounts(userType, userCode, "", "", customerName, "");
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetAccountsByBranchCodeAndCreditStatus() throws Exception {
		System.out.printf("testGetAccountsByBranchCodeAndCreditStatus: %s, %s", branchCode , creditstatus);
	    System.out.println();
		Accounts result = rpcDao.getAccounts(userType, userCode, "", branchCode, "", creditstatus);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test
	public void testGetAccountsByCreditStatus() throws Exception {
		System.out.printf("testGetAccountsByCreditStatus: %s", creditstatus);
	    System.out.println();
		Accounts result = rpcDao.getAccounts(userType, userCode, "", "", "", creditstatus);
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	@Test(expected=BedDAOUnAuthorizedException.class)  
	public void testGetAccountsByGuestUserType() throws Exception {
		System.out.println("testGetProductsByItemCode: BedDAOUnAuthorizedException is expected");
	    Accounts result = rpcDao.getAccounts(userTypeGuest, userCode, customercode, branchCode, "", "");
	    assertNotNull(result);
	    System.out.println("Result = " + result.toJSONString());
	}
	
	
}
