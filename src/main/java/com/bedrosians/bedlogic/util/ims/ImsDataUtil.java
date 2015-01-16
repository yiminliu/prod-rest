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


public class ImsDataUtil {

	public static MpsCode convertInactivecdToMpsCode(String inactiveCode){
		MpsCode mpsCode = null;
	
		if(inactiveCode == null)
	       return null;
	    else
	    	inactiveCode = inactiveCode.trim();
	    
	    switch(inactiveCode) {
	        case "N":  
	    	   mpsCode = MpsCode.Active_Product;
	    	   break;
	        case "D":
	    	   mpsCode = MpsCode.Pre_Drop;
	    	   break;
	        case "Y":
	    	   mpsCode = MpsCode.Drop;	
	    	   break;
	    }	
		return mpsCode;
	}
	
	/************************************ Icon Util *******************************/
	public static IconCollection convertLegacyIconsToIconCollection(String icons){
		 /*
		* Stored as 20 character string
		*
		* 0 - Made in Italy
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
		* 19 - Unused
		*
		*/
		
		if(icons == null || icons.length() == 0)
			return null;
		if(!icons.contains("Y"))
		   return null;	
		IconCollection icon = new IconCollection();
        OriginCountry country = null;
		if (icons.length() > 0 && icons.charAt(0) == 'Y' )
		    country = OriginCountry.Italy;
		else if (icons.length() > 3 && icons.charAt(3) == 'Y' )
		    country = OriginCountry.USA;
		else if (icons.length() > 13 && icons.charAt(13) == 'Y' )
			country = OriginCountry.China;
		else if (icons.length() > 14 && icons.charAt(14) == 'Y' )
		    country = OriginCountry.Turkey;
		else if (icons.length() > 15 && icons.charAt(15) == 'Y' )
		    country = OriginCountry.Mexico;
		icon.setMadeInCountry(country);
	
        icon.setExteriorProduct(icons.length() < 2? "No" : icons.charAt(1) == 'Y'? "Yes" : "No");
        icon.setAdaAccessibility(icons.length() < 4? "No" : icons.charAt(3) == 'Y'? "Yes" : "No");
        icon.setThroughColor(icons.length() < 5? "No" : icons.charAt(4) == 'Y'? "Yes" : "No");
        icon.setInkJet(icons.length() < 6? "No" : icons.charAt(5) == 'Y'? "Yes" : "No");
        icon.setRecycled(icons.length() < 7? "No" : icons.charAt(6) == 'Y'? "Yes" : "No");
        icon.setColorBody(icons.length() < 8? "No" : icons.charAt(7) == 'Y'? "Yes" : "No");
        icon.setGlazed(icons.length() < 9? "No" : icons.charAt(8) == 'Y'? "Yes" : "No");
        icon.setRectifiedEdge(icons.length() < 10? "No" : icons.charAt(9) == 'Y'? "Yes" : "No");
        icon.setUnglazed(icons.length() < 11? "No" : icons.charAt(10) == 'Y'? "Yes" : "No");
        icon.setPostRecycled(icons.length() < 12? "No" : icons.charAt(11) == 'Y'? "Yes" : "No");
        icon.setPreRecycled(icons.length() < 13? "No" : icons.charAt(12) == 'Y'? "Yes" : "No");
        icon.setCoefficientOfFriction(icons.length() < 17? "No" : icons.charAt(16) == 'Y'? "Yes" : "No");
        icon.setChiseledEdge(icons.length() < 18? "No" : icons.charAt(17) == 'Y'? "Yes" : "No");		
        
        return icon;
	}
	
