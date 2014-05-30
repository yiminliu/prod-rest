package com.bedrosians.bedlogic.service.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.item.ColorHueDao;
import com.bedrosians.bedlogic.dao.item.ItemDao;
import com.bedrosians.bedlogic.domain.item.ColorHue;
import com.bedrosians.bedlogic.domain.item.IconCollection;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.ItemVendor;
import com.bedrosians.bedlogic.domain.item.enums.DBOperation;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.models.Products;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.ImsDataUtil;
import com.bedrosians.bedlogic.util.ImsQueryUtil;
import com.bedrosians.bedlogic.util.ImsValidator;
import com.bedrosians.bedlogic.util.JsonUtil;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.JsonWrapper.ListWrapper;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;
import com.sun.jersey.core.util.MultivaluedMapImpl;


@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
	ItemDao itemDao;  
    
    @Autowired
	ColorHueDao colorHueDao;  
    
    @Autowired
	private SessionFactory sessionFactory;
      	    	
    @Loggable(value = LogLevel.TRACE)
    @Override
    @Transactional(readOnly = true)
	public Item getProductById(String id) throws BedDAOBadParamException, BedDAOException{
    	Item item = null;
    	if(id == null || id.length() < 1)
    	   throw new BedDAOBadParamException("Please enter valid item code !");	
		try{
    	    item = itemDao.getItemById(sessionFactory.getCurrentSession(), id);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  hbe.getMessage());	
		}
		return FormatUtil.process(item);
	}

	@Loggable(value = LogLevel.TRACE)
	@Override
	public List<Item> getProducts(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException{
		if(queryParams == null || queryParams.isEmpty()){
			queryParams = new MultivaluedMapImpl();
			queryParams.put("inactivecode", Arrays.asList(new String[]{"N"}));
		}
		List<Item> items = null;
		try{
			items = itemDao.getItemsByQueryParameters(queryParams);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage());
		
		}
		List<Item> processedItems = new ArrayList<>();
		for(Item item : items){
			processedItems.add(FormatUtil.process(item));			
		}
		return processedItems;
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	public List<Item> getProducts(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{
		if(inputJsonObj == null || inputJsonObj.length() < 1)
	    	   throw new BedDAOBadParamException("Please enter valid item code !");	
	    List<Item> items = null;
		try {
			items = getProducts(JsonUtil.JsonStringToMultivaluedMap(inputJsonObj.toString()));	 
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProducts(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProducts(), due to: " +  e.getMessage());	
		}
		return items;
		
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	public String createProduct(Item item) throws BedDAOBadParamException, BedDAOException{
		String id;
		ImsValidator.validateNewItem(item);
		try{
			id = itemDao.createItem(item); 
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
			   throw new BedDAOException("Error occured during createProduct(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
			else
			   throw new BedDAOException("Error occured during createProduct(), due to: " +  hbe.getMessage());		
		}
		return id; 
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	public String createProduct(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{  	
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		String id;
		Item newItem = new Item(itemCode);
     	Item item = (Item)JsonUtil.jsonObjectToPOJO(jsonObj, new Item());
     	newItem = ImsDataUtil.transformItem(newItem, item, DBOperation.CREATE);
     	ImsValidator.validateNewItem(newItem);
   	    try{
		   id = itemDao.createItem(newItem);
		}
		catch(HibernateException hbe){
		   hbe.printStackTrace();
		   if(hbe.getCause() != null)
		      throw new BedDAOException("Error occured during createProduct(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		   else
		  	  throw new BedDAOException("Error occured during createProduct(), due to: " +  hbe.getMessage());	
	    }	
   	    catch(Exception e){
		  e.printStackTrace();
		  if(e.getCause() != null)
	  	     throw new BedDAOException("Error occured during createProduct(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
	  	  else
	  	     throw new BedDAOException("Error occured during createProduct(), due to: " +  e.getMessage());	
      }
	  return id;		 	
    }
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	public String createProductWithJsonInFlatFormat(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{  	
		JsonUtil.validateItemCode(inputJsonObj);
		String id;
    	Item item = new Item();
		synchronized(item){
			item = initializeItemAssociations(item, inputJsonObj, "insert"); 
			try{
		      item = ImsQueryUtil.buildItemFromJsonObjectForInsert(item, inputJsonObj);
		   }
		   catch(Exception e){
			  e.printStackTrace();
			  throw new BedDAOBadParamException(e);
		   }
		   ImsValidator.validateNewItem(item);
		   try{
		      id = itemDao.createItem(item);
		   }
		   catch(HibernateException hbe){
			  hbe.printStackTrace();
			  if(hbe.getCause() != null)
		  	     throw new BedDAOException("Error occured during createProductWithJsonInFlatFormat(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	  else
		  	     throw new BedDAOException("Error occured during createProductWithJsonInFlatFormat(), due to: " +  hbe.getMessage());	
	       }
		   catch(Exception e){
			  e.printStackTrace();
			  if(e.getCause() != null)
		  	     throw new BedDAOException("Error occured during createProductWithJsonInFlatFormat(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	  else
		  		 throw new BedDAOException("Error occured during createProductWithJsonInFlatFormat(), due to: " +  e.getMessage());	
		   }
	   }		
	   return id;		 	
    }
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	public String createProduct(final MultivaluedMap<String, String> queryParams)throws BedDAOBadParamException, BedDAOException{
		String id;
		ImsValidator.validateInsertUpdateParams(queryParams);
		Item item = new Item(ImsQueryUtil.getItemCode(queryParams));
		synchronized(item){
		   item = initializeItemAssociations(item, queryParams, "insert");
		   try{
		      item = ImsQueryUtil.buildItemForInsert(item, queryParams);
		   }
	       catch(Exception e){
		      e.printStackTrace();
		      throw new BedDAOBadParamException(e);
	       }
		   ImsValidator.validateNewItem(item);
		   try{
		      id = itemDao.createItem(item);
		   }
		   catch(HibernateException hbe){
			  hbe.printStackTrace();
			  if(hbe.getCause() != null)
		  	     throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	  else
		  		 throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());
	       }
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
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during updateProduct(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
	  		else
	  		   throw new BedDAOException("Error occured during updateProduct(), due to: " +  hbe.getMessage());
	    }   
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(Item item){
		itemDao.updateItem(sessionFactory.getCurrentSession(), item); 
	}	
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		Item itemFromInput = (Item)JsonUtil.jsonObjectToPOJO(jsonObj, new Item());
		Item itemToUpdate = null;
		Session session = sessionFactory.getCurrentSession();
		try{
			itemToUpdate = itemDao.getItemById(session, itemCode.trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new BedDAOException("Error occured during gupdateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		if(itemToUpdate == null)
	       throw new BedResException("No data found for the given item code");	
	 	itemToUpdate = ImsDataUtil.transformItem(itemToUpdate, itemFromInput, DBOperation.UPDATE);
     	ImsValidator.validateNewItem(itemToUpdate);
    	try{
		   itemDao.updateItem(sessionFactory.getCurrentSession(),itemToUpdate);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during updateProduct(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
			   throw new BedDAOException("Error occured during updateProduct(), due to: " +  hbe.getMessage());	
		}  
	}
	
	/*
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{
		JsonUtil.validateItemCode(jsonObj);
     	Item item = (Item)JsonUtil.jsonObjectToPOJO(jsonObj, new Item());
     	Item newItem = FormatUtil.transformItem(item, "update");
     	ImsValidator.validateNewItem(newItem);
   		try{
		   itemDao.updateItem(newItem);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
			   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());	
	    }  
	}
	*/
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProductWithJsonFlateFormat(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{
		String itemCode = JsonUtil.getItemCode(inputJsonObj); 
		if(itemCode == null || itemCode.length() == 0)
		   throw new BedDAOBadParamException("Item code should not be empty");	
		Item item = new Item(itemCode);
		item = initializeItemAssociations(item, inputJsonObj, "update");
		/*Session session = sessionFactory.getCurrentSession();
		try{
		   item = itemDao.loadItemById(session, itemCode.trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new BedDAOException("Error occured during gupdateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		if(item == null)
	       throw new BedResException("No data found for the given item code");	
		*/
		synchronized(item) {
		   try {	
		       item = ImsQueryUtil.buildItemFromJsonObject(item, inputJsonObj, "update");
		   }
		   catch(ClassCastException e){
			   throw new BedDAOBadParamException("Error occured during parse json input: " + e.getMessage());	
		   }
		   catch(Exception e){
			   throw new BedDAOBadParamException("Error occured during parse json input: " + e.getMessage());	
		   }
		}
		try{
		   itemDao.updateItem(sessionFactory.getCurrentSession(), item);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
			   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());	
	    }  
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(final MultivaluedMap<String, String> queryParams) throws BedDAOException, BedResException{
		
		ImsValidator.validateInsertUpdateParams(queryParams);	
		String itemCode = ImsQueryUtil.getItemCode(queryParams);
		Item item = new Item(itemCode); 
		//item = initializeItemAssociations(item, queryParams, "Update");
		/*Session session = sessionFactory.getCurrentSession();
		try{
		   item = itemDao.loadItemById(session, itemcd.trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new BedDAOException("Error occured during gupdateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		if(item == null)
	       throw new BedResException("No data found for the given item code");	
		*/
		synchronized(item) {
		   item = ImsQueryUtil.buildItemForUpdate(item,queryParams);
		}
		//if(item.getImsNewFeature() != null)
		//   item.getImsNewFeature().setLastModifiedDate(new Date());
		try{
			itemDao.updateItem(sessionFactory.getCurrentSession(), item);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		  	   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());
	    }  
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
    public void deleteProductById(String id) throws BedDAOBadParamException, BedDAOException{
	    if(id == null || id.length() == 0)
	    	 throw new BedDAOBadParamException("Item code should not be empty");		
		Item item = new Item(id);
		try{
			itemDao.deleteItem(item);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		  	   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());
		
		}  
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	public void deleteProduct(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException, BedResException{
		String itemCode = ImsQueryUtil.getItemCode(queryParams);
		if(itemCode == null || itemCode.length() == 0)
		   throw new BedDAOBadParamException("Item code should not be empty");	
		Item item = new Item(itemCode);
		try{
			itemDao.deleteItem(item);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		  	   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getItemByQueryParameters(), due to: " +  hbe.getMessage());	
		}  
    }
	
	private Item initializeItemAssociations(Item item, MultivaluedMap<String, String> queryParams, String operation){
		
		if(ImsQueryUtil.containsAnyKey(queryParams, ImsNewFeature.allProperties()) &&
		   (item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null)) {
		   ImsNewFeature imsNewFeature = new ImsNewFeature();	
		   if("insert".equalsIgnoreCase(operation))
  		      imsNewFeature.setCreatedDate(new Date());
		   else if("update".equalsIgnoreCase(operation))
	  		  imsNewFeature.setLastModifiedDate(new Date());
		   item.addImsNewFeature(imsNewFeature);	
		}
		if(ImsQueryUtil.containsAnyKey(queryParams, ItemVendor.allProperties()) &&
  	      (item.getNewVendorSystem() == null || item.getNewVendorSystem().isEmpty())){
    	   item.initVendors(ImsQueryUtil.determineNumberOfVendors(queryParams));
		}
  	   	if(ImsQueryUtil.containsAnyKey(queryParams, IconCollection.allPropertis())){
		   item.addIconDescription(new IconCollection());	
		}   
		/*if(ImsQueryUtil.containsAnyKey(queryParams, Arrays.asList(new String[] {"poNote", "poNotes", "ponotes"}))) {	
		   Note poNote = new Note("po");
		   if("insert".equalsIgnoreCase(operation))
  		      poNote.setCreatedDate(new Date());
		   else if("update".equalsIgnoreCase(operation))
			   poNote.setLastModifiedDate(new Date()); 
		   item.addNote(poNote);
		}  
		if(ImsQueryUtil.containsAnyKey(queryParams, Arrays.asList(new String[] {"buyerNote", "note1", "notes1"}))) {
		   Note buyerNote = new Note("buyer");
		   if("insert".equalsIgnoreCase(operation))
		       buyerNote.setCreatedDate(new Date());
		   else if("update".equalsIgnoreCase(operation))
			   buyerNote.setLastModifiedDate(new Date());
		   item.addNote(buyerNote);
		}
		if(ImsQueryUtil.containsAnyKey(queryParams, Arrays.asList(new String[] {"invoiceNote", "note3", "notes3"}))) {
		   Note invoiceNote = new Note("invoice");
		   if("insert".equalsIgnoreCase(operation))
		      invoiceNote.setCreatedDate(new Date());
		   else if("update".equalsIgnoreCase(operation))
			   invoiceNote.setLastModifiedDate(new Date()); 
		   item.addNote(invoiceNote);
		}
		if(ImsQueryUtil.containsKey(queryParams, "internalNote")) {
		   Note internalNote = new Note("internal");
		   if("insert".equalsIgnoreCase(operation))
		       internalNote.setCreatedDate(new Date());
		   else if("update".equalsIgnoreCase(operation))
			   internalNote.setLastModifiedDate(new Date()); 
		   item.addNote(internalNote);
		}
		if(ImsQueryUtil.containsAnyKey(queryParams, Arrays.asList(new String[] {"additionalNote", "note1", "notes2"}))) {
		   Note additionalNote = new Note("additional");
		   if("insert".equalsIgnoreCase(operation))
  		      additionalNote.setCreatedDate(new Date());
		   else if("update".equalsIgnoreCase(operation))
			   additionalNote.setLastModifiedDate(new Date());
		   item.addNote(additionalNote);
		}*/
		//if(ImsQueryUtil.containsAnyKey(queryParams, Arrays.asList(new String[]{"colorHue", "colorhue", "colorHues", "colorHues", "colorCategory", "colorcategory"})))
		//   item.addNewColorHueSystem(new ColorHue());
		return item;
	}
	
    private Item initializeItemAssociations(Item item, JSONObject inputJsonObj, String operation){
		
       if(ImsQueryUtil.containsAnyKey(inputJsonObj, ImsNewFeature.allProperties()) &&
    	   (item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null)) {
		   ImsNewFeature imsNewFeature = new ImsNewFeature();	
		   if("insert".equalsIgnoreCase(operation))
    	      imsNewFeature.setCreatedDate(new Date());
		   else if("update".equalsIgnoreCase(operation))
			  imsNewFeature.setLastModifiedDate(new Date());
    	   item.addImsNewFeature(imsNewFeature);	
	   }
       if(ImsQueryUtil.containsAnyKey(inputJsonObj, ItemVendor.allProperties()) &&
         (item.getNewVendorSystem() == null || item.getNewVendorSystem().isEmpty())){
		  item.initVendors(ImsQueryUtil.determineNumberOfVendors(inputJsonObj));
	   }
	   if(ImsQueryUtil.containsAnyKey(inputJsonObj, IconCollection.allPropertis())){
	      item.addIconDescription(new IconCollection());	
	   }   
     /*  if(inputJsonObj.has("poNote") || inputJsonObj.has("poNotes") || inputJsonObj.has("ponotes")) {   	  
		  Note poNote = new Note("po");
		  if("insert".equalsIgnoreCase(operation))
		     poNote.setCreatedDate(new Date());
		  else if("update".equalsIgnoreCase(operation))
			  poNote.setLastModifiedDate(new Date());		  
		  item.addNote(poNote);
	   }  
	    if(inputJsonObj.has("buyerNote") || inputJsonObj.has("note1") || inputJsonObj.has("notes1")) {	   
		  Note buyerNote = new Note("buyer");
		  if("insert".equalsIgnoreCase(operation))
		     buyerNote.setCreatedDate(new Date());
		  else if("update".equalsIgnoreCase(operation))
			  buyerNote.setLastModifiedDate(new Date()); 
		  item.addNote(buyerNote);
	   }
	   if(inputJsonObj.has("invoiceNote") || inputJsonObj.has("note3") || inputJsonObj.has("notes3")) {	   
	      Note invoiceNote = new Note("invoice");
	      if("insert".equalsIgnoreCase(operation))
	         invoiceNote.setCreatedDate(new Date());
	      else if("update".equalsIgnoreCase(operation))
	    	  invoiceNote.setLastModifiedDate(new Date());
	      item.addNote(invoiceNote);
	   }
	   if(inputJsonObj.has("internalNote")) {
	      Note internalNote = new Note("internal");
	      if("insert".equalsIgnoreCase(operation))
		     internalNote.setCreatedDate(new Date());
	      else if("update".equalsIgnoreCase(operation))
	    	  internalNote.setLastModifiedDate(new Date());
		  item.addNote(internalNote);
	   }
	   if(inputJsonObj.has("additionalNote") || inputJsonObj.has("note2") || inputJsonObj.has("notes2")) {	   
		  Note additionalNote = new Note("additional");
		  if("insert".equalsIgnoreCase(operation))
		     additionalNote.setCreatedDate(new Date());
		  else if("update".equalsIgnoreCase(operation))
			  additionalNote.setLastModifiedDate(new Date());
		  item.addNote(additionalNote);
	   }*/
	   //if(ImsQueryUtil.containsAnyKey(inputJsonObj, Arrays.asList(new String[]{"colorHue", "colorhue", "colorHues", "colorHues", "colorCategory", "colorcategory"})))
	   //	  item.addNewColorHueSystem(new ColorHue());
	   return item;
	}

    @Transactional
    public void createColorHues(){
       List<Item> items = itemDao.findAll(sessionFactory.getCurrentSession());
       Set<ColorHue> colorHues = new HashSet<>();
       for(Item item : items){
    	   for(ColorHue colorHue : item.getColorhues()){
    		   colorHues.add(colorHue);   
    	   }
       }
       colorHueDao.createColorHues(colorHues);
    }
    
}
