package com.bedrosians.bedlogic.util;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import com.bedrosians.bedlogic.domain.item.ColorHue;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.Note;
import com.bedrosians.bedlogic.domain.item.ItemVendor;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.DesignLook;
import com.bedrosians.bedlogic.domain.item.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;
import com.bedrosians.bedlogic.domain.item.enums.Body;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.bedrosians.bedlogic.domain.item.enums.Edge;
import com.bedrosians.bedlogic.domain.item.enums.OriginCountry;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;

public class ImsQueryUtil {

	public static String getValue(MultivaluedMap<String, String> queryParams, String searchKey) {	
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator<Entry<String, List<String>>> it = set.iterator(); 
		String key = null;
		while(it.hasNext()) {
		   	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	key = (String)entry.getKey();
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
	
	public static String getItemCode(MultivaluedMap<String, String> queryParams){
    	return getValue(queryParams, "itemcode") != null ? getValue(queryParams, "itemcode") : 
    	  	   getValue(queryParams, "itemCode") != null ? getValue(queryParams, "itemCode") :
    		   getValue(queryParams, "itemcd") != null ? getValue(queryParams, "itemcd") : 
    		   getValue(queryParams, "itemCd") != null ? getValue(queryParams, "itemCd") :
    		   null;    		         
    }
	
	public static int getMaxResults(MultivaluedMap<String, String> queryParams){
		int maxResults = 0;
		String data = getValue(queryParams, "maxResults");
		if(data != null && !data.isEmpty()){
			try {
			    maxResults = Integer.parseInt(data);
			}
			catch(NumberFormatException e){
				//e.printStackTrace();
			}
		}
		return maxResults;
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

    /*
    public static Item buildItemFromJsonObjectForInsert(Item item, JSONObject inputJsonObj) throws BedDAOBadParamException{
    
    	ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    	try{
    	 //item = mapper.readValue(inputJsonObj.toString(), Item.class);
    	  item = mapper.readValue(inputJsonObj.toString(), Item.class);
    	}
    	catch(JsonParseException e){
			e.printStackTrace();
			throw new BedDAOBadParamException("Json parse exception occured: "+ e.getMessage()); 
	   	}
    	catch(JsonMappingException e){
			e.printStackTrace();
			throw new BedDAOBadParamException("Json mapping exception occured: "+ e.getMessage()); 
	   	}
    	catch(Exception e){
			e.printStackTrace();
		}

		return item;
	}
  */  
    public static Item buildItemFromJsonObjectForInsert(Item item, JSONObject inputJsonObj) throws BedDAOBadParamException{
        
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
		for(ItemVendor vendor : item.getNewVendorSystem()){
			if(vendor.getItemVendorId().getItemCode() == null)
			   vendor.getItemVendorId().setItemCode(item.getItemcode());
		}
		//for(ColorHue colorHue : item.getNewColorHueSystem()){
		//	if(colorHue.getItem() == null)
		//		colorHue.setItem(item);
		//}
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
		for(ItemVendor vendor : item.getNewVendorSystem()){
			if(vendor.getItemVendorId().getItemCode() == null)
			vendor.getItemVendorId().setItemCode(item.getItemcode());
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
		for(ItemVendor vendor : item.getNewVendorSystem()){
			if(vendor.getItemVendorId().getItemCode() == null)
			vendor.getItemVendorId().setItemCode(item.getItemcode());
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
    	String itemCode = null;    	
    	switch(field){

    	   /*------- basic info ---------*/ 
    	   case "itemcd": case "itemcode": case "itemCode":
    		   item.setItemcode(value.toUpperCase());
    		   itemCode = value;
      		   break;
    	   case "description": case "itemdesc1":
    		   item.getItemdesc().setItemdesc1(value);
    		   break;
    	   case "seriesname":  case "seriesName":
    		   item.getSeries().setSeriesname(value);
    		   break;
    	   case "seriescolor":  case "seriesColor": case "color":
        	   item.getSeries().setSeriescolor(value);
        	   break;	   
      	   case "colorcategory": case "colorCategory":
    		   item.setColorcategory(value.toUpperCase());
    		   //saveColorHue(item, value.toUpperCase()); //update just adds new entry and old entries still remain
    		   break;	   
    	   case "colorhue": case "colorHue": case "colorhues": case "colorHues":
    		   //saveColorHue(item, value.toUpperCase());
    		   item.setColorcategory(value.toUpperCase());
    		   break;	   
    	   case "category": case "itemcategory":
    		   item.setItemcategory(value);
    		   break;
    	   case "type":
    		   item.setType(value);
    		   break;
       	   case "itemtypecd": case "itemtypeCode": case "itemTypeCode":
    		   item.setItemtypecd(value.charAt(0));
    		   break;
       	   case "origin": case "countryorigin":
  		       item.setCountryorigin(value);
  		       break;	   
    	   case "showonwebsite": case "showOnWebsite":
    		   item.setShowonwebsite(value.charAt(0));
    		   break;	
    	   case "purchaser": case "productManager": case "productmanager":
    		   item.getPurchasers().setPurchaser(value);
    		   break;	
    	   case "purchaser2": case "buyer":
    		   item.getPurchasers().setPurchaser2(value);
    		   break;
    	   case "taxclass": case "taxClass": case "itemtaxclass": case "itemTaxClass":
    		   item.setTaxclass(value.charAt(0));	   
    		   break;
    	   case "inactivecd": case "inactivecode": case "inactiveCode":
    		   item.setInactivecode(value);
    		   break;	
    	   case "shadevariation": case "shadeVariation":
    		   item.setShadevariation(value);
    		   break;		
    	   case "inventoryitemcd": case "inventoryItemcd": case "inventoryItemCode":
    		   item.setInventoryitemcd(value);
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
    		   item.getItemdesc().setItemdesc2(value);
    		   break;
    	   case "fulldesc": case "fullDesc": case "fulldescription": case "fullDescription": case "itemfulldesc": case "itemFullDesc":
    		   item.getItemdesc().setFulldesc(value);
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
    		   item.getDimensions().setLength(value);
    		   break;
    	   case "width":
    		   item.getDimensions().setWidth(value);
    		   break;
    	   case "thickness":
    		   item.getDimensions().setThickness(value);
    		   break;
    	   case "nmLength": case "nominallength": case "nominalLength":
    		   item.getDimensions().setNominallength(Float.valueOf(value));
    		   break;
    	   case "nmWidth": case "nominalwidth": case "nominalWidth":
    		   item.getDimensions().setNominalwidth(Float.valueOf(value));
    		   break;
    	   case "nmThickness": case "nominalthickness": case "nominalThickness":
    		   item.getDimensions().setNominalthickness(Float.valueOf(value));
    		   break;	
    	   case "sizeunits": case "sizeUnits":
    		   item.getDimensions().setSizeunits(value);
    		   break;
    	   case "thicknessunit": case "thicknessUnit":
    		   item.getDimensions().setThicknessunit(value);
    		   break;	
    		   
    	/*----- material info -------*/
    	   case "mfeature": case "mFeature": case "materialfeature": case "materialFeature":
    		   item.getMaterial().setMaterialfeature(value);
    		   break;	
    	   case "mattype": case "matType": case "materialtype": case "materialType":
    		   item.getMaterial().setMaterialtype(value);
    		   break;			   
    	   case "matcategory": case "matCategory": case "materialcategory": case "materialCategory":
    		   item.getMaterial().setMaterialcategory(value);
    		   break;	   
    	   case "matstyle": case "matStyle": case "materialstyle": case "materialStyle":
    		   item.getMaterial().setMaterialstyle(value);
    		   break;	
    	   case "materialclass": case "materialclassCd": case "materialClassCd": case "materialClassCode":
    		   item.getMaterial().setMaterialclass(value);
    		   break; 	   
    			
    		 //------- price info --------//	
    	       case "sellprice": case "sellPrice": //case "price": 
    		   item.getPrice().setSellprice(new BigDecimal(value));
    		   break;	
    	   case "listPrice": case "listprice":
    		   item.getPrice().setListprice(new BigDecimal(value));
    		   break;	
    	   //case "price":
    		//   item.getPrice().setPrice(new BigDecimal(value));
    		//   break;	   
    	   case "futurePrice": case "futuresell":
    		   item.getPrice().setFuturesell(new BigDecimal(value));
    		   break;	
    	   case "priorPrice": case "priorsellprice":
    		   item.getPrice().setPriorsellprice(new BigDecimal(value));
    		   break;
    	   case "pricegroup": case "priceGroup":
    		   item.getPrice().setPricegroup(value);
    		   break;  	   
    	   case "tempPrice": case "tempprice":
    		   item.getPrice().setTempprice(new BigDecimal(value));
    		   break;
    	   case "tempdatefrom":  case "tempDateFrom":
    		   item.getPrice().setTempdatefrom(new Date(value));
    		   break;	   
    	   case "tempdatethru":  case "tempDateThrough":
    		   item.getPrice().setTempdatethru(new Date(value));
    		   break;	   	  
    	   case "sellpricemarginpct": case "pricemarginpct": case "priceMarginPct":
    		   item.getPrice().setSellpricemarginpct(Float.parseFloat(value));
    		   break;		   
    	   case "minmarginpct": case "minimalmarginpct": case "minimalMarginPct":
    		   item.getPrice().setMinmarginpct(Float.parseFloat(value));
    		   break;	
    	   case "sellpriceroundaccuracy": case "priceroundaccuracy": case "priceRoundAccuracy":
    		   item.getPrice().setSellpriceroundaccuracy(Integer.parseInt(value));
    		   break;
    	   case "listpricemarginpct": case "listPriceMarginPct":
    		   item.getPrice().setListpricemarginpct(Float.parseFloat(value));
    		   break;
    	   case "priorlistprice": case "priorListPrice":
    		   item.getPrice().setPriorlistprice(new BigDecimal(value));
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
    	   case "priorvendordiscpct": case "priorVendorDiscPct": case "priorvendordiscpct1": case "priorVendorDiscPct1":
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
  /*  	   case "vendorId": case "vendornbr1":
    		   item.getVendors().setVendornbr1(Integer.parseInt(value));
    		   item.getNewVendorSystem().get(0).getItemVendorId().setVendorId(Integer.parseInt(value));
    		   item.getNewVendorSystem().get(0).setVendorOrder(1);  //as default, it will be overwritten if "vendorOrder' is passed in
    		   if(itemCode != null)
    		      item.getNewVendorSystem().get(0).getItemVendorId().setItemCode(itemCode);
    		   break; 
    	   case "xrefid": case "vendorxrefcd":
    		   item.getVendors().setVendorxrefcd(value);
    		   item.getNewVendorSystem().get(0).setVendorXrefId(value);
    		   break; 
    	   case "vendorOrder": case "vendororder": 
    		   item.getNewVendorSystem().get(0).setVendorOrder(Integer.parseInt(value));
    		   break;
    	   case "vendorpriceunit": case "vendorPriceUnit":
    		   item.getVendors().setVendorpriceunit(value);
    		   item.getNewVendorSystem().get(0).setVendorPriceUnit(value);
    		   break; 
    	   case "vendorfob": case "vendorFob":
        	   item.getVendors().setVendorfob(value);
        	   item.getNewVendorSystem().get(0).setVendorFob(value);
        	   break; 	   
    	   case "vendorlistprice": case "vendorListPrice": case "vendor_list_price":
    		   item.getVendors().setVendorlistprice(new BigDecimal(value));
    		   item.getNewVendorSystem().get(0).setVendorListPrice(new BigDecimal(value));
    		   break;
    	   case "vendorDutyPct": case "dutypct": case "dutyPct":
    		   item.getVendors().setDutypct(Float.parseFloat(value));
    		   item.getNewVendorSystem().get(0).setDutyPct(Float.parseFloat(value));
    		   break;
    	   case "vendornetprice":  case "vendorNetPrice":
    		   item.getVendors().setVendornetprice(new BigDecimal(value));
    		   item.getNewVendorSystem().get(0).setVendorNetPrice(new BigDecimal(value));
    		   break;
    	   case "leadTime": case "leadtime":
    		   item.getVendors().setLeadtime(Integer.parseInt(value));
    		   item.getNewVendorSystem().get(0).setLeadTime(Integer.parseInt(value));
    		   break; 
    	   case "vendormarkuppct": case "vendorMarkupPct":
    		   item.getVendors().setVendormarkuppct(Float.parseFloat(value));
    		   item.getNewVendorSystem().get(0).setVendorMarkupPct(Float.parseFloat(value));
    		   break;	
    	   case "vendordiscpct": case "vendordiscpct1": case "vendorDiscPct": case "vendorDiscountPct":
    		   item.getVendors().setVendordiscpct(Float.parseFloat(value));
    		   item.getNewVendorSystem().get(0).setVendorDiscountPct(Float.parseFloat(value));
    		   break;	
    	   case "vendordiscpct2": case "vendorDiscPct2": case "vendorDiscountPct2":
    		   item.getVendors().setVendordiscpct2(Float.parseFloat(value));
    		   break;
    	   case "vendordiscpct3": case "vendorDiscPct3": case "vendorDiscountPct3":
    		   item.getVendors().setVendordiscpct3(Float.parseFloat(value));
    		   break;
    	   case "vendorlandedbasecost": case "vendorLandedBaseCost":
    		   item.getVendors().setVendorlandedbasecost(new BigDecimal(value));
    		   item.getNewVendorSystem().get(0).setVendorLandedBaseCost(new BigDecimal(value));
        	   break;	   
    	   case "vendorfreightratecwt": case "vendorFreightRateCwt":
    		   item.getVendors().setVendorfreightratecwt(Float.parseFloat(value));
    		   item.getNewVendorSystem().get(0).setVendorFreightRateCwt(Float.parseFloat(value));
    		   break;
    	   case "vendorroundaccuracy": case "vendorRoundAccuracy": case "vendorpriceroundaccuracy":
    		   item.getVendors().setVendorroundaccuracy(Integer.parseInt(value));
    		   item.getNewVendorSystem().get(0).setVendorPriceRoundAccuracy(Integer.parseInt(value));
    		   break; 
    	   case "vendornbr":
    		   item.getVendors().setVendornbr(Integer.parseInt(value));
    		   break; 
    	   case "vendornbr2":
    		   item.getVendors().setVendornbr2(Integer.parseInt(value));
    		   break;
    	   case "vendornbr3":
    		   item.getVendors().setVendornbr3(Integer.parseInt(value));
    		   break;   		   
    	   case "priorlastupdated":
    		   item.setPriorlastupdated(new Date(value));
    		   break;						
    	   case "v2_vendorId": case "v2_vendornbr1":
    		   item.getNewVendorSystem().get(1).getItemVendorId().setVendorId(Integer.parseInt(value));
    		   item.getNewVendorSystem().get(1).setVendorOrder(2);
    		   if(itemCode != null)
     		      item.getNewVendorSystem().get(1).getItemVendorId().setItemCode(itemCode);
    		   break; 
    	   case "v2_xrefid": case "v2_vendorxrefcd": 
    		   item.getNewVendorSystem().get(1).setVendorXrefId(value);
    		   break; 
    	   case "v2_vendorOrder": case "v2_vendororder": 
    		   item.getNewVendorSystem().get(1).setVendorOrder(Integer.parseInt(value));
    		   break;	   
    	   case "v2_vendorpriceunit": case "v2_vendorPriceUnit":
    		   item.getNewVendorSystem().get(1).setVendorPriceUnit(value);
    		   break; 
    	   case "v2_vendorfob": case "v2_vendorFob":
        	   item.getNewVendorSystem().get(1).setVendorFob(value);
        	   break; 	   
    	   case "v2_vendorlistprice": case "v2_vendorListPrice": case "v2_vendor_list_price":
     		   item.getNewVendorSystem().get(1).setVendorListPrice(new BigDecimal(value));
    		   break;
    	   case "v2_vendordiscountPct": case "v2_vendorDiscountPct":
    		   item.getNewVendorSystem().get(1).setVendorDiscountPct(Float.parseFloat(value));
    		   break;
    	   case "v2_vendorDutyPct": case "v2_dutypct": case "v2_dutyPct":
      		   item.getNewVendorSystem().get(1).setDutyPct(Float.parseFloat(value));
    		   break;
    	   case "v2_vendornetprice":  case "v2_vendorNetPrice":
    		   item.getNewVendorSystem().get(1).setVendorNetPrice(new BigDecimal(value));
    		   break;
    	   case "v2_leadTime": case "v2_leadtime":
     		   item.getNewVendorSystem().get(1).setLeadTime(Integer.parseInt(value));
    		   break; 
    	   case "v2_vendormarkuppct": case "v2_vendorMarkupPct":
    		   item.getNewVendorSystem().get(1).setVendorMarkupPct(Float.parseFloat(value));
    		   break;	
    	   case "v2_vendordiscpct": case "v2_vendordiscpct1": case "v2_vendorDiscPct":
    		   item.getNewVendorSystem().get(1).setVendorDiscountPct(Float.parseFloat(value));
    		   break;	  
    	   case "v2_vendorlandedbasecost": case "v2_vendorLandedBaseCost":
    		   item.getNewVendorSystem().get(1).setVendorLandedBaseCost(new BigDecimal(value));
        	   break;	   
    	   case "v2_vendorfreightratecwt": case "v2_vendorFreightRateCwt":
    		   item.getNewVendorSystem().get(1).setVendorFreightRateCwt(Float.parseFloat(value));
    		   break;
    	   case "v2_vendorroundaccuracy": case "v2_vendorRoundAccuracy": case "v2_vendorpriceroundaccuracy":
    		   item.getNewVendorSystem().get(1).setVendorPriceRoundAccuracy(Integer.parseInt(value));
    		   break; 
    	   case "v3_vendorId": case "v3_vendornbr1":
     		   item.getNewVendorSystem().get(2).getItemVendorId().setVendorId(Integer.parseInt(value));
     		  item.getNewVendorSystem().get(2).setVendorOrder(3);
    		   if(itemCode != null)
     		      item.getNewVendorSystem().get(2).getItemVendorId().setItemCode(itemCode);
    		   break; 
    	   case "v3_xrefid": case "v3_vendorxrefcd": 
    		   item.getNewVendorSystem().get(2).setVendorXrefId(value);
    		   break; 
    	   case "v3_vendorOrder": case "v3_vendororder": 
    		   item.getNewVendorSystem().get(2).setVendorOrder(Integer.parseInt(value));
    		   break;
    	   case "v3_vendorpriceunit": case "v3_vendorPriceUnit":
    		   item.getNewVendorSystem().get(2).setVendorPriceUnit(value);
    		   break; 
    	   case "v3_vendorfob": case "v3_vendorFob":
        	   item.getNewVendorSystem().get(2).setVendorFob(value);
        	   break; 	   
    	   case "v3_vendorlistprice": case "v3_vendorListPrice": case "v3_vendor_list_price":
     		   item.getNewVendorSystem().get(2).setVendorListPrice(new BigDecimal(value));
    		   break;
    	   case "v3_vendordiscountPct": case "v3_vendorDiscountPct":
    		   item.getNewVendorSystem().get(2).setVendorDiscountPct(Float.parseFloat(value));
    		   break;
    	   case "v3_vendorDutyPct": case "v3_dutypct": case "v3_dutyPct":
      		   item.getNewVendorSystem().get(2).setDutyPct(Float.parseFloat(value));
    		   break;
    	   case "v3_vendornetprice":  case "v3_vendorNetPrice":
    		   item.getNewVendorSystem().get(2).setVendorNetPrice(new BigDecimal(value));
    		   break;
    	   case "v3_leadTime": case "v3_leadtime":
     		   item.getNewVendorSystem().get(2).setLeadTime(Integer.parseInt(value));
    		   break; 
    	   case "v3_vendormarkuppct": case "v3_vendorMarkupPct":
    		   item.getNewVendorSystem().get(2).setVendorMarkupPct(Float.parseFloat(value));
    		   break;	
    	   case "v3_vendordiscpct": case "v3_vendordiscpct1": case "v3_vendorDiscPct":
    		   item.getNewVendorSystem().get(2).setVendorDiscountPct(Float.parseFloat(value));
    		   break;	
    	   case "v3_vendorlandedbasecost": case "v3_vendorLandedBaseCost":
    		   item.getNewVendorSystem().get(2).setVendorLandedBaseCost(new BigDecimal(value));
        	   break;	   
    	   case "v3_vendorfreightratecwt": case "v3_vendorFreightRateCwt":
    		   item.getNewVendorSystem().get(2).setVendorFreightRateCwt(Float.parseFloat(value));
    		   break;
    	   case "v3_vendorroundaccuracy": case "v3_vendorRoundAccuracy": case "v3_vendorpriceroundaccuracy":
    		   item.getNewVendorSystem().get(2).setVendorPriceRoundAccuracy(Integer.parseInt(value));
    		   break; 
         */		   
    		 //--------- units ----------//
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
    		   else if("Not Pass".equalsIgnoreCase(value) || "Not Passed".equalsIgnoreCase(value))
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
    	   	   	
    	   /*-------- usage ------*/	   
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
       	   case "madeInCountry": case "madeincountry": case "madeinCountry":
    		   item.getIconDescription().setMadeInCountry(OriginCountry.instanceOf(value));
    		   break;   
    	   case "adaAccessibility": case "adaaccessibility":		  
        	   item.getIconDescription().setAdaAccessibility(Boolean.valueOf(normalizeBooleanInput(value)));
        	   break;
    	   case "exteriorProduct": case "exteriorproduct":
    		   item.getIconDescription().setExteriorProduct(Boolean.valueOf(normalizeBooleanInput(value)));
    		   break;
    	   case "throughColor": case "throughcolor": case "thruColor":
    		   item.getIconDescription().setThroughColor(Boolean.valueOf(normalizeBooleanInput(value)));
    		   break;
	       case "inkJet": case "inkjet": case "ink_jet":
		       item.getIconDescription().setInkJet(Boolean.valueOf(normalizeBooleanInput(value)));
		       break;
           case "glazed": 
	           item.getIconDescription().setGlazed(Boolean.valueOf(normalizeBooleanInput(value)));
	           break;
           case "colorBody": case "colorbody":
		       item.getIconDescription().setColorBody(Boolean.valueOf(normalizeBooleanInput(value)));
		       break;    
           case "unGlazed": case "unglazed":
	           item.getIconDescription().setUnglazed(Boolean.valueOf(normalizeBooleanInput(value)));
	           break;
           case "rectifiedEdge": case "rectifiededge":
	           item.getIconDescription().setRectifiedEdge(Boolean.valueOf(normalizeBooleanInput(value)));
	           break;
           case "chiseledEdge": case "chiselededge":
               item.getIconDescription().setChiseledEdge(Boolean.valueOf(normalizeBooleanInput(value)));
               break;
           case "versaillesPattern": case "versaillespattern":
	           item.getIconDescription().setVersaillesPattern(Boolean.valueOf(normalizeBooleanInput(value)));
	           break;
           case "recycled": 
	           item.getIconDescription().setRecycled(Boolean.valueOf(normalizeBooleanInput(value)));
	           break;
           case "preRecycled": case "prerecycled": 
	           item.getIconDescription().setPreRecycled(Boolean.valueOf(normalizeBooleanInput(value)));
	           break;
           case "postRecycled": case "postrecycled": 
	           item.getIconDescription().setPostRecycled(Boolean.valueOf(normalizeBooleanInput(value)));
	           break;
           case "leadpointIcon": case "leadpointicon": case "icon_leadPoint": case "icon_leadpoint":
               item.getIconDescription().setLeadPointIcon(Boolean.valueOf(normalizeBooleanInput(value)));
               break;
           case "greenfriendlyIcon": case "greenfriendlyicon": case "icon_greenFriendly": case "icon_greenfriendly": 
               item.getIconDescription().setGreenFriendlyIcon(Boolean.valueOf(normalizeBooleanInput(value)));
               break;
           case "coefficientOfFriction": case "coefficientoffriction":
               item.getIconDescription().setCoefficientOfFriction(Boolean.valueOf(normalizeBooleanInput(value)));
               break;    
	   	   
    	   /*--------- notes ----------*/
    	   case "ponotes": case "poNotes":
    		   item.getNotes().setPonotes(value);
    		   if(item.getNewNoteSystem() != null && item.getNewNoteSystem().get(0) != null)
    		   item.getNewNoteSystem().get(0).setNote(value);
    		   break;
           case "note1": case "notes1":
    		   item.getNotes().setNotes1(value);
    		   if(item.getNewNoteSystem() != null && item.getNewNoteSystem().get(1) != null)
        		   item.getNewNoteSystem().get(1).setNote(value);
    		   break;
    	   case "note2": case "notes2":
    		   item.getNotes().setNotes2(value);
    		   if(item.getNewNoteSystem() != null && item.getNewNoteSystem().get(3) != null)
        		   item.getNewNoteSystem().get(3).setNote(value);
    		   break;
    	   case "note3": case "notes3":
    		   item.getNotes().setNotes3(value);
    		   if(item.getNewNoteSystem() != null && item.getNewNoteSystem().get(2) != null)
        		   item.getNewNoteSystem().get(2).setNote(value);
    		   break;	   
    	   case "ponote": case "poNote":
    		   if(item.getNewNoteSystem() == null || item.getNewNoteSystem().get(0) == null){
     			  Note poNote = new Note("po");
     		      poNote.setNote(value);
     	       }  
     	       else {	
        		   item.getNewNoteSystem().get(0).setNote(value);
     	       }	   
    		   item.getNotes().setPonotes(value);
    		   break;
    	   case "buyernote": case "buyerNote":
    		   if(item.getNewNoteSystem() == null || item.getNewNoteSystem().get(1) == null){
    			  Note buyerNote = new Note("buyer");
    		      buyerNote.setNote(value);
    	       }  
    	       else {	   	   
    		      item.getNewNoteSystem().get(1).setNote(value);
    		   }   
               item.getNotes().setNotes1(value);
    		   break;
    	   case "invoicenote": case "invoiceNote":
    		   if(item.getNewNoteSystem() == null || item.getNewNoteSystem().get(2) == null){
     			  Note invoiceNote = new Note("invoice");
     		      invoiceNote.setNote(value);
     	       }  
     	       else {	
     	    	   item.getNewNoteSystem().get(2).setNote(value);
     	       }
    		   item.getNotes().setNotes3(value);
    		   break;	
    	   case "internalnote": case "internalNote":
    		   if(item.getNewNoteSystem() == null || item.getNewNoteSystem().get(4) == null){
     			  Note internalNote = new Note("internal");
     		      internalNote.setNote(value);
     	       }  
     	       else {	
     	    	   item.getNewNoteSystem().get(4).setNote(value);
     	       }
    		   break;
    	   case "additionalnote": case "additionalNote":
    		   if(item.getNewNoteSystem() == null || item.getNewNoteSystem().get(3) == null){
     			  Note internalNote = new Note("additional");
     		      internalNote.setNote(value);
     	       }  
     	       else {	
     	    	   item.getNewNoteSystem().get(3).setNote(value);
     	       }
    		   item.getNotes().setNotes2(value);
    		   break;	   
    		 //----- similar items -----//
    	   case "similarItemcd1": case "similaritemcd1":
    		   item.getRelateditemcodes().setSimilaritemcd1(value);
    		   break;
    	   case "similarItemcd2": case "similaritemcd2":
    		   item.getRelateditemcodes().setSimilaritemcd2(value);
    		   break;
    	   case "similarItemcd3": case "similaritemcd3":
    		   item.getRelateditemcodes().setSimilaritemcd3(value);
    		   break;
    	   case "similarItemcd4": case "similaritemcd4":
    		   item.getRelateditemcodes().setSimilaritemcd4(value);
    		   break;
    	   case "similarItemcd5": case "similaritemcd5":
    		   item.getRelateditemcodes().setSimilaritemcd5(value);
    		   break;
    	   case "similarItemcd6": case "similaritemcd6":
    		   item.getRelateditemcodes().setSimilaritemcd6(value);
    		   break;
    	   case "similarItemcd7": case "similaritemcd7":
    		   item.getRelateditemcodes().setSimilaritemcd7(value);
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
    		item.getDimensions().setNominallength(Float.parseFloat(length));
 		    item.getDimensions().setNominalwidth(Float.parseFloat(width));
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
	
		Set<ItemVendor> vendors = item.getNewVendorSystem();
	    if(vendors.isEmpty()){
	    	ItemVendor vendor = new ItemVendor();
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
	
	private static  String normalizeBooleanInput(String value){
	    if("Y".equalsIgnoreCase(value) || "Yes".equalsIgnoreCase(value))
			   value = "true";
		    else if("N".equalsIgnoreCase(value) || "No".equalsIgnoreCase(value))
			   value = "false";
		return value; 
	}
	
	//public static void saveColorHue(Item item, String value){
	//	if(value != null && !value.isEmpty()) {
	//		item.setNewColorHueSystem(new ArrayList<ColorHue>());
	//		if(!value.contains(":") )
	//			item.addNewColorHueSystem(new ColorHue(value));	
	//		else {
	//			for(String color : value.trim().split(":")){
  	 //               if(color != null && !color.isEmpty())
	//	               item.addNewColorHueSystem(new ColorHue(value));	
	//			}    
	//	    }	   
	//	}
	//}
	
	//public static void saveColorCategory(Item item, String value){
	//	if(value != null && !value.isEmpty()){
	//		String colorCategory = item.getColorhues();
		    //item.setColorcategory((colorCategory == null && !colorCategory.isEmpty()) ?  value.toUpperCase() : colorCategory + ":" + value.toUpperCase());
	//		item.setColorhues(value.toUpperCase());
	//	}
	//}
	
}
