package com.bedrosians.bedlogic.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;

import com.bedrosians.bedlogic.domain.item.ColorHue;
import com.bedrosians.bedlogic.domain.item.IconCollection;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.ItemVendor;
import com.bedrosians.bedlogic.domain.item.Note;
import com.bedrosians.bedlogic.domain.item.embeddable.Notes;
import com.bedrosians.bedlogic.domain.item.embeddable.Price;
import com.bedrosians.bedlogic.domain.item.embeddable.Units;
import com.bedrosians.bedlogic.domain.item.embeddable.VendorInfo;
import com.bedrosians.bedlogic.domain.item.enums.DBOperation;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;


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
			//item.getVendors().setDutypct(process(item.getVendors().getDutypct()));
			item.getVendors().setVendorfob(process(item.getVendors().getVendorfob()));
			item.getVendors().setVendorxrefcd(process(item.getVendors().getVendorxrefcd()));
			item.getVendors().setVendorpriceunit(process(item.getVendors().getVendorpriceunit()));
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
		   item.getNotes().setNotes1(process(item.getNotes().getNotes1()));
		   item.getNotes().setNotes2(process(item.getNotes().getNotes2()));
		   item.getNotes().setNotes3(process(item.getNotes().getNotes3()));
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
		   //item.getPriorVendor().setPriorvendordiscpct1(process(item.getPriorVendor().getPriorvendordiscpct1()));	
		   item.getPriorVendor().setPriorvendorfob(process(item.getPriorVendor().getPriorvendorfob()));
		   //item.getPriorVendor().setPriorvendorfreightratecwt(process(item.getPriorVendor().getPriorvendorfreightratecwt()));
		   //item.getPriorVendor().setPriorvendorlandedbasecost(process(item.getPriorVendor().getPriorvendorlandedbasecost()));
		   //item.getPriorVendor().setPriorvendorlistprice(process(item.getPriorVendor().getPriorvendorlistprice()));
		   //item.getPriorVendor().setPriorvendormarkuppct(process(item.getPriorVendor().getPriorvendormarkuppct()));
		   //item.getPriorVendor().setPriorvendornetprice(process(item.getPriorVendor().getPriorvendornetprice()));
		   item.getPriorVendor().setPriorvendorpriceunit(process(item.getPriorVendor().getPriorvendorpriceunit()));
		   item.getPriorVendor().setPriorvendorroundaccuracy(process(item.getPriorVendor().getPriorvendorroundaccuracy()));
		}
		if(item.getIconDescription() == null)
		   item.setIconDescription(ImsResultUtil.parseIcons(item.getIconsystem()));	
	    if(item.getPriorVendor() != null)
		   ImsResultUtil.parsePriorVendor(item);	
		if(item.getPrice() != null)
		   item.getPrice().setPriceunit(ImsResultUtil.getStandardSellUnit(item));//item.getStandardSellUnit());	
		if(item.getUnits() != null)
		   item.setPackaginginfo(ImsResultUtil.getPackagingInfo(item));
		if(item.getApplications() != null)
		   item.setUsage(ImsResultUtil.convertApplicationsToUsage(item));
		if(item.getColorhues() == null || item.getColorhues().isEmpty())
		   item.setColorhues(ImsResultUtil.convertColorCategoryToColorHues(item.getColorcategory()));
		
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
	
	public static Item transformItem(Item newItem, Item origItem, DBOperation operation) throws BedDAOBadParamException{
		//Item newItem = new Item();
	    	//String itemCode = item.getItemcode().toUpperCase();
		    //newItem.setItemcode(itemCode);	
		if(origItem == null)
	       throw new BedDAOBadParamException("The input is empty");		
		transferProperty(newItem, origItem);
		transferComponent(newItem, origItem);
		transferAssociation(newItem, origItem, operation);	
		return newItem;
	}
		
	private static void transferProperty(Item newItem, Item originalItem) throws BedDAOBadParamException{
	  try{	
		if(originalItem.getAbccd() != null) newItem.setAbccd(originalItem.getAbccd());
		if(originalItem.getCountryorigin() != null) newItem.setCountryorigin(originalItem.getCountryorigin());
		if(originalItem.getDimensions() != null) newItem.setDimensions(originalItem.getDimensions());
		if(originalItem.getDirectship() != null) newItem.setDirectship(originalItem.getDirectship());
		if(originalItem.getDropdate() != null) newItem.setDropdate(originalItem.getDropdate());
		if(originalItem.getIconsystem() != null) newItem.setIconsystem(originalItem.getIconsystem());
		if(originalItem.getInactivecode() != null) newItem.setInactivecode(originalItem.getInactivecode());
		if(originalItem.getInventoryitemcd() != null) newItem.setInventoryitemcd(originalItem.getInventoryitemcd());
		if(originalItem.getItemcd2() != null) newItem.setItemcd2(originalItem.getItemcd2());
		if(originalItem.getItemgroupnbr() != null) newItem.setItemgroupnbr(originalItem.getItemgroupnbr());
		if(originalItem.getItemtypecd() != null) newItem.setItemtypecd(originalItem.getItemtypecd());	
		if(originalItem.getItemcategory() != null) newItem.setItemcategory(originalItem.getItemcategory());
		if(originalItem.getLottype() != null) newItem.setLottype(originalItem.getLottype());
		if(originalItem.getOffshade() != null) newItem.setOffshade(originalItem.getOffshade());
		if(originalItem.getPriorlastupdated() != null) newItem.setPriorlastupdated(originalItem.getPriorlastupdated());
		if(originalItem.getPrintlabel() != null) newItem.setPrintlabel(originalItem.getPrintlabel());
		if(originalItem.getShadevariation() != null) newItem.setShadevariation(originalItem.getShadevariation());
		if(originalItem.getShowonalysedwards() != null) newItem.setShowonalysedwards(originalItem.getShowonalysedwards());
		if(originalItem.getShowonwebsite() != null) newItem.setShowonwebsite(originalItem.getShowonwebsite());
		if(originalItem.getSubtype() != null) newItem.setSubtype(originalItem.getSubtype());
		if(originalItem.getTaxclass() != null) newItem.setTaxclass(originalItem.getTaxclass());
		if(originalItem.getType() != null) newItem.setType(originalItem.getType());
		if(originalItem.getUpdatecd() != null) newItem.setUpdatecd(originalItem.getUpdatecd());
		if(originalItem.getProductline() != null) newItem.setProductline(originalItem.getProductline());
		
		if(originalItem.getColorcategory() != null){
		   if(originalItem.getColorcategory().contains(":")){
			  String[] colors = originalItem.getColorcategory().split(":");
			  for(String color : colors){
				  newItem.addColorhue(new ColorHue(color));
			  }
		   }
		   newItem.setColorcategory(originalItem.getColorcategory());
		}
	  }
	  catch(Exception e){
			  throw new BedDAOBadParamException("Error occured while transferProperty(): " + e.getMessage(), e.getCause());	
	  }	
	}
	
	private static void transferComponent(Item newItem, Item originalItem) throws BedDAOBadParamException{
      try{
    	if(originalItem.getApplications() != null) newItem.setApplications(originalItem.getApplications());
      	if(originalItem.getCost() != null) newItem.setCost(originalItem.getCost());
		if(originalItem.getDimensions() != null) newItem.setDimensions(originalItem.getDimensions());
		if(originalItem.getItemdesc() != null) newItem.setItemdesc(originalItem.getItemdesc());
		if(originalItem.getMaterial() != null) newItem.setMaterial(originalItem.getMaterial());
		if(originalItem.getNotes() != null) newItem.setNotes(originalItem.getNotes());
		if(originalItem.getPackaginginfo() != null) newItem.setPackaginginfo(originalItem.getPackaginginfo());
		if(originalItem.getPriorVendor() != null) newItem.setPriorVendor(originalItem.getPriorVendor());
		if(originalItem.getPurchasers() != null) newItem.setPurchasers(originalItem.getPurchasers());
		if(originalItem.getRelateditemcodes() != null) newItem.setRelateditemcodes(originalItem.getRelateditemcodes());
		if(originalItem.getSeries() != null) newItem.setSeries(originalItem.getSeries());
		if(originalItem.getTestSpecification() != null) newItem.setTestSpecification(originalItem.getTestSpecification());
		if(originalItem.getUsage() != null) newItem.setUsage(originalItem.getUsage());		
		
		if(originalItem.getPrice() != null){
			Price price = originalItem.getPrice();
			if(price.getSellpricemarginpct() == null)
				price.setSellpricemarginpct(0f);
			if(price.getSellpriceroundaccuracy() == null)
				price.setSellpriceroundaccuracy(0);
			if(price.getListpricemarginpct() == null)
				price.setListpricemarginpct(0f);
			newItem.setPrice(price);
		}
		
		if(originalItem.getUnits() != null){
			Units units = originalItem.getUnits();
			if(units.getBaseunit() == null)
			   units.setBaseunit("PCS");
			
			newItem.setUnits(units);
		}
      }
	  catch(Exception e){
			  throw new BedDAOBadParamException("Error occured while transferComponent(): " + e.getMessage(), e.getCause());	
	  }		
	}
	
	private static void transferAssociation(Item newItem, Item originalItem, DBOperation operation) throws BedDAOBadParamException{
	  try{
		ImsNewFeature newFeature = originalItem.getImsNewFeature();
		Set<ColorHue> colorHueSet = originalItem.getColorhues();
		IconCollection iconCollection = originalItem.getIconDescription();
		Set<ItemVendor> itemVendorList = originalItem.getNewVendorSystem();
		List<Note> noteList = originalItem.getNewNoteSystem();
		VendorInfo vendorInfo = originalItem.getVendors();
	    Notes legacyNotes = (originalItem.getNotes() != null)? originalItem.getNotes() : new Notes();
	    
		if(newFeature != null && !newFeature.isEmpty()){
		    if(operation.equals(DBOperation.CREATE) && newFeature.getCreatedDate() == null)
			   newFeature.setCreatedDate(new Date());
		    else if(operation.equals(DBOperation.UPDATE) && newFeature.getLastModifiedDate() == null)
			   newFeature.setLastModifiedDate(new Date());
		    
		    newItem.addImsNewFeature(newFeature);	
		}
		
		if(colorHueSet != null && !colorHueSet.isEmpty()){
		   for(ColorHue color : colorHueSet){	
			   if(color != null && color.getColorHue() != null && !color.getColorHue().isEmpty())
			   newItem.addColorhue(color);	
		   }
		   //if("insert".equalsIgnoreCase(operation) && (newItem.getColorcategory() == null || newItem.getColorcategory().isEmpty()))
			  newItem.setColorcategory(ImsResultUtil.convertColorHuesToColorCategory(colorHueSet));
		   //else if("update".equalsIgnoreCase(operation)){
			   
		   //}
	    }
		
		if(iconCollection != null && !iconCollection.isEmpty()){//!iconCollection.equals(newItem.getIconDescription())){
		   newItem.addIconDescription(iconCollection);
		   newItem.setIconsystem(iconCollection.toLegancyIncons());
		}
		
		if(itemVendorList != null && !itemVendorList.isEmpty()){
			for(ItemVendor vendor : itemVendorList){
				if(vendor != null && !vendor.isEmpty()){
 		           newItem.addNewVendorSystem(vendor);	
 			   	   if((vendorInfo == null || vendorInfo.getVendornbr() == null || vendorInfo.getVendornbr() == 0) && vendor.getVendorOrder() == 1){
 			   	       newItem.setVendors(populateVendorInfo(vendor));	
 			   	   }
				}   
			}    
		}
		
		if(noteList != null && !noteList.isEmpty()){
			for(Note note : noteList){	
				if(operation.equals(DBOperation.CREATE) && note.getCreatedDate() == null)
 		           note.setCreatedDate(new Date());
				else if(operation.equals(DBOperation.UPDATE) && note.getLastModifiedDate() == null)
				   note.setLastModifiedDate(new Date());	
			    newItem.addNote(note);	
 		        //update legacy notes
			    if((legacyNotes.getPonotes() == null || legacyNotes.getPonotes().isEmpty()) && "po".equalsIgnoreCase(note.getNoteType()))
 		        	legacyNotes.setPonotes(note.getNote());
 		        else if((legacyNotes.getNotes1() == null || legacyNotes.getNotes1().isEmpty()) && "buyer".equalsIgnoreCase(note.getNoteType()))
 		        	legacyNotes.setNotes1(note.getNote());
 		        else if((legacyNotes.getNotes2() == null || legacyNotes.getNotes2().isEmpty()) && "additional".equalsIgnoreCase(note.getNoteType()))
 	    	    	legacyNotes.setNotes2(note.getNote());
 		        else if((legacyNotes.getNotes3() == null || legacyNotes.getNotes3().isEmpty()) && "invoice".equalsIgnoreCase(note.getNoteType()))
 	    	    	legacyNotes.setNotes3(note.getNote());        
		    }
			if(legacyNotes != null)
				newItem.setNotes(legacyNotes);
		}
	 }
	  catch(Exception e){
			  throw new BedDAOBadParamException("Error occured while transferAssociation(): " + e.getMessage(), e.getCause());	
	  }	
		
	}
	
	
	private static VendorInfo populateVendorInfo(ItemVendor itemVendor){
		VendorInfo vendorInfo = new VendorInfo();
		if(itemVendor != null){
			vendorInfo.setVendornbr1(itemVendor.getVendorId());
			vendorInfo.setVendorxrefcd(itemVendor.getVendorXrefId());
			vendorInfo.setVendorfob(itemVendor.getVendorFob());
			vendorInfo.setDutypct(itemVendor.getDutyPct());
		    vendorInfo.setLeadtime(itemVendor.getLeadTime());
			if(itemVendor.getVendorListPrice() != null) vendorInfo.setVendorlistprice(itemVendor.getVendorListPrice());
			if(itemVendor.getVendorPriceUnit() != null) vendorInfo.setVendorpriceunit(itemVendor.getVendorPriceUnit());
			if(itemVendor.getVendorDiscountPct() != null) vendorInfo.setVendordiscpct(itemVendor.getVendorDiscountPct());
		    if(itemVendor.getVendorPriceRoundAccuracy() != null) vendorInfo.setVendorroundaccuracy(itemVendor.getVendorPriceRoundAccuracy());
		    if(itemVendor.getVendorNetPrice() != null) vendorInfo.setVendornetprice(itemVendor.getVendorNetPrice());
 	        if(itemVendor.getVendorMarkupPct() != null) vendorInfo.setVendormarkuppct(itemVendor.getVendorMarkupPct());
		    if(itemVendor.getVendorFreightRateCwt() != null) vendorInfo.setVendorfreightratecwt(itemVendor.getVendorFreightRateCwt());
		    if(itemVendor.getVendorLandedBaseCost() != null) vendorInfo.setVendorlandedbasecost(itemVendor.getVendorLandedBaseCost());
		 } 
		return vendorInfo;
	}	
	
	//private Applications convertUsageToApplications(List<String> usage){
		//Applications applications = new Applications();
		//String residential = null;
		//if(usage.c)
		//return applications;
	//}
	//+ "\"usage\":[\"FR\",\"WR\",\"CR\",\"SR\",\"PR\",\"FL\",\"WL\",\"CL\",\"SL\",\"PL\",\"FC\",\"WC\",\"CC\",\"SC\",\"PC\"],"

	
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
