package com.bedrosians.bedlogic.dao.item;


import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.enums.Grade;
import com.bedrosians.bedlogic.domain.item.enums.Status;
import com.bedrosians.bedlogic.domain.item.enums.Body;
import com.bedrosians.bedlogic.domain.item.enums.Edge;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.item.enums.SurfaceType;
import com.bedrosians.bedlogic.domain.item.enums.DesignLook;
import com.bedrosians.bedlogic.domain.item.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.item.enums.MpsCode;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;

@Repository("itemDao")
public class ItemDaoImpl extends GenericDaoImpl<Item, String> implements ItemDao {
					
    @Autowired
	private SessionFactory sessionFactory;
	 
	//private static final int OFF_SET = 0;
	private static final int MAX_RESULTS = 500;
 
     
	@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(readOnly=true)
	public Item getItemById(final String itemId) {
       return findById(sessionFactory.openSession(), itemId);
	}
	
	@Override
	@Loggable(value = LogLevel.TRACE)
	public Item loadItemById(Session session, final String itemId) {
       return loadById(session, itemId);
	}
 
 
   @Override
   @Loggable(value = LogLevel.TRACE)
   @Transactional(readOnly=true)	
   public List<Item> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams){
	   if(queryParams == null) 
		   return null;
		List<Item> items = null;
		boolean exactMatch = queryParams.containsKey("exactMatch")? true : false;
		Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
	    Iterator it = set.iterator();
	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Item.class);
	    Criteria newFeatureCriteria = null;
	    Criteria vendorCriteria = null;
	  	criteria.setReadOnly(true);
	  	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
   	   	String key, value = null;
	   	while(it.hasNext()) {
   	    	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
   		   	key = (String)entry.getKey();
   		  		   
   		   	if(key == null && key.trim().length() == 0)
   	    	   throw new IllegalArgumentException("Invalid field name in query.");
   		
   		 	if("itemcd".equalsIgnoreCase(key) || "itemCode".equalsIgnoreCase(key) || "itemId".equalsIgnoreCase(key))
   		   		key = "itemcode";
   		    if("materialCategory".equalsIgnoreCase(key) || "matcategory".equalsIgnoreCase(key) || "matCategory".equalsIgnoreCase(key))
		   		key = "materialcategory";
   		    if("colorCategory".equalsIgnoreCase(key))
		   		key = "colorcategory";
		    if("seriesName".equalsIgnoreCase(key))
		   		key = "seriesname";
		    if("length".equalsIgnoreCase(key) || ("nominallength".equalsIgnoreCase(key)) || ("nominalLength".equalsIgnoreCase(key)))
		   		key = "nmLength";
		    if("width".equalsIgnoreCase(key) || ("nominalwidth".equalsIgnoreCase(key)) || ("nominalWidth".equalsIgnoreCase(key)))
		   		key = "nmWidth";
		    if("thickness".equalsIgnoreCase(key) || ("nominalthickness".equalsIgnoreCase(key)) || ("nominalThickness".equalsIgnoreCase(key)))
		   		key = "nmThickness";		
   		   	List<String> values = entry.getValue();
   		   	
   		   //for(String value : ((List<String>)entry.getValue())) {
   		  //for (int i = 0; i < values.size(); i++) {
   		    if(values != null) {
   		   	   value = values.get(0);	
   	   
   		   	   if(ImsNewFeature.allProperties().contains(key)) {	
   		   		   if(newFeatureCriteria == null)
   		   		      newFeatureCriteria = criteria.createCriteria("imsNewFeature", Criteria.LEFT_JOIN);
   		   			
   		   		    if("grade".equalsIgnoreCase(key))
   		   		        newFeatureCriteria.add(Restrictions.eq(key, Grade.instanceOf(value)));
   		   			else if("status".equalsIgnoreCase(key))
		   		        newFeatureCriteria.add(Restrictions.eq(key, Status.instanceOf(value)));
   		   		    else if("body".equalsIgnoreCase(key))
	   		            newFeatureCriteria.add(Restrictions.eq(key, Body.instanceOf(value)));
   		   		    else if("edge".equalsIgnoreCase(key))
	   		            newFeatureCriteria.add(Restrictions.eq(key, Edge.instanceOf(value)));
		   		    else if("mpsCode".equalsIgnoreCase(key))
			   		    newFeatureCriteria.add(Restrictions.eq(key, MpsCode.instanceOf(value)));
				    else if("surfaceApplication".equalsIgnoreCase(key))
		   		        newFeatureCriteria.add(Restrictions.eq(key, SurfaceApplication.instanceOf(value)));
		   		    else if("surfaceFinish".equalsIgnoreCase(key))
	   		            newFeatureCriteria.add(Restrictions.eq(key, SurfaceFinish.instanceOf(value)));
		   		    else if("surfaceType".equalsIgnoreCase(key))
	   		            newFeatureCriteria.add(Restrictions.eq(key, SurfaceType.instanceOf(value)));	
		   		    else if("designLook".equalsIgnoreCase(key))
	   		            newFeatureCriteria.add(Restrictions.eq(key, DesignLook.instanceOf(value)));
		   		    else if("designStyle".equalsIgnoreCase(key))
	   		            newFeatureCriteria.add(Restrictions.eq(key, DesignStyle.instanceOf(value)));
   		   	    	continue;
   		   	   }
   		   		
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
	    	       criteria.add(Restrictions.gt("nmLength", 0F)); 	
	    	   }
   		   	   else if("widthmax".equalsIgnoreCase(key)){
	    	       criteria.add(Restrictions.le("nmWidth", Float.parseFloat(value))); 
	    	       criteria.add(Restrictions.gt("nmWidth", 0F));
	    	   }
   		   	   else if("pricemax".equalsIgnoreCase(key)){
	    	       criteria.add(Restrictions.le("price", new BigDecimal(value))); 	
	    	   }
   		   	   else if("size".equalsIgnoreCase(key) || "sizes".equalsIgnoreCase(key)){
   		   		   criteria = parseSize(criteria, values);
   		   	   }
   		       else{
   	    		   if(values.size() > 1 )
   	    		      criteria.add(Restrictions.in(key, (String[])values.toArray()));
   	    		   else {
   	    		         if(!exactMatch && ("itemcode".equalsIgnoreCase(key) ||
   	    		        		            "itemdesc1".equalsIgnoreCase(key) || 
   	    		        		            "color".equalsIgnoreCase(key)  || 
   	    		        		            "colorcategory".equalsIgnoreCase(key) ||
   	    		        		            "seriesname".equalsIgnoreCase(key)  || 
   	    		        		            "materialcategory".equalsIgnoreCase(key))) {
   	    			        criteria.add(Restrictions.like(key, value+"%").ignoreCase());
   	    		         }   
   	    		         else {
   	    		        	if (key.contains("price") || key.endsWith("cost") || key.endsWith("Cost"))
     	    		    	    criteria.add(Restrictions.eq(key, new BigDecimal(value))); 
   	    		        	else if (key.toUpperCase().endsWith("RATIO") || 
   	    		        			 key.toUpperCase().endsWith("PCT") || key.toUpperCase().endsWith("PCT1") ||  key.toUpperCase().endsWith("PCT2") ||  key.toUpperCase().endsWith("PCT3") ||
   	    		        			 key.toUpperCase().endsWith("PERCENT") ||
   	    		        			 "nmLength".equalsIgnoreCase(key) || "nmWidth".equalsIgnoreCase(key) || 
   	    		            	     key.contains("scof") || key.contains("dcof") || key.contains("Consummer") || "scratchResistance".equalsIgnoreCase(key) || 
   	    		            	     "waterAbsorption".equalsIgnoreCase(key) || "peiAbrasion".equalsIgnoreCase(key) || "moh".equalsIgnoreCase(key))
   	    		    	          criteria.add(Restrictions.eq(key, Float.parseFloat(value))); 
   	    		              //else if (key.equalsIgnoreCase("vendorId") || key.contains("vendornbr"))
   	    		              //	  vendorCriteria.add(Restrictions.eq(key, Long.parseLong(value))); 
   	    		           	  else 
   	        	                  criteria.add(Restrictions.eq(key, value).ignoreCase());
   	    		         }     
   	               }
   	    	   }
   		    }   
	  	}
	  	criteria.addOrder(Order.asc("itemcode"));
	  	criteria.setMaxResults(MAX_RESULTS);
	  	System.out.println("criteria = " +criteria.toString());
	  	items =  (List<Item>)criteria.list();			
	  	return items;
	
    }
	  
	@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public String createItem(Item item){
		return (String)save(sessionFactory.getCurrentSession(), item); 
	}
	
	@Override
	@Loggable(value = LogLevel.TRACE)
	public void updateItem(Session session, Item item){
		update(session, item);
	}
	
	private Criteria parseSize(Criteria criteria, List<String> values){
	   		   	
		Disjunction disjunction = Restrictions.disjunction();
		for(String value : values) {
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
	    		   LogicalExpression andExpression = Restrictions.and(
	   					                       		                  Restrictions.eq("nmLength", Float.parseFloat(length)),
	   					                       		                  Restrictions.eq("nmWidth", Float.parseFloat(width))
	   					                                             ); 
	    		   disjunction.add(andExpression);
	          }  
	   	      criteria.add(disjunction);
	   	}
		return criteria;
   }
}
