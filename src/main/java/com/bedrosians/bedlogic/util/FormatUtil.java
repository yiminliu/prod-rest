package com.bedrosians.bedlogic.util;

import java.math.BigDecimal;
import java.util.Date;

import com.bedrosians.bedlogic.domain.item.Item;


public class FormatUtil {
  
	public static String process(String data){
		
		if (data == null)
			return new String("");
		else {
			data = data.trim(); 
		    return new String(data);
		}    
	}
	
    public static Character process(Character data){
		
		if (data == null || Character.isSpaceChar(data))
			return new Character(' ');
		else {
			    return Character.toString(data).trim().charAt(0);
		}    
	}

    public static Byte process(Byte data){
		Byte newByte = (byte)Integer.parseInt("0");
		if (data != null)
			newByte = (byte)Integer.parseInt(String.valueOf(data).trim());
	    return newByte;
	}
    
	public static Integer process(Integer data){
		Integer newInt = Integer.valueOf(0);
		if (data != null)
			newInt = Integer.valueOf((String.valueOf(data).trim()));
	    return newInt;
	}
	
	public static Long process(Long data){
		Long newData = Long.valueOf(0);
		if (data != null)
			newData = Long.valueOf((String.valueOf(data).trim()));
	    return newData;
	}
	
	public static Float process(Float data){
		Float newData = Float.valueOf(0); 
		if (data != null)
			newData = Float.valueOf((String.valueOf(data).trim()));
	    return Math.round(newData * 100)/100.00f;
	}
	
	public static BigDecimal process(BigDecimal data){
		BigDecimal newData = BigDecimal.valueOf(0);
		if (data != null)
			//newData = BigDecimal.valueOf((String.valueOf(data).trim()));
			newData = data;
	    return newData.setScale(4, BigDecimal.ROUND_HALF_EVEN);
	}
	   
	public static Date process(Date date) {
					
		if (date == null) {
			//Date emptyDate = new Date();
			//emptyDate.setTime(0);
			//return emptyDate;
			return null;
		}	
		else
			return date;
	}
	
