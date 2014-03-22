package com.bedrosians.bedlogic.domain.item.enums;

public enum SurfaceFinish {

	Antiquated("Antiquated"),
	Filled_And_Honed("Filled & Honed"),
	Fully_Gauged("Fully Gauged"),
	Honed("Honed"),
	Metallic("Metallic"),
	Nat_Clef("Nat. Clef"),
	Stain("Stain"),
	Tumbled("Tumbled"),
	Textured("Textured");
	
	 
	private String description;
		 
	private SurfaceFinish(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static SurfaceFinish instanceOf(String key){
		for(SurfaceFinish instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
