package com.bedrosians.bedlogic.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.item.Item;


public class ImsValidation {

	public void validateNewItem(Item item){
		if(item.getItemcd() == null || item.getItemcd().length() < 1)
		   throw new IllegalArgumentException("Item code cannot be null.");
		if(item.getBaseunit() == null || item.getBaseunit().length() < 1)
		   throw new IllegalArgumentException("BaseUnit cannot be null.");
	}
	
	//Validate the new item against the ims CHECK constraints
	public void validateNewItemUnit(Item item){
		
		if(item.getBaseunit() == null || item.getBaseunit().length() < 1)
		   throw new IllegalArgumentException("BaseUnit cannot be null.");
		//ims Check1-4
		if(((item.getUnit1unit() == null || item.getUnit1unit().trim().length() == 0) && item.getUnit1ratio() > 0) || ((item.getUnit1unit() != null && item.getUnit1unit().trim().length() > 0) && (item.getUnit1ratio() == null || item.getUnit1ratio() <= 0)) ||
	       ((item.getUnit2unit() == null || item.getUnit2unit().trim().length() == 0) && item.getUnit2ratio() > 0) || ((item.getUnit2unit() != null && item.getUnit2unit().trim().length() > 0) && (item.getUnit2ratio() == null || item.getUnit2ratio() <= 0)) ||
	  	   ((item.getUnit3unit() == null || item.getUnit3unit().trim().length() == 0) && item.getUnit3ratio() > 0) || ((item.getUnit3unit() != null && item.getUnit3unit().trim().length() > 0) && (item.getUnit3ratio() == null || item.getUnit3ratio() <= 0)) ||
		   ((item.getUnit4unit() == null || item.getUnit4unit().trim().length() == 0) && item.getUnit4ratio() > 0) || ((item.getUnit4unit() != null && item.getUnit4unit().trim().length() > 0) && (item.getUnit4ratio() == null || item.getUnit4ratio() <= 0)))
		    throw new IllegalArgumentException("Unit or unit ratio is wrong.");
		//ims Check 5
         if ((item.getVendorpriceunit() == null) ||
        	 (item.getVendorpriceunit().length() < 4 || 
        	 !item.getVendorpriceunit().equalsIgnoreCase(item.getBaseunit()) || 
        	 !item.getVendorpriceunit().equalsIgnoreCase(item.getUnit1unit()) ||
        	 !item.getVendorpriceunit().equalsIgnoreCase(item.getUnit2unit()) ||
        	 !item.getVendorpriceunit().equalsIgnoreCase(item.getUnit3unit()) ||
        	 !item.getVendorpriceunit().equalsIgnoreCase(item.getUnit4unit())) ||
        	 //(item.getVendordiscpct1() == null) ||
        	 //(item.getVendordiscpct2() == null) ||
        	 //(item.getVendordiscpct3() == null) ||
        	 (item.getVendorroundaccuracy() == null) ||
        	 (item.getVendorroundaccuracy() < 0) ||
        	 (item.getVendorroundaccuracy() > 4) ||
        	 
        	 (item.getVendormarkuppct() == null) ||
        	 (item.getVendorfreightratecwt() == null)
        	 //(item.getVendorlandedbasecost() == null)
        	 //(item.getSellpricemarginpct() == null) ||
        	 //(item.getSellpricemarginpct().longValue() >= 100) ||
        	 //(item.getListpricemarginpct() == null) ||
        	 //(item.getListpricemarginpct().longValue() >= 100) ||
        	 
        	 //(item.getSellpriceroundaccuracy() == null) ||
        	 //(item.getSellpriceroundaccuracy() < 0) ||
        	// (item.getSellpriceroundaccuracy() .length > 4) ||
        	 //(item.getSellpricemarginpct() == null) ||
        	 //(item.getSellpricemarginpct() == null)
        	 )
        	 throw new IllegalArgumentException("Unit price related value.");
		 
		//ims check 6
		if (item.getBaseunit() != null && checkIllegalValue(item.getBaseunit()) ||
	       (item.getUnit1unit() != null && checkIllegalValue(item.getUnit1unit())) ||
	       (item.getUnit2unit() != null && checkIllegalValue(item.getUnit2unit())) ||
	       (item.getUnit3unit() != null && checkIllegalValue(item.getUnit3unit())) ||
	       (item.getUnit4unit() != null && checkIllegalValue(item.getUnit4unit())))
	    	 throw new IllegalArgumentException("Unit or unit ratio is wrong.");
		
	}
	
	private boolean checkIllegalValue(String originalString){
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
