package com.bedrosians.bedlogic.domain.item.enums;

public enum ProductOperation {

	SEARCH("Search"),
	CREATE("Create"),
	UPDATE("Update"),
	DELETE("Delete");
		 
	
	private String description;
		 
	private ProductOperation(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static ProductOperation instanceOf(String key){
		for(ProductOperation body : values()){ 
		    if(body.getDescription().equalsIgnoreCase(key))
	            return body;				        
		}
	    return null;
	}

}
