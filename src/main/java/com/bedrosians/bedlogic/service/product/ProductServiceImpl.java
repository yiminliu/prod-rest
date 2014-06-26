package com.bedrosians.bedlogic.service.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

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
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.enums.DBOperation;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.ImsDataUtil;
import com.bedrosians.bedlogic.util.ImsValidator;
import com.bedrosians.bedlogic.util.JsonUtil;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
	private ItemDao itemDao;  
    
    @Autowired
	private SessionFactory sessionFactory;
      	    	  
    //--------------------------------Retrieval DB Operation --------------------------//
    
    @Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true)
	public Item getProductById(String id) throws BedDAOBadParamException, BedDAOException{
    	Item item = null;
    	if(id == null || id.length() < 1)
    	   throw new BedDAOBadParamException("Please enter valid item code !");	
		try{
	  	    item = itemDao.getItemById(getSession(), id);
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

    @Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true)
	public List<Item> getActiveAndShownOnWebsiteProducts() throws BedDAOBadParamException, BedDAOException{
    	return itemDao.getActiveAndShownOnWebsiteItems();
	}
    
	@Loggable(value = LogLevel.INFO)
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
	
	//--------------------------------Creation DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
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
		  if(e != null && e.getMessage().contains("constraint [ims_id]"))
			  throw new BedDAOBadParamException("Invalid Item code, since it is already existing in the database");
		  else if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			  throw new BedDAOBadParamException("Invalid vendor number (ID), since it cannot be found in the vendor table");
		  else if(e.getCause() != null)
	  	     throw new BedDAOException("Error occured during createProduct(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
	  	  else
	  	     throw new BedDAOException("Error occured during createProduct(), due to: " +  e.getMessage());	
      }
	  return id;		 	
    }
	
	//--------------------------------Update DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) 
	public void updateProduct(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException, BedResException{
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		Item itemFromInput = (Item)JsonUtil.jsonObjectToPOJO(jsonObj, new Item());
		Item itemToUpdate = null;
		Session session = getSession();
		try{
			itemToUpdate = itemDao.getItemById(session, itemCode.trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		if(itemToUpdate == null)
	       throw new BedResException("No data found for the given item code");	
	 	
		synchronized(itemToUpdate){
	  	   itemToUpdate = ImsDataUtil.transformItem(itemToUpdate, itemFromInput, DBOperation.UPDATE);
		   ImsValidator.validateNewItem(itemToUpdate);
    	   try{
		      itemDao.updateItem(session,itemToUpdate);
	 	   }
    	   catch(HibernateException hbe){
     	      if(hbe.getCause() != null)
 		         throw new BedDAOException("Error occured during createProduct(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
 		      else
 		  	     throw new BedDAOException("Error occured during createProduct(), due to: " +  hbe.getMessage());	
 	       }	
    	   catch(Exception e){
			  if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			     throw new BedDAOBadParamException("Invalid vendor number (ID), since it cannot be found in the vendor table");
			  if(e.getCause() != null)
		         throw new BedDAOException("Error occured during updateProduct(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	  else
			     throw new BedDAOException("Error occured during updateProduct(), due to: " +  e.getMessage());	
		   }
		}	   
	}
	
	//--------------------------------Deletion DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
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
	
	@Loggable(value = LogLevel.INFO)
	@Override
    public void deleteProduct(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException{
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		deleteProductById(itemCode);
	}

	private synchronized Session getSession(){
	    	return sessionFactory.getCurrentSession();
	}
}
