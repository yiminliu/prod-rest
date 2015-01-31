package com.bedrosians.bedlogic.test.ims.junit;

import org.codehaus.jettison.json.JSONObject;
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
	    imsService.deleteItemByItemCode("TEST1");
	     System.out.println("testDeleteItem Done");
	        
	 }
	
	@Test
	public void testDeleteItem()throws Exception {
		Ims item = new Ims("TEST2");
		String jStringItemInfo = "{\"itemcode\":\"TEST2\"}";
		JSONObject params = new JSONObject(jStringItemInfo);
	    imsService.deleteItem(JsonUtil.toObjectNode(params));
	     System.out.println("testDeleteItem Done");
	        
	 }
	
	 @Test
		public void testNothing(){
	}
}
