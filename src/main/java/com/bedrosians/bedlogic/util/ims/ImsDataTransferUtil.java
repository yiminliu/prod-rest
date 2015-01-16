package com.bedrosians.bedlogic.util.ims;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bedrosians.bedlogic.domain.ims.ColorHue;
import com.bedrosians.bedlogic.domain.ims.IconCollection;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.ImsNewFeature;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.domain.ims.embeddable.Applications;
import com.bedrosians.bedlogic.domain.ims.embeddable.Cost;
import com.bedrosians.bedlogic.domain.ims.embeddable.Dimensions;
import com.bedrosians.bedlogic.domain.ims.embeddable.Material;
import com.bedrosians.bedlogic.domain.ims.embeddable.Notes;
import com.bedrosians.bedlogic.domain.ims.embeddable.PackagingInfo;
import com.bedrosians.bedlogic.domain.ims.embeddable.Price;
import com.bedrosians.bedlogic.domain.ims.embeddable.Purchasers;
import com.bedrosians.bedlogic.domain.ims.embeddable.Series;
import com.bedrosians.bedlogic.domain.ims.embeddable.SimilarItemCode;
import com.bedrosians.bedlogic.domain.ims.embeddable.TestSpecification;
import com.bedrosians.bedlogic.domain.ims.embeddable.Units;
import com.bedrosians.bedlogic.domain.ims.embeddable.VendorInfo;
import com.bedrosians.bedlogic.domain.ims.enums.MpsCode;
import com.bedrosians.bedlogic.domain.ims.enums.OriginCountry;
import com.bedrosians.bedlogic.exception.InputParamException;
import com.bedrosians.bedlogic.util.enums.DBOperation;


public class ImsDataTransferUtil {

	public static Ims transferItemInfo(Ims itemToDB, Ims itemFromInput, DBOperation operation){
		if(itemFromInput == null)
	       throw new InputParamException("The input is empty, nothing to " + operation.getDescription());	
		if(itemToDB == null) 
		   itemToDB = new Ims(itemFromInput.getItemcode().toUpperCase());		
		transferProperty(itemToDB, itemFromInput, operation);
		if(operation != null && operation.equals(DBOperation.CREATE))
	  	   transferComponent(itemToDB, itemFromInput);
		else if(operation != null && operation.equals(DBOperation.UPDATE))		
		   transferComponentForUpdate(itemToDB, itemFromInput);
		transferAssociation(itemToDB, itemFromInput, operation);	
		return itemToDB;
	}
	
	private static synchronized void transferAssociation(Ims itemToDB, Ims itemFromInput, DBOperation operation){
	  try{
	 	 transferImsNewFeature(itemToDB, itemFromInput, operation); 
		 transferIconCollection(itemToDB, itemFromInput, operation);
		 transferColorHues(itemToDB, itemFromInput, operation);
		 transferNewVendorSystem(itemToDB, itemFromInput, operation);
	  }
	  catch(Exception e){
		   throw new InputParamException("Error occured while transferAssociation(): " + e.getMessage(), e);	 
	  }	
    }
	
    private static synchronized void transferColorHues(Ims itemToDB, Ims itemFromInput, DBOperation operation) {
	   List<ColorHue> inputColorHues = itemFromInput.getColorhues();
	   //if colorhues is not available in input data, then obtain it from colorCategory in input data
	   if((inputColorHues == null || inputColorHues.isEmpty()) && itemFromInput.getColorcategory() != null && !itemFromInput.getColorcategory().isEmpty()){
		  inputColorHues = ImsDataUtil.convertColorCategoryToColorHueObjects(itemFromInput.getColorcategory());
	   }
	   if(inputColorHues != null && !inputColorHues.isEmpty()){
		  /*** create new colorHue ***/ 
	 	 if(operation.equals(DBOperation.CREATE) || //Brand new item
		   (operation.equals(DBOperation.UPDATE) && (itemToDB.getColorhues() == null || itemToDB.getColorhues().isEmpty()))){ //existing item, but brand new ColorHue
		    for(ColorHue color : inputColorHues){	
  		        if(color != null && color.getColorHue() != null && !color.getColorHue().isEmpty())
		           itemToDB.addColorhue(color);	
	        }
		  }
	 	  /*** update existing colorHue ***/
		  else if(operation.equals(DBOperation.UPDATE)){
		    for(int i = 0; i < inputColorHues.size(); i++){
			    ColorHue colorHue = inputColorHues.get(i);
			    if(i >= itemToDB.getColorhues().size())
				   itemToDB.getColorhues().add(i, new ColorHue(itemToDB.getItemcode(), itemToDB)); 
			    if(colorHue.getColorHue() != null) 
				   itemToDB.getColorhues().get(i).setColorHue(colorHue.getColorHue());
		    }	   
		  }
	  }
	}
  
