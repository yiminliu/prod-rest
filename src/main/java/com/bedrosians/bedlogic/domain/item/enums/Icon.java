package com.bedrosians.bedlogic.domain.item.enums;

public enum Icon {

	Post_Recycled("Post Recycled"),
	Pre_Recycled("Pre Recycled"),
	Exterior_Product("Exterior Product"),
	ADA_Accessibility("ADA Accessibility"),
	Pos_Consumer("Pos Consumer"),
	Pre_Consumer("Pre Consumer"),
	Green_Friendly("Green Friendly"),
	Lead_Point("Lead Point");
	 
	private String description;
		 
	private Icon(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static Icon instanceOf(String key){
		for(Icon instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
