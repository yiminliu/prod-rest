package com.bedrosians.bedlogic.test.ims.junit;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bedrosians.bedlogic.domain.ims.ColorHue;
import com.bedrosians.bedlogic.domain.ims.IconCollection;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.util.JsonUtil;
import com.bedrosians.bedlogic.util.ims.ImsDataUtil;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/Bedlogic-test-context.xml")
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ImsServiceUpdateWithJsonTest {
		
	//@Mock
	//ItemDao ItemDaoMock;
	
	//@InjectMocks
	//productServiceImpl productService;
	
	@Autowired
	ImsService imsService;
	
	private String id = "TEST3";
	
 	
	@Before
	public void setup(){
	
	}
/*	
	@Test
	 public void testUpdateItemWithColorCategoryJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        //JSONObject params = new JSONObject(jStringColorHues);
	        imsService.updateItem(jStringColorHues);
	        
	        Ims item = imsService.getItem(id);
	        assertEquals("BLACK:RED:YELLOW", item.getColorcategory());
	        for(ColorHue ch : item.getColorhues()){
	        	assertTrue(ch.getColorHue().equals("RED") || ch.getColorHue().equals("BLACK") || ch.getColorHue().equals("YELLOW"));
	        }	
	}  
	
	@Test
	 public void testUpdateItemColorhuesWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        //JSONObject params = new JSONObject(jStringWithColorHues);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        
	        Ims item = imsService.getItem(id);
	        assertEquals("GREEN", item.getColorcategory());
	        for(ColorHue ch : item.getColorhues()){
	        	assertTrue(ch.getColorHue().equals("GREEN"));
	        }	
	}  
	
	@Test
	 public void testUpdateItemDesccriptionWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringDescription);
	        imsService.updateItem(JsonUtil.toObjectNode(params)); 
	}
	
	@Test
	 public void testUpdateItemMaterialWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringMaterialInfo);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        	        
	        Ims item = imsService.getItem(id);
	        
	      
	        //material
	        assertEquals("Porcelain", item.getMaterial().getMaterialtype());
	        assertEquals("CTSRC", item.getMaterial().getMaterialclass());
	        assertEquals("FW", item.getMaterial().getMaterialstyle());
	        assertEquals("Trim", item.getMaterial().getMaterialcategory());
	      
	 }      
	   
	 @Test
	 public void testUpdateItemDimensionWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithDimensions);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        	        
	        Ims item = imsService.getItem(id);
	        
	        //dimensions
	        assertEquals("11-13/16", item.getDimensions().getLength());
	        assertEquals("11-13/16", item.getDimensions().getWidth());
	        assertEquals("3/8", item.getDimensions().getThickness());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominallength());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominalwidth());
	        assertEquals("E", item.getDimensions().getSizeunits());
	        assertEquals("E", item.getDimensions().getThicknessunit());
	 }      
	        
	@Test
	 public void testUpdateItemPurchaserWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringDescription);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	}
	
	@Test
	 public void testUpdateItemPriceWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithPriceAndCost);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        
	        Ims item = imsService.getItem(id);
	              
	        //price
            assertEquals("18.3800", item.getPrice().getListprice().toString());
	        assertEquals("18.3800", item.getPrice().getSellprice().toString());
	        assertEquals("10.0000", item.getPrice().getFuturesell().toString());
	        //assertEquals("14.7000", item.getPrice().getPriorsellprice().toString());
	        //assertEquals("10.0000", item.getPrice().getPriorlistprice().toString());
	        assertEquals("16.0000", item.getPrice().getTempprice().toString());
	        assertEquals(new Float(2.0), item.getPrice().getSellpricemarginpct());
	        assertEquals("2", item.getPrice().getPricegroup());
	        assertEquals("2.0", item.getPrice().getSellpricemarginpct().toString());
	        assertEquals("2", item.getPrice().getSellpriceroundaccuracy().toString());
	        assertEquals("15.0", item.getPrice().getMinmarginpct().toString());
	        //assertEquals("SHT", item.getPrice().getPriceunit());
	        
	}
	
	@Test
	 public void testUpdateItemApplicationsWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemApplicationWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithApplicationsInfo);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        	        
	        Ims item = imsService.getItem(id);
   
	        assertEquals("FR:WR:CR:SR:PR", item.getApplications().getResidential());
            assertEquals("FL:WL:CL:SL:PL", item.getApplications().getLightcommercial());
            assertEquals("FC:WC:CC:SC:PC", item.getApplications().getCommercial());
            
            //assertEquals("[FR,WR,CR,SR,PR,FL,WL,CL,SL,PL,FC,WC,CC,SC,PC]", item.getUsage().toString());
	}
	
	@Test
	 public void testUpdateItemWithUsage() throws Exception {
	        System.out.println("testUpdateItemApplicationWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithUsageInfo);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        	        
	        Ims item = imsService.getItem(id);
  
	        assertEquals("FR:WR:CR:SR:PR", item.getApplications().getResidential());
            assertEquals("FL:WL:CL:SL:PL", item.getApplications().getLightcommercial());
            assertEquals("FC:WC:CC:SC:PC", item.getApplications().getCommercial());
            
            assertEquals("[FR,WR,CR,SR,PR,FL,WL,CL,SL,PL,FC,WC,CC,SC,PC]", item.getUsage().toString());
	        
               
	}
	
	@Test
	 public void testUpdateItemWithNotes() throws Exception {
	        System.out.println("testUpdateItemApplicationWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithNotesInfo);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        	        
	        Ims item = imsService.getItem(id);
 
	        assertEquals("test1 po note", item.getNotes().getPonotes());
	        assertEquals("test1 note1", item.getNotes().getBuyernotes());
	        assertEquals("test1 note2", item.getNotes().getInternalnotes());
	        assertEquals("test1 note3", item.getNotes().getInvoicenotes());
              
	}
	
	@Test
	 public void testUpdateItemTestSpecs() throws Exception {
	        System.out.println("testUpdateItemTestSpecWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithTestSpecs);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        	        
	        Ims item = imsService.getItem(id);

	      //test spec
		    assertEquals(Float.valueOf("0.5"), item.getTestSpecification().getWaterabsorption());
	        assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getScratchresistance());
	        assertEquals(Float.valueOf("0.7"), item.getTestSpecification().getPeiabrasion());
	        assertEquals(Float.valueOf("0.8"), item.getTestSpecification().getScofwet());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getScofdry());
	        //assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getBreakingstrength());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getDcof());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getMoh());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getPreconsummer());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getPosconsummer());
             
	}
	
	 @Test
	 public void testUpdateItemWithBasicImsInfoJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringFullItemInfo);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        
	        Ims item = imsService.getItem(id);
	        
	        //series
	        assertEquals("update Athena", item.getSeries().getSeriesname());
	        assertEquals("update Ash", item.getSeries().getSeriescolor());
	   
	        //material
	        assertEquals("update Porcelain", item.getMaterial().getMaterialtype());
	        assertEquals("CTSRC", item.getMaterial().getMaterialclass());
	        assertEquals("FW", item.getMaterial().getMaterialstyle());
	        assertEquals("Trim", item.getMaterial().getMaterialcategory());
	        
	        //dimensions
	        assertEquals("11-13/16", item.getDimensions().getLength());
	        assertEquals("11-13/16", item.getDimensions().getWidth());
	        assertEquals("3/8", item.getDimensions().getThickness());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominallength());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominalwidth());
	        assertEquals("E", item.getDimensions().getSizeunits());
	        assertEquals("E", item.getDimensions().getThicknessunit());
	        
	        //price
	        assertEquals("18.3800", item.getPrice().getListprice().toString());
	        assertEquals("18.3800", item.getPrice().getSellprice().toString());
	        
	        //Units
	        assertEquals("SHT", item.getUnits().getBaseunit());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getBaseisstdsell());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getBaseisstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getBaseisfractqty());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getBaseispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getBaseupc());
	        assertEquals(new BigDecimal("0.0000"), item.getUnits().getBasevolperunit());
	        assertEquals(new BigDecimal("4.2000"), item.getUnits().getBasewgtperunit());
	           
	        assertEquals("CTN", item.getUnits().getUnit1unit());
	        assertEquals(Float.valueOf("4.0"), item.getUnits().getUnit1ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isfractqty());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getUnit1ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit1upc());
	        assertEquals(new BigDecimal("17.4000"), item.getUnits().getUnit1wgtperunit());
	        
	        assertEquals("PLT", item.getUnits().getUnit2unit());
	        assertEquals(Float.valueOf("240.0"), item.getUnits().getUnit2ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit2upc());
	        assertEquals(new BigDecimal("1070.0000"), item.getUnits().getUnit2wgtperunit());
	        
	        assertEquals("", item.getUnits().getUnit3unit());
	        assertEquals(Float.valueOf("0"), item.getUnits().getUnit3ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit3upc());
	        //assertEquals(new BigDecimal("0"), item.getUnits().getUnit3wgtperunit());
	        
	        assertEquals("", item.getUnits().getUnit4unit());
	        assertEquals(Float.valueOf("0"), item.getUnits().getUnit4ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit4upc());
	        //assertEquals(new BigDecimal("0"), item.getUnits().getUnit4wgtperunit());
	        //test spec
		    assertEquals(Float.valueOf("0.5"), item.getTestSpecification().getWaterabsorption());
	        assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getScratchresistance());
	        assertEquals(Float.valueOf("0.7"), item.getTestSpecification().getPeiabrasion());
	        assertEquals(Float.valueOf("0.8"), item.getTestSpecification().getScofwet());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getScofdry());
	        //assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getBreakingstrength());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getDcof());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getMoh());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getPreconsummer());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getPosconsummer());
			//usage
		    //assertEquals("[FR,WR,CR,SR,PR,FL,WL,CL,SL,PL,FC,WC,CC,SC,PC]", item.getUsage());
	      	 
	        //assertEquals("BEIGE".toUpperCase(), item.getColorhues().get(0));
	        assertEquals("ATHENA", item.getItemcategory());
	        assertEquals("Italy", item.getCountryorigin());
	        assertEquals("N", item.getInactivecode());
	        assertEquals("update 2x2 Athena Mosaic on 12x12 Sheet", item.getItemdesc().getFulldesc());    
		    //assertEquals("[BEIGE]", item.getColorhues().toString());
		    //assertTrue(item.getColorhues().contains("BEIGE"));
		    assertEquals("F", item.getItemtypecode());
		    //assertEquals("test", item.getType());
		    assertEquals("C", item.getAbccode());
		    assertEquals("T", item.getTaxclass());
		    assertEquals("ALICIAB", item.getPurchasers().getPurchaser());
		    assertEquals("GFIL", item.getPurchasers().getPurchaser2());
		    assertEquals("V2", item.getShadevariation());
			
		    //application
		    assertEquals("FR:WR:CR:SR:PR", item.getApplications().getResidential());
	        assertEquals("FL:WL:CL:SL:PL", item.getApplications().getLightcommercial());
	        assertEquals("FC:WC:CC:SC:PC", item.getApplications().getCommercial());
			//for(ColorHue hue : item.getNewColorHueSystem()){
			//	System.out.println("hue.getColorDescription() = " + hue.getColorDescription());
			//	assertEquals("red".toUpperCase(), hue.getColorDescription().getDescription().toUpperCase());
	
	        System.out.println("newly Updated Item id  = " + id);
	 }
	 
	 @Test
	 public void testUpdateItemWithAllImsInfoNoUnitAndPriceJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringFullItemInfoWithouUnitAndPrice);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        
	        Ims item = imsService.getItem(id);
	        
	        //series
	        assertEquals("Athena", item.getSeries().getSeriesname());
	        assertEquals("Ash", item.getSeries().getSeriescolor());
	   
	        //material
	        assertEquals("Porcelain", item.getMaterial().getMaterialtype());
	        assertEquals("CTSRC", item.getMaterial().getMaterialclass());
	        assertEquals("FW", item.getMaterial().getMaterialstyle());
	        assertEquals("Trim", item.getMaterial().getMaterialcategory());
	        
	        //dimensions
	        assertEquals("11-13/16", item.getDimensions().getLength());
	        assertEquals("11-13/16", item.getDimensions().getWidth());
	        assertEquals("3/8", item.getDimensions().getThickness());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominallength());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominalwidth());
	        assertEquals("E", item.getDimensions().getSizeunits());
	        assertEquals("E", item.getDimensions().getThicknessunit());
	        
	         //assertEquals(new BigDecimal("0"), item.getUnits().getUnit4wgtperunit());
	        //test spec
		    assertEquals(Float.valueOf("0.5"), item.getTestSpecification().getWaterabsorption());
	        assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getScratchresistance());
	        assertEquals(Float.valueOf("0.7"), item.getTestSpecification().getPeiabrasion());
	        assertEquals(Float.valueOf("0.8"), item.getTestSpecification().getScofwet());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getScofdry());
	        //assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getBreakingstrength());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getDcof());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getMoh());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getPreconsummer());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getPosconsummer());
			//usage
		    //assertEquals("[FR,WR,CR,SR,PR,FL,WL,CL,SL,PL,FC,WC,CC,SC,PC]", item.getUsage());
	      	 
	        //assertEquals("BEIGE".toUpperCase(), item.getColorhues().get(0));
	        assertEquals("ATHENA", item.getItemcategory());
	        assertEquals("Italy", item.getCountryorigin());
	        assertEquals("N", item.getInactivecode());
	        assertEquals("updated 2x2 Athena Mosaic on 12x12 Sheet", item.getItemdesc().getFulldesc()); 
	        assertEquals("RED", item.getColorcategory());
		    //assertEquals("[RED]", item.getColorhues().toString());
		    //assertTrue(item.getColorhues().contains("BEIGE"));
		    assertEquals("F", (item.getItemtypecode()));
		    //assertEquals("test", item.getType());
		    assertEquals("C", item.getAbccode());
		    assertEquals("T", item.getTaxclass());
		    assertEquals("ALICIAB", item.getPurchasers().getPurchaser());
		    assertEquals("GFIL", item.getPurchasers().getPurchaser2());
		    assertEquals("V2", item.getShadevariation());
			
		    //application
		    assertEquals("FR:WR:CR:SR:PR", item.getApplications().getResidential());
	        assertEquals("FL:WL:CL:SL:PL", item.getApplications().getLightcommercial());
	        assertEquals("FC:WC:CC:SC:PC", item.getApplications().getCommercial());
			//for(ColorHue hue : item.getNewColorHueSystem()){
			//	System.out.println("hue.getColorDescription() = " + hue.getColorDescription());
			//	assertEquals("red".toUpperCase(), hue.getColorDescription().getDescription().toUpperCase());
	
	        System.out.println("newly Updated Item id  = " + id);
	 }
	 //-------------- Test Associations -------------//
	 
/*	 //@Test
	 public void testUpdateItemWithColorHuesJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithColorHues: ");
	        JSONObject params = new JSONObject(jStringWithColorHues);
	        productService.updateItem(JsonUtil.toObjectNode(params));
	          
	        Ims item = productService.getItem(id);
	        for(ColorHue colorHue : item.getColorhues()){
	            assertEquals("GREEN", colorHue.getColorHue());
	            assertEquals(colorHue.getColorHue(), item.getColorcategory());
	        } 
	        assertEquals("GREEN",item.getColorcategory());
	 }
	 
	 //@Test
	 public void testUpdateItemWithMultipleColorHuesJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithColorHues: ");
	        JSONObject params = new JSONObject(jStringWithMultipleColorHues);
	        productService.updateItem(JsonUtil.toObjectNode(params));
	        
	        Ims item = productService.getItem(id);
	        for(ColorHue colorHue : item.getColorhues()){
	            assertTrue("BEIGE".equals(colorHue.getColorHue()) || "RED".equals(colorHue.getColorHue()));
	            assertTrue(item.getColorcategory().contains(colorHue.getColorHue()));
	        } 
	        assertTrue("BEIGE".equals(item.getColorcategory()) || "RED".equals(item.getColorcategory()));
	 }
*/	 
	 @Test
	 public void testUpdateItemWithNewFeatureByJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        //JSONObject params = new JSONObject(jStringWithNewFeature);            
	        //imsService.updateItem(JsonUtil.toObjectNode(params));
	        imsService.updateItem(JsonUtil.jsonStringToPOJO(jStringWithNewFeature));
	        Ims item = imsService.getItem(id);
	        assertEquals("Second", item.getNewFeature().getGrade().getDescription());
	        assertEquals("Better", item.getNewFeature().getStatus().getDescription());
	        
	        assertEquals("Red Body", item.getNewFeature().getBody().getDescription());
	        assertEquals("Tumbled", item.getNewFeature().getEdge().getDescription());
	        assertEquals("Drop", item.getNewFeature().getMpsCode().getDescription());
	        
	        assertEquals("Wood", item.getNewFeature().getDesignLook().getDescription());
	        assertEquals("Modern", item.getNewFeature().getDesignStyle().getDescription());
	        assertEquals("Silk", item.getNewFeature().getSurfaceApplication().getDescription());
	        assertEquals("Cross Cut", item.getNewFeature().getSurfaceType().getDescription());
	        assertEquals("Antiquated", item.getNewFeature().getSurfaceFinish().getDescription());
	        assertEquals(new Integer("4"), item.getNewFeature().getWarranty());
	        assertEquals("2", item.getNewFeature().getRecommendedGroutJointMin());
	        assertEquals("3", item.getNewFeature().getRecommendedGroutJointMax());
	        //assertEquals(new Date(), item.getImsNewFeature().getLastModifiedDate());
	 }
	 
	 @Test
	 public void testUpdateItemWithUnitAndVendorJsonObject2() throws Exception {
	        System.out.println("testUpdateItemWithUnitAndVendor: ");
	        //JSONObject params = new JSONObject(jStringWithUnitAndVendor);
	        //JSONObject params = new JSONObject(jStringWithNewVendorSystem3);
	        //imsService.updateItem(JsonUtil.toObjectNode(params));
	        imsService.updateItem(JsonUtil.jsonStringToPOJO(jStringWithNewVendorSystem3));
	 }       
	 @Test
	 public void testUpdateItemWithUnitAndVendorJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithUnitAndVendor: ");
	        //JSONObject params = new JSONObject(jStringWithUnitAndVendor);
	        //JSONObject params = new JSONObject(jStringWithNewVendorSystem);
	        //imsService.updateItem(JsonUtil.toObjectNode(params));
	        imsService.updateItem(JsonUtil.jsonStringToPOJO(jStringWithNewVendorSystem));
	        
	        Ims item = imsService.getItem(id);
	        assertEquals("PCS", item.getUnits().getBaseunit());
	        //assertEquals(Character.valueOf('Y'), item.getUnits().getBaseisstdsell());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getBaseisstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getBaseisfractqty());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getBaseispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getBaseupc());
	        assertEquals(new BigDecimal("0.0000"), item.getUnits().getBasevolperunit());
	        assertEquals(new BigDecimal("4.2000"), item.getUnits().getBasewgtperunit());
	           
	        assertEquals("CTN", item.getUnits().getUnit1unit());
	        assertEquals(Float.valueOf("4.0"), item.getUnits().getUnit1ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isfractqty());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getUnit1ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit1upc());
	        assertEquals(new BigDecimal("17.4000"), item.getUnits().getUnit1wgtperunit());
	        
	        assertEquals("PLT", item.getUnits().getUnit2unit());
	        assertEquals(Float.valueOf("240.0"), item.getUnits().getUnit2ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit2upc());
	        assertEquals(new BigDecimal("1070.0000"), item.getUnits().getUnit2wgtperunit());
	        
	        assertEquals("", item.getUnits().getUnit3unit());
	        assertEquals(Float.valueOf("0"), item.getUnits().getUnit3ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit3upc());
	        //assertEquals(new BigDecimal("0"), item.getUnits().getUnit3wgtperunit());
	        
	        assertEquals("", item.getUnits().getUnit4unit());
	        assertEquals(Float.valueOf("0"), item.getUnits().getUnit4ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit4upc());
	        //assertEquals(new BigDecimal("0"), item.getUnits().getUnit4wgtperunit());

	        System.out.println("newly Updated Item id  = " + id);
	 }
	 
	/* @Test
	 public void testUpdateItemWithNewNotesJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithNewNotes: ");
	        JSONObject params = new JSONObject(jStringWithNewNotes);
	        imsService.updateItem(JsonUtil.toObjectNode(params));
	        
	        System.out.println("newly Updated Item id  = " + id);
	        Ims item = imsService.getItem(id);
	        //for(Note note : item.getNewNoteSystem()){
	            //assertTrue(note.g"First", item.getNewNoteSystem());
	        //}    
	 }
	 */
	 @Test
	 public void testUpdateItemWithNewIconSystemJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithNewIcon: ");
	        //JSONObject params = new JSONObject(jStringWithNewIcons);
	        //imsService.updateItem(JsonUtil.toObjectNode(params));
	        imsService.updateItem(JsonUtil.jsonStringToPOJO(jStringWithNewIcons));
	        System.out.println("newly Updated Item id  = " + id);
	        Ims item = imsService.getItem(id);
	        IconCollection icon = item.getIconDescription();
	        assertEquals("USA", icon.getMadeInCountry().getDescription());
	        assertEquals("false", icon.getExteriorProduct());
	        assertEquals("false", icon.getAdaAccessibility());
	        assertEquals("false", icon.getThroughColor());
	        assertEquals("true", icon.getColorBody());
	        assertEquals("false", icon.getInkJet());
	        assertEquals("true", icon.getGlazed());
	        assertEquals("false", icon.getUnglazed());
	        assertEquals("true", icon.getRectifiedEdge());
	        assertEquals("false", icon.getChiseledEdge());
	        assertEquals("false", icon.getRecycled());
	        assertEquals("true", icon.getPostRecycled());
	        assertEquals("false", icon.getPreRecycled());
	        assertEquals("true", icon.getLeadPoint());
	        assertEquals("false", icon.getGreenFriendly());
	        assertEquals("true", icon.getCoefficientOfFriction());
	      
	        /* 0 - Made in Italy
			* 1 - Outdoor
			* 2 - Made in USA
			* 3 - ADA
			* 4 - Thru Color
			* 5 - Inkjet
			* 6 - Recycled
			* 7 - Color Body
			* 8 - Glazed
			* 9 - Rectified
			* 10 - Unglazed
			* 11 - Post Recycled
			* 12 - Pre Recycled
			* 13 - Made in China
			* 14 - Made in Turkey
			* 15 - Made in Mexico
			* 16 - Coefficient of Friction
			* 17 - Chiseled Edge
			* 18 - Unused
			* 19 - Unused*/
	        //assertEquals("NNNNNNNYYYNYNYNNYNNN", ImsDataUtil.convertIconCollectionToLegancyIcons(icon));
	        assertEquals(item.getIconsystem(), ImsDataUtil.convertIconCollectionToLegancyIcons(icon));
	        
	 }
	 
	 @Test
	 public void testUpdateItemWithLagecyIconJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithNewIcon: ");
	        //JSONObject params = new JSONObject(jStringWithLagecyIcon);
	        //imsService.updateItem(JsonUtil.toObjectNode(params));
	        imsService.updateItem(JsonUtil.jsonStringToPOJO(jStringWithLagecyIcon));
	        
	        System.out.println("newly Updated Item id  = " + id);
	        Ims item = imsService.getItem(id);
	        IconCollection icon = item.getIconDescription();
	        assertEquals("China", icon.getMadeInCountry().getDescription());
	        assertEquals("No", icon.getExteriorProduct());
	        assertEquals("No", icon.getAdaAccessibility());
	        assertEquals("No", icon.getThroughColor());
	        assertEquals("Yes", icon.getColorBody());
	        assertEquals("No", icon.getInkJet());
	        assertEquals("Yes", icon.getGlazed());
	        assertEquals("No", icon.getUnglazed());
	        assertEquals("Yes", icon.getRectifiedEdge());
	        assertEquals("No", icon.getChiseledEdge());
	        assertEquals("No", icon.getRecycled());
	        assertEquals("Yes", icon.getPostRecycled());
	        assertEquals("No", icon.getPreRecycled());
	        assertEquals("No", icon.getLeadPoint());
	        assertEquals("No", icon.getGreenFriendly());
	        assertEquals("Yes", icon.getCoefficientOfFriction());
	      
	        /* 0 - Made in Italy
			* 1 - Outdoor
			* 2 - Made in USA
			* 3 - ADA
			* 4 - Thru Color
			* 5 - Inkjet
			* 6 - Recycled
			* 7 - Color Body
			* 8 - Glazed
			* 9 - Rectified
			* 10 - Unglazed
			* 11 - Post Recycled
			* 12 - Pre Recycled
			* 13 - Made in China
			* 14 - Made in Turkey
			* 15 - Made in Mexico
			* 16 - Coefficient of Friction
			* 17 - Chiseled Edge
			* 18 - Unused
			* 19 - Unused*/
	        assertEquals("NNNNNNNYYYNYNYNNYNNN", ImsDataUtil.convertIconCollectionToLegancyIcons(icon));
	 }

	 //---------- Item and associations ----------------//
	 @Test
	 public void testUpdateItemWithAllImsAndAssociationsByJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        //JSONObject params = new JSONObject(jStringFullItemAndAssociationInfo);
	        //imsService.updateItem(JsonUtil.toObjectNode(params));
	        imsService.updateItem(JsonUtil.jsonStringToPOJO(jStringFullItemAndAssociationInfo));
	        	        
	        Ims item = imsService.getItem(id);
	        
	        //series
	        assertEquals("Athena", item.getSeries().getSeriesname());
	        assertEquals("Ash", item.getSeries().getSeriescolor());
	   
	        //material
	        assertEquals("Porcelain", item.getMaterial().getMaterialtype());
	        assertEquals("CTSRC", item.getMaterial().getMaterialclass());
	        assertEquals("FW", item.getMaterial().getMaterialstyle());
	        assertEquals("Trim", item.getMaterial().getMaterialcategory());
	        
	        //dimensions
	        assertEquals("11-13/16", item.getDimensions().getLength());
	        assertEquals("11-13/16", item.getDimensions().getWidth());
	        assertEquals("3/8", item.getDimensions().getThickness());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominallength());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominalwidth());
	        assertEquals("E", item.getDimensions().getSizeunits());
	        assertEquals("E", item.getDimensions().getThicknessunit());
	        
	        //price
	        assertEquals("18.3800", item.getPrice().getListprice().toString());
	        assertEquals("18.3800", item.getPrice().getSellprice().toString());
	        
	        //Units
	        assertEquals("SHT", item.getUnits().getBaseunit());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getBaseisstdsell());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getBaseisstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getBaseisfractqty());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getBaseispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getBaseupc());
	        assertEquals(new BigDecimal("0.0000"), item.getUnits().getBasevolperunit());
	        assertEquals(new BigDecimal("4.2000"), item.getUnits().getBasewgtperunit());
	           
	        assertEquals("CTN", item.getUnits().getUnit1unit());
	        assertEquals(Float.valueOf("4.0"), item.getUnits().getUnit1ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isfractqty());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getUnit1ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit1upc());
	        assertEquals(new BigDecimal("17.4000"), item.getUnits().getUnit1wgtperunit());
	        
	        assertEquals("PLT", item.getUnits().getUnit2unit());
	        assertEquals(Float.valueOf("240.0"), item.getUnits().getUnit2ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit2upc());
	        assertEquals(new BigDecimal("1070.0000"), item.getUnits().getUnit2wgtperunit());
	        
	        assertEquals("", item.getUnits().getUnit3unit());
	        assertEquals(Float.valueOf("0"), item.getUnits().getUnit3ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit3upc());
	        //assertEquals(new BigDecimal("0"), item.getUnits().getUnit3wgtperunit());
	        
	        assertEquals("", item.getUnits().getUnit4unit());
	        assertEquals(Float.valueOf("0"), item.getUnits().getUnit4ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit4upc());
	        //assertEquals(new BigDecimal("0"), item.getUnits().getUnit4wgtperunit());
	        //test spec
		    assertEquals(Float.valueOf("0.5"), item.getTestSpecification().getWaterabsorption());
	        assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getScratchresistance());
	        assertEquals(Float.valueOf("0.7"), item.getTestSpecification().getPeiabrasion());
	        assertEquals(Float.valueOf("0.8"), item.getTestSpecification().getScofwet());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getScofdry());
	        //assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getBreakingstrength());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getDcof());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getMoh());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getPreconsummer());
	        assertEquals(Float.valueOf("0.0"), item.getTestSpecification().getPosconsummer());
			//usage
		    //assertEquals("[FR,WR,CR,SR,PR,FL,WL,CL,SL,PL,FC,WC,CC,SC,PC]", item.getUsage());
	      	 
	        //assertEquals("BEIGE".toUpperCase(), item.getColorhues().get(0));
	        assertEquals("ATHENA", item.getItemcategory());
	        assertEquals("Italy", item.getCountryorigin());
	        assertEquals("N", item.getInactivecode());
	        assertEquals("2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)", item.getItemdesc().getFulldesc());    
		    //assertEquals("[BEIGE, YELLOW]", item.getColorhues().toString());
		    assertEquals("BEIGE:YELLOW", item.getColorcategory());
		    //assertTrue(item.getColorhues().contains("BEIGE"));
		    for(ColorHue ch : item.getColorhues()){
		    	assertTrue(ch.getColorHue().equals("BEIGE") || ch.getColorHue().equals("YELLOW"));
		    }
		    assertEquals("F", (item.getItemtypecode()));
		    //assertEquals("test", item.getType());
		    assertEquals("C", item.getAbccode());
		    assertEquals("T", item.getTaxclass());
		    assertEquals("ALICIAB", item.getPurchasers().getPurchaser());
		    assertEquals("GFIL", item.getPurchasers().getPurchaser2());
		    assertEquals("V2", item.getShadevariation());
			
		    //application
		    assertEquals("FR:WR:CR:SR:PR", item.getApplications().getResidential());
	        assertEquals("FL:WL:CL:SL:PL", item.getApplications().getLightcommercial());
	        assertEquals("FC:WC:CC:SC:PC", item.getApplications().getCommercial());
			//for(ColorHue hue : item.getNewColorHueSystem()){
			//	System.out.println("hue.getColorDescription() = " + hue.getColorDescription());
			//	assertEquals("red".toUpperCase(), hue.getColorDescription().getDescription().toUpperCase());
	        //item new features
