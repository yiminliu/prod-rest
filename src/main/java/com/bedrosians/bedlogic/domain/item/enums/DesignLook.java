package com.bedrosians.bedlogic.domain.item.enums;

public enum DesignLook {

		 WOOD("Wood"),
	     TRAVERTINE("Travertine");
	   
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
					 instance = WOOD;
				     break;
				 case("Travertine"): case("TRAVERTINE"):
					 instance = TRAVERTINE;
				     break;    
			 }	   
			 return instance;
		 }

}
