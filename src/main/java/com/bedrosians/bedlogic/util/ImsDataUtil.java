package com.bedrosians.bedlogic.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.bedrosians.bedlogic.domain.item.IconCollection;
import com.bedrosians.bedlogic.domain.item.ItemNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.ItemVendor;
import com.bedrosians.bedlogic.domain.item.embeddable.Applications;
import com.bedrosians.bedlogic.domain.item.embeddable.Notes;
import com.bedrosians.bedlogic.domain.item.embeddable.PackagingInfo;
import com.bedrosians.bedlogic.domain.item.embeddable.Price;
import com.bedrosians.bedlogic.domain.item.embeddable.Units;
import com.bedrosians.bedlogic.domain.item.embeddable.VendorInfo;
import com.bedrosians.bedlogic.domain.item.enums.DBOperation;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;
import com.bedrosians.bedlogic.domain.item.enums.OriginCountry;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;


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
	
        icon.setExteriorProduct(icons.length() < 2? false : icons.charAt(1) == 'Y');
        icon.setAdaAccessibility(icons.length() < 4? false : icons.charAt(3) == 'Y');
        icon.setThroughColor(icons.length() < 5? false : icons.charAt(4) == 'Y');
        icon.setInkJet(icons.length() < 6? false : icons.charAt(5) == 'Y');
        icon.setRecycled(icons.length() < 7? false : icons.charAt(6) == 'Y');
        icon.setColorBody(icons.length() < 8? false : icons.charAt(7) == 'Y');
        icon.setGlazed(icons.length() < 9? false : icons.charAt(8) == 'Y');
        icon.setRectifiedEdge(icons.length() < 10? false : icons.charAt(9) == 'Y');
        icon.setUnglazed(icons.length() < 11? false : icons.charAt(10) == 'Y');
        icon.setPostRecycled(icons.length() < 12? false : icons.charAt(11) == 'Y');
        icon.setPreRecycled(icons.length() < 13? false : icons.charAt(12) == 'Y');
        icon.setCoefficientOfFriction(icons.length() < 17? false : icons.charAt(16) == 'Y');
        icon.setChiseledEdge(icons.length() < 18? false : icons.charAt(17) == 'Y');		
        
        return icon;
	}
	
	public static String convertIconCollectionToLegancyIcons(IconCollection iconCollection){
    	char[] legacyIcons = new char[20];
    	
    	legacyIcons[0] = OriginCountry.Italy.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[1] = (iconCollection.getExteriorProduct() != null && iconCollection.getExteriorProduct() == true) ? 'Y' : 'N'; 
    	legacyIcons[2] = OriginCountry.USA.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[3] = (iconCollection.getAdaAccessibility() != null && iconCollection.getAdaAccessibility() == true) ? 'Y' : 'N'; 
    	legacyIcons[4] = (iconCollection.getThroughColor() != null && iconCollection.getThroughColor() == true) ? 'Y' : 'N'; 
    	legacyIcons[5] = (iconCollection.getInkJet() != null && iconCollection.getInkJet() == true) ? 'Y' : 'N'; 
    	legacyIcons[6] = (iconCollection.getRecycled() != null && iconCollection.getRecycled() == true) ? 'Y' : 'N'; 
    	legacyIcons[7] = (iconCollection.getColorBody() != null && iconCollection.getColorBody() == true) ? 'Y' : 'N'; 
    	legacyIcons[8] = (iconCollection.getGlazed() != null && iconCollection.getGlazed() == true) ? 'Y' : 'N'; 
    	legacyIcons[9] = (iconCollection.getRectifiedEdge() != null && iconCollection.getRectifiedEdge() == true) ? 'Y' : 'N'; 
    	legacyIcons[10] = (iconCollection.getUnglazed() != null && iconCollection.getUnglazed() == true) ? 'Y' : 'N'; 
    	legacyIcons[11] = (iconCollection.getPostRecycled() != null && iconCollection.getPostRecycled() == true) ? 'Y' : 'N'; 
    	legacyIcons[12] = (iconCollection.getPreRecycled() != null && iconCollection.getPreRecycled() == true) ? 'Y' : 'N'; 
    	legacyIcons[13] = OriginCountry.China.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[14] = OriginCountry.Turkey.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[15] = OriginCountry.Mexico.equals(iconCollection.getMadeInCountry()) ? 'Y' : 'N'; 
    	legacyIcons[16] = (iconCollection.getCoefficientOfFriction() != null && iconCollection.getCoefficientOfFriction() == true) ? 'Y' : 'N'; 
    	legacyIcons[17] = (iconCollection.getChiseledEdge() != null && iconCollection.getChiseledEdge() == true) ? 'Y' : 'N'; 
    	legacyIcons[18] = 'N'; 
    	legacyIcons[19] = 'N'; 
    	    
    	return new String(legacyIcons);
    }
	
	protected static ItemVendor convertImsVendorInfoToItemVendor(VendorInfo vendorInfo){
		ItemVendor itemVendor = new ItemVendor();
		if(vendorInfo != null){
			itemVendor.setVendorId(vendorInfo.getVendornbr1());
			itemVendor.setVendorXrefId(vendorInfo.getVendorxrefcd());
			itemVendor.setVendorFob(vendorInfo.getVendorfob());
			itemVendor.setDutyPct(vendorInfo.getDutypct());
			itemVendor.setLeadTime(vendorInfo.getLeadtime());
			if(vendorInfo.getVendorlistprice() != null) itemVendor.setVendorListPrice(vendorInfo.getVendorlistprice());
			if(vendorInfo.getVendorpriceunit() != null) itemVendor.setVendorPriceUnit(vendorInfo.getVendorpriceunit());
			if(vendorInfo.getVendordiscpct() != null) itemVendor.setVendorDiscountPct(vendorInfo.getVendordiscpct());
		    if(vendorInfo.getVendorroundaccuracy() != null) itemVendor.setVendorPriceRoundAccuracy(vendorInfo.getVendorroundaccuracy());
		    if(vendorInfo.getVendornetprice() != null) itemVendor.setVendorNetPrice(vendorInfo.getVendornetprice());
	        if(vendorInfo.getVendormarkuppct() != null) itemVendor.setVendorMarkupPct(vendorInfo.getVendormarkuppct());
		    if(vendorInfo.getVendorfreightratecwt() != null) itemVendor.setVendorFreightRateCwt(vendorInfo.getVendorfreightratecwt());
		    if(vendorInfo.getVendorlandedbasecost() != null) itemVendor.setVendorLandedBaseCost(vendorInfo.getVendorlandedbasecost());
		 } 
		return itemVendor;
	}

	//convert new vendor system data to legacy vendor data
	protected static VendorInfo convertItemVendorToImsVendorInfo(ItemVendor itemVendor){
		VendorInfo vendorInfo = new VendorInfo();
		if(itemVendor != null){
			if(itemVendor.getItemVendorId() != null) vendorInfo.setVendornbr1(itemVendor.getVendorId());
			if(itemVendor.getVendorXrefId() != null) vendorInfo.setVendorxrefcd(itemVendor.getVendorXrefId());
			if(itemVendor.getVendorFob() != null) vendorInfo.setVendorfob(itemVendor.getVendorFob());
			if(itemVendor.getDutyPct() != null) vendorInfo.setDutypct(itemVendor.getDutyPct());
			if(itemVendor.getLeadTime() != null) vendorInfo.setLeadtime(itemVendor.getLeadTime());
			if(itemVendor.getVendorListPrice() != null) vendorInfo.setVendorlistprice(itemVendor.getVendorListPrice());
			if(itemVendor.getVendorPriceUnit() != null) vendorInfo.setVendorpriceunit(itemVendor.getVendorPriceUnit());
			if(itemVendor.getVendorDiscountPct() != null) vendorInfo.setVendordiscpct(itemVendor.getVendorDiscountPct());
		    if(itemVendor.getVendorPriceRoundAccuracy() != null) vendorInfo.setVendorroundaccuracy(itemVendor.getVendorPriceRoundAccuracy());
		    if(itemVendor.getVendorNetPrice() != null) vendorInfo.setVendornetprice(itemVendor.getVendorNetPrice());
	        if(itemVendor.getVendorMarkupPct() != null) vendorInfo.setVendormarkuppct(itemVendor.getVendorMarkupPct());
		    if(itemVendor.getVendorFreightRateCwt() != null) vendorInfo.setVendorfreightratecwt(itemVendor.getVendorFreightRateCwt());
		    if(itemVendor.getVendorLandedBaseCost() != null) vendorInfo.setVendorlandedbasecost(itemVendor.getVendorLandedBaseCost());
		 } 
		return vendorInfo;
	}

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

	public static Applications convertUsageToApplications(List<String> usage){
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

	public static List<String> convertColorCategoryToStringList(String colorCategory){
		if(colorCategory == null)
		   return null;	
		return Arrays.asList(colorCategory.trim().split(":"));
	}
	
	public static String convertColorhueStringListToColorCategory(List<String> colorStringList){
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
	
	/*public static Set<ColorHue> convertColorCategoryToColorHueString(String colorCategory){
		if(colorCategory == null || colorCategory.isEmpty())
		   return null;	
		else
		   return new HashSet(Arrays.asList(colorCategory.trim().split(":")));
	}
	
	public static Set<ColorHue> convertColorCategoryToColorHueObjects(Item item){
		Set<ColorHue> colorHues = new HashSet<>();
		ColorHue colorHue = null;
		if(item.getColorcategory() != null && !item.getColorcategory().isEmpty()) {
		   for(String color : item.getColorcategory().trim().split(":")){
			   if(color != null && !color.isEmpty()){
  			      colorHue = new ColorHue(color);
			      colorHue.setItem(item);
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
	*/
	//public static Item parsePriorVendor(Item item){
	//	
	//	if(item.getPriorVendor() != null && item.getPriorVendor().getPriorvendorpriceunit() == null 
	//			                         && item.getPriorVendor().getPriorvendorfob() == null) 
	//			                         //&& item.getPriorVendor().getPriorvendorlistprice() == null)
	//		item.setPriorVendor(null);	
	//	return item;
	//}
	
	public static List<String> convertApplicationsToUsage(Item item){
		StringBuilder stringBuilder = new StringBuilder();
		if(item.getApplications() != null){
	       if(item.getApplications().getResidential() != null && !item.getApplications().getResidential().isEmpty()){
		      stringBuilder.append(item.getApplications().getResidential());
	       }   
		   if(item.getApplications().getLightcommercial() != null && !item.getApplications().getLightcommercial().isEmpty()){
			  if(stringBuilder != null && stringBuilder.length() > 0)
		         stringBuilder.append(":").append(item.getApplications().getLightcommercial());
		      else
			     stringBuilder.append(item.getApplications().getLightcommercial());
		   }
		   if(item.getApplications().getCommercial() != null && !item.getApplications().getCommercial().isEmpty()){
			   if(stringBuilder != null && stringBuilder.length() > 0)
			      stringBuilder.append(":").append(item.getApplications().getCommercial());
			   else
				  stringBuilder.append(item.getApplications().getCommercial());
		   }
		}
		return Arrays.asList(stringBuilder.toString().split(":"));
	}
	
	public static String getStandardSellUnit(Item item) {
		if(item.getUnits() == null)
		   return null;
		String standardUnit = item.getUnits().getBaseunit();	
        if (item.getUnits().getUnit1isstdsell() != null && item.getUnits().getUnit1isstdsell() == 'Y')
        	standardUnit = item.getUnits().getUnit1unit();
        else if (item.getUnits().getUnit2isstdsell() != null && item.getUnits().getUnit2isstdsell() == 'Y')
        	standardUnit = item.getUnits().getUnit2unit();
        else if (item.getUnits().getUnit3isstdsell() != null && item.getUnits().getUnit3isstdsell() == 'Y')
        	standardUnit = item.getUnits().getUnit3unit();
        else if (item.getUnits().getUnit4isstdsell() != null && item.getUnits().getUnit4isstdsell() == 'Y')
        	standardUnit = item.getUnits().getUnit4unit();
        return standardUnit;
    }
	
	public static String getStandardPurchaseUnit(Item item) {
		if(item.getUnits() == null)
		   return null;	
		String standardUnit = item.getUnits().getBaseunit();
		if (item.getUnits().getUnit1isstdsell() != null && item.getUnits().getUnit1isstdord() == 'Y')
        	standardUnit = item.getUnits().getUnit1unit();
        else if (item.getUnits().getUnit2isstdsell() != null && item.getUnits().getUnit2isstdord() == 'Y')
        	standardUnit = item.getUnits().getUnit2unit();
        else if (item.getUnits().getUnit3isstdsell() != null && item.getUnits().getUnit3isstdord() == 'Y')
        	standardUnit = item.getUnits().getUnit3unit();
        else if (item.getUnits().getUnit4isstdsell() != null && item.getUnits().getUnit4isstdord() == 'Y')
        	standardUnit = item.getUnits().getUnit4unit();
        return standardUnit;
    }
	
    public static PackagingInfo getPackagingInfo(Item item) {
	    if(item == null || item.getUnits() == null)
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
    	
    	String unit1Unit = item.getUnits().getUnit1unit();
    	String unit2Unit = item.getUnits().getUnit2unit();
    	String unit4Unit = item.getUnits().getUnit4unit();	
        unit1Ratio = item.getUnits().getUnit1ratio();
        unit2Ratio = item.getUnits().getUnit2ratio();
        unit4Ratio = item.getUnits().getUnit4ratio();
        if(item.getUnits().getBasewgtperunit() != null)
           baseWgtPerUnit = (item.getUnits().getBasewgtperunit()).floatValue();
		String standardUnit = item.getUnits().getBaseunit();
		
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
   
	public static float getBaseToSellRatio(Item item) {
		float baseToSellRatio = 1f;
 
        if(item.getUnits().getUnit1isstdsell() != null && "Y".equalsIgnoreCase(item.getUnits().getUnit1isstdsell().toString().trim()))
            baseToSellRatio = item.getUnits().getUnit1ratio();
        else if(item.getUnits().getUnit2isstdsell() != null && "Y".equalsIgnoreCase(item.getUnits().getUnit2isstdsell().toString().trim()))
            baseToSellRatio = item.getUnits().getUnit2ratio();
        else if(item.getUnits().getUnit3isstdsell() != null && "Y".equalsIgnoreCase(item.getUnits().getUnit3isstdsell().toString().trim()))
            baseToSellRatio = item.getUnits().getUnit3ratio();
        else if(item.getUnits().getUnit4isstdsell() != null && "Y".equalsIgnoreCase(item.getUnits().getUnit4isstdsell().toString().trim()));
            baseToSellRatio = item.getUnits().getUnit4ratio();
       
         if(baseToSellRatio == 0)
        	baseToSellRatio = 1;
       
        return baseToSellRatio;
    }
	
	
	public static String getPackUnit(Item item)
    {
		String packUnit = item.getUnits().getBaseunit();
 
        if(item.getUnits().getUnit1ispackunit() != null && "Y".equalsIgnoreCase(item.getUnits().getUnit1ispackunit().toString().trim()))
           packUnit = item.getUnits().getUnit1unit();
        else if(item.getUnits().getUnit2ispackunit() != null && "Y".equalsIgnoreCase(item.getUnits().getUnit2ispackunit().toString().trim()))
           packUnit = item.getUnits().getUnit2unit();   
        else if(item.getUnits().getUnit3ispackunit() != null && "Y".equalsIgnoreCase(item.getUnits().getUnit3ispackunit().toString().trim()))
           packUnit = item.getUnits().getUnit3unit();   
        else if(item.getUnits().getUnit4ispackunit() != null && "Y".equalsIgnoreCase(item.getUnits().getUnit4ispackunit().toString().trim()))
           packUnit = item.getUnits().getUnit4unit();    
       
        return packUnit;
    }

	public static Item transformItem(Item itemToDB, Item itemFromInput, DBOperation operation) throws BedDAOBadParamException{
		if(itemFromInput == null)
	       throw new BedDAOBadParamException("In transformItem(), the input is empty");	
		if(itemToDB == null) 
		   itemToDB = new Item(itemFromInput.getItemcode().toUpperCase());		
		transferProperty(itemToDB, itemFromInput);
		if(operation != null && operation.equals(DBOperation.CREATE))
	  	   transferComponent(itemToDB, itemFromInput);
		else if(operation != null && operation.equals(DBOperation.UPDATE))		
		   transferComponentForUpdate(itemToDB, itemFromInput);
		transferAssociation(itemToDB, itemFromInput, operation);	
		return itemToDB;
	}
	
	private static void transferAssociation(Item itemToDB, Item itemFromInput, DBOperation operation) throws BedDAOBadParamException{
	  try{
		ItemNewFeature inputNewFeature = itemFromInput.getImsNewFeature();
	    //List<ColorHue> inputColorHues = itemFromInput.getColorhues();
	    IconCollection inputIconCollection = itemFromInput.getIconDescription();
		List<ItemVendor> inputItemVendors = itemFromInput.getNewVendorSystem();
		VendorInfo vendorInfo = itemFromInput.getVendors();
		String legacyIcon = itemFromInput.getIconsystem();
		//List<Note> noteList = itemFromInput.getNewNoteSystem();
		//Notes legacyNotes = (itemFromInput.getNotes() != null)? itemFromInput.getNotes() : new Notes();
		if(inputNewFeature != null && !inputNewFeature.isEmpty()){
		    if(operation.equals(DBOperation.CREATE) || //brand new Item
		      (operation.equals(DBOperation.UPDATE) && itemToDB.getImsNewFeature() == null)){ //existing Item, but brand new NewImsFeature
		       if(inputNewFeature.getCreatedDate() == null)	
			      inputNewFeature.setCreatedDate(new Date());
		       itemToDB.addImsNewFeature(inputNewFeature);
		    }
		    else if(operation.equals(DBOperation.UPDATE)){
		    	if(inputNewFeature.getBody() != null) itemToDB.getImsNewFeature().setBody(inputNewFeature.getBody());
		    	if(inputNewFeature.getDesignLook() != null) itemToDB.getImsNewFeature().setDesignLook(inputNewFeature.getDesignLook());
		    	if(inputNewFeature.getDesignStyle() != null) itemToDB.getImsNewFeature().setDesignStyle(inputNewFeature.getDesignStyle());
		      	if(inputNewFeature.getDroppedDate() != null) itemToDB.getImsNewFeature().setDroppedDate(inputNewFeature.getDroppedDate());
		    	if(inputNewFeature.getEdge() != null) itemToDB.getImsNewFeature().setEdge(inputNewFeature.getEdge());
		    	if(inputNewFeature.getGrade() != null) itemToDB.getImsNewFeature().setGrade(inputNewFeature.getGrade());
		      	if(inputNewFeature.getLaunchedDate() != null) itemToDB.getImsNewFeature().setLaunchedDate(inputNewFeature.getLaunchedDate());
		    	if(inputNewFeature.getMpsCode() != null) itemToDB.getImsNewFeature().setMpsCode(inputNewFeature.getMpsCode());
		    	if(inputNewFeature.getRecommendedGroutJointMax() != null) itemToDB.getImsNewFeature().setRecommendedGroutJointMax(inputNewFeature.getRecommendedGroutJointMax());
		    	if(inputNewFeature.getRecommendedGroutJointMin() != null) itemToDB.getImsNewFeature().setRecommendedGroutJointMin(inputNewFeature.getRecommendedGroutJointMin());
		    	if(inputNewFeature.getStatus() != null) itemToDB.getImsNewFeature().setStatus(inputNewFeature.getStatus());
		    	if(inputNewFeature.getSurfaceApplication() != null) itemToDB.getImsNewFeature().setSurfaceApplication(inputNewFeature.getSurfaceApplication());
		      	if(inputNewFeature.getSurfaceFinish() != null) itemToDB.getImsNewFeature().setSurfaceFinish(inputNewFeature.getSurfaceFinish());
		    	if(inputNewFeature.getSurfaceType() != null) itemToDB.getImsNewFeature().setSurfaceType(inputNewFeature.getSurfaceType());
		    	if(inputNewFeature.getWarranty() != null) itemToDB.getImsNewFeature().setWarranty(inputNewFeature.getWarranty());
		    	itemToDB.getImsNewFeature().setLastModifiedDate(new Date());
		    } 	
		}
	
		if(inputIconCollection != null && !inputIconCollection.isEmpty()){
			if(operation.equals(DBOperation.CREATE) || //Brand new Item
			  (operation.equals(DBOperation.UPDATE) && (itemToDB.getIconDescription() == null || itemToDB.getIconDescription().isEmpty()))){ //existing Item, but brand new inputIconCollection
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
				if(inputIconCollection.getGreenFriendlyIcon() != null) itemToDB.getIconDescription().setGreenFriendlyIcon(inputIconCollection.getGreenFriendlyIcon());
				if(inputIconCollection.getInkJet() != null) itemToDB.getIconDescription().setInkJet(inputIconCollection.getInkJet());
				if(inputIconCollection.getLeadPointIcon() != null) itemToDB.getIconDescription().setLeadPointIcon(inputIconCollection.getLeadPointIcon());
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
		else if(legacyIcon != null && !legacyIcon.isEmpty()){
			IconCollection iconCollection = ImsDataUtil.convertLegacyIconsToIconCollection(legacyIcon);
			if(operation.equals(DBOperation.CREATE) || //Brand new Item
			  (operation.equals(DBOperation.UPDATE) && (itemToDB.getIconDescription() == null || itemToDB.getIconDescription().isEmpty()))){ //existing Item, but brand new inputIconCollection
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
				if(iconCollection.getGreenFriendlyIcon() != null) itemToDB.getIconDescription().setGreenFriendlyIcon(iconCollection.getGreenFriendlyIcon());
				if(iconCollection.getInkJet() != null) itemToDB.getIconDescription().setInkJet(iconCollection.getInkJet());
				if(iconCollection.getLeadPointIcon() != null) itemToDB.getIconDescription().setLeadPointIcon(iconCollection.getLeadPointIcon());
				if(iconCollection.getPostRecycled() != null) itemToDB.getIconDescription().setPostRecycled(iconCollection.getPostRecycled());
				if(iconCollection.getPreRecycled() != null) itemToDB.getIconDescription().setPreRecycled(iconCollection.getPreRecycled());
				if(iconCollection.getRectifiedEdge() != null) itemToDB.getIconDescription().setRectifiedEdge(iconCollection.getRectifiedEdge());
				if(iconCollection.getRecycled() != null) itemToDB.getIconDescription().setRecycled(iconCollection.getRecycled());
				if(iconCollection.getThroughColor() != null) itemToDB.getIconDescription().setThroughColor(iconCollection.getThroughColor());
				if(iconCollection.getMadeInCountry() != null) itemToDB.getIconDescription().setMadeInCountry(iconCollection.getMadeInCountry());
				if(iconCollection.getUnglazed() != null) itemToDB.getIconDescription().setUnglazed(iconCollection.getUnglazed());
				if(iconCollection.getVersaillesPattern() != null) itemToDB.getIconDescription().setVersaillesPattern(inputIconCollection.getVersaillesPattern());
			}
		}
/*		//---------------Color Hue data---------------//
		if(inputColorHues != null && !inputColorHues.isEmpty()){
			if(operation.equals(DBOperation.CREATE) || //Brand new Item
			  (operation.equals(DBOperation.UPDATE) && (itemToDB.getColorhues() == null || itemToDB.getColorhues().isEmpty()))){ //existing Item, but brand new ItemColoeHue
			   for(ColorHue color : inputColorHues){	
	  		       if(color != null && color.getColorHue() != null && !color.getColorHue().isEmpty())
			          itemToDB.addColorhue(color);	
		       }
			}  //update existing ItemColoeHue
			else if(operation.equals(DBOperation.UPDATE)){
			   for(int i = 0; i < inputColorHues.size(); i++){
				   ColorHue colorHue = inputColorHues.get(i);
				   if(i >= itemToDB.getColorhues().size())
					   itemToDB.getColorhues().add(i, new ColorHue(itemToDB.getItemcode())); 
				   if(colorHue.getColorHue() != null) itemToDB.getColorhues().get(i).setColorHue(colorHue.getColorHue());
			   }	   
			}
	        itemToDB.setColorcategory(ImsDataUtil.convertColorHuesToColorCategory(inputColorHues));
		
		}//obtain ColorHue objects from colorCategory in the input, in order to sync the color_hue table with ims table, if the input doesn't contain associated colueHue
		else if(itemFromInput.getColorcategory() != null && !itemFromInput.getColorcategory().isEmpty()){
			inputColorHues = ImsDataUtil.convertColorCategoryToColorHueObjects(itemFromInput.getColorcategory());
			for(ColorHue color : inputColorHues){	
			    if(color != null && color.getColorHue() != null && !color.getColorHue().isEmpty())
				   itemToDB.addColorhue(color);	
			}
		}
*/	    //---------- vendors data ----------//
		if(inputItemVendors != null && !inputItemVendors.isEmpty()){
		   if(operation.equals(DBOperation.CREATE)|| //brand new Item
		     (operation.equals(DBOperation.UPDATE) && (itemToDB.getNewVendorSystem() == null || itemToDB.getNewVendorSystem().isEmpty()))){ //existing Item, but brand new ItemVendors
			  for(ItemVendor vendor : inputItemVendors){
			      if(vendor != null && !vendor.isEmpty()){
				     itemToDB.addNewVendorSystem(vendor);	
				     //Populate vendor info in ims
				     if((vendorInfo == null || vendorInfo.getVendornbr1() == null || vendorInfo.getVendornbr1() == 0) && vendor.getVendorOrder() == 1){
				         itemToDB.setVendors(convertItemVendorToImsVendorInfo(vendor));	
				     }
				   }   
			   }    
		   }
    	   else if(operation.equals(DBOperation.UPDATE)){ //update Item_Vendor table for existing Item
			   for(int i = 0; i < inputItemVendors.size(); i++){
				   ItemVendor vendor = inputItemVendors.get(i);
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
		           //Populate vendor info in ims table
				   if((vendorInfo == null || vendorInfo.getVendornbr1() == null || vendorInfo.getVendornbr1() == 0) && vendor.getVendorOrder() == 1)
				       itemToDB.setVendors(convertItemVendorToImsVendorInfo(vendor));				    
			   }
			   
    	   }	   
		}//Populate Ims_Item_Vendor table with the ims vendors info when ItemVendor is not available 
		else if((vendorInfo != null && vendorInfo.getVendornbr1() != null) && (inputItemVendors == null || inputItemVendors.isEmpty())){
			//ItemVendor vendor = convertImsVendorInfoToItemVendor(vendorInfo);
			if(itemToDB.getNewVendorSystem() == null || itemToDB.getNewVendorSystem().isEmpty())
			   itemToDB.addNewVendorSystem(new ItemVendor());
			if(vendorInfo.getVendornbr1() != null) itemToDB.getNewVendorSystem().get(0).setVendorId(vendorInfo.getVendornbr1());
			if(vendorInfo.getVendorxrefcd() != null) itemToDB.getNewVendorSystem().get(0).setVendorXrefId(vendorInfo.getVendorxrefcd());
			if(vendorInfo.getVendorfob() != null) itemToDB.getNewVendorSystem().get(0).setVendorFob(vendorInfo.getVendorfob());
			if(vendorInfo.getDutypct() != null) itemToDB.getNewVendorSystem().get(0).setDutyPct(vendorInfo.getDutypct());
			if(vendorInfo.getVendorlistprice() != null) itemToDB.getNewVendorSystem().get(0).setLeadTime(vendorInfo.getLeadtime());
			if(vendorInfo.getVendorlistprice() != null) itemToDB.getNewVendorSystem().get(0).setVendorListPrice(vendorInfo.getVendorlistprice());
			if(vendorInfo.getVendorpriceunit() != null) itemToDB.getNewVendorSystem().get(0).setVendorPriceUnit(vendorInfo.getVendorpriceunit());
			if(vendorInfo.getVendordiscpct() != null) itemToDB.getNewVendorSystem().get(0).setVendorDiscountPct(vendorInfo.getVendordiscpct());
		    if(vendorInfo.getVendorroundaccuracy() != null) itemToDB.getNewVendorSystem().get(0).setVendorPriceRoundAccuracy(vendorInfo.getVendorroundaccuracy());
		    if(vendorInfo.getVendornetprice() != null) itemToDB.getNewVendorSystem().get(0).setVendorNetPrice(vendorInfo.getVendornetprice());
	        if(vendorInfo.getVendormarkuppct() != null) itemToDB.getNewVendorSystem().get(0).setVendorMarkupPct(vendorInfo.getVendormarkuppct());
		    if(vendorInfo.getVendorfreightratecwt() != null) itemToDB.getNewVendorSystem().get(0).setVendorFreightRateCwt(vendorInfo.getVendorfreightratecwt());
		    if(vendorInfo.getVendorlandedbasecost() != null) itemToDB.getNewVendorSystem().get(0).setVendorLandedBaseCost(vendorInfo.getVendorlandedbasecost());
		    itemToDB.getNewVendorSystem().get(0).setVendorOrder(1);
			//vendor.setVendorOrder(1);
			//itemToDB.addNewVendorSystem(vendor);
		}
	
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
	  catch(Exception e){
		   if(e == null)
			  throw new BedDAOBadParamException("Error occured while transferAssociation()");	
		   else
			   throw new BedDAOBadParamException("Error occured while transferAssociation(): " + e.getMessage(), e.getCause());	 
	  }	
		
	}

	private static void transferComponent(Item itemToDB, Item itemFromInput) throws BedDAOBadParamException{
	  try{
		if(itemFromInput.getApplications() != null) 
		   itemToDB.setApplications(itemFromInput.getApplications());
		else if(itemFromInput.getUsage() != null) 
		   itemToDB.setApplications(convertUsageToApplications(itemFromInput.getUsage()));	
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
			itemToDB.setUnits(units);
		}
	  }
	  catch(Exception e){
			  throw new BedDAOBadParamException("Error occured while transferComponent(): " + e.getMessage(), e.getCause());	
	  }		
	}

	private static void transferComponentForUpdate(Item itemToDB, Item itemFromInput) throws BedDAOBadParamException{
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
				  if(itemFromInput.getSeries().getSeriesname() != null) 
					 itemToDB.getSeries().setSeriesname(itemFromInput.getSeries().getSeriesname());
				  if(itemFromInput.getSeries().getSeriescolor() != null) 
					 itemToDB.getSeries().setSeriescolor(itemFromInput.getSeries().getSeriescolor());
			   }
			   //dimension
			   if(itemFromInput.getDimensions() != null){
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
				  if(itemFromInput.getApplications().getCommercial() != null)
					  itemToDB.getApplications().setCommercial(itemFromInput.getApplications().getCommercial()); 
				  if(itemFromInput.getApplications().getLightcommercial() != null)
					  itemToDB.getApplications().setLightcommercial(itemFromInput.getApplications().getLightcommercial());
				  if(itemFromInput.getApplications().getResidential() != null)	
					  itemToDB.getApplications().setResidential(itemFromInput.getApplications().getResidential());	  
			  }
			  //update applications fields with usage input data when applications data is not available
			  else if(itemFromInput.getUsage() != null){ 
				  Applications applications = convertUsageToApplications(itemFromInput.getUsage());
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
				if(itemFromInput.getPurchasers().getPurchaser() != null) 
					itemToDB.getPurchasers().setPurchaser(itemFromInput.getPurchasers().getPurchaser()); 
				if(itemFromInput.getPurchasers().getPurchaser2() != null) 
					itemToDB.getPurchasers().setPurchaser2(itemFromInput.getPurchasers().getPurchaser2()); 
			}
			//related items
			if(itemFromInput.getRelateditemcodes() != null){
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
			}
			//units
			if(itemFromInput.getUnits() != null && !itemFromInput.getUnits().isDefault()){
				//----general----//
				if(itemFromInput.getUnits().getOrdratio() != null && itemFromInput.getUnits().getOrdratio() != 0)
				   itemToDB.getUnits().setOrdratio(itemFromInput.getUnits().getOrdratio());
				if(itemFromInput.getUnits().getOrdunit() != null)
				   itemToDB.getUnits().setOrdunit(itemFromInput.getUnits().getOrdunit());
			    if(itemFromInput.getUnits().getStdratio() != null && itemFromInput.getUnits().getStdratio() != 0)
				   itemToDB.getUnits().setStdratio(itemFromInput.getUnits().getStdratio());	
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
				  throw new BedDAOBadParamException("Error occured while transferComponent(): " + e.getMessage(), e.getCause());	
		  }		
	}
	
	private static void transferProperty(Item itemToDB, Item itemFromInput) throws BedDAOBadParamException{
	  try{	
		if(itemFromInput.getAbccd() != null) itemToDB.setAbccd(itemFromInput.getAbccd());
		if(itemFromInput.getCountryorigin() != null) itemToDB.setCountryorigin(itemFromInput.getCountryorigin());
		if(itemFromInput.getDirectship() != null) itemToDB.setDirectship(itemFromInput.getDirectship());
		if(itemFromInput.getDropdate() != null) itemToDB.setDropdate(itemFromInput.getDropdate());
		if(itemFromInput.getIconsystem() != null) itemToDB.setIconsystem(itemFromInput.getIconsystem());
		if(itemFromInput.getInactivecode() != null) itemToDB.setInactivecode(itemFromInput.getInactivecode());
		if(itemFromInput.getInventoryitemcd() != null) itemToDB.setInventoryitemcd(itemFromInput.getInventoryitemcd());
		if(itemFromInput.getItemcd2() != null) itemToDB.setItemcd2(itemFromInput.getItemcd2());
		if(itemFromInput.getItemgroupnbr() != null) itemToDB.setItemgroupnbr(itemFromInput.getItemgroupnbr());
		if(itemFromInput.getItemtypecd() != null) itemToDB.setItemtypecd(itemFromInput.getItemtypecd());	
		if(itemFromInput.getItemcategory() != null) itemToDB.setItemcategory(itemFromInput.getItemcategory());
		if(itemFromInput.getLottype() != null) itemToDB.setLottype(itemFromInput.getLottype());
		if(itemFromInput.getOffshade() != null) itemToDB.setOffshade(itemFromInput.getOffshade());
		if(itemFromInput.getPriorlastupdated() != null) itemToDB.setPriorlastupdated(itemFromInput.getPriorlastupdated());
		if(itemFromInput.getPrintlabel() != null) itemToDB.setPrintlabel(itemFromInput.getPrintlabel());
		if(itemFromInput.getShadevariation() != null) itemToDB.setShadevariation(itemFromInput.getShadevariation());
		if(itemFromInput.getShowonalysedwards() != null) itemToDB.setShowonalysedwards(itemFromInput.getShowonalysedwards());
		if(itemFromInput.getShowonwebsite() != null) itemToDB.setShowonwebsite(itemFromInput.getShowonwebsite());
		if(itemFromInput.getTaxclass() != null) itemToDB.setTaxclass(itemFromInput.getTaxclass());
		if(itemFromInput.getUpdatecd() != null) itemToDB.setUpdatecd(itemFromInput.getUpdatecd());
		if(itemFromInput.getProductline() != null) itemToDB.setProductline(itemFromInput.getProductline());
		//if(itemFromInput.getSubtype() != null) itemToDB.setSubtype(itemFromInput.getSubtype());
		//if(itemFromInput.getType() != null) itemToDB.setType(itemFromInput.getType());
		if(itemFromInput.getColorcategory() != null && !itemFromInput.getColorcategory().isEmpty()){
			itemToDB.setColorcategory(itemFromInput.getColorcategory());
		}
		else if(itemFromInput.getColorhues() != null && !itemFromInput.getColorhues().isEmpty()){
				itemToDB.setColorcategory(ImsDataUtil.convertColorhueStringListToColorCategory(itemFromInput.getColorhues()));	
		}
		
	    /*if(itemFromInput.getColorcategory() != null){
		   if(itemFromInput.getColorcategory().contains(":")){
			  String[] colors = itemFromInput.getColorcategory().split(":");
			  for(String color : colors){
				  itemToDB.addColorhue(new ColorHue(color));
			  }
		   }
		   itemToDB.setColorcategory(itemFromInput.getColorcategory());
		}
		*/
	  }
	  catch(Exception e){
			  throw new BedDAOBadParamException("Error occured while transferProperty(): " + e.getMessage(), e.getCause());	
	  }	
	}
	
	
	/*public static Item parseNotes(Item item){
	    String additionalNoteText = null;
	    Note buyerNote = null;
	  	if(item.getNewNoteSystem() != null && !item.getNewNoteSystem().isEmpty()){
           for(Note note : item.getNewNoteSystem())	{
        	   if("buyer".equalsIgnoreCase(note.getNoteType()) || "buyer_note".equalsIgnoreCase(note.getNoteType()))
        		   buyerNote = note;
        	   else if("additional".equalsIgnoreCase(note.getNoteType()) || "additional_note".equalsIgnoreCase(note.getNoteType()))
        		   additionalNoteText = note.getNote();
           }  
	       if(additionalNoteText != null) {
			  buyerNote.setNote(buyerNote.getNote() + ". " +additionalNoteText);
		      item.getNewNoteSystem().set(1, buyerNote);
	       }
	  	}
	  	return item;
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
	
	
}