    private static synchronized void transferNewVendorSystem(Ims itemToDB, Ims itemFromInput, DBOperation operation) {
    	List<Vendor> inputItemVendors = itemFromInput.getNewVendorSystem();
		VendorInfo legancyVendorInfo = itemFromInput.getVendors();
		/****** newVendorSystem data is included in input ******/
  		if(inputItemVendors != null && !inputItemVendors.isEmpty()){
  		   //// create new vendor record /////	
  		   if(operation.equals(DBOperation.CREATE)|| //brand new item
  		     (operation.equals(DBOperation.UPDATE) && (itemToDB.getNewVendorSystem() == null || itemToDB.getNewVendorSystem().isEmpty()))){ //existing item, but brand new ItemVendors
  			  for(Vendor vendor : inputItemVendors){
  			      if(vendor != null && !vendor.isEmpty()){
  			    	setCalculatedVendorData(itemFromInput, vendor); 
  				     itemToDB.addNewVendorSystem(vendor);	
  				     //Populate vendor info in ims
  				     if((legancyVendorInfo == null || legancyVendorInfo.getVendornbr1() == null || legancyVendorInfo.getVendornbr1() == 0) && vendor.getVendorOrder() == 1){
  				         itemToDB.setVendors(ImsDataUtil.convertNewVendorToLegancyVendorInfo(vendor));	
  				     }
  				  }   
  			   }    
  		   }
  		   ///// update existing item vendor record /////
           else if(operation.equals(DBOperation.UPDATE)){ //update existing item vendor
      		   int sizeOfItemVendors = itemToDB.getNewVendorSystem().size();
  			   for(int i = 0; i < inputItemVendors.size(); i++){
  				   Vendor vendor = inputItemVendors.get(i);
  				   setCalculatedVendorData(itemFromInput, vendor);
  				   if(vendor.getVendorOrder() == 1 && (vendor.getVendorId() == null || vendor.getVendorId().getId() == null || vendor.getVendorId().getId() == 0))
  					  throw new InputParamException("Error: No vendor ID is provided.");
  				   //Populate vendor info in ims table
  				   if((legancyVendorInfo == null || legancyVendorInfo.getVendornbr1() == null || legancyVendorInfo.getVendornbr1() == 0) && vendor.getVendorOrder() == 1)
  				       itemToDB.setVendors(ImsDataUtil.convertNewVendorToLegancyVendorInfo(vendor));				 
  				   if(sizeOfItemVendors <= i){
  					  itemToDB.addNewVendorSystem(vendor); //there more vendor in the new item than the current one
  					  continue;
  				   }	  
  				   if(itemToDB.getNewVendorSystem().get(i).getVendorId() == null)
  					   itemToDB.getNewVendorSystem().get(i).setVendorId(vendor.getVendorId());
  				   if(vendor.getDutyPct() != null) itemToDB.getNewVendorSystem().get(i).setDutyPct(vendor.getDutyPct());
  				   if(vendor.getLeadTime() != null) itemToDB.getNewVendorSystem().get(i).setLeadTime(vendor.getLeadTime());
  				   if(vendor.getVendorDiscountPct() != null) itemToDB.getNewVendorSystem().get(i).setVendorDiscountPct(vendor.getVendorDiscountPct());
  				   if(vendor.getVendorFob() != null) itemToDB.getNewVendorSystem().get(i).setVendorFob(vendor.getVendorFob().trim());
  				   if(vendor.getVendorFreightRateCwt() != null)itemToDB.getNewVendorSystem().get(i).setVendorFreightRateCwt(vendor.getVendorFreightRateCwt());
  				   if(vendor.getVendorLandedBaseCost() != null) itemToDB.getNewVendorSystem().get(i).setVendorLandedBaseCost(vendor.getVendorLandedBaseCost());
  				   if(vendor.getVendorListPrice() != null) itemToDB.getNewVendorSystem().get(i).setVendorListPrice(vendor.getVendorListPrice());
  				   if(vendor.getVendorMarkupPct() != null) itemToDB.getNewVendorSystem().get(i).setVendorMarkupPct(vendor.getVendorMarkupPct());
  				   if(vendor.getVendorName() != null) itemToDB.getNewVendorSystem().get(i).setVendorName(vendor.getVendorName().trim());
  				   if(vendor.getVendorName2() != null) itemToDB.getNewVendorSystem().get(i).setVendorName2(vendor.getVendorName2().trim());
  				   if(vendor.getVendorNetPrice() != null) itemToDB.getNewVendorSystem().get(i).setVendorNetPrice(vendor.getVendorNetPrice());
  				   if(vendor.getVendorOrder() != null) itemToDB.getNewVendorSystem().get(i).setVendorOrder(vendor.getVendorOrder());
  				   if(vendor.getVendorPriceRoundAccuracy() != null) itemToDB.getNewVendorSystem().get(i).setVendorPriceRoundAccuracy(vendor.getVendorPriceRoundAccuracy());
  				   if(vendor.getVendorPriceUnit() != null) itemToDB.getNewVendorSystem().get(i).setVendorPriceUnit(vendor.getVendorPriceUnit());
  				   if(vendor.getVendorXrefId() != null) itemToDB.getNewVendorSystem().get(i).setVendorXrefId(vendor.getVendorXrefId());
  				   //Populate vendor info in ims
			       if((legancyVendorInfo == null || legancyVendorInfo.getVendornbr1() == null || legancyVendorInfo.getVendornbr1() == 0) && vendor.getVendorOrder() == 1){
				       itemToDB.setVendors(ImsDataUtil.convertNewVendorToLegancyVendorInfo(vendor));	
				   }
  		        }
  		   }	   
  		}
  		/**** No newVendorSystem data is included in input. Populate Ims_Item_Vendor table with the ims vendors info ****/ 
  		else if((legancyVendorInfo != null && legancyVendorInfo.getVendornbr1() != null) && (inputItemVendors == null || inputItemVendors.isEmpty())){
  			//ProductVendor vendor = convertImslegancyVendorInfoToItemVendor(legancyVendorInfo);
  			if(itemToDB.getNewVendorSystem() == null || itemToDB.getNewVendorSystem().isEmpty())
  			   itemToDB.addNewVendorSystem(new Vendor());
  			if(legancyVendorInfo.getVendornbr1() != null) itemToDB.getNewVendorSystem().get(0).setId(legancyVendorInfo.getVendornbr1());
  			if(legancyVendorInfo.getVendorxrefcd() != null) itemToDB.getNewVendorSystem().get(0).setVendorXrefId(legancyVendorInfo.getVendorxrefcd());
  			if(legancyVendorInfo.getVendorfob() != null) itemToDB.getNewVendorSystem().get(0).setVendorFob(legancyVendorInfo.getVendorfob());
  			if(legancyVendorInfo.getDutypct() != null) itemToDB.getNewVendorSystem().get(0).setDutyPct(legancyVendorInfo.getDutypct());
  			if(legancyVendorInfo.getVendorlistprice() != null) itemToDB.getNewVendorSystem().get(0).setLeadTime(legancyVendorInfo.getLeadtime());
  			if(legancyVendorInfo.getVendorlistprice() != null) itemToDB.getNewVendorSystem().get(0).setVendorListPrice(legancyVendorInfo.getVendorlistprice());
  			if(legancyVendorInfo.getVendorpriceunit() != null) itemToDB.getNewVendorSystem().get(0).setVendorPriceUnit(legancyVendorInfo.getVendorpriceunit());
  			if(legancyVendorInfo.getVendordiscpct() != null) itemToDB.getNewVendorSystem().get(0).setVendorDiscountPct(legancyVendorInfo.getVendordiscpct());
  		    if(legancyVendorInfo.getVendorroundaccuracy() != null) itemToDB.getNewVendorSystem().get(0).setVendorPriceRoundAccuracy(legancyVendorInfo.getVendorroundaccuracy());
  		    if(legancyVendorInfo.getVendornetprice() != null) itemToDB.getNewVendorSystem().get(0).setVendorNetPrice(legancyVendorInfo.getVendornetprice());
  	        if(legancyVendorInfo.getVendormarkuppct() != null) itemToDB.getNewVendorSystem().get(0).setVendorMarkupPct(legancyVendorInfo.getVendormarkuppct());
  		    if(legancyVendorInfo.getVendorfreightratecwt() != null) itemToDB.getNewVendorSystem().get(0).setVendorFreightRateCwt(legancyVendorInfo.getVendorfreightratecwt());
  		    if(legancyVendorInfo.getVendorlandedbasecost() != null) itemToDB.getNewVendorSystem().get(0).setVendorLandedBaseCost(legancyVendorInfo.getVendorlandedbasecost());
  		    itemToDB.getNewVendorSystem().get(0).setVendorOrder(1);
  			//vendor.setVendorOrder(1);
  			//itemToDB.addNewVendorSystem(vendor);
  		}
    }	
 
  	public static void setCalculatedVendorData(Ims item, Vendor vendor){
  	   if(vendor.getVendorListPrice() != null){ //calculate net price
			  if(vendor.getVendorDiscountPct() != null){
		         BigDecimal netPrice = new BigDecimal(vendor.getVendorListPrice().floatValue() * ((100 - vendor.getVendorDiscountPct())/100.00));
		         vendor.setVendorNetPrice(netPrice);
		         if(item.getUnits() != null && item.getUnits().getStdratio() != null && item.getUnits().getBasewgtperunit() != null){
		            BigDecimal landedBaseCost = new BigDecimal(netPrice.floatValue() * 
		    		                                         ((100 + vendor.getVendorMarkupPct())/100.00/item.getUnits().getStdratio()) + 
		    		                                         vendor.getVendorFreightRateCwt() *
		    		                                         item.getUnits().getBasewgtperunit().floatValue()/100.00);
		            vendor.setVendorLandedBaseCost(landedBaseCost);
		         }   
			 }   
			 else 
			   vendor.setVendorNetPrice(vendor.getVendorListPrice());
	    }
  	} 	
  	
  	public static void setCalculatedVendorData(Ims item, VendorInfo vendorInfo){
   	   if(vendorInfo.getVendorlistprice() != null){ //calculate net price
 			  if(vendorInfo.getVendordiscpct() != null){
 		         BigDecimal netPrice = new BigDecimal(vendorInfo.getVendorlistprice().floatValue() * ((100 - vendorInfo.getVendordiscpct())/100.00));
 		         vendorInfo.setVendornetprice(netPrice);
 		         if(item.getUnits() != null && item.getUnits().getStdratio() != null && item.getUnits().getBasewgtperunit() != null){
 		            BigDecimal landedBaseCost = new BigDecimal(netPrice.floatValue() * 
 		    		                                         ((100 + vendorInfo.getVendormarkuppct())/100.00/item.getUnits().getStdratio()) + 
 		    		                                         vendorInfo.getVendorfreightratecwt() *
 		    		                                         item.getUnits().getBasewgtperunit().floatValue()/100.00);
 		           vendorInfo.setVendorlandedbasecost(landedBaseCost);
 		         }   
 			 }   
 			 else 
 				vendorInfo.setVendornetprice(vendorInfo.getVendorlistprice());
 	   }
   	} 	

