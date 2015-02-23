package com.bedrosians.bedlogic.domain.ims.enums;

public enum Status implements java.io.Serializable {

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

 	 public void setDescription(String description){
		this.description = description;
	 }
				
	 public static Status instanceOf(String key){
		for(Status instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
		        return instance;				        
		}
	    return null;
	 }
}
