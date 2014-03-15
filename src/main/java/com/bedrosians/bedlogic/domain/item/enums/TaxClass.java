package com.bedrosians.bedlogic.domain.item.enums;

public enum TaxClass {

	 T("Tax"),
     N("Non_Tax"),
     E("Exempt");
		 
	 private String description;
		 
	 TaxClass(String description){
		 this.description = description;
	 }
	 public String getDescription(){
		 return description;
	 }
	 
	 public static TaxClass instanceOf(String description){
		 TaxClass instance = null;
		 switch(description) {
			 case("T"): case("t"):
				 instance = T;
			     break;
			 case("Non_Tax"): case("Non-Tax"):
				 instance = N;
			     break;   
			 case("Exempt"): case("exempt"):
				 instance = E;
			     break;     
		 }	   
		 return instance;
	 }
	
}
