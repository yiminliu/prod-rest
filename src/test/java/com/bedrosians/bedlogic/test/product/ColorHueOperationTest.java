package com.bedrosians.bedlogic.test.product;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.dao.item.ColorHueDao;
import com.bedrosians.bedlogic.dao.item.ItemDao;
import com.bedrosians.bedlogic.domain.item.ColorHue;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.util.ImsDataUtil;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)

public class ColorHueOperationTest {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ItemDao itemDao;
	@Autowired
	ColorHueDao colorHueDao;
	
	@Test
	public void testCreateColorHue(){
       //List<Item> items = itemDao.findAll(sessionFactory.getCurrentSession());
		Set<ColorHue> colorHues = new HashSet<>();
		List<Item> items = null;
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		//params.put("inactivecode", Arrays.asList(new String[]{"N"}));
		//params.put("itemcode", Arrays.asList(new String[]{"AECBUB218NR"}));
		params.put("maxResults", Arrays.asList(new String[]{"50000"}));
	    try{
          items = itemDao.getItemsByQueryParameters(params);//.findAll(sessionFactory.getCurrentSession());
	    }
	    catch(Exception e){
    	   e.printStackTrace();
        }
	    System.out.println(items.size() + " items retrieved");
        for(Item item : items){
        	//System.out.println("colorHue = "+ item.getColorcategory());
        	Set<ColorHue> colors = ImsDataUtil.convertColorCategoryToColorHueObjects(item);
    	    if(colors != null)
    		  colorHues.addAll(colors);
        }
        System.out.println(colorHues.size() + " colorhues are ready to create");
       //for(ColorHue colorHue :colorHues){
    	 //  System.out.println("" + colorHue);
       //}
        colorHueDao.createColorHues(colorHues);
  
        System.out.println("Finished test. " + colorHues.size() + " colorhues are created");
	}
	
	@Test
	public void testNothing(){
	}
	
}
