package com.bedrosians.bedlogic.util.enums;

public enum RoleDomain implements java.io.Serializable {

	 ALL("ALL"),
	 IMS("IMS");
    
	 private String description;
		 
	 RoleDomain(String description){
		 this.description = description;
	 }
	 public String getDescription(){
		 return description;
	 }

	 public void setDescription(String description){
		this.description = description;
	}
			
}