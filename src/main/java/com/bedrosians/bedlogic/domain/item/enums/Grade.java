package com.bedrosians.bedlogic.domain.item.enums;

public enum Grade {

		 First("First"),
	     Second("Second"),
	     Third("Third");
		 
		 private String description;
		 
		 private Grade(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
		 }
		 
		 public static Grade instanceOf(String description){
			 Grade grade = null;
			 switch(description) {
				 case("First"): case("FIRST"):
				    grade = First;
				    break;
				 case("Second"): case("SECOND"):
				    grade = Second;
				    break;
				 case("Third"): case("THIRD"):
					grade = Third;
				    break;	    
			 }	   
			 return grade;
		 }

}
