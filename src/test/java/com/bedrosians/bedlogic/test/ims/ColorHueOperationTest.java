package com.bedrosians.bedlogic.test.ims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.dao.ims.ColorHueDao;
import com.bedrosians.bedlogic.dao.ims.ImsDao;
import com.bedrosians.bedlogic.domain.ims.ColorHue;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.util.ims.ImsDataUtil;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)

public class ColorHueOperationTest {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ImsDao imsDao;
	@Autowired
	ColorHueDao colorHueDao;
	
	@Test
	public void testCreateColorHue(){
       //List<Item> items = itemDao.findAll(sessionFactory.getCurrentSession());
		long startTime = System.currentTimeMillis();
		
		Set<ColorHue> colorHues = new HashSet<>();
		List<Ims> items = null;
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		//params.put("itemcode", Arrays.asList(new String[]{"MKTPCKCRDEPI"}));
		params.put("maxResults", Arrays.asList(new String[]{"50000"}));
	    try{
          items = imsDao.getItemsByQueryParameters(params);//.findAll(sessionFactory.getCurrentSession());
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    System.out.println(items.size() + " items retrieved");
        for(Ims item : items){
        	System.out.println("colorHue = "+ item.getColorcategory());
         	Set<ColorHue> colors = ImsDataUtil.convertColorCategoryToColorHueObjects(item);
    	    if(colors != null)
    		  colorHues.addAll(colors);
        }
        System.out.println(colorHues.size() + " colorhues are ready to create");
       for(ColorHue colorHue :colorHues){
    	   System.out.println("" + colorHue);
       }
        colorHueDao.createColorHues(colorHues);
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished test. " + colorHues.size() + " colorhues are created. And total time: " + totalTime +"(ms)");
	}
	
	@Test
	public void testEqualityBetweenColorHueAndColorCotegory(){
     	long startTime = System.currentTimeMillis();	
     	long totalTime;
		List<Ims> items = null;
		int numOfColorHues = 0;
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		//params.put("itemcode", Arrays.asList(new String[]{"MIS15"}));
		params.put("maxResults", Arrays.asList(new String[]{"50000"}));
	    try{
          items = imsDao.getItemsByQueryParameters(params);//.findAll(sessionFactory.getCurrentSession());
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    System.out.println(items.size() + " items retrieved");
	     for(Ims item : items){
        	List<String> colorHueList = new ArrayList<>();
        	List<String> colorCategoryList = new ArrayList<>();
         	for(ColorHue ch : item.getColorhues()){
        		//System.out.println("colorHues = "+ ch.getColorHue());
        		
         		colorHueList.add(ch.getColorHue());
        	}
       	  	Collections.sort(colorHueList);
       	  	if(item.getColorcategory() != null){
        	   colorCategoryList = Arrays.asList(item.getColorcategory().trim().split(":"));
        	   Collections.sort(colorCategoryList);
       	  	}
       	  	System.out.printf("itemcode %s, colorhuelist %s, colorCategorylist %s ", item.getItemcode(), colorHueList,  colorCategoryList);
        	System.out.println();
        	if(!colorCategoryList.isEmpty() && !colorHueList.isEmpty()){
        	   Assert.assertEquals(colorCategoryList, colorHueList);
        	   numOfColorHues++;
        	}   
        }
        totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Finished test. " + numOfColorHues + " colorhues are compared. And total time: " + totalTime/1000 + "s");
	}
	
	/*
	@Test
	public void testEqualityBetweenVendorTableAndVendorField(){
     	long startTime = System.currentTimeMillis();	
		List<Item> items = null;
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		//params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		//params.put("itemcode", Arrays.asList(new String[]{"MIS15"}));
		params.put("maxResults", Arrays.asList(new String[]{"50000"}));
	    try{
          items = imsDao.getItemsByQueryParameters(params);//.findAll(sessionFactory.getCurrentSession());
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    System.out.println(items.size() + " items retrieved");
        for(Ims item : items){
        	Integer vendorId = null;
        	Integer vendorNumber = null;
         	for(Vendor v : item.getNewVendorSystem()){
        		//System.out.println("colorHues = "+ ch.getColorHue());
         		if(v.getVendorOrder() == 1)
        		   vendorId = v.getVendorId();
        	}
       	  	if(item.getVendors() != null){
       	  	   vendorNumber = item.getVendors().getVendornbr1();
       	  	}
       	  	System.out.printf("itemcode %s,vendorId %s, vendorNumber %s ", item.getItemcode(), vendorId,  vendorNumber);
        	System.out.println();
        	if(vendorId != null && vendorId != 0 && vendorNumber != null && vendorNumber != 0 )
       	      Assert.assertEquals(vendorId, vendorNumber);
        }
        //System.out.println("Finished test. " + colorHues.size() + " colorhues are compared. And total time: " + totalTime);
	}
	*/
	
	//@Test
	public void testNothing(){
	}
	
}
