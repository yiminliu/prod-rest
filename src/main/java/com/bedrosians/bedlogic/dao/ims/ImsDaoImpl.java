package com.bedrosians.bedlogic.dao.ims;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.ImsNewFeature;
import com.bedrosians.bedlogic.domain.ims.enums.Body;
import com.bedrosians.bedlogic.domain.ims.enums.DesignLook;
import com.bedrosians.bedlogic.domain.ims.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.ims.enums.Edge;
import com.bedrosians.bedlogic.domain.ims.enums.Grade;
import com.bedrosians.bedlogic.domain.ims.enums.MpsCode;
import com.bedrosians.bedlogic.domain.ims.enums.Status;
import com.bedrosians.bedlogic.domain.ims.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.ims.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.ims.enums.SurfaceType;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.util.ims.ImsQueryUtil;


@Repository("imsDao")
public class ImsDaoImpl extends GenericDaoImpl<Ims, String> implements ImsDao {
	
	//private static final int DEFAULT_MAX_RESULTS = 50000;//500; 
    private int maxResults = 0;
    @Autowired
	private SessionFactory sessionFactory;
	
    //--------------------------- retrieval DB operation -----------------------//
    
	/*
	 * This method returns an Product with associated entities for the given product id.
     * Note: it's not set "Read Only" because it could be called by update()
     */
	@Override
	public Ims getItemByItemCode(Session session, final String itemCode) {
    	Query query = session.createQuery("From Ims where itemcode = :itemCode");
		query.setString("itemCode", itemCode);
		return (Ims)query.setCacheable(true).uniqueResult();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<String> getItemCodeList(){
		Query query = getSession().createQuery("Select itemcode From Ims");
		return (List<String>)query.setCacheable(true).list();
	}
	
	/*This method only gets a proxy of the persistent entity, without hitting the database
	*/
	@Override
	public Ims loadItemByItemCode(Session session, final String itemCode) {
       return loadById(session, itemCode);
	}
 
	/*  Rules for Pattern/Exact matches:
	 *  colorcategory: always use LIKE on both sides: like '%data%'
	 *  fulldesc and itemdesc1 always use LIKE on right side: like 'data%'
	 *  itemcode and materialcategory depend on the input exactmatch flag. If 'exactmatch' in input, use LIKE, otherwise, use exact match 
	 */
	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	@SuppressWarnings("unchecked")
	public List<Ims> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams){
	   if(queryParams == null) 
		  return null;
	   List<Ims> ims = null;
	   boolean exactMatch = queryParams.containsKey("exactmatch")? true : queryParams.containsKey("exactMatch")? true : false;
	   maxResults = ImsQueryUtil.getMaxResults(queryParams);
	   Set<Map.Entry<String, List<String>>> entrySet = queryParams.entrySet();
	   Iterator<Map.Entry<String, List<String>>> it = entrySet.iterator();
	   DetachedCriteria newFeatureCriteria = null;
	   DetachedCriteria vendorCriteria = null;
	   DetachedCriteria colorHueCriteria = null;
	   DetachedCriteria itemCriteria = DetachedCriteria.forClass(Ims.class);
	   String key, value = null;
	   List<String> values = null;
	   String stringValue = null;
    		
	   while(it.hasNext()) {
		    //--------------- Process the input data --------------//
	     	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
	 	    key = normalizeKey((String)entry.getKey());
	 	   	values = entry.getValue();	
	 	   	if(values == null || values.isEmpty())
	 	   	   continue;
	 	   	else if(values.size() == 1 && values.toString().contains(",")) { //the input contains multiple values for a key, but in String format
	 	   		stringValue = values.toString();
	 	   		stringValue =stringValue.substring(stringValue.indexOf("[")+1);
	 	     	stringValue =stringValue.substring(0, stringValue.lastIndexOf("]"));
	 	   		values = Arrays.asList(stringValue.split(","));  
	 	   	}
	        value = values.get(0) == null? values.get(0) : values.get(0).trim();
	        
	        //----------- Compose hibernate criteria -------------//
	 		//------ conditional pattern match -------//
    		if(!exactMatch && ("itemcode".equalsIgnoreCase(key) || ("material.materialcategory".equalsIgnoreCase(key)))){
    		   if(values.size() > 1)	
    			   itemCriteria = generateWildcardDisjunctionCriteria(itemCriteria, key, values);
    		   else
    			   itemCriteria.add(Restrictions.ilike(key, value.toUpperCase(), MatchMode.START));
	           continue;
            }
    		//------ unconditional pattern match -------//
   	        if("colorHue".equalsIgnoreCase(key)){
   	         	colorHueCriteria = itemCriteria.createCriteria("colorhues");
	        	if(values.size() > 1)	
	        		colorHueCriteria = generateEqualsDisjunctionCriteria(colorHueCriteria, key, values);
	        	else
	        		//colorHueCriteria.add(Restrictions.ilike(key, value, MatchMode.ANYWHERE));
	        		colorHueCriteria.add(Restrictions.eq(key, value));
		        continue;
		    }  
		    if("itemdesc.fulldesc".equalsIgnoreCase(key) || "itemdesc.itemdesc1".equalsIgnoreCase(key) || "colorcategory".equalsIgnoreCase(key)){
		      	if(values.size() > 1)	
	     		   itemCriteria = generateWildcardDisjunctionCriteria(itemCriteria, key, values);
	     		else
			       itemCriteria.add(Restrictions.ilike(key, value, MatchMode.ANYWHERE));
			    continue;
		    }  	
	        //----- take care of multiple values case other than "size" ------//
	 	   	if(!"size".equals(key) && (values.size() > 1)){
	 	   	    itemCriteria =  generateEqualsDisjunctionCriteria(itemCriteria, key, values); //Case insensitive
	 		    continue;
	 	   	}  
    		//------- add association criteria --------//
	 	   	if(ImsNewFeature.allProperties().contains(key)) {	
	   		   if(newFeatureCriteria == null)
	     	      newFeatureCriteria = itemCriteria.createCriteria("newFeature", JoinType.LEFT_OUTER_JOIN);
	   	       newFeatureCriteria = addNewFeatureRestrictions(newFeatureCriteria, key, value);
	       	   continue;
		    }
	 	   	switch(key) {
	   	   	   case "vendorid": case "vendorId": case "vendorNumber":
	   	           if(vendorCriteria == null)
	 		          vendorCriteria = itemCriteria.createCriteria("newVendorSystem", JoinType.LEFT_OUTER_JOIN);
			       vendorCriteria.add(Restrictions.eq("vendorId.vendorId", Integer.parseInt(value)));
			       break;
	 	       case "size": case "sizes":
   	   		       itemCriteria = parseSize(itemCriteria, values);
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
	 	   	   case "price.sellprice": case "price.listprice": case "cost.cost":  case "cost.cost1":
                   itemCriteria.add(Restrictions.eq(key, new BigDecimal(value))); 
                   break;
	 	   	   case "waterabsorption": case "waterAbsorption": case "peiabrasion": case "peiAbrasion": case "moh":
	 	   	   case "dcof": case "scof": case  "scratchresistance": case "scratchResistance":   
	 	   	       itemCriteria.add(Restrictions.eq(key, Float.parseFloat(value)));
	 	   	       break;
	 	   	   case "exactMatch": case "exactmatch": case "maxresults": case "maxResults":
 	   		       break;    
	 	   	   default:     
	 	   		   itemCriteria.add(Restrictions.eq(key, value).ignoreCase());
	 	   		   break;
	 	   	}	      	    	
	    }
        itemCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //itemCriteria.addOrder(Order.asc("itemcode"));
        System.out.println("getItemsByQueryParameters() using criteria = " +itemCriteria.toString());
  
		try {
			if(maxResults > 0)
		       ims =  (List<Ims>)itemCriteria.getExecutableCriteria(getSession()).setLockMode(LockMode.NONE).setFlushMode(FlushMode.COMMIT).setMaxResults(maxResults).setCacheable(true).list();//executeCriteria(itemCriteria);//(List<Product>)itemCriteria.list();			
			else
			   ims =  (List<Ims>)itemCriteria.getExecutableCriteria(getSession()).setLockMode(LockMode.NONE).setFlushMode(FlushMode.COMMIT).setCacheable(true).list();	
		}
		catch(Exception e){
		     throw e;	
		}
		System.out.println(ims == null? 0 : ims.size() + " items returned");       
        return ims;	
    }

	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	@SuppressWarnings("unchecked")
	public List<Ims> getItems(LinkedHashMap<String,List<String>> queryParams){
	   if(queryParams == null) 
		  return null;
	   List<Ims> ims = null;
	   boolean exactMatch = queryParams.containsKey("exactmatch")? true : queryParams.containsKey("exactMatch")? true : false;
	   //maxResults = ImsQueryUtil.getMaxResults(queryParams);
	   Set<Map.Entry<String, List<String>>> entrySet = queryParams.entrySet();
	   Iterator<Map.Entry<String, List<String>>> it = entrySet.iterator();
	   DetachedCriteria newFeatureCriteria = null;
	   DetachedCriteria vendorCriteria = null;
	   DetachedCriteria colorHueCriteria = null;
	   DetachedCriteria itemCriteria = DetachedCriteria.forClass(Ims.class);
	   String key, value = null;
	   List<String> values = null;
	   String stringValue = null;
    		
	   while(it.hasNext()) {
		    //--------------- Process the input data --------------//
	     	Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
	 	    key = normalizeKey((String)entry.getKey());
	 	   if(key == null || key.isEmpty())
	           continue;
	 	    Object obj = entry.getValue();
	 	    if(obj instanceof String)
	 	    	value = (String)obj;
	 	    else
	   	   	   values = (List<String>)entry.getValue();
	  	   	if((values == null || values.isEmpty()) && value == null)
	 	   	   continue;
	 	   	else if(values != null && values.size() == 1 && values.toString().contains(",")) { //the input contains multiple values for a key, but in String format
	 	   		stringValue = values.toString();
	 	   		stringValue =stringValue.substring(stringValue.indexOf("[")+1);
	 	     	stringValue =stringValue.substring(0, stringValue.lastIndexOf("]"));
	 	   		values = Arrays.asList(stringValue.split(","));  
	 	   	}
	  	    if((values != null && !values.isEmpty()) && value == null)
	        value = values.get(0) == null? values.get(0) : values.get(0).trim();
	        if(value == null || value.isEmpty())
	           continue;	
	        //----------- Compose hibernate criteria -------------//
	 		//------ conditional pattern match -------//
    		if(!exactMatch && ("itemcode".equalsIgnoreCase(key) || ("material.materialcategory".equalsIgnoreCase(key)))){
    		   if(values != null && values.size() > 1)	
    			   itemCriteria = generateWildcardDisjunctionCriteria(itemCriteria, key, values);
    		   else
    			   itemCriteria.add(Restrictions.ilike(key, value.toUpperCase(), MatchMode.START));
	           continue;
            }
    		//------ unconditional pattern match -------//
   	        if("colorHue".equalsIgnoreCase(key)){
   	        	if(colorHueCriteria == null)
   	         	   colorHueCriteria = itemCriteria.createCriteria("colorhues");
	        	if(values != null && values.size() > 1)	
	        		colorHueCriteria = generateEqualsDisjunctionCriteria(colorHueCriteria, key, values);
	        	else
	        		//colorHueCriteria.add(Restrictions.ilike(key, value, MatchMode.ANYWHERE));
	        		colorHueCriteria.add(Restrictions.eq(key, value));
		        continue;
		    }  
		    if("itemdesc.fulldesc".equalsIgnoreCase(key) || "itemdesc.itemdesc1".equalsIgnoreCase(key) || "colorcategory".equalsIgnoreCase(key)){
		      	if(values != null && values.size() > 1)	
	     		   itemCriteria = generateWildcardDisjunctionCriteria(itemCriteria, key, values);
	     		else
			       itemCriteria.add(Restrictions.ilike(key, value, MatchMode.ANYWHERE));
			    continue;
		    }  	
	        //----- take care of multiple values case other than "size" ------//
	 	   	if(!"size".equals(key) && (values != null && values.size() > 1)){
	 	   	    itemCriteria =  generateEqualsDisjunctionCriteria(itemCriteria, key, values); //Case insensitive
	 		    continue;
	 	   	}  
    		//------- add association criteria --------//
	 	   	if(ImsNewFeature.allProperties().contains(key) && value != null && !value.isEmpty()) {	
	   		   if(newFeatureCriteria == null)
	     	      newFeatureCriteria = itemCriteria.createCriteria("newFeature", JoinType.LEFT_OUTER_JOIN);
	   	       newFeatureCriteria = addNewFeatureRestrictions(newFeatureCriteria, key, value);
	       	   continue;
		    }
	 	  
	 	   	switch(key) {
	   	   	   case "vendorid": case "vendorId": case "vendorNumber":
	   	           if(vendorCriteria == null)
	 		          vendorCriteria = itemCriteria.createCriteria("newVendorSystem", JoinType.LEFT_OUTER_JOIN);
			       vendorCriteria.add(Restrictions.eq("vendorId.vendorId", Integer.parseInt(value)));
			       break;
	 	       case "size": case "sizes":
   	   		       itemCriteria = parseSize(itemCriteria, values);
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
	 	   	   case "dimensions.nominallength": case "dimensions.nominalLength": case "dimensions.nominalwidth": case "dimensions..nominalWidth":	
	 	   		   itemCriteria.add(Restrictions.eq(key, Float.parseFloat(value)));
	 	   		   break;	   
	  	   	   case "pricemax": case "priceMax":
	 	   		   itemCriteria.add(Restrictions.le("price.sellprice", new BigDecimal(value))); 
	 	   		   itemCriteria.add(Restrictions.gt("price.sellprice", new BigDecimal(0))); 
	 	           break;
	 	   	   case "price.sellprice": case "price.listprice": case "cost.cost":  case "cost.cost1":
                   itemCriteria.add(Restrictions.eq(key, new BigDecimal(value))); 
                   break;
	 	   	   case "waterabsorption": case "waterAbsorption": case "peiabrasion": case "peiAbrasion": case "moh":
	 	   	   case "dcof": case "scof": case  "scratchresistance": case "scratchResistance":   
	 	   	       itemCriteria.add(Restrictions.eq(key, Float.parseFloat(value)));
	 	   	       break;
	 	   	   case "exactMatch": case "exactmatch": case "maxresults": case "maxResults":
 	   		       break;    
	 	   	   default:     
	 	   		   itemCriteria.add(Restrictions.eq(key, value).ignoreCase());
	 	   		   break;
	 	   	}	      	    	
	    }
        itemCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //itemCriteria.addOrder(Order.asc("itemcode"));
        System.out.println("getItemsByQueryParameters() using criteria = " +itemCriteria.toString());
  
		try {
			if(maxResults > 0)
		       ims =  (List<Ims>)itemCriteria.getExecutableCriteria(getSession()).setLockMode(LockMode.NONE).setFlushMode(FlushMode.COMMIT).setMaxResults(maxResults).setCacheable(true).list();//executeCriteria(itemCriteria);//(List<Product>)itemCriteria.list();			
			else
			   ims =  (List<Ims>)itemCriteria.getExecutableCriteria(getSession()).setLockMode(LockMode.NONE).setFlushMode(FlushMode.COMMIT).setCacheable(true).list();	
		}
		catch(Exception e){
		     throw e;	
		}
		System.out.println(ims == null? 0 : ims.size() + " items returned");       
        return ims;	
    }
	
