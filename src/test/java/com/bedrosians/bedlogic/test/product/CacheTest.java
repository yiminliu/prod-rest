package com.bedrosians.bedlogic.test.product;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.dao.item.ItemDao;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.service.product.ProductService;
import com.bedrosians.bedlogic.service.user.KeymarkUcUserService;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)

public class CacheTest {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ItemDao itemDao;

	@Autowired
	ProductService productService;
	@Autowired
	KeymarkUcUserService keymarkUcUserService;
	
	@Test
	public void testBatchSize(){
		
	}
	
	@Test
	public void testCacheWithProductOriginByMultiSessions(){
    	long startTime = System.currentTimeMillis();
    	long totalTime = 0l;
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		//params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		params.put("origin", Arrays.asList(new String[]{"USA"}));
		params.put("maxResults", Arrays.asList(new String[]{"500"}));
		
		List<Item> items = null;
	    for(int i = 0; i< 100; i++){
	    	try{
	           Session session = sessionFactory.openSession();
	           session.getTransaction().begin();
	           items = productService.getProducts(params);
               session.getTransaction().commit();
               session.close();
	        }
	        catch(Exception e){
    	      e.printStackTrace();
            }
	    } 	
	    totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished. total time: " + totalTime);
	}
	
	@Test
	public void testUserCache(){
    	long startTime = System.currentTimeMillis();
		
		Session session = sessionFactory.openSession();
	    try{
	      session.getTransaction().begin();
	      System.out.println("Start 1st round test");
	      KeymarkUcUser user = keymarkUcUserService.getUserByUserCode("STEVEF");
	      session.getTransaction().commit();
          session.close();
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	   
	 /* System.out.println("1st round Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
	    System.out.println("1st round Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
        System.out.println("1st round Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
       */
	    System.out.println("Start 2nd round requery....");
	    session = sessionFactory.openSession();
	    try{
	    	session.getTransaction().begin();
	    	KeymarkUcUser user = keymarkUcUserService.getUserByUserCode("STEVEF    ");
		     session.getTransaction().commit();
		     System.out.println("Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
		      System.out.println("Statistics().getTransactionCount() = "  + sessionFactory.getStatistics().getTransactionCount());
		  	  System.out.println("Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
	          System.out.println("Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
	          System.out.println("Statistics().getEntityCount() = "  + session.getStatistics().getEntityCount());
	          System.out.println("Statistics().getEntityKeys() = "  + session.getStatistics().getEntityKeys()); 
	          System.out.println("Statistics().getCollectionCount() = "  + session.getStatistics().getCollectionCount());
	         System.out.println("Statistics().getCollectionKeys() = "  + session.getStatistics().getCollectionKeys()); 
	         sessionFactory.getStatistics().logSummary();
	          session.close();
		    }
		    catch(Exception e){
	    	   e.printStackTrace();
	     }  
	     long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished. And total time: " + totalTime);
	}
	
	@Test
	public void testAllUserCache(){
    	long startTime = System.currentTimeMillis();
		
		Session session = sessionFactory.openSession();
	    try{
	      session.getTransaction().begin();
	      System.out.println("Start 1st round requery...."); 
	      List<KeymarkUcUser> users = keymarkUcUserService.getAllUsers(session);
	      session.getTransaction().commit();
	      System.out.println( users.size() + " users retrieved");
          //System.out.println("1st round Statistics().getEntityFetchCount() = "  + session.getStatistics().getEntityKeys());
          session.close();
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished. 1st round total time: " + totalTime);
	   
	 /* System.out.println("1st round Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
	    System.out.println("1st round Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
        System.out.println("1st round Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
        */
	    System.out.println("Start 2nd round requery....");
	    startTime = System.currentTimeMillis();
	    session = sessionFactory.openSession();
	    System.out.println("Cache Contains \"STEVEF\" ?"+sessionFactory.getCache().containsEntity(KeymarkUcUser .class, "STEVEF"));
	    System.out.println("Cache Contains \"STEVEF    \" ?"+sessionFactory.getCache().containsEntity(KeymarkUcUser .class, "STEVEF    "));
	    try{
	    	session.getTransaction().begin();
	    	
	    	KeymarkUcUser user = keymarkUcUserService.getUserByUserCode("STEVEF    ");
	    	//KeymarkUcUser user = keymarkUcUserService.getUserByUserCode("STEVEF");
		    session.getTransaction().commit();
		    System.out.println("Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
		    System.out.println("Statistics().getTransactionCount() = "  + sessionFactory.getStatistics().getTransactionCount());
		  	System.out.println("Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
	        System.out.println("Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
	        System.out.println("Statistics().getEntityCount() = "  + session.getStatistics().getEntityCount());
	        System.out.println("Statistics().getEntityKeys() = "  + session.getStatistics().getEntityKeys()); 
	         // System.out.println("Statistics().getCollectionCount() = "  + session.getStatistics().getCollectionCount());
	        // sessionFactory.getStatistics().logSummary();
	          session.close();
		    }
		    catch(Exception e){
	    	   e.printStackTrace();
	     }  
	     totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished. 2nd round total time: " + totalTime);
	}
	
	@Test
	public void testNothing(){
	}
	
}
