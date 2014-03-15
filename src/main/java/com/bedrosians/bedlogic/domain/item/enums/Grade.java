package com.bedrosians.bedlogic.domain.item.enums;

public enum Grade {

		 FIRST("First"),
	     SECOND("Second"),
	     THIRD("Third");
		 
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
				    grade = FIRST;
				    break;
				 case("Second"): case("SECOND"):
				    grade = SECOND;
				    break;
				 case("Third"): case("THIRD"):
					grade = THIRD;
				    break;	    
			 }	   
			 return grade;
		 }

}
