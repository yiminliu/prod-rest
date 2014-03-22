package com.bedrosians.bedlogic.domain.item.enums;

public enum DesignStyle {

		 CONTEMPORARY("Contemporary"),
	     MODERN("Modern"),
	     ELECTIC("Eclectic"),
	     TRADITIONAL("Traditional"),
  	     ASIAN("Asian"),
	     BEACH_STYLE("Beach Style"),
	     CRAFTSMAN("Craftsman"),
	     INDUSTRIAL("Industrial"),
	     MEDITERRANEAN("Mediterranean"),
	     MIDCENTURY("Midcentury"),
	     RUSTIC("Rustic"),
	     TROPICAL("Tropical");
		 
	   
		 private String description;
		 
		 private DesignStyle(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
		 }
		 
		 public static DesignStyle instanceOf(String description){
			 DesignStyle instance = null;
			 switch(description) {
				 case("Contemporary"): case("CONTEMPORARY"):
					 instance = CONTEMPORARY;
				     break;
				 case("Modern"): case("MODEN"):
					 instance = MODERN;
				     break; 
				 case("Eclectic"): case("ELECTIC"):
					 instance = ELECTIC;
				     break;
				 case("Asian"): case("ASIAN"):
					 instance = ASIAN;
				     break; 
				 case("Beach Style"): case("BEACH_STYLE"):
					 instance = BEACH_STYLE;
				     break;
				 case("Craftsman"): case("CRAFTSMAN"):
					 instance = CRAFTSMAN;
				     break; 
				 case("Industrial"): case("INDUSTRIAL"):
					 instance = CONTEMPORARY;
				     break;
				 case("Mediterranean"): case("MEDITERRANEAN"):
					 instance = MEDITERRANEAN;
				     break; 
				 case("Midcentury"): case("MIDCENTURY"):
					 instance = MIDCENTURY;
				     break; 
				 case("Rustic"): case("RUSTIC"):
					 instance = RUSTIC;
				     break;
				 case("Tropical"): case("TROPICAL"):
					 instance = MEDITERRANEAN;
				     break;      
			 }	   
			 return instance;
		 }

}
