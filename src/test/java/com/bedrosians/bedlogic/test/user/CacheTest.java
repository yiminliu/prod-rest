package com.bedrosians.bedlogic.test.user;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.dao.ims.ImsDao;
import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.service.user.KeymarkUcUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)

public class CacheTest {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ImsDao itemDao;

	@Autowired
	ImsService imsService;
	@Autowired
	KeymarkUcUserService keymarkUcUserService;
	
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
          sessionFactory.getStatistics().logSummary();
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("1st round total time: " + totalTime + " ms");
	   
	    /* System.out.println("1st round Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
	    System.out.println("1st round Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
        System.out.println("1st round Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
       */
	    System.out.println("Start 2nd round requery....");
	    startTime = System.currentTimeMillis();
	    session = sessionFactory.openSession();
	    try{
	    	session.getTransaction().begin();
	    	KeymarkUcUser user = keymarkUcUserService.getUserByUserCode("STEVEF");
	    	//KeymarkUcUser user = keymarkUcUserService.getUserByUserCode("STEVEF    ");
		    session.getTransaction().commit();
		    System.out.println("Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
		    System.out.println("Statistics().getTransactionCount() = "  + sessionFactory.getStatistics().getTransactionCount());
		  	System.out.println("Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
	        System.out.println("Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
 	        //sessionFactory.getStatistics().logSummary();
	        session.close();
		    }
		    catch(Exception e){
	    	   e.printStackTrace();
	     }  
	     totalTime = System.currentTimeMillis() - startTime;
         System.out.println("2nd round total time: " + totalTime + " ms");
	}
	
	@Test
	public void testUserCacheWithMultipleUserIds(){
		KeymarkUcUser user = null;
		long startTime = System.currentTimeMillis();
		
		Session session = sessionFactory.openSession();
	    try{
	      session.getTransaction().begin();
	      System.out.println("Start 1st round test");
	      user = keymarkUcUserService.getUserByUserCode("STEVEF");
	      user = keymarkUcUserService.getUserByUserCode("ERIC");
	      user = keymarkUcUserService.getUserByUserCode("JIMMYV");
	      user = keymarkUcUserService.getUserByUserCode("HUNTER");
	      user = keymarkUcUserService.getUserByUserCode("CHRIS04");
	      session.getTransaction().commit();
          session.close();
          System.out.println("Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
		  System.out.println("Statistics().getTransactionCount() = "  + sessionFactory.getStatistics().getTransactionCount());
		  System.out.println("Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
	      System.out.println("Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 0
          //sessionFactory.getStatistics().logSummary();
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("1st round total time: " + totalTime + " ms");
	   
	    System.out.println("Start 2nd round requery....");
	    startTime = System.currentTimeMillis();
	    session = sessionFactory.openSession();
	    try{
	    	session.getTransaction().begin();
	    	user = keymarkUcUserService.getUserByUserCode("STEVEF");
	    	user = keymarkUcUserService.getUserByUserCode("ERIC");
		    user = keymarkUcUserService.getUserByUserCode("JIMMYV");
		    user = keymarkUcUserService.getUserByUserCode("HUNTER");
		    user = keymarkUcUserService.getUserByUserCode("CHRIS04");
	    	//KeymarkUcUser user = keymarkUcUserService.getUserByUserCode("STEVEF    ");
		    session.getTransaction().commit();
		    session.close();
		    System.out.println("Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
		    System.out.println("Statistics().getTransactionCount() = "  + sessionFactory.getStatistics().getTransactionCount());
		  	System.out.println("Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
	        System.out.println("Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
 	        //sessionFactory.getStatistics().logSummary();
		    }
		    catch(Exception e){
	    	   e.printStackTrace();
	     }  
	     totalTime = System.currentTimeMillis() - startTime;
         System.out.println("2nd round total time: " + totalTime + " ms");
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
        System.out.println("Finished. 1st round total time: " + totalTime + " ms");
	   
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
	    	
	      	KeymarkUcUser user = keymarkUcUserService.getUserByUserCode("STEVEF");
		    session.getTransaction().commit();
		    System.out.println("Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
		    System.out.println("Statistics().getTransactionCount() = "  + sessionFactory.getStatistics().getTransactionCount());
		  	System.out.println("Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
	        System.out.println("Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
	        // sessionFactory.getStatistics().logSummary();
	        session.close();
		    }
		    catch(Exception e){
	    	   e.printStackTrace();
	     }  
	    
	    totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished. 2nd round total time: " + totalTime + " ms");
	}
	
	@Test
	public void testNothing(){
	}
	
}
