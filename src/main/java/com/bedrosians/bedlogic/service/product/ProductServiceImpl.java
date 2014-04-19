package com.bedrosians.bedlogic.service.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.item.ItemDao;
import com.bedrosians.bedlogic.domain.item.IconDescription;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.Notes;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.ImsQueryUtil;
import com.bedrosians.bedlogic.util.ImsValidation;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;


@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
	ItemDao itemDao;  
    
    @Autowired
	private SessionFactory sessionFactory;
    
    	    	
    @Loggable(value = LogLevel.TRACE)
    @Override
	//@Transactional(readOnly=true)
	public Item getProductById(String id) throws BedDAOBadParamException, BedDAOException{
    	Item item = null;
    	if(id == null || id.length() < 1)
    	   throw new BedDAOBadParamException("Please enter valid item code !");	
		try{
    	    item = itemDao.getItemById(id);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			throw new BedDAOException("Error occured during getProductById() due to: " + hbe.getMessage(), hbe);
		}
		//return item;
		return FormatUtil.process(item);
	}

	@Loggable(value = LogLevel.TRACE)
	@Override
	//@Transactional(readOnly=true)
	public List<Item> getProductsByQueryParameters(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException{
			
		ImsValidation.validateQueryParams(queryParams);	
		List<Item> items = null;
		try{
			items = itemDao.getItemsByQueryParameters(queryParams);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			throw new BedDAOException("Error occured during getProductsByQueryParameters() due to: " + hbe.getMessage(), hbe);
		}
		List<Item> processedItems = new ArrayList<>();
		for(Item item : items){
			processedItems.add(FormatUtil.process(item));			
		}
		//return items;
		return processedItems;
	}
		
	@Loggable(value = LogLevel.TRACE)
	@Override
	//@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public synchronized String createProduct(Item item) throws BedDAOBadParamException, BedDAOException{
		String id;
		ImsValidation.validateNewItem(item);
		try{
			id = itemDao.createItem(item); 
		}
		catch(HibernateException hbe){
				hbe.printStackTrace();
				throw new BedDAOException("Error occured during getProductsByQueryParameters() due to: " + hbe.getMessage(), hbe);
		}
		return id; 
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	//@Transactional
	public String createProduct(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{  	
    	String id;
		Item item = new Item();
		synchronized(item){
		   if(item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null) {
		      ImsNewFeature imsNewFeature = new ImsNewFeature();	
		      imsNewFeature.setCreatedDate(new Date());
		      item.addImsNewFeature(imsNewFeature);	
		   }
		   if(item.getVendors() == null || item.getVendors().isEmpty()){
		      item.initVendors(ImsQueryUtil.determineNumberOfVendors(inputJsonObj));
	       }
		   if(ImsQueryUtil.containsAnyKey(inputJsonObj, IconDescription.allPropertis()))
			   item.addIcon(new IconDescription());	
		   if(inputJsonObj.has("poNote")) {
	  	       Notes poNote = new Notes("po");
		       poNote.setCreatedDate(new Date());
		       item.addNote(poNote);
		   }  
		   if(inputJsonObj.has("buyerNote")) {
		       Notes buyerNote = new Notes("buyer");
		       buyerNote.setCreatedDate(new Date());
		       item.addNote(buyerNote);
	       }
		   if(inputJsonObj.has("invoiceNote")) {
		       Notes invoiceNote = new Notes("invoice");
		       invoiceNote.setCreatedDate(new Date());
		       item.addNote(invoiceNote);
	       }
		   if(inputJsonObj.has("internalNote")) {
			   Notes internalNote = new Notes("internal");
			   internalNote.setCreatedDate(new Date());
			   item.addNote(internalNote);
		   }
		   try{
		      item = ImsQueryUtil.buildItemFromJsonObjectForInsert(item, inputJsonObj);
		   }
		   catch(Exception e){
			  e.printStackTrace();
			  throw new BedDAOBadParamException(e);
		   }
		   ImsValidation.validateNewItem(item);
		}
		try{
		   id = itemDao.createItem(item);
		}
		catch(HibernateException hbe){
			  hbe.printStackTrace();
			  throw new BedDAOException("Error occured during createProduct() due to: " + hbe.getMessage(), hbe);
	    }
		return id;		 	
    }
	
	/*
	@Loggable(value = LogLevel.TRACE)
	@Override
	//@Transactional
	public String createProduct(String inputJsonString) throws BedDAOBadParamException, BedDAOException, BedResException{  	
    	String id;
		Item item = new Item();
		synchronized(item){
		   if(item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null) {
		      ImsNewFeature imsNewFeature = new ImsNewFeature();	
		      imsNewFeature.setCreatedDate(new Date());
		      item.addImsNewFeature(imsNewFeature);	
		   }
		   if(item.getVendors() == null || item.getVendors().isEmpty()){
		      item.initVendors(ImsQueryUtil.determineNumberOfVendors(inputJsonObj));
	       }
		   if(ImsQueryUtil.containsAnyKey(inputJsonObj, Icon.allPropertis()))
			   item.addIcon(new Icon());	
		   if(inputJsonObj.has("poNote")) {
	  	       Note poNote = new Note("po");
		       poNote.setCreatedDate(new Date());
		       item.addNote(poNote);
		   }  
		   if(inputJsonObj.has("buyerNote")) {
		       Note buyerNote = new Note("buyer");
		       buyerNote.setCreatedDate(new Date());
		       item.addNote(buyerNote);
	       }
		   if(inputJsonObj.has("invoiceNote")) {
		       Note invoiceNote = new Note("invoice");
		       invoiceNote.setCreatedDate(new Date());
		       item.addNote(invoiceNote);
	       }
		   if(inputJsonObj.has("internalNote")) {
			   Note internalNote = new Note("internal");
			   internalNote.setCreatedDate(new Date());
			   item.addNote(internalNote);
		   }
		   try{
		      item = ImsQueryUtil.buildItemFromJsonObjectForInsert(item, inputJsonObj);
		   }
		   catch(Exception e){
			  e.printStackTrace();
			  throw new BedDAOBadParamException(e);
		   }
		   ImsValidation.validateNewItem(item);
		}
		try{
		   id = itemDao.createItem(item);
		}
		catch(HibernateException hbe){
			  hbe.printStackTrace();
			  throw new BedDAOException("Error occured during createProduct() due to: " + hbe.getMessage(), hbe);
	    }
		return id;		 	
    }
	*/
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	//@Transactional
	public String createProduct(final MultivaluedMap<String, String> queryParams)throws BedDAOBadParamException, BedDAOException{
		String id;
		ImsValidation.validateInsertUpdateParams(queryParams);
		Item item = new Item();
		synchronized(item){
		   if(item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null) {
		      ImsNewFeature imsNewFeature = new ImsNewFeature();	
		      imsNewFeature.setCreatedDate(new Date());
		      item.addImsNewFeature(imsNewFeature);	
		   }
		   if(item.getVendors() == null || item.getVendors().isEmpty()){
		      item.initVendors(ImsQueryUtil.determineNumberOfVendors(queryParams));
	       }
		   if(ImsQueryUtil.containsAnyKey(queryParams, IconDescription.allPropertis()))
			   item.addIcon(new IconDescription());	
		   if(ImsQueryUtil.containsKey(queryParams, "poNote")) {
	  	       Notes poNote = new Notes("po");
		       poNote.setCreatedDate(new Date());
		       item.addNote(poNote);
		   }  
		   if(ImsQueryUtil.containsKey(queryParams, "buyerNote")) {
		       Notes buyerNote = new Notes("buyer");
		       buyerNote.setCreatedDate(new Date());
		       item.addNote(buyerNote);
	       }
		   if(ImsQueryUtil.containsKey(queryParams, "invoiceNote")) {
		       Notes invoiceNote = new Notes("invoice");
		       invoiceNote.setCreatedDate(new Date());
		       item.addNote(invoiceNote);
	       }
		   if(ImsQueryUtil.containsKey(queryParams, "internalNote")) {
			   Notes internalNote = new Notes("internal");
			   internalNote.setCreatedDate(new Date());
			   item.addNote(internalNote);
		   }
		   try{
		      item = ImsQueryUtil.buildItemForInsert(item, queryParams);
		   }
		   catch(Exception e){
			  e.printStackTrace();
			  throw new BedDAOBadParamException(e);
		   }
		   ImsValidation.validateNewItem(item);
		}
		try{
			  id = itemDao.createItem(item);
		}
		catch(HibernateException hbe){
			  hbe.printStackTrace();
			  throw new BedDAOException("Error occured during createProduct() due to: " + hbe.getMessage(), hbe);
	    }
		return id;		
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public void updateProduct(final String itemId, Item item) throws BedDAOBadParamException, BedDAOException, BedResException{
		if(itemId == null || itemId.length() < 1)
	       throw new BedDAOBadParamException("Please enter valid item code !");	
		Session session = sessionFactory.getCurrentSession();
		Item retrievedItem = itemDao.loadItemById(session, itemId);
		if(retrievedItem == null)
		   throw new BedResException("No data found for the given item code");	
		try{
		   itemDao.updateItem(session, item); 
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }   
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(Item item){
		//itemDao.updateItem(item); 
	}	
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(String jsonString) throws BedDAOBadParamException, BedDAOException, BedResException{
		try {
		    updateProduct(new JSONObject(jsonString));
		}
		catch(JSONException e){
			throw new BedDAOBadParamException("Error occured during parsing the json input: " + e.getMessage());
		}
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{
		String itemcd = ImsQueryUtil.getItemCode(inputJsonObj); 
		if(itemcd == null || itemcd.length() == 0)
		   throw new BedDAOBadParamException("Item code should not be empty");	
		Item item = new Item(itemcd);
		Session session = sessionFactory.getCurrentSession();
		/*try{
		   item = itemDao.loadItemById(session, itemcd.trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new BedDAOException("Error occured during gupdateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		if(item == null)
	       throw new BedResException("No data found for the given item code");	
		
		synchronized(item) {
		*/   item = ImsQueryUtil.buildItemFromJsonObject(item, inputJsonObj, "update");
		//}
		if(item.getImsNewFeature() != null)
		   item.getImsNewFeature().setLastModifiedDate(new Date());
		try{
			//itemDao.updateItem(session, item);
			itemDao.update(session, item.getItemcode(), item);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }  
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(final MultivaluedMap<String, String> queryParams) throws BedDAOException, BedResException{
		
		ImsValidation.validateInsertUpdateParams(queryParams);	
		String itemcd = ImsQueryUtil.getValue(queryParams, "itemcd");
		if(itemcd == null || itemcd.trim().length() == 0)
			itemcd = ImsQueryUtil.getValue(queryParams, "itemcode");	
		Item item = new Item(itemcd); 
		
		Session session = sessionFactory.getCurrentSession();
	//	try{
	//	   item = itemDao.loadItemById(session, itemcd.trim());
	//	}
	//    catch(HibernateException hbe){
	//	    hbe.printStackTrace();
	//	    throw new BedDAOException("Error occured during gupdateProduct() due to: " + hbe.getMessage(), hbe);
	//    }
	//	if(item == null)
	//       throw new BedResException("No data found for the given item code");	
		
		//synchronized(item) {
		   item = ImsQueryUtil.buildItemForUpdate(item,queryParams);
		//}
		if(item.getImsNewFeature() != null)
		   item.getImsNewFeature().setLastModifiedDate(new Date());
		try{
			itemDao.update(session, item.getItemcode(), item);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }  
	}
	
}
