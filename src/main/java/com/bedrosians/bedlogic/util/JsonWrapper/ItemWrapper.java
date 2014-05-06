package com.bedrosians.bedlogic.util.JsonWrapper;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.bedrosians.bedlogic.domain.item.Item;

public class ItemWrapper {

	 private Item item;
	 
	 public ItemWrapper(){}
	 
	 public ItemWrapper(Item item){
		 this.item = item;
	 }
	 @JsonProperty("item")
	 public Item getItem() {
	     return item;
	 }

	 public void setItem(Item item) {
	     this.item = item;
	 }
	
}
