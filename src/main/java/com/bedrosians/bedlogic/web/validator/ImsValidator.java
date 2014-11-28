package com.bedrosians.bedlogic.web.validator;




import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.domain.ims.embeddable.Price;
import com.bedrosians.bedlogic.domain.ims.embeddable.TestSpecification;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;


@Component
public class ImsValidator implements Validator {
	
	
	@Override
	public boolean supports(Class<?> myClass) {
	   return myClass.getClass().isAssignableFrom(myClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
	   Ims item  = (Ims)target;
	   checkItemCode(item.getItemcode(), errors);
	   checkItemDescription(item.getItemdesc().getItemdesc1(), errors);
	  // if(item.getPrice() != null && !item.getPrice().isDefault())
	  //   checkPrice(item.getPrice(), errors);
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcode", "required.item.itemcode", "Item code is required.");
	  // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcategory", "required.item.category", "Category is required.");
	  // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",	"required.item.description", "Description is required.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inactivecode", "required.item.inactivecode", "Please enter a active status.");
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "showonwebsite", "required.item.showonwebsite", "Please enter a valid value.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.grade", "required.item.grade", "Please enter a valid grade.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.status", "required.item.status", "Please enter a valid status.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.mpsCode", "required.item.mpsCode", "Please enter a valid mps code.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryorigin", "required.item.countryorigin", "Please enter a valid origin country.");
	
	}
	
	 public void validateGeneralInfo(Ims item, Errors errors) {
		   checkItemCode(item.getItemcode(), errors);
		   checkItemDescription(item.getItemdesc().getItemdesc1(), errors);
		   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcode", "required.item.itemcode", "Item code is required.");
		   // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcategory", "required.item.category", "Category is required.");
		   // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",	"required.item.description", "Description is required.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inactivecode", "required.item.inactivecode", "Please enter a active status.");
		   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "showonwebsite", "required.item.showonwebsite", "Please enter a valid value.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.grade", "required.item.grade", "Please enter a valid grade.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.status", "required.item.status", "Please enter a valid status.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newFeature.mpsCode", "required.item.mpsCode", "Please enter a valid mps code.");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryorigin", "required.item.countryorigin", "Please enter a valid origin country.");

	 }
	 
	 private void checkItemCode(String itemCode, Errors errors) {
		 if (itemCode == null || itemCode.length() < 1) {
		    errors.rejectValue("itemcode", "item.itemcode.null", "Item code cannot be null");
		 }
		 else if (itemCode.length() > 18) {
			    errors.rejectValue("itemcode", "item.itemcode.invalid", "Item code cannot be longer than 18 charactors");
		 }
	 }
	 
	 private void checkItemDescription(String data, Errors errors) {
		 if (data == null || data.length() < 1) {
		    errors.rejectValue("itemdesc.itemdesc1", "item.description.null", "Item description cannot be null");
		 }
		 else if (data.length() > 35) {
		    errors.rejectValue("itemdesc.itemdesc1", "item.description.invalid", "Item description cannot be longer than 35 charactors");
		 }
	 }

	 public void validatePrice(Ims item, Errors errors) {
		 Price data = item.getPrice();
		 //if (data == null || data.getSellprice() == null) {
		 //   errors.rejectValue("price.sellprice", "item.price.sellprice.null", "Please enter a price");
		 //}
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
	 
	 public void validateTestSpecification(Ims item, Errors errors) {
		 TestSpecification data = item.getTestSpecification();
		 //if (data == null || data.getSellprice() == null) {
		 //   errors.rejectValue("price.sellprice", "item.price.sellprice.null", "Please enter a price");
		 //}
		 if (data == null) {
			 if(data.getDcof() != null && data.getDcof() > 1)
		        errors.rejectValue("test.dcof", "item.testspecification.dcofinvalid", "DCOF should < 1.0");
			 if(data.getLeadpoint() != null && data.getLeadpoint().length() > 1)
			    errors.rejectValue("test.leadpoint", "item.testspecification.leadpoint", "Leadpoint should be one character");
		 }		 		 
	 }
	 
	 public void validateVendorInfo(Ims item, Errors errors) {
		 List<Vendor> data = item.getNewVendorSystem();
		 if (data != null && !data.isEmpty()) {
			 if(data.get(0) != null && data.get(0).getVendorId().getId() == null)
				 System.out.println("item.newVendorSystem[0] =" + data.get(0));
		      //  errors.rejectValue("vendor.newVendorSystem[0].vendirId.id", "item.newVendorSystem[0].vendirId.id.null", "Please enter a valid number number");
		 }
	 }
	 
	 private void compareDates(Date startDate, Date endDate, Errors errors) {
		 if (startDate != null && endDate != null && startDate.after(endDate))
			errors.rejectValue("price.tempdatethru", "item.tempdatethru.invalid_endTtempdate", "End date should not be earlyer than start date");		
	 }

}
