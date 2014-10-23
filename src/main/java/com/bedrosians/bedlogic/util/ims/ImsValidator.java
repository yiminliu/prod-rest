package com.bedrosians.bedlogic.util.ims;


import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;


public class ImsValidator {

	public static void validateQueryParams(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		validateForNullParams(queryParams);	
	}
	
	public static void validateInsertUpdateParams( MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		validateForNullParams(queryParams);		
	}
	
	public static void validateNewItem(Ims ims) throws BedDAOBadParamException{
		if(ims == null)
		   throw new BedDAOBadParamException("Product should not be null.");
		validateItemCode(ims);
		validateRequiredProperties(ims);
		validateFieldLength(ims);
		validateNewItemUnit(ims);
		if(ims.getPrice() != null){
		   validateDates(ims.getPrice().getTempdatefrom(), ims.getPrice().getTempdatethru());
		}
	}

	public static void validateItemCode(Ims ims) throws BedDAOBadParamException{
		if(ims.getItemcode() == null || ims.getItemcode().length() < 1 || ims.getItemcode().length() > 18)
		   throw new BedDAOBadParamException("Product code cannot be empty and should be less than 18 characters");
		//if(!product.getItemcode().matches("^[a-zA-Z0-9-]*$"))
		//   throw new BedDAOBadParamException("Product code cannot contain any special characters, other than letters, numbers and dashes");
	}
	
	public static void validateRequiredProperties(Ims ims) throws BedDAOBadParamException{
		if(ims.getItemdesc() == null || ims.getItemdesc().getItemdesc1() == null || ims.getItemdesc().getItemdesc1().length() < 1)
		   throw new BedDAOBadParamException("Missing Product Description!");
		if(ims.getItemcategory() == null || ims.getItemcategory().length() < 1)
		   throw new BedDAOBadParamException("Missing Product Category!");
	}
		 
	private static void validateForNullParams(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		if(queryParams == null || queryParams.isEmpty())
		   throw new BedDAOBadParamException("Please enter valid query parameters!");	
	}
	
	public static void validateFieldLength(Ims ims) throws BedDAOBadParamException{
		if(ims.getItemdesc() != null && ims.getItemdesc().getItemdesc1() != null && ims.getItemdesc().getItemdesc1().length() > 35)
		   throw new BedDAOBadParamException("Description should not be longer that 35 characters.");
		if(ims.getPurchasers() != null && ims.getPurchasers().getPurchaser() != null && ims.getPurchasers().getPurchaser().length() > 10)
		   throw new BedDAOBadParamException("Purchaser should not be longer that 10 characters.");
		if(ims.getPurchasers() != null && ims.getPurchasers().getPurchaser2() != null && ims.getPurchasers().getPurchaser2().length() > 10)
		   throw new BedDAOBadParamException("Purchaser2 should not be longer that 10 characters.");
		if(ims.getIconsystem() != null && ims.getIconsystem().length() > 20)
		   throw new BedDAOBadParamException("Icons should not be longer than 20 charactors.");
		if(ims.getPrice() != null && ims.getPrice().getPricegroup() != null && ims.getPrice().getPricegroup().length() > 2)
		   throw new BedDAOBadParamException("Pricegroup length should be two charactors or less.");
		if(ims.getItemgroupnbr() != null && ims.getItemgroupnbr() > 99)
		   throw new BedDAOBadParamException("Itemgroupnbr length should be two digits or less.");
		//if(product.getVendors().getVendornbr1() != null && (product.getVendors().getVendornbr1() < 1 || String.valueOf(product.getVendors().getVendornbr1()).length() > 6))
		//   throw new BedDAOBadParamException("Vendor number should not be empty and not longer than 6.");
		//if(product.getNewVendorSystem() != null && !product.getNewVendorSystem().isEmpty()){
		//	for(ProductVendor vendor : product.getNewVendorSystem()){
		//		if((vendor.getItemVendorId() != null) && (vendor.getItemVendorId().getVendorId() < 1 || String.valueOf(vendor.getItemVendorId().getVendorId()).length() > 6))
		//				throw new BedDAOBadParamException("Vendor number should not be empty and not longer than 6.");
		//	}
		//}			   
		validateCharacter("lottype", ims.getLottype()); 
		validateCharacter("printlabel", ims.getPrintlabel()); 
		validateCharacter("showonwebsite", ims.getShowonwebsite()); 
		validateCharacter("showonalysedwards", ims.getShowonalysedwards()); 
		validateCharacter("offshade", ims.getOffshade());	
		validateCharacter("directship", ims.getDirectship());
		if(ims.getTestSpecification() != null && ims.getTestSpecification().getChemicalresistance() != null)
			validateCharacter("chemical resistance", ims.getTestSpecification().getChemicalresistance());
		if(ims.getTestSpecification() != null && ims.getTestSpecification().getFrostresistance() != null)
		   validateCharacter("frostresistance", ims.getTestSpecification().getFrostresistance());	
		if(ims.getTestSpecification() != null && ims.getTestSpecification().getWarpage() != null)
		   validateCharacter("warpage", ims.getTestSpecification().getWarpage());
		if(ims.getTestSpecification() != null && ims.getTestSpecification().getRestricted() != null)
		   validateCharacter("restricted", ims.getTestSpecification().getWarpage());
		if(ims.getTestSpecification() != null && ims.getTestSpecification().getWedging() != null)
		   validateCharacter("wedging", ims.getTestSpecification().getWedging());
		if(ims.getTestSpecification() != null && ims.getTestSpecification().getThermalshock() != null)
		   validateCharacter("thermal shock", ims.getTestSpecification().getThermalshock());
	}
	
