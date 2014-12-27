package com.bedrosians.bedlogic.test.ims.junit;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
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
	
	private String id = "TEST";
	
 	
	@Before
	public void setup(){
	
	}
	
	//@Test
	 public void testUpdateItemWithColorCategoryJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringColorCategory);
	        imsService.updateItem(params);
	        
	        Ims item = imsService.getItemByItemCode(id);
	        assertEquals("RED:BLACK", item.getColorcategory());
	        for(ColorHue ch : item.getColorhues()){
	        	assertTrue(ch.getColorHue().contains("RED") || ch.getColorHue().contains("BLACK"));
	        }	
	}  
	
	@Test
	 public void testUpdateItemColorhuesWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithColorHues);
	        imsService.updateItem(params);
	        
	        Ims item = imsService.getItemByItemCode(id);
	        assertEquals("GREEN", item.getColorcategory());
	        for(ColorHue ch : item.getColorhues()){
	        //	assertTrue(ch.getColorHue().contains("GREEN"));
	        }	
	}  
	
	@Test
	 public void testUpdateItemDesccriptionWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringDescription);
	        imsService.updateItem(params);
	}
	
	@Test
	 public void testUpdateItemMaterialWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringMaterialInfo);
	        imsService.updateItem(params);
	        	        
	        Ims item = imsService.getItemByItemCode(id);
	        
	      
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
	        imsService.updateItem(params);
	        	        
	        Ims item = imsService.getItemByItemCode(id);
	        
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
	        imsService.updateItem(params);
	}
	
	@Test
	 public void testUpdateItemPriceWithJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithPriceAndCost);
	        imsService.updateItem(params);
	        
	        Ims item = imsService.getItemByItemCode(id);
	              
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
	        imsService.updateItem(params);
	        	        
	        Ims item = imsService.getItemByItemCode(id);
   
	        assertEquals("FR:WR:CR:SR:PR", item.getApplications().getResidential());
            assertEquals("FL:WL:CL:SL:PL", item.getApplications().getLightcommercial());
            assertEquals("FC:WC:CC:SC:PC", item.getApplications().getCommercial());
            
            //assertEquals("[FR,WR,CR,SR,PR,FL,WL,CL,SL,PL,FC,WC,CC,SC,PC]", item.getUsage().toString());
	}
	
	@Test
	 public void testUpdateItemWithUsage() throws Exception {
	        System.out.println("testUpdateItemApplicationWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithUsageInfo);
	        imsService.updateItem(params);
	        	        
	        Ims item = imsService.getItemByItemCode(id);
  
	        assertEquals("FR:WR:CR:SR:PR", item.getApplications().getResidential());
            assertEquals("FL:WL:CL:SL:PL", item.getApplications().getLightcommercial());
            assertEquals("FC:WC:CC:SC:PC", item.getApplications().getCommercial());
            
            assertEquals("[FR,WR,CR,SR,PR,FL,WL,CL,SL,PL,FC,WC,CC,SC,PC]", item.getUsage().toString());
	        
               
	}
	
	@Test
	 public void testUpdateItemWithNotes() throws Exception {
	        System.out.println("testUpdateItemApplicationWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithNotesInfo);
	        imsService.updateItem(params);
	        	        
	        Ims item = imsService.getItemByItemCode(id);
 
	        assertEquals("test1 po note", item.getNotes().getPonotes());
	        assertEquals("test1 note1", item.getNotes().getBuyernotes());
	        assertEquals("test1 note2", item.getNotes().getInternalnotes());
	        assertEquals("test1 note3", item.getNotes().getInvoicenotes());
              
	}
	
	@Test
	 public void testUpdateItemTestSpecs() throws Exception {
	        System.out.println("testUpdateItemTestSpecWithJsonObject: ");
	        JSONObject params = new JSONObject(jStringWithTestSpecs);
	        imsService.updateItem(params);
	        	        
	        Ims item = imsService.getItemByItemCode(id);

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
	        imsService.updateItem(params);
	        
	        Ims item = imsService.getItemByItemCode(id);
	        
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
	        imsService.updateItem(params);
	        
	        Ims item = imsService.getItemByItemCode(id);
	        
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
		    assertEquals("[RED]", item.getColorhues().toString());
		    //assertTrue(item.getColorhues().contains("BEIGE"));
		    assertEquals("F", (item.getItemtypecode()));
		    //assertEquals("test", item.getType());
		    assertEquals("C", item.getAbccode());
		    assertEquals(Character.valueOf('T'), item.getTaxclass());
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
	        productService.updateItem(params);
	          
	        Ims item = productService.getItemByItemCode(id);
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
	        productService.updateItem(params);
	        
	        Ims item = productService.getItemByItemCode(id);
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
	        JSONObject params = new JSONObject(jStringWithNewFeature);            
	        imsService.updateItem(params);
	        
	        Ims item = imsService.getItemByItemCode(id);
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
	 public void testUpdateItemWithUnitAndVendorJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithUnitAndVendor: ");
	        //JSONObject params = new JSONObject(jStringWithUnitAndVendor);
	        JSONObject params = new JSONObject(jStringWithNewVendorSystem);
	        imsService.updateItem(params);
	        
	        Ims item = imsService.getItemByItemCode(id);
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

	        System.out.println("newly Updated Item id  = " + id);
	 }
	 
	 @Test
	 public void testUpdateItemWithNewNotesJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithNewNotes: ");
	        JSONObject params = new JSONObject(jStringWithNewNotes);
	        imsService.updateItem(params);
	        
	        System.out.println("newly Updated Item id  = " + id);
	        Ims item = imsService.getItemByItemCode(id);
	        //for(Note note : item.getNewNoteSystem()){
	            //assertTrue(note.g"First", item.getNewNoteSystem());
	        //}    
	 }
	 
	 @Test
	 public void testUpdateItemWithNewIconSystemJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithNewIcon: ");
	        JSONObject params = new JSONObject(jStringWithNewIcons);
	        imsService.updateItem(params);
	        
	        System.out.println("newly Updated Item id  = " + id);
	        Ims item = imsService.getItemByItemCode(id);
	        IconCollection icon = item.getIconDescription();
	        assertEquals("China", icon.getMadeInCountry().getDescription());
	        assertEquals(false, icon.getExteriorProduct());
	        assertEquals(false, icon.getAdaAccessibility());
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
	        assertEquals(true, icon.getLeadPoint());
	        assertEquals(false, icon.getGreenFriendly());
	        assertEquals(true, icon.getCoefficientOfFriction());
	      
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
	 
	 @Test
	 public void testUpdateItemWithLagecyIconJsonObject() throws Exception {
	        System.out.println("testUpdateItemWithNewIcon: ");
	        JSONObject params = new JSONObject(jStringWithLagecyIcon);
	        imsService.updateItem(params);
	        
	        System.out.println("newly Updated Item id  = " + id);
	        Ims item = imsService.getItemByItemCode(id);
	        IconCollection icon = item.getIconDescription();
	        assertEquals("China", icon.getMadeInCountry().getDescription());
	        assertEquals(false, icon.getExteriorProduct());
	        assertEquals(false, icon.getAdaAccessibility());
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
	        assertEquals(true, icon.getLeadPoint());
	        assertEquals(false, icon.getGreenFriendly());
	        assertEquals(true, icon.getCoefficientOfFriction());
	      
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
	        JSONObject params = new JSONObject(jStringFullItemAndAssociationInfo);
	        imsService.updateItem(params);
	        	        
	        Ims item = imsService.getItemByItemCode(id);
	        
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
	        assertEquals(new BigDecimal("0.000000"), item.getUnits().getBasevolperunit());
	        assertEquals(new BigDecimal("4.200000"), item.getUnits().getBasewgtperunit());
	           
	        assertEquals("CTN", item.getUnits().getUnit1unit());
	        assertEquals(Float.valueOf("4.0"), item.getUnits().getUnit1ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isfractqty());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getUnit1ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit1upc());
	        assertEquals(new BigDecimal("17.400000"), item.getUnits().getUnit1wgtperunit());
	        
	        assertEquals("PLT", item.getUnits().getUnit2unit());
	        assertEquals(Float.valueOf("240.0"), item.getUnits().getUnit2ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2ispackunit());
	        assertEquals(Long.valueOf("0"), item.getUnits().getUnit2upc());
	        assertEquals(new BigDecimal("1070.000000"), item.getUnits().getUnit2wgtperunit());
	        
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
		    assertEquals("[BEIGE, YELLOW]", item.getColorhues().toString());
		    assertEquals("BEIGE:YELLOW", item.getColorcategory());
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
	        assertEquals(true, icon.getLeadPointIcon());
	        assertEquals(false, icon.getGreenFriendlyIcon());
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
		     "{\"itemcode\":\"test\","
 			+ "\"colorhues\":[\"GREEN\"],"
    		+ "}";
 
	 String jStringWithMultipleColorHues = 
		     "{\"itemcode\":\"newItemcode31\","
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
		     "{\"itemcode\":\"newItemcode18\","
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
			    "{\"itemcode\":\"newItemcode1\","
	 			+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,"
	 			+ "\"sizeunits\":\"E\","
	 			+ "\"thickness\":\"3/8\","
	 			+ "\"thicknessunit\":\"E\","
	 			+ "\"length\":\"11-13/16\","
	 			+ "\"width\":\"11-13/16\","
	 			+ "\"nominalthickness\":1.0},"
	       		+ "}";
      
	 String jStringMaterialInfo = 
			   "{\"itemcode\":\"NEWITEMCODE1\","
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
		     "{\"itemcode\":\"test\","
			+ "\"applications\":{"
			+ "\"residential\":\"FR:WR:CR:SR:PR\","
			+ "\"lightcommercial\":\"FL:WL:CL:SL:PL\","
			+ "\"commercial\":\"FC:WC:CC:SC:PC\"},"
       		+ "}";
 
	 String jStringWithUsageInfo = 
		     "{\"itemcode\":\"newItemcode3\","
			+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
    		+ "}";

	 String jStringWithNotesInfo = 
		     "{\"itemcode\":\"newItemcode1\","
		     + "\"notes\":{\"ponotes\":\"test1 po note\",\"buyernotes\":\"test1 note1\",\"internalnotes\":\"test1 note2\",\"invoicenotes\":\"test1 note3\"},"
		     + "}";
	
	 String jStringWithUnitAndVendor = ""
	 		    + "{\"itemcode\":\"newItemcode3\","
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
			    + "\"itemcode\":\"newItemcode1\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		//+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":\"TestVendor1\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.0500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":134585},"
	    	    
	    		+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":\"test\",\"vendorName2\":null,\"vendorXrefId\":\"ATM41\",\"vendorListPrice\":5.1500,\"vendorNetPrice\":7.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test\",\"vendorDiscountPct\":10.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":11.0,\"vendorFreightRateCwt\":10.0,\"vendorLandedBaseCost\":3.1500,\"leadTime\":60,\"dutyPct\":30.0,\"id\":134585},"
	    		+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":6.1500,\"vendorNetPrice\":7.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test\",\"vendorDiscountPct\":20.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":10.0,\"vendorFreightRateCwt\":12.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":20.0,\"id\":271520},"
	    		+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":7.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"test\",\"vendorDiscountPct\":30.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":10.0,\"vendorFreightRateCwt\":13.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":10.0,\"id\":453680},"
	    		 + "],"
	    		+ "}";

	 
	 String jStringWithNewFeature = 
			     "{\"itemcode\":\"newItemcode1\","
			    + "\"itemcategory\":\"ATHENA\","
			    + "\"countryorigin\":\"Italy\","
			    + "\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"update 2x2 Athena Mosaic on 12x12 Sheet \",\"itemdesc1\":\" update 2x2 Athena Mosaic on 12x12\"},"
	     		+ "\"newFeature\":{"
	     		   + "\"grade\":\"Second\","
	     		   + "\"status\":\"Better\","
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
	
	 String jStringWithIcons = "{\"itemcode\":\"newItemcode3\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	      		//+ "\"iconsystem\":\"NYNYNNNNYNNYNYNNYNYN\","
	       		+ "\"iconDescription\":{\"madeInCountry\":\"China\",\"exteriorProduct\":false,\"adaAccessibility\":false,\"throughColor\":true,\"colorBody\":false,\"inkJet\":false,\"glazed\":true,\"unglazed\":false,\"rectifiedEdge\":true,\"chiseledEdge\":false,\"versaillesPattern\":true,\"recycled\":false,\"postRecycled\":true,\"preRecycled\":false,\"leadPointIcon\":true,\"greenFriendlyIcon\":false,\"coefficientOfFriction\":true},"
	       		+ "}";
	 
	 String jStringWithNewIcons = "{\"itemcode\":\"newItemcode1\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	      	   	+ "\"iconDescription\":"
	       		+ "{"
	       		+ "\"madeInCountry\":\"China\","
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
	       		+ "\"leadPointIcon\":true,"
	       		+ "\"greenFriendlyIcon\":false,"
	       		+ "\"coefficientOfFriction\":true},"
	       		+ "}";
	
	 String jStringWithLagecyIcon = "{\"itemcode\":\"newItemcode1\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		+ "\"iconsystem\":\"NNNNNNNYYYNYNYNNYNNN\","
	      		+ "}";
	 
	 String jStringWithPriceAndCost = 
			   "{\"itemcode\":\"newItemcode1\","
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
			   "{\"itemcode\":\"NEWITEMCODE11\","
			   	+ "\"itemdesc\":{\"fulldesc\":\"update 2x2 Athena Mosaic on 12x12 Sheet\",\"itemdesc1\":\"update 2x2 Athena Mosaic on 12x12 \"},"
	    		+ "\"colorcategory\":\"RED:BLACK\","
	    		+ "}";
	 
	 String jStringDescription = 
			   "{\"itemcode\":\"NEWITEMCODE31\","
			   + "\"itemdesc\":{\"itemdesc1\":\"update 2x2 Athena Mosaic on 12x12 \"},"		   
	    		+ "}";
	
	 
	 String jStringColorHues = 
			   "{\"itemcode\":\"NEWITEMCODE4\","
			    + "\"itemcategory\":\"ATHENA\","
			    + "\"countryorigin\":\"Italy\","
			    + "\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"update 2x2 Athena Mosaic on 12x12 Sheet\",\"itemdesc1\":\"update 2x2 Athena Mosaic on 12x12 \",\"itemdesc2\":\"update 2x2 Mosaic\"},"
	    		+ "\"series\":{\"seriesname\":\"update Athena\",\"seriescolor\":\"update Ash\"},"
	    		+ "\"colorhues\":[\"RED\"],"
	    		+ "}";
	 
	 String jStringWithNewNotes = "{\"itemcode\":\"newItemcode41\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	      		//+ "\"notes\":{\"ponotes\":\"test po note\",\"notes1\":\"test notes1\",\"notes2\":\"test note2\",\"notes3\":\"test notes3\"},"
	    		+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"note\":\"test Po note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"note\":\"test buyer note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"note\":\"test invoice note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"note\":\"test additional note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"note\":\"test internal note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null}],"
	       		+ "}";
	 
	 String jStringWithTestSpecs = 
			   "{\"itemcode\":\"NEWITEMCODE1\","
			    + "\"testSpecification\":{\"waterabsorption\":0.5,\"scratchresistance\":0.6,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.7,\"scofwet\":0.8,\"scofdry\":0.0,\"breakingstrength\":0,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.0,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.0,\"leadpoint\":\"N\",\"preconsummer\":0.0,\"posconsummer\":0.0},"
				+ "}";
	
	 String jStringFullItemInfo = 
			   "{\"itemcode\":\"NEWITEMCODE1\","
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
			   "{\"itemcode\":\"NEWITEMCODE1\","
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
			     "{\"itemcode\":\"NEWITEMCODE1\","
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
	    		+ "\"newFeature\":{\"grade\":\"Third\",\"status\":\"Best\",\"body\":\"Red_Body\",\"edge\":\"Tumbled\",\"mpsCode\":\"Drop\",\"designLook\":\"Wood\",\"designStyle\":\"Modern\",\"surfaceApplication\":\"Silk\",\"surfaceType\":\"Cross_Cut\",\"surfaceFinish\":\"Antiquated\",\"warranty\":3,\"recommendedGroutJointMin\":\"1\",\"recommendedGroutJointMax\":\"2\",\"launchedDate\":null,\"droppedDate\":null,\"lastModifiedDate\":null},"
	    		//+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"note\":\"test Po note new \",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"note\":\"test buyer note\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"note\":\"test invoice note\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"note\":\"test additional note\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"note\":\"test internal note\",\"lastModifiedDate\":null}],"
	    		+ "\"iconDescription\":{\"madeInCountry\":\"China\",\"exteriorProduct\":false,\"adaAccessibility\":false,\"throughColor\":false,\"colorBody\":true,\"inkJet\":false,\"glazed\":true,\"unglazed\":false,\"rectifiedEdge\":true,\"chiseledEdge\":false,\"versaillesPattern\":true,\"recycled\":false,\"postRecycled\":true,\"preRecycled\":false,\"leadPointIcon\":true,\"greenFriendlyIcon\":false,\"coefficientOfFriction\":true},"
		        + "}";
	 @Test
		public void testNothing(){
		}
}
