package com.bedrosians.bedlogic.util;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.bedrosians.bedlogic.domain.item.IconDescription;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;
import com.bedrosians.bedlogic.domain.item.enums.Origin;


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
	
	public static IconDescription parseIcons(String icons){
		 /*!
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
		
		IconDescription icon = new IconDescription();
		
		String country = null;
				
		if (icons.length() > 0 && icons.charAt(0) == 'Y' )
		    country = Origin.Italy.getDescription();
		else if (icons.length() > 3 && icons.charAt(3) == 'Y' )
		    country = Origin.USA.getDescription();
		else if (icons.length() > 13 && icons.charAt(13) == 'Y' )
			country = Origin.China.getDescription();
		else if (icons.length() > 14 && icons.charAt(14) == 'Y' )
		    country = Origin.Turkey.getDescription();
		else if (icons.length() > 15 && icons.charAt(15) == 'Y' )
		    country = Origin.Mexico.getDescription();
		
		icon.setOriginCountry(country);
		
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
	
	
	public static float getUnitSellRatio(Item item)
    {
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
	

	/*
	$Ims_SellRatio = 1;
	$Ims_SellUnit  = $Ims_BaseUnit;
	$Ims_SellIsFractQty = $Ims_BaseIsFractQty;

	$Ims_PackRatio = 1;
	$Ims_PackUnit  = $Ims_BaseUnit;
	$Ims_PackIsFractQty = $Ims_BaseIsFractQty;

	$Ims_VPrcRatio = 1;
	$Ims_VPrcUnit  = $Ims_BaseUnit;
	$Ims_VPrcIsFractQty = $Ims_BaseIsFractQty;

	for ($ii=0; $ii<5; $ii++)
	{if ($Ims_IsStdOrd[$ii] == 'Y')
	  {$Ims_PackRatio = $Ims_Ratio[$ii];
	   $Ims_PackUnit  = $Ims_Unit[$ii];
	   $Ims_PackIsFractQty = $Ims_IsFractQty[$ii];
	  }
	 if ($Ims_IsStdSell[$ii] == 'Y')
	  {$Ims_SellRatio = $Ims_Ratio[$ii];
	   $Ims_SellUnit  = $Ims_Unit[$ii];
	   $Ims_SellIsFractQty = $Ims_IsFractQty[$ii];
	  }
	 if ($Ims_VendorPriceUnit == $Ims_Unit[$ii])
	  {$Ims_VPrcRatio = $Ims_Ratio[$ii];
	   $Ims_VPrcUnit  = $Ims_VendorPriceUnit;
	   $Ims_VPrcIsFractQty = $Ims_IsFractQty[$ii];
	  }
	}
    */
	
	
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
