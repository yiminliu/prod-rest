package com.bedrosians.bedlogic.domain.ims.enums;

public enum OriginCountry implements java.io.Serializable {
	
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
		 
		 private OriginCountry(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
		 }

		 public void setDescription(String description){
			this.description = description;
		 }
				
		 public static OriginCountry instanceOf(String key){
				for(OriginCountry instance : values()){
				    if( instance.getDescription().equalsIgnoreCase(key))
			            return instance;				        
				}
			    return null;
			}

}
