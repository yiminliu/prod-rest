package com.bedrosians.bedlogic.dao.item;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.enums.Color;
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
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;

@Repository("itemDao")
public class ItemDaoImpl extends GenericDaoImpl<Item, String> implements ItemDao {
					
    @Autowired
	private SessionFactory sessionFactory;
	 
    private static final int MAX_RESULTS = 500;  
	//private static final int OFF_SET = 0;
	//private static final int TIMEOUT = 15;
 
     
	@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(readOnly=true)
	public Item getItemById(final String itemId) {
       //return findById(sessionFactory.getCurrentSession(), itemId);
		Query query = sessionFactory.getCurrentSession().createQuery("From Item where itemcode = :itemCode");
		query.setReadOnly(true);
		query.setString("itemCode", itemId);
		return (Item)query.uniqueResult();
	}
	
	@Override
	@Loggable(value = LogLevel.TRACE)
	public Item loadItemById(Session session, final String itemId) {
       return loadById(session, itemId);
	}
 
	@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(readOnly=true)	
	public List<Item> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams) throws BedDAOException{
	   if(queryParams == null) 
		   return null;
	   List<Item> items = null;
	   boolean exactMatch = queryParams.containsKey("exactMatch")? true : false;
	   Set<Map.Entry<String, List<String>>> entrySet = queryParams.entrySet();
	   Iterator<Map.Entry<String, List<String>>> it = entrySet.iterator();
	   Criteria newFeatureCriteria = null;
	   Criteria vendorCriteria = null;
	   Criteria noteCriteria = null;
       Criteria colorHueCriteria = null;
       Criteria itemCriteria = sessionFactory.getCurrentSession().createCriteria(Item.class);
	   String key, value = null;
	   List<String> values = null;
	   while(it.hasNext()) {
	     	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
	 	    key = normalizeKey((String)entry.getKey());
	 	   	values = entry.getValue();	
	 	    //----- multiple values case ------//
	 	   	if(values != null && !"size".equals(key) && (values.size() > 1 || values.contains(",") || values.indexOf(",") >= 0 || values.toArray().length > 1 || values.toString().contains(","))){
    		   itemCriteria.add(Restrictions.in(key, values));
	 	       //criteria.add(Restrictions.in(key, (String[])values.toArray()));
    		   continue;
	 	   	}   
    		value = values.get(0);
    		//------ do exact match -------//
    		if(exactMatch && ("itemcode".equalsIgnoreCase(key) || "color".equalsIgnoreCase(key)  || 
      		                  "seriesname".equalsIgnoreCase(key)  || "materialcategory".equalsIgnoreCase(key))){ 
	           itemCriteria.add(Restrictions.eq(key, value));
	           continue;
            }
	        else if(exactMatch && ("colorcategory".equalsIgnoreCase(key) || "colorhues".equalsIgnoreCase(key))){
	           itemCriteria.add(Restrictions.eq(key, value));
	           continue;
	        }  
	        else if(exactMatch && ("fulldesc".equalsIgnoreCase(key)) || "fulldescription".equalsIgnoreCase(key) || "description".equalsIgnoreCase(key) || "itemdescription".equalsIgnoreCase(key)){
		       itemCriteria.add(Restrictions.eq("itemdesc.fulldesc", value));
		       continue;
		    }  	
	        else if(exactMatch && "itemdesc1".equalsIgnoreCase(key)){
		       itemCriteria.add(Restrictions.eq("itemdesc.itemdesc1", value));
		       continue;
		    }  	
    		//------- add association criteria --------//
	 	   	if(ImsNewFeature.allProperties().contains(key)) {	
	   		   if(newFeatureCriteria == null)
	     	      newFeatureCriteria = itemCriteria.createCriteria("imsNewFeature");
	   	       newFeatureCriteria = addNewFeatureRestrictions(newFeatureCriteria, key, value);
	       	   continue;
		    }
	 	   	switch(key) {
	 	   	   //------- add association criteria --------//	    
	 	   	   case "colordescription": case "colorDescription":    
	   		       if(colorHueCriteria == null)
			       	  colorHueCriteria = itemCriteria.createCriteria("colorHues");
			       colorHueCriteria.add(Restrictions.eq(key, Color.instanceOf(value)));
			       break;
	 	   	   case "vendorid": case "vendorId": case "vendorNumber":
	   	           if(vendorCriteria == null)
	 		          vendorCriteria = itemCriteria.createCriteria("newVendorSystem");
			       vendorCriteria.add(Restrictions.eq("itemVendorId.vendorId", Integer.parseInt(value)));
			       break;
	 	   	   case "notetype": case "noteType":
	   		       if(noteCriteria == null)
	   		          noteCriteria = itemCriteria.createCriteria("newNoteSystem");
			       noteCriteria.add(Restrictions.eq(key, value).ignoreCase());
			       break;
	   	       // -------- add components Restrictions ------//
	 	   	   case "fulldesc": case "fullDesc": case "itemdescription": case "itemDescription": case "description":	//pattern match
	 		       itemCriteria.add(Restrictions.ilike("itemdesc.fulldesc", wildCard(value)));
	 		       break;
	 	   	   case "itemdesc1": case "itemDesc1":	
	 		       itemCriteria.add(Restrictions.ilike("itemdesc.itemdesc1", wildCard(value)));
	 		       break;
	 	   	   case "colorcategory": case "colorCategory": case "colorhues": case "colorHues": case "colorhue": case "colorHue":     
	 		      itemCriteria.add(Restrictions.ilike(key, wildCard(value)));
	 		      break;
	 	       case "materialtype":	case "materialType": 
	 	       case "materialcategory": case "materialCategory": 
	 	       case "materialstyle": case "materialStyle": 
	 	       case "materialfeature": case "materialFeature":
	 	       case "materialclass": case "materialClass":
	   		       itemCriteria.add(Restrictions.eq("material."+key , value).ignoreCase());
	   		       break;   
	  		   case "purchaser":	
	   		       itemCriteria.add(Restrictions.eq("purchasers.purchaser", value).ignoreCase());
	   		       break;
	 	   	   case "pricemax": case "priceMax":
	 	   		   itemCriteria.add(Restrictions.le("price.sellprice", new BigDecimal(value))); 
	 	           break;
	           //------ add comparison Restrictions ------// 
	 	   	   case "lengthmin": case "lengthMin":
	 	   		   itemCriteria.add(Restrictions.ge("nominallength", Float.parseFloat(value))); 
	 	   		   break;
	 	   	   case "widthmin": case "widthMin":
	 	   		   itemCriteria.add(Restrictions.ge("nominalwidth", Float.parseFloat(value))); 
	 	   		   break;
	 	   	   case "lengthmax": case "lengthMax":
	 	   		   itemCriteria.add(Restrictions.le("nominallength", Float.parseFloat(value)));
		    	   itemCriteria.add(Restrictions.gt("nominallength", 0F)); 	
		    	   break;
	 	   	   case "widthmax": case "widthMax":
	 	   		   itemCriteria.add(Restrictions.le("nominalwidth", Float.parseFloat(value))); 
		    	   itemCriteria.add(Restrictions.gt("nominalwidth", 0F));
		    	   break;
	 	   	   case "nominallength": case "nominalLength": case "nominalwidth": case "nominalWidth":	
	 	   		   itemCriteria.add(Restrictions.eq(key, Float.parseFloat(value)));
	 	   		   break;
	 	   	   case "size": case "sizes":
    	   		   itemCriteria = parseSize(itemCriteria, values);
	 	   		   break;
	 	   	   case "price": case "cost":  
                   itemCriteria.add(Restrictions.eq(key, new BigDecimal(value))); 
                   break;
	 	   	   case "waterabsorption": case "waterAbsorption": case "peiabrasion": case "peiAbrasion": case "moh": case "dcof": case "scof": case  "scratchresistance": case "scratchResistance":   
	 	   	       itemCriteria.add(Restrictions.eq(key, Float.parseFloat(value)));
	 	   	       break;
	 	   	   case "exactmatch": case "exactMatch":
 	   		       break;    
	 	   	   default:     
	 	   		   itemCriteria.add(Restrictions.eq(key, value).ignoreCase());
	 	   		   break;
	 	   	}	      	    	
	    }
	    itemCriteria.setReadOnly(true);
        itemCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        itemCriteria.addOrder(Order.asc("itemcode"));
	    itemCriteria.setMaxResults(MAX_RESULTS);
	    //criteria.setTimeout(TIMEOUT); //not is implememtated in Postgres
		System.out.println("criteria = " +itemCriteria.toString());
		try {
		    items =  (List<Item>)itemCriteria.list();			
		}
		catch(HibernateException hbe){
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
    		else
	  		   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());
		}
	  	return items;	
    }
 /*
   @Override
   @Loggable(value = LogLevel.TRACE)
   @Transactional(readOnly=true)	
   public List<Item> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams) throws BedDAOException{
	   if(queryParams == null) 
		   return null;
		List<Item> items = null;
		boolean exactMatch = queryParams.containsKey("exactMatch")? true : false;
		Set<Map.Entry<String, List<String>>> set = queryParams.entrySet();
	    Iterator it = set.iterator();
	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Item.class);
	    Criteria newFeatureCriteria = null;
	    Criteria vendorCriteria = null;
	    Criteria noteCriteria = null;
	    Criteria colorHueCriteria = null;
	  	criteria.setReadOnly(true);
	  	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
   	   	String key, value = null;
   	    List<String> values = null;
	   	while(it.hasNext()) {
   	    	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
   		    key = normalizeKey((String)entry.getKey());
   		   	values = entry.getValue();
   		   	
   		    if(values != null) {
   		   	   value = values.get(0);	
   		    if("exactMatch".equalsIgnoreCase(key))
     		    continue;
   		   	//Add associations criteria
   	   	    if(ImsNewFeature.allProperties().contains(key)) {	
   		   	   if(newFeatureCriteria == null)
   		   	      newFeatureCriteria = criteria.createCriteria("imsNewFeature");
   		       newFeatureCriteria = addNewFeatureRestrictions(newFeatureCriteria, key, value);
   	       	   continue;
   		    }
   	   	    if("colorDescription".equalsIgnoreCase(key)) {	
		       if(colorHueCriteria == null)
		       	  colorHueCriteria = criteria.createCriteria("colorHues");
		       colorHueCriteria.add(Restrictions.eq(key, Color.instanceOf(value)));
		       continue;
   	       }  	
   	   	   if("vendorId".equalsIgnoreCase(key) || "vendorNumber".equalsIgnoreCase(key)) {	
		      if(vendorCriteria == null)
 		         vendorCriteria = criteria.createCriteria("newVendorSystem");
		      vendorCriteria.add(Restrictions.eq("itemVendorId.vendorId", Integer.parseInt(value)));
		      continue;
 	       }
   	   	   if("noteType".equalsIgnoreCase(key)) {	
		      if(noteCriteria == null)
   		         noteCriteria = criteria.createCriteria("newNoteSystem");
		      noteCriteria.add(Restrictions.eq(key, value).ignoreCase());
		      continue;
   	       }
   	       //Add components Restrictions
   		   if("purchaser".equalsIgnoreCase(key)) {	
   		       criteria.add(Restrictions.eq("purchasers.purchaser", value).ignoreCase());
   		       continue;
   		   }
   		   if("fulldesc".equalsIgnoreCase(key) || "itemDescription".equalsIgnoreCase(key) || "description".equalsIgnoreCase(key)) {	
		       criteria.add(Restrictions.ilike("itemdesc.fulldesc", wildCard(value)));
		       continue;
		   }
   		   if("itemdesc1".equalsIgnoreCase(key)) {	
		       criteria.add(Restrictions.ilike("itemdesc.itemdesc1", wildCard(value)));
		       continue;
		   }
   		   if("pricemax".equalsIgnoreCase(key)){
 	           criteria.add(Restrictions.le("price.sellprice", new BigDecimal(value))); 
 	           continue;
           }	   
   		   //Add comparison Restrictions //criteria = addGtLtRestrictions(criteria, key, value); 
   		   else if("lengthmin".equalsIgnoreCase(key)){
   	    	   criteria.add(Restrictions.ge("nominallength", Float.parseFloat(value))); 	
   	       }
   		   else if("widthmin".equalsIgnoreCase(key)){
	    	       criteria.add(Restrictions.ge("nominalwidth", Float.parseFloat(value))); 	
	       }
   		   else if("lengthmax".equalsIgnoreCase(key)){
	    	       criteria.add(Restrictions.le("nominallength", Float.parseFloat(value)));
	    	       criteria.add(Restrictions.gt("nominallength", 0F)); 	
	       }
   		   else if("widthmax".equalsIgnoreCase(key)){
	    	       criteria.add(Restrictions.le("nominalwidth", Float.parseFloat(value))); 
	    	       criteria.add(Restrictions.gt("nominalwidth", 0F));
	       }
   		   else if("pricemax".equalsIgnoreCase(key)){
	    	       criteria.add(Restrictions.le("sellprice", new BigDecimal(value))); 	
	       }
   		   else if("size".equalsIgnoreCase(key) || "sizes".equalsIgnoreCase(key)){
   		   		   criteria = parseSize(criteria, values);
   		   }		
   	       else{
   	    		   if(values.size() > 1 || values.contains(",") || values.indexOf(",") >= 0 || values.toArray().length > 1 || values.toString().contains(","))
   	    		      criteria.add(Restrictions.in(key, values));
   	    		      //criteria.add(Restrictions.in(key, (String[])values.toArray()));
   	    		   else {
   	    		         if(!exactMatch && ("itemcode".equalsIgnoreCase(key) || "color".equalsIgnoreCase(key)  || 
   	    		        		            "seriesname".equalsIgnoreCase(key)  || "materialcategory".equalsIgnoreCase(key))){ 
   	    			        criteria.add(Restrictions.ilike(key, value + "%"));
   	    		         }
   	    			     else if(!exactMatch && "colorcategory".equalsIgnoreCase(key)|| "colorhues".equalsIgnoreCase(key) || "itemdesc1".equalsIgnoreCase(key) || "fulldesc".equalsIgnoreCase(key)){
   	    			        	criteria.add(Restrictions.ilike(key, "%" + value + "%"));
   	    			     }   	
   	    		         else {
   	    		        	if (key.contains("price") || key.endsWith("cost") || key.endsWith("Cost"))
     	    		    	    criteria.add(Restrictions.eq(key, new BigDecimal(value))); 
   	    		        	else if (key.toUpperCase().endsWith("RATIO") || 
   	    		        			 key.toUpperCase().endsWith("PCT") || key.toUpperCase().endsWith("PCT1") ||  key.toUpperCase().endsWith("PCT2") ||  key.toUpperCase().endsWith("PCT3") ||
   	    		        			 key.toUpperCase().endsWith("PERCENT") ||
   	    		        			 "nominallength".equalsIgnoreCase(key) || "nominalwidth".equalsIgnoreCase(key) || 
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
	  	criteria.addOrder(Order.asc("itemcode"));
	  	criteria.setMaxResults(MAX_RESULTS);
	  	//criteria.setTimeout(TIMEOUT); //not is implememtated in Postgres
	  	System.out.println("criteria = " +criteria.toString());
	  	try {
	  	    items =  (List<Item>)criteria.list();			
	  	}
	  	catch(HibernateException hbe){
	  		if(hbe.getCause() != null)
	  	       throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
	  		else
	  		   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());
	  	}
	  	return items;	
    }
	  
   */
	@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public String createItem(Item item){
		return (String)save(sessionFactory.getCurrentSession(), item); 
	}
	
	@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateItem(Item item){
		update(sessionFactory.getCurrentSession(), item);
	}
	
	@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	public void deleteItem(Item item){
		delete(sessionFactory.getCurrentSession(), item); 
	}

	private String normalizeKey(String key){
		
	    if ("itemcd".equalsIgnoreCase(key) || "itemCode".equalsIgnoreCase(key) || "itemid".equalsIgnoreCase(key))
	   		key = "itemcode";
	    else if("materialCategory".equalsIgnoreCase(key) || "matcategory".equalsIgnoreCase(key) || "matCategory".equalsIgnoreCase(key))
   		    key = "materialcategory";
	    else if("colorCategory".equalsIgnoreCase(key) || "colorHues".equalsIgnoreCase(key) || "colorHue".equalsIgnoreCase(key))
   		    key = "colorhues";
	   // else if("colorHues".equalsIgnoreCase(key) || "colorHue".equalsIgnoreCase(key) || "colorDescription".equalsIgnoreCase(key))
   		 //   key = "colorDescription";
	    else if("seriesName".equalsIgnoreCase(key))
   		    key = "seriesname";
	    else if("category".equalsIgnoreCase(key))
   		    key = "itemcategory";
	    else if("length".equalsIgnoreCase(key) || ("nominallength".equalsIgnoreCase(key)) || ("nominalLength".equalsIgnoreCase(key)))
   		    key = "nominallength";
	    else if("width".equalsIgnoreCase(key) || ("nominalwidth".equalsIgnoreCase(key)) || ("nominalWidth".equalsIgnoreCase(key)))
   		    key = "nominalwidth";
	    else if("thickness".equalsIgnoreCase(key) || ("nominalthickness".equalsIgnoreCase(key)) || ("nominalThickness".equalsIgnoreCase(key)))
   		    key = "nominalthickness";
	    else if("productManager".equalsIgnoreCase(key) || "productmanager".equalsIgnoreCase(key))
   		    key = "purchaser";
        return key;
	}    
		
	private Criteria addNewFeatureRestrictions(Criteria newFeatureCriteria, String key, String value){
		    if("grade".equalsIgnoreCase(key))
  		        newFeatureCriteria.add(Restrictions.eq(key, Grade.instanceOf(value)));
  			else if("status".equalsIgnoreCase(key))
		        newFeatureCriteria.add(Restrictions.eq(key, Status.instanceOf(value)));
  		    else if("body".equalsIgnoreCase(key))
	            newFeatureCriteria.add(Restrictions.eq(key, Body.instanceOf(value)));
  		    else if("edge".equalsIgnoreCase(key))
	            newFeatureCriteria.add(Restrictions.eq(key, Edge.instanceOf(value)));
		    else if("mpsCode".equalsIgnoreCase(key))
   		        newFeatureCriteria.add(Restrictions.eq("mpsCode", MpsCode.instanceOf(value)));
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
		    return newFeatureCriteria;
	}

    private Criteria addGtLtRestrictions(Criteria criteria, String key, String value){
       	if("lengthmin".equalsIgnoreCase(key)){
    	    criteria.add(Restrictions.ge("nominallength", Float.parseFloat(value))); 	
    	}
	   	else if("widthmin".equalsIgnoreCase(key)){
 	       criteria.add(Restrictions.ge("nominalwidth", Float.parseFloat(value))); 	
 	    }
	   	else if("lengthmax".equalsIgnoreCase(key)){
 	       criteria.add(Restrictions.le("nominallength", Float.parseFloat(value)));
 	       criteria.add(Restrictions.gt("nominallength", 0F)); 	
 	    }
	   	else if("widthmax".equalsIgnoreCase(key)){
 	       criteria.add(Restrictions.le("nominalwidth", Float.parseFloat(value))); 
 	       criteria.add(Restrictions.gt("nominalwidth", 0F));
 	    }
	   	else if("pricemax".equalsIgnoreCase(key)){
 	       criteria.add(Restrictions.le("sellprice", new BigDecimal(value))); 	
	   	}
       	return criteria;
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
	   					                       		                  Restrictions.eq("nominallength", Float.parseFloat(length)),
	   					                       		                  Restrictions.eq("nominalwidth", Float.parseFloat(width))
	   					                                             ); 
	    		   disjunction.add(andExpression);
	          }  
	   	      criteria.add(disjunction);
	   	}
		return criteria;
   }
	
   private String wildCard(String data){
	   return (data == null || data.isEmpty())? null : "%" + data + "%";
   }
}
