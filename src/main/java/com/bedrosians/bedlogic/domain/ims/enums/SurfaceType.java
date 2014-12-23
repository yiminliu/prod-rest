package com.bedrosians.bedlogic.domain.ims.enums;

public enum SurfaceType implements java.io.Serializable {

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

	public void setDescription(String description){
		this.description = description;
	}
		
	public static SurfaceType instanceOf(String key){
		for(SurfaceType instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
