package com.bedrosians.bedlogic.domain.ims.enums;

public enum Color implements java.io.Serializable {

	ALMOND("Almond"),
	BEIGE("Beige"),
	BLACK("Black"),
	BLUE("Blue"),
	BROWN("Brown"),
	CLEAR("Clear"),
	CREAM("Cream"),
	GOLD("Gold"),
	GREEN("Green"),
	GRAY("Gray"),
	ORANGE("Orange"),
	PEACH("Peach"),
	PINK("Pink"),
	RED("Red"),
	REST("Rust"),
	SALMON("Salmon"),
	SILVER("Silver"),
	TAN("Tan"),
	TAUPE("Taupe"),
	WALNUT("Walnut"),
	WHITE("White"),
	YELLOW("Yellow");
	
	private String description;
		 
	private Color(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
		 
	public static Color instanceOf(String key){
		if(key == null || key.length() == 0)
		   return null;	
		for(Color instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
