package com.bedrosians.bedlogic.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

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
			newData = data;
	    return newData.setScale(4, BigDecimal.ROUND_HALF_EVEN);
	}
	   
	public static Date process(Date date) {
					
		if (date == null) {
			return null;
		}	
		else
			return date;
	}
	
	public static Item process(Item item){
		if(item == null)
		   return null;	
		item.setItemcode(process(item.getItemcode()));	
		item.setAbccd(process(item.getAbccd()));
		item.setItemcategory(process(item.getItemcategory()));
		item.setColorcategory(process(item.getColorcategory()));
		item.setInactivecode(process(item.getInactivecode()));
		item.setInventoryitemcd(process(item.getInventoryitemcd()));
		item.setItemcd2(process(item.getItemcd2()));
		item.setItemgroupnbr(process(item.getItemgroupnbr()));
		item.setItemtypecd(process(item.getItemtypecd()));
		item.setLottype(process(item.getLottype()));
		item.setIconsystem(process(item.getIconsystem()));
        item.setCountryorigin(process(item.getCountryorigin()));
		item.setPrintlabel(process(item.getPrintlabel()));
		item.setPriorlastupdated(process(item.getPriorlastupdated()));
		item.setProductline(process(item.getProductline()));	
		item.setShadevariation(process(item.getShadevariation()));
		item.setSubtype(process(item.getSubtype()));
		item.setType(process(item.getType()));
		item.setUpdatecd(process(item.getUpdatecd()));
		if(item.getItemdesc() != null){
			item.getItemdesc().setFulldesc(process(item.getItemdesc().getFulldesc()));
			item.getItemdesc().setItemdesc1(process(item.getItemdesc().getItemdesc1()));
			item.getItemdesc().setItemdesc2(process(item.getItemdesc().getItemdesc2()));
		}
		if(item.getSeries() != null){
		   item.getSeries().setSeriesname(process(item.getSeries().getSeriesname()));
		   item.getSeries().setSeriescolor(process(item.getSeries().getSeriescolor()));
		}	
		if(item.getPurchasers() != null){
			item.getPurchasers().setPurchaser(process(item.getPurchasers().getPurchaser()));	
			item.getPurchasers().setPurchaser2(process(item.getPurchasers().getPurchaser2()));	
		}
		if(item.getApplications() != null){ 
		   item.getApplications().setCommercial(process(item.getApplications().getCommercial()));
		   item.getApplications().setLightcommercial(process(item.getApplications().getLightcommercial()));
		   item.getApplications().setResidential(process(item.getApplications().getResidential()));
		}
		if(item.getMaterial() != null){
			item.getMaterial().setMaterialcategory(process(item.getMaterial().getMaterialcategory()));
		    item.getMaterial().setMaterialclass(process(item.getMaterial().getMaterialclass()));
		    item.getMaterial().setMaterialstyle(process(item.getMaterial().getMaterialstyle()));
		    item.getMaterial().setMaterialtype(process(item.getMaterial().getMaterialtype()));
		    item.getMaterial().setMaterialfeature(process(item.getMaterial().getMaterialfeature()));
		}
		if(item.getDimensions() != null){
			//item.getDimensions().setNominallength(process(item.getDimensions().getNominallength()));
			//item.getDimensions().setNominalthickness(process(item.getDimensions().getNominalthickness()));
		    //item.getDimensions().setNominalwidth(process(item.getDimensions().getNominalwidth()));	
		    item.getDimensions().setLength(process(item.getDimensions().getLength()));
		    item.getDimensions().setThickness(process(item.getDimensions().getThickness()));
		    item.getDimensions().setWidth(process(item.getDimensions().getWidth()));
		    item.getDimensions().setSizeunits(process(item.getDimensions().getSizeunits()));
		    item.getDimensions().setThicknessunit(process(item.getDimensions().getThicknessunit()));
		}
		if(item.getPrice() != null){
			item.getPrice().setPriceunit(process(item.getPrice().getPriceunit()));
			item.getPrice().setPricegroup(process(item.getPrice().getPricegroup()));
		}
		if(item.getVendors() != null){
			item.getVendors().setVendorfob(process(item.getVendors().getVendorfob()));
			item.getVendors().setVendorxrefcd(process(item.getVendors().getVendorxrefcd()));
			item.getVendors().setVendorpriceunit(process(item.getVendors().getVendorpriceunit()));
			//item.getVendors().setDutypct(process(item.getVendors().getDutypct()));
		}
		if(item.getUnits() != null){
			item.getUnits().setOrdunit(process(item.getUnits().getOrdunit()));
			item.getUnits().setStdunit(process(item.getUnits().getStdunit()));
			item.getUnits().setBaseunit(process(item.getUnits().getBaseunit()));
			item.getUnits().setUnit1unit(process(item.getUnits().getUnit1unit()));
			item.getUnits().setUnit2unit(process(item.getUnits().getUnit2unit()));
			item.getUnits().setUnit3unit(process(item.getUnits().getUnit3unit()));
			item.getUnits().setUnit4unit(process(item.getUnits().getUnit4unit()));
			item.getUnits().setBaseupcdesc(process(item.getUnits().getBaseupcdesc()));
			item.getUnits().setUnit1upcdesc(process(item.getUnits().getUnit1upcdesc()));
			item.getUnits().setUnit2upcdesc(process(item.getUnits().getUnit2upcdesc()));
			item.getUnits().setUnit3upcdesc(process(item.getUnits().getUnit3upcdesc()));
			item.getUnits().setUnit4upcdesc(process(item.getUnits().getUnit4upcdesc()));
		}
		if(item.getTestSpecification() != null){
			item.getTestSpecification().setLeadpoint(process(item.getTestSpecification().getLeadpoint()));
			item.getTestSpecification().setBondstrength(process(item.getTestSpecification().getBondstrength()));
			item.getTestSpecification().setScratchstandard(process(item.getTestSpecification().getScratchstandard()));
			item.getTestSpecification().setBreakingstandard(process(item.getTestSpecification().getBreakingstandard()));
		}
		if(item.getNotes() != null){
		   item.getNotes().setPonotes(process(item.getNotes().getPonotes()));	
		   item.getNotes().setBuyernotes(process(item.getNotes().getBuyernotes()));
		   item.getNotes().setInternalnotes(process(item.getNotes().getInternalnotes()));
		   item.getNotes().setInvoicenotes(process(item.getNotes().getInvoicenotes()));
		}   
		if(item.getRelateditemcodes() != null){
			item.getRelateditemcodes().setSimilaritemcd1(process(item.getRelateditemcodes().getSimilaritemcd1()));
			item.getRelateditemcodes().setSimilaritemcd2(process(item.getRelateditemcodes().getSimilaritemcd2()));
			item.getRelateditemcodes().setSimilaritemcd3(process(item.getRelateditemcodes().getSimilaritemcd3()));
			item.getRelateditemcodes().setSimilaritemcd4(process(item.getRelateditemcodes().getSimilaritemcd4()));
			item.getRelateditemcodes().setSimilaritemcd5(process(item.getRelateditemcodes().getSimilaritemcd5()));
			item.getRelateditemcodes().setSimilaritemcd6(process(item.getRelateditemcodes().getSimilaritemcd6()));
		}
		if(item.getPriorVendor() != null){
		   item.getPriorVendor().setPriorvendorfob(process(item.getPriorVendor().getPriorvendorfob()));
		   item.getPriorVendor().setPriorvendorpriceunit(process(item.getPriorVendor().getPriorvendorpriceunit()));
		   item.getPriorVendor().setPriorvendorroundaccuracy(process(item.getPriorVendor().getPriorvendorroundaccuracy()));
		   //item.getPriorVendor().setPriorvendordiscpct1(process(item.getPriorVendor().getPriorvendordiscpct1()));	
		   //item.getPriorVendor().setPriorvendorfreightratecwt(process(item.getPriorVendor().getPriorvendorfreightratecwt()));
		   //item.getPriorVendor().setPriorvendorlandedbasecost(process(item.getPriorVendor().getPriorvendorlandedbasecost()));
		   //item.getPriorVendor().setPriorvendorlistprice(process(item.getPriorVendor().getPriorvendorlistprice()));
		   //item.getPriorVendor().setPriorvendormarkuppct(process(item.getPriorVendor().getPriorvendormarkuppct()));
		   //item.getPriorVendor().setPriorvendornetprice(process(item.getPriorVendor().getPriorvendornetprice()));
		}
		if(item.getIconDescription() == null)
		   item.setIconDescription(ImsDataUtil.convertLegacyIconsToIconCollection(item.getIconsystem()));	
	    if(item.getPriorVendor() != null)
		   ImsDataUtil.parsePriorVendor(item);	
		if(item.getPrice() != null)
		   item.getPrice().setPriceunit(ImsDataUtil.getStandardSellUnit(item));//item.getStandardSellUnit());	
		if(item.getUnits() != null)
		   item.setPackaginginfo(ImsDataUtil.getPackagingInfo(item));
		if(item.getApplications() != null)
		   item.setUsage(ImsDataUtil.convertApplicationsToUsage(item));
		if(item.getImsNewFeature() != null && item.getImsNewFeature().getMpsCode() == null)
	       item.getImsNewFeature().setMpsCode(ImsDataUtil.convertInactivecdToMpsCode(item.getInactivecode()));	
		if(item.getColorhues() == null || item.getColorhues().isEmpty())
		   item.setColorhues(new ArrayList<>(ImsDataUtil.convertColorCategoryToColorHueObjects(item)));
		if(item.getNewVendorSystem() != null && !item.getNewVendorSystem().isEmpty())
		   item.setNewVendorSystem(new ArrayList<>(new HashSet<>(item.getNewVendorSystem())));	
		return item;
		/*
		//item.setOffShade(process(item.getOffShade()));
		//item.setSeriesName((process(item.getSeriesName())));	
        //item.setShowonalysedwards(process(item.getShowonalysedwards()));
        //item.setShowonwebsite(process(item.getShowonwebsite()));
        //item.setSizeUnits(process(item.getSizeUnits()));
		//item.setThickness(process(item.getThickness()));
		//item.setThicknessUnit(process(item.getThicknessUnit()));
		//item.setColor(process(item.getColor()));
		//item.setVendordiscpct(process(item.getVendordiscpct()));
		//item.setVendordiscpct2(process(item.getVendordiscpct2()));
		//item.setVendordiscpct3(process(item.getVendordiscpct3()));	
		//item.setVendorfob(process(item.getVendorfob()));
		//item.setVendorfreightratecwt(process(item.getVendorfreightratecwt()));
		//item.setVendorLandedBaseCost(process(item.getVendorLandedBaseCost()));
		//item.setVendorlistprice(process(item.getVendorlistprice()));
		//item.setVendormarkuppct(process(item.getVendormarkuppct()));
		//item.setVendornbr(process(item.getVendornbr()));
		//item.setVendornbr1(process(item.getVendornbr1()));
		//item.setVendornbr2(process(item.getVendornbr2()));
		//item.setVendornbr3(process(item.getVendornbr3()));
		//item.setVendornetprice(process(item.getVendornetprice()));
		//item.setVendorpriceunit(process(item.getVendorpriceunit()));
		//item.setVendorroundaccuracy(process(item.getVendorroundaccuracy()));
		//item.setVendorxrefcd(process(item.getVendorxrefcd()));
		//item.setDescription(process(item.getDescription()));
		//item.setDirectship(process(item.getDirectship()));
		//item.setDropdate(process(item.getDropdate()));
		//item.setDutypct(process(item.getDutypct()));
		//item.setFulldesc(process(item.getFulldesc()));
		//item.setLeadtime(process(item.getLeadtime()));
		//item.setLength(process(item.getLength()));
		//item.setListpricemarginpct(process(item.getListpricemarginpct()));
		item.setAbccd(process(item.getAbccd()));
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
	
}
