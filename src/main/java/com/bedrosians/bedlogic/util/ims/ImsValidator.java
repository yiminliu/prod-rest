package com.bedrosians.bedlogic.util.ims;


import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.embeddable.Units;
import com.bedrosians.bedlogic.exception.InputParamException;


public class ImsValidator {

	public static void validateQueryParams(MultivaluedMap<String, String> queryParams) {
		validateForNullParams(queryParams);	
	}
	
	public static void validateInsertUpdateParams( MultivaluedMap<String, String> queryParams) {
		validateForNullParams(queryParams);		
	}
	
	public static void validateNewItem(Ims item){
		if(item == null)
		   throw new InputParamException("Item should not be null.");
		validateItemCode(item);
		validateRequiredProperties(item);
		validateFieldLength(item);
		validateNewItemUnit(item);
		if(item.getPrice() != null){
		   validateDates(item.getPrice().getTempdatefrom(), item.getPrice().getTempdatethru());
		}
	}

	public static void validateItemCode(Ims item){
		if(item.getItemcode() == null || item.getItemcode().length() < 1 || item.getItemcode().length() > 18)
		   throw new InputParamException("Item code cannot be empty and should be less than 18 characters"         );
		item.setItemcode(item.getItemcode().toUpperCase());
		//if(!product.getItemcode().matches("^[a-zA-Z0-9-]*$"))
		//   throw new InputParamException("Product code cannot contain any special characters, other than letters, numbers and dashes");
	}
	
	public static void validateRequiredProperties(Ims item){
		if(item.getItemdesc() == null || item.getItemdesc().getItemdesc1() == null || item.getItemdesc().getItemdesc1().length() < 1)
		   throw new InputParamException("Missing Item Description!");
		if(item.getItemcategory() == null || item.getItemcategory().length() < 1)
		   throw new InputParamException("Missing ItemCategory!");
	}
		 
	private static void validateForNullParams(MultivaluedMap<String, String> queryParams) {
		if(queryParams == null || queryParams.isEmpty())
		   throw new InputParamException("Please enter valid query parameters!");	
	}
	
	public static void validateFieldLength(Ims item){
		if(item.getMaterial() != null && item.getMaterial().getMaterialclass() != null && item.getMaterial().getMaterialclass().length() > 5)
		   throw new InputParamException("Matrial class code should not be longer that 5 characters.");
		if(item.getMaterial() != null && item.getMaterial().getMaterialstyle() != null && item.getMaterial().getMaterialstyle().length() > 7 )
		   throw new InputParamException("Matrial style should not be longer that 7 characters.");
		if(item.getItemdesc() != null && item.getItemdesc().getItemdesc1() != null && item.getItemdesc().getItemdesc1().length() > 35)
		   throw new InputParamException("Description should not be longer that 35 characters.");
		if(item.getPurchasers() != null && item.getPurchasers().getPurchaser() != null && item.getPurchasers().getPurchaser().length() > 10)
		   throw new InputParamException("Purchaser should not be longer that 10 characters.");
		if(item.getPurchasers() != null && item.getPurchasers().getPurchaser2() != null && item.getPurchasers().getPurchaser2().length() > 10)
		   throw new InputParamException("Purchaser2 should not be longer that 10 characters.");
		if(item.getIconsystem() != null && item.getIconsystem().length() > 20)
		   throw new InputParamException("Icons should not be longer than 20 charactors.");
		if(item.getPrice() != null && item.getPrice().getPricegroup() != null && item.getPrice().getPricegroup().length() > 2)
		   throw new InputParamException("Pricegroup length should be two charactors or less.");
		if(item.getItemgroupnbr() != null && item.getItemgroupnbr() > 99)
		   throw new InputParamException("Itemgroupnbr length should be two digits or less.");
		//if(product.getVendors().getVendornbr1() != null && (product.getVendors().getVendornbr1() < 1 || String.valueOf(product.getVendors().getVendornbr1()).length() > 6))
		//   throw new InputParamException("Vendor number should not be empty and not longer than 6.");
		//if(product.getNewVendorSystem() != null && !product.getNewVendorSystem().isEmpty()){
		//	for(ProductVendor vendor : product.getNewVendorSystem()){
		//		if((vendor.getItemVendorId() != null) && (vendor.getItemVendorId().getVendorId() < 1 || String.valueOf(vendor.getItemVendorId().getVendorId()).length() > 6))
		//				throw new InputParamException("Vendor number should not be empty and not longer than 6.");
		//	}
		//}			   
		validateCharacter("lottype", item.getLottype()); 
		validateCharacter("printlabel", item.getPrintlabel()); 
		validateCharacter("showonwebsite", item.getShowonwebsite()); 
		validateCharacter("showonalysedwards", item.getShowonalysedwards()); 
		validateCharacter("offshade", item.getOffshade());	
		validateCharacter("directship", item.getDirectship());
		if(item.getTestSpecification() != null && item.getTestSpecification().getChemicalresistance() != null)
			validateCharacter("chemical resistance", item.getTestSpecification().getChemicalresistance());
		if(item.getTestSpecification() != null && item.getTestSpecification().getFrostresistance() != null)
		   validateCharacter("frostresistance", item.getTestSpecification().getFrostresistance());	
		if(item.getTestSpecification() != null && item.getTestSpecification().getWarpage() != null)
		   validateCharacter("warpage", item.getTestSpecification().getWarpage());
		if(item.getTestSpecification() != null && item.getTestSpecification().getRestricted() != null)
		   validateCharacter("restricted", item.getTestSpecification().getWarpage());
		if(item.getTestSpecification() != null && item.getTestSpecification().getWedging() != null)
		   validateCharacter("wedging", item.getTestSpecification().getWedging());
		if(item.getTestSpecification() != null && item.getTestSpecification().getThermalshock() != null)
		   validateCharacter("thermal shock", item.getTestSpecification().getThermalshock());
	}
	
