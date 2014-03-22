package com.bedrosians.bedlogic.domain.item.enums;

public enum MaterialStyle {

	Beak("Beak"),
	Cove_Base("Cove Base"),
	Cove_Base_Inside_Corner("Cove Base Inside Corner"),
	Cove_Base_Outside_Corner("Cove Base Outside Corner"),
	Cane("Cane"),
	Chair_Rail("Chair Rail"),
	Quarter_Round("Quarter Round"),
	Surface("Surface"),
	Surface_Corner("Surface Corner"),
	Surface_Corner_Right("Surface Corner Right"),
	Surface_Corner_Left("Surface Corner Left"),
	V_Cap("V-Cap"),
	V_Cap_Corner("V-Cap Corner"),
	Wall("Wall");
	
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
