package com.bedrosians.bedlogic.dao.item;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;


@Repository("itemDao")
public class ItemDaoImpl extends GenericDaoImpl<Item, String> implements ItemDao {
					
	 private static final int OFF_SET =  0;
	 private static final int LIMIT = 500;
 
     
	@Override
	public Item getItemById(String itemId) {
       return findById(itemId);//branch info cannot be linked to the item.
		//List<Item> list = findByParameter("itemcd", itemId);
	//	if(list != null && !list.isEmpty())
//		   return list.get(0);
//		else return null;
	}
	
   public List<Item> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams){
	   //return findByParameters(queryParams);
	   if(queryParams == null) 
		   return null;
		
		List<Item> items = null;
		
		boolean exactMatch = queryParams.containsKey("exactMatch")? true : false;
		
		Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
	    Iterator it = set.iterator();
	    Criteria criteria = currentSession().createCriteria(Item.class);
	  	criteria.setReadOnly(true);
	  	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
   	   	String key, value = null;
	   	while(it.hasNext()) {
   	    	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
   		   	key = (String)entry.getKey();
   		   
   		   	if(key == null && key.trim().length() == 0)
   	    	   throw new IllegalArgumentException("Invalid field name in query.");
   		
   		   	List<String> values = entry.getValue();
   		   	
   		   //for(String value : ((List<String>)entry.getValue())) {
   		   // for (int i = 0; i < size; i++) {
   		    if(values != null) {
   		   	   value = values.get(0);	
   	   
   		       if("exactMatch".equalsIgnoreCase(key))
   		   	       continue;
   		   	   if("lengthmin".equalsIgnoreCase(key)){
   	    	       criteria.add(Restrictions.ge("nmLength", Float.parseFloat(value))); 	
   	    	   }
   		   	   else if("widthmin".equalsIgnoreCase(key)){
	    	       criteria.add(Restrictions.ge("nmWidth", Float.parseFloat(value))); 	
	    	   }
   		   	   else if("lengthmax".equalsIgnoreCase(key)){
	    	       criteria.add(Restrictions.le("nmLength", Float.parseFloat(value))); 	
	    	   }
   		   	   else if("widthmax".equalsIgnoreCase(key)){
	    	       criteria.add(Restrictions.le("nmWidth", Float.parseFloat(value))); 	
	    	   }
   		   	   else if("size".equalsIgnoreCase(key) || "sizes".equalsIgnoreCase(key)){
   		   		   boolean lengthXwidth = true;
   		    	   String[] sizeArray = value.split(",");
   		    	   String length, width;
   		    	   for(String size : sizeArray){
   		    	       String[] lengthAndWidth = size.split("X");
   		    		   if(lengthXwidth){
   		    		      length = lengthAndWidth[0];
   		    	          width = lengthAndWidth[1];
   		    		   }
   		    		   else{
   		    		       length = lengthAndWidth[1];
 		    	           width = lengthAndWidth[0];	
   		    		   }
   		    	       criteria.add(Restrictions.eq("nmLength", Float.parseFloat(length))); 
   		       	       criteria.add(Restrictions.eq("nmWidth", Float.parseFloat(length))); 
   		    	   }     
   		       }
   		       else{
   	    		   if(values.size() > 1 )
   	    		      criteria.add(Restrictions.in(key, (String[])values.toArray()));
   	    		   else {
   	    		         if(!exactMatch && ("itemcd".equalsIgnoreCase(key) || "description".equalsIgnoreCase(key) || "color".equalsIgnoreCase(key) || "materialCategory".equalsIgnoreCase(key))) 
   	    			        criteria.add(Restrictions.like(key, value+"%").ignoreCase());
   	    		         else {
   	    		              if (key.contains("price") || key.contains("ratio") || key.contains("Consummer") ||"nmLength".equalsIgnoreCase(key) || "nmWidth".equalsIgnoreCase(key) || 
   	    		            	  key.contains("scof") || key.contains("dcof") || key.contains("Consummer") || "scratchResistance".equalsIgnoreCase(key) || 
   	    		            	  "waterAbsorption".equalsIgnoreCase(key) || "peiAbrasion".equalsIgnoreCase(key) || "moh".equalsIgnoreCase(key))
   	    		    	          criteria.add(Restrictions.eq(key, Float.parseFloat(value))); 
   	    		              else 
   	        	                  criteria.add(Restrictions.eq(key, value).ignoreCase());
   	    		         }     
   	               }
   	    	   }
   		    }   
	  	}
	  	criteria.addOrder(Order.asc("itemcd"));
	  	criteria.setMaxResults(LIMIT);
	  	System.out.println("criteria = " +criteria.toString());
	  	items =  (List<Item>)criteria.list();			
	  	return items;
	
   }
	
	@Override
	public void updateItem(Item item){
		update(item);
	}
	  
	@Override
	public String createItem(Item item){
		//return (String)save(item); 
		return (String)currentSession().save(item); 
	}
}
