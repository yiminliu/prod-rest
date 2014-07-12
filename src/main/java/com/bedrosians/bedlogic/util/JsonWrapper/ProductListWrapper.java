package com.bedrosians.bedlogic.util.JsonWrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductListWrapper {

	 private List<ProductWrapper> list;
	 
	 public ProductListWrapper(){}
	 
	 public ProductListWrapper(List<ProductWrapper> list){
		 this.list = list;
	 }
	 
	 @JsonProperty("products")
	 public List<ProductWrapper> getList() {
	     return list;
	 }

	 public void setList(List<ProductWrapper> list) {
	     this.list = list;
	 }
	
}
