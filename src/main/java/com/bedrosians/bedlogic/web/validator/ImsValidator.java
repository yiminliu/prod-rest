package com.bedrosians.bedlogic.web.validator;




import java.math.BigDecimal;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.embeddable.Price;


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
	   
	   //checkPrice(item.getPrice(), errors);
	   //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcode", "required.item.itemcode", "Item code is required.");
	  // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemcategory", "required.item.category", "Category is required.");
	  // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",	"required.item.description", "Description is required.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inactivecode", "required.item.inactivecode", "Please enter a active status.");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "showonwebsite", "required.item.showonwebsite", "Please enter a valid value.");
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

	 private void checkPrice(Price data, Errors errors) {
		
		 if (data == null || data.getSellprice() == null) {
		    errors.rejectValue("price.sellprice", "item.price.sellprice.null", "Please enter a price");
		 }
		 else if (data.getSellprice().intValue() < 0) {
		    errors.rejectValue("price.sellprice", "item.sellprice.invalid", "Please enter a valid price");
		 }
	 }

}
