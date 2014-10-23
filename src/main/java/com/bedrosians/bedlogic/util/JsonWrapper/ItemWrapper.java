package com.bedrosians.bedlogic.util.JsonWrapper;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ItemWrapper {

	 private Ims ims;
	 
	 public ItemWrapper(){}
	 
	 public ItemWrapper(Ims ims){
		ObjectMapper mapper = new  ObjectMapper(); 
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true); //version2.0
		this.ims = ims;
	 }
	 
	 @JsonProperty("item")
	 public Ims getProduct() {
	     return ims;
	 }

	 public void setProduct(Ims ims) {
	     this.ims = ims;
	 }
	
}
