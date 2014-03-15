package com.bedrosians.bedlogic.domain.item.enums;

public enum Status {

		 BEST("Best"),
	     BETTER("Better"),
	     GOOD("Good");
		 
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
				 case("Best"): case("BEST"):
				    status = BEST;
				    break;
				 case("Better"): case("BETTER"):
				    status = BETTER;
				    break;
				 case("Good"): case("GOOD"):
					status = GOOD;
				    break;	    
			 }	   
			 return status;
		 }
}
