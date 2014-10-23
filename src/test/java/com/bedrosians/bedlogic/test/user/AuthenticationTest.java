package com.bedrosians.bedlogic.test.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.dao.user.KeymarkUcUserDao;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.service.security.KeymarkUcUserSecurityService;
import com.bedrosians.bedlogic.util.enums.ApiName;
import com.bedrosians.bedlogic.util.enums.DBOperation;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/Bedlogic-test-context.xml")
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class AuthenticationTest {
		
	private static final ApiName APINAME = ApiName.IMS;
	@Autowired
	ImsService imsService;
	@Autowired
	KeymarkUcUserSecurityService keymarkUcUserSecurityService;
	
	@Autowired
	KeymarkUcUserDao keymarkUcUserSecurityDao;
 	
	@Before
	public void setup(){
	
	}
	   
	@Test
	public void testGuestUserForProductSearchPermission(){
		String userType = "guest";
		String userCode = "";
		try{
		   keymarkUcUserSecurityService.doSecurityCheck(userType, userCode, APINAME, DBOperation.SEARCH);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(expected = BedResUnAuthorizedException.class)
	public void testGuestUserForProductUpdatePermission()throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		String userType = "guest";
		String userCode = "";
  	    keymarkUcUserSecurityService.doSecurityCheck(userType, userCode, APINAME, DBOperation.UPDATE);
	}
	
	@Test(expected = BedDAOBadParamException.class)
	public void testBadUserType() throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		String userType = "abcde";
		String userCode = "";
  	    keymarkUcUserSecurityService.doSecurityCheck(userType, userCode, APINAME, DBOperation.UPDATE);
	}
	
	
	@Test
	public void testKeymarkUcUserForProductSearchPermission(){
		String userType = "keymark";
		String userCode = "JBED";
		try{
		   keymarkUcUserSecurityService.doSecurityCheck(userType, userCode, APINAME, DBOperation.SEARCH);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testKeymarkUcUserForProductUpdatePermission(){
		String userType = "keymark";
		String userCode = "JBED";
		try{
		   keymarkUcUserSecurityService.doSecurityCheck(userType, userCode, APINAME, DBOperation.UPDATE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(expected = BedResUnAuthorizedException.class)
	public void testInvalidKeymarkUcUser()throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		String userType = "keymark";
		String userCode = "yyyy";
		keymarkUcUserSecurityService.doSecurityCheck(userType, userCode, APINAME, DBOperation.UPDATE);
	}
	
	@Test(expected = BedResUnAuthorizedException.class)
	public void testKeymarkUcUserWithoutUpdatePermission() throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		String userType = "keymark";
		String userCode = "TEST";
	    keymarkUcUserSecurityService.doSecurityCheck(userType, userCode, APINAME, DBOperation.UPDATE);
		
	}
	
}
