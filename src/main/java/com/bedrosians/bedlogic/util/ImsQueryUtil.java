package com.bedrosians.bedlogic.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

public class ImsQueryUtil {

	public static String getValue(MultivaluedMap<String, String> queryParams, String searchKey) {	
	    Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
		Iterator<Entry<String, List<String>>> it = set.iterator(); 
		String key = null;
		while(it.hasNext()) {
		   	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
		   	key = (String)entry.getKey();
		   	if(key.equalsIgnoreCase(searchKey))
		   	   return ((List<String>)entry.getValue()).get(0);
		} 
		return null;
	}

	
	public static String getItemCode(MultivaluedMap<String, String> queryParams){
    	return getValue(queryParams, "itemcode") != null ? getValue(queryParams, "itemcode") : 
    	  	   getValue(queryParams, "itemCode") != null ? getValue(queryParams, "itemCode") :
    		   getValue(queryParams, "itemcd") != null ? getValue(queryParams, "itemcd") : 
    		   getValue(queryParams, "itemCd") != null ? getValue(queryParams, "itemCd") :
    		   null;    		         
    }
	
	public static int getMaxResults(MultivaluedMap<String, String> queryParams){
		int maxResults = 0;
		String data = getValue(queryParams, "maxResults");
		if(data != null && !data.isEmpty()){
			try {
			    maxResults = Integer.parseInt(data);
			}
			catch(NumberFormatException e){
				//e.printStackTrace();
			}
		}
		return maxResults;
	}

}