	public static void validateCharacter(String name, Character value) throws BedDAOBadParamException {
		if(value != null && !(value instanceof Character))
		   throw new BedDAOBadParamException(name + " should be Charactor type.");
	}
	
	public static void validateCharacter(String name, String value) throws BedDAOBadParamException {
		if(value != null && value.length() > 1)
		   throw new BedDAOBadParamException(name + " is " + value + ". It should be one charactor.");
	}	

	public static void validateDates(Date startDate, Date endDate) throws BedDAOBadParamException{
		if (startDate != null && endDate != null && startDate.after(endDate))
			throw new BedDAOBadParamException("End date should not be earlier than start date.");
	}
	
	//Validate new product against the ims table CHECK constraints
	public static void validateNewItemUnit(Ims ims) throws BedDAOBadParamException{
	    if(ims.getUnits() == null || ims.getUnits().isDefault()) 
	       return;
	
	    validateStandardOrderUnit(ims);
	    validateStandardOrderUnit(ims);
	        
		if(ims.getUnits().getBaseunit() == null || ims.getUnits().getBaseunit().trim().length() < 1)
		  // throw new BedDAOBadParamException("BaseUnit cannot be null.");
			ims.getUnits().setBaseunit("PCS");
		
		if((ims.getUnits().getBaseunit() != null && ims.getUnits().getBaseunit().length() > 4) || 
		   (ims.getUnits().getUnit1unit() != null && ims.getUnits().getUnit1unit().length() > 4) ||
		   (ims.getUnits().getUnit2unit() != null && ims.getUnits().getUnit2unit().length() > 4) ||
		   (ims.getUnits().getUnit3unit() != null && ims.getUnits().getUnit3unit().length() > 4) ||
		   (ims.getUnits().getUnit4unit() != null && ims.getUnits().getUnit4unit().length() > 4))
			throw new BedDAOBadParamException("Unit cannot be longer than four characters.");
		//db constraint ims_check 1
		if((ims.getUnits().getUnit1unit() == null || ims.getUnits().getUnit1unit().trim().length() == 0) && (ims.getUnits().getUnit1ratio() != null && ims.getUnits().getUnit1ratio() > 0))
			throw new BedDAOBadParamException("Unit1 ratio should be 0, since unit1 unit is empty.");
		if((ims.getUnits().getUnit1unit() != null && ims.getUnits().getUnit1unit().length() > 0) && ims.getUnits().getUnit1ratio() <= 0)
			throw new BedDAOBadParamException("Please privide Unit1 ratio.");
		
		//db constraint ims_check 2
		if((ims.getUnits().getUnit2unit() == null || ims.getUnits().getUnit2unit().trim().length() == 0) && (ims.getUnits().getUnit2ratio() != null && ims.getUnits().getUnit2ratio() > 0))
			throw new BedDAOBadParamException("Unit2 ratio should be 0, since unit2 unit is empty.");
		if((ims.getUnits().getUnit2unit() != null && ims.getUnits().getUnit2unit().length() > 0) && ims.getUnits().getUnit2ratio() <= 0)
			throw new BedDAOBadParamException("Please privide Unit2 ratio.");
	
		//db constraint ims_check 3
		if((ims.getUnits().getUnit3unit() == null || ims.getUnits().getUnit3unit().trim().length() == 0) && (ims.getUnits().getUnit3ratio()  != null && ims.getUnits().getUnit3ratio() > 0))
			throw new BedDAOBadParamException("Unit3 ratio should be 0, since unit3 unit is empty.");
		if((ims.getUnits().getUnit3unit() != null && ims.getUnits().getUnit3unit().trim().length() > 0) && ims.getUnits().getUnit3ratio() <= 0)
			throw new BedDAOBadParamException("Please privide Unit3 ratio.");
				
		//db constraint ims_check 4
		if((ims.getUnits().getUnit4unit() == null || ims.getUnits().getUnit4unit().trim().length() == 0) && (ims.getUnits().getUnit4ratio() != null && ims.getUnits().getUnit4ratio() > 0))
			throw new BedDAOBadParamException("Unit4 ratio should be 0, since unit4 unit is empty.");
		if((ims.getUnits().getUnit4unit() != null && ims.getUnits().getUnit4unit().trim().length() > 0) && ims.getUnits().getUnit4ratio() <= 0)
			throw new BedDAOBadParamException("Please privide Unit4 ratio.");		   
		
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
         if(ims.getVendors().getVendorpriceunit() == null || ims.getVendors().getVendorpriceunit().length() == 0 || ims.getVendors().getVendorpriceunit().length() > 4){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor price unit shoud not be null and its length shoud be 4 or less characters.");
         }
         if(!ims.getVendors().getVendorpriceunit().equalsIgnoreCase(ims.getUnits().getBaseunit()) && 
        	!ims.getVendors().getVendorpriceunit().equalsIgnoreCase(ims.getUnits().getUnit1unit()) &&
        	!ims.getVendors().getVendorpriceunit().equalsIgnoreCase(ims.getUnits().getUnit2unit()) &&
        	!ims.getVendors().getVendorpriceunit().equalsIgnoreCase(ims.getUnits().getUnit3unit()) &&
        	!ims.getVendors().getVendorpriceunit().equalsIgnoreCase(ims.getUnits().getUnit4unit())) {
        	 System.out.println("Vendorpriceunit = " + ims.getVendors().getVendorpriceunit());
        	 System.out.println("Base unit = " + ims.getUnits().getBaseunit());
        	 System.out.println("Unit 1 unit = " + ims.getUnits().getUnit1unit());
        	 System.out.println("Unit 2 unit = " + ims.getUnits().getUnit2unit());
        	 System.out.println("Unit 3 unit = " + ims.getUnits().getUnit3unit());
        	 System.out.println("Unit 4 unit = " + ims.getUnits().getUnit4unit());
           throw new BedDAOBadParamException("According to ims table requirments, vendor price unit should match one of the unit's packaging unit."); 	 
         }
         if(ims.getVendors().getVendorlistprice() == null){
           	throw new BedDAOBadParamException("According to ims table requirments, Vendor list price should not be null.");  
         }
         if(ims.getVendors().getVendordiscpct() == null || ims.getVendors().getVendordiscpct2() == null || ims.getVendors().getVendordiscpct3() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor discount percent (1--3) should not be null.");  
         }
         if(ims.getVendors().getVendorroundaccuracy() == null || ims.getVendors().getVendorroundaccuracy() < 0 || ims.getVendors().getVendorroundaccuracy() > 100){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor round accuracy should be 0 to 100.");   
         }
         if(ims.getVendors().getVendornetprice() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor net price should not be null.");  
         }
         if(ims.getVendors().getVendormarkuppct() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor markup percent should not be null.");  
         }
         if(ims.getVendors().getVendorfreightratecwt() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor freight rate cwt should not be null.");   
         }
         if(ims.getVendors().getVendorlandedbasecost() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor landed base cost should not be null.");
         }
         if(ims.getPrice().getSellpricemarginpct() == null || ims.getPrice().getSellpricemarginpct().longValue() < 0 || ims.getPrice().getSellpricemarginpct().longValue() > 100){
        	throw new BedDAOBadParamException("According to ims table requirments, Sell price margine percent should not be null and shoud be 0 to 100..");
         }
         if(ims.getPrice().getListpricemarginpct() == null || ims.getPrice().getListpricemarginpct().longValue() < 0 || ims.getPrice().getListpricemarginpct().longValue() > 100){
        	 throw new BedDAOBadParamException("According to ims table requirments, List price margine percent should not be null and shoud be 0 to 100.");
         }
         if(ims.getPrice().getListpricemarginpct().longValue() >= 100) {
        	 throw new BedDAOBadParamException("According to ims table requirments, List price margine percent should be 0 to 100.");
         }		
         if((ims.getPrice().getSellpriceroundaccuracy() == null) || ims.getPrice().getSellpriceroundaccuracy() < 0 || ims.getPrice().getSellpriceroundaccuracy() > 100){
        	throw new BedDAOBadParamException("According to ims table requirments, List price round accuracy should be 0 to 100.");
         }
          
		//ims check 6
		if (ims.getUnits().getBaseunit() != null && validateUnitName(ims.getUnits().getBaseunit()) ||
	       (ims.getUnits().getUnit1unit() != null && validateUnitName(ims.getUnits().getUnit1unit())) ||
	       (ims.getUnits().getUnit2unit() != null && validateUnitName(ims.getUnits().getUnit2unit())) ||
	       (ims.getUnits().getUnit3unit() != null && validateUnitName(ims.getUnits().getUnit3unit())) ||
	       (ims.getUnits().getUnit4unit() != null && validateUnitName(ims.getUnits().getUnit4unit())))
	    	 throw new BedDAOBadParamException("Unit or unit ratio is wrong.");
	}
	
