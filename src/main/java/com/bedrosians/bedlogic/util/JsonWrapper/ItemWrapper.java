package com.bedrosians.bedlogic.util.JsonWrapper;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ItemWrapper {

	 private Ims item;
	 
	 public ItemWrapper(){}
	 
	 public ItemWrapper(Ims item){
		ObjectMapper mapper = new  ObjectMapper(); 
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true); //version2.0
		this.item = item;
	 }
	 
	 @JsonProperty("item")
	 public Ims getItem() {
	     return item;
	 }

	 public void setItem(Ims item) {
	     this.item = item;
	 }
	
}
