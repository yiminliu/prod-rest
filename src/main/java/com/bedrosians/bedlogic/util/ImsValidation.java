package com.bedrosians.bedlogic.util;


import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;


public class ImsValidation {

	public static void validateQueryParams(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		validateForNullParams(queryParams);	
	}
	
	public static void validateInsertUpdateParams( MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		validateForNullParams(queryParams);		
	}
	
	public static void validateNewItem(Item item) throws BedDAOBadParamException{
		if(item == null)
		   throw new BedDAOBadParamException("Item should not be null.");
		validateItemCode(item);
		validateFieldLength(item);
		validateNewItemUnit(item);
		if(item.getPrice() != null){
		   validateDates(item.getPrice().getTempdatefrom(), item.getPrice().getTempdatethru());
		}
	}

	public static void validateItemCode(Item item) throws BedDAOBadParamException{
		if(item.getItemcode() == null || item.getItemcode().length() < 1)
		   throw new BedDAOBadParamException("Item code cannot be empty.");
		if(item.getItemcode().length() > 18)
		   throw new BedDAOBadParamException("Item code cannot longer that 18 characters.");
		//if(item.getImsNewFeature() != null && item.getImsNewFeature().getItemcd() == null)
		//	item.getImsNewFeature().setItemcd(item.getItemcode());	
	}
	
	private static void validateForNullParams(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		if(queryParams == null || queryParams.isEmpty())
		   throw new BedDAOBadParamException("Please enter valid query parameters!");	
	}
	
	public static void validateFieldLength(Item item) throws BedDAOBadParamException{
		if(item.getItemdesc1() != null && item.getItemdesc1().length() > 35)
		   throw new BedDAOBadParamException("Description should not be longer that 35 characters.");
		if(item.getIconsystem() != null && item.getIconsystem().length() > 20)
		   throw new BedDAOBadParamException("Icons should not be longer than 20 charactors.");
		if(item.getPrice().getPricegroup() != null && item.getPrice().getPricegroup().length() > 2)
		   throw new BedDAOBadParamException("Pricegroup length should be two charactors or less.");
		if(item.getItemgroupnbr() != null && item.getItemgroupnbr() > 99)
		   throw new BedDAOBadParamException("Itemgroupnbr length should be two digits or less.");
		validateCharacter("Lottype", item.getLottype()); 
		validateCharacter("frostresistance", item.getTestSpecification() == null ? null : item.getTestSpecification().getFrostresistance());
		//validateCharacter("directship", item.getDirectship());
		
	}
	
	public static void validateCharacter(String name, String value) throws BedDAOBadParamException {
		if(value != null && value.length() > 1)
		   throw new BedDAOBadParamException(name + " is " + value + ". It should be one charactor.");
	}	

	public static void validateDates(Date startDate, Date endDate) throws BedDAOBadParamException{
		if (startDate != null && endDate != null && startDate.after(endDate))
			throw new BedDAOBadParamException("End date should not be earlier than start date.");
	}
	
