package com.bedrosians.bedlogic.domain.product.enums;

public enum DesignLook {

		 Wood("Wood"),
		 Travertine("Travertine");
	   
		 private String description;
		 
		 private DesignLook(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
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
