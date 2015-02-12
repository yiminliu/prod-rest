package com.bedrosians.bedlogic.test.ims;

import java.util.Arrays;

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
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.service.user.KeymarkUcUserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)

public class ImsPerformanceTest {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ImsDao imsDao;
	@Autowired
	ImsService imsService;
	@Autowired
	KeymarkUcUserService keymarkUcUserService;
	
	@Test
	public void testBatchSize(){
		
	}
	
	@Test
	public void testPerformanceWithProductOriginByMultiSessions(){
    	long startTime = System.currentTimeMillis();
    	long totalTime = 0l;
		MultivaluedMap<String,String> params = new MultivaluedStringMap();
		//params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		params.put("origin", Arrays.asList(new String[]{"USA"}));
		params.put("maxResults", Arrays.asList(new String[]{"500"}));
		
	    for(int i = 0; i< 100; i++){
	    	try{
	           Session session = sessionFactory.openSession();
	           session.getTransaction().begin();
	           imsService.getItems(params, true);
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
	public void testNothing(){
	}
	
}
