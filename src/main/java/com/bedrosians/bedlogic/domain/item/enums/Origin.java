package com.bedrosians.bedlogic.domain.item.enums;

public enum Origin {

		 FIRST("First"),
	     SECOND("Second"),
	     THIRD("Third");
		/* 
		 ('Armenia'),
	      ('Germany'),
	      ('Namibia'),
	      ('S. Korea'),
	      ('Turkey'),
	      ('China'),
	      ('South Africa'),
	      ('Peru'),
	      ('Madagascar'),
	      ('Angola'),
	      ('Iran'),      
	      ('Italy'),
	      ('Pakistan'),
	      ('Greece'),
	      ('Spain'),
	      ('India'),
	      ('Brazil'),
	      ('Morocco'),
	      ('Israel'),
	      ('Macedonia'),
	      ('Russia'),
	      ('USA'),
	      ('Portugal'),
	      ('Mexico'),
	      ('Finland'),
	      ('Taiwan'),
	      ('Egypt'),
	      ('Norway'),  
	      ('Saudi Arabia'), 
	      ('Canada'),
	      ('');
		 */
		 private String description;
		 
		 private Origin(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
		 }
		 
		 public static Origin instanceOf(String description){
			 Origin grade = null;
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