	//The purpose of this method is to compare the performance of Hibernate Search and regular Hibernate query 
	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	@SuppressWarnings("unchecked")
    public List<Ims> getActiveAndShownOnWebsiteItems(){
	   List<Ims> ims = null;
	   FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		
	   QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Ims.class).get();
	   // create native Lucene query
	   org.apache.lucene.search.Query luceneQuery = queryBuilder.bool()
		   	                                       .must(queryBuilder.keyword().onField("inactivecode").matching("N  ").createQuery())//ims table uses char type which may need some padding to match index id 
	   	                                           .must(queryBuilder.keyword().onField("showonwebsite").matching("Y").createQuery()).createQuery();   
	   // wrap Lucene query in a javax.persistence.Query
	   org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Ims.class).setCacheable(true);
	   // retrieve product list
	   ims = (List<Ims>)fullTextQuery.list();     
	   return ims;
	}
    
	//------------------------------- creation DB operation -------------------------//
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public String createItem(Ims ims){
		return (String)save(sessionFactory.getCurrentSession(), ims); 
	}
	
	//------------------------------- update DB operation ---------------------------//
	
	@Override
	public void updateItem(Session session, Ims ims){
		update(session, ims);
	}
	
	//------------------------------- deletion DB operation -------------------------//
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	public void deleteItem(Ims ims){
		delete(sessionFactory.getCurrentSession(), ims); 
	}

	//------------------- internal helper methods ------------------//
	
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
		  case "_colorhues": case "_material":
	   	     key = "";
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
		  case "inactivecd": case "inactiveCode":
			 key = "inactivecode";
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
	   	  case "materialCategory": case "materialcategory": case "matcategory": case "matCategory":
	   	     key = "material.materialcategory";
	   	     break;
		  case "materialType": case "materialtype": case "matType":
			 key = "material.materialtype";
			 break;
		  case "materialStyle": case "materialstyle": case "matStyle":
			 key = "material.materialstyle";
			 break;
		  case "materialFeature": case "materialfeature": case "matFeature": case "matfeature":
			 key = "material.materialfeature";
			 break;
		  case "materialClass": case "materialclass": case "matClass": case "matclass": case "matclasscode":  case "matClassCode": 
			 key = "material.materialclass";
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
	      case "purchaser":	
	   		 key = "purchasers.purchaser";
	   		 break;
	      case "cost": 
	   		 key = "cost.cost";
			 break;	 
	      case "cost1": 
		   	 key = "cost.cost1";
			 break;	
	      case "newFeature.status": 
		   	 key = "status";
			 break;	 
		  case "newFeature.grade": 
		   	 key = "grade";
			 break;	 
		  case "newFeature.mpsCode": 
			 key = "mpsCode";
			 break;	 
		  case "newFeature.designLook": 
				 key = "designLook";
				 break;
		  case "newFeature.designStyle": 
				 key = "designStyle";
				 break;
		  case "newFeature.body": 
				 key = "body";
				 break;
		  case "newFeature.edge": 
				 key = "edge";
				 break;
		  case "newFeature.surfaceApplication": 
				 key = "surfaceApplication";
				 break;
		  case "newFeature.surfaceType": 
				 key = "surfaceType";
				 break;
		  case "newFeature.surfaceFinish": 
				 key = "surfaceFinish";
				 break;	
       }
       if(key.endsWith("_colorHue")){
    	   if(!key.endsWith("_colorhues"))
    	       key ="colorHue";
    	   else
    		   key = "";
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
	
   private DetachedCriteria generateWildcardDisjunctionCriteria(DetachedCriteria criteria, String name, List<String> values){
	    Disjunction or = Restrictions.disjunction();
		for(String value : values) {
			if("colorcategory".equalsIgnoreCase(name) || name.startsWith("itemdesc"))
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
			or.add(Restrictions.eq(name, value.trim()).ignoreCase());
	    }  
	   	criteria.add(or);
		return criteria;
   }	

   private synchronized Session getSession(){
	   Session session = sessionFactory.getCurrentSession();
	   if (session == null)
		   session = sessionFactory.openSession();
       return session;
   }
}
