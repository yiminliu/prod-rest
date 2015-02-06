package com.bedrosians.bedlogic.test.ims;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.dao.ims.ImsDao;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.service.user.KeymarkUcUserService;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(ConcurrentJunitRunner.class)
//@Concurrency(6)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)

public class ImsLoadTest {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ImsDao imsDao;

	@Autowired
	ImsService imsService;
	@Autowired
	KeymarkUcUserService keymarkUcUserService;
	

	@Test
	public void testLoadWithMultiSessions(){
    	long startTime = System.currentTimeMillis();
    	long totalTime = 0l;
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		//params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		//params.put("itemcode", Arrays.asList(new String[]{"AECBUB218NR"}));
		params.put("maxResults", Arrays.asList(new String[]{"500"}));
		for(int i = 0; i < 100; i++){
	        try{
	      	   Session session = sessionFactory.openSession();
	           session.getTransaction().begin();
	           imsService.getItem("AECBUB218NR");
               session.getTransaction().commit();
               session.close();
	        }
	        catch(Exception e){
    	       e.printStackTrace();
            }
	    }
	    totalTime = System.currentTimeMillis() - startTime;
        totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished. total time: " + totalTime);
	}
	
	
	@Test
	public void testWithConcurrentLoad(){
	    ExecutorService executorService = Executors.newFixedThreadPool(10);

	    for (int i = 0; i < 500; i++) {
	    	MyRunnable worker = new MyRunnable();
	        executorService.execute(worker) ;
	        System.out.println("test round " + i);
	        try{
	           Thread.sleep(200);
	        }
	        catch(Exception e){
        	     e.printStackTrace();
            }  
	    }
	    executorService.shutdown();
	}
	
	class MyRunnable implements Runnable{
		
		public MyRunnable(){}
		
		Ims ims = null;
		@Override
		public void run(){
			 for (int i = 0; i < 10; i++) {
	    		   try{
	    			   System.out.println("sub test round " + i);
	    	      	   Session session = getSession();
	    	           session.getTransaction().begin();
	    	           ims = imsService.getItem("AECBUB218NR");
	                   
	    	           session.getTransaction().commit();
	                   session.close();
	                   Assert.assertEquals("AECBUB218NR", ims.getItemcode());
	                  
	    	        }
	    		   catch(Exception e){
	          	     e.printStackTrace();
	              }  
	    		   
	        }
		}
		
	}
	
	private synchronized Session getSession(){
		return sessionFactory.openSession();
	}
}
