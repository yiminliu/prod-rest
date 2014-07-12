package com.bedrosians.bedlogic.util.product;


import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.product.ProductVendor;
import com.bedrosians.bedlogic.domain.product.Product;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;


public class ImsValidator {

	public static void validateQueryParams(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		validateForNullParams(queryParams);	
	}
	
	public static void validateInsertUpdateParams( MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		validateForNullParams(queryParams);		
	}
	
	public static void validateNewItem(Product product) throws BedDAOBadParamException{
		if(product == null)
		   throw new BedDAOBadParamException("Product should not be null.");
		validateItemCode(product);
		validateRequiredProperties(product);
		validateFieldLength(product);
		validateNewItemUnit(product);
		if(product.getPrice() != null){
		   validateDates(product.getPrice().getTempdatefrom(), product.getPrice().getTempdatethru());
		}
	}

	public static void validateItemCode(Product product) throws BedDAOBadParamException{
		if(product.getItemcode() == null || product.getItemcode().length() < 1 || product.getItemcode().length() > 18)
		   throw new BedDAOBadParamException("Product code cannot be empty and should be less than 18 characters");
		//if(!product.getItemcode().matches("^[a-zA-Z0-9-]*$"))
		//   throw new BedDAOBadParamException("Product code cannot contain any special characters, other than letters, numbers and dashes");
	}
	
	public static void validateRequiredProperties(Product product) throws BedDAOBadParamException{
		if(product.getItemdesc() == null || product.getItemdesc().getItemdesc1() == null || product.getItemdesc().getItemdesc1().length() < 1)
		   throw new BedDAOBadParamException("Missing Product Description!");
		if(product.getItemcategory() == null || product.getItemcategory().length() < 1)
		   throw new BedDAOBadParamException("Missing Product Category!");
	}
		 
	private static void validateForNullParams(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		if(queryParams == null || queryParams.isEmpty())
		   throw new BedDAOBadParamException("Please enter valid query parameters!");	
	}
	