	public static void validateCharacter(String name, Character value) {
		if(value != null && !(value instanceof Character))
		   throw new InputParamException(name + " should be Character type.");
	}
	
	public static void validateCharacter(String name, String value) {
		if(value != null && value.length() > 1)
		   throw new InputParamException(name + " is " + value + ". It should be one character.");
	}	

	public static void validateDates(Date startDate, Date endDate) {
		if(startDate == null && endDate != null)
			throw new InputParamException("Start date is missing.");
		if (startDate != null && endDate != null && startDate.after(endDate))
			throw new InputParamException("End date should not be earlier than start date.");
	}
	
	//Validate new product against the item table CHECK constraints
	public static void validateNewItemUnit(Ims item) {
	    if(item.getUnits() == null || item.getUnits().isDefault() || item.getUnits().getBasewgtperunit() == null) 
	       return;
	
	    validateStandardOrderUnit(item);
	    validateStandardSellUnit(item);
	    Units units = item.getUnits();
	 	if(item.getUnits().getBaseunit() == null || item.getUnits().getBaseunit().trim().length() < 1)
		   item.getUnits().setBaseunit("PCS");
		
		if((units.getBaseunit() != null && units.getBaseunit().length() > 4) || 
		   (units.getUnit1unit() != null && units.getUnit1unit().length() > 4) ||
		   (units.getUnit2unit() != null && units.getUnit2unit().length() > 4) ||
		   (units.getUnit3unit() != null && units.getUnit3unit().length() > 4) ||
		   (units.getUnit4unit() != null && units.getUnit4unit().length() > 4))
			throw new InputParamException("Unit cannot be longer than four characters.");
		//db constraint ims_check 1
		if((units.getUnit1unit() == null || units.getUnit1unit().trim().length() == 0) && (units.getUnit1ratio() != null && units.getUnit1ratio() > 0))
			throw new InputParamException("Unit1 ratio should be 0, since unit1 unit is empty.");
		if((units.getUnit1unit() != null && units.getUnit1unit().length() > 0) && units.getUnit1ratio() <= 0)
			throw new InputParamException("Please provide Unit1 ratio.");
		
		//db constraint ims_check 2
		if((units.getUnit2unit() == null || units.getUnit2unit().trim().length() == 0) && (units.getUnit2ratio() != null && units.getUnit2ratio() > 0))
			throw new InputParamException("Unit2 ratio should be 0, since unit2 unit is empty.");
		if((units.getUnit2unit() != null && units.getUnit2unit().length() > 0) && units.getUnit2ratio() <= 0)
			throw new InputParamException("Please provide Unit2 ratio.");
	
		//db constraint ims_check 3
		if((units.getUnit3unit() == null || units.getUnit3unit().trim().length() == 0) && (units.getUnit3ratio()  != null && units.getUnit3ratio() > 0))
			throw new InputParamException("Unit3 ratio should be 0, since unit3 unit is empty.");
		if((units.getUnit3unit() != null && units.getUnit3unit().trim().length() > 0) && units.getUnit3ratio() <= 0)
			throw new InputParamException("Please provide Unit3 ratio.");
				
		//db constraint ims_check 4
		if((units.getUnit4unit() == null || units.getUnit4unit().trim().length() == 0) && (units.getUnit4ratio() != null && units.getUnit4ratio() > 0))
			throw new InputParamException("Unit4 ratio should be 0, since unit4 unit is empty.");
		if((units.getUnit4unit() != null && units.getUnit4unit().trim().length() > 0) && units.getUnit4ratio() <= 0)
			throw new InputParamException("Please provide Unit4 ratio.");		   
		
	 	//db constraint ims_check 5
		  /*CONSTRAINT ims_check5 CHECK (((((((((((((((((((
		        (vendorpriceunit IS NOT NULL) AND 
		        ((((
		           ((vendorpriceunit <= '    '::bpchar) OR 
		        	(vendorpriceunit = baseunit)) OR (vendorpriceunit = unit1unit)) OR (vendorpriceunit = unit2unit)) OR (vendorpriceunit = unit3unit)) OR (vendorpriceunit = unit4unit))) AND 
		        	(vendorlistprice IS NOT NULL)) AND 
		        	(vendordiscpct1 IS NOT NULL)) AND 
		        	(vendordiscpct2 IS NOT NULL)) AND 
		        	(vendordiscpct3 IS NOT NULL)) AND 
		        	(vendorroundaccuracy IS NOT NULL)) AND 
		        	(vendorroundaccuracy >= (0)::numeric)) AND 
		        	(vendorroundaccuracy <= (4)::numeric)) AND 
		        	(vendornetprice IS NOT NULL)) AND 
		        	(vendormarkuppct IS NOT NULL)) AND 
		        	(vendorfreightratecwt IS NOT NULL)) AND 
		        	(vendorlandedbasecost IS NOT NULL)) AND 
		        	(sellpricemarginpct IS NOT NULL)) AND 
		        	(sellpricemarginpct <> (100)::numeric)) AND 
		        	(sellpriceroundaccuracy IS NOT NULL)) AND 
		        	(sellpriceroundaccuracy >= (0)::numeric)) AND 
		        	(sellpriceroundaccuracy <= (4)::numeric)) AND 
		        	(listpricemarginpct IS NOT NULL)) AND 
		        	(listpricemarginpct <> (100)::numeric))
		  */
         if(item.getVendors().getVendorpriceunit() == null || item.getVendors().getVendorpriceunit().length() == 0 || item.getVendors().getVendorpriceunit().length() > 4){
        	 throw new InputParamException("According to item table requirments, Vendor price unit shoud not be null and its length shoud be 4 or less characters.");
         }
         if(!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getBaseunit()) && 
        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit1unit()) &&
        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit2unit()) &&
        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit3unit()) &&
        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit4unit())) {
            throw new InputParamException("According to item table requirments, vendor price unit should match one of the unit's packaging unit."); 	 
         }
         if(item.getVendors().getVendorlistprice() == null){
           	throw new InputParamException("According to item table requirments, Vendor list price should not be null.");  
         }
         if(item.getVendors().getVendordiscpct() == null || item.getVendors().getVendordiscpct2() == null || item.getVendors().getVendordiscpct3() == null){
        	 throw new InputParamException("According to item table requirments, Vendor discount percent (1--3) should not be null.");  
         }
         if(item.getVendors().getVendorroundaccuracy() == null || item.getVendors().getVendorroundaccuracy() < 0 || item.getVendors().getVendorroundaccuracy() > 100){
        	 throw new InputParamException("According to item table requirments, Vendor round accuracy should be 0 to 100.");   
         }
         //if(item.getVendors().getVendornetprice() == null){
         //	 throw new InputParamException("According to item table requirments, Vendor net price should not be null.");  
         // }
         if(item.getVendors().getVendormarkuppct() == null){
        	 throw new InputParamException("According to item table requirments, Vendor markup percent should not be null.");  
         }
         if(item.getVendors().getVendorfreightratecwt() == null){
        	 throw new InputParamException("According to item table requirments, Vendor freight rate cwt should not be null.");   
         }
         if(item.getVendors().getVendorlandedbasecost() == null){
        	 throw new InputParamException("According to item table requirments, Vendor landed base cost should not be null.");
         }
         if(item.getPrice().getSellpricemarginpct() == null || item.getPrice().getSellpricemarginpct().longValue() < 0 || item.getPrice().getSellpricemarginpct().longValue() > 100){
        	throw new InputParamException("According to item table requirments, Sell price margine percent should not be null and shoud be 0 to 100..");
         }
         if(item.getPrice().getListpricemarginpct() == null || item.getPrice().getListpricemarginpct().longValue() < 0 || item.getPrice().getListpricemarginpct().longValue() > 100){
        	 throw new InputParamException("According to item table requirments, List price margine percent should not be null and shoud be 0 to 100.");
         }
         if(item.getPrice().getListpricemarginpct().longValue() >= 100) {
        	 throw new InputParamException("According to item table requirments, List price margine percent should be 0 to 100.");
         }		
         if((item.getPrice().getSellpriceroundaccuracy() == null) || item.getPrice().getSellpriceroundaccuracy() < 0 || item.getPrice().getSellpriceroundaccuracy() > 100){
        	throw new InputParamException("According to item table requirments, List price round accuracy should be 0 to 100.");
         }
          
		//item check 6
		if (item.getUnits().getBaseunit() != null && validateUnitName(item.getUnits().getBaseunit()) ||
	       (item.getUnits().getUnit1unit() != null && validateUnitName(item.getUnits().getUnit1unit())) ||
	       (item.getUnits().getUnit2unit() != null && validateUnitName(item.getUnits().getUnit2unit())) ||
	       (item.getUnits().getUnit3unit() != null && validateUnitName(item.getUnits().getUnit3unit())) ||
	       (item.getUnits().getUnit4unit() != null && validateUnitName(item.getUnits().getUnit4unit())))
	    	 throw new InputParamException("Unit or unit ratio is wrong.");
	}
	
	public static void validateStandardOrderUnit(Ims item){
	    int count = 0;
	    if(item.getUnits() != null){
		   if(item.getUnits().getBaseisstdord() != null && (item.getUnits().getBaseisstdord() == 'Y' || item.getUnits().getBaseisstdord() == 'y')) 
			   count++;
		   if(item.getUnits().getUnit1isstdord() != null && (item.getUnits().getUnit1isstdord() == 'Y' || item.getUnits().getUnit1isstdord() == 'y')) 
			   count++;
		   if(item.getUnits().getUnit2isstdord() != null && (item.getUnits().getUnit2isstdord() == 'Y' || item.getUnits().getUnit2isstdord() == 'y')) 
			   count++;
		   if(item.getUnits().getUnit3isstdord() != null && (item.getUnits().getUnit3isstdord() == 'Y' || item.getUnits().getUnit3isstdord() == 'y')) 
			   count++;
		   if(item.getUnits().getUnit4isstdord() != null && (item.getUnits().getUnit4isstdord() == 'Y' || item.getUnits().getUnit4isstdord() == 'y')) 
			   count++;
		   if(count < 1)
		      throw new InputParamException("ERROR: Missing Std Order Unit!.");	
		   if(count > 1)
			  throw new InputParamException("ERROR: Multiple Std Order Unit!!.");	
	    }   
	}    	
	
	public static void validateStandardSellUnit(Ims item) {
	    int count = 0;
	    if(item.getUnits() != null){
		   if(item.getUnits().getBaseisstdsell() != null && (item.getUnits().getBaseisstdsell() == 'Y' || item.getUnits().getBaseisstdsell() == 'y')) 
			  count++;
		   if(item.getUnits().getUnit1isstdsell() != null && (item.getUnits().getUnit1isstdsell() == 'Y' || item.getUnits().getUnit1isstdsell() == 'y')) 
			  count++;
		   if(item.getUnits().getUnit2isstdsell() != null && (item.getUnits().getUnit2isstdsell() == 'Y' || item.getUnits().getUnit2isstdsell() == 'y'))
			  count++;
		   if(item.getUnits().getUnit3isstdsell() != null && (item.getUnits().getUnit3isstdsell() == 'Y' || item.getUnits().getUnit3isstdsell() == 'y'))
			  count++;
		   if(item.getUnits().getUnit4isstdsell() != null && (item.getUnits().getUnit4isstdsell() == 'Y' || item.getUnits().getUnit4isstdsell() == 'y')) 
			   count++;
		   if(count < 1)
			 throw new InputParamException("ERROR: Missing Std Sell Unit!.");	
		   if(count > 1)
			 throw new InputParamException("ERROR: Multiple Std Sell Unit!!.");
	    }   
	}    	
		
	private static boolean validateUnitName(String originalString){
		String[] illegalValues = new String[]{"EA", "EACH", "PC", "LF", "SF", "CT", "CTM", "CTNH", "PK"};
		for(String s : illegalValues){
			if(originalString.equalsIgnoreCase(s))
			   return true;	
		}
		return false;
	}
	
	public boolean validateUnitUPC(Ims item){
		return true;
	}	
	
	public boolean validateInventoryItemCode(String inventoryItemCode){
		return true;
	}
}
