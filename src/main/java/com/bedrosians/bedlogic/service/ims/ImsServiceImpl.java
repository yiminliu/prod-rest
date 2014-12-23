package com.bedrosians.bedlogic.service.ims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

import com.bedrosians.bedlogic.dao.ims.ColorHueDao;
import com.bedrosians.bedlogic.dao.ims.ImsDao;
import com.bedrosians.bedlogic.domain.ims.ColorHue;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.service.mvc.ims.ImsServiceMVC;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.JsonUtil;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.enums.DBOperation;
import com.bedrosians.bedlogic.util.ims.ImsDataUtil;
import com.bedrosians.bedlogic.util.ims.ImsValidator;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;

@Service("imsService")
public class ImsServiceImpl implements ImsService {

    @Autowired
	private ImsDao imsDao;
    
    @Autowired
	private ColorHueDao colorHueDao; 
    
    @Autowired
	private SessionFactory sessionFactory;
    
    @Autowired
   	private ImsServiceMVC imsServiceMVC;
      	    	  
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
		  	   throw new BedDAOException("Error occured during getItemByItemCode, due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getItemByItemCode, due to: " +  hbe.getMessage());	
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getItemByItemCode, due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getItemByItemCode, due to: " +  e.getMessage());	
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
		       throw new BedDAOException("Error occured during getItems(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getItems(), due to: " +  hbe.getMessage());
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getItems(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getItems(), due to: " +  e.getMessage());	
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
		       throw new BedDAOException("Error occured during getWrappedItems, due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getWrappedItems, due to: " +  hbe.getMessage());
		
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during getWrappedItems, due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during getWrappedItems, due to: " +  e.getMessage());	
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
     	Ims itemFromInput = (Ims)JsonUtil.jsonObjectToPOJO(jsonObj, new Ims());
     	itemFromInput.setItemcode(itemFromInput.getItemcode().toUpperCase());
     	id = imsServiceMVC.createItem(itemFromInput, DBOperation.CREATE);
     	/*
     	Ims itemToCreate = new Ims(itemCode);
     	itemToCreate = ImsDataUtil.transformItem(itemToCreate, itemFromInput, DBOperation.CREATE);
     	ImsValidator.validateNewItem(itemToCreate);
   	    try{
		   id = imsDao.createItem(itemToCreate);
		}
		catch(HibernateException hbe){
		   hbe.printStackTrace();
		   if(hbe.getCause() != null)
		      throw new BedDAOException("Error occured during createItem(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		   else
		  	  throw new BedDAOException("Error occured during createItem(), due to: " +  hbe.getMessage());	
	    }	
   	    catch(Exception e){
		  e.printStackTrace();
		  if(e != null && e.getMessage().contains("constraint [item_code]"))
			  throw new BedDAOBadParamException("Invalid item code, since it is already existing in the database");
		  else if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			  throw new BedDAOBadParamException("Invalid vendor number (ID), since it cannot be found in the vendor table");
		  else if(e.getCause() != null)
	  	     throw new BedDAOException("Error occured during createItem(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
	  	  else
	  	     throw new BedDAOException("Error occured during createItem(), due to: " +  e.getMessage());	
      }*/
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
		//if((itemFromInput.getColorhues() != null && !itemFromInput.getColorhues().isEmpty()) || 
		//  		   (itemFromInput.getColorcategory() != null && !itemFromInput.getColorcategory().isEmpty())){ //existing colorhues
		//   Set<ColorHue> colorhues = colorHueDao.getItemColorHues(itemCode);
		//   if(colorhues != null && !colorhues.isEmpty()) {
		//	   for(ColorHue colorHue : colorhues){
		//		   colorHueDao.delete(session, colorHue);
		//	   }
		//   }
		//}
	
		try{
			itemToUpdate = imsDao.getItemByItemCode(session, itemCode.trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new BedDAOException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during updateItem(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during updateItem(), due to: " +  e.getMessage());	
		}
		if(itemToUpdate == null)
	       throw new BedDAOException("No data found for the given item code: " + itemCode);	 
		
		itemToUpdate = ImsDataUtil.transformItem(itemToUpdate, itemFromInput, DBOperation.UPDATE);
  	    ImsValidator.validateNewItem(itemToUpdate);
    	try{
		      imsDao.updateItem(session,itemToUpdate);
	 	}
    	catch(HibernateException hbe){
     	      if(hbe.getCause() != null)
 		         throw new BedDAOException("Error occured during updateItem, due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
 		      else
 		  	     throw new BedDAOException("Error occured during updateItem, due to: " +  hbe.getMessage());	
 	    }	
    	catch(Exception e){
			  if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			     throw new BedDAOBadParamException("Invalid vendor number (ID), since it cannot be found in the vendor table");
			  if(e.getCause() != null)
		         throw new BedDAOException("Error occured during updateItem(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	  else
			     throw new BedDAOException("Error occured during updateItem(), due to: " +  e.getMessage());	
		   }	   
	}
	
	//--------------------------------Deletion DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	synchronized public void deleteItemByItemCode(String itemCode) throws BedDAOBadParamException, BedDAOException{
	    if(itemCode == null || itemCode.length() == 0)
	    	 throw new BedDAOBadParamException("Item code should not be empty");		
		Ims ims = new Ims(itemCode);
		try{
			imsDao.deleteItem(ims);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new BedDAOException("Error occured during deleteItemByItemCode(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during deleteItemByItemCode(), due to: " +  hbe.getMessage());
		
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new BedDAOException("Error occured during deleteItemByItemCode(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new BedDAOException("Error occured during deleteItemByItemCode(), due to: " +  e.getMessage());	
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
