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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
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
import com.bedrosians.bedlogic.util.ImsQueryUtil;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;

@Repository("itemDao")
public class ItemDaoImpl extends GenericDaoImpl<Item, String> implements ItemDao {
					
    @Autowired
	private SessionFactory sessionFactory;
	 
    private static final int DEFAULT_MAX_RESULTS = 50000;//500; 
    private int maxResults = 0;
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
 
	/*
	@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(readOnly=true)	
	public List<Item> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams) throws BedDAOException{
	   if(queryParams == null) 
		   return null;
	   List<Item> items = null;
	   boolean exactMatch = queryParams.containsKey("exactmatch")? true : queryParams.containsKey("exactMatch")? true : false;
	   maxNumberOfResults = ImsQueryUtil.getMaxResults(queryParams);
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
	 	   	if(values == null || values.isEmpty())
	 	   	   continue;
    		value = values.get(0);
    		//------ conditional pattern match -------//
    		if(!exactMatch && "itemcode".equalsIgnoreCase(key)){
    		   if(values.size() > 1)	
    			   itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, key, values);
    		   else
    			   itemCriteria.add(Restrictions.ilike(key, wildCardRight(value)));
	           continue;
            }
   	        else if(!exactMatch && ("materialcategory".equalsIgnoreCase(key))){
   	        	if(values.size() > 1)	
     			   itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, "material." + key, values);
     		   else
	        	   itemCriteria.add(Restrictions.ilike("material." + key, wildCardRight(value)));
			    continue;
	        }
    		//------ unconditional pattern match -------//
	        if("colorcategory".equalsIgnoreCase(key) || "colorhues".equalsIgnoreCase(key)){
	        	if(values.size() > 1)	
	     		   itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, "colorcategory", values);
	        	else
	        	   itemCriteria.add(Restrictions.ilike("colorcategory", wildCardBoth(value)));
		        continue;
		    }  
		    else if("itemdesc.fulldesc".equalsIgnoreCase(key) || "itemdesc.itemdesc1".equalsIgnoreCase(key)){
		      	 if(values.size() > 1)	
	     		    itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, key, values);
	     		 else
			        itemCriteria.add(Restrictions.ilike(key, wildCardBoth(value)));
			     continue;
		    }  	
	        //----- other multiple values case ------//
	 	   	if(!"size".equals(key) && (values.size() > 1 || values.contains(",") || values.indexOf(",") >= 0 || values.toArray().length > 1 || values.toString().contains(","))){
    		   itemCriteria.add(Restrictions.in(key, values));
	 	       //criteria.add(Restrictions.in(key, (String[])values.toArray()));
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
			   //------ add comparison Restrictions ------//     
	 	   	   case "series.seriesname": 
	 	   		   if(values.size() > 1)	
	     			  itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, key, values);
	     		   else
	 		          itemCriteria.add(Restrictions.eq(key, value));
	 		       break;  
	 	   	   case "series.seriescolor":	
	 		       itemCriteria.add(Restrictions.eq(key, value));
	 		       break;     
	 	       case "materialtype":	case "materialType": 
	 	       case "materialcategory": case "materialCategory": 
	 	       case "materialstyle": case "materialStyle": 
	 	       case "materialfeature": case "materialFeature":
	 	       case "materialclass": case "materialClass":
	 	    	  if(values.size() > 1)	
	     			  itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, "material."+ key, values);
	     		   else
	   		          itemCriteria.add(Restrictions.eq("material."+ key, value).ignoreCase());
	   		       break;
	 	      case "size": case "sizes":
   	   		       itemCriteria = parseSize(itemCriteria, values);
	 	   		   break;    
	  		   case "purchaser":	
	   		       itemCriteria.add(Restrictions.eq("purchasers.purchaser", value).ignoreCase());
	   		       break;
	 	   	   case "lengthmin": case "lengthMin":
	 	   		   itemCriteria.add(Restrictions.ge("dimensions.nominallength", Float.parseFloat(value))); 
	 	   		   break;
	 	   	   case "widthmin": case "widthMin":
	 	   		   itemCriteria.add(Restrictions.ge("dimensions.nominalwidth", Float.parseFloat(value))); 
	 	   		   break;
	 	   	   case "lengthmax": case "lengthMax":
	 	   		   itemCriteria.add(Restrictions.le("dimensions.nominallength", Float.parseFloat(value)));
		    	   itemCriteria.add(Restrictions.gt("dimensions.nominallength", 0F)); 	
		    	   break;
	 	   	   case "widthmax": case "widthMax":
	 	   		   itemCriteria.add(Restrictions.le("dimensions.nominalwidth", Float.parseFloat(value))); 
		    	   itemCriteria.add(Restrictions.gt("dimensions.nominalwidth", 0F));
		    	   break;
	 	   	   case "nominallength": case "nominalLength": case "nominalwidth": case "nominalWidth":	
	 	   		   itemCriteria.add(Restrictions.eq("dimensions" + "." +key, Float.parseFloat(value)));
	 	   		   break;
	 	   	   case "pricemax": case "priceMax":
	 	   		   itemCriteria.add(Restrictions.le("price.sellprice", new BigDecimal(value))); 
	 	   		   itemCriteria.add(Restrictions.gt("price.sellprice", new BigDecimal(0))); 
	 	           break;
	 	   	   case "sellprice": case "listprice": case "cost":  case "cost1": 
                   itemCriteria.add(Restrictions.eq(key, new BigDecimal(value))); 
                   break;
	 	   	   case "waterabsorption": case "waterAbsorption": case "peiabrasion": case "peiAbrasion": case "moh": case "dcof": case "scof": case  "scratchresistance": case "scratchResistance":   
	 	   	       itemCriteria.add(Restrictions.eq(key, Float.parseFloat(value)));
	 	   	       break;
	 	   	   case "exactMatch": case "exactmatch": case "maxresults": case "maxResults":
 	   		       break;    
	 	   	   default:     
	 	   		   itemCriteria.add(Restrictions.eq(key, value).ignoreCase());
	 	   		   break;
	 	   	}	      	    	
	    }
	    itemCriteria.setReadOnly(true);
        itemCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        itemCriteria.addOrder(Order.asc("itemcode"));
        if(maxNumberOfResults > 0)
	      itemCriteria.setMaxResults(maxNumberOfResults);
        //else
        //	itemCriteria.setMaxResults(DEFAULT_MAX_RESULTS);	
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
	*/
	
