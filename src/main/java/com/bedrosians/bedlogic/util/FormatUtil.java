package com.bedrosians.bedlogic.util;

import java.math.BigDecimal;
import java.util.Date;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.ItemVendor;


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
		//item.setSubtype(process(item.getSubtype()));
		//item.setType(process(item.getType()));
		item.setUpdatecd(process(item.getUpdatecd()));
		if(item.getItemdesc() != null){
			item.getItemdesc().setFulldesc(process(item.getItemdesc().getFulldesc()));
			item.getItemdesc().setItemdesc1(process(item.getItemdesc().getItemdesc1()));
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
		if(item.getVendors() != null){
			item.getVendors().setVendorfob(process(item.getVendors().getVendorfob()));
			item.getVendors().setVendorxrefcd(process(item.getVendors().getVendorxrefcd()));
			item.getVendors().setVendorpriceunit(process(item.getVendors().getVendorpriceunit()));
			//item.getVendors().setDutypct(process(item.getVendors().getDutypct()));
		}//populate ItemVendor with lagec Vendor info when ItemVendor is empty, this maybe removed after Item_Vedor table is populated with the ims data
		if(item.getVendors() != null && (item.getNewVendorSystem() == null || item.getNewVendorSystem().isEmpty())){
			ItemVendor itemVendor = ImsDataUtil.convertImsVendorInfoToItemVendor(item.getVendors());
			itemVendor.setVendorOrder(1);
			item.addNewVendorSystem(itemVendor);
		}
		if(item.getIconDescription() == null)
		   item.setIconDescription(ImsDataUtil.convertLegacyIconsToIconCollection(item.getIconsystem()));	
	  	if(item.getPrice() != null)
		   item.getPrice().setPriceunit(ImsDataUtil.getStandardSellUnit(item));//item.getStandardSellUnit());	
		if(item.getUnits() != null)
		   item.setPackaginginfo(ImsDataUtil.getPackagingInfo(item));
		if(item.getApplications() != null)
		   item.setUsage(ImsDataUtil.convertApplicationsToUsage(item));
		if(item.getImsNewFeature() != null && item.getImsNewFeature().getMpsCode() == null)
	       item.getImsNewFeature().setMpsCode(ImsDataUtil.convertInactivecdToMpsCode(item.getInactivecode()));	
	    //if((item.getColorhues() == null || item.getColorhues().isEmpty()) && item.getColorcategory() != null && !item.getColorcategory().isEmpty())
	    //item.setColorhues(new ArrayList<>(ImsDataUtil.convertColorCategoryToColorHueObjects(item)));
		//if(item.getNewVendorSystem() != null && !item.getNewVendorSystem().isEmpty())
		//   item.setNewVendorSystem(new ArrayList<>(new HashSet<>(item.getNewVendorSystem())));	
		return item;
	}	
	
}
