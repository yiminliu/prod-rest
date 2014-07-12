package com.bedrosians.bedlogic.domain.product.enums;

public enum SurfaceType {

	Abrasive("Abrasive"),
	Flamed("Flamed"),
	Glazed("Glazed"),
	Polished("Polished"),
	Resined("Resined"),
	Unglazed("Unglazed"),
	Vein_Cut("Vein Cut"),
	Cross_Cut("Cross Cut");
	
	private String description;
		 
	private SurfaceType(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static SurfaceType instanceOf(String key){
		for(SurfaceType instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
