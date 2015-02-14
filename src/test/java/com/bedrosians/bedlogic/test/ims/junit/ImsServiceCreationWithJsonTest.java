package com.bedrosians.bedlogic.test.ims.junit;


import java.math.BigDecimal;
import java.util.Random;

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
import com.bedrosians.bedlogic.util.enums.DBOperation;
import com.bedrosians.bedlogic.util.ims.ImsDataUtil;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application_context/bedlogic-context.xml", "/application_context/bedlogic-persistence.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ImsServiceCreationWithJsonTest {
	
	@Autowired
	ImsService imsService;
	
	private static String testItemId = null;
	
	@Before
	public void setup(){
		testItemId = "Test"; 	
	}
	
	@Test
	public void testItemCodeWithJsonObject() throws Exception {
	    String id = imsService.createItem(jStringWithItemCode);
        assertNotNull(id); 
	}

	 @Test
	 public void testCreateItemWithBasicInfoJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithBasicInfo);
	        assertNotNull(id);
	        
	        Ims item = imsService.getItem(id);
	             
	        assertEquals("ATHENA", item.getItemcategory());
	        assertEquals("Italy", item.getCountryorigin());
	        assertEquals("N", item.getInactivecode());
	        assertEquals("2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)", item.getItemdesc().getFulldesc());    
		    assertEquals("F", item.getItemtypecode());
		    //assertEquals("test", item.getType());
		    assertEquals("C", item.getAbccode());
		    assertEquals("T", item.getTaxclass());
		    assertEquals("N", item.getPrintlabel());
		    assertEquals("ALICIAB", item.getPurchasers().getPurchaser());
		    assertEquals("GFIL", item.getPurchasers().getPurchaser2());
		    assertEquals("V2", item.getShadevariation());
		    assertEquals("S", item.getLottype());
		
	        System.out.println("newly created Item id  = " + id);
	 }
	 
	  
	@Test
	 public void testCreateItemWithDimensionsJsonObject() throws Exception {
	        System.out.println("testCreateItemWithDimensions: ");
	        String id = imsService.createItem(jStringWithDimensions);
	        assertNotNull(id);
	       
	        Ims item = imsService.getItem(id);
	        
	        assertEquals("11-13/16", item.getDimensions().getLength());
	        assertEquals("11-13/16", item.getDimensions().getWidth());
	        assertEquals("3/8", item.getDimensions().getThickness());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominallength());
	        assertEquals(Float.valueOf("12.0"), item.getDimensions().getNominalwidth());
	        assertEquals("E", item.getDimensions().getSizeunits());
	        assertEquals("E", item.getDimensions().getThicknessunit());
	 }
	 
	 @Test
	 public void testCreateItemWithPricesJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithPriceInfo);
	        assertNotNull(id);
	       
	        Ims item = imsService.getItem(id);
	        
	        assertEquals("18.3800", item.getPrice().getListprice().toString());
	        assertEquals("18.3800", item.getPrice().getSellprice().toString());
	        assertEquals("10.0000", item.getPrice().getFuturesell().toString());
	        assertEquals("14.7000", item.getPrice().getPriorsellprice().toString());
	        assertEquals("10.0000", item.getPrice().getPriorlistprice().toString());
	        assertEquals("16.0000", item.getPrice().getTempprice().toString());
	          
	        assertEquals("2", item.getPrice().getPricegroup());
	        assertEquals("2.0", item.getPrice().getSellpricemarginpct().toString());
	        assertEquals("2", item.getPrice().getSellpriceroundaccuracy().toString());
	        assertEquals("15.0", item.getPrice().getMinmarginpct().toString());
	        //assertEquals("SHT", item.getPrice().getPriceunit());
	 }
	 
	  @Test
	 public void testCreateItemWithMaterialJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithMaterialInfo);
	        assertNotNull(id);
	       
	        Ims item = imsService.getItem(id);
	        
	        assertEquals("Porcelain", item.getMaterial().getMaterialtype());
	        assertEquals("CTSRC", item.getMaterial().getMaterialclass());
	        assertEquals("FW", item.getMaterial().getMaterialstyle());
	        assertEquals("Trim", item.getMaterial().getMaterialcategory());
	 }
	 
	 @Test
	 public void testCreateItemWithSeriesJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithSeriesInfo);
	        assertNotNull(id);  
	        Ims item = imsService.getItem(id);
	        assertEquals("Athena", item.getSeries().getSeriesname());
	        assertEquals("Ash", item.getSeries().getSeriescolor());
	 }
		    	
	 @Test
	 public void testCreateItemWithTestSpecsJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithTestInfo);
	        assertNotNull(id);
	       
	        Ims item = imsService.getItem(id);
	        
	        assertEquals(Float.valueOf("0.1"), item.getTestSpecification().getWaterabsorption());
	        assertEquals(Float.valueOf("0.2"), item.getTestSpecification().getScratchresistance());
	        assertEquals(Float.valueOf("0.3"), item.getTestSpecification().getPeiabrasion());
	        assertEquals(Float.valueOf("0.4"), item.getTestSpecification().getScofwet());
	        assertEquals(Float.valueOf("0.5"), item.getTestSpecification().getScofdry());
	        //assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getBreakingstrength());
	        assertEquals(Float.valueOf("0.7"), item.getTestSpecification().getDcof());
	        assertEquals(Float.valueOf("0.8"), item.getTestSpecification().getMoh());
	        assertEquals(Float.valueOf("0.9"), item.getTestSpecification().getPreconsummer());
	        assertEquals(Float.valueOf("0.11"), item.getTestSpecification().getPosconsummer());
	        //assertEquals('N', item.getTestSpecification().getRestricted());
	        //assertEquals("N", item.getTestSpecification().getLeadpoint());
	 }
	
	 @Test
	 public void testCreateItemWithApplicationsJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithApplicationsInfo);
	        assertNotNull(id);
	       
	        Ims item = imsService.getItem(id);
	        assertEquals("FR:WR:CR:SR:PR", item.getApplications().getResidential());
	        assertEquals("FL:WL:CL:SL:PL", item.getApplications().getLightcommercial());
	        assertEquals("FC:WC:CC:SC:PC", item.getApplications().getCommercial());
	        
	        assertEquals("[FR, WR, CR, SR, PR, FL, WL, CL, SL, PL, FC, WC, CC, SC, PC]", item.getUsage().toString());
	 }
		
	 @Test
	 public void testCreateItemWithUsageJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithBasicInfo);
	        assertNotNull(id);
	       
	        Ims item = imsService.getItem(id);
	        System.out.println(item.getUsage());
	        assertEquals("[FR, WR, CR, SR, PR, FL, WL, CL, SL, PL, FC, WC, CC, SC, PC]", item.getUsage().toString());
	        assertEquals("FC:WC:CC:SC:PC", item.getApplications().getCommercial());
	        assertEquals("FR:WR:CR:SR:PR", item.getApplications().getResidential());
	 }
	 
	 @Test
	 public void testCreateItemWithNotesJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithNoteInfo);
	        assertNotNull(id);
	       
	        Ims item = imsService.getItem(id);
	         assertEquals("test po note", item.getNotes().getPonotes());
	        assertEquals("test note1", item.getNotes().getBuyernotes());
	        assertEquals("test note2", item.getNotes().getInternalnotes());
	        assertEquals("test note3", item.getNotes().getInvoicenotes());
	 }
	 
	 @Test
	 public void testCreateItemWithPackagingInfoJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithPackingInfo);
	        assertNotNull(id);
	       
	        Ims item = imsService.getItem(id);
	        
	      //  assertEquals("Athena", item.getSeries().getSeriesname());
	      //  assertEquals("Ash", item.getSeries().getSeriescolor());
	 }
	 
	 String newItemcode = testItemId + new Random().nextInt(9000);

	 
	  @Test
	 public void testCreateItemWithColorCategoryJsonObject() throws Exception {
	        System.out.println("testCreateItemWithColorCategory: ");
	        String id = imsService.createItem(jStringWithColorCategory);
	        assertNotNull(id);    
	        Ims item = imsService.getItem(id);
	        assertEquals("BLACK:WHITE", item.getColorcategory());
	        //assertEquals("[BLACK, WHITE]", item.getColorhues().toString());
	        for(ColorHue colorHue : item.getColorhues()){
	            //assertTrue(colorHue.getColorHue().equals("GREEN") || colorHue.getColorHue().equals("WHITE"));
	            assertTrue(item.getColorcategory().contains(colorHue.getColorHue()));
	        } 
		 }
	  
	 @Test
	 public void testCreateItemWithAllImsInfoJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringFullItemInfo);
	        assertNotNull(id);
	        
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
	        assertEquals(Long.valueOf("3333"), item.getUnits().getBaseupc());
	        assertEquals(new BigDecimal("44.0000"), item.getUnits().getBasevolperunit());
	        assertEquals(new BigDecimal("4.2000"), item.getUnits().getBasewgtperunit());
	           
	        assertEquals("CTN", item.getUnits().getUnit1unit());
	        assertEquals(Float.valueOf("4.0"), item.getUnits().getUnit1ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit1isfractqty());
	        assertEquals(Character.valueOf('Y'), item.getUnits().getUnit1ispackunit());
	        assertEquals(Long.valueOf("1234"), item.getUnits().getUnit1upc());
	        assertEquals(new BigDecimal("17.4000"), item.getUnits().getUnit1wgtperunit());
	        
	        assertEquals("PLT", item.getUnits().getUnit2unit());
	        assertEquals(Float.valueOf("240.0"), item.getUnits().getUnit2ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit2ispackunit());
	        assertEquals(Long.valueOf("12345"), item.getUnits().getUnit2upc());
	        assertEquals(new BigDecimal("1070.0000"), item.getUnits().getUnit2wgtperunit());
	        
	        assertEquals("test", item.getUnits().getUnit3unit());
	        assertEquals(Float.valueOf("10.0"), item.getUnits().getUnit3ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit3ispackunit());
	        assertEquals(Long.valueOf("12345"), item.getUnits().getUnit3upc());
	        //assertEquals(new BigDecimal("0"), item.getUnits().getUnit3wgtperunit());
	        
	        assertEquals("test", item.getUnits().getUnit4unit());
	        assertEquals(Float.valueOf("10.0"), item.getUnits().getUnit4ratio());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isstdsell());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isstdord());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4isfractqty());
	        assertEquals(Character.valueOf('N'), item.getUnits().getUnit4ispackunit());
	        assertEquals(Long.valueOf("12353"), item.getUnits().getUnit4upc());
	        //assertEquals(new BigDecimal("0"), item.getUnits().getUnit4wgtperunit());
	        //test spec
		    assertEquals(Float.valueOf("0.5"), item.getTestSpecification().getWaterabsorption());
	        assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getScratchresistance());
	        assertEquals(Float.valueOf("0.7"), item.getTestSpecification().getPeiabrasion());
	        assertEquals(Float.valueOf("0.8"), item.getTestSpecification().getScofwet());
	        assertEquals(Float.valueOf("0.2"), item.getTestSpecification().getScofdry());
	        //assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getBreakingstrength());
	        assertEquals(Float.valueOf("0.3"), item.getTestSpecification().getDcof());
	        assertEquals(Float.valueOf("0.4"), item.getTestSpecification().getMoh());
	        assertEquals(Float.valueOf("0.6"), item.getTestSpecification().getPreconsummer());
	        assertEquals(Float.valueOf("0.9"), item.getTestSpecification().getPosconsummer());
			//usage
		    //assertEquals("[FR,WR,CR,SR,PR,FL,WL,CL,SL,PL,FC,WC,CC,SC,PC]", item.getUsage());
	      	 
	        //assertEquals("BEIGE".toUpperCase(), item.getColorhues().get(0));
	        assertEquals("ATHENA", item.getItemcategory());
	        assertEquals("Italy", item.getCountryorigin());
	        assertEquals("N", item.getInactivecode());
	        assertEquals("2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)", item.getItemdesc().getFulldesc());    
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
	
	        //notes
	        
	        System.out.println("newly created Item id  = " + id);
	 }
	 
	 //-------------- Test Associations -------------//
	 
	 @Test
	 public void testCreateItemWithColorHuesJsonObject() throws Exception {
	        System.out.println("testCreateItemWithColorHues: ");
	        String id = imsService.createItem(jStringWithColorHues);
	        assertNotNull(id);    
	        Ims item = imsService.getItem(id);
	        for(ColorHue colorHue : item.getColorhues()){
		        assertEquals("BEIGE", colorHue.getColorHue());
		        //    assertEquals(colorHue.getColorHue(), item.getColorcategory());
		    } 
	        assertEquals("BEIGE",item.getColorcategory());
	 }
	 
	 @Test
	 public void testCreateItemWithMultipleColorHuesJsonObject() throws Exception {
	        System.out.println("testCreateItemWithColorHues: ");
	        String id = imsService.createItem(jStringWithMultipleColorHues);
	        assertNotNull(id);    
	        Ims item = imsService.getItem(id);
	        for(ColorHue colorHue : item.getColorhues()){
	            assertTrue("BEIGE".equals(colorHue.getColorHue()) || "RED".equals(colorHue.getColorHue()));
	            assertTrue(item.getColorcategory().contains(colorHue.getColorHue()));
	        } 
	 }
	 
	 @Test
	 public void testCreateItemWithNewFeatureByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        String id = imsService.createItem(jStringWithNewFeature);
	        assertNotNull(id);
	        Ims item = imsService.getItem(id);
	        assertEquals("First", item.getNewFeature().getGrade().getDescription());
	        assertEquals("GOOD", item.getNewFeature().getStatus().getDescription());
	        
	        assertEquals("Red Body", item.getNewFeature().getBody().getDescription());
	        assertEquals("Tumbled", item.getNewFeature().getEdge().getDescription());
	        assertEquals("Drop", item.getNewFeature().getMpsCode().getDescription());
	        
	        assertEquals("Wood", item.getNewFeature().getDesignLook().getDescription());
	        assertEquals("Modern", item.getNewFeature().getDesignStyle().getDescription());
	        assertEquals("Silk", item.getNewFeature().getSurfaceApplication().getDescription());
	        assertEquals("Cross Cut", item.getNewFeature().getSurfaceType().getDescription());
	        assertEquals("Antiquated", item.getNewFeature().getSurfaceFinish().getDescription());
	        assertEquals(new Integer("3"), item.getNewFeature().getWarranty());
	        assertEquals("1", item.getNewFeature().getRecommendedGroutJointMin());
	        assertEquals("2", item.getNewFeature().getRecommendedGroutJointMax());
	 }
	 
	 @Test
	 public void testCreateItemWithUnitAndNewVendorJsonObject() throws Exception {
	        System.out.println("testCreateItemWithUnitAndVendor: ");
	        //JSONObject params = new JSONObject(jStringWithNewVendorSystem);
	        String id = imsService.createItem(jStringWithUnitAndNewVendor);
	        assertNotNull(id);
	        Ims item = imsService.getItem(id);
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

	        System.out.println("newly created Item id  = " + id);
	 }
	 
	 @Test
	 public void testCreateItemWithUnitAndNewVendorSystemJsonObject() throws Exception {
	        System.out.println("testCreateItemWithUnitAndVendor: ");
	        //JSONObject params = new JSONObject(jStringWithNewVendorSystem);
	        String id = imsService.createItem(jStringWithUnitAndNewVendor);
	        assertNotNull(id);
	        Ims item = imsService.getItem(id);
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

	        System.out.println("newly created Item id  = " + id);
	 }
	 // @Test
	 public void testCreateItemWithNewNotesJsonObject() throws Exception {
	        System.out.println("testCreateItemWithNewNotes: ");
	        String id = imsService.createItem(jStringWithNewNotes);
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        Ims item = imsService.getItem(id);
	        //for(Note note :)
	        //assertEquals("First", item.getNewNoteSystem());
	 }
	 
	 @Test
	 public void testCreateItemWithNewIconJsonObject() throws Exception {
	        System.out.println("testCreateItemWithNewIcon: ");
	        String id = imsService.createItem(jStringWithNewIcons);
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        Ims item = imsService.getItem(id);
	        IconCollection icon = item.getIconDescription();
	        assertEquals("USA", icon.getMadeInCountry().getDescription());
	        assertEquals(true, icon.getExteriorProduct());
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
	        assertEquals("NYYYNNNYYYNYNNNNYNNN", ImsDataUtil.convertIconCollectionToLegancyIcons(icon));
	   
	 }

	 @Test
	 public void testCreateItemWithLagecyIconJsonObject() throws Exception {
	        System.out.println("testCreateItemWithNewIcon: ");
	        String id = imsService.createItem(jStringWithLagecyIcon);
	        assertNotNull(id);
	        System.out.println("newly created Item id  = " + id);
	        Ims item = imsService.getItem(id);
	        
	        IconCollection icon = item.getIconDescription();
	        
	        assertEquals("NYYYNNNYYYNYNNNNYNNN", ImsDataUtil.convertIconCollectionToLegancyIcons(icon));
	       
	        assertEquals("USA", icon.getMadeInCountry().getDescription());
	        assertEquals(true, icon.getExteriorProduct());
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
	        //assertEquals(true, icon.getLeadPointIcon());
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
	 }
	 
	 //---------- Item and associations ----------------//
	 @Test
	 public void testCreateItemWithAllImsAndAssociationsByJsonObject() throws Exception {
	        System.out.println("testCreateItemWithJsonObject: ");
	        //JSONObject params = new JSONObject(jStringFullItemAndAssociationInfo);
	        String id = imsService.createOrUpdateItem(JsonUtil.jsonStringToPOJO(jStringFullItemAndAssociationInfo), DBOperation.CREATE);
	        assertNotNull(id);
	        
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
	        assertEquals("15.0000", item.getPrice().getTempprice().toString());
	        
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
		    for(ColorHue ch : item.getColorhues()){
		    	assertTrue(ch.getColorHue().contains("BEIGE") || ch.getColorHue().contains("YELLOW"));
		    }
	        assertEquals("BEIGE:YELLOW", item.getColorcategory());
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
	        //item new features
	        assertEquals("First", item.getNewFeature().getGrade().getDescription());
	        assertEquals("GOOD", item.getNewFeature().getStatus().getDescription());
	        assertEquals("Red Body", item.getNewFeature().getBody().getDescription());
	        assertEquals("Tumbled", item.getNewFeature().getEdge().getDescription());
	        assertEquals("Drop", item.getNewFeature().getMpsCode().getDescription());
	        assertEquals("Wood", item.getNewFeature().getDesignLook().getDescription());
	        assertEquals("Modern", item.getNewFeature().getDesignStyle().getDescription());
	        assertEquals("Silk", item.getNewFeature().getSurfaceApplication().getDescription());
	        assertEquals("Cross Cut", item.getNewFeature().getSurfaceType().getDescription());
	        assertEquals("Antiquated", item.getNewFeature().getSurfaceFinish().getDescription());
	        assertEquals(new Integer("3"), item.getNewFeature().getWarranty());
	        assertEquals("1", item.getNewFeature().getRecommendedGroutJointMin());
	        assertEquals("2", item.getNewFeature().getRecommendedGroutJointMax());
	        //colorhue
//	        for(ColorHue colorHue : item.getColorhues()){
//	            assertTrue("BEIGE".equals(colorHue.getColorHue()) || "YELLOW".equals(colorHue.getColorHue()));
//	            assertTrue(item.getColorcategory().contains(colorHue.getColorHue()));
//	        } 
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
	        assertEquals(true, icon.getLeadPoint());
	        assertEquals(false, icon.getGreenFriendly());
	        assertEquals(true, icon.getCoefficientOfFriction());
	        System.out.println("newly created Item id  = " + id);
	 }
	 
	 String jStringWithItemCode = 
			   "{\"itemcode\":\"TEST2\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		+ "}";
	 
	 String jStringWithBasicInfo = 
		     "{\"itemcode\":\"newItemcode\","
		    + "\"itemcategory\":\"ATHENA\","
		    + "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
 		    + "\"countryorigin\":\"Italy\","
 		    + "\"inactivecode\":\"N\","
 			+ "\"showonwebsite\":\"Y\","
 			+ "\"itemtypecode\":\"F\","
 			+ "\"abccode\":\"C\","
 			+ "\"itemcode2\":\"Test\","
 			//+ "\"inventoryitemcode\":\"newItemcode7\","
 			+ "\"showonalysedwards\":\"N\","
 			+ "\"offshade\":\"N\","
 			+ "\"printlabel\":\"N\","
 			+ "\"taxclass\":\"T\","
 			+ "\"lottype\":\"S\","
 			+ "\"updatecode\":\"CERA-CRD\","
 			+ "\"directship\":\"Y\","
 			+ "\"dropdate\":null,"
 			+ "\"itemgroupnbr\":0,"
 			+ "\"priorlastupdated\":\"2014-03-31\","
 	      	+ "\"shadevariation\":\"V2\","
 	      	+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
       		+ "\"productline\":\"CERA\","
    		+ "}";
	 
	 String jStringWithBasicInfo2 = 
			     "{\"itemcode\":\"newItemcode4\","
			    + "\"itemcategory\":\"ATHENA\","
	 		    + "\"countryorigin\":\"Italy\","
	 		    + "\"inactivecode\":\"N\","
	 			+ "\"showonwebsite\":\"Y\","
	 			+ "\"itemtypecode\":\"F\","
	 			+ "\"abccode\":\"C\","
	 			+ "\"itemcode2\":\"\","
	 			//+ "\"inventoryitemcd\":\"F\","
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
	    		+ "\"colorcategory\":\"BEIGE\","
	     		+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0},"
	    		+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":0.0000,\"priorsellprice\":14.7000,\"tempprice\":0.0000,\"tempdatefrom\":null,\"tempdatethru\":null,\"priorlistprice\":0.0000},"
	    		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
	    		+ "\"testSpecification\":{\"waterabsorption\":0.1,\"scratchresistance\":0.2,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.3,\"scofwet\":0.4,\"scofdry\":0.5,\"breakingstrength\":6,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.7,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.8,\"leadpoint\":\"N\",\"preconsummer\":0.9,\"posconsummer\":0.11},"
	    		+ "\"relateditemcodes\":null,"
	    		+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
	    		+ "\"packaginginfo\":{\"boxPieces\":4.0,\"boxSF\":0.0,\"boxWeight\":16.8,\"palletBox\":60.0,\"palletSF\":0.0,\"palletWeight\":1007.99994},"
	     		+ "\"productline\":\"\","
	    		//+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
	       		+ "\"cost\":{\"cost1\":0.0000,\"priorcost\":0.0000},"
	       	    + "\"notes\":{\"ponotes\":\"test po note\",\"buyernotes\":\"test note1\",\"internalnotes\":\"test note2\",\"invoicenotes\":\"test note3\"}"
    			+ "}";
	 
	 String jStringWithMaterialInfo = 
		     "{\"itemcode\":\"TEST3\","
		    + "\"itemcategory\":\"ATHENA\","
  	      	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
    		+ "\"material\":{\"materialtype\":\"Porcelain\",\"materialcategory\":\"Trim\",\"materialclass\":\"CTSRC\",\"materialstyle\":\"FW\",\"materialfeature\":\"\"}"
        	+ "}";
	 
	 String jStringWithSeriesInfo = 
		     "{\"itemcode\":\"newItemcode13\","
		    + "\"itemcategory\":\"ATHENA\","
 	    	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
    		+ "\"series\":{\"seriesname\":\"Athena\",\"seriescolor\":\"Ash\"}"
    		+ "}";

	 String jStringWithPriceInfo = 
		     "{\"itemcode\":\"newItemcode10\","
		    + "\"itemcategory\":\"ATHENA\","
        	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
        	+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"2\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":10.0000,\"priorsellprice\":14.7000,\"tempprice\":16.0000,\"tempdatefrom\":2014-06-31,\"tempdatethru\":2014-08-31,\"priorlistprice\":10.0000},"
        	//+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"1\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":0.0000,\"priorsellprice\":14.7000,\"tempprice\":13.0000,\"tempdatefrom\":null,\"tempdatethru\":null,\"priorlistprice\":0.0000},"
 		+ "}";

	 
	 String jStringWithApplicationsInfo = 
		     "{\"itemcode\":\"newItemcode15\","
		    + "\"itemcategory\":\"ATHENA\","
		    + "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
     		+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"}"
    		+ "}";
 
	 
	 String jStringWithColorHues = 
		     "{\"itemcode\":\"newItemcode1\","
		    + "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
		    + "\"itemcategory\":\"ATHENA\","
 		    + "\"countryorigin\":\"Italy\","
 		    + "\"inactivecode\":\"N\","
 			+ "\"showonwebsite\":\"Y\","
 			+ "\"itemtypecode\":\"#\","
 			+ "\"abccode\":\"C\","
 			+ "\"itemcode2\":\"\","
 			+ "\"colorhues\":[\"BEIGE\"]"
    		+ "}";
 
	 String jStringWithMultipleColorHues = 
		     "{\"itemcode\":\"newItemcode3\","
		    + "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
		    + "\"itemcategory\":\"ATHENA\","
 		    + "\"countryorigin\":\"Italy\","
 		    + "\"inactivecode\":\"N\","
 			+ "\"showonwebsite\":\"Y\","
 			+ "\"itemtypecode\":\"#\","
 			+ "\"abccode\":\"C\","
 			+ "\"itemcode2\":\"\","
 			+ "\"colorhues\":[\"BEIGE\", \"RED\"]"
    		+ "}";
 
	 
	 String jStringWithColorCategory = 
		     "{\"itemcode\":\"newItemcode20\","
		    + "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
		    + "\"itemcategory\":\"ATHENA\","
 		    + "\"countryorigin\":\"Italy\","
 		    + "\"inactivecode\":\"N\","
 			+ "\"showonwebsite\":\"Y\","
 			+ "\"itemtypecode\":\"#\","
 			+ "\"abccode\":\"C\","
 			+ "\"itemcode2\":\"\","
 			+ "\"colorcategory\":\"BLACK:WHITE\""
    		+ "}";
 
	 
	 String jStringWithDimensions = 
			    "{\"itemcode\":\"newItemcode8\","
			    + "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
			    + "\"itemcategory\":\"ATHENA\","
	 		    + "\"countryorigin\":\"Italy\","
	 		    + "\"inactivecode\":\"N\","
	 			+ "\"showonalysedwards\":\"N\","
	 			+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0}"
	       		+ "}";
       
	 String jStringWithTestInfo = 
		     "{\"itemcode\":\"newItemcode14\","
		    + "\"itemcategory\":\"ATHENA\","
 	    	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
        	//+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
    		+ "\"testSpecification\":{\"waterabsorption\":0.1,\"scratchresistance\":0.2,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.3,\"scofwet\":0.4,\"scofdry\":0.5,\"breakingstrength\":6,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.7,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.8,\"leadpoint\":\"N\",\"preconsummer\":0.9,\"posconsummer\":0.11},"
        	+ "}";
 
	 String jStringWithNoteInfo = 
		     "{\"itemcode\":\"newItemcode20\","
		    + "\"itemcategory\":\"ATHENA\","
 		    + "\"countryorigin\":\"Italy\","
 		   	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
      	    + "\"notes\":{\"ponotes\":\"test po note\",\"buyernotes\":\"test note1\",\"internalnotes\":\"test note2\",\"invoicenotes\":\"test note3\"},"
			+ "}";
 
	 String jStringWithPackingInfo = 
		     "{\"itemcode\":\"newItemcode21\","
		    + "\"itemcategory\":\"ATHENA\","
 		  	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
     		+ "\"packaginginfo\":{\"boxPieces\":4.0,\"boxSF\":0.0,\"boxWeight\":16.8,\"palletBox\":60.0,\"palletSF\":0.0,\"palletWeight\":1007.99994},"
        	+ "}";

	 
	 String jStringWithUnitAndVendor = 
			    "{"
			    + "\"itemcode\":\"newItemcode26\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		+ "\"units\":"
	    		+ "{\"stdunit\":\"SHT\",\"stdratio\":1.0,\"ordunit\":\"SHT\",\"ordratio\":1.0,"
	    		+ "\"baseunit\":\"SHT\",\"baseisstdsell\":\"Y\",\"baseisstdord\":\"Y\",\"baseisfractqty\":\"N\",\"baseispackunit\":\"Y\",\"baseupc\":0,\"baseupcdesc\":\"\",\"basevolperunit\":0.0000,\"basewgtperunit\":4.2000,"
	    		+ "\"unit1unit\":\"CTN\",\"unit1ratio\":4.0,\"unit1isstdsell\":\"N\",\"unit1isstdord\":\"N\",\"unit1isfractqty\":\"N\",\"unit1ispackunit\":\"Y\",\"unit1upc\":0,\"unit1upcdesc\":\"\",\"unit1wgtperunit\":17.4000,"
	    		+ "\"unit2unit\":\"PLT\",\"unit2ratio\":240.0,\"unit2isstdsell\":\"N\",\"unit2isstdord\":\"N\",\"unit2isfractqty\":\"N\",\"unit2ispackunit\":\"N\",\"unit2upc\":0,\"unit2upcdesc\":\"\",\"unit2wgtperunit\":1070.0000,"
	    		+ "\"unit3unit\":\"\",\"unit3ratio\":0.0,\"unit3isstdsell\":\"N\",\"unit3isstdord\":\"N\",\"unit3isfractqty\":\"N\",\"unit3ispackunit\":\"N\",\"unit3upc\":0,\"unit3upcdesc\":\"\",\"unit3wgtperunit\":0.0000,"
	    		+ "\"unit4unit\":\"\",\"unit4ratio\":0.0,\"unit4isstdsell\":\"N\",\"unit4isstdord\":\"N\",\"unit4isfractqty\":\"N\",\"unit4ispackunit\":\"N\",\"unit4upc\":0,\"unit4upcdesc\":\"\",\"unit4wgtperunit\":0.0000},"
	     		+ "\"vendors\":"
	     		   + "{\"vendornbr\":0,"
	     		   + "\"vendornbr1\":134585,"
	     		   + "\"vendornbr2\":0,"
	     		   + "\"vendorxrefcd\":\"ATM40\","
	     		   + "\"vendorlistprice\":4.1500,"
	     		   + "\"vendorpriceunit\":\"SHT\","
	     		   + "\"vendorfob\":\"test\","
	     		   + "\"vendordiscpct\":10.1,"
	     		   + "\"vendorroundaccuracy\":2,"
	     		   + "\"vendornetprice\":4.1500,"
	     		   + "\"vendormarkuppct\":9.4,"
	     		   + "\"vendorfreightratecwt\":20.0,"
	     		   + "\"dutypct\":10.0,"
	     		   + "\"leadtime\":60,"
	     		   + "\"vendorlandedbasecost\":4.1500,"
	     		   + "\"vendordiscpct2\":10.0,"
	     		   + "\"vendordiscpct3\":12.0},"
	    		//+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134585},"
	    		//+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134586},"
	    		//+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":134587},"
    		    //+ "                     ],"
	     	    + "}";
	 
	 String jStringWithUnitAndNewVendor = 
			    "{"
			    + "\"itemcode\":\"newItemcode2\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		+ "\"units\":"
	    		+ "{\"stdunit\":\"SHT\",\"stdratio\":1.0,\"ordunit\":\"SHT\",\"ordratio\":1.0,"
	    		+ "\"baseunit\":\"SHT\",\"baseisstdsell\":\"Y\",\"baseisstdord\":\"Y\",\"baseisfractqty\":\"N\",\"baseispackunit\":\"Y\",\"baseupc\":0,\"baseupcdesc\":\"\",\"basevolperunit\":0.0000,\"basewgtperunit\":4.2000,"
	    		+ "\"unit1unit\":\"CTN\",\"unit1ratio\":4.0,\"unit1isstdsell\":\"N\",\"unit1isstdord\":\"N\",\"unit1isfractqty\":\"N\",\"unit1ispackunit\":\"Y\",\"unit1upc\":0,\"unit1upcdesc\":\"\",\"unit1wgtperunit\":17.4000,"
	    		+ "\"unit2unit\":\"PLT\",\"unit2ratio\":240.0,\"unit2isstdsell\":\"N\",\"unit2isstdord\":\"N\",\"unit2isfractqty\":\"N\",\"unit2ispackunit\":\"N\",\"unit2upc\":0,\"unit2upcdesc\":\"\",\"unit2wgtperunit\":1070.0000,"
	    		+ "\"unit3unit\":\"\",\"unit3ratio\":0.0,\"unit3isstdsell\":\"N\",\"unit3isstdord\":\"N\",\"unit3isfractqty\":\"N\",\"unit3ispackunit\":\"N\",\"unit3upc\":0,\"unit3upcdesc\":\"\",\"unit3wgtperunit\":0.0000,"
	    		+ "\"unit4unit\":\"\",\"unit4ratio\":0.0,\"unit4isstdsell\":\"N\",\"unit4isstdord\":\"N\",\"unit4isfractqty\":\"N\",\"unit4ispackunit\":\"N\",\"unit4upc\":0,\"unit4upcdesc\":\"\",\"unit4wgtperunit\":0.0000},"
	    		+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":\"TestVendor1\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.0500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":134585},"
	    		+ "                     {\"vendorOrder\":2,\"vendorName\":\"TestVendor2\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.2500,\"vendorNetPrice\":4.0600,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":271520},"
	    		+ "                     {\"vendorOrder\":3,\"vendorName\":\"TestVendor3\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.3500,\"vendorNetPrice\":4.0700,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":453680},"
 		        + "                     ],"

	     	    + "}";


	 String jStringWithNewVendorSystem = 
			    "{"
			    + "\"itemcode\":\"newItemcode13\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	      	  	+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":374906},"
	    		+ "                     {\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":302871},"
	    		+ "                     {\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorNetPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"vendorLandedBaseCost\":4.1500,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"vendorId\":529554}],"
	    		+ "}";
	 
	 String jStringWithNewFeature = "{\"itemcode\":\"newItemcode\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	     		+ "\"newFeature\":{\"grade\":\"First\",\"status\":\"GOOD\",\"body\":\"Red_Body\",\"edge\":\"Tumbled\",\"mpsCode\":\"Drop\",\"designLook\":\"Wood\",\"designStyle\":\"Modern\",\"surfaceApplication\":\"Silk\",\"surfaceType\":\"Cross_Cut\",\"surfaceFinish\":\"Antiquated\",\"warranty\":3,\"recommendedGroutJointMin\":\"1\",\"recommendedGroutJointMax\":\"2\",\"createdDate\":\"2014-05-14\",\"launchedDate\":null,\"droppedDate\":null,\"lastModifiedDate\":null},"
	    		+ "}";
	 
	 String jStringWithNewNotes = "{\"itemcode\":\"newItemcode41\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	      		//+ "\"notes\":{\"ponotes\":\"test po note\",\"notes1\":\"test notes1\",\"notes2\":\"test note2\",\"notes3\":\"test notes3\"},"
	    		+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"note\":\"test Po note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"note\":\"test buyer note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"note\":\"test invoice note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"note\":\"test additional note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"note\":\"test internal note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null}],"
	    	
	    		+ "}";
	
	 String jStringWithNewIcons = "{\"itemcode\":\"newItemcode30\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	      	   	+ "\"iconDescription\":"
	       		+ "{"
	       		+ "\"madeInCountry\":\"USA\","
	       		+ "\"exteriorProduct\":true,"
	       		+ "\"adaAccessibility\":true,"
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
	       		+ "\"coefficientOfFriction\":true}"
	       		+ "}";
	
	 String jStringWithLagecyIcon = "{\"itemcode\":\"newItemcode7\",\"itemcategory\":\"ATHENA\",\"countryorigin\":\"Italy\",\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		+ "\"iconsystem\":\"NYYYNNNYYYNYNNNNYNNN\","
	      		+ "}";
	 
	 String jStringWithUsageWithoutApplications = 
			   "{\"itemcode\":\"newItemcode9\","
		  		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"]"
	    		//+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
	       		+ "}";
	 	 
	 String jStringFullItemInfo = 
			   "{\"itemcode\":\"newItemcode\","
			    + "\"itemcategory\":\"ATHENA\","
			    + "\"countryorigin\":\"Italy\","
			    + "\"inactivecode\":\"N\","
	    		+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		+ "\"series\":{\"seriesname\":\"Athena\",\"seriescolor\":\"Ash\"},"
	    		+ "\"material\":{\"materialtype\":\"Porcelain\",\"materialcategory\":\"Trim\",\"materialclass\":\"CTSRC\",\"materialstyle\":\"FW\",\"materialfeature\":\"\"},"
	    		+ "\"showonwebsite\":\"Y\","
	    		+ "\"itemtypecode\":\"F\","
	    		+ "\"abccode\":\"C\","
	    		+ "\"itemcode2\":\"testcd2\","
	    		+ "\"inventoryitemcode\":\"UPC7078425\","
	    		+ "\"showonalysedwards\":\"N\","
	    		+ "\"offshade\":\"N\","
	    		+ "\"printlabel\":\"Y\","
	    		+ "\"taxclass\":\"T\","
	    		+ "\"lottype\":\"Y\","
	    		+ "\"updatecode\":\"CERA-CRD\","
	    		+ "\"directship\":\"Y\","
	    		+ "\"dropdate\":2014-03-31,"
	    		+ "\"itemgroupnbr\":5,"
	    		+ "\"priorlastupdated\":\"2014-03-31\","
		    	+ "\"shadevariation\":\"V2\","
	    		+ "\"colorhues\":[\"BEIGE\"],"
	    		+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0},"
	    		+ "\"price\":{\"listprice\":18.3800,\"sellprice\":18.3800,\"pricegroup\":\"2\",\"priceunit\":\"SHT\",\"sellpricemarginpct\":2.0,\"sellpriceroundaccuracy\":2,\"listpricemarginpct\":0.0,\"minmarginpct\":15.0,\"futuresell\":10.0000,\"priorsellprice\":14.7000,\"tempprice\":16.0000,\"tempdatefrom\":2014-06-31,\"tempdatethru\":2014-08-31,\"priorlistprice\":10.0000},"
	    		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
	    		+ "\"testSpecification\":{\"waterabsorption\":0.5,\"scratchresistance\":0.6,\"frostresistance\":\"P\",\"chemicalresistance\":\"P\",\"peiabrasion\":0.7,\"scofwet\":0.8,\"scofdry\":0.2,\"breakingstrength\":0.2,\"scratchstandard\":\"Test\",\"breakingstandard\":\"Test\",\"restricted\":\"N\",\"warpage\":\"Y\",\"wedging\":\"Y\",\"dcof\":0.3,\"thermalshock\":\"Y\",\"bondstrength\":\"test\",\"greenfriendly\":\"N\",\"moh\":0.4,\"leadpoint\":\"N\",\"preconsummer\":0.6,\"posconsummer\":0.9},"
	    		+ "\"relateditemcodes\":null,"
	    		+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
	    		+ "\"packaginginfo\":{\"boxPieces\":4.0,\"boxSF\":0.0,\"boxWeight\":16.8,\"palletBox\":60.0,\"palletSF\":0.0,\"palletWeight\":1007.99994},"
	    		+ "\"productline\":\"test\","
	    		+ "\"iconsystem\":\"YNNYNNNNNNNNNNNNNNNN\","
	    		+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
	    		+ "\"units\":"
	    		+ "{"
	    	   	    + "\"baseunit\":\"SHT\","
	    	   	    + "\"baseisstdsell\":\"Y\","
	    	   	    + "\"baseisstdord\":\"Y\","
	    	   	    + "\"baseisfractqty\":\"N\","
	    	   	    + "\"baseispackunit\":\"Y\","
	    	   	    + "\"baseupc\":3333,"
	    	   	    + "\"baseupcdesc\":\"test\","
	    	   	    + "\"basevolperunit\":44,"
	    	   	    + "\"basewgtperunit\":4.2000,"
	    		+ "\"unit1unit\":\"CTN\",\"unit1ratio\":4.0,\"unit1isstdsell\":\"N\",\"unit1isstdord\":\"N\",\"unit1isfractqty\":\"N\",\"unit1ispackunit\":\"Y\",\"unit1upc\":1234,\"unit1upcdesc\":\"test\",\"unit1wgtperunit\":17.4000,"
	    		+ "\"unit2unit\":\"PLT\",\"unit2ratio\":240.0,\"unit2isstdsell\":\"N\",\"unit2isstdord\":\"N\",\"unit2isfractqty\":\"N\",\"unit2ispackunit\":\"N\",\"unit2upc\":12345,\"unit2upcdesc\":\"test\",\"unit2wgtperunit\":1070.0000,"
	    		+ "\"unit3unit\":\"test\",\"unit3ratio\":10.0,\"unit3isstdsell\":\"N\",\"unit3isstdord\":\"N\",\"unit3isfractqty\":\"N\",\"unit3ispackunit\":\"N\",\"unit3upc\":12345,\"unit3upcdesc\":\"test\",\"unit3wgtperunit\":10.0000,"
	    		+ "\"unit4unit\":\"test\",\"unit4ratio\":10.0,\"unit4isstdsell\":\"N\",\"unit4isstdord\":\"N\",\"unit4isfractqty\":\"N\",\"unit4ispackunit\":\"N\",\"unit4upc\":12353,\"unit4upcdesc\":\"test\",\"unit4wgtperunit\":20.0000},"
	     		//+ "\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorLandedBaseCost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},"
	     		+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":\"TestVendor1\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"leadTime\":60,\"dutyPct\":0.0,\"id\":134585},"
	    		+ "                     {\"vendorOrder\":2,\"vendorName\":\"TestVendor2\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.2500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"leadTime\":60,\"dutyPct\":0.0,\"id\":271520},"
	    		+ "                     {\"vendorOrder\":3,\"vendorName\":\"TestVendor3\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.3500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"leadTime\":60,\"dutyPct\":0.0,\"id\":453680},"
 		        + "                     ],"
	    	
	        	//+ "\"vendors\":{\"vendornbr\":0,\"vendornbr1\":134585,\"vendornbr2\":0,\"vendornbr3\":0,\"vendorxrefcd\":\"ATM40\",\"vendorlistprice\":4.1500,\"vendorpriceunit\":\"SHT\",\"vendorfob\":\"\",\"vendordiscpct\":0.0,\"vendorroundaccuracy\":2,\"vendornetprice\":4.1500,\"vendormarkuppct\":0.0,\"vendorfreightratecwt\":0.0,\"dutypct\":0.0,\"leadtime\":60,\"vendorLandedBaseCost\":4.1500,\"vendordiscpct2\":0.0,\"vendordiscpct3\":0.0},
	    		+ "\"cost\":{\"cost1\":1.0000,\"priorcost\":1.0000},"
	    		//+ "\"imsNewFeature\":{\"grade\":\"First\",\"status\":\"GOOD\",\"body\":\"Red_Body\",\"edge\":\"Tumbled\",\"mpsCode\":\"Drop\",\"designLook\":\"Wood\",\"designStyle\":\"Modern\",\"surfaceApplication\":\"Silk\",\"surfaceType\":\"Cross_Cut\",\"surfaceFinish\":\"Antiquated\",\"warranty\":3,\"recommendedGroutJointMin\":\"1\",\"recommendedGroutJointMax\":\"2\",\"createdDate\":\"2014-05-14\",\"launchedDate\":null,\"droppedDate\":null,\"lastModifiedDate\":null},"
	    		//+ "\"notes\":{\"ponotes\":\"test po note\",\"notes1\":\"test notes1\",\"notes2\":\"test note2\",\"notes3\":\"test notes3\"},"
	    		 + "\"notes\":{\"ponotes\":\"test po note\",\"buyernotes\":\"test note1\",\"internalnotes\":\"test note2\",\"invoicenotes\":\"test note3\"}"
    			//+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"note\":\"test Po note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"note\":\"test buyer note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"note\":\"test invoice note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"note\":\"test additional note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"note\":\"test internal note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null}],"
	    		+ "}";
	 
	 String jStringFullItemAndAssociationInfo = 
			    //basic info
			     "{\"itemcode\":\"newItemcode\","
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
		    	+ "\"itemdesc\":{\"fulldesc\":\"2x2 Athena Mosaic on 12x12 Sheet  Ash(Gray)\",\"itemdesc1\":\"2x2 Athena Mosaic on 12x12 SHT Ash\"},"
	    		+ "\"series\":{\"seriesname\":\"Athena\",\"seriescolor\":\"Ash\"},"
	    		+ "\"material\":{\"materialtype\":\"Porcelain\",\"materialcategory\":\"Trim\",\"materialclass\":\"CTSRC\",\"materialstyle\":\"FW\",\"materialfeature\":\"\"},"
	    		+ "\"dimensions\":{\"nominallength\":12.0,\"nominalwidth\":12.0,\"sizeunits\":\"E\",\"thickness\":\"3/8\",\"thicknessunit\":\"E\",\"length\":\"11-13/16\",\"width\":\"11-13/16\",\"nominalthickness\":0.0},"
	    		+ "\"price\":"
	    		   + "{"
	    		      + "\"listprice\":18.3800,"
	    		      + "\"sellprice\":18.3800,"
	    		      + "\"pricegroup\":\"3\","
	    		      + "\"priceunit\":\"SHT\","
	    		      + "\"sellpricemarginpct\":2.0,"
	    		      + "\"sellpriceroundaccuracy\":2,"
	    		      + "\"listpricemarginpct\":0.0,"
	    		      + "\"minmarginpct\":15.0,"
	    		      + "\"futuresell\":0.0000,"
	    		      + "\"priorsellprice\":14.7000,"
	    		      + "\"tempprice\":15.0000,\"tempdatefrom\":null,\"tempdatethru\":null,\"priorlistprice\":0.0000},"
	    		+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"
	    		+ "\"testSpecification\":{\"waterabsorption\":0.5,\"scratchresistance\":0.6,\"frostresistance\":\"\",\"chemicalresistance\":\"\",\"peiabrasion\":0.7,\"scofwet\":0.8,\"scofdry\":0.0,\"breakingstrength\":0,\"scratchstandard\":\"\",\"breakingstandard\":\"\",\"restricted\":\"N\",\"warpage\":\" \",\"wedging\":\" \",\"dcof\":0.0,\"thermalshock\":\" \",\"bondstrength\":\"\",\"greenfriendly\":\"N\",\"moh\":0.0,\"leadpoint\":\"Y\",\"preconsummer\":0.0,\"posconsummer\":0.0},"
	    		+ "\"relateditemcodes\":null,"
	    		+ "\"purchasers\":{\"purchaser\":\"ALICIAB\",\"purchaser2\":\"GFIL\"},"
	    		+ "\"packaginginfo\":{\"boxPieces\":4.0,\"boxSF\":0.0,\"boxWeight\":16.8,\"palletBox\":60.0,\"palletSF\":0.0,\"palletWeight\":1007.99994},"
	    		+ "\"cost\":{\"cost1\":0.0000,\"priorcost\":0.0000},"
	       		//+ "\"iconsystem\":\"NNNNNNNNNNNNNNNNNNNN\","
	    		+ "\"applications\":{\"residential\":\"FR:WR:CR:SR:PR\",\"lightcommercial\":\"FL:WL:CL:SL:PL\",\"commercial\":\"FC:WC:CC:SC:PC\"},"
	    		+ "\"units\":{\"baseunit\":\"SHT\",\"baseisstdsell\":\"Y\",\"baseisstdord\":\"Y\",\"baseisfractqty\":\"N\",\"baseispackunit\":\"Y\",\"baseupc\":0,\"baseupcdesc\":\"\",\"basevolperunit\":0.0000,\"basewgtperunit\":4.2000,"
	    		           + "\"unit1unit\":\"CTN\",\"unit1ratio\":4.0,\"unit1isstdsell\":\"N\",\"unit1isstdord\":\"N\",\"unit1isfractqty\":\"N\",\"unit1ispackunit\":\"Y\",\"unit1upc\":0,\"unit1upcdesc\":\"\",\"unit1wgtperunit\":17.4000,"
	    		           + "\"unit2unit\":\"PLT\",\"unit2ratio\":240.0,\"unit2isstdsell\":\"N\",\"unit2isstdord\":\"N\",\"unit2isfractqty\":\"N\",\"unit2ispackunit\":\"N\",\"unit2upc\":0,\"unit2upcdesc\":\"\",\"unit2wgtperunit\":1070.0000,"
	    		           + "\"unit3unit\":\"\",\"unit3ratio\":0.0,\"unit3isstdsell\":\"N\",\"unit3isstdord\":\"N\",\"unit3isfractqty\":\"N\",\"unit3ispackunit\":\"N\",\"unit3upc\":0,\"unit3upcdesc\":\"\",\"unit3wgtperunit\":0.0000,"
	    		           + "\"unit4unit\":\"\",\"unit4ratio\":0.0,\"unit4isstdsell\":\"N\",\"unit4isstdord\":\"N\",\"unit4isfractqty\":\"N\",\"unit4ispackunit\":\"N\",\"unit4upc\":0,\"unit4upcdesc\":\"\",\"unit4wgtperunit\":0.0000},"
	    		+ "\"notes\":{\"ponotes\":\"test po note\",\"buyernotes\":\"test notes1\",\"internalnotes\":\"test note2\",\"invoicenotes\":\"test notes3\"},"           
	    		//------ associations ------//
	    		+ "\"colorhues\":[\"BEIGE\",\"YELLOW\", \"GREEN\"],"
		    	/*+ "\"vendors\":"
		    	+ "{"
		    	+ "\"vendornbr\":0,"
		    	+ "\"vendornbr1\":134585,"
		    	+ "\"vendornbr2\":0,"
		    	+ "\"vendorxrefcd\":\"ATM40\","
		    	+ "\"vendorlistprice\":4.1500,"
		    	+ "\"vendorpriceunit\":\"SHT\","
		    	+ "\"vendorfob\":\"\","
		    	+ "\"vendordiscpct\":0.0,"
		    	+ "\"vendorroundaccuracy\":2,"
		    	+ "\"vendornetprice\":4.1500,"
		    	+ "\"vendormarkuppct\":0.0,"
		    	+ "\"vendorfreightratecwt\":0.0,"
		    	+ "\"dutypct\":0.0,"
		    	+ "\"leadtime\":60,"
		    	+ "\"vendorlandedbasecost\":4.1500,"
		    	+ "\"vendordiscpct2\":0.0,"
		    	+ "\"vendordiscpct3\":0.0},"*/
	    		//+ "\"newVendorSystem\":[{\"vendorOrder\":1,\"vendorName\":\"TestVendor1\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.1500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":134585},"
	    		//+ "                     {\"vendorOrder\":2,\"vendorName\":\"TestVendor2\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.2500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":271520},"
	    		//+ "                     {\"vendorOrder\":3,\"vendorName\":\"TestVendor3\",\"vendorName2\":null,\"vendorXrefId\":\"ATM40\",\"vendorListPrice\":4.3500,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0.0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0.0,\"vendorFreightRateCwt\":0.0,\"leadTime\":60,\"dutyPct\":0.0,\"version\":null,\"id\":453680},"
 		        //+ "                     ],"
	    		+ "\"newVendorSystem\":"
		   		+ "["
		   		+ "{\"vendorId\":{\"id\":544394},\"vendorOrder\":1,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"K822751R\",\"vendorListPrice\":12.7,\"vendorNetPrice\":12.7,\"vendorPriceUnit\":\"SHT\",\"vendorFob\":\"\",\"vendorDiscountPct\":0,\"vendorPriceRoundAccuracy\":2,\"vendorMarkupPct\":0,\"vendorFreightRateCwt\":0,\"vendorLandedBaseCost\":12.7,\"leadTime\":0,\"dutyPct\":0},"
		   		+ "{\"vendorId\":{\"id\":null},\"vendorOrder\":2,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"\",\"vendorListPrice\":0,\"vendorNetPrice\":null,\"vendorPriceUnit\":\"0\",\"vendorFob\":\"\",\"vendorDiscountPct\":null,\"vendorPriceRoundAccuracy\":null,\"vendorMarkupPct\":0,\"vendorFreightRateCwt\":0,\"vendorLandedBaseCost\":0,\"leadTime\":null,\"dutyPct\":null},"
		   		+ "{\"vendorId\":{\"id\":null},\"vendorOrder\":3,\"vendorName\":null,\"vendorName2\":null,\"vendorXrefId\":\"\",\"vendorListPrice\":0,\"vendorNetPrice\":null,\"vendorPriceUnit\":\"0\",\"vendorFob\":\"\",\"vendorDiscountPct\":null,\"vendorPriceRoundAccuracy\":null,\"vendorMarkupPct\":0,\"vendorFreightRateCwt\":0,\"vendorLandedBaseCost\":0,\"leadTime\":null,\"dutyPct\":null}"
		   		+ "],"
		   		
	    		+ "\"newFeature\":{\"grade\":\"First\",\"status\":\"GOOD\",\"body\":\"Red_Body\",\"edge\":\"Tumbled\",\"mpsCode\":\"Drop\",\"designLook\":\"Wood\",\"designStyle\":\"Modern\",\"surfaceApplication\":\"Silk\",\"surfaceType\":\"Cross_Cut\",\"surfaceFinish\":\"Antiquated\",\"warranty\":3,\"recommendedGroutJointMin\":\"1\",\"recommendedGroutJointMax\":\"2\",\"createdDate\":\"2014-05-14\",\"launchedDate\":null,\"droppedDate\":null,\"lastModifiedDate\":null},"
	    		//+ "\"newNoteSystem\":[{\"noteType\":\"po\",\"text\":\"test Po note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"buyer\",\"text\":\"test buyer note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"invoice\",\"text\":\"test invoice note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"additional\",\"text\":\"test additional note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null},{\"noteType\":\"internal\",\"text\":\"test internal note\",\"createdDate\":\"2014-05-14\",\"lastModifiedDate\":null}],"
	    		+ "\"iconDescription\":{\"madeInCountry\":\"USA\",\"exteriorProduct\":true,\"adaAccessibility\":true,\"throughColor\":false,\"colorBody\":true,\"inkJet\":false,\"glazed\":true,\"unglazed\":false,\"rectifiedEdge\":true,\"chiseledEdge\":false,\"versaillesPattern\":true,\"recycled\":false,\"postRecycled\":true,\"preRecycled\":false,\"leadPoint\":true,\"greenFriendly\":false,\"coefficientOfFriction\":true}"
		        + "}";
	 
	 @Test
		public void testNothing(){
		}
}
