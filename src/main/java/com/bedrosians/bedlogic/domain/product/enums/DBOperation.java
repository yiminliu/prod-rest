package com.bedrosians.bedlogic.domain.product.enums;

public enum DBOperation {

	SEARCH("Search"),
	CREATE("Create"),
	UPDATE("Update"),
	DELETE("Delete");
		 
	
	private String description;
		 
	private DBOperation(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static DBOperation instanceOf(String key){
		for(DBOperation body : values()){ 
		    if(body.getDescription().equalsIgnoreCase(key))
	            return body;				        
		}
	    return null;
	}

}