	public static Item process(Item item){
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			User user = (User)auth.getPrincipal();
		
		   //if(!auth.getPrincipal().getAuthorities().containsAll(Role.getRolesAbove(Role.ROLE_ADMIN, true))) {
		   if(!Role.getRolesAbove(Role.ROLE_ADMIN, true).contains(user.getGreatestAuthority())) {
		      //item..setVendorlandedbasecost(null);
		      item.setCost(null);
		      item.setListpricemarginpct(null);
		      //item.setPoincludeinvendorcost(null);
		      item.getPrice().setMinimalMarginPct(null);
		      item.getPrice().setPriceMarginPct(null);   
		   }
		}   
		//Buyer buyer = item.getBuyer();
		//process(item.getBuyer().getBuyer());
		*/
		item.setItemcode(process(item.getItemcode()));
		if(item.getNewIconSystem() == null)
		   item.setNewIconSystem(ImsResultUtil.parseIcons(item.getIconsystem()));	
		if(item.getPriorVendor() != null)
		   ImsResultUtil.parsePriorVendor(item);	
		if(item.getPrice() != null)
			item.getPrice().setPriceunit(item.getStandardSellUnit());	
		//if(item.getNewColorHueSystem() == null || item.getNewColorHueSystem().isEmpty())
		//   item.setNewColorHueSystem(ImsResultUtil.parseColorCategory(item.getColorhues()));
		return item;
		/*
		newItem.setAbccd(process(item.getAbccd()));
		newItem.setCategory(process(item.getCategory()));
		newItem.setColorCategory(process(item.getColorCategory()));
		newItem.setColor(process(item.getColor()));
		newItem.setDescription(process(item.getDescription()));
		newItem.setDirectship(process(item.getDirectship()));
		newItem.setDropdate(process(item.getDropdate()));
		newItem.setDutypct(process(item.getDutypct()));
		newItem.setFulldesc(process(item.getFulldesc()));
		newItem.setIcons(process(item.getIcons()));
		newItem.setInactivecd(process(item.getInactivecd()));
		newItem.setInventoryItemCode(process(item.getInventoryItemCode()));
		newItem.setItemcd2(process(item.getItemcd2()));
		newItem.setItemCode(process(item.getItemCode()));
		newItem.setItemdesc2(process(item.getItemdesc2()));
		newItem.setItemgroupnbr(process(item.getItemgroupnbr()));
		newItem.setItemtypecd(process(item.getItemtypecd()));
		newItem.setLeadtime(process(item.getLeadtime()));
		newItem.setLength(process(item.getLength()));
		newItem.setListpricemarginpct(process(item.getListpricemarginpct()));
		newItem.setLottype(process(item.getLottype()));
		//newItem.setMaterialCategory(process(item.getMaterialCategory()));
        //newItem.setMaterialClassCode(process(item.getMaterialClassCode()));
		//newItem.setMaterialStyle(process(item.getMaterialStyle()));
		//newItem.setMaterialType(process(item.getMaterialType()));
		newItem.setMaterialFeature(process(item.getMaterialFeature()));
		newItem.setNominalLength(process(item.getNominalLength()));
		newItem.setNominalThickness(process(item.getNominalThickness()));
		newItem.setNominalWidth(process(item.getNominalWidth()));		
		newItem.setNotes1(process(item.getNotes1()));
		newItem.setNotes2(process(item.getNotes2()));
		newItem.setNotes3(process(item.getNotes3()));
		newItem.setOffShade(process(item.getOffShade()));
		newItem.setOrigin(process(item.getOrigin()));
		newItem.setPoNotes(process(item.getPoNotes()));
		newItem.setPrintlabel(process(item.getPrintlabel()));
		newItem.setPriorlastupdated(process(item.getPriorlastupdated()));
		newItem.setProductline(process(item.getProductline()));
		newItem.setSeriesName((process(item.getSeriesName())));		
		newItem.setShadeVariation(process(item.getShadeVariation()));
		newItem.setShowOnAlysedwards(process(item.getShowOnAlysedwards()));
		newItem.setShowOnWebsite(process(item.getShowOnWebsite()));
		newItem.setSizeUnits(process(item.getSizeUnits()));
		newItem.setSubtype(process(item.getSubtype()));
		newItem.setThickness(process(item.getThickness()));
		newItem.setThicknessUnit(process(item.getThicknessUnit()));
		newItem.setType(process(item.getType()));
		newItem.setUpdatecd(process(item.getUpdatecd()));
		newItem.setVendordiscpct(process(item.getVendordiscpct()));
		newItem.setVendordiscpct2(process(item.getVendordiscpct2()));
		newItem.setVendordiscpct3(process(item.getVendordiscpct3()));	
		newItem.setVendorfob(process(item.getVendorfob()));
		newItem.setVendorfreightratecwt(process(item.getVendorfreightratecwt()));
		newItem.setVendorLandedBaseCost(process(item.getVendorLandedBaseCost()));
		newItem.setVendorlistprice(process(item.getVendorlistprice()));
		newItem.setVendormarkuppct(process(item.getVendormarkuppct()));
		newItem.setVendornbr(process(item.getVendornbr()));
		newItem.setVendornbr1(process(item.getVendornbr1()));
		newItem.setVendornbr2(process(item.getVendornbr2()));
		newItem.setVendornbr3(process(item.getVendornbr3()));
		newItem.setVendornetprice(process(item.getVendornetprice()));
		newItem.setVendorpriceunit(process(item.getVendorpriceunit()));
		newItem.setVendorroundaccuracy(process(item.getVendorroundaccuracy()));
		newItem.setVendorxrefcd(process(item.getVendorxrefcd()));	
		newItem.setWidth(process(item.getWidth()));
		*/
	}
	
	/*
	public static Object process(Object obj){
		String newString = "";
		if (obj != null) {
		   if(obj.getClass().isInstance(String.class)) {
			  return process((String)obj);
		   }
		   else if(obj.getClass().isInstance(Integer.class)) {
			   return process((Integer)obj);
		   }
		   else if(obj.getClass().isInstance(Long.class)) {
			   return process((Long)obj);
		   }
		   else if(obj.getClass().isInstance(Float.class)) {
			   return process((Float)obj);
		   }
		   else if(obj.getClass().isInstance(Date.class)) {
			   return process((Date)obj);
		   }
		}   
		return newString;
	}
	*/		
}
