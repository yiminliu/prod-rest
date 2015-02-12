package com.bedrosians.bedlogic.test.ims.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.util.JsonUtil;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ImsServiceDeletionTest {
		

	@Autowired
	ImsService imsService;
 	
	@Before
	public void setup(){
	
	}
	
	@Test
	public void testDeleteItemByItemCode()throws Exception {
	    imsService.deleteItemByItemCode("MKTPCKCRDEPI");
	     System.out.println("testDeleteItem Done");
	        
	 }
	
	@Test
	public void testDeleteItem()throws Exception {
		Ims item = new Ims("TEST2");
		String jStringItemInfo = "{\"itemcode\":\"TEST2\"}";
		//JSONObject params = new JSONObject(jStringItemInfo);
		ObjectNode params= new ObjectNode(JsonNodeFactory.instance);
		params.put("itemcode","TEST2");
	    imsService.deleteItem(params);
	    //imsService.deleteItem(JsonUtil.toObjectNode(params));
	     System.out.println("testDeleteItem Done");
	        
	 }
	
	 @Test
		public void testNothing(){
	}
}