  	private static synchronized void transferImsNewFeature(Ims itemToDB, Ims itemFromInput, DBOperation operation) {
		ImsNewFeature inputNewFeature = itemFromInput.getNewFeature();
	    if(inputNewFeature != null && !inputNewFeature.isEmpty()){
	       if(operation.equals(DBOperation.CREATE) || //brand new Product
	         (operation.equals(DBOperation.UPDATE) && itemToDB.getNewFeature() == null)){ //create a brand new NewImsFeature for an existing Product 
	          if(inputNewFeature.getCreatedDate() == null)	
		         inputNewFeature.setCreatedDate(new Date());
	         itemToDB.addNewFeature(inputNewFeature);
	       }  
	       else if(operation.equals(DBOperation.UPDATE)){ //update existing NewImsFeature
	    	 if(inputNewFeature.getBody() != null) itemToDB.getNewFeature().setBody(inputNewFeature.getBody());
	    	 if(inputNewFeature.getDesignLook() != null) itemToDB.getNewFeature().setDesignLook(inputNewFeature.getDesignLook());
	    	 if(inputNewFeature.getDesignStyle() != null) itemToDB.getNewFeature().setDesignStyle(inputNewFeature.getDesignStyle());
	      	 if(inputNewFeature.getDroppedDate() != null) itemToDB.getNewFeature().setDroppedDate(inputNewFeature.getDroppedDate());
	    	 if(inputNewFeature.getEdge() != null) itemToDB.getNewFeature().setEdge(inputNewFeature.getEdge());
	    	 if(inputNewFeature.getGrade() != null) itemToDB.getNewFeature().setGrade(inputNewFeature.getGrade());
	      	 if(inputNewFeature.getLaunchedDate() != null) itemToDB.getNewFeature().setLaunchedDate(inputNewFeature.getLaunchedDate());
	    	 if(inputNewFeature.getMpsCode() != null) itemToDB.getNewFeature().setMpsCode(inputNewFeature.getMpsCode());
	    	 if(inputNewFeature.getRecommendedGroutJointMax() != null) itemToDB.getNewFeature().setRecommendedGroutJointMax(inputNewFeature.getRecommendedGroutJointMax());
	    	 if(inputNewFeature.getRecommendedGroutJointMin() != null) itemToDB.getNewFeature().setRecommendedGroutJointMin(inputNewFeature.getRecommendedGroutJointMin());
	    	 if(inputNewFeature.getStatus() != null) itemToDB.getNewFeature().setStatus(inputNewFeature.getStatus());
	    	 if(inputNewFeature.getSurfaceApplication() != null) itemToDB.getNewFeature().setSurfaceApplication(inputNewFeature.getSurfaceApplication());
	      	 if(inputNewFeature.getSurfaceFinish() != null) itemToDB.getNewFeature().setSurfaceFinish(inputNewFeature.getSurfaceFinish());
	    	 if(inputNewFeature.getSurfaceType() != null) itemToDB.getNewFeature().setSurfaceType(inputNewFeature.getSurfaceType());
	    	 if(inputNewFeature.getWarranty() != null) itemToDB.getNewFeature().setWarranty(inputNewFeature.getWarranty());
	    	 itemToDB.getNewFeature().setLastModifiedDate(new Date());
	      } 	
	   }
	}

	private static synchronized void transferIconCollection(Ims itemToDB, Ims itemFromInput, DBOperation operation) {
		IconCollection inputIconCollection = itemFromInput.getIconDescription();
		String legacyIcon = itemFromInput.getIconsystem();
	    /***** IconCollection is included in input *****/
		if(inputIconCollection != null && !inputIconCollection.isEmpty()){
		   if(operation.equals(DBOperation.CREATE) || //Brand new Product
		     (operation.equals(DBOperation.UPDATE) && (itemToDB.getIconDescription() == null || itemToDB.getIconDescription().isEmpty()))){ //existing Product, but brand new inputIconCollection
		      itemToDB.addIconDescription(inputIconCollection);
		   }
		   else if(operation.equals(DBOperation.UPDATE)){
			  if(itemToDB.getIconDescription() == null)
				 itemToDB.setIconDescription(new IconCollection());
			  if(inputIconCollection.getAdaAccessibility() != null) itemToDB.getIconDescription().setAdaAccessibility(inputIconCollection.getAdaAccessibility());
			  if(inputIconCollection.getChiseledEdge() != null) itemToDB.getIconDescription().setChiseledEdge(inputIconCollection.getChiseledEdge());
			  if(inputIconCollection.getCoefficientOfFriction() != null) itemToDB.getIconDescription().setCoefficientOfFriction(inputIconCollection.getCoefficientOfFriction());
			  if(inputIconCollection.getColorBody() != null) itemToDB.getIconDescription().setColorBody(inputIconCollection.getColorBody());
			  if(inputIconCollection.getExteriorProduct() != null) itemToDB.getIconDescription().setExteriorProduct(inputIconCollection.getExteriorProduct());
			  if(inputIconCollection.getGlazed() != null) itemToDB.getIconDescription().setGlazed(inputIconCollection.getGlazed());
			  if(inputIconCollection.getGreenFriendly() != null) itemToDB.getIconDescription().setGreenFriendly(inputIconCollection.getGreenFriendly());
			  if(inputIconCollection.getInkJet() != null) itemToDB.getIconDescription().setInkJet(inputIconCollection.getInkJet());
			  if(inputIconCollection.getLeadPoint() != null) itemToDB.getIconDescription().setLeadPoint(inputIconCollection.getLeadPoint());
			  if(inputIconCollection.getPostRecycled() != null) itemToDB.getIconDescription().setPostRecycled(inputIconCollection.getPostRecycled());
			  if(inputIconCollection.getPreRecycled() != null) itemToDB.getIconDescription().setPreRecycled(inputIconCollection.getPreRecycled());
			  if(inputIconCollection.getRectifiedEdge() != null) itemToDB.getIconDescription().setRectifiedEdge(inputIconCollection.getRectifiedEdge());
			  if(inputIconCollection.getRecycled() != null) itemToDB.getIconDescription().setRecycled(inputIconCollection.getRecycled());
			  if(inputIconCollection.getThroughColor() != null) itemToDB.getIconDescription().setThroughColor(inputIconCollection.getThroughColor());
			  if(inputIconCollection.getMadeInCountry() != null) itemToDB.getIconDescription().setMadeInCountry(inputIconCollection.getMadeInCountry());
			  if(inputIconCollection.getUnglazed() != null) itemToDB.getIconDescription().setUnglazed(inputIconCollection.getUnglazed());
			  if(inputIconCollection.getVersaillesPattern() != null) itemToDB.getIconDescription().setVersaillesPattern(inputIconCollection.getVersaillesPattern());
		   }
		   itemToDB.setIconsystem(inputIconCollection.toLegancyIcons());
	    }
		/***** No IconCollection is included in input, but legacy icon info is included in input *****/
	    else if(legacyIcon != null && !legacyIcon.isEmpty()){
	          IconCollection iconCollection = ImsDataUtil.convertLegacyIconsToIconCollection(legacyIcon);
	          if(operation.equals(DBOperation.CREATE) || //Brand new item
		         (operation.equals(DBOperation.UPDATE) && (itemToDB.getIconDescription() == null || itemToDB.getIconDescription().isEmpty()))){ //existing Product, but brand new inputIconCollection
	               if(iconCollection != null)
		 	          itemToDB.addIconDescription(iconCollection);
		      }	
	          else if(operation.equals(DBOperation.UPDATE)){
			     if(iconCollection.getAdaAccessibility() != null) itemToDB.getIconDescription().setAdaAccessibility(iconCollection.getAdaAccessibility());
			     if(iconCollection.getChiseledEdge() != null) itemToDB.getIconDescription().setChiseledEdge(iconCollection.getChiseledEdge());
			     if(iconCollection.getCoefficientOfFriction() != null) itemToDB.getIconDescription().setCoefficientOfFriction(iconCollection.getCoefficientOfFriction());
			     if(iconCollection.getColorBody() != null) itemToDB.getIconDescription().setColorBody(iconCollection.getColorBody());
			     if(iconCollection.getExteriorProduct() != null) itemToDB.getIconDescription().setExteriorProduct(iconCollection.getExteriorProduct());
			     if(iconCollection.getGlazed() != null) itemToDB.getIconDescription().setGlazed(iconCollection.getGlazed());
			     if(iconCollection.getGreenFriendly() != null) itemToDB.getIconDescription().setGreenFriendly(iconCollection.getGreenFriendly());
		    	 if(iconCollection.getInkJet() != null) itemToDB.getIconDescription().setInkJet(iconCollection.getInkJet());
			     if(iconCollection.getLeadPoint() != null) itemToDB.getIconDescription().setLeadPoint(iconCollection.getLeadPoint());
			     if(iconCollection.getPostRecycled() != null) itemToDB.getIconDescription().setPostRecycled(iconCollection.getPostRecycled());
			     if(iconCollection.getPreRecycled() != null) itemToDB.getIconDescription().setPreRecycled(iconCollection.getPreRecycled());
			     if(iconCollection.getRectifiedEdge() != null) itemToDB.getIconDescription().setRectifiedEdge(iconCollection.getRectifiedEdge());
			     if(iconCollection.getRecycled() != null) itemToDB.getIconDescription().setRecycled(iconCollection.getRecycled());
			     if(iconCollection.getThroughColor() != null) itemToDB.getIconDescription().setThroughColor(iconCollection.getThroughColor());
			     if(iconCollection.getMadeInCountry() != null) itemToDB.getIconDescription().setMadeInCountry(iconCollection.getMadeInCountry());
			     if(iconCollection.getUnglazed() != null) itemToDB.getIconDescription().setUnglazed(iconCollection.getUnglazed());
			     if(iconCollection.getVersaillesPattern() != null) itemToDB.getIconDescription().setVersaillesPattern(iconCollection.getVersaillesPattern());
		     }
	   }
	}
	
