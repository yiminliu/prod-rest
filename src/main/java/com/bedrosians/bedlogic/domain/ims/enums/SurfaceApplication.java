package com.bedrosians.bedlogic.domain.ims.enums;

public enum SurfaceApplication implements java.io.Serializable {

	Ink_Jet("Ink Jet"),
	Roto("Roto"),
	Silk("Silk"),
	Hand_Painted_Crafted("Hand Painted/Crafted");
	

	
	private String description;
		 
	private SurfaceApplication(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static SurfaceApplication instanceOf(String key){
		for(SurfaceApplication instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
