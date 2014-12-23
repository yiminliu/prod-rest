package com.bedrosians.bedlogic.domain.ims.enums;

public enum MaterialCategory implements java.io.Serializable {

	Medallion("Medallion"),
	Deco("Deco"),
	Tool("Tool"),
	Catalog("Catalog"),
	SettingMat("SettingMat"),
	Tear_Sheet("Tear Sheet"),
	Board("Board"),
	Showroom_Board("Showroom Board"), 
	Contractor_Board("Contractor Board"),
	Feature_Board("Feature Board"),
	Label("Label"),
	Listello("Listello"),
	Trim("Trim"),
	SWA("SWA"),
	Tile("Tile"),
	Allied("Allied"),
	Paver("Paver"),
	Mosaic("Mosaic"),
	Slab("Slab"),
	Pack_Out("Pack Out"),
	Display("Display"),
	ARC("ARC"),
	Ledger("Ledger");
		
	
	private String description;
		 
	private MaterialCategory(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
		
	public static MaterialCategory instanceOf(String key){
		for(MaterialCategory instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
