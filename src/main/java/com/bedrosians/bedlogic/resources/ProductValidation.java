package com.bedrosians.bedlogic.resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

public class ProductValidation {

	public boolean validate(MultivaluedMap<String,String> queryParams){
		
		Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator it = set.iterator();
		
		String key, value = null;
		while(it.hasNext()) {
		    Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		    key = (String)entry.getKey();
		    value = ((List<String>)entry.getValue()).get(0);
		    if("activityStatus".equalsIgnoreCase(key)) 
		    	validateStatus(value);
		    if(key != null && key.startsWith("unit")  && key.endsWith("unit")) 
		    	validateUnit(value);
 		}
		return false;
   }

	public boolean validateStatus(String status){
		 return (status.contains("N") || status.contains("Y") || status.contains("D")) ? true : false;			
	}
	
    public boolean validateUnit(String unit){
		return (unit.length() > 4) ? false : true;
   }
    
    public String generateSql(MultivaluedMap<String,String> map){
    	String sql = "";
    	
    	Set<Map.Entry<String, List<String>>> set = map.entrySet();
    	Iterator it = set.iterator();
    	String key, value = null; 
    	String[] strArray = new String[map.size()];
    	List<String> list = new ArrayList<>();
    	//while(it.hasNext()) {
    	  // Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
    	   //key = (String)entry.getKey();
    	   //value = ((List<String>)entry.getValue()).get(0); 
    	  // list.add(key+"=>"+value);
    	//}
    	//return (String[])list.toArray(); Arrays.
    	for(int i = 0; i < set.size(); i++) {
    		Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
     	   key = (String)entry.getKey();
     	   value = ((List<String>)entry.getValue()).get(0); 
     	   strArray[i] = key+"=>"+value;
    	}
    	return sql;
    }
		
	//public boolean validateVendorPriceUnit(){
	//}
}
