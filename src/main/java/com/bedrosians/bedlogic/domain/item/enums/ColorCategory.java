package com.bedrosians.bedlogic.domain.item.enums;

public enum ColorCategory {

	Almond("Almond"),
	Beige("Beige"),
	Black("Black"),
	Blue("Blue"),
	Brown("Brown"),
	Cream("Cream"),
	Gold("Gold"),
	Green("Green"),
	Gray("Gray"),
	Orange("Orange"),
	Peach("Peach"),
	Pink("Pink"),
	Red("Red"),
	Silver("Silver"),
	Tan("Tan"),
	Taupe("Taupe"),
	Walnut("Walnut"),
	White("White"),
	Yellow("Yellow");
	
	private String description;
		 
	private ColorCategory(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static ColorCategory instanceOf(String key){
		for(ColorCategory instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
