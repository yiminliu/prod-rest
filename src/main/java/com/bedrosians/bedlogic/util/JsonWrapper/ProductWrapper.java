package com.bedrosians.bedlogic.util.JsonWrapper;

import com.bedrosians.bedlogic.domain.product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ProductWrapper {

	 private Product product;
	 
	 public ProductWrapper(){}
	 
	 public ProductWrapper(Product product){
		ObjectMapper mapper = new  ObjectMapper(); 
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true); //version2.0
		this.product = product;
	 }
	 
	 @JsonProperty("product")
	 public Product getProduct() {
	     return product;
	 }

	 public void setProduct(Product product) {
	     this.product = product;
	 }
	
}
