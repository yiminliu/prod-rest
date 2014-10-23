package com.bedrosians.bedlogic.util;

import java.math.BigDecimal;
import java.util.Date;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.util.product.ImsDataUtil;


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
		if(data.equals(BigDecimal.ZERO))
		   return BigDecimal.ZERO;
		else
	       return newData.setScale(4, BigDecimal.ROUND_HALF_EVEN);
	}
	   
	public static Date process(Date date) {
					
		if (date == null) {
			return null;
		}	
		else
			return date;
	}
	
	public static synchronized Ims process(Ims ims){
		if(ims == null)
		   return null;	
		ims.setItemcode(process(ims.getItemcode()));	
		ims.setAbccode(process(ims.getAbccode()));
		ims.setItemcategory(process(ims.getItemcategory()));
		ims.setColorcategory(process(ims.getColorcategory()));
		ims.setInactivecode(process(ims.getInactivecode()));
		ims.setInventoryitemcode(process(ims.getInventoryitemcode()));
		ims.setItemcode2(process(ims.getItemcode2()));
		ims.setItemgroupnumber(process(ims.getItemgroupnumber()));
		ims.setItemtypecode(process(ims.getItemtypecode()));
		ims.setLottype(process(ims.getLottype()));
		ims.setIconsystem(process(ims.getIconsystem()));
        ims.setCountryorigin(process(ims.getCountryorigin()));
		ims.setPrintlabel(process(ims.getPrintlabel()));
		ims.setPriorlastupdated(process(ims.getPriorlastupdated()));
		ims.setProductline(process(ims.getProductline()));	
		ims.setShadevariation(process(ims.getShadevariation()));
		//product.setSubtype(process(product.getSubtype()));
		//product.setType(process(product.getType()));
		ims.setUpdatecode(process(ims.getUpdatecode()));
		if(ims.getItemdesc() != null){
			ims.getItemdesc().setFulldesc(process(ims.getItemdesc().getFulldesc()));
			ims.getItemdesc().setItemdesc1(process(ims.getItemdesc().getItemdesc1()));
		}
		if(ims.getSeries() != null){
		   ims.getSeries().setSeriesname(process(ims.getSeries().getSeriesname()));
		   ims.getSeries().setSeriescolor(process(ims.getSeries().getSeriescolor()));
		}	
		if(ims.getPurchasers() != null){
			ims.getPurchasers().setPurchaser(process(ims.getPurchasers().getPurchaser()));	
			ims.getPurchasers().setPurchaser2(process(ims.getPurchasers().getPurchaser2()));	
		}
		if(ims.getApplications() != null){ 
		   ims.getApplications().setCommercial(process(ims.getApplications().getCommercial()));
		   ims.getApplications().setLightcommercial(process(ims.getApplications().getLightcommercial()));
		   ims.getApplications().setResidential(process(ims.getApplications().getResidential()));
		}
		if(ims.getMaterial() != null){
			ims.getMaterial().setMaterialcategory(process(ims.getMaterial().getMaterialcategory()));
		    ims.getMaterial().setMaterialclass(process(ims.getMaterial().getMaterialclass()));
		    ims.getMaterial().setMaterialstyle(process(ims.getMaterial().getMaterialstyle()));
		    ims.getMaterial().setMaterialtype(process(ims.getMaterial().getMaterialtype()));
		    ims.getMaterial().setMaterialfeature(process(ims.getMaterial().getMaterialfeature()));
		}
		if(ims.getDimensions() != null){
			//product.getDimensions().setNominallength(process(product.getDimensions().getNominallength()));
			//product.getDimensions().setNominalthickness(process(product.getDimensions().getNominalthickness()));
		    //product.getDimensions().setNominalwidth(process(product.getDimensions().getNominalwidth()));	
		    ims.getDimensions().setLength(process(ims.getDimensions().getLength()));
		    ims.getDimensions().setThickness(process(ims.getDimensions().getThickness()));
		    ims.getDimensions().setWidth(process(ims.getDimensions().getWidth()));
		    ims.getDimensions().setSizeunits(process(ims.getDimensions().getSizeunits()));
		    ims.getDimensions().setThicknessunit(process(ims.getDimensions().getThicknessunit()));
		}
		if(ims.getPrice() != null){
			ims.getPrice().setPriceunit(process(ims.getPrice().getPriceunit()));
			ims.getPrice().setPricegroup(process(ims.getPrice().getPricegroup()));
		}
		if(ims.getUnits() != null){
			ims.getUnits().setOrdunit(process(ims.getUnits().getOrdunit()));
			ims.getUnits().setStdunit(process(ims.getUnits().getStdunit()));
			ims.getUnits().setBasevolperunit(process(ims.getUnits().getBasevolperunit()));
			ims.getUnits().setBaseunit(process(ims.getUnits().getBaseunit()));
			ims.getUnits().setUnit1unit(process(ims.getUnits().getUnit1unit()));
			ims.getUnits().setUnit2unit(process(ims.getUnits().getUnit2unit()));
			ims.getUnits().setUnit3unit(process(ims.getUnits().getUnit3unit()));
			ims.getUnits().setUnit4unit(process(ims.getUnits().getUnit4unit()));
			ims.getUnits().setBaseupcdesc(process(ims.getUnits().getBaseupcdesc()));
			ims.getUnits().setUnit1upcdesc(process(ims.getUnits().getUnit1upcdesc()));
			ims.getUnits().setUnit2upcdesc(process(ims.getUnits().getUnit2upcdesc()));
			ims.getUnits().setUnit3upcdesc(process(ims.getUnits().getUnit3upcdesc()));
			ims.getUnits().setUnit4upcdesc(process(ims.getUnits().getUnit4upcdesc()));
			ims.getUnits().setBasewgtperunit(process(ims.getUnits().getBasewgtperunit()));
			ims.getUnits().setUnit1wgtperunit(process(ims.getUnits().getUnit1wgtperunit()));
			ims.getUnits().setUnit2wgtperunit(process(ims.getUnits().getUnit2wgtperunit()));
			ims.getUnits().setUnit3wgtperunit(process(ims.getUnits().getUnit3wgtperunit()));
			ims.getUnits().setUnit4wgtperunit(process(ims.getUnits().getUnit4wgtperunit()));
		}
		if(ims.getTestSpecification() != null){
			ims.getTestSpecification().setLeadpoint(process(ims.getTestSpecification().getLeadpoint()));
			ims.getTestSpecification().setBondstrength(process(ims.getTestSpecification().getBondstrength()));
			ims.getTestSpecification().setScratchstandard(process(ims.getTestSpecification().getScratchstandard()));
			ims.getTestSpecification().setBreakingstandard(process(ims.getTestSpecification().getBreakingstandard()));
		}
		if(ims.getNotes() != null){
		   ims.getNotes().setPonotes(process(ims.getNotes().getPonotes()));	
		   ims.getNotes().setBuyernotes(process(ims.getNotes().getBuyernotes()));
		   ims.getNotes().setInternalnotes(process(ims.getNotes().getInternalnotes()));
		   ims.getNotes().setInvoicenotes(process(ims.getNotes().getInvoicenotes()));
		}   
		if(ims.getRelateditemcodes() != null){
			ims.getRelateditemcodes().setSimilaritemcd1(process(ims.getRelateditemcodes().getSimilaritemcd1()));
			ims.getRelateditemcodes().setSimilaritemcd2(process(ims.getRelateditemcodes().getSimilaritemcd2()));
			ims.getRelateditemcodes().setSimilaritemcd3(process(ims.getRelateditemcodes().getSimilaritemcd3()));
			ims.getRelateditemcodes().setSimilaritemcd4(process(ims.getRelateditemcodes().getSimilaritemcd4()));
			ims.getRelateditemcodes().setSimilaritemcd5(process(ims.getRelateditemcodes().getSimilaritemcd5()));
			ims.getRelateditemcodes().setSimilaritemcd6(process(ims.getRelateditemcodes().getSimilaritemcd6()));
		}
		if(ims.getVendors() != null){
			ims.getVendors().setVendorfob(process(ims.getVendors().getVendorfob()));
			ims.getVendors().setVendorxrefcd(process(ims.getVendors().getVendorxrefcd()));
			ims.getVendors().setVendorpriceunit(process(ims.getVendors().getVendorpriceunit()));
			ims.getVendors().setVendorlandedbasecost(process(ims.getVendors().getVendorlandedbasecost()));
			//product.getVendors().setDutypct(process(product.getVendors().getDutypct()));
		}//populate ProductVendor with lagec Vendor info when ProductVendor is empty, this maybe removed after Item_Vedor table is populated with the ims data
		if(ims.getVendors() != null && (ims.getNewVendorSystem() == null || ims.getNewVendorSystem().isEmpty())){
			Vendor vendor = ImsDataUtil.convertImsVendorInfoToItemVendor(ims.getVendors());
			vendor.setVendorOrder(1);
			ims.addNewVendorSystem(vendor);
		}
		if(ims.getIconDescription() == null)
		   ims.setIconDescription(ImsDataUtil.convertLegacyIconsToIconCollection(ims.getIconsystem()));	
	  	if(ims.getPrice() != null)
		   ims.getPrice().setPriceunit(ImsDataUtil.getStandardSellUnit(ims));//product.getStandardSellUnit());	
		if(ims.getUnits() != null)
		   ims.setPackaginginfo(ImsDataUtil.getPackagingInfo(ims));
		if(ims.getApplications() != null)
		   ims.setUsage(ImsDataUtil.convertApplicationsToUsage(ims));
		if(ims.getProductNewFeature() != null && ims.getProductNewFeature().getMpsCode() == null)
	       ims.getProductNewFeature().setMpsCode(ImsDataUtil.convertInactivecdToMpsCode(ims.getInactivecode()));	
	    //if((product.getColorhues() == null || product.getColorhues().isEmpty()) && product.getColorcategory() != null && !product.getColorcategory().isEmpty())
	    //product.setColorhues(new ArrayList<>(ImsDataUtil.convertColorCategoryToColorHueObjects(product)));
		//if(product.getNewVendorSystem() != null && !product.getNewVendorSystem().isEmpty())
		//   product.setNewVendorSystem(new ArrayList<>(new HashSet<>(product.getNewVendorSystem())));	
		return ims;
	}	
}