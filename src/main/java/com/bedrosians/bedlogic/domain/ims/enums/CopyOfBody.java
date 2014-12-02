package com.bedrosians.bedlogic.domain.ims.enums;

public enum CopyOfBody {

	Double_Loaded("Double Loaded"),
	Soluable_Salt("Soluable Salt"),
	Thru_Body("Thru Body"),
	Color_Body("Color Body"),
	Red_Body("Red Body"),
	White_Body("White Body"),
	Composite_Body("Composite Body"),
	Porcelain_Body_Stone_Face("Porcelain Body Stone Face");
		 
	
	private String description;
		 
	private CopyOfBody(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static CopyOfBody instanceOf(String key){
		for(CopyOfBody body : values()){ 
		    if(body.getDescription().equalsIgnoreCase(key))
	            return body;				        
		}
	    return null;
	}

}