/*	        assertEquals("First", item.getImsNewFeature().getGrade().getDescription());
	        assertEquals("Good", item.getImsNewFeature().getStatus().getDescription());
	        assertEquals("Red Body", item.getImsNewFeature().getBody().getDescription());
	        assertEquals("Tumbled", item.getImsNewFeature().getEdge().getDescription());
	        assertEquals("Drop", item.getImsNewFeature().getMpsCode().getDescription());
	        assertEquals("Wood", item.getImsNewFeature().getDesignLook().getDescription());
	        assertEquals("Modern", item.getImsNewFeature().getDesignStyle().getDescription());
	        assertEquals("Silk", item.getImsNewFeature().getSurfaceApplication().getDescription());
	        assertEquals("Cross Cut", item.getImsNewFeature().getSurfaceType().getDescription());
	        assertEquals("Antiquated", item.getImsNewFeature().getSurfaceFinish().getDescription());
	        assertEquals(new Integer("3"), item.getImsNewFeature().getWarranty());
	        assertEquals("1", item.getImsNewFeature().getRecommendedGroutJointMin());
	        assertEquals("2", item.getImsNewFeature().getRecommendedGroutJointMax());
	        //colorhue
	        for(ColorHue colorHue : item.getColorhues()){
	            assertTrue("BEIGE".equals(colorHue.getColorHue()) || "YELLOW".equals(colorHue.getColorHue()));
	            assertTrue(item.getColorcategory().contains(colorHue.getColorHue()));
	        } 
	        assertTrue("BEIGE:YELLOW".equals(item.getColorcategory()) || "YELLOW:BEIGE".equals(item.getColorcategory()));
	        //icons
	        IconCollection icon = item.getIconDescription();
	        assertEquals("USA", icon.getMadeInCountry().getDescription());
	        assertEquals(true, icon.getAdaAccessibility());
	        assertEquals(false, icon.getThroughColor());
	        assertEquals(true, icon.getColorBody());
	        assertEquals(false, icon.getInkJet());
	        assertEquals(true, icon.getGlazed());
	        assertEquals(false, icon.getUnglazed());
	        assertEquals(true, icon.getRectifiedEdge());
	        assertEquals(false, icon.getChiseledEdge());
	        assertEquals(false, icon.getRecycled());
	        assertEquals(true, icon.getPostRecycled());
	        assertEquals(false, icon.getPreRecycled());
	        assertEquals(true, icon.getleadPoint());
	        assertEquals(false, icon.getgreenFriendly());
	        assertEquals(true, icon.getCoefficientOfFriction());
	*/        System.out.println("newly Updated Item id  = " + id);
	 }
	 
	 
	 
	 
	 String jStringWithBasicInfo = 
			     "{\"itemcode\":\"newItemcode\","
			    + "\"itemcategory\":\"ATHENA\","
	 		    + "\"countryorigin\":\"Italy\","
	 		    + "\"inactivecode\":\"N\","
	 			+ "\"showonwebsite\":\"Y\","
	 			+ "\"itemtypecode\":\"#\","
	 			+ "\"abccode\":\"C\","
	 			+ "\"itemcode2\":\"\","
	 			//+ "\"inventoryitemcode\":\"F\","
	 			+ "\"showonalysedwards\":\"N\","
	 			+ "\"offshade\":\"N\","
	 			+ "\"printlabel\":\" \","
	 			+ "\"taxclass\":\"T\","
	 			+ "\"lottype\":\"\","
	 			+ "\"updatecode\":\"CERA-CRD\",\"directship\":\" \",\"dropdate\":null,\"itemgroupnbr\":0,"
	 			+ "\"priorlastupdated\":\"2014-03-31\","
	 	      	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		+ "\"series\":{\"seriesname\":\"Athena\",\"seriescolor\":\"Ash\"},"
	    		+ "\"material\":{\"materialtype\":\"Porcelain\",\"materialcategory\":\"Trim\",\"materialclass\":\"CTSRC\",\"materialstyle\":\"FW\",\"materialfeature\":\"\"},"
	    		+ "\"shadevariation\":\"V2\","
	     		+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0},"
	    		+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":0.0000,\"priorsellprice\":14.7000,\"tempprice\":0.0000,\"tempdatefrom\":null,\"tempdatethru\":null,\"priorlistprice\":0.0000},"
	    		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
	    		+ "\"testSpecification\":{\"waterabsorption\":0.1,\"scratchresistance\":0.2,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.3,\"scofwet\":0.4,\"scofdry\":0.5,\"breakingstrength\":6,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.7,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.8,\"leadpoint\":\"N\",\"preconsummer\":0.9,\"posconsummer\":0.11},"
	    		+ "\"relateditemcodes\":null,"
	    		+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
	    		+ "\"packaginginfo\":{\"boxPieces\":4.0,\"boxSF\":0.0,\"boxWeight\":16.8,\"palletBox\":60.0,\"palletSF\":0.0,\"palletWeight\":1007.99994},"
	     		+ "\"productline\":\"\","
	    		+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
	       		+ "\"cost\":{\"cost1\":0.0000,\"priorcost\":0.0000},"
	    		+ "\"priorVendor\":null}";
	 
	 String jStringWithColorHues = 
		     "{\"itemcode\":\"newItemcode\","
 			+ "\"colorhues\":[\"GREEN\"],"
    		+ "}";
 
	 String jStringWithMultipleColorHues = 
		     "{\"itemcode\":\"newItemcode\","
		    + "\"itemcategory\":\"ATHENA\","
 		    + "\"countryorigin\":\"Italy\","
 		    + "\"inactivecode\":\"N\","
 			+ "\"showonwebsite\":\"Y\","
 			+ "\"itemtypecode\":\"#\","
 			+ "\"abccode\":\"C\","
 			+ "\"itemcode2\":\"\","
 			+ "\"colorhues\":[\"PINK\", \"RED\", \"CLEAR\", \"TAN\"],"
    		+ "\"priorVendor\":null}";
 
	 
	 String jStringWithColorCategory = 
		     "{\"itemcode\":\"newItemcode\","
		    + "\"itemcategory\":\"ATHENA\","
 		    + "\"countryorigin\":\"Italy\","
 		    + "\"inactivecode\":\"N\","
 			+ "\"showonwebsite\":\"Y\","
 			+ "\"itemtypecode\":\"#\","
 			+ "\"abccode\":\"C\","
 			+ "\"itemcode2\":\"\","
 			+ "\"colorcategory\":\"GREEN:WHITE\","
    		+ "\"priorVendor\":null}";
 
	 
	 String jStringWithDimensions = 
			    "{\"itemcode\":\"newItemcode\","
	 			+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,"
	 			+ "\"sizeunits\":\"E\","
	 			+ "\"thickness\":\"3/8\","
	 			+ "\"thicknessunit\":\"E\","
	 			+ "\"length\":\"11-13/16\","
	 			+ "\"width\":\"11-13/16\","
	 			+ "\"nominalthickness\":1.0},"
	       		+ "}";
      
	 String jStringMaterialInfo = 
			   "{\"itemcode\":\"NEWITEMCODE\","
			    + "\"itemcategory\":\"ATHENA\","
			    + "\"countryorigin\":\"Italy\","
			    + "\"inactivecode\":\"N\","
	    		+ "\"material\":"
	    		+ "{\"materialtype\":\"Porcelain\","
	    		+ "\"materialcategory\":\"Trim\","
	    		+ "\"materialclass\":\"CTSRC\","
	    		+ "\"materialstyle\":\"FW\","
	    		+ "\"materialfeature\":\"test\"},"
	    		+ "}";
	 
	 String jStringWithApplicationsInfo = 
		     "{\"itemcode\":\"newItemcode\","
			+ "\"applications\":{"
			+ "\"residential\":\"FR:WR:CR:SR:PR\","
			+ "\"lightcommercial\":\"FL:WL:CL:SL:PL\","
			+ "\"commercial\":\"FC:WC:CC:SC:PC\"},"
       		+ "}";
 
	 String jStringWithUsageInfo = 
		     "{\"itemcode\":\"newItemcode\","
			+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
    		+ "}";

	 String jStringWithNotesInfo = 
		     "{\"itemcode\":\"newItemcode\","
		     + "\"notes\":{\"ponotes\":\"test1 po note\",\"buyernotes\":\"test1 note1\",\"internalnotes\":\"test1 note2\",\"invoicenotes\":\"test1 note3\"},"
		     + "}";
	
	 String jStringWithUnitAndVendor = ""
	 		    + "{\"itemcode\":\"newItemcode\","
	     		+ "\"units\":{\"stdunit\":\"SHT\",\"stdratio\":1.0,\"ordunit\":\"SHT\",\"ordratio\":1.0,"
	    		+ "\"baseunit\":\"SHT\",\"baseisstdsell\":\"Y\",\"baseisstdord\":\"Y\",\"baseisfractqty\":\"N\",\"baseispackunit\":\"Y\",\"baseupc\":0,\"baseupcdesc\":\"\",\"basevolperunit\":0.0000,\"basewgtperunit\":4.2000,"
	    		+ "\"unit1unit\":\"CTN\",\"unit1ratio\":4.0,\"unit1isstdsell\":\"N\",\"unit1isstdord\":\"N\",\"unit1isfractqty\":\"N\",\"unit1ispackunit\":\"Y\",\"unit1upc\":0,\"unit1upcdesc\":\"\",\"unit1wgtperunit\":17.4000,"
	    		+ "\"unit2unit\":\"PLT\",\"unit2ratio\":240.0,\"unit2isstdsell\":\"N\",\"unit2isstdord\":\"N\",\"unit2isfractqty\":\"N\",\"unit2ispackunit\":\"N\",\"unit2upc\":0,\"unit2upcdesc\":\"\",\"unit2wgtperunit\":1070.0000,"
	    		+ "\"unit3unit\":\"\",\"unit3ratio\":0.0,\"unit3isstdsell\":\"N\",\"unit3isstdord\":\"N\",\"unit3isfractqty\":\"N\",\"unit3ispackunit\":\"N\",\"unit3upc\":0,\"unit3upcdesc\":\"\",\"unit3wgtperunit\":0.0000,"
	    		+ "\"unit4unit\":\"\",\"unit4ratio\":0.0,\"unit4isstdsell\":\"N\",\"unit4isstdord\":\"N\",\"unit4isfractqty\":\"N\",\"unit4ispackunit\":\"N\",\"unit4upc\":0,\"unit4upcdesc\":\"\",\"unit4wgtperunit\":0.0000},"
	     		+ "\"vendors\":{\"vendornbr\":0,"
	     		+ "\"vendornbr1\":134585,\"vendornbr2\":0,"
	     		+ "\"vendorxrefcd\":\"ATM45\","
	     		+ "\"vendorlistprice\":7.1500,"
	     		+ "\"vendorpriceunit\":\"SHT\","
	     		+ "\"vendorfob\":\"test\","
	     		+ "\"vendordiscpct\":10.0,"
	     		+ "\"vendorroundaccuracy\":1,"
	     		+ "\"vendornetprice\":43.1500,"
	     		+ "\"vendormarkuppct\":7.0,"
	     		+ "\"vendorfreightratecwt\":10.10,"
	     		+ "\"dutypct\":23.0,"
	     		+ "\"leadtime\":60,"
	     		+ "\"vendorlandedbasecost\":43.1500,"
	     		+ "\"vendordiscpct2\":0.0,"
	     		+ "\"vendordiscpct3\":0.0},"
	    		//+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":update test1,\"vendorName2\":null,\"vendorXrefId\":\"ATM41\",\"vendorListPrice\":9.1500,\"vendorNetPrice\":6.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test19\",\"vendorDiscountPct\":16.0,\"vendorPriceRoundAccuracy\":3,\"vendorMarkupPct\":12.0,\"vendorFreightRateCwt\":43.0,\"vendorLandedBaseCost\":9.1500,\"leadTime\":70,\"dutyPct\":2.0,\"version\":null,\"vendorId\":134589},"
	    		//+ "                     {\"vendorOrder\":2,\"vendorName\":update test2,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test2\",\"vendorDiscountPct\":20.0,\"vendorPriceRoundAccuracy\":1,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134586},"
	    		//+ "                     {\"vendorOrder\":3,\"vendorName\":update test3,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test3\",\"vendorDiscountPct\":30.0,\"vendorPriceRoundAccuracy\":1,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134587},"
    		    //+ "                     ],"
	     	    + "}";

	 String jStringWithNewVendorSystem = 
			    "{"
			    + "\"itemcode\":\"newItemcode\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		//+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":\"TestVendor1\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.0500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":134585},"
	    	    
	    		+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":\"test\",\"vendorName2\":null,\"vendorXrefId\":\"ATM41\",\"vendorListPrice\":5.1500,\"vendorNetPrice\":7.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test\",\"vendorDiscountPct\":10.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":11.0,\"vendorFreightRateCwt\":10.0,\"vendorLandedBaseCost\":3.1500,\"leadTime\":60,\"dutyPct\":30.0,\"id\":134585},"
	    		+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":6.1500,\"vendorNetPrice\":7.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test\",\"vendorDiscountPct\":20.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":10.0,\"vendorFreightRateCwt\":12.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":20.0,\"id\":271520},"
	    		+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":7.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test\",\"vendorDiscountPct\":30.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":10.0,\"vendorFreightRateCwt\":13.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":10.0,\"id\":453680},"
	    		 + "],"
	    		+ "}";

	 
	 String jStringWithNewFeature = 
			     "{\"itemcode\":\"newItemcode\","
			    + "\"itemcategory\":\"ATHENA\","
			    + "\"countryorigin\":\"Italy\","
			    + "\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"update 2x2 Athena Mosaic on 12x12 Sheet \",\"itemdesc1\":\" update 2x2 Athena Mosaic on 12x12\"},"
	     		+ "\"newFeature\":{"
	     		   + "\"grade\":\"Second\","
	     		   + "\"status\":\"BETTER\","
	     		   + "\"body\":\"Red_Body\","
	     		   + "\"edge\":\"Tumbled\","
	     		   + "\"mpsCode\":\"Drop\","
	     		   + "\"designLook\":\"Wood\","
	     		   + "\"designStyle\":\"Modern\","
	     		   + "\"surfaceApplication\":\"Silk\","
	     		   + "\"surfaceType\":\"Cross_Cut\","
	     		   + "\"surfaceFinish\":\"Antiquated\","
	     		   + "\"warranty\":4,"
	     		   + "\"recommendedGroutJointMin\":\"2\","
	     		   + "\"recommendedGroutJointMax\":\"3\","
	     		   + "\"launchedDate\":null,"
	     		   + "\"droppedDate\":null"
	     		   + "},"
	    		+ "}";
	 
	 //String jStringWithNewNotes = "{\"itemcode\":\"newItemcode3\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	 //     		//+ "\"notes\":{\"ponotes\":\"test po note\",\"notes1\":\"test notes1\",\"notes2\":\"test note2\",\"notes3\":\"test notes3\"},"
	 //   		+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"text\":\"update test Po note\",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"text\":\"update test buyer note\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"text\":\"update test invoice note\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"text\":\"update test additional note\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"text\":\" update test internal note\",\"lastModifiedDate\":null}],"
	 //      		+ "\"priorVendor\":null}";
	
	 String jStringWithIcons = "{\"itemcode\":\"newItemcode\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	      		//+ "\"iconsystem\":\"NYNYNNNNYNNYNYNNYNYN\","
	       		+ "\"iconDescription\":{\"madeInCountry\":\"China\",\"exteriorProduct\":false,\"adaAccessibility\":false,\"throughColor\":true,\"colorBody\":false,\"inkJet\":false,\"glazed\":true,\"unglazed\":false,\"rectifiedEdge\":true,\"chiseledEdge\":false,\"versaillesPattern\":true,\"recycled\":false,\"postRecycled\":true,\"preRecycled\":false,\"leadPoint\":true,\"greenFriendly\":false,\"coefficientOfFriction\":true},"
	       		+ "}";
	 
	 String jStringWithNewIcons = "{\"itemcode\":\"newItemcode\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	      	   	+ "\"iconDescription\":"
	       		+ "{"
	       		+ "\"madeInCountry\":\"USA\","
	       		+ "\"exteriorProduct\":false,"
	       		+ "\"adaAccessibility\":false,"
	       		+ "\"throughColor\":false,"
	       		+ "\"colorBody\":true,"
	       		+ "\"inkJet\":false,"
	       		+ "\"glazed\":true,"
	       		+ "\"unglazed\":false,"
	       		+ "\"rectifiedEdge\":true,"
	       		+ "\"chiseledEdge\":false,"
	       		+ "\"versaillesPattern\":true,"
	       		+ "\"recycled\":false,"
	       		+ "\"postRecycled\":true,"
	       		+ "\"preRecycled\":false,"
	       		+ "\"leadPoint\":true,"
	       		+ "\"greenFriendly\":false,"
	       		+ "\"coefficientOfFriction\":true},"
	       		+ "}";
	
	 String jStringWithLagecyIcon = "{\"itemcode\":\"newItemcode\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		+ "\"iconsystem\":\"NNNNNNNYYYNYNYNNYNNN\","
	      		+ "}";
	 
	 String jStringWithPriceAndCost = 
			   "{\"itemcode\":\"newItemcode\","
	    		//+ "\"price\":"
	    		//+ "{\"sellprice\":20.3900,"
	    		//+ "\"pricegroup\":\"3\","
	     		//+ "\"tempprice\":2.1000,"
	    		//+ "\"tempdatefrom\":2014-03-31,"
	    		//+ "\"tempdatethru\":null,\"priorlistprice\":1.0000,"
	    		//+ "\"listpricemarginpct\":10.0,"
	    		//+ "},"
	    		+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"2\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":10.0000,\"priorsellprice\":14.7000,\"tempprice\":16.0000,\"tempdatefrom\":2014-06-31,\"tempdatethru\":2014-08-31,\"priorlistprice\":10.0000},"
	    		+ "}";
	 	 
	 String jStringColorCategory = 
			   "{\"itemcode\":\"NEWITEMCODE\","
			   	+ "\"itemdesc\":{\"fulldesc\":\"update 2x2 Athena Mosaic on 12x12 Sheet\",\"itemdesc1\":\"update 2x2 Athena Mosaic on 12x12 \"},"
	    		+ "\"colorcategory\":\"RED:BLACK\","
	    		+ "}";
	 
	 String jStringDescription = 
			   "{\"itemcode\":\"NEWITEMCODE\","
			   + "\"itemdesc\":{\"itemdesc1\":\"update 2x2 Athena Mosaic on 12x12 \"},"		   
	    		+ "}";
	
	 
	 String jStringColorHues = 
			   "{\"itemcode\":\"TEST3\","
			    + "\"itemcategory\":\"ATHENA\","
			    + "\"countryorigin\":\"Italy\","
			    + "\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"update 2x2 Athena Mosaic on 12x12 Sheet\",\"itemdesc1\":\"update 2x2 Athena Mosaic on 12x12 \"},"
	    		+ "\"series\":{\"seriesname\":\"update Athena\",\"seriescolor\":\"update Ash\"},"
	    		+ "\"colorhues\":[\"BLACK\", \"RED\", \"YELLOW\"],"
	    		+ "}";
	 
	 String jStringWithNewNotes = "{\"itemcode\":\"newItemcode\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	      		//+ "\"notes\":{\"ponotes\":\"test po note\",\"notes1\":\"test notes1\",\"notes2\":\"test note2\",\"notes3\":\"test notes3\"},"
	    		+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"note\":\"test Po note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"note\":\"test buyer note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"note\":\"test invoice note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"note\":\"test additional note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"note\":\"test internal note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null}],"
	       		+ "}";
	 
	 String jStringWithTestSpecs = 
			   "{\"itemcode\":\"NEWITEMCODE\","
			    + "\"testSpecification\":{\"waterabsorption\":0.5,\"scratchresistance\":0.6,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.7,\"scofwet\":0.8,\"scofdry\":0.0,\"breakingstrength\":0,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.0,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.0,\"leadpoint\":\"N\",\"preconsummer\":0.0,\"posconsummer\":0.0},"
				+ "}";
	
	 String jStringFullItemInfo = 
			   "{\"itemcode\":\"NEWITEMCODE\","
			    + "\"itemcategory\":\"ATHENA\","
			    + "\"countryorigin\":\"Italy\","
			    + "\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"update 2x2 Athena Mosaic on 12x12 Sheet\",\"itemdesc1\":\"update 2x2 Athena Mosaic on 12x12 \"},"
	    		+ "\"series\":{\"seriesname\":\"update Athena\",\"seriescolor\":\"update Ash\"},"
	    		+ "\"material\":{\"materialtype\":\"update Porcelain\",\"materialcategory\":\"Trim\",\"materialclass\":\"CTSRC\",\"materialstyle\":\"FW\",\"materialfeature\":\"\"},"
	    		+ "\"showonwebsite\":\"Y\","
	    		+ "\"itemtypecode\":\"F\","
	    		+ "\"abccode\":\"C\","
	    		+ "\"itemcode2\":\"\","
	    		+ "\"inventoryitemcode\":\"\","
	    		+ "\"showonalysedwards\":\"N\","
	    		+ "\"offshade\":\"N\","
	    		+ "\"printlabel\":\" \","
	    		+ "\"taxclass\":\"T\","
	    		+ "\"lottype\":\"\","
	    		+ "\"updatecode\":\"CERA-CRD\","
	    		+ "\"directship\":\"Y\","
	    		+ "\"dropdate\":null,\"itemgroupnbr\":0,\"priorlastupdated\":\"2014-03-31\","
		    	+ "\"shadevariation\":\"V2\","
	    		+ "\"colorhues\":[\"BEIGE\"],"
	    		+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0},"
	    		+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"2\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":0.0000,\"priorsellprice\":14.7000,\"tempprice\":0.0000,\"tempdatefrom\":null,\"tempdatethru\":null,\"priorlistprice\":0.0000},"
	    		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
	    		+ "\"testSpecification\":{\"waterabsorption\":0.5,\"scratchresistance\":0.6,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.7,\"scofwet\":0.8,\"scofdry\":0.0,\"breakingstrength\":0,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.0,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.0,\"leadpoint\":\"N\",\"preconsummer\":0.0,\"posconsummer\":0.0},"
	    		+ "\"relateditemcodes\":null,"
	    		+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
	    		+ "\"packaginginfo\":{\"boxPieces\":4.0,\"boxSF\":0.0,\"boxWeight\":16.8,\"palletBox\":60.0,\"palletSF\":0.0,\"palletWeight\":1007.99994},"
	    		+ "\"productline\":\"\","
	    		+ "\"iconsystem\":\"YNNYNNNNNNNNNNNNNNNN\","
	    		+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
	    		+ "\"units\":{\"stdunit\":\"SHT\",\"stdratio\":1.0,\"ordunit\":\"SHT\",\"ordratio\":1.0,"
	    		+ "\"baseunit\":\"SHT\",\"baseisstdsell\":\"Y\",\"baseisstdord\":\"Y\",\"baseisfractqty\":\"N\",\"baseispackunit\":\"Y\",\"baseupc\":0,\"baseupcdesc\":\"\",\"basevolperunit\":0.0000,\"basewgtperunit\":4.2000,"
	    		+ "\"unit1unit\":\"CTN\",\"unit1ratio\":4.0,\"unit1isstdsell\":\"N\",\"unit1isstdord\":\"N\",\"unit1isfractqty\":\"N\",\"unit1ispackunit\":\"Y\",\"unit1upc\":0,\"unit1upcdesc\":\"\",\"unit1wgtperunit\":17.4000,"
	    		+ "\"unit2unit\":\"PLT\",\"unit2ratio\":240.0,\"unit2isstdsell\":\"N\",\"unit2isstdord\":\"N\",\"unit2isfractqty\":\"N\",\"unit2ispackunit\":\"N\",\"unit2upc\":0,\"unit2upcdesc\":\"\",\"unit2wgtperunit\":1070.0000,"
	    		+ "\"unit3unit\":\"\",\"unit3ratio\":0.0,\"unit3isstdsell\":\"N\",\"unit3isstdord\":\"N\",\"unit3isfractqty\":\"N\",\"unit3ispackunit\":\"N\",\"unit3upc\":0,\"unit3upcdesc\":\"\",\"unit3wgtperunit\":0.0000,"
	    		+ "\"unit4unit\":\"\",\"unit4ratio\":0.0,\"unit4isstdsell\":\"N\",\"unit4isstdord\":\"N\",\"unit4isfractqty\":\"N\",\"unit4ispackunit\":\"N\",\"unit4upc\":0,\"unit4upcdesc\":\"\",\"unit4wgtperunit\":0.0000},"
	     		+ "\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorlandedbasecost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},"
	    		//+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134585},"
	    		//+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134586},"
	    		//+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134587},"
    		    //+ "                     ],"
	        	//,"\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendornbr3\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorLandedBaseCost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},
	    		//+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134585},"
	    		//+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134586},"
	    		//+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134587},"
	    		//+ "                     ],"
	    		//+ "\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendornbr3\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorlandedbasecost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},"
	    		+ "\"cost\":{\"cost1\":0.0000,\"priorcost\":0.0000},"
	    		//+ "\"imsNewFeature\":{\"grade\":\"First\",\"status\":\"Good\",\"body\":\"Red_Body\",\"edge\":\"Tumbled\",\"mpsCode\":\"Drop\",\"designLook\":\"Wood\",\"designStyle\":\"Modern\",\"surfaceApplication\":\"Silk\",\"surfaceType\":\"Cross_Cut\",\"surfaceFinish\":\"Antiquated\",\"warranty\":3,\"recommendedGroutJointMin\":\"1\",\"recommendedGroutJointMax\":\"2\",\"UpdatedDate\":\"2014-05-14\",\"launchedDate\":null,\"droppedDate\":null,\"lastModifiedDate\":null},"
	    		//+ "\"notes\":{\"ponotes\":\"test po note\",\"notes1\":\"test notes1\",\"notes2\":\"test note2\",\"notes3\":\"test notes3\"},"
	    		//+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"note\":\"test Po note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"note\":\"test buyer note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"note\":\"test invoice note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"note\":\"test additional note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"note\":\"test internal note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null}],"
	    		+ "}";
	 
	 String jStringFullItemInfoWithouUnitAndPrice = 
			   "{\"itemcode\":\"NEWITEMCODE\","
			    + "\"itemcategory\":\"ATHENA\","
			    + "\"countryorigin\":\"Italy\","
			    + "\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"updated 2x2 Athena Mosaic on 12x12 Sheet \",\"itemdesc1\":\"updated 2x2 Athena Mosaic on 12x12\"},"
	    		+ "\"series\":{\"seriesname\":\"Athena\",\"seriescolor\":\"Ash\"},"
	    		+ "\"material\":{\"materialtype\":\"Porcelain\",\"materialcategory\":\"Trim\",\"materialclass\":\"CTSRC\",\"materialstyle\":\"FW\",\"materialfeature\":\"\"},"
	    		+ "\"showonwebsite\":\"Y\","
	    		+ "\"itemtypecode\":\"F\","
	    		+ "\"abccode\":\"C\","
	    		+ "\"itemcode2\":\"\","
	    		+ "\"inventoryitemcode\":\"\","
	    		+ "\"showonalysedwards\":\"N\","
	    		+ "\"offshade\":\"N\","
	    		+ "\"printlabel\":\" \","
	    		+ "\"taxclass\":\"T\","
	    		+ "\"lottype\":\"\","
	    		+ "\"updatecode\":\"CERA-CRD\","
	    		+ "\"directship\":\"Y\","
	    		+ "\"dropdate\":null,"
	    		+ "\"itemgroupnbr\":0,\"priorlastupdated\":\"2014-03-31\","
		    	+ "\"shadevariation\":\"V2\","
	    		+ "\"colorhues\":[\"RED\"],"
	    		+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0},"
	    		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
	    		+ "\"testSpecification\":{\"waterabsorption\":0.5,\"scratchresistance\":0.6,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.7,\"scofwet\":0.8,\"scofdry\":0.0,\"breakingstrength\":0,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.0,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.0,\"leadpoint\":\"N\",\"preconsummer\":0.0,\"posconsummer\":0.0},"
	    		+ "\"relateditemcodes\":null,"
	    		+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
	    		+ "\"productline\":\"\","
	    		+ "\"iconsystem\":\"YNNYNNNNNNNNNNNNNNNN\","
	    		+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
	     		//"\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendornbr3\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorLandedBaseCost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},
	    		//+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134585},"
	    		//+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134586},"
	    		//+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134587},"
  		        //+ "                     ],"
	        	//,"\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendornbr3\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorLandedBaseCost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},
	    		//+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134585},"
	    		//+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134586},"
	    		//+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134587},"
	    		//+ "                     ],"
	    		//+ "\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendornbr3\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorlandedbasecost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},"
	    		+ "\"cost\":{\"cost1\":0.0000,\"priorcost\":0.0000},"
	    		//+ "\"imsNewFeature\":{\"grade\":\"First\",\"status\":\"Good\",\"body\":\"Red_Body\",\"edge\":\"Tumbled\",\"mpsCode\":\"Drop\",\"designLook\":\"Wood\",\"designStyle\":\"Modern\",\"surfaceApplication\":\"Silk\",\"surfaceType\":\"Cross_Cut\",\"surfaceFinish\":\"Antiquated\",\"warranty\":3,\"recommendedGroutJointMin\":\"1\",\"recommendedGroutJointMax\":\"2\",\"UpdatedDate\":\"2014-05-14\",\"launchedDate\":null,\"droppedDate\":null,\"lastModifiedDate\":null},"
	    		//+ "\"notes\":{\"ponotes\":\"test po note\",\"notes1\":\"test notes1\",\"notes2\":\"test note2\",\"notes3\":\"test notes3\"},"
	    		//+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"note\":\"test Po note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"note\":\"test buyer note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"note\":\"test invoice note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"note\":\"test additional note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"note\":\"test internal note\",\"UpdatedDate\":\"2014-05-14\",\"lastModifiedDate\":null}],"
	    		+ "}";
	 
	 
	 String jStringFullItemAndAssociationInfo = 
			    //basic info
			     "{\"itemcode\":\"NEWITEMCODE\","
			    + "\"itemcategory\":\"ATHENA\","
			    + "\"countryorigin\":\"Italy\","
			    + "\"inactivecode\":\"N\","
			    + "\"showonwebsite\":\"Y\","
			    + "\"itemtypecode\":\"F\","
			    + "\"abccode\":\"C\","
			    + "\"itemcode2\":\"test\","
			    + "\"inventoryitemcode\":\"\","
			    + "\"showonalysedwards\":\"N\",\"offshade\":\"N\","
			    + "\"printlabel\":\" \","
			    + "\"taxclass\":\"T\","
			    + "\"lottype\":\"\","
			    + "\"updatecode\":\"CERA-CRD\","
			    + "\"directship\":\" \","
			    + "\"dropdate\":null,"
			    + "\"itemgroupnbr\":0,"
			    + "\"priorlastupdated\":\"2014-03-31\","
		    	+ "\"shadevariation\":\"V2\","
		    	+ "\"productline\":\"\","
		    	//components
		    	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT\"},"
	    		+ "\"series\":{\"seriesname\":\"Athena\",\"seriescolor\":\"Ash\"},"
	    		+ "\"material\":{\"materialtype\":\"Porcelain\",\"materialcategory\":\"Trim\",\"materialclass\":\"CTSRC\",\"materialstyle\":\"FW\",\"materialfeature\":\"\"},"
	    		+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0},"
	    		+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"3\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":0.0000,\"priorsellprice\":14.7000,\"tempprice\":0.0000,\"tempdatefrom\":null,\"tempdatethru\":null,\"priorlistprice\":0.0000},"
	    		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
	    		+ "\"testSpecification\":{\"waterabsorption\":0.5,\"scratchresistance\":0.6,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.7,\"scofwet\":0.8,\"scofdry\":0.0,\"breakingstrength\":0,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.0,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.0,\"leadpoint\":\"N\",\"preconsummer\":0.0,\"posconsummer\":0.0},"
	    		+ "\"relateditemcodes\":null,"
	    		+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
	    		+ "\"packaginginfo\":{\"boxPieces\":4.0,\"boxSF\":0.0,\"boxWeight\":16.8,\"palletBox\":60.0,\"palletSF\":0.0,\"palletWeight\":1007.99994},"
	    		+ "\"cost\":{\"cost1\":0.0000,\"priorcost\":0.0000},"
	       		//+ "\"iconsystem\":\"NNNNNNNNNNNNNNNNNNNN\","
	    		+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
	    		+ "\"units\":{\"stdunit\":\"SHT\",\"stdratio\":1.0,\"ordunit\":\"SHT\",\"ordratio\":1.0,"
	    		           + "\"baseunit\":\"SHT\",\"baseisstdsell\":\"Y\",\"baseisstdord\":\"Y\",\"baseisfractqty\":\"N\",\"baseispackunit\":\"Y\",\"baseupc\":0,\"baseupcdesc\":\"\",\"basevolperunit\":0.0000,\"basewgtperunit\":4.2000,"
	    		           + "\"unit1unit\":\"CTN\",\"unit1ratio\":4.0,\"unit1isstdsell\":\"N\",\"unit1isstdord\":\"N\",\"unit1isfractqty\":\"N\",\"unit1ispackunit\":\"Y\",\"unit1upc\":0,\"unit1upcdesc\":\"\",\"unit1wgtperunit\":17.4000,"
	    		           + "\"unit2unit\":\"PLT\",\"unit2ratio\":240.0,\"unit2isstdsell\":\"N\",\"unit2isstdord\":\"N\",\"unit2isfractqty\":\"N\",\"unit2ispackunit\":\"N\",\"unit2upc\":0,\"unit2upcdesc\":\"\",\"unit2wgtperunit\":1070.0000,"
	    		           + "\"unit3unit\":\"\",\"unit3ratio\":0.0,\"unit3isstdsell\":\"N\",\"unit3isstdord\":\"N\",\"unit3isfractqty\":\"N\",\"unit3ispackunit\":\"N\",\"unit3upc\":0,\"unit3upcdesc\":\"\",\"unit3wgtperunit\":0.0000,"
	    		           + "\"unit4unit\":\"\",\"unit4ratio\":0.0,\"unit4isstdsell\":\"N\",\"unit4isstdord\":\"N\",\"unit4isfractqty\":\"N\",\"unit4ispackunit\":\"N\",\"unit4upc\":0,\"unit4upcdesc\":\"\",\"unit4wgtperunit\":0.0000},"
	    		//+ "\"notes\":{\"ponotes\":\"test po note\",\"notes1\":\"test notes1\",\"notes2\":\"test note2\",\"notes3\":\"test notes3\"},"
	    		//------ associations ------//
	    		+ "\"colorhues\":[\"BEIGE\",\"YELLOW\"],"
		    	//"\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendornbr3\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorLandedBaseCost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},
	    		+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":134585},"
	    		+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":302871},"
	    		+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":529554},"
 		        + "                     ],"
	    		+ "\"newFeature\":{\"grade\":\"Third\",\"status\":\"BEST\",\"body\":\"Red_Body\",\"edge\":\"Tumbled\",\"mpsCode\":\"Drop\",\"designLook\":\"Wood\",\"designStyle\":\"Modern\",\"surfaceApplication\":\"Silk\",\"surfaceType\":\"Cross_Cut\",\"surfaceFinish\":\"Antiquated\",\"warranty\":3,\"recommendedGroutJointMin\":\"1\",\"recommendedGroutJointMax\":\"2\",\"launchedDate\":null,\"droppedDate\":null,\"lastModifiedDate\":null},"
	    		//+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"note\":\"test Po note new \",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"note\":\"test buyer note\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"note\":\"test invoice note\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"note\":\"test additional note\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"note\":\"test internal note\",\"lastModifiedDate\":null}],"
	    		+ "\"iconDescription\":{\"madeInCountry\":\"China\",\"exteriorProduct\":false,\"adaAccessibility\":false,\"throughColor\":false,\"colorBody\":true,\"inkJet\":false,\"glazed\":true,\"unglazed\":false,\"rectifiedEdge\":true,\"chiseledEdge\":false,\"versaillesPattern\":true,\"recycled\":false,\"postRecycled\":true,\"preRecycled\":false,\"leadPoint\":true,\"greenFriendly\":false,\"coefficientOfFriction\":true},"
		        + "}";
	 
	 String jStringWithNewVendorSystem2 = 
			    "{"
			    + "\"itemcode\":\"MKTPCKCRDEPI2\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		//+ "\"vendors\":{\"vendornbr\":null,\"vendornbr1\":0,\"vendornbr2\":null,\"vendorxrefcd\":\"\",\"vendorlistprice\":35.94,\"vendorpriceunit\":\"SET\",\"vendorfob\":\"\",\"vendordiscpct\":0,\"vendorroundaccuracy\":2,\"vendornetprice\":35.94,\"vendormarkuppct\":0,\"vendorfreightratecwt\":0,\"dutypct\":0,\"leadtime\":0,\"vendorlandedbasecost\":35.94,\"vendordiscpct2\":0,\"vendordiscpct3\":0},"
				
	    		+ "\"newVendorSystem\":["
	    		+   "{\"vendorId\":{\"id\":544394},"
	    		//+ "\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"K822751R\","
	    		//+ "\"vendorListPrice\":12.7,\"vendorNetPrice\":12.7,\"vendorPriceUnit\":\"SM\",\"vendorFob\":null,"
	    		//+ "\"vendorDiscountPct\":0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0,\"vendorFreightRateCwt\":0,"
	    		//+ "\"vendorLandedBaseCost\":12.7,\"leadTime\":0,"
	    		//+ "\"dutyPct\":0},"
	    		+ "\"vendorOrder\":1,\"vendorName\":\"null\",\"vendorName2\":null,\"vendorXrefId\":\"ATM41\",\"vendorListPrice\":35.94,\"vendorNetPrice\":7.1500,\"vendorPriceUnit\":\"SET\",\"vendorFob\":\"\",\"vendorDiscountPct\":0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":11.0,\"vendorFreightRateCwt\":0,\"vendorLandedBaseCost\":35.94,\"leadTime\":60,\"dutyPct\":30.0},"
	    		//+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":6.1500,\"vendorNetPrice\":7.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test\",\"vendorDiscountPct\":20.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":10.0,\"vendorFreightRateCwt\":12.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":20.0,\"vendorId\":{\"id\":0}},"
	    		//+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":7.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test\",\"vendorDiscountPct\":30.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":10.0,\"vendorFreightRateCwt\":13.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":10.0,\"vendorId\":{\"id\":null}},"
	    		//+ "                     {\"vendorOrder\":null,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":null,\"vendorListPrice\":0,\"vendorNetPrice\":null,\"vendorPriceUnit\":null,\"vendorFob\":\"\",\"vendorDiscountPct\":null,\"vendorPriceRoundAccuracy\":null,\"vendorMarkupPct\":0,\"vendorFreightRateCwt\":0,\"vendorLandedBaseCost\":0,\"leadTime\":null,\"dutyPct\":null,\"vendorId\":{\"id\":null}},"
        		 + "],"
	    		+ "}";
	 String jStringWithNewVendorSystem3 =
		     "{"
		     	+ "\"itemcode\":\"ELMARAGT1224\","
		     	+ "\"itemcategory\":\"ARARAT\","
		     	+ "\"countryorigin\":\"USA\","
		     	//+ "\"inactivecode\":\"Y\","
		     	//+ "\"shadevariation\":\"V1\","
		     	//+ "\"colorcategory\":\"GRAY\","
		     	//+ "\"showonwebsite\":\"N\",\"iconsystem\":\"NNNNNNNNNNYNNNNNNNNN\",\"itemtypecode\":\"#\",\"abccode\":\"D",\"itemcode2\":"",\"inventoryitemcode\":"","
		     //	+ "\"showonalysedwards\":\"N\",\"offshade\":\"N\",\"printlabel\":"",\"taxclass\":\"T\",\"lottype\":"",\"updatecode\":\"CERA-TCR\",\"directship\":null,\"dropdate\":null,"
		     	//+ "\"productline\":"",\"itemgroupnbr\":0,\"priorlastupdated\":1396137600000,\"itemdesc\":{\"fulldesc\":\"12x24 Ararat Field Tile Rectified Porcelain Matte Graphite\","
		     	//+ "\"itemdesc1\":\"gwegb\"},"
		     	//+ "\"series\":{\"seriesname\":"Ararat\",\"seriescolor\":\"grey\"},\"material\":{\"materialtype\":\"Porcelain\",\"materialcategory\":\"Tile\",\"materialclass\":\"CTSRC\","
		     	//+ "\"materialstyle\":\"Beak\",\"materialfeature\":\"RE\"},"
		     	//+ "\"dimensions\":{\"nominallength\":24,\"nominalwidth\":12,\"sizeunits\":\"E\",\"thickness\":\"5\/16\",\"thicknessunit\":\"E\",\"length\":\"23-5\/16\",\"width\":\"11-1\/2\","
		     	//+ "\"nominalthickness\":0},"
		     	//+ "\"price\":{\"listprice\":4.48,\"sellprice\":4.48,\"pricegroup\":\"",\"priceunit\":"S\/F",\"sellpricemarginpct\":2,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0,"
		     	//	+ "\"minmarginpct\":15,\"futuresell\":0,\"tempprice\":1.99,\"tempdatefrom\":1406703600000,\"tempdatethru\":1419840000000,\"priorlistprice\":5.75,\"priorsellprice\":4.55,"default":false},"
		     	//+ "\"testSpecification\":{\"waterabsorption\":null,\"scratchresistance\":null,\"frostresistance\":null,\"chemicalresistance\":null,\"peiabrasion\":null,\"scofwet\":null,"
		     	//+ "\"scofdry\":null,\"breakingstrength\":null,\"scratchstandard\":"",\"breakingstandard\":"","
		     	//+ "\"restricted\":\"N\",\"warpage\":null,\"wedging\":null,\"dcof\":null,\"thermalshock\":null,\"bondstrength\":"","
		     	//+ "\"greenfriendly\":\"N\",\"moh\":null,\"leadpoint\":\"N\",\"preconsummer\":0,\"posconsummer\":0},"
		     	//+ ""purchasers":{"purchaser":"terth","purchaser2":"GFIL"},"
		     	//+ ""packaginginfo":{"boxPieces":6,"boxSF":11.62115,"boxWeight":43.199997,"palletBox":44,"palletSF":511.3306,"palletWeight":1900.7999},"
		     	//+ ""notes":{"ponotes":"","buyernotes":"","invoicenotes":"","internalnotes":""},"
		   		//+ ""applications":{"residential":"FR:WR:CR:SR:PR","lightcommercial":"WL:CL:SL:PL","commercial":"WC:CC:SC:PC","residentialList":null,"lightcommercialList":null,"commercialList":null},"usage":["FR","WR","SR","PR","CR","WL","SL","PL","CL","WC","SC","PC","CC"],"
	/*	   		+ "\"units\":{"
		   		//+ "\"stdunit\":\"S/F\","
		   		//+ "\"stdratio\":0.5163,"
		   		//+ "\"ordunit\":\"S/M\","
		   		//+ "\"ordratio\":5.5574,
		   		//+ "\"baseunit\":\"PCS\",\"baseisstdsell\":null,\"baseisstdord\":null,\"baseisfractqty\":null,\"baseispackunit\":\"Y\",\"baseupc\":null,\"baseupcdesc\":\"\",\"basevolperunit\":0,\"basewgtperunit\":7.2,"
		   		+ "\"unit1unit\":\"CTN\",\"unit1ratio\":6,\"unit1isstdsell\":null,\"unit1isstdord\":null,\"unit1isfractqty\":null,"
		   		//+ "\"unit1ispackunit\":\"Y\","
		   		//+ "\"unit1upc\":null,\"unit1upcdesc\":\"\","
		   		+ "\"unit1wgtperunit\":43.8,"
		   		+ "\"unit2unit\":\"PLT\",\"unit2ratio\":264,\"unit2isstdsell\":null,\"unit2isstdord\":null,\"unit2isfractqty\":null,\"unit2ispackunit\":\"Y\",\"unit2upc\":null,\"unit2upcdesc\":\"\",\"unit2wgtperunit\":1954,"
		   		//+ "\"unit3unit\":\"S/M\","
		   		+ "\"unit3ratio\":5.5574,\"unit3isstdsell\":null,\"unit3isstdord\":\"Y\",\"unit3isfractqty\":\"Y\",\"unit3ispackunit\":null,\"unit3upc\":0,\"unit3upcdesc\":\"\",\"unit3wgtperunit\":0,"
		   		//+ "\"unit4unit\":\"S/F\","
		   		+ "\"unit4ratio\":0.5163,\"unit4isstdsell\":\"Y\",\"unit4isstdord\":null,\"unit4isfractqty\":\"Y\",\"unit4ispackunit\":null,\"unit4upc\":0,\"unit4upcdesc\":\"\",\"unit4wgtperunit\":0},"
		*/   									
		   		//+ ""cost":{"cost1":0,"priorcost":0,"futurecost":0,"poincludeinvendorcost":"Y","nonstockcostpct":0,"costrangepct":null},"relateditemcodes":null,"
		   		+ "\"vendors\":{\"vendornbr\":null,\"vendornbr1\":544394,\"vendornbr2\":null,\"vendorxrefcd\":\"K822751R\",\"vendorlistprice\":12.7,\"vendorpriceunit\":\"S/M\",\"vendorfob\":\"\","
		   		+ "\"vendordiscpct\":0,\"vendorroundaccuracy\":2,\"vendornetprice\":12.7,\"vendormarkuppct\":0,\"vendorfreightratecwt\":0,\"dutypct\":0,\"leadtime\":0,\"vendorlandedbasecost\":2.2852,\"vendordiscpct2\":0,\"vendordiscpct3\":0},"
		   		+ "\"newVendorSystem\":"
		   		+ "["
		   		+ "{\"vendorId\":{\"id\":544394},\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"K822751R\",\"vendorListPrice\":12.7,\"vendorNetPrice\":12.7,\"vendorPriceUnit\":\"S/M\",\"vendorFob\":\"\",\"vendorDiscountPct\":0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0,\"vendorFreightRateCwt\":0,\"vendorLandedBaseCost\":12.7,\"leadTime\":0,\"dutyPct\":0},"
		   		+ "{\"vendorId\":{\"id\":null},\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"\",\"vendorListPrice\":0,\"vendorNetPrice\":null,\"vendorPriceUnit\":\"0\",\"vendorFob\":\"\",\"vendorDiscountPct\":null,\"vendorPriceRoundAccuracy\":null,\"vendorMarkupPct\":0,\"vendorFreightRateCwt\":0,\"vendorLandedBaseCost\":0,\"leadTime\":null,\"dutyPct\":null},"
		   		+ "{\"vendorId\":{\"id\":null},\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"\",\"vendorListPrice\":0,\"vendorNetPrice\":null,\"vendorPriceUnit\":\"0\",\"vendorFob\":\"\",\"vendorDiscountPct\":null,\"vendorPriceRoundAccuracy\":null,\"vendorMarkupPct\":0,\"vendorFreightRateCwt\":0,\"vendorLandedBaseCost\":0,\"leadTime\":null,\"dutyPct\":null}"
		   		+ "],"
		   				
		   		//+ ""newFeature":{"grade":"Second","status":"BETTER","body":"Double_Loaded","edge":"Rectified","mpsCode":"New_Product","designLook":"Wood","designStyle":"Contemporary","surfaceApplication":"Ink_Jet","surfaceType":"Abrasive","surfaceFinish":"Antiquated","warranty":3,"recommendedGroutJointMin":"1","recommendedGroutJointMax":"1","createdDate":1423008000000,"launchedDate":null,"droppedDate":null,"lastModifiedDate":null},"iconDescription":{"madeInCountry":"Italy","exteriorProduct":null,"adaAccessibility":null,"throughColor":null,"colorBody":null,"inkJet":null,"glazed":null,"unglazed":"Yes","rectifiedEdge":null,"chiseledEdge":null,"versaillesPattern":null,"recycled":null,"postRecycled":null,"preRecycled":null,"leadPoint":null,"greenFriendly":null,"coefficientOfFriction":null},"colorhues":[{"colorHue":"GRAY"}]}
		   		//+ "],"
	    		+ "}";
	 /*
			 + "itemcode":"MKTPCKCRDEPI6","itemcategory":"PACKOUT","countryorigin":"Italy","inactivecode":"Y","shadevariation":"V1","colorcategory":"CLEAR","showonwebsite":"N","iconsystem":"NNNNNNNNNNNNNNNNNNNN","itemtypecode":"#","abccode":"SO","itemcode2":"","inventoryitemcode":"","showonalysedwards":"N","offshade":"N","printlabel":"","taxclass":"A","lottype":"","updatecode":"ALLD-ALD","directship":null,"dropdate":null,"productline":"","itemgroupnbr":0,"priorlastupdated":null,"itemdesc":{"fulldesc":"Packout CRD Epic 8x40 (1 box)","itemdesc1":"Packout CRD Epic 8x40 (1 box)"},"series":{"seriesname":"Dynasty","seriescolor":"redwood"},
			 "material":{"materialtype":"Brick","materialcategory":"Medallion","materialclass":"SAMPL","materialstyle":"Beak","materialfeature":""},
			 "dimensions":{"nominallength":11,"nominalwidth":11,"sizeunits":"E","thickness":"1","thicknessunit":"M","length":"11","width":"11","nominalthickness":0},
			 "price":{"listprice":59.9,"sellprice":35,"pricegroup":"","priceunit":"SET","sellpricemarginpct":2,"sellpriceroundaccuracy":2,"listpricemarginpct":0,"minmarginpct":0,"futuresell":0,"tempprice":0,"tempdatefrom":null,"tempdatethru":null,"priorlistprice":null,"priorsellprice":null,"default":false},
			 "testSpecification":{"waterabsorption":null,"scratchresistance":null,"frostresistance":null,"chemicalresistance":null,"peiabrasion":null,"scofwet":null,"scofdry":null,"breakingstrength":null,"scratchstandard":"","breakingstandard":"","restricted":"N","warpage":null,"wedging":null,"dcof":null,"thermalshock":null,"bondstrength":"","greenfriendly":"N","moh":0,"leadpoint":"N","preconsummer":0,"posconsummer":0},
			 "purchasers":{"purchaser":"EDDIEB","purchaser2":""},
			 "packaginginfo":{"boxPieces":0,"boxSF":0,"boxWeight":0,"palletBox":0,"palletSF":0,"palletWeight":0},
			 "notes":{"ponotes":"","buyernotes":"","invoicenotes":"","internalnotes":""},
			 "applications":{"residential":"","lightcommercial":"","commercial":"","residentialList":null,"lightcommercialList":null,"commercialList":null},
			 "usage":[""],
			 "units":{"stdunit":"SET","stdratio":1,"ordunit":"SET","ordratio":1,"baseunit":"SET","baseisstdsell":"Y","baseisstdord":"Y","baseisfractqty":null,"baseispackunit":"Y","baseupc":0,"baseupcdesc":"","basevolperunit":0,"basewgtperunit":45,"unit1unit":"","unit1ratio":0,"unit1isstdsell":null,"unit1isstdord":null,"unit1isfractqty":null,"unit1ispackunit":null,"unit1upc":0,"unit1upcdesc":"","unit1wgtperunit":0,"unit2unit":"","unit2ratio":0,"unit2isstdsell":null,"unit2isstdord":null,"unit2isfractqty":null,"unit2ispackunit":null,"unit2upc":0,"unit2upcdesc":"","unit2wgtperunit":0,"unit3unit":"","unit3ratio":0,"unit3isstdsell":null,"unit3isstdord":null,"unit3isfractqty":null,"unit3ispackunit":null,"unit3upc":0,"unit3upcdesc":"","unit3wgtperunit":0,"unit4unit":"","unit4ratio":0,"unit4isstdsell":null,"unit4isstdord":null,"unit4isfractqty":null,"unit4ispackunit":null,"unit4upc":0,"unit4upcdesc":"","unit4wgtperunit":0},
			 "cost":{"cost1":0,"priorcost":0,"futurecost":0,"poincludeinvendorcost":"Y","nonstockcostpct":75,"costrangepct":null},"relateditemcodes":null,
			 "vendors":{"vendornbr":null,"vendornbr1":0,"vendornbr2":null,"vendorxrefcd":"","vendorlistprice":35.94,"vendorpriceunit":"SET","vendorfob":"","vendordiscpct":0,"vendorroundaccuracy":2,"vendornetprice":35.94,"vendormarkuppct":0,"vendorfreightratecwt":0,"dutypct":0,"leadtime":0,"vendorlandedbasecost":35.94,"vendordiscpct2":0,"vendordiscpct3":0},
			 "newVendorSystem":[
			                    {"vendorOrder":1,"vendorName":null,"vendorName2":null,"vendorXrefId":"","vendorListPrice":35.94,"vendorNetPrice":35.94,"vendorPriceUnit":"SET","vendorFob":"","vendorDiscountPct":0,"vendorPriceRoundAccuracy":2,"vendorMarkupPct":0,"vendorFreightRateCwt":0,"vendorLandedBaseCost":35.94,"leadTime":0,"dutyPct":0,"id":0},
			                    {"vendorOrder":2,"vendorName":null,"vendorName2":null,"vendorXrefId":"","vendorListPrice":0,"vendorNetPrice":null,"vendorPriceUnit":"0","vendorFob":"","vendorDiscountPct":null,"vendorPriceRoundAccuracy":null,"vendorMarkupPct":0,"vendorFreightRateCwt":0,"vendorLandedBaseCost":0,"leadTime":null,"dutyPct":null,"id":null},
			                    {"vendorOrder":3,"vendorName":null,"vendorName2":null,"vendorXrefId":"","vendorListPrice":0,"vendorNetPrice":null,"vendorPriceUnit":"0","vendorFob":"","vendorDiscountPct":null,"vendorPriceRoundAccuracy":null,"vendorMarkupPct":0,"vendorFreightRateCwt":0,"vendorLandedBaseCost":0,"leadTime":null,"dutyPct":null,"id":null},
			                    {"vendorOrder":null,"vendorName":null,"vendorName2":null,"vendorXrefId":null,"vendorListPrice":0,"vendorNetPrice":null,"vendorPriceUnit":null,"vendorFob":"","vendorDiscountPct":null,"vendorPriceRoundAccuracy":null,"vendorMarkupPct":0,"vendorFreightRateCwt":0,"vendorLandedBaseCost":0,"leadTime":null,"dutyPct":null,"id":null}],
			                    "newFeature":{"grade":null,"status":"BEST","body":null,"edge":null,"mpsCode":"New_Product","designLook":null,"designStyle":null,"surfaceApplication":null,"surfaceType":null,"surfaceFinish":null,"warranty":null,"recommendedGroutJointMin":null,"recommendedGroutJointMax":null,"createdDate":1422921600000,"launchedDate":null,"droppedDate":null,"lastModifiedDate":null},
			                    "iconDescription":{"madeInCountry":null,"exteriorProduct":null,"adaAccessibility":null,"throughColor":null,"colorBody":null,"inkJet":null,"glazed":null,"unglazed":null,"rectifiedEdge":null,"chiseledEdge":null,"versaillesPattern":null,"recycled":null,"postRecycled":null,"preRecycled":null,"leadPoint":null,"greenFriendly":null,"coefficientOfFriction":null},
			                    "colorhues":[{"colorHue":"CLEAR"}]}
     */
	 @Test
		public void testNothing(){
		}
}
