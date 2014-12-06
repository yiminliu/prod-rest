package com.bedrosians.bedlogic.service.mvc.ims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;













import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

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
import com.bedrosians.bedlogic.dao.ims.KeymarkVendorDao;
import com.bedrosians.bedlogic.domain.ims.ColorHue;
import com.bedrosians.bedlogic.domain.ims.IconCollection;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.ImsNewFeature;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.domain.ims.embeddable.Applications;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.DataOperationException;
import com.bedrosians.bedlogic.exception.InputParamException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.enums.DBOperation;
import com.bedrosians.bedlogic.util.ims.ImsDataUtil;
import com.bedrosians.bedlogic.util.ims.ImsValidator;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;

@Service("imsServiceMVC")
public class ImsServiceMVCImpl implements ImsServiceMVC {

    @Autowired
	private ImsDao imsDao;
    
    @Autowired
	private ColorHueDao colorHueDao; 
    
    @Autowired
   	private KeymarkVendorDao keymarkVendorDao;
    
    @Autowired
	private SessionFactory sessionFactory;
      	    	  
    //--------------------------------Retrieval DB Operation --------------------------//
    
    @Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public Ims getItemByItemCode(String itemCode){
    	Ims ims = null;
    	if(itemCode == null || itemCode.length() < 1)
    	   throw new InputParamException("Please enter valid Item Code !");	
		try{
			Session session = getSession();
			session.setCacheMode(CacheMode.NORMAL);
	  	    ims = imsDao.getItemByItemCode(session, itemCode);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		  	   throw new DataOperationException("Error occured during getItemByItemCode, due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		  	else
		  	   throw new DataOperationException("Error occured during getItemByItemCode, due to: " +  hbe.getMessage());	
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DataOperationException("Error occured during getItemByItemCode, due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new DataOperationException("Error occured during getItemByItemCode, due to: " +  e.getMessage());	
		}
		return FormatUtil.process(ims);
	}

    public boolean itemCodeIsTaken(String itemCode){
    	List<String> itemCodeList = null;
    	try{
     	  itemCodeList = imsDao.getItemCodeList();
    	}
    	catch(Exception e){
    		 throw new DataOperationException("Error occured while retriving item code. " + e);
    	}
    	for(String s : itemCodeList){
    		if(s.trim().equalsIgnoreCase(itemCode))
    		   return true;
    	}
    	return false;
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
	public List<Ims> getItems(LinkedHashMap<String, List<String>> queryParams){
		if(queryParams == null || queryParams.isEmpty()){
			//queryParams = new MultivaluedMapImpl();
			//queryParams.put("inactivecode", Arrays.asList(new String[]{"N"}));
		}
		List<Ims> itemList = null;
		try{
			itemList = imsDao.getItems(queryParams);
		}
		catch(HibernateException hbe){
	    	if(hbe.getCause() != null)
		       throw new DataOperationException("Error occured during getItems(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage(), hbe);	
		  	else
		  	   throw new DataOperationException("Error occured during getItems(), due to: " +  hbe.getMessage());
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DataOperationException("Error occured during getItems(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage(), e);	
		  	else
		  	   throw new DataOperationException("Error occured during getItems(), due to: " +  e.getMessage(), e);	
		}
		List<Ims> processedItems = new ArrayList<>();
		for(Ims ims : itemList){
			processedItems.add(FormatUtil.process(ims));
		}
		return processedItems;
	}
	
    
	//--------------------------------Creation DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	public String createItem(Ims item){  	
		String id = "";
		try{
     	   ImsValidator.validateNewItem(item);
		}
		catch(Exception e){
			throw new InputParamException("Input valiation error: "+e.getMessage(), e);
		}
		//take care of associations
     	ImsNewFeature newFeature = item.getNewFeature();
     	if(newFeature != null && !newFeature.isEmpty())
     	   item.addNewFeature(newFeature);
     	else
     	   item.setNewFeature(null);
     	List<ColorHue> colorhues = item.getColorhues();
     	if(colorhues != null && !colorhues.isEmpty()){
     	   item.setColorhues(null);
     	   for(ColorHue colorhue : colorhues){
     		   item.addColorhue(colorhue);
     	   }
     	}
     	IconCollection icons = item.getIconDescription();
     	if(icons != null && !icons.isEmpty())
     	   item.addIconDescription(icons);	
     	else
     	   item.setIconDescription(null);	
     	List<Vendor> vendors = item.getNewVendorSystem();
     	item.setNewVendorSystem(null);
     	if(vendors != null && !vendors.isEmpty()){
     		for(Vendor vendor : vendors){
     			if(vendor.getId() != null)
           		  item.addNewVendorSystem(vendor);
     		}	
     	}
     	item = processApplications(item);
     	try{
		   id = imsDao.createItem(item);
		}
		catch(HibernateException hbe){
		   hbe.printStackTrace();
		   if(hbe.getCause() != null)
		      throw new DataOperationException("Error occured during createItem(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
		   else
		  	  throw new DataOperationException("Error occured during createItem(), due to: " +  hbe.getMessage());	
	    }	
   	    catch(Exception e){
		  e.printStackTrace();
		  if(e != null && e.getMessage() != null){
			  if(e.getMessage().contains("constraint [item_code]"))
				  throw new InputParamException("Invalid item code, since it is already existing in the database", e);
		      else if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			      throw new InputParamException("Invalid vendor number (ID), since it cannot be found in the vendor table", e);
		      else
		    	  throw new DataOperationException("Error occured during createItem(), due to: " + e.getMessage());
		  }
		  else if(e.getCause() != null)
	  	     throw new DataOperationException("Error occured during createItem(), due to: " +  " Root cause: " + e.getCause().getMessage());	
	  	  else
	  	     throw new DataOperationException("Error occured during createItem().");	
      }
	  return id;		 	
    }
	
	//--------------------------------Update DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) 
	public synchronized void updateItem(Ims itemFromInput){
		Ims itemToUpdate = null;
		Session session = getSession();
		try{
			itemToUpdate = imsDao.getItemByItemCode(session, itemFromInput.getItemcode().trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new DataOperationException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DataOperationException("Error occured during updateItem(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new DataOperationException("Error occured during updateItem(), due to: " +  e.getMessage());	
		}
		if(itemToUpdate == null)
	       throw new DataOperationException("No data found for the given item code: " + itemFromInput.getItemcode());	 
		try {
			itemToUpdate = ImsDataUtil.transformItem(itemToUpdate, itemFromInput, DBOperation.UPDATE);
  	        ImsValidator.validateNewItem(itemToUpdate);
		}
		catch(Exception e){
		   throw new InputParamException("Error in input parameters.", e);
		}
		try{
		   imsDao.updateItem(session,itemToUpdate);
	 	}
    	catch(HibernateException hbe){
     	      if(hbe.getCause() != null)
 		         throw new InputParamException("Error occured during updateItem, due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
 		      else
 		  	     throw new InputParamException("Error occured during updateItem, due to: " +  hbe.getMessage());	
 	    }	
    	catch(Exception e){
			  if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			     throw new InputParamException("Invalid vendor number (ID), since it cannot be found in the vendor table");
			  if(e.getCause() != null)
		         throw new InputParamException("Error occured during updateItem(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	  else
			     throw new InputParamException("Error occured during updateItem(), due to: " +  e.getMessage());	
		   }	   
	}
	
	@Loggable(value = LogLevel.INFO)
	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) 
	public synchronized void deactivateItem(Ims itemFromInput) throws BedDAOBadParamException, BedDAOException{
		Ims itemToUpdate = null;
		Session session = getSession();
		try{
			itemToUpdate = imsDao.getItemByItemCode(session, itemFromInput.getItemcode().trim());
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
	       throw new BedDAOException("No data found for the given item code: " + itemFromInput.getItemcode());	 
		
		itemToUpdate.setInactivecode("Y");
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
	synchronized public void deleteItem(Ims item) throws BedDAOBadParamException, BedDAOException{
		 if(item.getItemcode() == null || item.getItemcode().length() == 0)
	    	 throw new BedDAOBadParamException("Item code should not be empty");
		imsDao.deleteItem(item);
	}

	private synchronized Session getSession(){
	    	return sessionFactory.getCurrentSession();
	}
	
	public void initVendors(int n){
		new Ims().initVendors(3);
	}
	
	public List<Vendor> getNewVendorSystem(){
		Ims item = new Ims();
		item.initVendors(3);
		return item.getNewVendorSystem();
	}
	
	private Ims processApplications(Ims item){
		Applications app = item.getApplications();
     	if(app != null){
     	   String residential = app.getResidential();
    	   String lightCommercial = app.getLightcommercial();
    	   String commercial = app.getCommercial();
    	
    	   if(residential != null)
    		  app.setResidential(residential.replace(",", ":"));
    	   if(lightCommercial != null)
    		  app.setLightcommercial(lightCommercial.replace(",", ":"));
    	   if(commercial != null)
    		  app.setCommercial(commercial.replace(",", ":"));
     	}   
    	item.setApplications(app);
    	return item;
	} 	
	
	public boolean validateVendorId(Integer vendorId){
		List<Integer> keymarkVedorIdList = null;
		try{
			keymarkVedorIdList = keymarkVendorDao.getKeymarkVedorIdList();
		}
		catch(BedDAOException e){
			e.printStackTrace();
		}
    	for(Integer id : keymarkVedorIdList){
    		id = Integer.valueOf(String.valueOf(id).trim());
    		if(id.equals(vendorId))
    		   return true;
    	}
    	return false;
	}
}