	public static void validateStandardOrderUnit(Ims ims) throws BedDAOBadParamException{
	    int count = 0;
		if(ims.getUnits().getBaseisstdord() == 'Y' || ims.getUnits().getBaseisstdord() == 'y') count++;
		if(ims.getUnits().getUnit1isstdord() == 'Y' || ims.getUnits().getUnit1isstdord() == 'y') count++;
		if(ims.getUnits().getUnit2isstdord() == 'Y' || ims.getUnits().getUnit2isstdord() == 'y') count++;
		if(ims.getUnits().getUnit3isstdord() == 'Y' || ims.getUnits().getUnit3isstdord() == 'y') count++;
		if(ims.getUnits().getUnit4isstdord() == 'Y' || ims.getUnits().getUnit4isstdord() == 'y') count++;
		if(count < 1)
			throw new BedDAOBadParamException("ERROR: Missing Std Order Unit!.");	
		if(count > 1)
			throw new BedDAOBadParamException("ERROR: Multiple Std Sell Unit!!.");	
	}    	
	
	public static void validateStandardSellUnit(Ims ims) throws BedDAOBadParamException{
	    int count = 0;
		if(ims.getUnits().getBaseisstdsell() == 'Y' || ims.getUnits().getBaseisstdsell() == 'y') count++;
		if(ims.getUnits().getUnit1isstdsell() == 'Y' || ims.getUnits().getUnit1isstdsell() == 'y') count++;
		if(ims.getUnits().getUnit2isstdsell() == 'Y' || ims.getUnits().getUnit2isstdsell() == 'y') count++;
		if(ims.getUnits().getUnit3isstdsell() == 'Y' || ims.getUnits().getUnit3isstdsell() == 'y') count++;
		if(ims.getUnits().getUnit4isstdsell() == 'Y' || ims.getUnits().getUnit4isstdsell() == 'y') count++;
		if(count < 1)
			throw new BedDAOBadParamException("ERROR: Missing Std Order Unit!.");	
		if(count > 1)
			throw new BedDAOBadParamException("ERROR: Multiple Std Sell Unit!!.");		
	}    	
		
	private static boolean validateUnitName(String originalString){
		String[] illegalValues = new String[]{"EA", "EACH", "PC", "LF", "SF", "CT", "CTM", "CTNH", "PK"};
		for(String s : illegalValues){
			if(originalString.equalsIgnoreCase(s))
			   return true;	
		}
		return false;
	}
	
	public boolean validateUnitUPC(Ims ims){
		return true;
	}	
	
	public boolean validateInventoryItemCode(String inventoryItemCode){
		return true;
	}
}
