package com.bedrosians.bedlogic.domain.ims.enums;

public enum Usage {

	 F("Floor"),
     W("Wall"),
     S("S"),
	 P("P"),
	 X("X"),
	 C("C");
		 
	 private String description;
		 
	 Usage(String description){
		 this.description = description;
	 }
	 public String getDescription(){
		 return description;
	 }
	 
}