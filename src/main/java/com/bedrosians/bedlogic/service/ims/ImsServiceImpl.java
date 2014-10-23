package com.bedrosians.bedlogic.service.ims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.ims.ImsDao;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.JsonUtil;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.enums.DBOperation;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;
import com.bedrosians.bedlogic.util.product.ImsDataUtil;
import com.bedrosians.bedlogic.util.product.ImsValidator;

@Service("imsService")
public class ImsServiceImpl implements ImsService {

    @Autowired
	private ImsDao imsDao;  
    
    @Autowired
	private SessionFactory sessionFactory;
      	    	  
    //--------------------------------Retrieval DB Operation --------------------------//
    
    @Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public Ims getItemByItemCode(String itemCode) throws BedDAOBadParamException, BedDAOException{
    	Ims ims = null;
    	if(itemCode == null || itemCode.length() < 1)
    	   throw new BedDAOBadParamException("Please enter valid product code !");	
		try{
			Session session = getSession();
			session.setCacheMode(CacheMode.NORMAL);
	  	    ims = imsDao.getItemByItemCode(session, itemCode);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  hbe.getMessage());	
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage());	
		}
		return FormatUtil.process(ims);
	}

    @Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true)
	public List<Ims> getActiveAndShownOnWebsiteItems() throws BedDAOBadParamException, BedDAOException{
    	return imsDao.getActiveAndShownOnWebsiteItems();
	}
    
	@Loggable(value = LogLevel.INFO)
	@Override
	public List<Ims> getItems(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException{
		if(queryParams == null || queryParams.isEmpty()){
			queryParams = new MultivaluedMapImpl();
			queryParams.put("inactivecode", Arrays.asList(new String[]{"N"}));
		}
		List<Ims> itemList = null;
		try{
			itemList = imsDao.getItemsByQueryParameters(queryParams);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage());
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage());	
		}
		List<Ims> processedItems = new ArrayList<>();
		for(Ims ims : itemList){
			processedItems.add(FormatUtil.process(ims));
		}
		return processedItems;
	}
	

	@Loggable(value = LogLevel.INFO)
	@Override
	public List<ItemWrapper> getWrappedItems(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException{
		if(queryParams == null || queryParams.isEmpty()){
			queryParams = new MultivaluedMapImpl();
			queryParams.put("inactivecode", Arrays.asList(new String[]{"N"}));
		}
		List<Ims> itemList = null;
		try{
			itemList = imsDao.getItemsByQueryParameters(queryParams);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage());
		
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage());	
		}
		List<ItemWrapper> productWrapperList = new ArrayList<ItemWrapper>(itemList.size());
		for(Ims ims : itemList){
			productWrapperList.add(new ItemWrapper(FormatUtil.process(ims)));
		}
		return productWrapperList;
	}
	
    
	//--------------------------------Creation DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	public String createItem(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException{  	
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		String id;
		Ims itemToCreate = new Ims(itemCode);
     	Ims itemFromInput = (Ims)JsonUtil.jsonObjectToPOJO(jsonObj, new Ims());
     	itemToCreate = ImsDataUtil.transformItem(itemToCreate, itemFromInput, DBOperation.CREATE);
     	ImsValidator.validateNewItem(itemToCreate);
   	    try{
		   id = imsDao.createItem(itemToCreate);
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
			  throw new BedDAOBadParamException("Invalid Product code, since it is already existing in the database");
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
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) 
	public synchronized void updateItem(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException{
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		Ims itemFromInput = (Ims)JsonUtil.jsonObjectToPOJO(jsonObj, new Ims());
		Ims itemToUpdate = null;
		Session session = getSession();
		try{
			itemToUpdate = imsDao.getItemByItemCode(session, itemCode.trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage());	
		}
		if(itemToUpdate == null)
	       throw new BedDAOException("No data found for the given item code");	 
		itemToUpdate = ImsDataUtil.transformItem(itemToUpdate, itemFromInput, DBOperation.UPDATE);
  	    ImsValidator.validateNewItem(itemToUpdate);
    	try{
		      imsDao.updateItem(session,itemToUpdate);
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
	
	//--------------------------------Deletion DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	synchronized public void deleteItemByItemCode(String itemCode) throws BedDAOBadParamException, BedDAOException{
	    if(itemCode == null || itemCode.length() == 0)
	    	 throw new BedDAOBadParamException("Product code should not be empty");		
		Ims ims = new Ims(itemCode);
		try{
			imsDao.deleteItem(ims);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProducts(), due to: " +  hbe.getMessage());
		
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getProductById(), due to: " +  e.getMessage());	
		}
	}
	
	@Loggable(value = LogLevel.INFO)
	@Override
	synchronized public void deleteItem(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException{
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		deleteItemByItemCode(itemCode);
	}

	private synchronized Session getSession(){
	    	return sessionFactory.getCurrentSession();
	}
}
