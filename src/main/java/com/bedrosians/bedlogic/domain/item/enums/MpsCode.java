package com.bedrosians.bedlogic.domain.item.enums;

public enum MpsCode {

		 NEW_PRODUCT("New Product"),
	     ACTIVE_PRODUCT("Active Product"),
	     PRE_DROP("Pre Drop"),
	     DROP("Drop"),
	     OBSOLET("Obsolete"),
	     DO_NOT_INVENTORY("Do Not Inventory"),
	     SECOND_PROMO("Second/Promo"),
	     CLAIMS_INVETORY("Claims Inventory"),
	     DIRECT_SHIP("Direct Ship"),
	     SPECIAL_OORDER("Special Order"),
	     NONE("N/A");
	   		 
		 private String description;
		 
		 MpsCode(){
		 }
		 
		 MpsCode(String description){
			 this.description = description;
		 }
		 public String getDescription(){
			 return description;
		 }
		 
		 public void setDescription(String description){
			 this.description = description;
		 }	
		 
		 public static MpsCode instanceOf(String description){
			 MpsCode instance = null;
			 switch(description) {
				 case("New Product"): case("New"): case("New_Product"):
					 instance = NEW_PRODUCT;
				     break;
				 case("Active Product"): case("Active"): case("Active_Product"):
				     instance = ACTIVE_PRODUCT;
			         break;    
				 case("Pre Drop"): case("Pre_Drop"):
					 instance = PRE_DROP;
				     break;   
				 case("Drop"): case("drop"):
					 instance = DROP;
				     break;     
				 case("Obsolete"): case("obsolete"):
					 instance = OBSOLET;
				     break;     
				 case("Do Not Inventory"): case("Do_Not_Inventory"):
				     instance = DO_NOT_INVENTORY;
			         break;
			     case("Second/Promo"): case("Second_Promo"):
			         instance = SECOND_PROMO;
		             break;    
			     case("Claims Inventory"): case("Claims_Inventory"):
				     instance = CLAIMS_INVETORY;
			         break;   
			     case("Direct Ship"): case("Direct_Ship"):
				     instance = DIRECT_SHIP;
			         break;     
			     case("Special Order"): case("Special_Order"):
				     instance = SPECIAL_OORDER;
			         break;
			     
			 }	   
			 return instance;
		 }
}
