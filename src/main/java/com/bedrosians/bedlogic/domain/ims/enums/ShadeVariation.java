package com.bedrosians.bedlogic.domain.ims.enums;

public enum ShadeVariation implements java.io.Serializable {

	V1("V1"),
	V2("V2"),
	V3("V3"),
	V4("V4");
	
	//V1("Low"),
	//V2("Medium"),
	//V3("High"),
	//V4("Extreme");
		 
	 private String description;
		 
	 ShadeVariation(String description){
		 this.description = description;
	 }
	 public String getDescription(){
		 return description;
	 }

	 public void setDescription(String description){
		this.description = description;
	 }
			
}