	private static synchronized void transferComponent(Ims itemToDB, Ims itemFromInput) {
	   try{
		  if(itemFromInput.getApplications() != null) 
		     itemToDB.setApplications(itemFromInput.getApplications());
		  else if(itemFromInput.getUsage() != null) 
		     itemToDB.setApplications(ImsDataUtil.convertUsageToApplications(itemFromInput.getUsage()));	
		  if(itemFromInput.getCost() != null) itemToDB.setCost(itemFromInput.getCost());
		  if(itemFromInput.getDimensions() != null) itemToDB.setDimensions(itemFromInput.getDimensions());
		  if(itemFromInput.getItemdesc() != null) itemToDB.setItemdesc(itemFromInput.getItemdesc());
		  if(itemFromInput.getMaterial() != null) itemToDB.setMaterial(itemFromInput.getMaterial());
		  if(itemFromInput.getNotes() != null) itemToDB.setNotes(itemFromInput.getNotes());
		  if(itemFromInput.getPackaginginfo() != null) itemToDB.setPackaginginfo(itemFromInput.getPackaginginfo());
		  //if(itemFromInput.getPriorVendor() != null) itemToDB.setPriorVendor(itemFromInput.getPriorVendor());
		  if(itemFromInput.getPurchasers() != null) itemToDB.setPurchasers(itemFromInput.getPurchasers());
		  if(itemFromInput.getRelateditemcodes() != null) itemToDB.setRelateditemcodes(itemFromInput.getRelateditemcodes());
		  if(itemFromInput.getSeries() != null) itemToDB.setSeries(itemFromInput.getSeries());
		  if(itemFromInput.getTestSpecification() != null) itemToDB.setTestSpecification(itemFromInput.getTestSpecification());
		  if(itemFromInput.getVendors() != null && !itemFromInput.getVendors().isDefault()) itemToDB.setVendors(itemFromInput.getVendors());
		  if(itemFromInput.getPrice() != null){
			 Price price = itemFromInput.getPrice();
			 if(price.getSellpricemarginpct() == null)
				price.setSellpricemarginpct(0f);
			 if(price.getSellpriceroundaccuracy() == null)
				price.setSellpriceroundaccuracy(0);
			 if(price.getListpricemarginpct() == null)
				price.setListpricemarginpct(0f);
			itemToDB.setPrice(price);
		  }
		  if(itemFromInput.getUnits() != null && !itemFromInput.getUnits().isDefault()){
		  	 Units units = itemFromInput.getUnits();
			if(units.getBaseunit() == null)
			   units.setBaseunit("PCS");
			
			itemToDB.getUnits().setOrdunit(ImsDataUtil.getStandardOrderUnit(itemFromInput));
			itemToDB.getUnits().setOrdratio(ImsDataUtil.getBaseToOrderRatio(itemFromInput));
			itemToDB.getUnits().setStdunit(ImsDataUtil.getStandardSellUnit(itemFromInput));
			itemToDB.getUnits().setStdratio(ImsDataUtil.getBaseToSellRatio(itemFromInput));
					
			itemToDB.setUnits(units);
		  }
	   }
	   catch(Exception e){
		  throw new InputParamException("Error occured while transferComponent(): " + e.getMessage(), e.getCause());	
	   }		
	}

	private static synchronized void transferProperty(Ims itemToDB, Ims itemFromInput, DBOperation operation){
		  try{	
			if(itemFromInput.getAbccode() != null) itemToDB.setAbccode(itemFromInput.getAbccode());
			if(itemFromInput.getCountryorigin() != null) itemToDB.setCountryorigin(itemFromInput.getCountryorigin());
			if(itemFromInput.getDirectship() != null) itemToDB.setDirectship(itemFromInput.getDirectship());
			if(itemFromInput.getDropdate() != null) itemToDB.setDropdate(itemFromInput.getDropdate());
			if(itemFromInput.getIconsystem() != null) itemToDB.setIconsystem(itemFromInput.getIconsystem());
			if(itemFromInput.getInactivecode() != null) itemToDB.setInactivecode(itemFromInput.getInactivecode());
			if(itemFromInput.getInventoryitemcode() != null) itemToDB.setInventoryitemcode(itemFromInput.getInventoryitemcode());
			if(itemFromInput.getItemcode2() != null) itemToDB.setItemcode2(itemFromInput.getItemcode2());
			if(itemFromInput.getItemgroupnbr() != null) itemToDB.setItemgroupnbr(itemFromInput.getItemgroupnbr());
			if(itemFromInput.getItemtypecode() != null) itemToDB.setItemtypecode(itemFromInput.getItemtypecode());	
			if(itemFromInput.getItemcategory() != null) itemToDB.setItemcategory(itemFromInput.getItemcategory());
			if(itemFromInput.getLottype() != null) itemToDB.setLottype(itemFromInput.getLottype());
			if(itemFromInput.getOffshade() != null) itemToDB.setOffshade(itemFromInput.getOffshade());
			if(itemFromInput.getPrintlabel() != null) itemToDB.setPrintlabel(itemFromInput.getPrintlabel());
			if(itemFromInput.getShadevariation() != null) itemToDB.setShadevariation(itemFromInput.getShadevariation());
			if(itemFromInput.getShowonalysedwards() != null) itemToDB.setShowonalysedwards(itemFromInput.getShowonalysedwards());
			if(itemFromInput.getShowonwebsite() != null) itemToDB.setShowonwebsite(itemFromInput.getShowonwebsite());
			if(itemFromInput.getTaxclass() != null) itemToDB.setTaxclass(itemFromInput.getTaxclass());
			if(itemFromInput.getUpdatecode() != null) itemToDB.setUpdatecode(itemFromInput.getUpdatecode());
			if(itemFromInput.getProductline() != null) itemToDB.setProductline(itemFromInput.getProductline());
			//if(itemFromInput.getPriorlastupdated() != null) itemToDB.setPriorlastupdated(itemFromInput.getPriorlastupdated());
			if(operation.equals(DBOperation.UPDATE))
			   itemToDB.setPriorlastupdated(new Date());
			//if(itemFromInput.getSubtype() != null) itemToDB.setSubtype(itemFromInput.getSubtype());
			//if(itemFromInput.getType() != null) itemToDB.setType(itemFromInput.getType());
			if(itemFromInput.getColorcategory() != null && !itemFromInput.getColorcategory().isEmpty())
				itemToDB.setColorcategory(itemFromInput.getColorcategory());
			else if(itemFromInput.getColorhues() != null || !itemFromInput.getColorhues().isEmpty())
			   	itemToDB.setColorcategory(ImsDataUtil.convertColorHuesToColorCategory(itemFromInput.getColorhues()));
		  }
		  catch(Exception e){
				  throw new InputParamException("Error occured while transferProperty(): " + e.getMessage(), e.getCause());	
		  }	
	}
		
