package com.bedrosians.bedlogic.domain.product.enums;

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
		     case("Tax"): case("tax"): case("T"): case("t"):
				 instance = T;
			     break;
			 case("Non_Tax"): case("Non-Tax"): case("non_tax"): case("non-tax"): case("N"):  case("n"):
				 instance = N;
			     break;   
			 case("Exempt"): case("exempt"):  case("E"):
				 instance = E;
			     break;     
		 }	   
		 return instance;
	 }
	
}
