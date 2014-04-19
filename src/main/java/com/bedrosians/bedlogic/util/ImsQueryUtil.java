package com.bedrosians.bedlogic.util;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jettison.json.JSONObject;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.Vendor;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.DesignLook;
import com.bedrosians.bedlogic.domain.item.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.item.enums.TaxClass;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;
import com.bedrosians.bedlogic.domain.item.enums.Body;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.bedrosians.bedlogic.domain.item.enums.Edge;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;

public class ImsQueryUtil {

	public static String getValue(MultivaluedMap<String, String> queryParams, String searchKey) {
		
		
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator<Entry<String, List<String>>> it = set.iterator(); 
		String key = null;
		while(it.hasNext()) {
		   	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	key = (String)entry.getKey();
		   	//key = Character.toUpperCase(key.charAt(0)) + key.substring(1); 
		   	if(key.equalsIgnoreCase(searchKey))
		   	   return ((List<String>)entry.getValue()).get(0);
		} 
		return null;
	}
	
	public static boolean containsKey(MultivaluedMap<String, String> queryParams, String searchKey) {
		Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
	    for(Entry<String, List<String>> entry : set){
	    	if(entry.getKey().equalsIgnoreCase(searchKey))
	    	   return true;
	    }
	    return false;
	 }
	
	public static boolean containsAnyKey(MultivaluedMap<String, String> queryParams, List<String> keys) {
		Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
	    for(Entry<String, List<String>> entry : set){
	    	for(String key : keys){
	    	   if(entry.getKey().equalsIgnoreCase(key))
	    	      return true;
	    	}   
	    }
	    return false;
	}
	
	public static boolean containsAnyKey(JSONObject inputJsonObj, List<String> keys) {
		Iterator<String> iterator = inputJsonObj.keys(); 
	    while(iterator.hasNext()){
	      	if(keys.contains((String)iterator.next()))
	    	   return true; 
	    }
	    return false;
	}
	
	public static String getItemCode(JSONObject inputJsonObj){
    	String itemCode = null;
    	try {
    		itemCode = inputJsonObj.optString("itemcode") != ""?  inputJsonObj.optString("itemcode") : 
    		         inputJsonObj.optString("itemCode") != ""?  inputJsonObj.optString("itemCode") : 
    		         inputJsonObj.optString("itemcd") != ""?  inputJsonObj.optString("itemcd") :  	
    		         inputJsonObj.optString("itemId");	 
    	}
    	catch(Exception e){
    		e.printStackTrace(); // just swallow it
    	}
    	return itemCode;	         
    }
	
	public static int determineNumberOfVendors(MultivaluedMap<String, String> queryParams) {
		int numberOfVendors = 0;
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
	    for(Entry<String, List<String>> entry : set){
	    	if(entry.getKey().startsWith("vendor") && numberOfVendors < 1)
	    		numberOfVendors = 1;	
	       	if((entry.getKey().startsWith("vendor2") || entry.getKey().startsWith("v2_")) && numberOfVendors < 2)
	    		numberOfVendors = 2;
	    	if(entry.getKey().startsWith("vendor3") || entry.getKey().startsWith("v3_")){
	    		numberOfVendors = 3;
	    		break;
	    	}	
	    }
	    return numberOfVendors;
	}
    
    public static int determineNumberOfVendors(JSONObject inputJsonObj) {
		int numberOfVendors = 0;
	    Iterator iterator = inputJsonObj.keys();
	    String key = "";
	    while(iterator.hasNext()){
	    	key = (String)iterator.next();
	    	if(key.startsWith("vendor") && numberOfVendors < 1)
	    		numberOfVendors = 1;	
	       	if((key.startsWith("vendor2") || key.startsWith("v2_")) && numberOfVendors < 2)
	    		numberOfVendors = 2;
	    	if(key.startsWith("vendor3") || key.startsWith("v3_")){
	    		numberOfVendors = 3;
	    		break;
	    	}	
	    }
	    return numberOfVendors;
	}

