package com.bedrosians.bedlogic.domain.ims.enums;

public enum DesignLook implements java.io.Serializable {

		 Wood("Wood"),
		 Travertine("Travertine");
	   
		 private String description;
		 
		 private DesignLook(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
		 }

		 public void setDescription(String description){
			 this.description = description;
		 }
				
		 public static DesignLook instanceOf(String description){
			 DesignLook instance = null;
			 switch(description) {
				 case("Wood"): case("WOOD"):
					 instance = Wood;
				     break;
				 case("Travertine"): case("TRAVERTINE"):
					 instance = Travertine;
				     break;    
			 }	   
			 return instance;
		 }

}
