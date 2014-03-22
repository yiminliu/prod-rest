package com.bedrosians.bedlogic.domain.item.enums;

public enum MaterialClass {

	Ceramic_Tile_Manufactured("Ceramic Tile / Manufactured"),
	Ceramic_Tile_Sourced("Ceramic Tile Sourced"),
	Decorative_Products("Decorative Products"),
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
