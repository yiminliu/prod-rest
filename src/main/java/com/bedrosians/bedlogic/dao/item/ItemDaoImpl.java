package com.bedrosians.bedlogic.dao.item;

import java.math.BigDecimal;
import java.util.Arrays;
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
import com.bedrosians.bedlogic.domain.item.ItemNewFeature;
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
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.util.ImsQueryUtil;


@Repository("itemDao")
public class ItemDaoImpl extends GenericDaoImpl<Item, String> implements ItemDao {
	
	//private static final int DEFAULT_MAX_RESULTS = 50000;//500; 
    private int maxResults = 0;
    @Autowired
	private SessionFactory sessionFactory;
	
    //--------------------------- retrieval DB operation -----------------------//
    
	/*
	 * This method returns an Item with associated entities for the given item id.
     * Note: it's not set "Read Only" because is could be called by update()
     */
	@Override
	public Item getItemById(Session session, final String itemId) {
    	Query query = session.createQuery("From Item where itemcode = :itemCode");
		query.setString("itemCode", itemId);
		return (Item)query.setCacheable(true).uniqueResult();
	}
	
	@Override
	public Item loadItemById(Session session, final String itemId) {
       return loadById(session, itemId);
	}
 
	//The purpose of this method is to compare the performance of Hibernate Search and regular Hibernate query 
	@Override
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	 public List<Item> getActiveAndShownOnWebsiteItems(){
	   List<Item> items = null;
	   FullTextSession fullTextSession = Search.getFullTextSession(getSession());
	
	   QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
	   // create native Lucene query
       //org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onField("colorcategory").matching("CLEAR").createQuery();
       org.apache.lucene.search.Query luceneQuery = queryBuilder.bool()
	   	                                       .must(queryBuilder.keyword().onField("inactivecode").matching("N  ").createQuery())//ims table uses char type witch may need some padding to match index id 
   	                                           .must(queryBuilder.keyword().onField("showonwebsite").matching("Y").createQuery()).createQuery();
       
	   // wrap Lucene query in a javax.persistence.Query
       org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Item.class).setCacheable(true);
 	   items = (List<Item>)fullTextQuery.list();     
   	   return items;
	}
	
	/*  Rules for Pattern/Exact matches:
	 *  colorcategory: always use LIKE on both sides: like '%data%'
	 *  fulldesc and itemdesc1 always use LIKE on right side: like 'data%'
	 *  itemcode and materialcategory depend on the input exactmatch flag. If 'exactmatch' in input, use LIKE, otherwise, use exact match 
	 */
	@Override
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
	   //DetachedCriteria colorHueCriteria = null;
       //Criteria itemCriteria = sessionFactory.getCurrentSession().createCriteria(Item.class);
       DetachedCriteria itemCriteria = DetachedCriteria.forClass(Item.class);
	   String key, value = null;
	   List<String> values = null;
	   String stringValue = null;
    		
	   while(it.hasNext()) {
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
	 	   	value = values.get(0);
    		//------ conditional pattern match -------//
    		if(!exactMatch && "itemcode".equalsIgnoreCase(key)){
    		   if(values.size() > 1)	
    			   itemCriteria = generateLikeDisjunctionCriteria(itemCriteria, key, values);
    		   else
    			   itemCriteria.add(Restrictions.ilike(key, value.toUpperCase(), MatchMode.START));
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
   	        //if("colorHue".equalsIgnoreCase(key)){
   	        //	colorHueCriteria = itemCriteria.createCriteria("colorhues");
	        //	if(values.size() > 1)	
	        //		colorHueCriteria = generateLikeDisjunctionCriteria(colorHueCriteria, key, values);
	        //	else
	        //		colorHueCriteria.add(Restrictions.ilike(key, value, MatchMode.ANYWHERE));
		    //    continue;
		    //}  
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
	        //----- take care of multiple values case other than "size" ------//
	 	   	if(!"size".equals(key) && (values.size() > 1 || values.contains(",") || values.indexOf(",") >= 0 || values.toArray().length > 1 || values.toString().contains(","))){
    		    if(key.startsWith("material")){
    		    	//itemCriteria.add(Restrictions.in("material." + key, values)); //Case sensitive
    		    	itemCriteria =  generateEqualsDisjunctionCriteria(itemCriteria, "material." + key, values);//Case insensitive
    		    }
    		    else{
    		    	//itemCriteria.add(Restrictions.in(key, values)); //Case sensitive
    		        itemCriteria =  generateEqualsDisjunctionCriteria(itemCriteria, key, values); //Case insensitive
	 		    }    
    		    continue;
	 	   	}  
    		//------- add association criteria --------//
	 	   	if(ItemNewFeature.allProperties().contains(key)) {	
	   		   if(newFeatureCriteria == null)
	     	      newFeatureCriteria = itemCriteria.createCriteria("imsNewFeature", JoinType.LEFT_OUTER_JOIN);
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
	 		          itemCriteria.add(Restrictions.eq(key, value).ignoreCase());
	 		       break;  
	 	   	   case "series.seriescolor":	
	 		       itemCriteria.add(Restrictions.eq(key, value).ignoreCase());
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
        itemCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //itemCriteria.addOrder(Order.asc("itemcode"));
        System.out.println("getItemsByQueryParameters() using criteria = " +itemCriteria.toString());
  
		try {
			if(maxResults > 0)
		       items =  (List<Item>)itemCriteria.getExecutableCriteria(getSession()).setMaxResults(maxResults).setCacheable(true).list();//executeCriteria(itemCriteria);//(List<Item>)itemCriteria.list();			
			else
			   items =  (List<Item>)itemCriteria.getExecutableCriteria(getSession()).setCacheable(true).list();	
		}
		catch(HibernateException hbe){
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
    		else
	  		   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());
		}
		
		System.out.println(items == null? 0 : items.size() + " items returned");       
        return items;	
    }
	
    //------------------------------- creation DB operation -------------------------//
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public String createItem(Item item){
		return (String)save(sessionFactory.getCurrentSession(), item); 
	}
	
	//------------------------------- update DB operation -------------------------//
	
	@Override
	public void updateItem(Session session, Item item){
		update(session, item);
	}
	
	//------------------------------- deletion DB operation -------------------------//
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	public void deleteItem(Item item){
		delete(sessionFactory.getCurrentSession(), item); 
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
   		     key = "colorcategory"; //"colorHue";
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
			or.add(Restrictions.eq(name, value).ignoreCase());
	    }  
	   	criteria.add(or);
		return criteria;
   }	

   private synchronized Session getSession(){
   	return sessionFactory.getCurrentSession();
}
}
