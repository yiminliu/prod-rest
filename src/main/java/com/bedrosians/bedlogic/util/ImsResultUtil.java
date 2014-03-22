package com.bedrosians.bedlogic.util;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;


public class ImsResultUtil {

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
	        case "Y":
	    	   mpsCode = MpsCode.Pre_Drop;
	    	   break;
	        case "D":
	    	   mpsCode = MpsCode.Drop;	
	    	   break;
	    }	
		return mpsCode;
	}
	
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
	public static String transformABCCode(String abcCode){
		String result = "";
		return result;
	}
	
	public static String getStandardSellUnit(Item item) {
		String standardUnit = item.getBaseunit();
		
        if (item.getUnit1isstdsell() != null && item.getUnit1isstdsell() == 'Y')
        	standardUnit = item.getUnit1unit().trim();
        else  if (item.getUnit2isstdsell() != null && item.getUnit2isstdsell() == 'Y')
        	standardUnit = item.getUnit2unit().trim();
        else  if (item.getUnit3isstdsell() != null && item.getUnit3isstdsell() == 'Y')
        	standardUnit = item.getUnit3unit().trim();
        else  if (item.getUnit4isstdsell() != null && item.getUnit4isstdsell() == 'Y')
        	standardUnit = item.getUnit4unit().trim();
       
        return standardUnit;
    }
	
	public static String getStandardOrderUnit(Item item) {
		String standardUnit = item.getBaseunit();
		
        if (item.getUnit1isstdsell() != null && item.getUnit1isstdord() == 'Y')
        	standardUnit = item.getUnit1unit().trim();
        else  if (item.getUnit2isstdsell() != null && item.getUnit2isstdord() == 'Y')
        	standardUnit = item.getUnit2unit().trim();
        else  if (item.getUnit3isstdsell() != null && item.getUnit3isstdord() == 'Y')
        	standardUnit = item.getUnit3unit().trim();
        else  if (item.getUnit4isstdsell() != null && item.getUnit4isstdord() == 'Y')
        	standardUnit = item.getUnit4unit().trim();
       
        return standardUnit;
    }
	
	
	public static float getBaseToSellRatio(Item item)
    {
		float baseToSellRatio = 1f;
 
        if(item.getUnit1isstdsell() != null && "Y".equalsIgnoreCase(item.getUnit1isstdsell().toString().trim()));
            baseToSellRatio = item.getUnit1ratio();
        if(item.getUnit2isstdsell() != null && "Y".equalsIgnoreCase(item.getUnit2isstdsell().toString().trim()));
            baseToSellRatio = item.getUnit2ratio();
        if(item.getUnit3isstdsell() != null && "Y".equalsIgnoreCase(item.getUnit3isstdsell().toString().trim()));
            baseToSellRatio = item.getUnit3ratio();
        if(item.getUnit4isstdsell() != null && "Y".equalsIgnoreCase(item.getUnit4isstdsell().toString().trim()));
            baseToSellRatio = item.getUnit4ratio();
       
         if(baseToSellRatio == 0)
        	baseToSellRatio = 1;
       
        return baseToSellRatio;
    }
	
	
	
	/*
	 public static function getUnitSellRatio($imsRec, $unit)
	    {
	        $baseToSellRatio = 1;

	        if ($unit == trim($imsRec['baseunit']))
	            $baseToSellRatio = 1;
	        else if ($unit == trim($imsRec['unit1unit']))
	            $baseToSellRatio = $imsRec['unit1ratio']; 
	        else if ($unit == trim($imsRec['unit2unit']))
	            $baseToSellRatio = $imsRec['unit2ratio']; 
	        else if ($unit == trim($imsRec['unit3unit']))
	            $baseToSellRatio = $imsRec['unit3ratio']; 
	        else if ($unit == trim($imsRec['unit4unit']))
	            $baseToSellRatio = $imsRec['unit4ratio']; 

	        if ($baseToSellRatio == 0)
	            $baseToSellRatio = 1;

	        return $baseToSellRatio;
	    }
	    
	 
    public static Float getUnitToSellRatio(Item item)
	    {
	        Float ratio = 1f;
	        
	        if(item.getUnit1ratio())

	        if ($unit == trim($imsRec['baseunit']))
	            $baseToSellRatio = 1;
	        else if ($unit == trim($imsRec['unit1unit']))
	            $baseToSellRatio = $imsRec['unit1ratio']; 
	        else if ($unit == trim($imsRec['unit2unit']))
	            $baseToSellRatio = $imsRec['unit2ratio']; 
	        else if ($unit == trim($imsRec['unit3unit']))
	            $baseToSellRatio = $imsRec['unit3ratio']; 
	        else if ($unit == trim($imsRec['unit4unit']))
	            $baseToSellRatio = $imsRec['unit4ratio']; 

	        if ($baseToSellRatio == 0)
	            $baseToSellRatio = 1;

	        return $baseToSellRatio;
	    }
	 
	
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
	
	/*
	  public static function getBaseToSellRatio($imsRec)
    {
        $baseToSellRatio = 1;

        if ($imsRec['unit1isstdsell'] == 'Y')
            $baseToSellRatio = $imsRec['unit1ratio']; 
        else if ($imsRec['unit2isstdsell'] == 'Y') 
            $baseToSellRatio = $imsRec['unit2ratio']; 
        else if ($imsRec['unit3isstdsell'] == 'Y') 
            $baseToSellRatio = $imsRec['unit3ratio']; 
        else if ($imsRec['unit4isstdsell'] == 'Y') 
            $baseToSellRatio = $imsRec['unit4ratio']; 

        if ($baseToSellRatio == 0)
            $baseToSellRatio = 1;
    
        return $baseToSellRatio;
    }
    
   
    public static function adjustStandardPrice($imsRec, $stdPrice, $unit)
    {
        $baseToSellRatio = self::getBaseToSellRatio($imsRec);
    
        $basePrice = round(($stdPrice / $baseToSellRatio), 6);

        if ($unit == trim($imsRec['baseunit']))
            $adjPrice = round($basePrice, 4);
        else if ($unit == trim($imsRec['unit1unit']))
            $adjPrice = round(($basePrice * $imsRec['unit1ratio']), 4);
        else if ($unit == trim($imsRec['unit2unit']))
            $adjPrice = round(($basePrice * $imsRec['unit2ratio']), 4);
        else if ($unit == trim($imsRec['unit3unit']))
            $adjPrice = round(($basePrice * $imsRec['unit3ratio']), 4);
        else if ($unit == trim($imsRec['unit4unit']))
            $adjPrice = round(($basePrice * $imsRec['unit4ratio']), 4);
        else
            $adjPrice = 0;

        return $adjPrice;
    }
    
	 * 
	 */
}
