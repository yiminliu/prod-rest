package com.bedrosians.bedlogic.util.JsonWrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ItemListWrapper {

	 private List<ItemWrapper> list;
	 
	 public ItemListWrapper(){}
	 
	 public ItemListWrapper(List<ItemWrapper> list){
		 this.list = list;
	 }
	 
	 @JsonProperty("ims")
	 public List<ItemWrapper> getList() {
	     return list;
	 }

	 public void setList(List<ItemWrapper> list) {
	     this.list = list;
	 }
	
}
