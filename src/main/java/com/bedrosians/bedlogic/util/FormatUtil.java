package com.bedrosians.bedlogic.util;

import java.math.BigDecimal;
import java.util.Date;

import com.bedrosians.bedlogic.domain.account.Account;
import com.bedrosians.bedlogic.domain.account.embeddable.Contact;
import com.bedrosians.bedlogic.domain.account.embeddable.Owner;
import com.bedrosians.bedlogic.domain.account.embeddable.Phone;
import com.bedrosians.bedlogic.domain.account.embeddable.Statement;
import com.bedrosians.bedlogic.domain.product.ProductVendor;
import com.bedrosians.bedlogic.domain.product.Product;
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
	
	public static synchronized Product process(Product product){
		if(product == null)
		   return null;	
		product.setItemcode(process(product.getItemcode()));	
		product.setAbccode(process(product.getAbccode()));
		product.setItemcategory(process(product.getItemcategory()));
		product.setColorcategory(process(product.getColorcategory()));
		product.setInactivecode(process(product.getInactivecode()));
		product.setInventoryitemcode(process(product.getInventoryitemcode()));
		product.setItemcode2(process(product.getItemcode2()));
		product.setItemgroupnumber(process(product.getItemgroupnumber()));
		product.setItemtypecode(process(product.getItemtypecode()));
		product.setLottype(process(product.getLottype()));
		product.setIconsystem(process(product.getIconsystem()));
        product.setCountryorigin(process(product.getCountryorigin()));
		product.setPrintlabel(process(product.getPrintlabel()));
		product.setPriorlastupdated(process(product.getPriorlastupdated()));
		product.setProductline(process(product.getProductline()));	
		product.setShadevariation(process(product.getShadevariation()));
		//product.setSubtype(process(product.getSubtype()));
		//product.setType(process(product.getType()));
		product.setUpdatecode(process(product.getUpdatecode()));
		if(product.getItemdesc() != null){
			product.getItemdesc().setFulldesc(process(product.getItemdesc().getFulldesc()));
			product.getItemdesc().setItemdesc1(process(product.getItemdesc().getItemdesc1()));
		}
		if(product.getSeries() != null){
		   product.getSeries().setSeriesname(process(product.getSeries().getSeriesname()));
		   product.getSeries().setSeriescolor(process(product.getSeries().getSeriescolor()));
		}	
		if(product.getPurchasers() != null){
			product.getPurchasers().setPurchaser(process(product.getPurchasers().getPurchaser()));	
			product.getPurchasers().setPurchaser2(process(product.getPurchasers().getPurchaser2()));	
		}
		if(product.getApplications() != null){ 
		   product.getApplications().setCommercial(process(product.getApplications().getCommercial()));
		   product.getApplications().setLightcommercial(process(product.getApplications().getLightcommercial()));
		   product.getApplications().setResidential(process(product.getApplications().getResidential()));
		}
		if(product.getMaterial() != null){
			product.getMaterial().setMaterialcategory(process(product.getMaterial().getMaterialcategory()));
		    product.getMaterial().setMaterialclass(process(product.getMaterial().getMaterialclass()));
		    product.getMaterial().setMaterialstyle(process(product.getMaterial().getMaterialstyle()));
		    product.getMaterial().setMaterialtype(process(product.getMaterial().getMaterialtype()));
		    product.getMaterial().setMaterialfeature(process(product.getMaterial().getMaterialfeature()));
		}
		if(product.getDimensions() != null){
			//product.getDimensions().setNominallength(process(product.getDimensions().getNominallength()));
			//product.getDimensions().setNominalthickness(process(product.getDimensions().getNominalthickness()));
		    //product.getDimensions().setNominalwidth(process(product.getDimensions().getNominalwidth()));	
		    product.getDimensions().setLength(process(product.getDimensions().getLength()));
		    product.getDimensions().setThickness(process(product.getDimensions().getThickness()));
		    product.getDimensions().setWidth(process(product.getDimensions().getWidth()));
		    product.getDimensions().setSizeunits(process(product.getDimensions().getSizeunits()));
		    product.getDimensions().setThicknessunit(process(product.getDimensions().getThicknessunit()));
		}
		if(product.getPrice() != null){
			product.getPrice().setPriceunit(process(product.getPrice().getPriceunit()));
			product.getPrice().setPricegroup(process(product.getPrice().getPricegroup()));
		}
		if(product.getUnits() != null){
			product.getUnits().setOrdunit(process(product.getUnits().getOrdunit()));
			product.getUnits().setStdunit(process(product.getUnits().getStdunit()));
			product.getUnits().setBasevolperunit(process(product.getUnits().getBasevolperunit()));
			product.getUnits().setBaseunit(process(product.getUnits().getBaseunit()));
			product.getUnits().setUnit1unit(process(product.getUnits().getUnit1unit()));
			product.getUnits().setUnit2unit(process(product.getUnits().getUnit2unit()));
			product.getUnits().setUnit3unit(process(product.getUnits().getUnit3unit()));
			product.getUnits().setUnit4unit(process(product.getUnits().getUnit4unit()));
			product.getUnits().setBaseupcdesc(process(product.getUnits().getBaseupcdesc()));
			product.getUnits().setUnit1upcdesc(process(product.getUnits().getUnit1upcdesc()));
			product.getUnits().setUnit2upcdesc(process(product.getUnits().getUnit2upcdesc()));
			product.getUnits().setUnit3upcdesc(process(product.getUnits().getUnit3upcdesc()));
			product.getUnits().setUnit4upcdesc(process(product.getUnits().getUnit4upcdesc()));
			product.getUnits().setBasewgtperunit(process(product.getUnits().getBasewgtperunit()));
			product.getUnits().setUnit1wgtperunit(process(product.getUnits().getUnit1wgtperunit()));
			product.getUnits().setUnit2wgtperunit(process(product.getUnits().getUnit2wgtperunit()));
			product.getUnits().setUnit3wgtperunit(process(product.getUnits().getUnit3wgtperunit()));
			product.getUnits().setUnit4wgtperunit(process(product.getUnits().getUnit4wgtperunit()));
		}
		if(product.getTestSpecification() != null){
			product.getTestSpecification().setLeadpoint(process(product.getTestSpecification().getLeadpoint()));
			product.getTestSpecification().setBondstrength(process(product.getTestSpecification().getBondstrength()));
			product.getTestSpecification().setScratchstandard(process(product.getTestSpecification().getScratchstandard()));
			product.getTestSpecification().setBreakingstandard(process(product.getTestSpecification().getBreakingstandard()));
		}
		if(product.getNotes() != null){
		   product.getNotes().setPonotes(process(product.getNotes().getPonotes()));	
		   product.getNotes().setBuyernotes(process(product.getNotes().getBuyernotes()));
		   product.getNotes().setInternalnotes(process(product.getNotes().getInternalnotes()));
		   product.getNotes().setInvoicenotes(process(product.getNotes().getInvoicenotes()));
		}   
		if(product.getRelateditemcodes() != null){
			product.getRelateditemcodes().setSimilaritemcd1(process(product.getRelateditemcodes().getSimilaritemcd1()));
			product.getRelateditemcodes().setSimilaritemcd2(process(product.getRelateditemcodes().getSimilaritemcd2()));
			product.getRelateditemcodes().setSimilaritemcd3(process(product.getRelateditemcodes().getSimilaritemcd3()));
			product.getRelateditemcodes().setSimilaritemcd4(process(product.getRelateditemcodes().getSimilaritemcd4()));
			product.getRelateditemcodes().setSimilaritemcd5(process(product.getRelateditemcodes().getSimilaritemcd5()));
			product.getRelateditemcodes().setSimilaritemcd6(process(product.getRelateditemcodes().getSimilaritemcd6()));
		}
		if(product.getVendors() != null){
			product.getVendors().setVendorfob(process(product.getVendors().getVendorfob()));
			product.getVendors().setVendorxrefcd(process(product.getVendors().getVendorxrefcd()));
			product.getVendors().setVendorpriceunit(process(product.getVendors().getVendorpriceunit()));
			product.getVendors().setVendorlandedbasecost(process(product.getVendors().getVendorlandedbasecost()));
			//product.getVendors().setDutypct(process(product.getVendors().getDutypct()));
		}//populate ProductVendor with lagec Vendor info when ProductVendor is empty, this maybe removed after Item_Vedor table is populated with the ims data
		if(product.getVendors() != null && (product.getNewVendorSystem() == null || product.getNewVendorSystem().isEmpty())){
			ProductVendor productVendor = ImsDataUtil.convertImsVendorInfoToItemVendor(product.getVendors());
			productVendor.setVendorOrder(1);
			product.addNewVendorSystem(productVendor);
		}
		if(product.getIconDescription() == null)
		   product.setIconDescription(ImsDataUtil.convertLegacyIconsToIconCollection(product.getIconsystem()));	
	  	if(product.getPrice() != null)
		   product.getPrice().setPriceunit(ImsDataUtil.getStandardSellUnit(product));//product.getStandardSellUnit());	
		if(product.getUnits() != null)
		   product.setPackaginginfo(ImsDataUtil.getPackagingInfo(product));
		if(product.getApplications() != null)
		   product.setUsage(ImsDataUtil.convertApplicationsToUsage(product));
		if(product.getProductNewFeature() != null && product.getProductNewFeature().getMpsCode() == null)
	       product.getProductNewFeature().setMpsCode(ImsDataUtil.convertInactivecdToMpsCode(product.getInactivecode()));	
	    //if((product.getColorhues() == null || product.getColorhues().isEmpty()) && product.getColorcategory() != null && !product.getColorcategory().isEmpty())
	    //product.setColorhues(new ArrayList<>(ImsDataUtil.convertColorCategoryToColorHueObjects(product)));
		//if(product.getNewVendorSystem() != null && !product.getNewVendorSystem().isEmpty())
		//   product.setNewVendorSystem(new ArrayList<>(new HashSet<>(product.getNewVendorSystem())));	
		return product;
	}	
}