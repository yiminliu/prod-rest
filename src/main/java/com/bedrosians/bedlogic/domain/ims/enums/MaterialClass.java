package com.bedrosians.bedlogic.domain.ims.enums;

public enum MaterialClass {

	CTMNF("Ceramic Tile / Manufactured"),
	CTSRC("Ceramic Tile Sourced"),
	DECOR("Decorative Products"),
	SAMPL("SAMPL");
	
	private String description;
		 
	private MaterialClass(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static MaterialClass instanceOf(String key){
		for(MaterialClass instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
