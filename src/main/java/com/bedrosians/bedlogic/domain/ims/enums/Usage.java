package com.bedrosians.bedlogic.domain.ims.enums;

public enum Usage implements java.io.Serializable {

	 F("Floor"),
     W("Wall"),
     S("Shower"),
	 P("Pool/Spa"),
	 X("Exterior"),
	 C("Counter Top");
		 
	 private String description;
		 
	 Usage(String description){
		 this.description = description;
	 }
	 public String getDescription(){
		 return description;
	 }

	 public void setDescription(String description){
		this.description = description;
	}
			
}