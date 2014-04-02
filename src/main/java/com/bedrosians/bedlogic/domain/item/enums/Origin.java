package com.bedrosians.bedlogic.domain.item.enums;

public enum Origin {
        Italy("Italy"),
        USA("USA"),
        China("China"),
        Turkey("Turkey"),
        Mexico("Mexico");
		/* 
	      ('Germany'),
	      ('Namibia'),
	      ('S. Korea'),
	      ('South Africa'),
	      ('Peru'),
	      ('Madagascar'),
	      ('Angola'),
	      ('Iran'),      
	      ('Pakistan'),
	      ('Greece'),
	      ('Spain'),
	      ('India'),
	      ('Brazil'),
	      ('Morocco'),
	      ('Israel'),
	      ('Macedonia'),
	      ('Russia'),
	      ('Portugal'),
	      ('Finland'),
	      ('Taiwan'),
	      ('Egypt'),
	      ('Norway'),  
	      ('Saudi Arabia'), 
	      ('Canada'),
	      ('Armenia'),
	      ('');
		 */
		 private String description;
		 
		 private Origin(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
		 }
		 
		 public static Origin instanceOf(String key){
				for(Origin instance : values()){
				    if( instance.getDescription().equalsIgnoreCase(key))
			            return instance;				        
				}
			    return null;
			}

}
