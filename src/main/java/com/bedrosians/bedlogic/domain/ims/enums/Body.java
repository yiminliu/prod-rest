package com.bedrosians.bedlogic.domain.ims.enums;

public enum Body implements java.io.Serializable {
	
	Double_Loaded("Double Loaded"),
	Soluable_Salt("Soluable Salt"),
	Thru_Body("Thru Body"),
	Color_Body("Color Body"),
	Red_Body("Red Body"),
	White_Body("White Body"),
	Composite_Body("Composite Body"),
	Porcelain_Body_Stone_Face("Porcelain Body Stone Face");
		 
	private String description;
		 
	private Body(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
		 
	public static Body instanceOf(String key){
		for(Body body : values()){ 
		    if(body.getDescription().equalsIgnoreCase(key))
	            return body;				        
		}
	    return null;
	}

}
