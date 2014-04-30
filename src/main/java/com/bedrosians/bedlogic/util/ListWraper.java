package com.bedrosians.bedlogic.util;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.codehaus.jackson.annotate.JsonProperty;

import com.bedrosians.bedlogic.domain.item.Item;

public class ListWraper {

	 
	 private List<Item> list;
	 
	 public ListWraper(){}
	 
	 public ListWraper(List<Item> list){
		 this.list = list;
	 }
	 @JsonProperty("items")
	 public List<Item> getList() {
	     return list;
	 }

	 public void setList(List<Item> list) {
	     this.list = list;
	 }
	
}
