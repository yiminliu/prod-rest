package com.bedrosians.bedlogic.util.enums;

public enum DBOperation {

	SEARCH("Search"),
	CREATE("Create"),
	UPDATE("Update"),
	DELETE("Delete"),
	CLONE("Clone");
		 
	
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
