package com.bedrosians.bedlogic.service.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

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
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.ImsDataUtil;
import com.bedrosians.bedlogic.util.ImsQueryUtil;
import com.bedrosians.bedlogic.util.ImsValidator;
import com.bedrosians.bedlogic.util.JsonUtil;
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
		   itemDao.updateItem(session,itemToUpdate);
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