    public static Item buildItemFromJsonObjectForInsert(Item item, JSONObject inputJsonObj){
    	Iterator<String> itrator = inputJsonObj.keys();
   		String key, value = null;
		while(itrator.hasNext()) {
			try{
		   	   key = (String)itrator.next();
		   	   value = (String)inputJsonObj.get(key);
		   	   setParameter(item, key, value);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		} 
		for(Vendor vendor : item.getVendors()){
			if(vendor.getItemVendorId().getitemcd() == null)
			vendor.getItemVendorId().setItemcd(item.getItemcode());
		}
		return item;
	}
    
    public static Item buildItemFromJsonObject(Item item, JSONObject inputJsonObj, String action){
		Iterator<String> itrator = inputJsonObj.keys();
   		String key, value = null;
		while(itrator.hasNext()) {
			try{
		   	   key = (String)itrator.next();
		   	   if("update".equalsIgnoreCase(action) && (key.equalsIgnoreCase("itemcd") || 
		   			                                    key.equalsIgnoreCase("itemcode") || 
		   			                                    key.equalsIgnoreCase("itemCode") || 
		   			                                    key.equalsIgnoreCase("itemId")))
			   	   continue;
		   	   value = (String)inputJsonObj.get(key);
		   	   setParameter(item, key, value);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		} 
		for(Vendor vendor : item.getVendors()){
			if(vendor.getItemVendorId().getitemcd() == null)
			vendor.getItemVendorId().setItemcd(item.getItemcode());
		}
		return item;
	}
 
    public static Item buildItemFromJsonString(String jsonString){
    	return (Item)JsonUtil.jsonStringToPOJO(jsonString, new Item());
    }
    	
    
	public static Item buildItemForInsert(Item item, MultivaluedMap<String, String> queryParams){
			
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator<Entry<String, List<String>>> it = set.iterator(); 
		String key, value = null;
		while(it.hasNext()) {
			try{
		   	   Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	   key = (String)entry.getKey();
		   	   value = ((List<String>)entry.getValue()).get(0);
		   	   setParameter(item, key, value);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		} 
		for(Vendor vendor : item.getVendors()){
			if(vendor.getItemVendorId().getitemcd() == null)
			vendor.getItemVendorId().setItemcd(item.getItemcode());
		}
		return item;
	}
	
	public static Item buildItemForUpdate(Item item, MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException {
		
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator<Entry<String, List<String>>> it = set.iterator(); 
		String key, value = null;
		while(it.hasNext()) {
		   	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	key = (String)entry.getKey();
		   	if(key.equalsIgnoreCase("itemcd") || key.equalsIgnoreCase("itemcode") || key.equalsIgnoreCase("itemCode"))
		   	   continue; 
		   	value = ((List<String>)entry.getValue()).get(0);
		   	setParameter(item, key, value);
		} 	
			  	
		return item;
	}
	
	
	/*This method is used to set item field values for creating or updating item
	 * 
	 */
    private static Item setParameter(Item item, String field, String value) throws BedDAOBadParamException{
    	String itemcd = null;    	
   /* 	List<Vendor> vendors = item.getVendors();
   
    	Vendor	vendor1 = vendors.isEmpty() ? new Vendor() : vendors.get(0);
    	Vendor	item.getVendors().get(2) = vendors.size() <= 1? new Vendor() : vendors.get(1);
    	Vendor	item.getVendors().get(2) = vendors.size() <= 2? new Vendor() : vendors.get(2);
    		
    	if(!vendors.contains(vendor1))
    		item.addVendor(vendor1);
    	if(!vendors.contains(vendor2))
    		item.addVendor(vendor2);
    	if(!vendors.contains(vendor3))
    		item.addVendor(vendor3);
    */	
    	switch(field){

    	   /*------- basic info ---------*/ 
    	   case "itemcd": case "itemcode": case "itemCode":
    		   item.setItemcode(value);
    		   itemcd = value;
      		   break;
    	   case "description": case "itemdesc1":
    		   item.setItemdesc1(value);
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
    		   item.setItemtypecd(value.charAt(0));
    		   break;
       	   case "origin":
  		       item.setOrigin(value);
  		       break;	   
    	   case "showonwebsite": case "showOnWebsite":
    		   item.setShowonwebsite(value.charAt(0));
    		   break;	
    	   case "purchaser": case "productManager": case "productmanager":
    		   item.getPurchaser().setPurchaser(value);
    		   break;	
    	   case "purchaser2": case "buyer":
    		   item.getPurchaser().setPurchaser(value);
    		   break;
    	   case "taxclass": case "taxClass": case "itemtaxclass": case "itemTaxClass":
    		   item.setTaxClass(value.charAt(0));	   
    		   break;
    	   case "inactivecd": case "inactive_code":
    		   item.setInactivecd(value);
    		   break;	
    	   case "shadevariation": case "shadeVariation":
    		   item.setShadevariation(value);
    		   break;		
    	   case "inventoryitemcd": case "inventoryItemcd": case "inventoryItemCode":
    		   item.setInventoryItemcd(value);
    		   break;	
    	   case "showonalysedwards": case "showOnAlysedwards":
    		   item.setShowonalysedwards(value.charAt(0));
    		   break;	
    	   case "offshade": case "offShade":
    		   item.setOffshade(value.charAt(0));
    		   break;		
    	   case "printlabel": case "printLabel":
    		   item.setPrintlabel(value.charAt(0));
    		   break;	
       	   case "lottype": case "lotType":
    		   item.setLottype(value);
    		   break;	
    	   case "abccd": case "abcCd": case "abccode": case "abcCode":
    		   item.setAbccd(value);
    		   break;	
    	  case "itemdesc2":  case "itemDesc2": 
    		   item.setItemdesc2(value);
    		   break;
    	   case "fulldesc": case "fullDesc":
    		   item.setFulldesc(value);
    		   break;	   
    	   case "subtype": case "subType":
    		   item.setSubtype(value);
    		   break;
    	   case "itemcd2": case "itemCd2": case "itemcode2": case "itemCode2":
    		   item.setItemcd2(value);
    		   break;
    	   case "productline": case "productLine":
    		   item.setProductline(value);
    		   break;	   
     	   case "icons":
    		   item.setIconsystem(value);
    		   break;
    	   case "directship": case "directShip":
    		   item.setDirectship(value.charAt(0));
    		   break;	   
    	   case "itemgroupnbr": case "itemGroupNbr":
    		   item.setItemgroupnbr(Integer.parseInt(value));
    		   break;
    	   case "dropdate":  case "dropDate":
    		   item.setDropdate(new Date(value));
    		   break;
    	   case "updatecd":
    		   item.setUpdatecd(value);
    		   break;		
    			
    	/*-------- dimension --------*/	   
    	   case "size":
    		   item = generateItemSizeQuery(item, value);
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
    	   case "nmLength":  case "nominalLength":
    		   item.setNmLength(Float.valueOf(value));
    		   break;
    	   case "nmWidth":  case "nominalWidth":
    		   item.setNmWidth(Float.valueOf(value));
    		   break;
    	   case "nmThickness": case "nominalThickness":
    		   item.setNmThickness(Float.valueOf(value));
    		   break;	
    	   case "sizeunits": case "sizeUnits":
    		   item.setSizeunits(value);
    		   break;
    	   case "thicknessunit": case "thicknessUnit":
    		   item.setThicknessunit(value);
    		   break;	
    		   
    	/*----- material info -------*/
    	   case "mfeature": case "mFeature": case "materialfeature": case "materialFeature":
    		   item.setMaterialfeature(value);
    		   break;	
    	   case "mattype": case "matType": case "materialtype": case "materialType":
    		   item.setMaterialtype(value);
    		   break;			   
    	   case "matcategory": case "matCategory": case "materialcategory": case "materialCategory":
    		   item.setMaterialcategory(value);
    		   break;	   
    	   case "matstyle": case "matStyle": case "materialstyle": case "materialStyle":
    		   item.setMaterialstyle(value);
    		   break;	
    	   case "materialclass": case "materialclassCd": case "materialClassCd": case "materialClassCode":
    		   item.setMaterialclass(value);
    		   break; 	   
    			
    		 //------- price info --------//	
    	   case "price": case "sellprice": case "sellPrice":
    		   item.getPrices().setSellprice(new BigDecimal(value));
    		   break;	
    	   case "listPrice": case "lisrprice":
    		   item.getPrices().setListprice(new BigDecimal(value));
    		   break;	   
    	   case "futurePrice": case "futuresell":
    		   item.getPrices().setFuturesell(new BigDecimal(value));
    		   break;	
    	   case "priorPrice": case "priorsellprice":
    		   item.getPrices().setPriorsellprice(new BigDecimal(value));
    		   break;
    	   case "pricegroup": case "priceGroup":
    		   item.getPrices().setPricegroup(value);
    		   break;  	   
    	   case "tempPrice": case "tempprice":
    		   item.getPrices().setTempprice(new BigDecimal(value));
    		   break;
    	   case "tempdatefrom":  case "tempDateFrom":
    		   item.getPrices().setTempdatefrom(new Date(value));
    		   break;	   
    	   case "tempdatethru":  case "tempDateThrough":
    		   item.getPrices().setTempdatethru(new Date(value));
    		   break;	   	  
    	   case "sellpricemarginpct": case "pricemarginpct": case "priceMarginPct":
    		   item.getPrices().setSellpricemarginpct(Float.parseFloat(value));
    		   break;		   
    	   case "minmarginpct": case "minimalmarginpct": case "minimalMarginPct":
    		   item.getPrices().setMinmarginpct(Float.parseFloat(value));
    		   break;	
    	   case "sellpriceroundaccuracy": case "priceroundaccuracy": case "priceRoundAccuracy":
    		   item.getPrices().setSellpriceroundaccuracy(Integer.parseInt(value));
    		   break;
    	   case "listpricemarginpct": case "listPriceMarginPct":
    		   item.setListpricemarginpct(Float.parseFloat(value));
    		   break;
    	   case "priorlistprice": case "priorListPrice":
    		   item.getPrices().setPriorlistprice(new BigDecimal(value));
    		   break; 
    	   case "priorvendorpriceunit": case "priorVendorPriceUnit":
    		   item.getPriorVendor().setPriorvendorpriceunit(value);
    	   case "priorvendorfob": case "priorVendorFob":
    		   item.getPriorVendor().setPriorvendorfob(value);	 
    		   break;
    	   case "priorvendorlandedbasecost": case "priorvendorLandedBaseCost":
    		   item.getPriorVendor().setPriorvendorlandedbasecost(new BigDecimal(value));
    		   break;	   
    	   case "priorvendorlistprice": case "priorVendorListPrice":
    		   item.getPriorVendor().setPriorvendorlistprice(new BigDecimal(value));
    		   break;
    	   case "priorvendornetprice": case "priorVendorNetPrice":
    		   item.getPriorVendor().setPriorvendornetprice(new BigDecimal(value));
    		   break;
    	   case "priorvendorfreightratecwt": case "priorVendorFreightRateCwt":
    		   item.getPriorVendor().setPriorvendorfreightratecwt(Float.parseFloat(value));
    		   break;
    	   case "priorvendordiscpct1": case "priorVendorDiscPct1":
    		   item.getPriorVendor().setPriorvendordiscpct1(Float.parseFloat(value));
    		   break;
    	   case "priorvendormarkuppct": case "priorVendorMarkupPct":
    		   item.getPriorVendor().setPriorvendormarkuppct(Float.parseFloat(value));
    		   break;
    	   case "priorvendorroundaccuracy": case "priorVendorRoundAccuracy":
    		   item.getPriorVendor().setPriorvendorroundaccuracy(Integer.parseInt(value));
    		   break;
    	   		
    	   /*---------- Cost ----------*/	 
    	   case "cost": case "cost1":
    		   item.getCost().setCost1(new BigDecimal(value));
    		   break;	
    	   case "poincludeinvendorcost": case "poIncludeinVendorCost":
    		   item.getCost().setPoincludeinvendorcost(value.charAt(0));
    		   break;	
    	   case "nonstockcostpct":
    		   item.getCost().setNonstockcostpct(Float.parseFloat(value));
    		   break;
    	   case "priorcost": case "priorCost":
    		   item.getCost().setPriorcost(new BigDecimal(value));
    		   break; 
    	   case "priorcost1": case "priorCost1":
    		   item.getCost().setPriorcost1(new BigDecimal(value));
    		   break;	   
    	   case "futurecost": case "futureCost":
    		   item.getCost().setFuturecost(new BigDecimal(value));
    		   break;   
    	   case "futurecost1": case "futureCost1":
    		   item.getCost().setFuturecost1(new BigDecimal(value));
    		   break;  
    		   
    		/*---------- Vendor ----------*/	
    	   case "vendorId": case "vendornbr1":
    		   item.setVendornbr1(Integer.parseInt(value));
    		   item.getVendors().get(0).getItemVendorId().setVendorId(Long.parseLong(value));
    		   if(itemcd != null)
    		      item.getVendors().get(0).getItemVendorId().setItemcd(itemcd);
    		   break; 
    	   case "xrefid": case "vendorxrefcd":
    		   item.setVendorxrefcd(value);
    		   item.getVendors().get(0).setVendorXrefId(value);
    		   break; 
    	   case "vendorOrder": case "vendororder": 
    		   item.getVendors().get(0).setVendorOrder(Integer.parseInt(value));
    		   break;
    	   case "vendorpriceunit": case "vendorPriceUnit":
    		   item.setVendorpriceunit(value);
    		   item.getVendors().get(0).setVendorPriceUnit(value);
    		   break; 
    	   case "vendorfob": case "vendorFob":
        	   item.setVendorfob(value);
        	   item.getVendors().get(0).setVendorFob(value);
        	   break; 	   
    	   case "vendorlistprice": case "vendorListPrice": case "vendor_list_price":
    		   item.setVendorlistprice(new BigDecimal(value));
    		   item.getVendors().get(0).setVendorListPrice(new BigDecimal(value));
    		   break;
    	   case "vendorDutyPct": case "dutypct": case "dutyPct":
    		   item.setDutypct(Float.parseFloat(value));
    		   item.getVendors().get(0).setDutyPct(Float.parseFloat(value));
    		   break;
    	   case "vendornetprice":  case "vendorNetPrice":
    		   item.setVendornetprice(new BigDecimal(value));
    		   item.getVendors().get(0).setVendorNetPrice(new BigDecimal(value));
    		   break;
    	   case "leadTime": case "leadtime":
    		   item.setLeadtime(Integer.parseInt(value));
    		   item.getVendors().get(0).setLeadTime(Integer.parseInt(value));
    		   break; 
    	   case "vendormarkuppct": case "vendorMarkupPct":
    		   item.setVendormarkuppct(Float.parseFloat(value));
    		   item.getVendors().get(0).setVendorMarkupPct(Float.parseFloat(value));
    		   break;	
    	   case "vendordiscpct": case "vendordiscpct1": case "vendorDiscPct": case "vendorDiscountPct":
    		   item.setVendordiscpct(Float.parseFloat(value));
    		   item.getVendors().get(0).setVendorDiscountPct(Float.parseFloat(value));
    		   break;	
    	   case "vendordiscpct2": case "vendorDiscPct2": case "vendorDiscountPct2":
    		   item.setVendordiscpct2(Float.parseFloat(value));
    		   break;
    	   case "vendordiscpct3": case "vendorDiscPct3": case "vendorDiscountPct3":
    		   item.setVendordiscpct3(Float.parseFloat(value));
    		   break;
    	   case "vendorlandedbasecost": case "vendorLandedBaseCost":
    		   item.setVendorLandedBaseCost(new BigDecimal(value));
        	   break;	   
    	   case "vendorfreightratecwt": case "vendorFreightRateCwt":
    		   item.setVendorfreightratecwt(Float.parseFloat(value));
    		   item.getVendors().get(0).setVendorFreightRateCwt(Float.parseFloat(value));
    		   break;
    	   case "vendorroundaccuracy": case "vendorRoundAccuracy": case "vendorpriceroundaccuracy":
    		   item.setVendorroundaccuracy(Integer.parseInt(value));
    		   item.getVendors().get(0).setVendorPriceRoundAccuracy(Integer.parseInt(value));
    		   break; 
    	   case "vendornbr":
    		   item.setVendornbr(Integer.parseInt(value));
    		   break; 
    	   case "vendornbr2":
    		   item.setVendornbr2(Integer.parseInt(value));
    		   break;
    	   case "vendornbr3":
    		   item.setVendornbr3(Integer.parseInt(value));
    		   break;   		   
    	   case "priorlastupdated":
    		   item.setPriorlastupdated(new Date(value));
    		   break;			
    			
    	   case "v2_vendorId": case "v2_vendornbr1":
    		   item.getVendors().get(1).getItemVendorId().setVendorId(Long.parseLong(value));
    		   if(itemcd != null)
     		      item.getVendors().get(1).getItemVendorId().setItemcd(itemcd);
    		   break; 
    	   case "v2_xrefid": case "v2_vendorxrefcd": 
    		   item.getVendors().get(1).setVendorXrefId(value);
    		   break; 
    	   case "v2_vendorOrder": case "v2_vendororder": 
    		   item.getVendors().get(1).setVendorOrder(Integer.parseInt(value));
    		   break;	   
    	   case "v2_vendorpriceunit": case "v2_vendorPriceUnit":
    		   item.getVendors().get(1).setVendorPriceUnit(value);
    		   break; 
    	   case "v2_vendorfob": case "v2_vendorFob":
        	   item.getVendors().get(1).setVendorFob(value);
        	   break; 	   
    	   case "v2_vendorlistprice": case "v2_vendorListPrice": case "v2_vendor_list_price":
     		   item.getVendors().get(1).setVendorListPrice(new BigDecimal(value));
    		   break;
    	   case "v2_vendordiscountPct": case "v2_vendorDiscountPct":
    		   item.getVendors().get(1).setVendorDiscountPct(Float.parseFloat(value));
    		   break;
    	   case "v2_vendorDutyPct": case "v2_dutypct": case "v2_dutyPct":
      		   item.getVendors().get(1).setDutyPct(Float.parseFloat(value));
    		   break;
    	   case "v2_vendornetprice":  case "v2_vendorNetPrice":
    		   item.getVendors().get(1).setVendorNetPrice(new BigDecimal(value));
    		   break;
    	   case "v2_leadTime": case "v2_leadtime":
     		   item.getVendors().get(1).setLeadTime(Integer.parseInt(value));
    		   break; 
    	   case "v2_vendormarkuppct": case "v2_vendorMarkupPct":
    		   item.getVendors().get(1).setVendorMarkupPct(Float.parseFloat(value));
    		   break;	
    	   case "v2_vendordiscpct": case "v2_vendordiscpct1": case "v2_vendorDiscPct":
    		   item.getVendors().get(1).setVendorDiscountPct(Float.parseFloat(value));
    		   break;	  
    	   case "v2_vendorlandedbasecost": case "v2_vendorLandedBaseCost":
    		   item.getVendors().get(1).setVendorLandedBaseCost(new BigDecimal(value));
        	   break;	   
    	   case "v2_vendorfreightratecwt": case "v2_vendorFreightRateCwt":
    		   item.getVendors().get(1).setVendorFreightRateCwt(Float.parseFloat(value));
    		   break;
    	   case "v2_vendorroundaccuracy": case "v2_vendorRoundAccuracy": case "v2_vendorpriceroundaccuracy":
    		   item.getVendors().get(1).setVendorPriceRoundAccuracy(Integer.parseInt(value));
    		   break; 
    		   
    	   case "v3_vendorId": case "v3_vendornbr1":
     		   item.getVendors().get(2).getItemVendorId().setVendorId(Long.parseLong(value));
    		   if(itemcd != null)
     		      item.getVendors().get(2).getItemVendorId().setItemcd(itemcd);
    		   break; 
    	   case "v3_xrefid": case "v3_vendorxrefcd": 
    		   item.getVendors().get(2).setVendorXrefId(value);
    		   break; 
    	   case "v3_vendorOrder": case "v3_vendororder": 
    		   item.getVendors().get(2).setVendorOrder(Integer.parseInt(value));
    		   break;
    	   case "v3_vendorpriceunit": case "v3_vendorPriceUnit":
    		   item.getVendors().get(2).setVendorPriceUnit(value);
    		   break; 
    	   case "v3_vendorfob": case "v3_vendorFob":
        	   item.getVendors().get(2).setVendorFob(value);
        	   break; 	   
    	   case "v3_vendorlistprice": case "v3_vendorListPrice": case "v3_vendor_list_price":
     		   item.getVendors().get(2).setVendorListPrice(new BigDecimal(value));
    		   break;
    	   case "v3_vendordiscountPct": case "v3_vendorDiscountPct":
    		   item.getVendors().get(2).setVendorDiscountPct(Float.parseFloat(value));
    		   break;
    	   case "v3_vendorDutyPct": case "v3_dutypct": case "v3_dutyPct":
      		   item.getVendors().get(2).setDutyPct(Float.parseFloat(value));
    		   break;
    	   case "v3_vendornetprice":  case "v3_vendorNetPrice":
    		   item.getVendors().get(2).setVendorNetPrice(new BigDecimal(value));
    		   break;
    	   case "v3_leadTime": case "v3_leadtime":
     		   item.getVendors().get(2).setLeadTime(Integer.parseInt(value));
    		   break; 
    	   case "v3_vendormarkuppct": case "v3_vendorMarkupPct":
    		   item.getVendors().get(2).setVendorMarkupPct(Float.parseFloat(value));
    		   break;	
    	   case "v3_vendordiscpct": case "v3_vendordiscpct1": case "v3_vendorDiscPct":
    		   item.getVendors().get(2).setVendorDiscountPct(Float.parseFloat(value));
    		   break;	
    	   case "v3_vendorlandedbasecost": case "v3_vendorLandedBaseCost":
    		   item.getVendors().get(1).setVendorLandedBaseCost(new BigDecimal(value));
        	   break;	   
    	   case "v3_vendorfreightratecwt": case "v3_vendorFreightRateCwt":
    		   item.getVendors().get(2).setVendorFreightRateCwt(Float.parseFloat(value));
    		   break;
    	   case "v3_vendorroundaccuracy": case "v3_vendorRoundAccuracy": case "v3_vendorpriceroundaccuracy":
    		   item.getVendors().get(2).setVendorPriceRoundAccuracy(Integer.parseInt(value));
    		   break; 
    		   
    		 /*--------- units ----------*/
    	   case "stdunit": case "std_unit": case "standardUnit":
    		   item.getUnits().setStdunit(value);
    		   break; 
    	   case "ordunit": case "ord_unit": case "OrderUnit":
    		   item.getUnits().setOrdunit(value);
    		   break; 	   
    	   case "stdratio": case "std_ratio": case "standardRatio":
    		   item.getUnits().setStdratio(Float.valueOf(value));
    		   break;	 
    	   case "ordratio": case "ord_ratio": case "OrderRatio":
    		   item.getUnits().setOrdratio(Float.valueOf(value));
    		   break;   
    	   case "baseunit": case "base_unit": case "BaseUnit":
    		   item.getUnits().setBaseunit(value);
    		   break; 	 
    	   case "baseisstdsell": case "baseIsStdsell": 
    		   item.getUnits().setBaseisstdsell(value.charAt(0));
    		   break;	
    	   case "baseisstdord": case "baseIsStdOrder": 
    		   item.getUnits().setBaseisstdord(value.charAt(0));
    		   break;
    	   case "baseisfractqty": case "baseIsFractQty": 
    		   item.getUnits().setBaseisfractqty(value.charAt(0));
    		   break;	
    	   case "baseispackunit": case "baseIsPackUnit": 
    		   item.getUnits().setBaseispackunit(value.charAt(0));
    		   break;
    	   case "baseupc": case "baseUpc": 
    		   item.getUnits().setBaseupc(Long.valueOf(value));
    		   break;   
    	   case "baseupcdesc": case "baseUpcDesc": 
    		   item.getUnits().setBaseupcdesc(value);
    		   break;   	   
    	   case "basevolperunit": case "baseVolPerUnit": 
    		   item.getUnits().setBasevolperunit(new BigDecimal(value));
    		   break;	
    	   case "basewgtperunit": case "baseWgtPerUnit": 
    		   item.getUnits().setBasewgtperunit(new BigDecimal(value));
    		   break;
    	   case "unit1unit": case "unit1_unit": case "unit1Unit":
    		   item.getUnits().setUnit1unit(value);
    		   break;
    	   case "unit1ratio": case "unit1Ratio":
    		   item.getUnits().setUnit1ratio(Float.parseFloat(value));
    		   break; 	   
    	   case "unit1isstdsell": case "unit1IsStdsell": 
    		   item.getUnits().setUnit1isstdsell(value.charAt(0));
    		   break;	
    	   case "unit1isstdord": case "unit1IsStdOrder": 
    		   item.getUnits().setUnit1isstdord(value.charAt(0));
    		   break;
    	   case "unit1isfractqty": case "unit1IsFractQty": 
    		   item.getUnits().setUnit1isfractqty(value.charAt(0));
    		   break;	
    	   case "unit1ispackunit": case "unit1IsPackUnit": 
    		   item.getUnits().setUnit1ispackunit(value.charAt(0));
    		   break;
    	   case "unit1upc": case "unit1Upc": 
    		   item.getUnits().setUnit1upc(Long.valueOf(value));
    		   break; 
    	   case "unit1upcdesc": case "unit1UpcDesc": 
    		   item.getUnits().setUnit1upcdesc(value);
    		   break;	   
    	   case "unit1wgtperunit": case "unit1WgtPerUnit": 
    		   item.getUnits().setUnit1wgtperunit(new BigDecimal(value));
    		   break;
    	   case "unit2unit": case "unit2Unit":
    		   item.getUnits().setUnit2unit(value);
    		   break;
    	   case "unit2ratio": case "unit2Ratio":
    		   item.getUnits().setUnit2ratio(Float.parseFloat(value));
    		   break; 	   
    	   case "unit2isstdsell": case "unit2IsStdsell": 
    		   item.getUnits().setUnit2isstdsell(value.charAt(0));
    		   break;	
    	   case "unit2isstdord": case "unit2IsStdOrder": 
    		   item.getUnits().setUnit2isstdord(value.charAt(0));
    		   break;
    	   case "unit2isfractqty": case "unit2IsFractQty": 
    		   item.getUnits().setUnit2isfractqty(value.charAt(0));
    		   break;	
    	   case "unit2ispackunit": case "unit2IsPackUnit": 
    		   item.getUnits().setUnit2ispackunit(value.charAt(0));
    		   break;
    	   case "unit2upc": case "unit2Upc": 
    		   item.getUnits().setUnit2upc(Long.valueOf(value));
    		   break;
    	   case "unit2upcdesc": case "unit2UpcDesc": 
    		   item.getUnits().setUnit2upcdesc(value);
    		   break;	   
    	   case "unit2wgtperunit": case "unit2WgtPerUnit": 
    		   item.getUnits().setUnit2wgtperunit(new BigDecimal(value));
    		   break;
    	   case "unit3unit": case "unit3Unit":
    		   item.getUnits().setUnit3unit(value);
    		   break;
    	   case "unit3ratio": case "unit3Ratio":
    		   item.getUnits().setUnit3ratio(Float.parseFloat(value));
    		   break; 	   
    	   case "unit3isstdsell": case "unit3IsStdsell": 
    		   item.getUnits().setUnit3isstdsell(value.charAt(0));
    		   break;	
    	   case "unit3isstdord": case "unit3IsStdOrder": 
    		   item.getUnits().setUnit3isstdord(value.charAt(0));
    		   break;
    	   case "unit3isfractqty": case "unit3IsFractQty": 
    		   item.getUnits().setUnit3isfractqty(value.charAt(0));
    		   break;	
    	   case "unit3ispackunit": case "unit3IsPackUnit": 
    		   item.getUnits().setUnit3ispackunit(value.charAt(0));
    		   break;
    	   case "unit3upc": case "unit3Upc": 
    		   item.getUnits().setUnit3upc(Long.valueOf(value));
    		   break;   	
    	   case "unit3upcdesc": case "unit3UpcDesc": 
    		   item.getUnits().setUnit3upcdesc(value);
    		   break;	   
    	   case "unit3wgtperunit": case "unit3WgtPerUnit": 
    		   item.getUnits().setUnit3wgtperunit(new BigDecimal(value));
    		   break;
    	   case "unit4unit": case "unit4Unit":
    		   item.getUnits().setUnit4unit(value);
    		   break;
    	   case "unit4ratio": case "unit4Ratio":
    		   item.getUnits().setUnit4ratio(Float.parseFloat(value));
    		   break; 	   
    	   case "unit4isstdsell": case "unit4IsStdsell": 
    		   item.getUnits().setUnit4isstdsell(value.charAt(0));
    		   break;	
    	   case "unit4isstdord": case "unit4IsStdOrder": 
    		   item.getUnits().setUnit4isstdord(value.charAt(0));
    		   break;
    	   case "unit4isfractqty": case "unit4IsFractQty": 
    		   item.getUnits().setUnit4isfractqty(value.charAt(0));
    		   break;	
    	   case "unit4ispackunit": case "unit4IsPackUnit": 
    		   item.getUnits().setUnit4ispackunit(value.charAt(0));
    		   break;
    	   case "unit4upc": case "unit4Upc": 
    		   item.getUnits().setUnit4upc(Long.valueOf(value));
    		   break; 
    	   case "unit4upcdesc": case "unit4UpcDesc": 
    		   item.getUnits().setUnit4upcdesc(value);
    		   break;	   
    	   case "unit4wgtperunit": case "unit4WgtPerUnit": 
    		   item.getUnits().setUnit4wgtperunit(new BigDecimal(value));
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
    	   case "warranty":
    		   item.getImsNewFeature().setWarranty(Integer.parseInt(value));
    	       break;
    	   case "recommendedGroutJointMin":
    		   item.getImsNewFeature().setRecommendedGroutJointMin(value);
    	       break;
    	   case "recommendedGroutJointMax":
    		   item.getImsNewFeature().setRecommendedGroutJointMax(value);
    	       break;    
    				   
    	   /*----------- test -----------*/	   
    	   case "waterabsorption": case "waterAbsorption":
    		   item.getTestSpecification().setWaterabsorption(Float.parseFloat(value));
    		   break;	   
    	   case "scratchresistance": case "scratchResistance":
    		   item.getTestSpecification().setScratchresistance(Float.parseFloat(value));
    		   break;
    	   case "frostresistance": case "frostResistance":
    		   if("Pass".equalsIgnoreCase(value) || "Passed".equalsIgnoreCase(value))
    		      item.getTestSpecification().setFrostresistance("P");
    		   else if("Not Pass".equalsIgnoreCase(value) || "Not Passed".equalsIgnoreCase(value) || "N".equalsIgnoreCase(value))
    			   item.getTestSpecification().setFrostresistance("N");
    		   else
    			   item.getTestSpecification().setFrostresistance(value);
    			   break;
    	   case "chemicalresistance": case "chemicalResistance":
    		   item.getTestSpecification().setChemicalresistance(value);
    		   break;	   
    	   case "peiabrasion": case "peiAbrasion":
    		   item.getTestSpecification().setPeiabrasion(Float.parseFloat(value));
    		   break;	   
    	   case "scofwet": case "scofWet":
    		   item.getTestSpecification().setScofwet(Float.parseFloat(value));
    		   break;
    	   case "scofdry": case "scofDry":
    		   item.getTestSpecification().setScofdry(Float.parseFloat(value));
    		   break;	   
    	   case "restricted": 
    		   item.getTestSpecification().setRestricted(value.charAt(0));
    		   break;
    	   case "warpage": 
    		   item.getTestSpecification().setWarpage(value.charAt(0));
    		   break;
    	   case "wedging": 
    		   item.getTestSpecification().setWedging(value.charAt(0));
    		   break;
    	   case "greenfriendly": 
    		   item.getTestSpecification().setGreenfriendly(value.charAt(0));
    		   break;
    	   case "thermalshock":  case "thermalShock": 
    		   item.getTestSpecification().setThermalshock(value.charAt(0));
    		   break;
    	   case "dcof":
    		   item.getTestSpecification().setDcof(Float.parseFloat(value));
    		   break;	   
    	   case "moh":
    		   item.getTestSpecification().setMoh(Float.parseFloat(value));
    		   break;	   
    	   case "bondstrength": case "bondStrength":
    		   item.getTestSpecification().setBondstrength(value);
    	       break;
    	   case "brStrength":  case "breakingstrength": case "breakingStrength":
    		   item.getTestSpecification().setBreakingstrength(Integer.parseInt(value));
    	       break;	    
    	   case "leadpoint": case "leadPoint":
    		   item.getTestSpecification().setLeadpoint(value);
    	       break;	
    	   case "preconsummer": case "preConsummer":
    		   item.getTestSpecification().setPreconsummer(Float.parseFloat(value));
    		   break;
    	   case "posconsummer": case "posConsummer":
    		   item.getTestSpecification().setPosconsummer(Float.parseFloat(value));
    		   break;
    	   	   	
    	   /*-------- applications ------*/	   
    	   case "residential":
    		   item.getApplications().setResidential(value);
    		   break;	
    	   case "commercial":
    		   item.getApplications().setCommercial(value);
    		   break;
    	   case "lightcommercial": case "lightCommercial":
    		   item.getApplications().setLightcommercial(value);
    		   break;
    	   //case "application": 
    	   //	   item.setApplication(value);
    	   //	   break;
    		   
    	   /*---------icons -----------*/
       	   case "originCountry": case "origincountry":
    		   item.getIconDescriptio().setOriginCountry(value);
    		   break;   
    	   case "adaAccessibility": case "adaaccessibility":
        	   item.getIconDescriptio().setAdaAccessibility(Boolean.valueOf(value));
        	   break;
    	   case "exteriorProduct": case "exteriorproduct":
    		   item.getIconDescriptio().setExteriorProduct(Boolean.valueOf(value));
    		   break;
    	   case "throughColor": case "throughcolor": case "thruColor":
    		   item.getIconDescriptio().setThroughColor(Boolean.valueOf(value));
    		   break;
	       case "colorBody": case "colorbody":
		       item.getIconDescriptio().setColorBody(Boolean.valueOf(value));
		       break;
	       case "inkJet": case "inkjet": case "ink_jet":
		       item.getIconDescriptio().setInkJet(Boolean.valueOf(value));
		       break;
           case "glazed": 
	           item.getIconDescriptio().setGlazed(Boolean.valueOf(value));
	           break;
           case "unGlazed": case "unglazed":
	           item.getIconDescriptio().setUnglazed(Boolean.valueOf(value));
	           break;
           case "rectifiedEdge": case "rectifiededge":
	           item.getIconDescriptio().setRectifiedEdge(Boolean.valueOf(value));
	           break;
           case "chiseledEdge": case "chiselededge":
               item.getIconDescriptio().setChiseledEdge(Boolean.valueOf(value));
               break;
           case "versaillesPattern": case "versaillespattern":
	           item.getIconDescriptio().setVersaillesPattern(Boolean.valueOf(value));
	           break;
           case "recycled": 
	           item.getIconDescriptio().setRecycled(Boolean.valueOf(value));
	           break;
           case "preRecycled": case "prerecycled": 
	           item.getIconDescriptio().setPreRecycled(Boolean.valueOf(value));
	           break;
           case "postRecycled": case "postrecycled": 
	           item.getIconDescriptio().setPostRecycled(Boolean.valueOf(value));
	           break;
           case "icon_leadPoint": case "icon_leadpoint":
               item.getIconDescriptio().setLeadPoint(Boolean.valueOf(value));
               break;
           case "icon_greenFriendly": case "icon_greenfriendly":
               item.getIconDescriptio().setGreenFriendly(Boolean.valueOf(value));
               break;
           case "coefficientOfFriction": case "coefficientoffriction":
               item.getIconDescriptio().setCoefficientOfFriction(Boolean.valueOf(value));
               break;    
	   	   
    	   /*--------- notes ----------*/
    	   case "ponotes": case "poNotes":
    		   item.setPoNotes(value);
    		   //item.getPoNote().setNote(value);
    		   if(item.getNotes() != null && item.getNotes().get(0) != null)
    		   item.getNotes().get(0).setNote(value);
    		   break;
           case "note1":
    		   item.setNotes1(value);
    		   //item.getBuyerNote().setNote(value);
    		   if(item.getNotes() != null && item.getNotes().get(1) != null)
        		   item.getNotes().get(0).setNote(value);
    		   break;
    	   case "note2":
    		   item.setNotes2(value);
    		   //item.getBuyerNote().setNote(value);
    		   if(item.getNotes() != null && item.getNotes().get(1) != null)
        		   item.getNotes().get(1).setNote(value);
    		   break;
    	   case "note3":
    		   item.setNotes3(value);
    		   //item.getInvoiceNote().setNote(value);
    		   if(item.getNotes() != null && item.getNotes().get(2) != null)
        		   item.getNotes().get(2).setNote(value);
    		   break;	   
    	   case "ponote": case "poNote":
    		   //item.getPoNote().setNote(value);
    		   if(item.getNotes() != null && item.getNotes().get(0) != null)
        		   item.getNotes().get(0).setNote(value);
    		   item.setPoNotes(value);
    		   break;
    	   case "buyernote": case "buyerNote":
    		   //item.getBuyerNote().setNote(value);
    		   if(item.getNotes() != null && item.getNotes().get(1) != null)
        		   item.getNotes().get(1).setNote(value);
    		   item.setNotes1(value);
    		   break;
    	   case "invoicenote": case "invoiceNote":
    		   //item.getPoNote().setNote(value);
    		   if(item.getNotes() != null && item.getNotes().get(2) != null)
        		   item.getNotes().get(2).setNote(value);
    		   item.setNotes3(value);
    		   break;	
    	   case "internalnote": case "internalNote":
    		   //item.getInternalNote().setNote(value);
    		   if(item.getNotes() != null && item.getNotes().get(3) != null)
        		   item.getNotes().get(3).setNote(value);
    		   break;
    		   
    		   
    		 //----- similar items -----//
    	   case "similarItemcd1": case "similaritemcd1":
    		   item.getSimilarItemCode().setSimilaritemcd1(value);
    		   break;
    	   case "similarItemcd2": case "similaritemcd2":
    		   item.getSimilarItemCode().setSimilaritemcd2(value);
    		   break;
    	   case "similarItemcd3": case "similaritemcd3":
    		   item.getSimilarItemCode().setSimilaritemcd3(value);
    		   break;
    	   case "similarItemcd4": case "similaritemcd4":
    		   item.getSimilarItemCode().setSimilaritemcd4(value);
    		   break;
    	   case "similarItemcd5": case "similaritemcd5":
    		   item.getSimilarItemCode().setSimilaritemcd5(value);
    		   break;
    	   case "similarItemcd6": case "similaritemcd6":
    		   item.getSimilarItemCode().setSimilaritemcd6(value);
    		   break;
    	   case "similarItemcd7": case "similaritemcd7":
    		   item.getSimilarItemCode().setSimilaritemcd7(value);
    		   break;
     	   default:
      		   System.out.println("Field: "+ field + " is not supported");
      	       throw new BedDAOBadParamException("Invalid property name. Please check the property name. Property name is case senstive: " + field);
    	}	
    	
    	return item;
    	
    }
    
    private static Item generateItemSizeQuery(Item item, String sizes){
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
