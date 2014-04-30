package com.bedrosians.bedlogic.service.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.item.ItemDao;
import com.bedrosians.bedlogic.domain.item.IconCollection;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.Note;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.ImsQueryUtil;
import com.bedrosians.bedlogic.util.ImsValidation;
import com.bedrosians.bedlogic.util.JsonUtil;
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
	public List<Item> getProducts(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException{
			
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
	public List<Item> getProducts(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{
		if(inputJsonObj == null || inputJsonObj.length() < 1)
	    	   throw new BedDAOBadParamException("Please enter valid item code !");	
	    List<Item> items = null;
		try {
			//convert JSON string to Map
			MultivaluedMap mmap = JsonUtil.JsonStringToMultivaluedMap(inputJsonObj.toString());//new MultivaluedMapImpl();
		/*	Map<String, String> map = new HashMap<>();
		    ObjectMapper mapper = new ObjectMapper();
		    map = mapper.readValue(inputJsonObj.toString(), new TypeReference<Map<String, String>>(){}); 
			String key = null;
  			for(Iterator it =  map.entrySet().iterator(); it.hasNext();){
  				Entry<String, List<String>> entry = (Entry<String, List<String>>)it.next();
  	   		   	key = (String)entry.getKey();
  				mmap.add(key, map.get(key));
  			}
		*/		
			items = getProducts(mmap);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
		
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
	public String createProduct(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{  	
    	String id;
    	Item item = new Item();
    	item = initializeItemAssociationsForInsert(item, inputJsonObj);
		synchronized(item){
		   try{
		      item = ImsQueryUtil.buildItemFromJsonObjectForInsert(item, inputJsonObj);
		   }
		   catch(Exception e){
			  e.printStackTrace();
			  throw new BedDAOBadParamException(e);
		   }
		   ImsValidation.validateNewItem(item);
		   try{
		      id = itemDao.createItem(item);
		   }
		   catch(HibernateException hbe){
			  hbe.printStackTrace();
			  throw new BedDAOException("Error occured during createProduct() due to: " + hbe.getMessage(), hbe);
	       }
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
	public String createProduct(final MultivaluedMap<String, String> queryParams)throws BedDAOBadParamException, BedDAOException{
		String id;
		ImsValidation.validateInsertUpdateParams(queryParams);
		Item item = new Item(ImsQueryUtil.getItemCode(queryParams));
		synchronized(item){
		   item = initializeItemAssociationsForInsert(item, queryParams);
		   try{
		      item = ImsQueryUtil.buildItemForInsert(item, queryParams);
		   }
	       catch(Exception e){
		      e.printStackTrace();
		      throw new BedDAOBadParamException(e);
	       }
		   ImsValidation.validateNewItem(item);
		   try{
		      id = itemDao.createItem(item);
		   }
		   catch(HibernateException hbe){
			  hbe.printStackTrace();
			  throw new BedDAOException("Error occured during createProduct() due to: " + hbe.getMessage(), hbe);
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
		   itemDao.updateItem(item); 
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
	//@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{
		String itemcd = JsonUtil.getItemCode(inputJsonObj); 
		if(itemcd == null || itemcd.length() == 0)
		   throw new BedDAOBadParamException("Item code should not be empty");	
		Item item = new Item(itemcd);
		//item = initializeItemAssociations(item, inputJsonObj, "Update");
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
		if(item.getImsNewFeature() != null)
		   item.getImsNewFeature().setLastModifiedDate(new Date());
		try{
		   itemDao.updateItem(item);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }  
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	//@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void updateProduct(final MultivaluedMap<String, String> queryParams) throws BedDAOException, BedResException{
		
		ImsValidation.validateInsertUpdateParams(queryParams);	
		String itemcd = ImsQueryUtil.getValue(queryParams, "itemcd");
		if(itemcd == null || itemcd.trim().length() == 0)
			itemcd = ImsQueryUtil.getValue(queryParams, "itemcode");	
		Item item = new Item(itemcd); 
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
			itemDao.updateItem(item);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
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
			throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
		}  
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	public void deleteProduct(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException, BedResException{
		String itemcd = ImsQueryUtil.getItemCode(queryParams);
		if(itemcd == null || itemcd.length() == 0)
		   throw new BedDAOBadParamException("Item code should not be empty");	
		Item item = new Item(itemcd);
		try{
			itemDao.deleteItem(item);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
		}  
    }
	
	private Item initializeItemAssociationsForInsert(Item item, MultivaluedMap<String, String> queryParams){
		
		if(ImsQueryUtil.containsAnyKey(queryParams, ImsNewFeature.allProperties()) &&
		   item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null) {
		   ImsNewFeature imsNewFeature = new ImsNewFeature();	
  		   imsNewFeature.setCreatedDate(new Date());
		   item.addImsNewFeature(imsNewFeature);	
		}
  	    if(item.getNewVendorSystem() == null || item.getNewVendorSystem().isEmpty()){
		   item.initVendors(ImsQueryUtil.determineNumberOfVendors(queryParams));
		}
		if(ImsQueryUtil.containsAnyKey(queryParams, IconCollection.allPropertis()))
		   item.addIconDescription(new IconCollection());	
		if(ImsQueryUtil.containsKey(queryParams, "poNote")) {
		   Note poNote = new Note("po");
		   poNote.setCreatedDate(new Date());
		   item.addNote(poNote);
		}  
		if(ImsQueryUtil.containsKey(queryParams, "buyerNote")) {
		   Note buyerNote = new Note("buyer");
		   buyerNote.setCreatedDate(new Date());
		   item.addNote(buyerNote);
		}
		if(ImsQueryUtil.containsKey(queryParams, "invoiceNote")) {
		   Note invoiceNote = new Note("invoice");
		   invoiceNote.setCreatedDate(new Date());
		   item.addNote(invoiceNote);
		}
		if(ImsQueryUtil.containsKey(queryParams, "internalNote")) {
		   Note internalNote = new Note("internal");
		   internalNote.setCreatedDate(new Date());
		   item.addNote(internalNote);
		}
		if(ImsQueryUtil.containsKey(queryParams, "additional")) {
		   Note internalNote = new Note("additional");
		   internalNote.setCreatedDate(new Date());
		   item.addNote(internalNote);
		}
		return item;
	}
	
    private Item initializeItemAssociationForUpdate(Item item, MultivaluedMap<String, String> queryParams){
		
		if(ImsQueryUtil.containsAnyKey(queryParams, ImsNewFeature.allProperties()) &&
		   item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null) {
		   ImsNewFeature imsNewFeature = new ImsNewFeature();	
	  	   imsNewFeature.setLastModifiedDate(new Date());
		   item.addImsNewFeature(imsNewFeature);	
		}
  	    if(item.getNewVendorSystem() == null || item.getNewVendorSystem().isEmpty()){
		   item.initVendors(ImsQueryUtil.determineNumberOfVendors(queryParams));
		}
		//if(ImsQueryUtil.containsAnyKey(queryParams, IconCollection.allPropertis()))
		   item.addIconDescription(new IconCollection());	
		if(ImsQueryUtil.containsKey(queryParams, "poNote")) {
		   Note poNote = new Note("po");
		   poNote.setLastModifiedDate(new Date());
		   item.addNote(poNote);
		}  
		if(ImsQueryUtil.containsKey(queryParams, "buyerNote")) {
		   Note buyerNote = new Note("buyer");
		   buyerNote.setLastModifiedDate(new Date());
		   item.addNote(buyerNote);
		}
		if(ImsQueryUtil.containsKey(queryParams, "invoiceNote")) {
		   Note invoiceNote = new Note("invoice");
		   invoiceNote.setLastModifiedDate(new Date());
		   item.addNote(invoiceNote);
		}
		if(ImsQueryUtil.containsKey(queryParams, "internalNote")) {
		   Note internalNote = new Note("internal");
		   internalNote.setLastModifiedDate(new Date());
		   item.addNote(internalNote);
		}
		if(ImsQueryUtil.containsKey(queryParams, "additional")) {
		   Note internalNote = new Note("additional");
		   internalNote.setLastModifiedDate(new Date());
		   item.addNote(internalNote);
		}
		return item;
	}

    private Item initializeItemAssociationsForInsert(Item item, JSONObject inputJsonObj){
		
	   if(item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null) {
		   ImsNewFeature imsNewFeature = new ImsNewFeature();	
    	   imsNewFeature.setCreatedDate(new Date());
    	   item.addImsNewFeature(imsNewFeature);	
	   }
	   if(item.getNewVendorSystem() == null || item.getNewVendorSystem().isEmpty()){
		  item.initVendors(ImsQueryUtil.determineNumberOfVendors(inputJsonObj));
	   }
	   if(ImsQueryUtil.containsAnyKey(inputJsonObj, IconCollection.allPropertis()))
	      item.addIconDescription(new IconCollection());	
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
	   if(inputJsonObj.has("additional")) {
		      Note internalNote = new Note("additional");
			  internalNote.setCreatedDate(new Date());
			  item.addNote(internalNote);
		   }
		return item;
	}
    
    private Item initializeItemAssociationsForUpdate(Item item, JSONObject inputJsonObj){
		
 	   if(item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null) {
 		   ImsNewFeature imsNewFeature = new ImsNewFeature();	
     	   imsNewFeature.setLastModifiedDate(new Date());
     	   item.addImsNewFeature(imsNewFeature);	
 	   }
 	   if(item.getNewVendorSystem() == null || item.getNewVendorSystem().isEmpty()){
 		  item.initVendors(ImsQueryUtil.determineNumberOfVendors(inputJsonObj));
 	   }
 	   if(ImsQueryUtil.containsAnyKey(inputJsonObj, IconCollection.allPropertis()))
 	      item.setIconDescription(new IconCollection());	
        if(inputJsonObj.has("poNote")) {
 		  Note poNote = new Note("po");
 		  poNote.setLastModifiedDate(new Date());
 		  item.addNote(poNote);
 	   }  
 	   if(inputJsonObj.has("buyerNote")) {
 		  Note buyerNote = new Note("buyer");
 		  buyerNote.setLastModifiedDate(new Date());
 		  item.addNote(buyerNote);
 	   }
 	   if(inputJsonObj.has("invoiceNote")) {
 	      Note invoiceNote = new Note("invoice");
 	      invoiceNote.setLastModifiedDate(new Date());
 	      item.addNote(invoiceNote);
 	   }
 	   if(inputJsonObj.has("internalNote")) {
 	      Note internalNote = new Note("internal");
 		  internalNote.setLastModifiedDate(new Date());
 		  item.addNote(internalNote);
 	   }
 	  if(inputJsonObj.has("additional")) {
 	      Note internalNote = new Note("additional");
 		  internalNote.setLastModifiedDate(new Date());
 		  item.addNote(internalNote);
 	   }
 		return item;
 	}


}