	//Validate the new item against the ims CHECK constraints
	public static void validateNewItemUnit(Item item) throws BedDAOBadParamException{
		
		if(item.getPackaginginfo().getBaseunit() == null || item.getPackaginginfo().getBaseunit().length() < 1)
		   throw new BedDAOBadParamException("BaseUnit cannot be null.");
	
		if((item.getPackaginginfo().getBaseunit() != null && item.getPackaginginfo().getBaseunit().length() > 4) || 
		   (item.getPackaginginfo().getUnit1unit() != null && item.getPackaginginfo().getUnit1unit().length() > 4) ||
		   (item.getPackaginginfo().getUnit2unit() != null && item.getPackaginginfo().getUnit2unit().length() > 4) ||
		   (item.getPackaginginfo().getUnit3unit() != null && item.getPackaginginfo().getUnit3unit().length() > 4) ||
		   (item.getPackaginginfo().getUnit4unit() != null && item.getPackaginginfo().getUnit4unit().length() > 4))
			throw new BedDAOBadParamException("Unit cannot be longer than four characters.");
		//db constraint ims_check 1
		if((item.getPackaginginfo().getUnit1unit() == null || item.getPackaginginfo().getUnit1unit().length() == 0) && item.getPackaginginfo().getUnit1ratio() > 0)
			throw new BedDAOBadParamException("Unit1 ratio should be 0, since unit1 unit is empty.");
		if((item.getPackaginginfo().getUnit1unit() != null && item.getPackaginginfo().getUnit1unit().length() > 0) && item.getPackaginginfo().getUnit1ratio() < 0)
			throw new BedDAOBadParamException("Please privide Unit1 ratio.");
		
		//db constraint ims_check 2
		if((item.getPackaginginfo().getUnit2unit() == null || item.getPackaginginfo().getUnit2unit().length() == 0) && item.getPackaginginfo().getUnit2ratio() > 0)
			throw new BedDAOBadParamException("Unit2 ratio should be 0, since unit2 unit is empty.");
		if((item.getPackaginginfo().getUnit2unit() != null && item.getPackaginginfo().getUnit2unit().length() > 0) && item.getPackaginginfo().getUnit2ratio() < 0)
			throw new BedDAOBadParamException("Please privide Unit2 ratio.");
	
		//db constraint ims_check 3
		if((item.getPackaginginfo().getUnit3unit() == null || item.getPackaginginfo().getUnit3unit().length() == 0) && item.getPackaginginfo().getUnit3ratio() > 0)
			throw new BedDAOBadParamException("Unit3 ratio should be 0, since unit3 unit is empty.");
		if((item.getPackaginginfo().getUnit3unit() != null && item.getPackaginginfo().getUnit3unit().length() > 0) && item.getPackaginginfo().getUnit3ratio() < 0)
			throw new BedDAOBadParamException("Please privide Unit3 ratio.");
				
		//db constraint ims_check 4
		if((item.getPackaginginfo().getUnit4unit() == null || item.getPackaginginfo().getUnit4unit().length() == 0) && item.getPackaginginfo().getUnit4ratio() > 0)
			throw new BedDAOBadParamException("Unit4 ratio should be 0, since unit4 unit is empty.");
		if((item.getPackaginginfo().getUnit4unit() != null && item.getPackaginginfo().getUnit4unit().length() > 0) && item.getPackaginginfo().getUnit4ratio() < 0)
			throw new BedDAOBadParamException("Please privide Unit1 rtio.");
	/*		
	 	//ims Check 5
         if ((item.getUnit().getVendorpriceunit() == null) ||
        	 (item.getUnit().getVendorpriceunit().length() < 4 || 
        	 !item.getUnit().getVendorpriceunit().equalsIgnoreCase(item.getUnit().getBaseunit()) || 
        	 !item.getUnit().getVendorpriceunit().equalsIgnoreCase(item.getUnit().getUnit1unit()) ||
        	 !item.getUnit().getVendorpriceunit().equalsIgnoreCase(item.getUnit().getUnit2unit()) ||
        	 !item.getUnit().getVendorpriceunit().equalsIgnoreCase(item.getUnit().getUnit3unit()) ||
        	 !item.getUnit().getVendorpriceunit().equalsIgnoreCase(item.getUnit().getUnit4unit())) ||
        	 //(item.getUnit().getVendordiscpct1() == null) ||
        	 //(item.getUnit().getVendordiscpct2() == null) ||
        	 //(item.getUnit().getVendordiscpct3() == null) ||
        	 (item.getUnit().getVendorroundaccuracy() == null) ||
        	 (item.getUnit().getVendorroundaccuracy() < 0) ||
        	 (item.getUnit().getVendorroundaccuracy() > 4) ||
        	 
        	 (item.getUnit().getVendormarkuppct() == null) ||
        	 (item.getUnit().getVendorfreightratecwt() == null)
        	 //(item.getUnit().getVendorlandedbasecost() == null)
        	 //(item.getUnit().getSellpricemarginpct() == null) ||
        	 //(item.getUnit().getSellpricemarginpct().longValue() >= 100) ||
        	 //(item.getUnit().getListpricemarginpct() == null) ||
        	 //(item.getUnit().getListpricemarginpct().longValue() >= 100) ||
        	 
        	 //(item.getUnit().getSellpriceroundaccuracy() == null) ||
        	 //(item.getUnit().getSellpriceroundaccuracy() < 0) ||
        	// (item.getUnit().getSellpriceroundaccuracy() .length > 4) ||
        	 //(item.getUnit().getSellpricemarginpct() == null) ||
        	 //(item.getUnit().getSellpricemarginpct() == null)
        	 )
        	 throw new IllegalArgumentException("Unit price related value.");
	*/	 
		//ims check 6
		if (item.getPackaginginfo().getBaseunit() != null && checkIllegalValue(item.getPackaginginfo().getBaseunit()) ||
	       (item.getPackaginginfo().getUnit1unit() != null && checkIllegalValue(item.getPackaginginfo().getUnit1unit())) ||
	       (item.getPackaginginfo().getUnit2unit() != null && checkIllegalValue(item.getPackaginginfo().getUnit2unit())) ||
	       (item.getPackaginginfo().getUnit3unit() != null && checkIllegalValue(item.getPackaginginfo().getUnit3unit())) ||
	       (item.getPackaginginfo().getUnit4unit() != null && checkIllegalValue(item.getPackaginginfo().getUnit4unit())))
	    	 throw new IllegalArgumentException("Unit or unit ratio is wrong.");
		
	}
	
	private static boolean checkIllegalValue(String originalString){
		String[] illegalValues = new String[]{"EA", "EACH", "PC", "LF", "SF", "CT", "CTM", "CTNH", "PK"};
		for(String s : illegalValues){
			if(originalString.equalsIgnoreCase(s))
			   return true;	
		}
		return false;
	}
	
	public boolean validateUnitUPC(Item item){
		return true;
	}	
}
