package com.bedrosians.bedlogic.domain.item.enums;

public enum Edge {

	Rectified("Rectified"),
	Squared("Squared"),
	Chiseled("Chiseled"),
	Antiquated("Antiquated"),
	Tapered("Tapered"),
	Tumbled("Tumbled"),
	Beveled("Beveled"),
	Cushioned("Cushioned"),
	Pillowed("Pillowed"),
	Staggard("Staggard");
	
	
	private String description;
		 
	private Edge(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static Edge instanceOf(String key){
		for(Edge edge : values()){
		    if(edge.getDescription().equalsIgnoreCase(key))
	            return edge;				        
		}
	    return null;
	}

}
