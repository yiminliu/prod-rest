package com.bedrosians.bedlogic.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.Vendor;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.DesignLook;
import com.bedrosians.bedlogic.domain.item.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.item.enums.TaxClass;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;
import com.bedrosians.bedlogic.domain.item.enums.Icon;
import com.bedrosians.bedlogic.domain.item.enums.Body;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.bedrosians.bedlogic.domain.item.enums.Edge;
import com.bedrosians.bedlogic.domain.item.enums.ShadeVariation;

public class ImsQueryUtil {

	public static String getValue(MultivaluedMap<String, String> queryParams, String searchKey) {
		
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator<Entry<String, List<String>>> it = set.iterator(); 
		String key, value = null;
		while(it.hasNext()) {
		   	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	key = (String)entry.getKey();
		   	//key = Character.toUpperCase(key.charAt(0)) + key.substring(1); 
		   	if(key.equalsIgnoreCase(searchKey))
		   	   return ((List<String>)entry.getValue()).get(0);
		   	
		} 	
			  	
		return null;
	}
	
	public static Item buildItemForInsert(MultivaluedMap<String, String> queryParams){
		Item item = new Item();
		
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator<Entry<String, List<String>>> it = set.iterator(); 
		String key, value = null;
		while(it.hasNext()) {
			try{
		   	   Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	   key = (String)entry.getKey();
		   	   //key = Character.toUpperCase(key.charAt(0)) + key.substring(1); 
		   	   value = ((List<String>)entry.getValue()).get(0);
		   	   setParameter(item, key, value);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		} 
		//ImsNewFeature newFeature = item.getImsNewFeature();
		//if(newFeature != null && newFeature.getItemcd() == null) 
			//newFeature.setItemcd(item.getItemcd());
		
		//newFeature.setCreatedDate(new Date());
		//newFeature.setItem(item);
		//item.setImsNewFeature(newFeature);
				  	
		return item;
	}
	
	public static Item buildItemForUpdate(Item item, MultivaluedMap<String, String> queryParams){
		
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator<Entry<String, List<String>>> it = set.iterator(); 
		String key, value = null;
		while(it.hasNext()) {
		   	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	key = (String)entry.getKey();
		   	if(key.equalsIgnoreCase("itemcd"))
		   	   continue; 
		   	value = ((List<String>)entry.getValue()).get(0);
		   	setParameter(item, key, value);
		} 	
			  	
		return item;
	}
	
	/*This method is used to set item field values for creating or updating item
	 * 
	 */
    private static Item setParameter(Item item, String field, String value){
    	
    	//if(value != null) 
    	//value = value.trim().toUpperCase();
    	
    	List<Vendor> vendors = item.getVendors();
   
    	Vendor	vendor1 = vendors.isEmpty() ? new Vendor() : vendors.get(0);
    	Vendor	vendor2 = vendors.size() <= 1? new Vendor() : vendors.get(1);
    	Vendor	vendor3 = vendors.size() <= 2? new Vendor() : vendors.get(2);
    		
    	if(!vendors.contains(vendor1))
    		item.addVendor(vendor1);
    	if(!vendors.contains(vendor2))
    		item.addVendor(vendor2);
    	if(!vendors.contains(vendor3))
    		item.addVendor(vendor3);
    	
    	switch(field){

    	   /*------- basic info ---------*/ 
    	   case "itemcd": case "itemcode": case "itemCode":
    		   item.setItemcd(value);
    		   //vendor.setItemcd(value);
    		   //if(item.getImsNewFeature() != null && item.getImsNewFeature().getItemcd() == null)
    		     // item.getImsNewFeature().setItemcd(value);
    		   break;
    	   case "description": case "itemdesc1":
    		   item.setDescription(value);
    		   break;
    	   case "seriesname":  case "seriesName":
    		   item.setSeriesname(value);
    		   break;
    	   case "color":
    		   item.setColor(value);
    		   break;	
    	   case "colorcategory": case "colorCategory":
    		   item.setColorcategory(value);
    		   break;	   
    	   case "category":
    		   item.setCategory(value);
    		   break;
    	   case "type":
    		   item.setType(value);
    		   break;
       	   case "itemtypecd": case "itemtypeCode": case "itemTypeCode":
    		   item.setItemtypecd(value);
    		   break;
       	   case "origin":
  		       item.setOrigin(value);
  		       break;	   
    	   case "showonwebsite": case "showOnWebsite":
    		   item.setShowonwebsite(value);
    		   break;	
    	   case "productManager":
    		   item.setProductManager(value);
    		   break;	
    	   case "buyer":
    		   item.setBuyer(value);
    	   case "taxclass": case "taxClass":
    		   item.setTaxClass(TaxClass.instanceOf(value));	   
    		   break;
    	   case "inactivecd": case "inactive_code":
    		   item.setInactivecd(value);
    		   break;	
    	   case "shadevariation": case "shadeVariation":
    		   item.setShadevariation(value);
    		   break;		
    	   case "inventoryItemcd": case "inventoryitemcd":
    		   item.setInventoryItemcd(value);
    		   break;	
    	   case "showonalysedwards": case "showOnAlysedwards":
    		   item.setShowonalysedwards(value);
    		   break;	
    	   case "offshade": case "offShade":
    		   item.setOffshade(value);
    		   break;		
    	   case "printlabel": case "printLabel":
    		   item.setPrintlabel(value);
    		   break;	
    	   case "lottype": case "lotType":
    		   item.setLottype(value);
    		   break;		
    		
    	/*-------- dimension --------*/	   
    	   case "size":
    		   item = generateSizeQuery(item, value);
    		   break;	   
    	   case "length":
    		   item.setLength(value);
    		   break;
    	   case "width":
    		   item.setWidth(value);
    		   break;
    	   case "thickness":
    		   item.setThickness(value);
    		   break;
    	   case "nmLength":  case "norminalLength":
    		   item.setNmLength(Float.valueOf(value));
    		   break;
    	   case "nmWidth":  case "norminalWidth":
    		   item.setNmWidth(Float.valueOf(value));
    		   break;
    	   case "nmThickness": case "norminalThickness":
    		   item.setNmThickness(Float.valueOf(value));
    		   break;	
    	   case "sizeunits": case "sizeUnits":
    		   item.setSizeunits(value);
    		   break;
    	   case "thicknessunit": case "thicknessUnit":
    		   item.setThicknessunit(value);
    		   break;	
    		   
    	/*----- material info -------*/
    	   case "mfeature": case "mFeature": case "materialFeature":
    		   item.setMfeature(value);
    		   break;	
    	   case "mattype": case "matType": case "materialType":
    		   item.setMattype(value);
    		   break;			   
    	   case "matcategory": case "matCategory": case "materialCategory":
    		   item.setMatcategory(value);
    		   break;	   
    	   case "matstyle": case "matStyle": case "materialStyle":
    		   item.setMatstyle(value);
    		   break;	
    	   case "materialclassCd": case "materialClassCd": case "materialClassCode":
    		   item.setMaterialclassCd(value);
    		   break; 	   
    			
    		 //------- price info --------//	
    	   case "price": case "sellprice":
    		   item.setPrice(new BigDecimal(value));
    		   break;		   
    	   case "futurePrice": case "futuresell":
    		   item.setFuturePrice(new BigDecimal(value));
    		   break;	
    	   case "priorPrice": case "priorsellprice":
    		   item.setPriorPrice(new BigDecimal(value));
    		   break;
    	   case "tempPrice": case "tempprice":
    		   item.setFuturePrice(new BigDecimal(value));
    		   break;
    	   case "tempdatefrom":  case "tempdDteFrom":
    		   item.setTempdatefrom(new Date(value));
    		   break;	   
    	   case "tempdatethru":  case "tempDateThru":
    		   item.setTempdatethru(new Date(value));
    		   break;	   
    		  
    	   case "pricemarginpct": case "priceMarginPct":
    		   item.setPriceMarginPct(Float.parseFloat(value));
    		   break;		   
    	   case "minimalmarginpct": case "minimalMarginPct":
    		   item.setMinimalMarginPct(Float.parseFloat(value));
    		   break;	
    	   case "priceroundaccuracy": case "priceRoundAccuracy":
    		   item.setPriceRoundAccuracy(Integer.parseInt(value));
    		   break;	   
    			
    		   
    			/*---------- Vendor ----------*/	
    	   case "vendorId": case "vendornbr1":
    		   item.setVendorId(Integer.parseInt(value));
    		   vendor1.setVendorId(Long.parseLong(value));
    		   break; 
    	   case "xrefid": case "vendorxrefcd": 
    		   item.setVendorxrefcd(value);
    		   vendor1.setVendorXrefId(value);
    		   break; 
    	   case "vendorpriceunit": case "vendorPriceUnit":
    		   item.setVendorpriceunit(value);
    		   vendor1.setVendorPriceUnit(value);
    		   break; 
    	   case "vendorfob": case "vendorFob":
        	   item.setVendorfob(value);
        	   vendor1.setVendorFob(value);
        	   break; 	   
    	   case "vendorlistprice": case "vendorListPrice": case "vendor_list_price":
    		   item.setVendorlistprice(new BigDecimal(value));
    		   vendor1.setVendorListPrice(new BigDecimal(value));
    		   break;
    	   case "vendordiscountPct": case "vendorDiscountPct":
    		   item.setVendordiscpct(Float.parseFloat(value));
    		   vendor1.setVendorDiscountPct(Float.parseFloat(value));
    		   break;
    	   case "vendorDutyPct": case "dutypct": case "dutyPct":
    		   item.setDutypct(Float.parseFloat(value));
    		   vendor1.setDutyPct(Float.parseFloat(value));
    		   break;
    	   case "vendornetprice":  case "vendorNetPrice":
    		   item.setVendornetprice(new BigDecimal(value));
    		   vendor1.setVendorNetPrice(new BigDecimal(value));
    		   break;
    	   case "leadTime": case "leadtime":
    		   item.setLeadtime(Integer.parseInt(value));
    		   vendor1.setLeadTime(Integer.parseInt(value));
    		   break; 
    	   case "vendormarkuppct": case "vendorMarkupPct":
    		   item.setVendormarkuppct(Float.parseFloat(value));
    		   vendor1.setVendorMarkupPct(Float.parseFloat(value));
    		   break;	
    	   case "vendordiscpct": case "vendordiscpct1": case "vendorDiscPct":
    		   item.setVendordiscpct(Float.parseFloat(value));
    		   vendor1.setVendorDiscountPct(Float.parseFloat(value));
    		   break;	 
    	   case "vendorlandedbasecost": case "vendorLandedBaseCost":
    		   item.setVendorlandedbasecost(new BigDecimal(value));
        	   break;	   
    	   case "vendorfreightratecwt": case "vendorFreightRateCwt":
    		   item.setVendorfreightratecwt(new BigDecimal(value));
    		   vendor1.setVendorFreightRateCwt(new BigDecimal(value));
    		   break;
    	   case "vendorroundaccuracy": case "vendorRoundAccuracy": case "vendorpriceroundaccuracy":
    		   item.setVendorroundaccuracy(Integer.parseInt(value));
    		   vendor1.setVendorPriceRoundAccuracy(Integer.parseInt(value));
    		   break; 
    		   
    	   case "v2_vendorId": case "v2_vendornbr1":
    		   vendor2.setVendorId(Long.parseLong(value));
    		   break; 
    	   case "v2_xrefid": case "v2_vendorxrefcd": 
    		   vendor2.setVendorXrefId(value);
    		   break; 
    	   case "v2_vendorpriceunit": case "v2_vendorPriceUnit":
    		   vendor2.setVendorPriceUnit(value);
    		   break; 
    	   case "v2_vendorfob": case "v2_vendorFob":
        	   vendor2.setVendorFob(value);
        	   break; 	   
    	   case "v2_vendorlistprice": case "v2_vendorListPrice": case "v2_vendor_list_price":
     		   vendor2.setVendorListPrice(new BigDecimal(value));
    		   break;
    	   case "v2_vendordiscountPct": case "v2_vendorDiscountPct":
    		   vendor2.setVendorDiscountPct(Float.parseFloat(value));
    		   break;
    	   case "v2_vendorDutyPct": case "v2_dutypct": case "v2_dutyPct":
      		   vendor2.setDutyPct(Float.parseFloat(value));
    		   break;
    	   case "v2_vendornetprice":  case "v2_vendorNetPrice":
    		   vendor2.setVendorNetPrice(new BigDecimal(value));
    		   break;
    	   case "v2_leadTime": case "v2_leadtime":
     		   vendor2.setLeadTime(Integer.parseInt(value));
    		   break; 
    	   case "v2_vendormarkuppct": case "v2_vendorMarkupPct":
    		   vendor2.setVendorMarkupPct(Float.parseFloat(value));
    		   break;	
    	   case "v2_vendordiscpct": case "v2_vendordiscpct1": case "v2_vendorDiscPct":
    		   vendor2.setVendorDiscountPct(Float.parseFloat(value));
    		   break;	  
    	   case "v2_vendorfreightratecwt": case "v2_vendorFreightRateCwt":
    		   vendor2.setVendorFreightRateCwt(new BigDecimal(value));
    		   break;
    	   case "v2_vendorroundaccuracy": case "v2_vendorRoundAccuracy": case "v2_vendorpriceroundaccuracy":
    		   vendor2.setVendorPriceRoundAccuracy(Integer.parseInt(value));
    		   break; 
    		   
    	   case "v3_vendorId": case "v3_vendornbr1":
    		   vendor3.setVendorId(Long.parseLong(value));
    		   break; 
    	   case "v3_xrefid": case "v3_vendorxrefcd": 
    		   vendor3.setVendorXrefId(value);
    		   break; 
    	   case "v3_vendorpriceunit": case "v3_vendorPriceUnit":
    		   vendor3.setVendorPriceUnit(value);
    		   break; 
    	   case "v3_vendorfob": case "v3_vendorFob":
        	   vendor3.setVendorFob(value);
        	   break; 	   
    	   case "v3_vendorlistprice": case "v3_vendorListPrice": case "v3_vendor_list_price":
     		   vendor3.setVendorListPrice(new BigDecimal(value));
    		   break;
    	   case "v3_vendordiscountPct": case "v3_vendorDiscountPct":
    		   vendor3.setVendorDiscountPct(Float.parseFloat(value));
    		   break;
    	   case "v3_vendorDutyPct": case "v3_dutypct": case "v3_dutyPct":
      		   vendor3.setDutyPct(Float.parseFloat(value));
    		   break;
    	   case "v3_vendornetprice":  case "v3_vendorNetPrice":
    		   vendor3.setVendorNetPrice(new BigDecimal(value));
    		   break;
    	   case "v3_leadTime": case "v3_leadtime":
     		   vendor3.setLeadTime(Integer.parseInt(value));
    		   break; 
    	   case "v3_vendormarkuppct": case "v3_vendorMarkupPct":
    		   vendor3.setVendorMarkupPct(Float.parseFloat(value));
    		   break;	
    	   case "v3_vendordiscpct": case "v3_vendordiscpct1": case "v3_vendorDiscPct":
    		   vendor3.setVendorDiscountPct(Float.parseFloat(value));
    		   break;	  
    	   case "v3_vendorfreightratecwt": case "v3_vendorFreightRateCwt":
    		   vendor3.setVendorFreightRateCwt(new BigDecimal(value));
    		   break;
    	   case "v3_vendorroundaccuracy": case "v3_vendorRoundAccuracy": case "v3_vendorpriceroundaccuracy":
    		   vendor3.setVendorPriceRoundAccuracy(Integer.parseInt(value));
    		   break; 
    		   
    		 /*--------- units ----------*/
    	   case "stdunit": case "std_unit": case "standardUnit":
    		   item.setStdunit(value);
    		   break; 
    	   case "ordunit": case "ord_unit": case "OrderUnit":
    		   item.setOrdunit(value);
    		   break; 	   
    	   case "stdratio": case "std_ratio": case "standardRatio":
    		   item.setStdratio(Float.valueOf(value));
    		   break;	 
    	   case "ordratio": case "ord_ratio": case "OrderRatio":
    		   item.setStdratio(Float.valueOf(value));
    		   break;   
    	   case "baseunit": case "base_unit": case "BaseUnit":
    		   item.setBaseunit(value);
    		   break; 	 
    	   case "baseisstdsell": case "baseIsStdsell": 
    		   item.setBaseisstdsell(value.charAt(0));
    		   break;	
    	   case "baseisstdord": case "baseIsStdOrder": 
    		   item.setBaseisstdord(value.charAt(0));
    		   break;
    	   case "baseisfractqty": case "baseIsFractQty": 
    		   item.setBaseisfractqty(value.charAt(0));
    		   break;	
    	   case "baseispackunit": case "baseIsPackUnit": 
    		   item.setBaseispackunit(value.charAt(0));
    		   break;
    	   case "baseupc": case "baseUpc": 
    		   item.setBaseupc(Long.valueOf(value));
    		   break;   
    	   case "basevolperunit": case "baseVolPerUnit": 
    		   item.setBasevolperunit(new BigDecimal(value));
    		   break;	
    	   case "basewgtperunit": case "baseWgtPerUnit": 
    		   item.setBasewgtperunit(new BigDecimal(value));
    		   break;
    		
    	   case "unit1unit": case "unit1_unit": case "unit1Unit":
    		   item.setUnit1unit(value);
    		   break;
    	   case "unit1ratio": case "unit1Ratio":
    		   item.setUnit1unit(value);
    		   break; 	   
    	   case "unit1isstdsell": case "unit1IsStdsell": 
    		   item.setUnit1isstdsell(value.charAt(0));
    		   break;	
    	   case "unit1isstdor": case "unit1IsStdOrder": 
    		   item.setUnit1isstdord(value.charAt(0));
    		   break;
    	   case "unit1isfractqty": case "unit1IsFractQty": 
    		   item.setUnit1isfractqty(value.charAt(0));
    		   break;	
    	   case "unit1ispackunit": case "unit1IsPackUnit": 
    		   item.setUnit1ispackunit(value.charAt(0));
    		   break;
    	   case "unit1upc": case "unit1Upc": 
    		   item.setBaseupc(Long.valueOf(value));
    		   break;   
    	   case "unit1wgtperunit": case "unit1WgtPerUnit": 
    		   item.setUnit1wgtperunit(new BigDecimal(value));
    		   break;
    		   
    	   case "unit2unit": case "unit2Unit":
    		   item.setUnit1unit(value);
    		   break;
    	   case "unit2ratio": case "unit2Ratio":
    		   item.setUnit1unit(value);
    		   break; 	   
    	   case "unit2isstdsell": case "unit2IsStdsell": 
    		   item.setUnit1isstdsell(value.charAt(0));
    		   break;	
    	   case "unit2isstdor": case "unit2IsStdOrder": 
    		   item.setUnit1isstdord(value.charAt(0));
    		   break;
    	   case "unit2isfractqty": case "unit2IsFractQty": 
    		   item.setUnit1isfractqty(value.charAt(0));
    		   break;	
    	   case "unit2ispackunit": case "unit2IsPackUnit": 
    		   item.setUnit1ispackunit(value.charAt(0));
    		   break;
    	   case "unit2upc": case "unit2Upc": 
    		   item.setBaseupc(Long.valueOf(value));
    		   break;  
    	   case "unit2wgtperunit": case "unit2WgtPerUnit": 
    		   item.setUnit2wgtperunit(new BigDecimal(value));
    		   break;
    		   
    	   case "unit3unit": case "unit3Unit":
    		   item.setUnit1unit(value);
    		   break;
    	   case "unit3ratio": case "unit3Ratio":
    		   item.setUnit1unit(value);
    		   break; 	   
    	   case "unit3isstdsell": case "unit3IsStdsell": 
    		   item.setUnit1isstdsell(value.charAt(0));
    		   break;	
    	   case "unit3isstdor": case "unit3IsStdOrder": 
    		   item.setUnit1isstdord(value.charAt(0));
    		   break;
    	   case "unit3isfractqty": case "unit3IsFractQty": 
    		   item.setUnit1isfractqty(value.charAt(0));
    		   break;	
    	   case "unit3ispackunit": case "unit3IsPackUnit": 
    		   item.setUnit1ispackunit(value.charAt(0));
    		   break;
    	   case "unit3upc": case "unit3Upc": 
    		   item.setBaseupc(Long.valueOf(value));
    		   break;   	   
    	   case "unit3wgtperunit": case "unit3WgtPerUnit": 
    		   item.setUnit3wgtperunit(new BigDecimal(value));
    		   break;
    		   
    	   case "unit4unit": case "unit4Unit":
    		   item.setUnit1unit(value);
    		   break;
    	   case "unit4ratio": case "unit4Ratio":
    		   item.setUnit1unit(value);
    		   break; 	   
    	   case "unit4isstdsell": case "unit4IsStdsell": 
    		   item.setUnit1isstdsell(value.charAt(0));
    		   break;	
    	   case "unit4isstdor": case "unit4IsStdOrder": 
    		   item.setUnit1isstdord(value.charAt(0));
    		   break;
    	   case "unit4isfractqty": case "unit4IsFractQty": 
    		   item.setUnit1isfractqty(value.charAt(0));
    		   break;	
    	   case "unit4ispackunit": case "unit4IsPackUnit": 
    		   item.setUnit1ispackunit(value.charAt(0));
    		   break;
    	   case "unit4upc": case "unit4Upc": 
    		   item.setBaseupc(Long.valueOf(value));
    		   break; 
    	   case "unit4wgtperunit": case "unit4WgtPerUnit": 
    		   item.setUnit4wgtperunit(new BigDecimal(value));
    		   break;
    		   
   		
    	  /*---------New feature -------*/ 			
    	   case "status":
    		   item.getImsNewFeature().setStatus(Status.instanceOf(value));
    		   break;
    	   case "grade":
    		   item.getImsNewFeature().setGrade(Grade.instanceOf(value));
    	       break;	 
    	   case "mpsCode": case "mps":
    		   item.getImsNewFeature().setMpsCode(MpsCode.instanceOf(value));
    	       break;    
    	   case "designstyle": case "designStyle":
    		   item.getImsNewFeature().setDesignStyle(DesignStyle.instanceOf(value));
    		   break;
    	   case "designlook": case "designLook":
    		   item.getImsNewFeature().setDesignLook(DesignLook.instanceOf(value));
    		   break;    
    	   case "icon":
    		   item.getImsNewFeature().setIcon(Icon.instanceOf(value));
    		   break;
    	   case "body":
    		   item.getImsNewFeature().setBody(Body.instanceOf(value));
    	       break;	 
    	   case "edge":
    		   item.getImsNewFeature().setEdge(Edge.instanceOf(value));
    	       break;    
    	   case "surfacetype": case "surfaceType":
    		   item.getImsNewFeature().setSurfaceType(SurfaceType.instanceOf(value));
    		   break;
    	   case "surfacefinish": case "surfaceFinish":
    		   item.getImsNewFeature().setSurfaceFinish(SurfaceFinish.instanceOf(value));
    		   break;  
    	   case "surfaceapplication": case "surfaceApplication":
    		   item.getImsNewFeature().setSurfaceApplication(SurfaceApplication.instanceOf(value));
    		   break; 	   
    	   case "launcheddate": case "launchedDate":
    		   item.getImsNewFeature().setLaunchedDate(new Date(value));
    		   break;		  
    		   
    	   /*----------- test -----------*/	   
    	   case "waterAbsorption":
    		   item.setWaterAbsorption(Float.parseFloat(value));
    		   break;	   
    	   case "scratchResistance":
    		   item.setScratchResistance(Float.parseFloat(value));
    		   break;
    	   case "frostResistance":
    		   item.setFrostResistance(value.charAt(0));
    		   break;
    	   case "chemicalResistance":
    		   item.setChemicalResistance(value.charAt(0));
    		   break;	   
    	   case "peiAbrasion":
    		   item.setPeiAbrasion(Float.parseFloat(value));
    		   break;	   
    	   case "scofWet":
    		   item.setScofWet(Float.parseFloat(value));
    		   break;
    	   case "scofDry":
    		   item.setScofDry(Float.parseFloat(value));
    		   break;	   
    	   case "breakingStrengt":
    		   item.setBreakingStrength(Integer.parseInt(value));
    		   break;	  
    	   case "restricted": 
    		   item.setRestricted(value.charAt(0));
    		   break;
    	   case "warpage": 
    		   item.setWarpage(value.charAt(0));
    		   break;
    	   case "wedging": 
    		   item.setWedging(value.charAt(0));
    		   break;
    	   case "greenfriendly": 
    		   item.setGreenfriendly(value.charAt(0));
    		   break;
    	   case "thermalShock": 
    		   item.setThermalShock(value.charAt(0));
    		   break;
    	   case "dcof":
    		   item.setDcof(Float.parseFloat(value));
    		   break;	   
    	   case "moh":
    		   item.setMoh(Float.parseFloat(value));
    		   break;	   
    	   case "bondStrength":
    		   item.setBondStrength(value);
    	       break;
    	   case "brStrength":  case "breakingStrength":
    		   item.setBreakingStrength(Integer.parseInt(value));
    	       break;	    
    	   case "leadPoint":
    		   item.setLeadPoint(value);
    	       break;	
    	       
    
    	   /*-------- application ------*/	   
    	   case "residential":
    		   item.setResidential(value);
    		   break;	
    	   case "commercial":
    		   item.setCommercial(value);
    		   break;	   
    	   default:
    		  // throw new IllegalArgumentException("Invalid filed name. Please check the field name. Field name is case senstive: " + field);
    		   System.out.println("Field: "+ field + " is not supported");
    		   //log it
    	}	
    	
    	return item;
    	
    }
    
    private static Item generateSizeQuery(Item item, String sizes){
    	boolean lengthXwidth = true;
    	String[] sizeArray = sizes.split(",");
    	String length, width;
    	for(String size : sizeArray){
    		String[] lengthAndWidth = size.split("X");
    		if(lengthXwidth){
    		   length = lengthAndWidth[0];
    	       width = lengthAndWidth[1];
    		}
    		else{
    		   length = lengthAndWidth[1];
     	       width = lengthAndWidth[0];	
    		}
    		//Item.setLength(length);
 		    //Item.setWidth(width);
    		item.setNmLength(Float.parseFloat(length));
 		    item.setNmWidth(Float.parseFloat(width));
    	}
    	return item;
    }
    
    
	public static Item buildInsertSqlStatment(MultivaluedMap<String, String> queryParams){
		Item Item = new Item();
		
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator it = set.iterator();
		String key, value = null;
		while(it.hasNext()) {
		   	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	key = (String)entry.getKey();
		   	key = Character.toUpperCase(key.charAt(0)) + key.substring(1); 
		   	String methodName = "Set"+key+"()";
		   	
		   	value = ((List<String>)entry.getValue()).get(0);
		} 	
			  	
		return Item;
	}
	
	public static String generateInsertSqlStatment(String tableName, MultivaluedMap<String, String> queryParams){
		Item item = new Item();
		
		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();
		columns.append("INSERT INTO "+ tableName + " (");
		values.append(" VALUES(");
		String end = ")";
		int i = 0;
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator it = set.iterator();
		String key, value = null;
		while(it.hasNext()) {
			i += 1;
		   	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	key = (String)entry.getKey();
		   	value = ((List<String>)entry.getValue()).get(0);
		   	if(i == set.size()) {
		   		columns.append(key);
			   	values.append(value);
		   	}
		   	else {
		   	    columns.append(key).append(",");
		   	    values.append(value).append(",");
		   	}    
		} 	
		columns.append(")");
		values.append(end);
		columns.append(values);
		  	
		return columns.toString();
	}

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
        	 //(item.getVendorlandedbasecost() == null) ||
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
	
	public static Item buildVendorInfo(MultivaluedMap<String, String> queryParams, Item item){
	
		List<Vendor> vendors = item.getVendors();
	    if(vendors.isEmpty()){
	    	Vendor vendor = new Vendor();
	    }
	    
		Set<Map.Entry<String, List<String>>> set = queryParams.entrySet(); 
		
		Iterator<Entry<String, List<String>>> it = set.iterator(); 
		String key, value = null;
		while(it.hasNext()) {
			try{
		   	   Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	   key = (String)entry.getKey();
		   	 
		   	   //key = Character.toUpperCase(key.charAt(0)) + key.substring(1); 
		   	   value = ((List<String>)entry.getValue()).get(0);
		   	   setParameter(item, key, value);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		} 
		//ImsNewFeature newFeature = item.getImsNewFeature();
		//if(newFeature != null && newFeature.getItemcd() == null) 
			//newFeature.setItemcd(item.getItemcd());
		
		//newFeature.setCreatedDate(new Date());
		//newFeature.setItem(item);
		//item.setImsNewFeature(newFeature);
				  	
		return item;
	}
}
