package com.bedrosians.bedlogic.web.validator;




import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
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
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.service.mvc.ims.ImsServiceMVC;


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
	   //checkItemCode(item.getItemcode(), errors);
	   //checkItemDescription(item.getItemdesc().getItemdesc1(), errors);
	  // if(item.getPrice() != null && !item.getPrice().isDefault())
	  //   checkPrice(item.getPrice(), errors);
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcode", "required.item.itemcode", "Item code is required.");
	  // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcategory", "required.item.category", "Category is required.");
	  // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",	"required.item.description", "Description is required.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inactivecode", "required.item.inactivecode", "Please enter a active status.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "showonwebsite", "required.item.showonwebsite", "Please enter a valid value.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.grade", "required.item.grade", "Please enter a valid grade.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.status", "required.item.status", "Please enter a valid status.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.mpsCode", "required.item.mpsCode", "Please enter a valid mps code.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryorigin", "required.item.countryorigin", "Please enter a valid origin country.");
	
	}
	
	 public void validateGeneralInfo(Ims item, Errors errors) {
		   validateItemCode(item.getItemcode(), errors);
		   checkItemDescription(item.getItemdesc().getItemdesc1(), errors);
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcategory", "required.item.category", "Category is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "series.seriesname", "required.item.series.seriesname", "Series Name is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "series.seriescolor", "required.item.series.seriescolor", "Series Color is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryorigin", "required.item.countryorigin", "Origin Country is required.");
		   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inactivecode", "required.item.inactivecode", "Active status is required.");
		   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.grade", "required.item.grade", "Grade is required.");
		   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.status", "required.item.status", "Status is required.");
		   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.mpsCode", "required.item.mpsCode", "MPS code is required.");
		   //

	 }
	 
	 public void validateItemCode(String itemCode, Errors errors) {
		 if (itemCode == null || itemCode.length() < 1) {
		    errors.rejectValue("itemcode", "item.itemcode.null", "Item code is required.");
		 }
		 else if (itemCode.length() > 18) {
			    errors.rejectValue("itemcode", "item.itemcode.invalid", "Item code cannot be longer than 18 charactors");
		 }
		 else checkItemCodeAvailability(itemCode, errors);
	 }
	 
	 private void checkItemCodeAvailability(String itemCode, Errors errors) {
	    if(imsService.itemCodeIsTaken(itemCode))
	       errors.rejectValue("itemcode", "item.itemcode.taken", "Item code is taken, please use a different item code");
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
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "material.materialtype", "required.item.material.materialtype", "Material Type is required.");
		 
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
		 if (data != null && data.getTempprice() != null){
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
		 if (data != null && !data.isEmpty()) {
			 if(data.get(0) != null && data.get(0).getVendorId() != null)
			    validateVendorId(data.get(0).getVendorId().getId(), errors);
			 //if(data.get(0) != null && data.get(0).getVendorId().getId() == null)
				 
				// System.out.println("item.newVendorSystem[0] =" + data.get(0));
		      //  errors.rejectValue("vendor.newVendorSystem[0].vendirId.id", "item.newVendorSystem[0].vendirId.id.null", "Please enter a valid number number");
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
		 if (startDate != null && endDate != null && startDate.after(endDate))
			errors.rejectValue("price.tempdatethru", "item.tempdatethru.invalid_endTtempdate", "End date should not be earlyer than start date");		
	 }

}