	private static synchronized void transferComponentForUpdate(Ims itemToDB, Ims itemFromInput) {
	   try{
		   //description
		   if(itemFromInput.getItemdesc() != null){
			  if(itemFromInput.getItemdesc().getFulldesc() != null) 
			 	 itemToDB.getItemdesc().setFulldesc(itemFromInput.getItemdesc().getFulldesc());
			  if(itemFromInput.getItemdesc().getItemdesc1() != null) 
				  itemToDB.getItemdesc().setItemdesc1(itemFromInput.getItemdesc().getItemdesc1());
		   }
		   //material
		   if(itemFromInput.getMaterial() != null){
			  if(itemToDB.getMaterial() == null)
			     itemToDB.setMaterial(new Material()); 
			  if(itemFromInput.getMaterial().getMaterialcategory() != null) 
				 itemToDB.getMaterial().setMaterialcategory(itemFromInput.getMaterial().getMaterialcategory());
			  if(itemFromInput.getMaterial().getMaterialclass() != null) 
				 itemToDB.getMaterial().setMaterialclass(itemFromInput.getMaterial().getMaterialclass());
			  if(itemFromInput.getMaterial().getMaterialfeature() != null) 
				 itemToDB.getMaterial().setMaterialfeature(itemFromInput.getMaterial().getMaterialfeature());
			  if(itemFromInput.getMaterial().getMaterialstyle() != null) 
			   	 itemToDB.getMaterial().setMaterialstyle(itemFromInput.getMaterial().getMaterialstyle());
			  if(itemFromInput.getMaterial().getMaterialtype() != null) 
				 itemToDB.getMaterial().setMaterialtype(itemFromInput.getMaterial().getMaterialtype());
		   }
		   //series
		   if(itemFromInput.getSeries() != null){
			  if(itemToDB.getSeries() == null)
				 itemToDB.setSeries(new Series()); 
			  if(itemFromInput.getSeries().getSeriesname() != null) 
				 itemToDB.getSeries().setSeriesname(itemFromInput.getSeries().getSeriesname());
			  if(itemFromInput.getSeries().getSeriescolor() != null) 
				 itemToDB.getSeries().setSeriescolor(itemFromInput.getSeries().getSeriescolor());
		   }
		   //dimension
		   if(itemFromInput.getDimensions() != null){
			  if(itemToDB.getDimensions() == null)
			     itemToDB.setDimensions(new Dimensions()); 
	   	      if(itemFromInput.getDimensions().getLength() != null) 
			     itemToDB.getDimensions().setLength(itemFromInput.getDimensions().getLength());
		  	  if(itemFromInput.getDimensions().getWidth() != null) 
		  		 itemToDB.getDimensions().setWidth(itemFromInput.getDimensions().getWidth());
		  	  if(itemFromInput.getDimensions().getThickness() != null) 
			  	 itemToDB.getDimensions().setThickness(itemFromInput.getDimensions().getThickness());
			  if(itemFromInput.getDimensions().getNominallength() != null) 
		  		 itemToDB.getDimensions().setNominallength(itemFromInput.getDimensions().getNominallength());
		  	  if(itemFromInput.getDimensions().getNominalthickness() != null) 
		  		 itemToDB.getDimensions().setNominalthickness(itemFromInput.getDimensions().getNominalthickness());
		 	  if(itemFromInput.getDimensions().getNominalwidth() != null) 
		 		 itemToDB.getDimensions().setNominalwidth(itemFromInput.getDimensions().getNominalwidth());
		  	  if(itemFromInput.getDimensions().getSizeunits() != null) 
			  	 itemToDB.getDimensions().setSizeunits(itemFromInput.getDimensions().getSizeunits());
			  if(itemFromInput.getDimensions().getThicknessunit() != null) 
		  		 itemToDB.getDimensions().setThicknessunit(itemFromInput.getDimensions().getThicknessunit());
		   }
		   //price 
		   if(itemFromInput.getPrice() != null && !itemFromInput.getPrice().isDefault()){
			  if(itemToDB.getPrice() == null)
				itemToDB.setPrice(new Price());
			  Price newPrice = itemFromInput.getPrice();
		      if(newPrice.getFuturesell() != null) 
				 itemToDB.getPrice().setFuturesell(newPrice.getFuturesell());
			  if(newPrice.getListprice() != null) 
			 	 itemToDB.getPrice().setListprice(newPrice.getListprice());
			  if(newPrice.getListpricemarginpct() != null && newPrice.getListpricemarginpct() != 0F) 
			 	 itemToDB.getPrice().setListpricemarginpct(newPrice.getListpricemarginpct());
			  if(newPrice.getSellprice() != null) 
				 itemToDB.getPrice().setSellprice(newPrice.getSellprice());
			  if(newPrice.getSellpricemarginpct() != null && newPrice.getSellpricemarginpct() != 0F) 
				 itemToDB.getPrice().setSellpricemarginpct(newPrice.getSellpricemarginpct());
			  if(newPrice.getSellpriceroundaccuracy() != null && newPrice.getSellpriceroundaccuracy() != 0) 
				 itemToDB.getPrice().setSellpriceroundaccuracy(newPrice.getSellpriceroundaccuracy());
			  if(newPrice.getMinmarginpct() != null) 
				 itemToDB.getPrice().setMinmarginpct(newPrice.getMinmarginpct());
			  if(newPrice.getPricegroup() != null) 
				 itemToDB.getPrice().setPricegroup(newPrice.getPricegroup());
			  if(newPrice.getPriceunit() != null) 
				 itemToDB.getPrice().setPriceunit(newPrice.getPriceunit());
			  if(newPrice.getPriorlistprice() != null) 
				 itemToDB.getPrice().setPriorlistprice(newPrice.getPriorlistprice());
			  if(newPrice.getPriorsellprice() != null) 
				 itemToDB.getPrice().setPriorsellprice(newPrice.getPriorsellprice());
			  if(newPrice.getTempprice() != null) 
				 itemToDB.getPrice().setTempprice(newPrice.getTempprice());
			  if(newPrice.getTempdatefrom() != null) 
				 itemToDB.getPrice().setTempdatefrom(newPrice.getTempdatefrom());
			  if(newPrice.getTempdatethru() != null) 
				 itemToDB.getPrice().setTempdatethru(newPrice.getTempdatethru());	
		  } 
		  //cost
		  if(itemFromInput.getCost() != null){
			 if(itemToDB.getCost() == null)
			    itemToDB.setCost(new Cost());  
		     if(itemFromInput.getCost().getCost1() != null)
			    itemToDB.getCost().setCost1(itemFromInput.getCost().getCost1());
		     if(itemFromInput.getCost().getCostrangepct() != null)
			    itemToDB.getCost().setCostrangepct(itemFromInput.getCost().getCostrangepct()); 
		     if(itemFromInput.getCost().getFuturecost() != null)
		   	   itemToDB.getCost().setFuturecost(itemFromInput.getCost().getFuturecost());
		     if(itemFromInput.getCost().getNonstockcostpct() != null)
		 	   itemToDB.getCost().setNonstockcostpct(itemFromInput.getCost().getNonstockcostpct()); 
		     if(itemFromInput.getCost().getPoincludeinvendorcost() != null)
			    itemToDB.getCost().setPoincludeinvendorcost(itemFromInput.getCost().getPoincludeinvendorcost()); 
		     if(itemFromInput.getCost().getPriorcost() != null)
			    itemToDB.getCost().setPriorcost(itemFromInput.getCost().getPriorcost()); 
		   }
		  //applications
		  if(itemFromInput.getApplications() != null){
			 if(itemToDB.getApplications() == null)
				itemToDB.setApplications(new Applications()); 
		     if(itemFromInput.getApplications().getCommercial() != null)
			    itemToDB.getApplications().setCommercial(itemFromInput.getApplications().getCommercial()); 
		     if(itemFromInput.getApplications().getLightcommercial() != null)
			    itemToDB.getApplications().setLightcommercial(itemFromInput.getApplications().getLightcommercial());
		     if(itemFromInput.getApplications().getResidential() != null)	
			    itemToDB.getApplications().setResidential(itemFromInput.getApplications().getResidential());	  
		  }
		  //update applications fields with usage input data when applications data is not available
		  else if(itemFromInput.getUsage() != null){ 
	         Applications applications = ImsDataUtil.convertUsageToApplications(itemFromInput.getUsage());
			 itemToDB.setApplications(new Applications());
			 if(applications != null && applications.getCommercial() != null && !applications.getCommercial().isEmpty())
			    itemToDB.getApplications().setCommercial(applications.getCommercial()); 
	    	 if(applications != null && applications.getLightcommercial() != null && !applications.getLightcommercial().isEmpty())
			    itemToDB.getApplications().setLightcommercial(applications.getLightcommercial());
			 if(applications != null && applications.getResidential() != null && !applications.getResidential().isEmpty())
			    itemToDB.getApplications().setResidential(applications.getResidential());
		  }	  
		  //notes
	 	  if(itemFromInput.getNotes() != null){
		 	 if(itemToDB.getNotes() == null)
				itemToDB.setNotes(new Notes());
	 		 if(itemFromInput.getNotes().getBuyernotes() != null)
				itemToDB.getNotes().setBuyernotes(itemFromInput.getNotes().getBuyernotes());
			 if(itemFromInput.getNotes().getInternalnotes() != null) 
				itemToDB.getNotes().setInternalnotes(itemFromInput.getNotes().getInternalnotes());
			 if(itemFromInput.getNotes().getInvoicenotes() != null) 
				itemToDB.getNotes().setInvoicenotes(itemFromInput.getNotes().getInvoicenotes());
			 if(itemFromInput.getNotes().getPonotes() != null) 
				itemToDB.getNotes().setPonotes(itemFromInput.getNotes().getPonotes());
		  }
		  //purchaser
		  if(itemFromInput.getPurchasers() != null){
			 if(itemToDB.getPurchasers() == null)
				itemToDB.setPurchasers(new Purchasers()); 
		  	 if(itemFromInput.getPurchasers().getPurchaser() != null) 
				itemToDB.getPurchasers().setPurchaser(itemFromInput.getPurchasers().getPurchaser()); 
			 if(itemFromInput.getPurchasers().getPurchaser2() != null) 
				itemToDB.getPurchasers().setPurchaser2(itemFromInput.getPurchasers().getPurchaser2()); 
	   	  }
		  //related items
		  if(itemFromInput.getRelateditemcodes() != null){
			 if(itemToDB.getRelateditemcodes() == null)
				itemToDB.setRelateditemcodes(new SimilarItemCode());
			 if(itemFromInput.getRelateditemcodes().getSimilaritemcd1() != null)
			    itemToDB.getRelateditemcodes().setSimilaritemcd1(itemFromInput.getRelateditemcodes().getSimilaritemcd1());	
			 if(itemFromInput.getRelateditemcodes().getSimilaritemcd2() != null)
			   itemToDB.getRelateditemcodes().setSimilaritemcd2(itemFromInput.getRelateditemcodes().getSimilaritemcd2());	
			 if(itemFromInput.getRelateditemcodes().getSimilaritemcd3() != null)
			   itemToDB.getRelateditemcodes().setSimilaritemcd3(itemFromInput.getRelateditemcodes().getSimilaritemcd3());	
			 if(itemFromInput.getRelateditemcodes().getSimilaritemcd4() != null)
			   itemToDB.getRelateditemcodes().setSimilaritemcd4(itemFromInput.getRelateditemcodes().getSimilaritemcd4());	
			 if(itemFromInput.getRelateditemcodes().getSimilaritemcd5() != null)
			   itemToDB.getRelateditemcodes().setSimilaritemcd5(itemFromInput.getRelateditemcodes().getSimilaritemcd5());	
			 if(itemFromInput.getRelateditemcodes().getSimilaritemcd6() != null)
			   itemToDB.getRelateditemcodes().setSimilaritemcd6(itemFromInput.getRelateditemcodes().getSimilaritemcd6());	
			 if(itemFromInput.getRelateditemcodes().getSimilaritemcd7() != null)
			   itemToDB.getRelateditemcodes().setSimilaritemcd7(itemFromInput.getRelateditemcodes().getSimilaritemcd7());	
		}
		//test spec
		if(itemFromInput.getTestSpecification() != null){
		   if(itemToDB.getTestSpecification() == null)
			  itemToDB.setTestSpecification(new TestSpecification());	
		   if(itemFromInput.getTestSpecification().getBondstrength() != null)
		  	  itemToDB.getTestSpecification().setBondstrength(itemFromInput.getTestSpecification().getBondstrength());
	 	   if(itemFromInput.getTestSpecification().getBreakingstandard() != null)
		  	  itemToDB.getTestSpecification().setBreakingstandard(itemFromInput.getTestSpecification().getBreakingstandard());
		   if(itemFromInput.getTestSpecification().getBreakingstrength() != null)
		  	  itemToDB.getTestSpecification().setBreakingstrength(itemFromInput.getTestSpecification().getBreakingstrength());
		   if(itemFromInput.getTestSpecification().getChemicalresistance() != null)
		  	  itemToDB.getTestSpecification().setChemicalresistance(itemFromInput.getTestSpecification().getChemicalresistance());
		   if(itemFromInput.getTestSpecification().getDcof() != null)
		  	  itemToDB.getTestSpecification().setDcof(itemFromInput.getTestSpecification().getDcof());
		   if(itemFromInput.getTestSpecification().getFrostresistance() != null)
		  	  itemToDB.getTestSpecification().setFrostresistance(itemFromInput.getTestSpecification().getFrostresistance());
		   if(itemFromInput.getTestSpecification().getGreenfriendly() != null)
		  	  itemToDB.getTestSpecification().setGreenfriendly(itemFromInput.getTestSpecification().getGreenfriendly());
		   if(itemFromInput.getTestSpecification().getLeadpoint() != null)
		  	  itemToDB.getTestSpecification().setLeadpoint(itemFromInput.getTestSpecification().getLeadpoint());
		   if(itemFromInput.getTestSpecification().getMoh() != null)
		 	  itemToDB.getTestSpecification().setMoh(itemFromInput.getTestSpecification().getMoh());
		   if(itemFromInput.getTestSpecification().getPeiabrasion() != null)
		  	  itemToDB.getTestSpecification().setPeiabrasion(itemFromInput.getTestSpecification().getPeiabrasion());
		   if(itemFromInput.getTestSpecification().getPosconsummer() != null)
		 	  itemToDB.getTestSpecification().setPosconsummer(itemFromInput.getTestSpecification().getPosconsummer());
		   if(itemFromInput.getTestSpecification().getPreconsummer() != null)
		  	  itemToDB.getTestSpecification().setPreconsummer(itemFromInput.getTestSpecification().getPreconsummer());
		   if(itemFromInput.getTestSpecification().getRestricted() != null)
		  	  itemToDB.getTestSpecification().setRestricted(itemFromInput.getTestSpecification().getRestricted());
		   if(itemFromInput.getTestSpecification().getScofdry() != null)
		  	  itemToDB.getTestSpecification().setScofdry(itemFromInput.getTestSpecification().getScofdry());
		   if(itemFromInput.getTestSpecification().getScofwet() != null)
		 	  itemToDB.getTestSpecification().setScofwet(itemFromInput.getTestSpecification().getScofwet());
 		   if(itemFromInput.getTestSpecification().getScratchresistance() != null)
		  	  itemToDB.getTestSpecification().setScratchresistance(itemFromInput.getTestSpecification().getScratchresistance());
		   if(itemFromInput.getTestSpecification().getScratchstandard() != null)
		  	  itemToDB.getTestSpecification().setScratchstandard(itemFromInput.getTestSpecification().getScratchstandard());
		   if(itemFromInput.getTestSpecification().getThermalshock() != null)
		  	  itemToDB.getTestSpecification().setThermalshock(itemFromInput.getTestSpecification().getThermalshock()); 
		   if(itemFromInput.getTestSpecification().getWarpage() != null)
		 	  itemToDB.getTestSpecification().setWarpage(itemFromInput.getTestSpecification().getWarpage());
		   if(itemFromInput.getTestSpecification().getWaterabsorption() != null)
		 	  itemToDB.getTestSpecification().setWaterabsorption(itemFromInput.getTestSpecification().getWaterabsorption());
		   if(itemFromInput.getTestSpecification().getWedging() != null)
		 	  itemToDB.getTestSpecification().setWedging(itemFromInput.getTestSpecification().getWedging());
		}
		//vendor info
		if(itemFromInput.getVendors() != null && !itemFromInput.getVendors().isDefault()){
		   if(itemToDB.getVendors() == null)
			  itemToDB.setVendors(new VendorInfo());
		   if(itemFromInput.getVendors().getDutypct() != null)
			  itemToDB.getVendors().setDutypct(itemFromInput.getVendors().getDutypct());
		   if(itemFromInput.getVendors().getVendordiscpct() != null && itemFromInput.getVendors().getVendordiscpct() !=0 )
			  itemToDB.getVendors().setVendordiscpct(itemFromInput.getVendors().getVendordiscpct());
		   if(itemFromInput.getVendors().getVendordiscpct2() != null && itemFromInput.getVendors().getVendordiscpct2() !=0 ) 
			  itemToDB.getVendors().setVendordiscpct2(itemFromInput.getVendors().getVendordiscpct2());
		   if(itemFromInput.getVendors().getVendordiscpct3() != null && itemFromInput.getVendors().getVendordiscpct3() !=0 )
			  itemToDB.getVendors().setVendordiscpct3(itemFromInput.getVendors().getVendordiscpct3());
		   if(itemFromInput.getVendors().getLeadtime() != null)
			  itemToDB.getVendors().setLeadtime(itemFromInput.getVendors().getLeadtime());
		   if(itemFromInput.getVendors().getVendorfob() != null)
			  itemToDB.getVendors().setVendorfob(itemFromInput.getVendors().getVendorfob());
		   if(itemFromInput.getVendors().getVendorfreightratecwt() != null)
			  itemToDB.getVendors().setVendorfreightratecwt(itemFromInput.getVendors().getVendorfreightratecwt());
		   if(itemFromInput.getVendors().getVendorlandedbasecost() != null && itemFromInput.getVendors().getVendorlandedbasecost().compareTo(BigDecimal.ZERO) != 0)
			  itemToDB.getVendors().setVendorlandedbasecost(itemFromInput.getVendors().getVendorlandedbasecost());
		   if(itemFromInput.getVendors().getVendorlistprice() != null && itemFromInput.getVendors().getVendorlistprice().compareTo(BigDecimal.ZERO) != 0)
			  itemToDB.getVendors().setVendorlistprice(itemFromInput.getVendors().getVendorlistprice());
		   if(itemFromInput.getVendors().getVendormarkuppct() != null)
			  itemToDB.getVendors().setVendormarkuppct(itemFromInput.getVendors().getVendormarkuppct());
		   if(itemFromInput.getVendors().getVendornbr() != null)
			  itemToDB.getVendors().setVendornbr(itemFromInput.getVendors().getVendornbr());
		   if(itemFromInput.getVendors().getVendornbr1() != null)
			  itemToDB.getVendors().setVendornbr1(itemFromInput.getVendors().getVendornbr1());
		   if(itemFromInput.getVendors().getVendornbr2() != null)
			  itemToDB.getVendors().setVendornbr2(itemFromInput.getVendors().getVendornbr2());
		   if(itemFromInput.getVendors().getVendornetprice() != null && itemFromInput.getVendors().getVendornetprice().compareTo(BigDecimal.ZERO) != 0)
			  itemToDB.getVendors().setVendornetprice(itemFromInput.getVendors().getVendornetprice());
		   if(itemFromInput.getVendors().getVendorpriceunit() != null && !itemFromInput.getVendors().getVendorpriceunit().equalsIgnoreCase("PCS"))
			  itemToDB.getVendors().setVendorpriceunit(itemFromInput.getVendors().getVendorpriceunit());
		   if(itemFromInput.getVendors().getVendorroundaccuracy() != null && itemFromInput.getVendors().getVendorroundaccuracy() != 1)
			  itemToDB.getVendors().setVendorroundaccuracy(itemFromInput.getVendors().getVendorroundaccuracy());
		   if(itemFromInput.getVendors().getVendorxrefcd() != null)
			  itemToDB.getVendors().setVendorxrefcd(itemFromInput.getVendors().getVendorxrefcd());
		   setCalculatedVendorData(itemFromInput, itemFromInput.getVendors());
		}
		//units
		if(itemFromInput.getUnits() != null && !itemFromInput.getUnits().isDefault()){
		   if(itemToDB.getUnits() == null)
			  itemToDB.setUnits(new Units());
		   //----general----//
		   itemToDB.getUnits().setOrdunit(ImsDataUtil.getStandardOrderUnit(itemFromInput));
		   itemToDB.getUnits().setOrdratio(ImsDataUtil.getBaseToOrderRatio(itemFromInput));
		   itemToDB.getUnits().setStdunit(ImsDataUtil.getStandardSellUnit(itemFromInput));
		   itemToDB.getUnits().setStdratio(ImsDataUtil.getBaseToSellRatio(itemFromInput));
		  
		   //---base unit----//	
		   if(itemFromInput.getUnits().getBaseisfractqty() != null)
			  itemToDB.getUnits().setBaseisfractqty(itemFromInput.getUnits().getBaseisfractqty());
		   if(itemFromInput.getUnits().getBaseispackunit() != null)
			  itemToDB.getUnits().setBaseispackunit(itemFromInput.getUnits().getBaseispackunit());
		   if(itemFromInput.getUnits().getBaseisstdord() != null)
			  itemToDB.getUnits().setBaseisstdord(itemFromInput.getUnits().getBaseisstdord());
		   if(itemFromInput.getUnits().getBaseisstdsell() != null)
			  itemToDB.getUnits().setBaseisstdsell(itemFromInput.getUnits().getBaseisstdsell());
		   if(itemFromInput.getUnits().getBaseunit() != null)
			  itemToDB.getUnits().setBaseunit(itemFromInput.getUnits().getBaseunit());
		   if(itemFromInput.getUnits().getBaseupc() != null)
			  itemToDB.getUnits().setBaseupc(itemFromInput.getUnits().getBaseupc());
		   if(itemFromInput.getUnits().getBaseupcdesc() != null)
			  itemToDB.getUnits().setBaseupcdesc(itemFromInput.getUnits().getBaseupcdesc());
		   if(itemFromInput.getUnits().getBasevolperunit() != null)
			  itemToDB.getUnits().setBasevolperunit(itemFromInput.getUnits().getBasevolperunit());
		   if(itemFromInput.getUnits().getBasewgtperunit() != null)
			  itemToDB.getUnits().setBasewgtperunit(itemFromInput.getUnits().getBasewgtperunit());
		   //---unit 1----//
		   if(itemFromInput.getUnits().getUnit1isfractqty() != null)
			  itemToDB.getUnits().setUnit1isfractqty(itemFromInput.getUnits().getUnit1isfractqty());
		   if(itemFromInput.getUnits().getUnit1ispackunit() != null)
			  itemToDB.getUnits().setUnit1ispackunit(itemFromInput.getUnits().getUnit1ispackunit());
		   if(itemFromInput.getUnits().getUnit1isstdord() != null)
			  itemToDB.getUnits().setUnit1isstdord(itemFromInput.getUnits().getUnit1isstdord());
		   if(itemFromInput.getUnits().getUnit1isstdsell() != null)
			  itemToDB.getUnits().setUnit1isstdsell(itemFromInput.getUnits().getUnit1isstdsell());
		   if(itemFromInput.getUnits().getUnit1ratio() != null)
			  itemToDB.getUnits().setUnit1ratio(itemFromInput.getUnits().getUnit1ratio());
		   if(itemFromInput.getUnits().getUnit1unit() != null)
			  itemToDB.getUnits().setUnit1unit(itemFromInput.getUnits().getUnit1unit());
		   if(itemFromInput.getUnits().getUnit1upc() != null)
			  itemToDB.getUnits().setUnit1upc(itemFromInput.getUnits().getUnit1upc());
		   if(itemFromInput.getUnits().getUnit1upcdesc() != null)
			  itemToDB.getUnits().setUnit1upcdesc(itemFromInput.getUnits().getUnit1upcdesc());
		   if(itemFromInput.getUnits().getUnit1wgtperunit() != null)
			  itemToDB.getUnits().setUnit1wgtperunit(itemFromInput.getUnits().getUnit1wgtperunit());
		   //---unit 2----//
		   if(itemFromInput.getUnits().getUnit2isfractqty() != null)
			  itemToDB.getUnits().setUnit2isfractqty(itemFromInput.getUnits().getUnit2isfractqty());
		   if(itemFromInput.getUnits().getUnit2ispackunit() != null)
			  itemToDB.getUnits().setUnit2ispackunit(itemFromInput.getUnits().getUnit2ispackunit());
		   if(itemFromInput.getUnits().getUnit2isstdord() != null)
			  itemToDB.getUnits().setUnit2isstdord(itemFromInput.getUnits().getUnit2isstdord());
		   if(itemFromInput.getUnits().getUnit2isstdsell() != null)
			  itemToDB.getUnits().setUnit2isstdsell(itemFromInput.getUnits().getUnit2isstdsell());
		   if(itemFromInput.getUnits().getUnit2ratio() != null)
			  itemToDB.getUnits().setUnit2ratio(itemFromInput.getUnits().getUnit2ratio());
		   if(itemFromInput.getUnits().getUnit2unit() != null)
			  itemToDB.getUnits().setUnit2unit(itemFromInput.getUnits().getUnit2unit());
		   if(itemFromInput.getUnits().getUnit2upc() != null)
			  itemToDB.getUnits().setUnit2upc(itemFromInput.getUnits().getUnit2upc());
		   if(itemFromInput.getUnits().getUnit2upcdesc() != null)
			  itemToDB.getUnits().setUnit2upcdesc(itemFromInput.getUnits().getUnit2upcdesc());
		   if(itemFromInput.getUnits().getUnit2wgtperunit() != null)
			  itemToDB.getUnits().setUnit2wgtperunit(itemFromInput.getUnits().getUnit2wgtperunit());
		   //---unit 3----//
		   if(itemFromInput.getUnits().getUnit3isfractqty() != null)
			  itemToDB.getUnits().setUnit3isfractqty(itemFromInput.getUnits().getUnit3isfractqty());
		   if(itemFromInput.getUnits().getUnit3ispackunit() != null)
			  itemToDB.getUnits().setUnit3ispackunit(itemFromInput.getUnits().getUnit3ispackunit());
		   if(itemFromInput.getUnits().getUnit3isstdord() != null)
			  itemToDB.getUnits().setUnit3isstdord(itemFromInput.getUnits().getUnit3isstdord());
		   if(itemFromInput.getUnits().getUnit3isstdsell() != null)
			  itemToDB.getUnits().setUnit3isstdsell(itemFromInput.getUnits().getUnit3isstdsell());
		   if(itemFromInput.getUnits().getUnit3ratio() != null)
			  itemToDB.getUnits().setUnit3ratio(itemFromInput.getUnits().getUnit3ratio());
		   if(itemFromInput.getUnits().getUnit3unit() != null)
			  itemToDB.getUnits().setUnit3unit(itemFromInput.getUnits().getUnit3unit());
		   if(itemFromInput.getUnits().getUnit3upc() != null)
			  itemToDB.getUnits().setUnit3upc(itemFromInput.getUnits().getUnit3upc());
		   if(itemFromInput.getUnits().getUnit3upcdesc() != null)
			  itemToDB.getUnits().setUnit3upcdesc(itemFromInput.getUnits().getUnit3upcdesc());
		   if(itemFromInput.getUnits().getUnit3wgtperunit() != null)
			  itemToDB.getUnits().setUnit3wgtperunit(itemFromInput.getUnits().getUnit3wgtperunit());
		   //---unit 4----//
		   if(itemFromInput.getUnits().getUnit4isfractqty() != null)
			  itemToDB.getUnits().setUnit4isfractqty(itemFromInput.getUnits().getUnit4isfractqty());
		   if(itemFromInput.getUnits().getUnit4ispackunit() != null)
			  itemToDB.getUnits().setUnit4ispackunit(itemFromInput.getUnits().getUnit4ispackunit());
		   if(itemFromInput.getUnits().getUnit4isstdord() != null)
			  itemToDB.getUnits().setUnit4isstdord(itemFromInput.getUnits().getUnit4isstdord());
		   if(itemFromInput.getUnits().getUnit4isstdsell() != null)
			  itemToDB.getUnits().setUnit4isstdsell(itemFromInput.getUnits().getUnit4isstdsell());
		   if(itemFromInput.getUnits().getUnit4ratio() != null)
			  itemToDB.getUnits().setUnit4ratio(itemFromInput.getUnits().getUnit4ratio());
		   if(itemFromInput.getUnits().getUnit4unit() != null)
			  itemToDB.getUnits().setUnit4unit(itemFromInput.getUnits().getUnit4unit());
		   if(itemFromInput.getUnits().getUnit4upc() != null)
			  itemToDB.getUnits().setUnit4upc(itemFromInput.getUnits().getUnit4upc());
		   if(itemFromInput.getUnits().getUnit4upcdesc() != null)
			  itemToDB.getUnits().setUnit4upcdesc(itemFromInput.getUnits().getUnit4upcdesc());
		   if(itemFromInput.getUnits().getUnit4wgtperunit() != null)
			  itemToDB.getUnits().setUnit4wgtperunit(itemFromInput.getUnits().getUnit4wgtperunit());		
		   //if(itemFromInput.getPriorVendor() != null) itemToDB.setPriorVendor(itemFromInput.getPriorVendor());
	    }
	  }
	  catch(Exception e){
		  throw new InputParamException("Error occured while transferComponent(): " + e.getMessage(), e.getCause());	
	  }		
	}
}
