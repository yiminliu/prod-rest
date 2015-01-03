package com.bedrosians.bedlogic.web.validator;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.domain.ims.embeddable.Material;
import com.bedrosians.bedlogic.domain.ims.embeddable.Price;
import com.bedrosians.bedlogic.domain.ims.embeddable.TestSpecification;
import com.bedrosians.bedlogic.domain.ims.embeddable.Units;
import com.bedrosians.bedlogic.service.mvc.ims.ImsServiceMVC;
import com.bedrosians.bedlogic.util.enums.DBOperation;


@Component
public class ImsValidator implements Validator {
	
	@Autowired
	private ImsServiceMVC imsService;
	
	@Override
	public boolean supports(Class<?> myClass) {
	   return myClass.getClass().isAssignableFrom(myClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
	   Ims item  = (Ims)target;
	}
	
	public void validateGeneralInfo(Ims item, DBOperation action, Errors errors) {
	   validateItemCode(item.getItemcode(), action, errors);
	   checkItemDescription(item.getItemdesc().getItemdesc1(), errors);
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcategory", "required.item.category", "Category is required.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "series.seriesname", "required.item.series.seriesname", "Series Name is required.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "series.seriescolor", "required.item.series.seriescolor", "Series Color is required.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryorigin", "required.item.countryorigin", "Origin Country is required.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inactivecode", "required.item.inactivecode", "Active status is required.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.grade", "required.item.grade", "Grade is required.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.status", "required.item.status", "Status is required.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.mpsCode", "required.item.mpsCode", "MPS code is required.");
	}
	 
	public void validateItemCode(String itemCode, DBOperation action, Errors errors) {
		 if (itemCode == null || itemCode.length() < 1) {
		    errors.rejectValue("itemcode", "item.itemcode.null", "Item code is required.");
		 }
		 else if (itemCode.length() > 18) {
			    errors.rejectValue("itemcode", "item.itemcode.invalid", "Item code cannot be longer than 18 charactors");
		 }
		 else checkItemCodeAvailability(itemCode, action, errors);
	 }
	 
	 private void checkItemCodeAvailability(String itemCode, DBOperation action, Errors errors) {
	    if(imsService.itemCodeIsTaken(itemCode) && (action.equals(DBOperation.CREATE)  || action.equals(DBOperation.CLONE))) 
	       errors.rejectValue("itemcode", "item.itemcode.taken", "Item code is taken, please use a different item code");
	    else if(!imsService.itemCodeIsTaken(itemCode) && (action.equals(DBOperation.UPDATE) || action.equals(DBOperation.DELETE)))		
		      errors.rejectValue("itemcode", "item.itemcode.not_found", "No Item found for this item code.");
	 }
	 
	 private void checkItemDescription(String data, Errors errors) {
		 if (data == null || data.length() < 1) {
		    errors.rejectValue("itemdesc.itemdesc1", "item.description.null", "Item description is required.");
		 }
		 else if (data.length() > 35) {
		    errors.rejectValue("itemdesc.itemdesc1", "item.description.invalid", "Item description cannot be longer than 35 charactors");
		 }
	 }

	 public void validateMaterialInfo(Ims item, Errors errors) {
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "material.materialcategory", "required.item.material.materialcategory", "Material Category is required.");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "material.materialstyle", "required.item.material.materialstyle", "Material Style is required.");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "material.materialclass", "required.item.material.materialclass", "Material Class is required.");
		 //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "material.materialtype", "required.item.material.materialtype", "Material Type is required.");
		 
		 Material data = item.getMaterial();
		 if (data.getMaterialstyle() != null && data.getMaterialstyle().length() > 7) 
		    errors.rejectValue("material.materialstyle", "item.material.materialstyle.too.long", "Mmaterial style length cannot longer than 7");
		 if (data.getMaterialclass() != null && data.getMaterialclass().length() > 5) 
			    errors.rejectValue("material.materialstyle", "item.material.materialstyle.too.long", "Mmaterial style length cannot longer than 7");
	 }
	 
	 public void validatePrice(Ims item, Errors errors) {
		 Price data = item.getPrice();
		 if (data == null || data.getSellprice() == null) {
		    errors.rejectValue("price.sellprice", "item.price.sellprice.null", "MSRP is required.");
		 }
		 if (data.getSellprice() != null && data.getSellprice().intValue() < 0) {
		    errors.rejectValue("price.sellprice", "item.sellprice.invalid", "Please enter a valid price");
		 }
		 
		 if (data != null && data.getTempdatefrom() != null && data.getTempprice() == null){
		    errors.rejectValue("price.tempprice", "item.tempprice.null", "Please enter valid special price");
		 }	 
		 if (data != null && data.getTempprice() != null && data.getTempprice().floatValue() > 0.0){
			 if(data.getTempdatefrom() == null) 
			    errors.rejectValue("price.tempdatefrom", "item.tempdatefrom.null", "Please enter start date");
			 if(data.getTempdatethru() == null) 
				errors.rejectValue("price.tempdatethru", "item.tempdatethru.null", "Please enter end date");
		 }		 
		 compareDates(data.getTempdatefrom(), data.getTempdatethru(), errors);
		 		 
	 }
	 
	 public void validatePackageUnits(Ims item, Errors errors) {
		 Units data = item.getUnits();
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "units.baseunit", "required.units.baseunit", "units.baseunit is required.");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "units.basewgtperunit", "required.units.basewgtperunit", "Base Unit Weight is required.");
		 validateUnitRatio(data, errors);
	 }
	 
	 private void validateUnitRatio(Units units, Errors errors) {
		 if((units.getUnit1unit() != null && units.getUnit1unit().length() > 0) && units.getUnit1ratio() <= 0)
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "units.unit1ratio", "required.units.unit1ratio", "Unit1 ratio is required.");
		 if((units.getUnit1unit() == null || units.getUnit1unit().trim().length() == 0) && (units.getUnit1ratio() != null && units.getUnit1ratio() > 0))
			 errors.rejectValue("units.unit1ratio", "units.unit1ratio.invalid", "Unit1 ratio should be 0, since unit1 unit is empty.");
		 if((units.getUnit2unit() != null && units.getUnit2unit().length() > 0) && units.getUnit2ratio() <= 0)
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "units.unit2ratio", "required.units.unit2ratio", "Unit2 ratio is required.");
		 if((units.getUnit2unit() == null || units.getUnit2unit().trim().length() == 0) && (units.getUnit2ratio() != null && units.getUnit2ratio() > 0))
			 errors.rejectValue("units.unit2ratio", "units.unit2ratio.invalid", "Unit2 ratio should be 0, since unit2 unit is empty.");
		 if((units.getUnit3unit() != null && units.getUnit3unit().length() > 0) && units.getUnit3ratio() <= 0)
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "units.unit3ratio", "required.units.unit3ratio", "Unit3 ratio is required.");
		 if((units.getUnit3unit() == null || units.getUnit3unit().trim().length() == 0) && (units.getUnit3ratio() != null && units.getUnit3ratio() > 0))
			 errors.rejectValue("units.unit3ratio", "units.unit3ratio.invalid", "Unit3 ratio should be 0, since unit3 unit is empty.");
		 if((units.getUnit4unit() != null && units.getUnit4unit().length() > 0) && units.getUnit4ratio() <= 0)
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "units.unit4ratio", "required.units.unit4ratio", "Unit4 ratio is required.");
		 if((units.getUnit4unit() == null || units.getUnit4unit().trim().length() == 0) && (units.getUnit4ratio() != null && units.getUnit4ratio() > 0))
			 errors.rejectValue("units.unit4ratio", "units.unit4ratio.invalid", "Unit4 ratio should be 0, since unit4 unit is empty.");
	 }
	
	 public void validateTestSpecification(Ims item, Errors errors) {
		 TestSpecification data = item.getTestSpecification();
		 //if (data == null || data.getSellprice() == null) {
		 //   errors.rejectValue("price.sellprice", "item.price.sellprice.null", "Please enter a price");
		 //}
		 if (data != null) {
			 if(data.getDcof() != null && data.getDcof() > 1)
		        errors.rejectValue("test.dcof", "item.testspecification.dcofinvalid", "DCOF should < 1.0");
			 if(data.getLeadpoint() != null && data.getLeadpoint().length() > 1)
			    errors.rejectValue("test.leadpoint", "item.testspecification.leadpoint", "Leadpoint should be one character");
		 }		 		 
	 }
	 
	 public void validateVendorInfo(Ims item, Errors errors) {
		 List<Vendor> data = item.getNewVendorSystem();
		 Units units = item.getUnits();
		 if (data != null && !data.isEmpty()) {
			 Vendor vendor1 = data.get(0);
			 if(vendor1 != null && vendor1.getVendorId() != null)
			    validateVendorId(vendor1.getVendorId().getId(), errors);
			 //if(data.get(0) != null && data.get(0).getVendorId().getId() == null)
				 
	         //  errors.rejectValue("vendor.newVendorSystem[0].vendirId.id", "item.newVendorSystem[0].vendirId.id.null", "Please enter a valid number number");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorPriceUnit", "required.item.newVendorSystem[\"0\"].vendorPriceUnit", "Required.");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorListPrice", "required.item.newVendorSystem[\"0\"].vendorListPrice", "Required.");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorDiscountPct", "required.item.newVendorSystem[\"0\"].vendorDiscountPct", "Required.");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorPriceRoundAccuracy", "required.item.newVendorSystem[\"0\"].vendorPriceRoundAccuracy", "Required.");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorMarkupPct", "required.item.newVendorSystem[\"0\"].vendorMarkupPct", "Required.");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorFreightRateCwt", "required.item.newVendorSystem[\"0\"].vendorFreightRateCwt", "Required.");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorMarkupPct", "required.item.newVendorSystem[\"0\"].vendorMarkupPct", "Required.");
			 //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorNetPrice", "required.item.newVendorSystem[\"0\"].vendorNetPrice", "Required.");
			 //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorLandedBaseCost", "required.item.newVendorSystem[\"0\"].vendorLandedBaseCost", "Required.");
			
			 if((vendor1.getVendorPriceUnit() == null) ||
			    (!vendor1.getVendorPriceUnit().equals(units.getBaseunit()) && 
			     !vendor1.getVendorPriceUnit().equals(units.getUnit1unit()) && 
			     !vendor1.getVendorPriceUnit().equals(units.getUnit2unit()) && 
			     !vendor1.getVendorPriceUnit().equals(units.getUnit3unit()) && 
			     !vendor1.getVendorPriceUnit().equals(units.getUnit4unit()))){
				 errors.rejectValue("newVendorSystem[\"0\"].vendorPriceUnit", "item.newVendorSystem[\"0\"].vendorPriceUnit", "Vendor price unit should match one of the packageing units");
			 }
			// if((vendor1.getVendorPriceUnit() == null) ||
			//		    (vendor1.getVendorPriceUnit().equals(units.getBaseunit()) && vendor1.getVendorFreightRateCwt()
			//		     vendor1.getVendorPriceUnit().equals(units.getUnit1unit()) &&
			 
		 }
	 }
	 
	 public void validateVendorId(Integer id, Errors errors) {
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newVendorSystem[\"0\"].vendorId.id", "required.item.newVendorSystem[\"0\"].vendorId.id", "Required.");
		 if(id != null && !imsService.validateVendorId(id))
			 errors.rejectValue("newVendorSystem[\"0\"].vendorId.id", "item.newVendorSystem[0].vendorId.id.invalid", "Invalid Vendor number."); 
	 }
	 
	 public void validateBuyer(Ims item, Errors errors) {
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchasers.purchaser", "required.item.purchasers.purchaser", "Product manager is required.");
	 }
		 
	 private void compareDates(Date startDate, Date endDate, Errors errors) {
		 if (startDate != null && startDate.before(new Date()))
				errors.rejectValue("price.tempdatefrom", "item.tempdatefrom.invalid_startTempdate", "Start date should not be earlyer than today");		
		 if (endDate != null && endDate.before(new Date()))
				errors.rejectValue("price.item.tempdatethru", "item.tempdatethru.invalid_thruTempdate", "End date should not be earlyer than today");		
		 if (startDate != null && endDate != null && startDate.after(endDate))
			errors.rejectValue("price.tempdatethru", "item.tempdatethru.invalid_endTtempdate", "End date should not be earlyer than start date");		
	 }

}
