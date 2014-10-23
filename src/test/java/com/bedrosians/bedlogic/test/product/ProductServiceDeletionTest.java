package com.bedrosians.bedlogic.test.product;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.service.ims.ImsService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ProductServiceDeletionTest {
		

	@Autowired
	ImsService imsService;
 	
	@Before
	public void setup(){
	
	}
	
	@Test
	public void testDeleteItemById()throws Exception {
	    imsService.deleteProductById("NEWITEMCODE");
	     System.out.println("testDeleteItem Done");
	        
	 }
	
	@Test
	public void testDeleteItem()throws Exception {
		Item item = new Item("NEWITEMCODE3");
		String jStringItemInfo = "{\"itemcode\":\"newItemcode3\"}";
		JSONObject params = new JSONObject(jStringItemInfo);
	    imsService.deleteProduct(params);
	     System.out.println("testDeleteItem Done");
	        
	 }
	
	 @Test
		public void testNothing(){
	}
}
