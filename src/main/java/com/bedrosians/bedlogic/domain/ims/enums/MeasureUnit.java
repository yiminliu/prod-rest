package com.bedrosians.bedlogic.domain.ims.enums;

public enum MeasureUnit {

	E("English"),
	M("Metric");
		 
	
	private String description;
		 
	private MeasureUnit(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static MeasureUnit instanceOf(String key){
		for(MeasureUnit body : values()){
		    if( body.getDeclaringClass().equals(key))
	            return body;				        
		}
	    return null;
	}

}
