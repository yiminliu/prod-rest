package com.bedrosians.bedlogic.domain.ims.enums;

public enum MaterialStyle {

	Beak("Beak"),
	CB("Cove Base"),
	CBCI("Cove Base Inside Corner"),
	CBCO("Cove Base Outside Corner"),
	Cane("Cane"),
	CR("Chair Rail"),
	QR("Quarter Round"),
	SF("Surface"),
	SFC("Surface Corner"),
	SFCR("Surface Corner Right"),
	SFCL("Surface Corner Left"),
	VC("V-Cap"),
	VCC("V-Cap Corner"),
	WL("Wall");
	
	private String description;
		 
	private MaterialStyle(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static MaterialStyle instanceOf(String key){
		for(MaterialStyle instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
