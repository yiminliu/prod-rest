package com.bedrosians.bedlogic.util;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.bedrosians.bedlogic.domain.item.Item;

public class ListWrapper {

	 private List<Item> list;
	 
	 public ListWrapper(){}
	 
	 public ListWrapper(List<Item> list){
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
