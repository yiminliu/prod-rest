package com.bedrosians.bedlogic.domain.ims.enums;

public enum MaterialType implements java.io.Serializable {

	Brick("Brick"),
	Ceramic("Ceramic"),
	Decorative("Decorative"),
	Engineered_Quartz("Engineered Quartz"),
	Glass("Glass"),
	Glass_Block("Glass Block"),
	Porcelain("Porcelain"),
	Pebble_Rock("Pebble Rock"),
	Quarry("Quarry"),
	Resin_Metal("Resin/Metal"),
	Other("Other");
		
	
	private String description;
		 
	private MaterialType(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static MaterialType instanceOf(String key){
		for(MaterialType instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
