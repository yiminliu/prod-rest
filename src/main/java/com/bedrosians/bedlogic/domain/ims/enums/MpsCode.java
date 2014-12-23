package com.bedrosians.bedlogic.domain.ims.enums;

public enum MpsCode implements java.io.Serializable {

		 New_Product("New Product"),
		 Active_Product("Active Product"),
		 Pre_Drop("Pre Drop"),
	     Drop("Drop"),
	     Obsolete("Obsolete"),
	     Do_Not_Inventory("Do Not Inventory"),
	     Second_Promo("Second/Promo"),
	     Claims_Inventory("Claims Inventory"),
	     Direct_Ship("Direct Ship"),
	     Special_Order("Special Order");
	   		 
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
					 instance = New_Product;
				     break;
				 case("Active Product"): case("Active"): case("Active_Product"):
				     instance = Active_Product;
			         break;    
				 case("Pre Drop"): case("Pre_Drop"):
					 instance = Pre_Drop;
				     break;   
				 case("Drop"): case("drop"):
					 instance = Drop;
				     break;     
				 case("Obsolete"): case("obsolete"):
					 instance = Obsolete;
				     break;     
				 case("Do Not Inventory"): case("Do_Not_Inventory"):
				     instance = Do_Not_Inventory;
			         break;
			     case("Second/Promo"): case("Second_Promo"):
			         instance = Second_Promo;
		             break;    
			     case("Claims Inventory"): case("Claims_Inventory"):
				     instance = Claims_Inventory;
			         break;   
			     case("Direct Ship"): case("Direct_Ship"):
				     instance = Direct_Ship;
			         break;     
			     case("Special Order"): case("Special_Order"):
				     instance = Special_Order;
			         break;
			     
			 }	   
			 return instance;
		 }
}
