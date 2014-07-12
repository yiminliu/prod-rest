package com.bedrosians.bedlogic.domain.product.enums;

public enum Status {

		 Best("Best"),
	     Better("Better"),
	     Good("Good");
		 
		 private String description;
		 
		 Status(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
		 }

		 public static Status instanceOf(String description){
			 Status status = null ;
			 switch(description) {
				 case("Best"): case("BEST"): case("best"):
				    status = Best;
				    break;
				 case("Better"): case("BETTER"): case("better"):
				    status = Better;
				    break;
				 case("Good"): case("GOOD"): case("good"):
					status = Good;
				    break;	    
			 }	   
			 return status;
		 }
}
