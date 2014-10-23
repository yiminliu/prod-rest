package com.bedrosians.bedlogic.domain.ims.enums;

public enum DesignStyle {

	Contemporary("Contemporary"),
	Modern("Modern"),
	Eclectic("Eclectic"),
	Traditional("Traditional"),
	Asian("Asian"),
	Beach_Style("Beach Style"),
	Craftsman("Craftsman"),
	Industrial("Industrial"),
	Mediterranean("Mediterranean"),
	Midcentury("Midcentury"),
	Rustic("Rustic"),
	Tropical("Tropical");
		 	   
    private String description;
		 
	private DesignStyle(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static DesignStyle instanceOf(String key){
	  for(DesignStyle designStyle : values()){
		  if(designStyle.getDescription().equalsIgnoreCase(key))
		     return designStyle;				        
	  }
	  return null;
	}

}