	public static String convertIconCollectionToLegancyIcons(final IconCollection iconCollection){
    	char[] legacyIcons = new char[20];
    	
    	legacyIcons[0] = OriginCountry.Italy.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[1] = (iconCollection.getExteriorProduct() != null && iconCollection.getExteriorProduct().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[2] = OriginCountry.USA.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[3] = (iconCollection.getAdaAccessibility() != null && iconCollection.getAdaAccessibility().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[4] = (iconCollection.getThroughColor() != null && iconCollection.getThroughColor().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[5] = (iconCollection.getInkJet() != null && iconCollection.getInkJet().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[6] = (iconCollection.getRecycled() != null && iconCollection.getRecycled().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[7] = (iconCollection.getColorBody() != null && iconCollection.getColorBody().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[8] = (iconCollection.getGlazed() != null && iconCollection.getGlazed().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[9] = (iconCollection.getRectifiedEdge() != null && iconCollection.getRectifiedEdge().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[10] = (iconCollection.getUnglazed() != null && iconCollection.getUnglazed().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[11] = (iconCollection.getPostRecycled() != null && iconCollection.getPostRecycled().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[12] = (iconCollection.getPreRecycled() != null && iconCollection.getPreRecycled().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[13] = OriginCountry.China.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[14] = OriginCountry.Turkey.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[15] = OriginCountry.Mexico.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[16] = (iconCollection.getCoefficientOfFriction() != null && iconCollection.getCoefficientOfFriction().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[17] = (iconCollection.getChiseledEdge() != null && iconCollection.getChiseledEdge().equalsIgnoreCase("yes")) ? 'Y' : 'N'; 
    	legacyIcons[18] = 'N'; 
    	legacyIcons[19] = 'N'; 
    	    
    	return new String(legacyIcons);
    }
	
	/************************************ Vendor Util *******************************/
	public static Vendor convertImsVendorInfoToItemVendor(final  VendorInfo vendorInfo){
		Vendor vendor = new Vendor();
		if(vendorInfo != null){
			vendor.setId(vendorInfo.getVendornbr1());
			vendor.setVendorXrefId(vendorInfo.getVendorxrefcd());
			vendor.setVendorFob(vendorInfo.getVendorfob());
			vendor.setDutyPct(vendorInfo.getDutypct());
			vendor.setLeadTime(vendorInfo.getLeadtime());
			if(vendorInfo.getVendorlistprice() != null) vendor.setVendorListPrice(vendorInfo.getVendorlistprice());
			if(vendorInfo.getVendorpriceunit() != null) vendor.setVendorPriceUnit(vendorInfo.getVendorpriceunit());
			if(vendorInfo.getVendordiscpct() != null) vendor.setVendorDiscountPct(vendorInfo.getVendordiscpct());
		    if(vendorInfo.getVendorroundaccuracy() != null) vendor.setVendorPriceRoundAccuracy(vendorInfo.getVendorroundaccuracy());
		    if(vendorInfo.getVendornetprice() != null) vendor.setVendorNetPrice(vendorInfo.getVendornetprice());
	        if(vendorInfo.getVendormarkuppct() != null) vendor.setVendorMarkupPct(vendorInfo.getVendormarkuppct());
		    if(vendorInfo.getVendorfreightratecwt() != null) vendor.setVendorFreightRateCwt(vendorInfo.getVendorfreightratecwt());
		    if(vendorInfo.getVendorlandedbasecost() != null) vendor.setVendorLandedBaseCost(vendorInfo.getVendorlandedbasecost());
		 } 
		return vendor;
	}

	//convert new vendor system data to legacy vendor data
	public static VendorInfo convertNewVendorToLegancyVendorInfo(final Vendor vendor){
		VendorInfo vendorInfo = new VendorInfo();
		if(vendor != null){
			if(vendor.getVendorId() != null) vendorInfo.setVendornbr1(vendor.getVendorId().getId());
			if(vendor.getVendorXrefId() != null) vendorInfo.setVendorxrefcd(vendor.getVendorXrefId());
			if(vendor.getVendorFob() != null) vendorInfo.setVendorfob(vendor.getVendorFob());
			if(vendor.getDutyPct() != null) vendorInfo.setDutypct(vendor.getDutyPct());
			if(vendor.getLeadTime() != null) vendorInfo.setLeadtime(vendor.getLeadTime());
			if(vendor.getVendorListPrice() != null) vendorInfo.setVendorlistprice(vendor.getVendorListPrice());
			if(vendor.getVendorPriceUnit() != null) vendorInfo.setVendorpriceunit(vendor.getVendorPriceUnit());
			if(vendor.getVendorDiscountPct() != null) vendorInfo.setVendordiscpct(vendor.getVendorDiscountPct());
		    if(vendor.getVendorPriceRoundAccuracy() != null) vendorInfo.setVendorroundaccuracy(vendor.getVendorPriceRoundAccuracy());
		    if(vendor.getVendorNetPrice() != null) vendorInfo.setVendornetprice(vendor.getVendorNetPrice());
	        if(vendor.getVendorMarkupPct() != null) vendorInfo.setVendormarkuppct(vendor.getVendorMarkupPct());
		    if(vendor.getVendorFreightRateCwt() != null) vendorInfo.setVendorfreightratecwt(vendor.getVendorFreightRateCwt());
		    if(vendor.getVendorLandedBaseCost() != null) vendorInfo.setVendorlandedbasecost(vendor.getVendorLandedBaseCost());
		 } 
		return vendorInfo;
	}

	/************************************ Applications/Usage Util *******************************/
	private String convertUsageToApplications(List<String> usage, String application){
		if(usage == null || usage.isEmpty())
		   return null;
		StringBuilder builder = new StringBuilder();		
	    for(int i = 0; i < usage.size(); i++){
	    	if(usage.get(i).endsWith("R") && "Residential".equalsIgnoreCase(application)){
	           if(i == usage.size() - 1)	
	        	   builder.append(usage.get(i));	
	    	   else    
	    		   builder.append(usage.get(i)).append(":"); 
	    	}
	        else if(usage.get(i).endsWith("L") && "LightCommercial".equalsIgnoreCase(application)){
		           if(i == usage.size() - 1)	
		        	   builder.append(usage.get(i));	
		    	   else    
		    		   builder.append(usage.get(i)).append(":");
	    	}   
	        else if(usage.get(i).endsWith("C") && "Commercial".equalsIgnoreCase(application)){
	           if(i == usage.size() - 1)	
	        	   builder.append(usage.get(i));	
	    	   else    
	    		   builder.append(usage.get(i)).append(":"); 
	    	}
	    }
	    return builder.toString();
	}

	public static Applications convertUsageToApplications(final List<String> usage){
		if(usage == null || usage.isEmpty())
		   return null;
		Applications applocation = new Applications();
		StringBuilder residential = new StringBuilder();
		StringBuilder lightCommercial = new StringBuilder();
		StringBuilder commercial = new StringBuilder();
			
	    for(int i = 0; i < usage.size(); i++){
	    	if(usage.get(i).endsWith("R")){
	           if(i == usage.size() - 1)	
	       	      residential.append(usage.get(i));	
	    	   else    
	    		   residential.append(usage.get(i)).append(":"); 
	    	}
	        else if(usage.get(i).endsWith("L")){
		        if(i == usage.size() - 1)	
		       	   lightCommercial.append(usage.get(i));	
		    	else    
				   lightCommercial.append(usage.get(i)).append(":"); 
	    	}   
		    else if(usage.get(i).endsWith("C")){
		        if(i == usage.size() - 1)	
		       	   commercial.append(usage.get(i));	
		    	else    
		    	   commercial.append(usage.get(i)).append(":"); 
		    }     
	    }
	    String rStr = residential.toString();
	    if(rStr != null && rStr.endsWith(":"))
	    	rStr = rStr.substring(0, rStr.lastIndexOf(":"));
	    applocation.setResidential(rStr);
	    rStr = lightCommercial.toString();
	    if(rStr != null && rStr.endsWith(":"))
	    	rStr = rStr.substring(0, rStr.lastIndexOf(":"));
	    applocation.setLightcommercial(rStr);
	    rStr = commercial.toString();
	    if(rStr != null && rStr.endsWith(":"))
	    	rStr = rStr.substring(0, rStr.lastIndexOf(":"));
	    applocation.setCommercial(rStr);
	    return applocation;
	}

	public static String convertUsageToApplicationString(final List<String> usage){
		if(usage == null || usage.isEmpty())
		   return null;
		StringBuilder sb = new StringBuilder();
		
			
	    for(int i = 0; i < usage.size(); i++){
	    	if(usage.get(i).endsWith("R")){
	           if(i == usage.size() - 1)	
	       	      sb.append(usage.get(i));	
	    	   else    
	    		   sb.append(usage.get(i)).append(":"); 
	    	}
	        else if(usage.get(i).endsWith("L")){
		        if(i == usage.size() - 1)	
		       	   sb.append(usage.get(i));	
		    	else    
				   sb.append(usage.get(i)).append(":"); 
	    	}   
		    else if(usage.get(i).endsWith("C")){
		        if(i == usage.size() - 1)	
		       	   sb.append(usage.get(i));	
		    	else    
		    	   sb.append(usage.get(i)).append(":"); 
		    }     
	    }
	    String string = sb.toString();
	    if(string != null && string.endsWith(":"))
	    	string = string.substring(0, string.lastIndexOf(":"));
	   return string;
	}

	
	/************************************ ColorCategory/ColorHue Util *******************************/
	public static List<String> convertColorCategoryToStringList(String colorCategory){
		if(colorCategory == null)
		   return null;	
		return Arrays.asList(colorCategory.trim().split(":"));
	}
	
	public static String convertColorhueStringListToColorCategory(final  List<String> colorStringList){
		if(colorStringList == null || colorStringList.isEmpty())
		   return null;	
		else{
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < colorStringList.size(); i++){
				if(i == colorStringList.size() - 1)
  				   sb.append(colorStringList.get(i));
				else
				   sb.append(colorStringList.get(i)).append(":");
			}	
			return sb.toString();
		}
	}
	
	public static String convertColorHuesToColorCategory(List<ColorHue> colorHues){
		if(colorHues == null || colorHues.isEmpty())
		   return null;	
		else{
		    int i = 0;	
		    StringBuilder sBuilder = new StringBuilder();	
			for(ColorHue colorHue : colorHues){
				if(i == colorHues.size() - 1)
				   sBuilder.append(colorHue.getColorHue());
				else
					sBuilder.append(colorHue.getColorHue()).append(":");
				i++;
			}
		return sBuilder.toString();
	  }
	}
	
	public static String convertColorHuesToString(List<ColorHue> colorHues){
		if(colorHues == null || colorHues.isEmpty())
		   return null;	
		else{
		    int i = 0;	
		    StringBuilder sBuilder = new StringBuilder();	
			for(ColorHue colorHue : colorHues){
				if(i == colorHues.size() - 1)
				   sBuilder.append(colorHue.getColorHue());
				else
					sBuilder.append(colorHue.getColorHue()).append(", ");
				i++;
			}
		return sBuilder.toString();
	  }
	}
	
	public static Set<ColorHue> convertColorCategoryToColorHueObjects(Ims product){
		Set<ColorHue> colorHues = new HashSet<>();
		ColorHue colorHue = null;
		if(product.getColorcategory() != null && !product.getColorcategory().isEmpty()) {
		   for(String color : product.getColorcategory().trim().split(":")){
			   if(color != null && !color.isEmpty()){
  			      colorHue = new ColorHue(color);
			      colorHue.setItem(product);
			      colorHues.add(colorHue);
			   }
		   }	   
		}
	    return colorHues;
	}
	
	public static List<ColorHue> convertColorCategoryToColorHueObjects(String colorCategory){
		if(colorCategory == null || colorCategory.isEmpty())
		   return null;	
		else{
			List<ColorHue> colorHues = new ArrayList<>();
			ColorHue colorHue = null;
			for(String color : colorCategory.trim().split(":")){
				colorHue = new ColorHue(color);
				colorHues.add(colorHue);
			}
	        return colorHues;
		}
	}
	
	public static List<ColorHue> convertColorListToColorHueObjects(List<String> colors){
		if(colors == null || colors.isEmpty())
		   return null;	
		else{
			List<ColorHue> colorHues = new ArrayList<>();
			ColorHue colorHue = null;
			for(String color : colors){
				colorHue = new ColorHue(color);
				colorHues.add(colorHue);
			}
	        return colorHues;
		}
	}
	
	public static boolean colorHuesAndColorsEquals(final List<ColorHue> colorhues, final List<String> colors){
		if(colors == null && colorhues == null)
		   return true;	
		if((colors == null && colorhues != null) || (colors != null && colorhues == null))
			   return false;
		if(colorhues.size() != colors.size())
		   return false;
		List<String> tempList = new ArrayList<String>(colorhues.size());
		for(ColorHue colorhue : colorhues){
			tempList.add(colorhue.getColorHue());
		}
		return colors.containsAll(tempList) && tempList.containsAll(colors);
	}
	
	/*public static Set<ColorHue> convertColorCategoryToColorHueString(String colorCategory){
		if(colorCategory == null || colorCategory.isEmpty())
		   return null;	
		else
		   return new HashSet(Arrays.asList(colorCategory.trim().split(":")));
	}
	
	
		
	*/
	//public static Product parsePriorVendor(Product product){
	//	
	//	if(product.getPriorVendor() != null && product.getPriorVendor().getPriorvendorpriceunit() == null 
	//			                         && product.getPriorVendor().getPriorvendorfob() == null) 
	//			                         //&& product.getPriorVendor().getPriorvendorlistprice() == null)
	//		product.setPriorVendor(null);	
	//	return product;
	//}
	
	public static List<String> convertApplicationsToUsage(Ims ims){
		StringBuilder stringBuilder = new StringBuilder();
		if(ims.getApplications() != null){
	       if(ims.getApplications().getResidential() != null && !ims.getApplications().getResidential().isEmpty()){
		      stringBuilder.append(ims.getApplications().getResidential());
	       }   
		   if(ims.getApplications().getLightcommercial() != null && !ims.getApplications().getLightcommercial().isEmpty()){
			  if(stringBuilder != null && stringBuilder.length() > 0)
		         stringBuilder.append(":").append(ims.getApplications().getLightcommercial());
		      else
			     stringBuilder.append(ims.getApplications().getLightcommercial());
		   }
		   if(ims.getApplications().getCommercial() != null && !ims.getApplications().getCommercial().isEmpty()){
			   if(stringBuilder != null && stringBuilder.length() > 0)
			      stringBuilder.append(":").append(ims.getApplications().getCommercial());
			   else
				  stringBuilder.append(ims.getApplications().getCommercial());
		   }
		}
		return Arrays.asList(stringBuilder.toString().split(":"));
	}
	
	public static String getStandardSellUnit(Ims ims) {
		if(ims.getUnits() == null)
		   return null;
		String standardUnit = ims.getUnits().getBaseunit();	
        if (ims.getUnits().getUnit1isstdsell() != null && ims.getUnits().getUnit1isstdsell() == 'Y')
        	standardUnit = ims.getUnits().getUnit1unit();
        else if (ims.getUnits().getUnit2isstdsell() != null && ims.getUnits().getUnit2isstdsell() == 'Y')
        	standardUnit = ims.getUnits().getUnit2unit();
        else if (ims.getUnits().getUnit3isstdsell() != null && ims.getUnits().getUnit3isstdsell() == 'Y')
        	standardUnit = ims.getUnits().getUnit3unit();
        else if (ims.getUnits().getUnit4isstdsell() != null && ims.getUnits().getUnit4isstdsell() == 'Y')
        	standardUnit = ims.getUnits().getUnit4unit();
        return standardUnit;
    }
	
	public static String getStandardOrderUnit(Ims ims) {
		if(ims.getUnits() == null)
		   return null;	
		String standardUnit = ims.getUnits().getBaseunit();
		if (ims.getUnits().getUnit1isstdord() != null && ims.getUnits().getUnit1isstdord() == 'Y')
        	standardUnit = ims.getUnits().getUnit1unit();
        else if (ims.getUnits().getUnit2isstdord() != null && ims.getUnits().getUnit2isstdord() == 'Y')
        	standardUnit = ims.getUnits().getUnit2unit();
        else if (ims.getUnits().getUnit3isstdord() != null && ims.getUnits().getUnit3isstdord() == 'Y')
        	standardUnit = ims.getUnits().getUnit3unit();
        else if (ims.getUnits().getUnit4isstdord() != null && ims.getUnits().getUnit4isstdord() == 'Y')
        	standardUnit = ims.getUnits().getUnit4unit();
        return standardUnit;
    }
	
    public static PackagingInfo getPackagingInfo(Ims ims) {
	    if(ims == null || ims.getUnits() == null)
	       return null;	
    	float boxPieces = 0f;
    	float boxSF = 0f;
    	float boxWeight = 0f;
    	float palletBox = 0f;
    	float palletSF = 0f;
    	float palletWeight = 0f;	
    	Float baseWgtPerUnit = 0F;
    	Float unit1Ratio = 0F;
    	Float unit2Ratio = 0F;
    	Float unit4Ratio = 0F;
    	PackagingInfo packagingInfo = new PackagingInfo();
    	
    	String unit1Unit = ims.getUnits().getUnit1unit();
    	String unit2Unit = ims.getUnits().getUnit2unit();
    	String unit4Unit = ims.getUnits().getUnit4unit();	
        unit1Ratio = ims.getUnits().getUnit1ratio();
        unit2Ratio = ims.getUnits().getUnit2ratio();
        unit4Ratio = ims.getUnits().getUnit4ratio();
        if(ims.getUnits().getBasewgtperunit() != null)
           baseWgtPerUnit = (ims.getUnits().getBasewgtperunit()).floatValue();
		String standardUnit = ims.getUnits().getBaseunit();
		
		if ("CTN".equalsIgnoreCase(unit1Unit)){
            boxPieces = unit1Ratio;
            boxWeight = unit1Ratio * baseWgtPerUnit;
            if ("S/F".equalsIgnoreCase(unit4Unit))
                boxSF = (1 / unit4Ratio) * unit1Ratio;
        }
        
        if ("PLT".equalsIgnoreCase(unit2Unit)){
            palletWeight = unit2Ratio * baseWgtPerUnit;
            if ("S/F".equalsIgnoreCase(unit4Unit))
                palletSF = (1 / unit4Ratio) * unit2Ratio;
            if ("CTN".equalsIgnoreCase(unit1Unit))
                palletBox = unit2Ratio / unit1Ratio;
        }
          
        packagingInfo.setBoxPieces(boxPieces);
        packagingInfo.setBoxSF(boxSF);
        packagingInfo.setBoxWeight(boxWeight);
        packagingInfo.setPalletBox(palletBox);
        packagingInfo.setPalletSF(palletSF);
        packagingInfo.setPalletWeight(palletWeight);
        
        return packagingInfo;
    }
   
	public static Float getBaseToSellRatio(Ims ims) {
		Float baseToSellRatio = 1f;
		Units units = ims.getUnits();
	    if(units != null){
           if(units.getUnit1isstdsell() != null && "Y".equalsIgnoreCase(units.getUnit1isstdsell().toString().trim()))
              baseToSellRatio = units.getUnit1ratio();
           else if(units.getUnit2isstdsell() != null && "Y".equalsIgnoreCase(units.getUnit2isstdsell().toString().trim()))
              baseToSellRatio = units.getUnit2ratio();
           else if(units.getUnit3isstdsell() != null && "Y".equalsIgnoreCase(units.getUnit3isstdsell().toString().trim()))
              baseToSellRatio = units.getUnit3ratio();
           else if(units.getUnit4isstdsell() != null && "Y".equalsIgnoreCase(units.getUnit4isstdsell().toString().trim()))
              baseToSellRatio = units.getUnit4ratio();
	    }
        if(baseToSellRatio != null && baseToSellRatio.intValue() == 0)
        	baseToSellRatio = 1f;
        return baseToSellRatio;
    }
	
	public static Float getBaseToOrderRatio(Ims ims) {
		Float baseToOrderRatio = 1f;
        Units units = ims.getUnits();
        if(units != null){
           if(units.getUnit1isstdord() != null && "Y".equalsIgnoreCase(units.getUnit1isstdord().toString().trim()))
              baseToOrderRatio = units.getUnit1ratio();
           else if(units.getUnit2isstdord() != null && "Y".equalsIgnoreCase(units.getUnit2isstdord().toString().trim()))
              baseToOrderRatio = units.getUnit2ratio();
           else if(units.getUnit3isstdord() != null && "Y".equalsIgnoreCase(units.getUnit3isstdord().toString().trim()))
              baseToOrderRatio = units.getUnit3ratio();
           else if(units.getUnit4isstdord() != null && "Y".equalsIgnoreCase(units.getUnit4isstdord().toString().trim()))
              baseToOrderRatio = units.getUnit4ratio();
        }
        if(baseToOrderRatio != null && baseToOrderRatio.intValue() == 0)
           baseToOrderRatio = 1f;
        return baseToOrderRatio;
    }
	
	public static String getPackUnit(Ims ims)
    {
		String packUnit = ims.getUnits().getBaseunit();
 
        if(ims.getUnits().getUnit1ispackunit() != null && "Y".equalsIgnoreCase(ims.getUnits().getUnit1ispackunit().toString().trim()))
           packUnit = ims.getUnits().getUnit1unit();
        else if(ims.getUnits().getUnit2ispackunit() != null && "Y".equalsIgnoreCase(ims.getUnits().getUnit2ispackunit().toString().trim()))
           packUnit = ims.getUnits().getUnit2unit();   
        else if(ims.getUnits().getUnit3ispackunit() != null && "Y".equalsIgnoreCase(ims.getUnits().getUnit3ispackunit().toString().trim()))
           packUnit = ims.getUnits().getUnit3unit();   
        else if(ims.getUnits().getUnit4ispackunit() != null && "Y".equalsIgnoreCase(ims.getUnits().getUnit4ispackunit().toString().trim()))
           packUnit = ims.getUnits().getUnit4unit();    
       
        return packUnit;
    }

	public static Vendor setCalculatedVendorData(Ims sourceItem, Vendor vendor){
	  	if(vendor.getVendorListPrice() == null)
	  	   return vendor;	
	  	//calculate net price
		if(vendor.getVendorDiscountPct() != null){
		   BigDecimal netPrice = new BigDecimal(vendor.getVendorListPrice().floatValue() * ((100 - vendor.getVendorDiscountPct())/100.00));
		   vendor.setVendorNetPrice(netPrice);
		   //calculate landedBaseCost
		   if(sourceItem.getUnits() != null && sourceItem.getUnits().getStdratio() != null && sourceItem.getUnits().getBasewgtperunit() != null){
		      BigDecimal landedBaseCost = new BigDecimal(netPrice.floatValue() * 
		    		                                         ((100 + vendor.getVendorMarkupPct())/100.00/sourceItem.getUnits().getStdratio()) + 
		    		                                         vendor.getVendorFreightRateCwt() *
		    		                                         sourceItem.getUnits().getBasewgtperunit().floatValue()/100.00);
		      vendor.setVendorLandedBaseCost(landedBaseCost);
		   }   
		}   
	    else 
		   vendor.setVendorNetPrice(vendor.getVendorListPrice());
	    return vendor;	
	} 	
	  	
	public static VendorInfo setCalculatedVendorData(Ims sourceItem, VendorInfo legancyVendorInfo){
		if(legancyVendorInfo.getVendorlistprice() == null)
	   	   return legancyVendorInfo;
		 //calculate net price
		if(legancyVendorInfo.getVendordiscpct() != null){
	 	   BigDecimal netPrice = new BigDecimal(legancyVendorInfo.getVendorlistprice().floatValue() * ((100 - legancyVendorInfo.getVendordiscpct())/100.00));
	 	   legancyVendorInfo.setVendornetprice(netPrice);
	 	   //calculate landedBaseCost
	 	   if(sourceItem.getUnits() != null && sourceItem.getUnits().getStdratio() != null && sourceItem.getUnits().getBasewgtperunit() != null){
	 	      BigDecimal landedBaseCost = new BigDecimal(netPrice.floatValue() * 
	 	    		                                         ((100 + legancyVendorInfo.getVendormarkuppct())/100.00/sourceItem.getUnits().getStdratio()) + 
	 	    		                                         legancyVendorInfo.getVendorfreightratecwt() *
	 	    		                                        sourceItem.getUnits().getBasewgtperunit().floatValue()/100.00);
	 	      legancyVendorInfo.setVendorlandedbasecost(landedBaseCost);
	 	    }   
	 	}   
	 	else 
	 	  legancyVendorInfo.setVendornetprice(legancyVendorInfo.getVendorlistprice());
		return legancyVendorInfo;
	} 	
		
	/*public static Product parseNotes(Product product){
	    String additionalNoteText = null;
	    Note buyerNote = null;
	  	if(product.getNewNoteSystem() != null && !product.getNewNoteSystem().isEmpty()){
           for(Note note : product.getNewNoteSystem())	{
        	   if("buyer".equalsIgnoreCase(note.getNoteType()) || "buyer_note".equalsIgnoreCase(note.getNoteType()))
        		   buyerNote = note;
        	   else if("additional".equalsIgnoreCase(note.getNoteType()) || "additional_note".equalsIgnoreCase(note.getNoteType()))
        		   additionalNoteText = note.getNote();
           }  
	       if(additionalNoteText != null) {
			  buyerNote.setNote(buyerNote.getNote() + ". " +additionalNoteText);
		      product.getNewNoteSystem().set(1, buyerNote);
	       }
	  	}
	  	return product;
	}*/

/*
public static Status convertAbcCodeToStatus(String inactiveCode){
	 abc code: 
	 * option value="A">A -High Volume</option>
          <option value="B">B -Medium Volume</option>
          <option value="C">C -Low Volume</option>
          <option value="E">E -Exotic Slab</option>
          <option value="S">S -Second</option>
          <option value="SO">SO -Special Order</option>
          <option value="D">D -Discontinued</option>
          <option value="FRT">FRT -Freight</option>
          <option value="MISC">MISC -Misc</option>
          <option value="MKT">MKT -Marketing</option>
          <option value="P">P -Promo</option>
          
	 
       return MpsCode.NONE;
    else
    	inactiveCode = inactiveCode.trim();
    
    switch(inactiveCode) {
        case "N":  
    	   mpsCode = MpsCode.ACTIVE_PRODUCT;
    	   break;
        case "Y":
    	   mpsCode = MpsCode.PRE_DROP;
    	   break;
        case "D":
    	   mpsCode = MpsCode.DROP;	
    	   break;
    }	
	return mpsCode;
}
*/
	
	//public ProdUnit getStandardUnit(Set<ProdUnit> prodUnits, String type){
	//	ProdUnit baseUnit= null;
	//	for(ProdUnit unit : prodUnits){
	//		if(("sell".equalsIgnoreCase(type) && unit.getIsStdSell().equals('Y') || unit.getIsStdSell().equals('y')) ||
	//		  ("order".equalsIgnoreCase(type) && unit.getIsStdOrder().equals('Y') || unit.getIsStdOrder().equals('y')))
	//		  return unit;
	//		else if(unit.getName().contains("base"))
     //         baseUnit = unit;
	//	}    
	//	return baseUnit;
	//}	
	//process new note system
	//List<Note> noteList = itemFromInput.getNewNoteSystem();
			//Notes legacyNotes = (itemFromInput.getNotes() != null)? itemFromInput.getNotes() : new Notes();
			/*if(noteList != null && !noteList.isEmpty()){
				if(operation.equals(DBOperation.UPDATE) && (itemToDB.getNewNoteSystem() == null || itemToDB.getNewNoteSystem().isEmpty()))
				   itemToDB.initNotes(noteList.size());//.setNewNoteSystem(new ArrayList<Note>());
				
				for(Note note : noteList){	
					if(operation.equals(DBOperation.CREATE)){
			           if(note.getCreatedDate() == null)
			        	   note.setCreatedDate(new Date());
			           itemToDB.addNote(note);
					}   
					else if(operation.equals(DBOperation.UPDATE)){
						switch(note.getNoteType()){
						   case "po": case "po_note":
							   itemToDB.getNewNoteSystem().get(0).setNoteType("po");
							   itemToDB.getNewNoteSystem().get(0).setLastModifiedDate(new Date());
							   if(note.getCreatedDate() != null) itemToDB.getNewNoteSystem().get(0).setCreatedDate(note.getCreatedDate());
							   //if(note.getNoteId() >= 0) itemToDB.getNewNoteSystem().get(0).setNoteId(note.getNoteId());
							   if(note.getText() != null) itemToDB.getNewNoteSystem().get(0).setText(note.getText());
							   break;
						   case "buyer": case "buyer_note":
							   itemToDB.getNewNoteSystem().get(1).setNoteType("buyer");
							   itemToDB.getNewNoteSystem().get(1).setLastModifiedDate(new Date());
							   if(note.getCreatedDate() != null) itemToDB.getNewNoteSystem().get(1).setCreatedDate(note.getCreatedDate());
							   //if(note.getNoteId() >= 0) itemToDB.getNewNoteSystem().get(1).setNoteId(note.getNoteId());
							   if(note.getText() != null) itemToDB.getNewNoteSystem().get(1).setText(note.getText());
							   break;	
						   case "invoice": case "invoice_note":
							   itemToDB.getNewNoteSystem().get(2).setNoteType("invoice");
							   itemToDB.getNewNoteSystem().get(2).setLastModifiedDate(new Date());
							   if(note.getCreatedDate() != null) itemToDB.getNewNoteSystem().get(2).setCreatedDate(note.getCreatedDate());
							   //if(note.getNoteId() >= 0) itemToDB.getNewNoteSystem().get(2).setNoteId(note.getNoteId());
							   if(note.getText() != null) itemToDB.getNewNoteSystem().get(2).setText(note.getText());
							   break;
						   case "internal": case "internal_note":
							   itemToDB.getNewNoteSystem().get(3).setNoteType("internal");
							   itemToDB.getNewNoteSystem().get(3).setLastModifiedDate(new Date());
							   if(note.getCreatedDate() != null) itemToDB.getNewNoteSystem().get(3).setCreatedDate(note.getCreatedDate());
							   //if(note.getNoteId() >= 0) itemToDB.getNewNoteSystem().get(3).setNoteId(note.getNoteId());
							   if(note.getText() != null) itemToDB.getNewNoteSystem().get(3).setText(note.getText());
							   break;	
						   case "additional": case "additional_note":
							   itemToDB.getNewNoteSystem().get(4).setNoteType("additional");
							   itemToDB.getNewNoteSystem().get(4).setLastModifiedDate(new Date());
							   if(note.getCreatedDate() != null) itemToDB.getNewNoteSystem().get(4).setCreatedDate(note.getCreatedDate());
							   //if(note.getNoteId() >= 0) itemToDB.getNewNoteSystem().get(4).setNoteId(note.getNoteId());
							   if(note.getText() != null) itemToDB.getNewNoteSystem().get(4).setText(note.getText());
							   break;	   
						}
					}
			        //update legacy notes
				    if((legacyNotes.getPonotes() == null || legacyNotes.getPonotes().isEmpty()) && "po".equalsIgnoreCase(note.getNoteType()))
			        	legacyNotes.setPonotes(note.getText());
			        else if((legacyNotes.getNotes1() == null || legacyNotes.getNotes1().isEmpty()) && "buyer".equalsIgnoreCase(note.getNoteType()))
			        	legacyNotes.setNotes1(note.getText());
			        else if((legacyNotes.getNotes2() == null || legacyNotes.getNotes2().isEmpty()) && "additional".equalsIgnoreCase(note.getNoteType()))
		    	    	legacyNotes.setNotes2(note.getText());
			        else if((legacyNotes.getNotes3() == null || legacyNotes.getNotes3().isEmpty()) && "invoice".equalsIgnoreCase(note.getNoteType()))
		    	    	legacyNotes.setNotes3(note.getText());        
			    }
				if(legacyNotes != null)
					itemToDB.setNotes(legacyNotes);
			}*/	
}
