package com.bedrosians.bedlogic.test.user;

import java.io.StringWriter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import net.minidev.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.user.User;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.service.product.ProductService;
import com.bedrosians.bedlogic.service.security.KeymarkUcUserAuthentication;
import com.bedrosians.bedlogic.service.security.UserAuthenticationProvider;
import com.bedrosians.bedlogic.service.user.UserService;
import com.bedrosians.bedlogic.dao.user.UserDao;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/Bedlogic-test-context.xml")
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
//@TransactionConfiguration(defaultRollback = false)
public class UserAuthenticationTest {
		
	//@Mock
	//ItemDao ItemDaoMock;
	
	//@InjectMocks
	//productServiceImpl productService;
	
	@Autowired
	ProductService productService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	UserAuthenticationProvider authProvider;
	
	@Autowired
	KeymarkUcUserAuthentication keymarkUcUserAuthentication; 
	
	private static String testItemId = null;
	
	@Before
	public void setup(){
		testItemId = "TCRBRE33B-2"; 
	}
	
	@Test
	public void testGetUserByName(){
		User user = null;
		try{	
	      user = userService.getUserByName("yiminliu");
	    }
	    catch(Exception e){
		   e.printStackTrace();
	   }
	  System.out.println("user = " + user.getUsername());
	}
	
	@Test
	public void testAuthenticated(){
		Authentication auth = new UsernamePasswordAuthenticationToken("keymark", "password");
		try{
			Authentication result = authProvider.authenticate(auth);
			assertTrue(result.isAuthenticated());
			List<GrantedAuthority> list = (List<GrantedAuthority>)result.getAuthorities();
		}
	     catch(AuthenticationException e) {
             System.out.println("Authentication failed: " + e.getMessage());
         }
         System.out.println("Successfully authenticated. Security context contains: " +
              SecurityContextHolder.getContext().getAuthentication());
  
	 }
	
	@Test
	public void testGetAuthorities(){
		Authentication auth = new UsernamePasswordAuthenticationToken("test", "password");
		try{
			Authentication result = authProvider.authenticate(auth);
			assertNotNull(result.getAuthorities());
			List<GrantedAuthority> list = (List<GrantedAuthority>)result.getAuthorities();
			for(GrantedAuthority ga : list){
				System.out.println("authority = " + ga.getAuthority());
			}
		}
	     catch(AuthenticationException e) {
             System.out.println("Authentication failed: " + e.getMessage());
         }
         System.out.println("Successfully authenticated. Security context contains: " + SecurityContextHolder.getContext().getAuthentication()); 
	 }
	
	@Test
	public void testRoleBasedAuthrorization(){
		Authentication auth = new UsernamePasswordAuthenticationToken("yiminliu", "password");
		Item item = null;
		try{
			Authentication result = authProvider.authenticate(auth);
			SecurityContextHolder.getContext().setAuthentication(result);
			assertNotNull(result.getAuthorities());
			List<GrantedAuthority> list = (List<GrantedAuthority>)result.getAuthorities();
			for(GrantedAuthority ga : list){
				System.out.println("authority = " + ga.getAuthority());
			}
			item = productService.getProductById(testItemId);
		}
		catch(AuthenticationException e) {
             System.out.println("Test failed: " + e.getMessage());
         }
		 catch(Exception e) {
             System.out.println("Authentication failed: " + e.getMessage());
         }
         String jsonStr = null;  
        
         Products result = new Products(item);
        
        try{
            jsonStr = result.toJSONStringWithJackson();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
       
        System.out.println("items   = " + jsonStr);
        
		//System.out.println("item = " + item);
         System.out.println("Successfully authenticated. Security context contains: " + SecurityContextHolder.getContext().getAuthentication()); 
	 }
	
	
	@Test
	public void testKeymarkUcUserAuthenticationWithGuest(){
		String userType = "guest";
		String userCode = "";
		boolean result = false;
		//"keymark", "password");
		try{
			result = keymarkUcUserAuthentication.authenticate(userType, userCode);
			
		}
	     catch(Exception e) {
             System.out.println("Authentication failed: " + e.getMessage());
         }
		 assertTrue(result);
         System.out.println("Successfully authenticated = " + result);
     
	 }
	
	@Test  
	public void testKeymarkUcUserAuthenticationWithKeymarkUser(){
		String userType = "keymark";
		String userCode = "JOSEDE";//"MIKET";
		boolean result = false;
		//"keymark", "password");
		try{
			result = keymarkUcUserAuthentication.authenticate(userType, userCode);
		}
	     catch(BedResUnAuthorizedException e) {
             System.out.println("Authentication failed: " + e.getMessage());
         }
		catch(Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
		System.out.println("Successfully authenticated = " + result);
		assertTrue(result);
	 }
	
	@Test(expected = BedResUnAuthorizedException.class)  
	public void testKeymarkUcUserAuthenticationWithInactiveKeymarkUser(){
		String userType = "keymark";
		String userCode = "JOSEDE";//"MIKET";
		boolean result = false;
		try{
			result = keymarkUcUserAuthentication.authenticate(userType, userCode);
		}
	     catch(BedResUnAuthorizedException e) {
             System.out.println("Authentication failed: " + e.getMessage());
         }
		catch(Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
		System.out.println("Successfully authenticated = " + result);
	 }
	
	@Test
	public void testKeymarkUcUserAuthenticationWithFalkKeymarkUser(){
		String userType = "keymark";
		String userCode = "MT";
		boolean result = false;
		//"keymark", "password");
		try{
			result = keymarkUcUserAuthentication.authenticate(userType, userCode);
		}
	     catch(Exception e) {
             System.out.println("Authentication failed: " + e.getMessage());
         }
		assertFalse(result);
        System.out.println("Successfully authenticated = " + result);
  
	 }
	
}
