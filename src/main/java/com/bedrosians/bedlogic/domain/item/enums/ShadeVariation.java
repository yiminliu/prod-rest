package com.bedrosians.bedlogic.domain.item.enums;

public enum ShadeVariation {

		   V1("Low"),
		   V2("Medium"),
		   V3("High"),
		   V4("Extreme");
		 
		 private String description;
		 
		 ShadeVariation(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
		 }

	
}