	/*Rules for Pattern/Exact matches:
	 *  colorcategory: always use LIKE on both sides: like '%data%'
	 *  fulldesc and itemdesc1 always use LIKE on right side: like 'data%'
	 *  itemcode and materialcategory depend on the input exactmatch flag. If 'exactmatch' in input, use LIKE, otherwise, use exact match 
	 */
	@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Item> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams) throws BedDAOException{
	   if(queryParams == null) 
		   return null;
	   List<Item> items = null;
	   boolean exactMatch = queryParams.containsKey("exactmatch")? true : queryParams.containsKey("exactMatch")? true : false;
	   maxResults = ImsQueryUtil.getMaxResults(queryParams);
	   Set<Map.Entry<String, List<String>>> entrySet = queryParams.entrySet();
	   Iterator<Map.Entry<String, List<String>>> it = entrySet.iterator();
	   DetachedCriteria newFeatureCriteria = null;
	   DetachedCriteria vendorCriteria = null;
	   DetachedCriteria noteCriteria = null;
	   DetachedCriteria colorHueCriteria = null;
       //Criteria itemCriteria = sessionFactory.getCurrentSession().createCriteria(Item.class);
       DetachedCriteria itemCriteria = DetachedCriteria.forClass(Item.class);
	   String key, value = null;
	   List<String> values = null;
	   while(it.hasNext()) {
	     	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
	 	    key = normalizeKey((String)entry.getKey());
	 	   	values = entry.getValue();	
	 	   	if(values == null || values.isEmpty())
	 	   	   continue;
	 	   	else
	 	   	   value = values.get(0);
    		//------ conditional pattern match -------//
    		if(!exactMatch && "itemcode".equalsIgnoreCase(key)){
    		   if(values.size() > 1)	
    			   itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, key, values);
    		   else
    			   itemCriteria.add(Restrictions.ilike(key, value, MatchMode.START));
	           continue;
            }
   	        if(!exactMatch && ("materialcategory".equalsIgnoreCase(key))){
   	        	if(values.size() > 1)	
     			   itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, "material." + key, values);
     		   else
	        	   itemCriteria.add(Restrictions.ilike("material." + key, value, MatchMode.START));
			    continue;
	        }
    		//------ unconditional pattern match -------//
   	        if("colorHue".equalsIgnoreCase(key)){
   	        	colorHueCriteria = itemCriteria.createCriteria("colorhues");
	        	if(values.size() > 1)	
	        		colorHueCriteria = generateLikeDisjunctionCriteria(colorHueCriteria, key, values);
	        	else
	        		colorHueCriteria.add(Restrictions.ilike(key, value, MatchMode.ANYWHERE));
		        continue;
		    }  
	        if("colorcategory".equalsIgnoreCase(key)){
	        	if(values.size() > 1)	
	     		   itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, "colorcategory", values);
	        	else
	        	   itemCriteria.add(Restrictions.ilike("colorcategory", value, MatchMode.ANYWHERE));
		        continue;
		    }  
		    if("itemdesc.fulldesc".equalsIgnoreCase(key) || "itemdesc.itemdesc1".equalsIgnoreCase(key)){
		      	 if(values.size() > 1)	
	     		    itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, key, values);
	     		 else
			        itemCriteria.add(Restrictions.ilike(key, value, MatchMode.ANYWHERE));
			     continue;
		    }  	
	        //----- take care of all other multiple values case, except for "size" ------//
	 	   	if(!"size".equals(key) && (values.size() > 1 || values.contains(",") || values.indexOf(",") >= 0 || values.toArray().length > 1 || values.toString().contains(","))){
    		    if(key.startsWith("material"))
    		    	itemCriteria.add(Restrictions.in("material." + key, values));
    		    else
    		    	itemCriteria.add(Restrictions.in(key, values));
	 	       //criteria.add(Restrictions.in(key, (String[])values.toArray()));
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
			   //------ add comparison Restrictions ------//     
	 	   	   case "series.seriesname": 
	 	   		   if(values.size() > 1)	
	     			  itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, key, values);
	     		   else
	 		          itemCriteria.add(Restrictions.eq(key, value));
	 		       break;  
	 	   	   case "series.seriescolor":	
	 		       itemCriteria.add(Restrictions.eq(key, value));
	 		       break;     
	 	       case "materialtype":	case "materialType": 
	 	       case "materialcategory": case "materialCategory": 
	 	       case "materialstyle": case "materialStyle": 
	 	       case "materialfeature": case "materialFeature":
	 	       case "materialclass": case "materialClass":
	 	    	  if(values.size() > 1)	
	     			  itemCriteria = generateEqualsDisjunctionCriteria(itemCriteria, "material."+ key, values);
	     		   else
	   		          itemCriteria.add(Restrictions.eq("material."+ key, value).ignoreCase());
	   		       break;
	 	      case "size": case "sizes":
   	   		       itemCriteria = parseSize(itemCriteria, values);
	 	   		   break;    
	  		   case "purchaser":	
	   		       itemCriteria.add(Restrictions.eq("purchasers.purchaser", value).ignoreCase());
	   		       break;
	 	   	   case "lengthmin": case "lengthMin":
	 	   		   itemCriteria.add(Restrictions.ge("dimensions.nominallength", Float.parseFloat(value))); 
	 	   		   break;
	 	   	   case "widthmin": case "widthMin":
	 	   		   itemCriteria.add(Restrictions.ge("dimensions.nominalwidth", Float.parseFloat(value))); 
	 	   		   break;
	 	   	   case "lengthmax": case "lengthMax":
	 	   		   itemCriteria.add(Restrictions.le("dimensions.nominallength", Float.parseFloat(value)));
		    	   itemCriteria.add(Restrictions.gt("dimensions.nominallength", 0F)); 	
		    	   break;
	 	   	   case "widthmax": case "widthMax":
	 	   		   itemCriteria.add(Restrictions.le("dimensions.nominalwidth", Float.parseFloat(value))); 
		    	   itemCriteria.add(Restrictions.gt("dimensions.nominalwidth", 0F));
		    	   break;
	 	   	   case "nominallength": case "nominalLength": case "nominalwidth": case "nominalWidth":	
	 	   		   itemCriteria.add(Restrictions.eq("dimensions" + "." +key, Float.parseFloat(value)));
	 	   		   break;
	 	   	   case "showonwebsite": case "showOnWebSite": 
	 	   	   case "showonalysedwards": case "showOnAlysedwards":
	 	   	   case "itemtypecd": case "itemtypecode":
	 	   	   case "taxclass": case "taxClass": case "itemtaxclass": case "itemTaxClass":	
	  	   		   itemCriteria.add(Restrictions.eq(key, value.charAt(0)));
	 	   		   break;	   
	 	   	   case "pricemax": case "priceMax":
	 	   		   itemCriteria.add(Restrictions.le("price.sellprice", new BigDecimal(value))); 
	 	   		   itemCriteria.add(Restrictions.gt("price.sellprice", new BigDecimal(0))); 
	 	           break;
	 	   	   case "sellprice": case "listprice": 
                   itemCriteria.add(Restrictions.eq(key, new BigDecimal(value))); 
                   break;
	 	   	   case "cost":  case "cost1": 
                   itemCriteria.add(Restrictions.eq(key, new BigDecimal(value))); 
                   break;    
	 	   	   case "waterabsorption": case "waterAbsorption": case "peiabrasion": case "peiAbrasion": case "moh": case "dcof": case "scof": case  "scratchresistance": case "scratchResistance":   
	 	   	       itemCriteria.add(Restrictions.eq(key, Float.parseFloat(value)));
	 	   	       break;
	 	   	   case "exactMatch": case "exactmatch": case "maxresults": case "maxResults":
 	   		       break;    
	 	   	   default:     
	 	   		   itemCriteria.add(Restrictions.eq(key, value).ignoreCase());
	 	   		   break;
	 	   	}	      	    	
	    }
	   // itemCriteria.setReadOnly(true);
        itemCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        itemCriteria.addOrder(Order.asc("itemcode"));
        //  if(maxNumberOfResults > 0)
	   //   itemCriteria.setMaxResults(maxNumberOfResults);
        //else
        //	itemCriteria.setMaxResults(DEFAULT_MAX_RESULTS);	
        System.out.println("criteria = " +itemCriteria.toString());
		try {
			if(maxResults > 0)
		       items =  (List<Item>)itemCriteria.getExecutableCriteria(sessionFactory.getCurrentSession()).setMaxResults(maxResults).setCacheable(true).list();//executeCriteria(itemCriteria);//(List<Item>)itemCriteria.list();			
			else
			   items =  (List<Item>)itemCriteria.getExecutableCriteria(sessionFactory.getCurrentSession()).setMaxResults(DEFAULT_MAX_RESULTS).setCacheable(true).list();	
		}
		catch(HibernateException hbe){
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
    		else
	  		   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());
		}
	  	return items;	
    }
	
	//@Override
	@Loggable(value = LogLevel.TRACE)
	@Transactional(readOnly=true)	
	public List<Item> executeCriteria(DetachedCriteria detachedCriteria) throws BedDAOException{
		
		return (List<Item>)detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
	
	}
	
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
       switch(key) {
		  case "itemcd": case "itemCode": case "itemId": case "itemid": 
	   		 key = "itemcode";
	   		 break;
		  case "fulldesc": case "fullDesc":  case "description":  case "fulldescription": case "fullDescription":
	   	     key = "itemdesc.fulldesc";
	   	     break;
		  case "itemdesc1": case "itemDesc1":
		   	 key = "itemdesc.itemdesc1";
		   	 break;
		  case "colorCategory": 
	         key = "colorcategory";
	   		 break;	
		   case "colorhues": case "colorHues": case "colorhue": case "colorHue": 
   		     key = "colorHue";
   		     break;
		  case "seriesname": case "seriesName":
   		     key = "series.seriesname";
   		     break;
   		  case "color": case "seriescolor": case "seriesColor":
	   	     key = "series.seriescolor";
		     break;
		  case "category":
   		     key = "itemcategory";
   		     break;
		  case "length": case "nomlength": case "nomLength": case "nominalLength":
   		     key = "nominallength";
   		     break;
	 	  case "width": case "nomwidth": case "nomLwidth": case "nominalWidth":
   		     key = "nominalwidth";
   		     break;
		  case "thickness": case "nomthickness": case "nomThickness": case "nominalthickness": case "nominalThickness":
   		     key = "nominalthickness";
   		     break;
		  case "productManager": case "productmanager":
   		     key = "purchasers.purchaser";
   		     break;
		  case "countryOrigin": case "origin":
	   		 key = "countryorigin";
	   		 break;
	   	  case "materialCategory": case "matcategory": case "matCategory":
	   	     key = "materialcategory";
	   	     break;
		  case "materialType": case "matType":
			 key = "materialtype";
			 break;
		  case "materialStyle": case "matStyle":
			 key = "materialstyle";
			 break;
		  case "materialFeature": case "matFeature": case "matfeature":
			 key = "materialfeature";
			 break;
		  case "materialClass": case "matClass": case "matclass": case "matclasscode":  case "matClassCode": 
			 key = "materialclass";
			 break; 
		  case "sellprice": case "sellPrice":
			 key = "price.sellprice";
			 break;	  
		  case "listprice": case "listPrice":
			 key = "price.listprice";
			 break;	
	      case "pricegroup": case "priceGroup": 
	   		 key = "price.pricegroup";
			 break;  
	      case "cost": 
	   		 key = "cost.cost";
			 break;	 
	      case "cost1": 
		   	 key = "cost.cost";
			 break;		 
       }
        return key;
	}    
		
	private DetachedCriteria addNewFeatureRestrictions(DetachedCriteria newFeatureCriteria, String key, String value){
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
	 
	private DetachedCriteria parseSize(DetachedCriteria criteria, List<String> values){
	   		   	
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
	   					                       		                  Restrictions.eq("dimensions.nominallength", Float.parseFloat(length)),
	   					                       		                  Restrictions.eq("dimensions.nominalwidth", Float.parseFloat(width))
	   					                                             ); 
	    		   disjunction.add(andExpression);
	          }  
	   	      criteria.add(disjunction);
	   	}
		return criteria;
   }
	
	private DetachedCriteria generateLikeDisjunctionCriteria(DetachedCriteria criteria, String name, List<String> values){
	    Disjunction or = Restrictions.disjunction();
		for(String value : values) {
			if("colorcategory".equalsIgnoreCase(name) || name.startsWith("itemdesc") || name.equalsIgnoreCase("fulldesc"))
			   or.add(Restrictions.ilike(name, value, MatchMode.ANYWHERE));
			else
			   or.add(Restrictions.ilike(name, value, MatchMode.START));
	    }  
	   	criteria.add(or);
		return criteria;
   }	
	
   private DetachedCriteria generateEqualsDisjunctionCriteria(DetachedCriteria criteria, String name, List<String> values){
	    Disjunction or = Restrictions.disjunction();
		for(String value : values) {
			or.add(Restrictions.eq(name, value));
	    }  
	   	criteria.add(or);
		return criteria;
   }	
		
}
