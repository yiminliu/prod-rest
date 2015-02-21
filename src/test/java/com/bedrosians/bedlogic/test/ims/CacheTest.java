package com.bedrosians.bedlogic.test.ims;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.dao.ims.ImsDao;
import com.bedrosians.bedlogic.domain.ims.IconCollection;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.ImsNewFeature;
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
	ImsService imsService;
	@Autowired
	ImsDao imsDao;
	@Autowired
	KeymarkUcUserService keymarkUcUserService;
	
	@Test
	//@Transactional
	public void testCacheWithProductId(){
    	long startTime = System.currentTimeMillis();
    	long totalTime = 0l;
		MultivaluedMap<String,String> params = new MultivaluedStringMap();
		//params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		//params.put("itemcode", Arrays.asList(new String[]{"AECBUB218NR"}));
		params.put("maxResults", Arrays.asList(new String[]{"500"}));
		Session session = sessionFactory.openSession();
		Ims item = null;
	    try{
	      session.getTransaction().begin();
	      System.out.println("Before 1st round Statistics().getEntityFetchCount() = "  + session.getStatistics().getEntityKeys());
         // item = itemDao.getItemById(session, "AECBUB218NR");
	      item = imsService.getItem("AECBUB218NR");
          session.getTransaction().commit();
          session.close();
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished 1st query, total time: " + totalTime);
	    System.out.println("1st round retrieved item = " + item);
	    System.out.println("1st round Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
	    System.out.println("1st round Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
        System.out.println("1st round Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
        System.out.println("Item retrieved: " + item);
	    System.out.println("Start 2nd round requery....");
	    session = sessionFactory.openSession(); 
	    System.out.println("Cache Contains Item \"AECBUB218NR\" ?"+sessionFactory.getCache().containsEntity(Ims.class, "AECBUB218NR"));
	    System.out.println("Cache Contains Item\"AECBUB218NR       \" ?"+sessionFactory.getCache().containsEntity(Ims.class, "AECBUB218NR       "));
	
	    //System.out.println("Cache Contains ItemNewFeature \"AECBUB218NR\" ?"+sessionFactory.getCache().containsEntity("itemNewFeature", "AECBUB218NR"));
	    //System.out.println("Cache Contains ItemNewFeature \"AECBUB218NR       \" ?"+sessionFactory.getCache().containsEntity("Item.itemNewFeature", "AECBUB218NR       "));
	  
	    //System.out.println("Cache Contains IconCollection \"AECBUB218NR\" ?"+sessionFactory.getCache().containsEntity("item.iconDescription", "AECBUB218NR"));
	    //System.out.println("Cache Contains IconCollection \"AECBUB218NR       \" ?"+sessionFactory.getCache().containsEntity("Item.iconDescription", "AECBUB218NR       "));
	
	    System.out.println("Cache Contains ItemNewFeature \"AECBUB218NR\" ?"+sessionFactory.getCache().containsEntity(ImsNewFeature.class, "AECBUB218NR"));
	    System.out.println("Cache Contains ItemNewFeature \"AECBUB218NR       \" ?"+sessionFactory.getCache().containsEntity(ImsNewFeature.class, "AECBUB218NR       "));
	  
	    System.out.println("Cache Contains IconCollection \"AECBUB218NR\" ?"+sessionFactory.getCache().containsEntity(IconCollection.class, "AECBUB218NR"));
	    System.out.println("Cache Contains IconCollection \"AECBUB218NR       \" ?"+sessionFactory.getCache().containsEntity(IconCollection.class, "AECBUB218NR       "));
	
	    try{
	    	startTime = System.currentTimeMillis();
	    	session.getTransaction().begin();
	         // item = itemDao.getItemById(session, "AECBUB218NR       ");
	     	item = imsService.getItem("AECBUB218NR");
	        System.out.println("2 round retrieved item = " + item);
	        session.getTransaction().commit();
	
		}
		catch(Exception e){
	       e.printStackTrace();
	    }
		finally
        {
	      System.out.println("Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
	      System.out.println("Statistics().getTransactionCount() = "  + sessionFactory.getStatistics().getTransactionCount());
	  	  System.out.println("Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
          System.out.println("Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
          System.out.println("In session, Statistics().getEntityCount() = "  + session.getStatistics().getEntityCount());
          System.out.println("In session, Statistics().getEntityKeys() = "  + session.getStatistics().getEntityKeys()); 
          System.out.println("In session, Statistics().getCollectionCount() = "  + session.getStatistics().getCollectionCount());
          System.out.println("In session, Statistics().getCollectionKeys() = "  + session.getStatistics().getCollectionKeys()); 
          sessionFactory.getStatistics().logSummary();
          session.close();
        }
	    System.out.println("Item retrieved: " + item);    
	    totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished. 2nd query total time: " + totalTime);
	}
	
	@Test
	@Transactional
	public void testCacheWithProductIdMatch(){
    	long startTime = System.currentTimeMillis();
    	long totalTime = 0l;
		MultivaluedMap<String,String> params = new MultivaluedStringMap();
		//params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		params.put("itemcode", Arrays.asList(new String[]{"TCRD"}));
		params.put("maxResults", Arrays.asList(new String[]{"500"}));
		Session session = sessionFactory.openSession();
		List<Ims> items = null;
	    try{
	      session.getTransaction().begin();
	      System.out.println("Before 1st round Statistics().getEntityFetchCount() = "  + session.getStatistics().getEntityKeys());
	      items = imsDao.getItemsByQueryParameters(params);
          session.getTransaction().commit();
          session.close();
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    totalTime = System.currentTimeMillis() - startTime;
        System.out.println("1st query, total time: " + totalTime);
	    System.out.println("1st round Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
	    System.out.println("1st round Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
        System.out.println("1st round Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 0
     
	    System.out.println("Start 2nd round requery....");
	  
	    try{
	    	startTime = System.currentTimeMillis();
	   	    session = sessionFactory.openSession(); 

	    	session.getTransaction().begin();
	    	items = imsDao.getItemsByQueryParameters(params);
	        session.getTransaction().commit();
		}
		catch(Exception e){
	       e.printStackTrace();
	    }
		finally
        {
	      System.out.println("Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
	      System.out.println("Statistics().getTransactionCount() = "  + sessionFactory.getStatistics().getTransactionCount());
	  	  System.out.println("Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
          System.out.println("Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
          //sessionFactory.getStatistics().logSummary();
          session.close();
        }  
	    totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished. 2nd query total time: " + totalTime);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	//@Transactional
	public void testCacheWithProductOrigin(){
    	long startTime = System.currentTimeMillis();
    	long totalTime = 0l;
		MultivaluedMap<String,String> params = new MultivaluedStringMap();
		//params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		params.put("origin", Arrays.asList(new String[]{"USA"}));
		params.put("maxResults", Arrays.asList(new String[]{"500"}));
		Session session = sessionFactory.openSession();
		List<Ims> items = null;
	    try{
	      session.getTransaction().begin();
	      System.out.println("Before 1st round Statistics().getEntityFetchCount() = "  + session.getStatistics().getEntityKeys());
         // item = itemDao.getItemById(session, "AECBUB218NR");
	      items = (List<Ims>)imsService.getItems(params, false);
          session.getTransaction().commit();
          System.out.println("1st round Statistics().getEntityFetchCount() = "  + session.getStatistics().getEntityKeys());
          session.close();
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished 1st query, total time: " + totalTime);
	    System.out.println("1st round retrieved # of item = " + items.size());
	    System.out.println("1st round Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
	    System.out.println("1st round Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
        System.out.println("1st round Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
        
	    System.out.println("Start 2nd round requery....");
	    session = sessionFactory.openSession();
	    //System.out.println("Cache Contains \"AECBUB218NR\" ?"+sessionFactory.getCache().containsEntity(Item.class, "AECBUB218NR"));
	    //System.out.println("Cache Contains \"AECBUB218NR       \" ?"+sessionFactory.getCache().containsEntity(Item.class, "AECBUB218NR       "));
	  
	    try{
	    	startTime = System.currentTimeMillis();
	    	session.getTransaction().begin();
	    	items = (List<Ims>)imsService.getItems(params, false);
	        session.getTransaction().commit();
	
		}
		catch(Exception e){
	       e.printStackTrace();
	    }
		finally
        {
		  System.out.println("1st round retrieved # of item = " + items.size());
	      System.out.println("Statistics().getConnectCount() = "  + sessionFactory.getStatistics().getConnectCount());
	      System.out.println("Statistics().getTransactionCount() = "  + sessionFactory.getStatistics().getTransactionCount());
	  	  System.out.println("Statistics().getEntityFetchCount() = "  + sessionFactory.getStatistics().getEntityFetchCount()); //Prints 1
          System.out.println("Statistics().getSecondLevelCacheHitCount() = " + sessionFactory.getStatistics().getSecondLevelCacheHitCount()); //Prints 1
          sessionFactory.getStatistics().logSummary();
          session.close();
        } 
	    totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished. 2nd query total time: " + totalTime);
	}
	
	@Test
	public void testNothing(){
	}
	
}
