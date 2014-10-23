package com.bedrosians.bedlogic.util.enums;

public enum ApiName {

	IMS("Ims"),
	ACCOUNT("Account");
		
	private String description;
		 
	private ApiName(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static ApiName instanceOf(String key){
		for(ApiName name : values()){ 
		    if(name.getDescription().equalsIgnoreCase(key))
	            return name;				        
		}
	    return null;
	}

}