	public static void validateFieldLength(Product product) throws BedDAOBadParamException{
		if(product.getItemdesc() != null && product.getItemdesc().getItemdesc1() != null && product.getItemdesc().getItemdesc1().length() > 35)
		   throw new BedDAOBadParamException("Description should not be longer that 35 characters.");
		if(product.getPurchasers() != null && product.getPurchasers().getPurchaser() != null && product.getPurchasers().getPurchaser().length() > 10)
		   throw new BedDAOBadParamException("Purchaser should not be longer that 10 characters.");
		if(product.getPurchasers() != null && product.getPurchasers().getPurchaser2() != null && product.getPurchasers().getPurchaser2().length() > 10)
		   throw new BedDAOBadParamException("Purchaser2 should not be longer that 10 characters.");
		if(product.getIconsystem() != null && product.getIconsystem().length() > 20)
		   throw new BedDAOBadParamException("Icons should not be longer than 20 charactors.");
		if(product.getPrice() != null && product.getPrice().getPricegroup() != null && product.getPrice().getPricegroup().length() > 2)
		   throw new BedDAOBadParamException("Pricegroup length should be two charactors or less.");
		if(product.getItemgroupnumber() != null && product.getItemgroupnumber() > 99)
		   throw new BedDAOBadParamException("Itemgroupnbr length should be two digits or less.");
		//if(product.getVendors().getVendornbr1() != null && (product.getVendors().getVendornbr1() < 1 || String.valueOf(product.getVendors().getVendornbr1()).length() > 6))
		//   throw new BedDAOBadParamException("Vendor number should not be empty and not longer than 6.");
		//if(product.getNewVendorSystem() != null && !product.getNewVendorSystem().isEmpty()){
		//	for(ProductVendor vendor : product.getNewVendorSystem()){
		//		if((vendor.getItemVendorId() != null) && (vendor.getItemVendorId().getVendorId() < 1 || String.valueOf(vendor.getItemVendorId().getVendorId()).length() > 6))
		//				throw new BedDAOBadParamException("Vendor number should not be empty and not longer than 6.");
		//	}
		//}			   
		validateCharacter("lottype", product.getLottype()); 
		validateCharacter("printlabel", product.getPrintlabel()); 
		validateCharacter("showonwebsite", product.getShowonwebsite()); 
		validateCharacter("showonalysedwards", product.getShowonalysedwards()); 
		validateCharacter("offshade", product.getOffshade());	
		validateCharacter("directship", product.getDirectship());
		if(product.getTestSpecification() != null && product.getTestSpecification().getChemicalresistance() != null)
			validateCharacter("chemical resistance", product.getTestSpecification().getChemicalresistance());
		if(product.getTestSpecification() != null && product.getTestSpecification().getFrostresistance() != null)
		   validateCharacter("frostresistance", product.getTestSpecification().getFrostresistance());	
		if(product.getTestSpecification() != null && product.getTestSpecification().getWarpage() != null)
		   validateCharacter("warpage", product.getTestSpecification().getWarpage());
		if(product.getTestSpecification() != null && product.getTestSpecification().getRestricted() != null)
		   validateCharacter("restricted", product.getTestSpecification().getWarpage());
		if(product.getTestSpecification() != null && product.getTestSpecification().getWedging() != null)
		   validateCharacter("wedging", product.getTestSpecification().getWedging());
		if(product.getTestSpecification() != null && product.getTestSpecification().getThermalshock() != null)
		   validateCharacter("thermal shock", product.getTestSpecification().getThermalshock());
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
	public static void validateNewItemUnit(Product product) throws BedDAOBadParamException{
	    if(product.getUnits() == null || product.getUnits().isDefault()) 
	       return;
	
	    validateStandardOrderUnit(product);
	    validateStandardOrderUnit(product);
	        
		if(product.getUnits().getBaseunit() == null || product.getUnits().getBaseunit().trim().length() < 1)
		  // throw new BedDAOBadParamException("BaseUnit cannot be null.");
			product.getUnits().setBaseunit("PCS");
		
		if((product.getUnits().getBaseunit() != null && product.getUnits().getBaseunit().length() > 4) || 
		   (product.getUnits().getUnit1unit() != null && product.getUnits().getUnit1unit().length() > 4) ||
		   (product.getUnits().getUnit2unit() != null && product.getUnits().getUnit2unit().length() > 4) ||
		   (product.getUnits().getUnit3unit() != null && product.getUnits().getUnit3unit().length() > 4) ||
		   (product.getUnits().getUnit4unit() != null && product.getUnits().getUnit4unit().length() > 4))
			throw new BedDAOBadParamException("Unit cannot be longer than four characters.");
		//db constraint ims_check 1
		if((product.getUnits().getUnit1unit() == null || product.getUnits().getUnit1unit().trim().length() == 0) && (product.getUnits().getUnit1ratio() != null && product.getUnits().getUnit1ratio() > 0))
			throw new BedDAOBadParamException("Unit1 ratio should be 0, since unit1 unit is empty.");
		if((product.getUnits().getUnit1unit() != null && product.getUnits().getUnit1unit().length() > 0) && product.getUnits().getUnit1ratio() <= 0)
			throw new BedDAOBadParamException("Please privide Unit1 ratio.");
		
		//db constraint ims_check 2
		if((product.getUnits().getUnit2unit() == null || product.getUnits().getUnit2unit().trim().length() == 0) && (product.getUnits().getUnit2ratio() != null && product.getUnits().getUnit2ratio() > 0))
			throw new BedDAOBadParamException("Unit2 ratio should be 0, since unit2 unit is empty.");
		if((product.getUnits().getUnit2unit() != null && product.getUnits().getUnit2unit().length() > 0) && product.getUnits().getUnit2ratio() <= 0)
			throw new BedDAOBadParamException("Please privide Unit2 ratio.");
	
		//db constraint ims_check 3
		if((product.getUnits().getUnit3unit() == null || product.getUnits().getUnit3unit().trim().length() == 0) && (product.getUnits().getUnit3ratio()  != null && product.getUnits().getUnit3ratio() > 0))
			throw new BedDAOBadParamException("Unit3 ratio should be 0, since unit3 unit is empty.");
		if((product.getUnits().getUnit3unit() != null && product.getUnits().getUnit3unit().trim().length() > 0) && product.getUnits().getUnit3ratio() <= 0)
			throw new BedDAOBadParamException("Please privide Unit3 ratio.");
				
		//db constraint ims_check 4
		if((product.getUnits().getUnit4unit() == null || product.getUnits().getUnit4unit().trim().length() == 0) && (product.getUnits().getUnit4ratio() != null && product.getUnits().getUnit4ratio() > 0))
			throw new BedDAOBadParamException("Unit4 ratio should be 0, since unit4 unit is empty.");
		if((product.getUnits().getUnit4unit() != null && product.getUnits().getUnit4unit().trim().length() > 0) && product.getUnits().getUnit4ratio() <= 0)
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
         if(product.getVendors().getVendorpriceunit() == null || product.getVendors().getVendorpriceunit().length() == 0 || product.getVendors().getVendorpriceunit().length() > 4){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor price unit shoud not be null and its length shoud be 4 or less characters.");
         }
         if(!product.getVendors().getVendorpriceunit().equalsIgnoreCase(product.getUnits().getBaseunit()) && 
        	!product.getVendors().getVendorpriceunit().equalsIgnoreCase(product.getUnits().getUnit1unit()) &&
        	!product.getVendors().getVendorpriceunit().equalsIgnoreCase(product.getUnits().getUnit2unit()) &&
        	!product.getVendors().getVendorpriceunit().equalsIgnoreCase(product.getUnits().getUnit3unit()) &&
        	!product.getVendors().getVendorpriceunit().equalsIgnoreCase(product.getUnits().getUnit4unit())) {
        	 System.out.println("Vendorpriceunit = " + product.getVendors().getVendorpriceunit());
        	 System.out.println("Base unit = " + product.getUnits().getBaseunit());
        	 System.out.println("Unit 1 unit = " + product.getUnits().getUnit1unit());
        	 System.out.println("Unit 2 unit = " + product.getUnits().getUnit2unit());
        	 System.out.println("Unit 3 unit = " + product.getUnits().getUnit3unit());
        	 System.out.println("Unit 4 unit = " + product.getUnits().getUnit4unit());
           throw new BedDAOBadParamException("According to ims table requirments, vendor price unit should match one of the unit's packaging unit."); 	 
         }
         if(product.getVendors().getVendorlistprice() == null){
           	throw new BedDAOBadParamException("According to ims table requirments, Vendor list price should not be null.");  
         }
         if(product.getVendors().getVendordiscpct() == null || product.getVendors().getVendordiscpct2() == null || product.getVendors().getVendordiscpct3() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor discount percent (1--3) should not be null.");  
         }
         if(product.getVendors().getVendorroundaccuracy() == null || product.getVendors().getVendorroundaccuracy() < 0 || product.getVendors().getVendorroundaccuracy() > 100){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor round accuracy should be 0 to 100.");   
         }
         if(product.getVendors().getVendornetprice() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor net price should not be null.");  
         }
         if(product.getVendors().getVendormarkuppct() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor markup percent should not be null.");  
         }
         if(product.getVendors().getVendorfreightratecwt() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor freight rate cwt should not be null.");   
         }
         if(product.getVendors().getVendorlandedbasecost() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor landed base cost should not be null.");
         }
         if(product.getPrice().getSellpricemarginpct() == null || product.getPrice().getSellpricemarginpct().longValue() < 0 || product.getPrice().getSellpricemarginpct().longValue() > 100){
        	throw new BedDAOBadParamException("According to ims table requirments, Sell price margine percent should not be null and shoud be 0 to 100..");
         }
         if(product.getPrice().getListpricemarginpct() == null || product.getPrice().getListpricemarginpct().longValue() < 0 || product.getPrice().getListpricemarginpct().longValue() > 100){
        	 throw new BedDAOBadParamException("According to ims table requirments, List price margine percent should not be null and shoud be 0 to 100.");
         }
         if(product.getPrice().getListpricemarginpct().longValue() >= 100) {
        	 throw new BedDAOBadParamException("According to ims table requirments, List price margine percent should be 0 to 100.");
         }		
         if((product.getPrice().getSellpriceroundaccuracy() == null) || product.getPrice().getSellpriceroundaccuracy() < 0 || product.getPrice().getSellpriceroundaccuracy() > 100){
        	throw new BedDAOBadParamException("According to ims table requirments, List price round accuracy should be 0 to 100.");
         }
          
		//ims check 6
		if (product.getUnits().getBaseunit() != null && validateUnitName(product.getUnits().getBaseunit()) ||
	       (product.getUnits().getUnit1unit() != null && validateUnitName(product.getUnits().getUnit1unit())) ||
	       (product.getUnits().getUnit2unit() != null && validateUnitName(product.getUnits().getUnit2unit())) ||
	       (product.getUnits().getUnit3unit() != null && validateUnitName(product.getUnits().getUnit3unit())) ||
	       (product.getUnits().getUnit4unit() != null && validateUnitName(product.getUnits().getUnit4unit())))
	    	 throw new BedDAOBadParamException("Unit or unit ratio is wrong.");
	}
	
	public static void validateStandardOrderUnit(Product product) throws BedDAOBadParamException{
	    int count = 0;
		if(product.getUnits().getBaseisstdord() == 'Y' || product.getUnits().getBaseisstdord() == 'y') count++;
		if(product.getUnits().getUnit1isstdord() == 'Y' || product.getUnits().getUnit1isstdord() == 'y') count++;
		if(product.getUnits().getUnit2isstdord() == 'Y' || product.getUnits().getUnit2isstdord() == 'y') count++;
		if(product.getUnits().getUnit3isstdord() == 'Y' || product.getUnits().getUnit3isstdord() == 'y') count++;
		if(product.getUnits().getUnit4isstdord() == 'Y' || product.getUnits().getUnit4isstdord() == 'y') count++;
		if(count < 1)
			throw new BedDAOBadParamException("ERROR: Missing Std Order Unit!.");	
		if(count > 1)
			throw new BedDAOBadParamException("ERROR: Multiple Std Sell Unit!!.");	
	}    	
	
	public static void validateStandardSellUnit(Product product) throws BedDAOBadParamException{
	    int count = 0;
		if(product.getUnits().getBaseisstdsell() == 'Y' || product.getUnits().getBaseisstdsell() == 'y') count++;
		if(product.getUnits().getUnit1isstdsell() == 'Y' || product.getUnits().getUnit1isstdsell() == 'y') count++;
		if(product.getUnits().getUnit2isstdsell() == 'Y' || product.getUnits().getUnit2isstdsell() == 'y') count++;
		if(product.getUnits().getUnit3isstdsell() == 'Y' || product.getUnits().getUnit3isstdsell() == 'y') count++;
		if(product.getUnits().getUnit4isstdsell() == 'Y' || product.getUnits().getUnit4isstdsell() == 'y') count++;
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
	
	public boolean validateUnitUPC(Product product){
		return true;
	}	
	
	public boolean validateInventoryItemCode(String inventoryItemCode){
		return true;
	}
}
