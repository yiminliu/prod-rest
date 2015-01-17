package com.bedrosians.bedlogic.service.ims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
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

import com.bedrosians.bedlogic.dao.ims.ColorHueDao;
import com.bedrosians.bedlogic.dao.ims.ColorHueDaoImpl;
import com.bedrosians.bedlogic.dao.ims.ImsDao;
import com.bedrosians.bedlogic.dao.ims.KeymarkVendorDao;
import com.bedrosians.bedlogic.domain.ims.ColorHue;
import com.bedrosians.bedlogic.domain.ims.IconCollection;
import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.ImsNewFeature;
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.domain.ims.embeddable.Units;
import com.bedrosians.bedlogic.domain.ims.embeddable.VendorInfo;
import com.bedrosians.bedlogic.exception.DataNotFoundException;
import com.bedrosians.bedlogic.exception.DatabaseOperationException;
import com.bedrosians.bedlogic.exception.InputParamException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.JsonUtil;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;
import com.bedrosians.bedlogic.util.enums.DBOperation;
import com.bedrosians.bedlogic.util.ims.ImsDataTransferUtil;
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
   	private KeymarkVendorDao keymarkVendorDao;
    
    @Autowired
	private SessionFactory sessionFactory;
         	    	  
    //--------------------------------Retrieval DB Operation --------------------------//
   
    @Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public Ims getItemByItemCode(String itemCode){
    	Ims item = null;
    	if(itemCode == null || itemCode.length() < 1)
    	   throw new InputParamException("Please enter a valid Item Code !");	
		try{
			Session session = getSession();
	  	    item = imsDao.getItemByItemCode(session, itemCode.toUpperCase());
		}
		catch(HibernateException hbe){
			if(hbe.getCause() != null)
				throw new DatabaseOperationException("Error occured during getItems(), due to: " +  hbe.getMessage() + ". Root cause -- " + hbe.getCause().getMessage(), hbe);	
			else
				throw new DatabaseOperationException("Error occured during getItems(), due to: " +  hbe.getMessage(), hbe);
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
				throw new DatabaseOperationException("Error occured during getItems(), due to: " +  e.getMessage() + ". Root cause -- " + e.getCause().getMessage(), e);	
			else
				throw new DatabaseOperationException("Error occured during getItems(), due to: " +  e.getMessage(), e);
	    }
		return FormatUtil.process(item);
	}
    
    @Loggable(value = LogLevel.INFO)
	@Override
	public List<Ims> getItems(LinkedHashMap<String, List<String>> queryParams){
		List<Ims> itemList = null;
		try{
			itemList = imsDao.getItems(queryParams);
		}
		catch(HibernateException hbe){
		  	if(hbe.getCause() != null)
		  		throw new DatabaseOperationException("Error occured during getItems(), due to: " +  hbe.getMessage() + ". Root cause -- " + hbe.getCause().getMessage(), hbe);	
		   	else
		   		throw new DatabaseOperationException("Error occured during getItems(), due to: " +  hbe.getMessage(), hbe);
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
				throw new DatabaseOperationException("Error occured during getItems(), due to: " +  e.getMessage() + ". Root cause -- " + e.getCause().getMessage(), e);	
		   	else
		   		throw new DatabaseOperationException("Error occured during getItems(), due to: " +  e.getMessage(), e);			
		}
		List<Ims> processedItems = new ArrayList<>();
		for(Ims item : itemList){
			processedItems.add(FormatUtil.process(item));
		}
		return processedItems;
	}
	
    @Loggable(value = LogLevel.INFO)
	@Override
	public List<Ims> getItems(MultivaluedMap<String, String> queryParams){
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
		       throw new DatabaseOperationException("Error occured during getItems(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage(), hbe);	
		  	else
		  	   throw new DatabaseOperationException("Error occured during getItems(), due to: " +  hbe.getMessage(), hbe);
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DatabaseOperationException("Error occured during getItems(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage(), e);	
		  	else
		  	   throw new DatabaseOperationException("Error occured during getItems(), due to: " +  e.getMessage(), e);	
		}
		List<Ims> processedItems = new ArrayList<>();
		for(Ims ims : itemList){
			processedItems.add(FormatUtil.process(ims));
		}
		return processedItems;
	}
	
	@Loggable(value = LogLevel.INFO)
	@Override
	public List<ItemWrapper> getWrappedItems(MultivaluedMap<String, String> queryParams){
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
		       throw new DatabaseOperationException("Error occured during getWrappedItems, due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage(), hbe);	
		  	else
		  	   throw new DatabaseOperationException("Error occured during getWrappedItems, due to: " +  hbe.getMessage(), hbe);
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DatabaseOperationException("Error occured during getWrappedItems, due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage(), e);	
		  	else
		  	   throw new DatabaseOperationException("Error occured during getWrappedItems, due to: " +  e.getMessage(), e);	
		}
		List<ItemWrapper> productWrapperList = new ArrayList<ItemWrapper>(itemList.size());
		for(Ims ims : itemList){
			productWrapperList.add(new ItemWrapper(FormatUtil.process(ims)));
		}
		return productWrapperList;
	}
	
	@Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true)
	public List<Ims> getActiveAndShownOnWebsiteItems() {
    	return imsDao.getActiveAndShownOnWebsiteItems();
	}
	
	//--------------------------------Creation DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	public String createItem(JSONObject jsonObj) {  	
		JsonUtil.validateItemCode(jsonObj);
		String itemCode;
     	Ims itemFromInput = (Ims)JsonUtil.jsonObjectToPOJO(jsonObj, new Ims());
     	itemCode = createOrUpdateItem(itemFromInput, DBOperation.CREATE);
     	return itemCode;		 	
    }
	
	@Loggable(value = LogLevel.INFO)
	@Override
	public String createOrUpdateItem(Ims item, DBOperation operation){  	
		String id = "";
		item.setItemcode(item.getItemcode().toUpperCase());
		//take care of associations and components
		processNewFeature(item, operation);
		processColorHues(item, operation);
       	processApplications(item);
     	processIcons(item, operation); 
     	processPackgeUnits(item);
     	processVendor(item, operation);
     	if(DBOperation.CREATE.equals(operation) || DBOperation.CLONE.equals(operation)){ 
     	   try{
      	      ImsValidator.validateNewItem(item);
 		   }
 		   catch(Exception e){
 			  throw new InputParamException("Input valiation error: "+e.getMessage(), e);
 		   }
     	}   
     	try{
     	   id = imsDao.createItem(item);
		}
		catch(HibernateException hbe){
			if(hbe.getCause() != null)
			   System.out.println("Error occured during createOrUpdateItem(), due to: " +  hbe.getMessage() + ". Root cause -- " + hbe.getCause().getMessage());	
			else
			   System.out.println("Error occured during createOrUpdateItem(), due to: " +  hbe.getMessage());
			throw new DatabaseOperationException("Error occured during createOrUpdateItem(). ", hbe);
	    }	
   	    catch(Exception e){
		  e.printStackTrace();
		  if(e != null && e.getMessage() != null){
			  if(e.getMessage().contains("constraint [item_code]") || e.getMessage().contains("constraint [ims_code]") || e.getMessage().contains("constraint [ims_id]"))
				  throw new InputParamException("Invalid item code, since it is already existing in the database", e);
		      else if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			      throw new InputParamException("Invalid vendor number (ID), since it cannot be found in the vendor table", e);
		      else
		    	  throw new DatabaseOperationException("Error occured during createItem(), due to: " + e.getMessage(), e);
		  }
		  if(e.getCause() != null)
			 System.out.println("Error occured during createOrUpdateItem(), due to: " +  e.getMessage() + ". Root cause -- " + e.getCause().getMessage());	
		  else
			 System.out.println("Error occured during createOrUpdateItem(), due to: " +  e.getMessage());
		  throw new DatabaseOperationException("Error occured during createOrUpdateItem(). ", e);
	
      }
	  return id;	
   }
	
	//--------------------------------Update DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) 
	public synchronized void updateItem(JSONObject jsonObj){
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		Ims itemFromInput = (Ims)JsonUtil.jsonObjectToPOJO(jsonObj, new Ims());
		itemFromInput.setItemcode(itemFromInput.getItemcode().toUpperCase());
     	Ims itemToUpdate = null;
		Session session = getSession();	
		try{
			itemToUpdate = imsDao.getItemByItemCode(session, itemCode.trim().toUpperCase());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new DatabaseOperationException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DatabaseOperationException("Error occured during updateItem(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new DatabaseOperationException("Error occured during updateItem(), due to: " +  e.getMessage());	
		}
		if(itemToUpdate == null)
	       throw new DataNotFoundException("No data found for the given item code: " + itemCode);	
		itemToUpdate = ImsDataTransferUtil.transferItemInfo(itemToUpdate, itemFromInput, DBOperation.UPDATE);
		transferColorHues(itemToUpdate, itemFromInput, DBOperation.UPDATE);
  	    //ImsValidator.validateNewItem(itemToUpdate);
    	try{
	       imsDao.updateItem(session,itemToUpdate);
	 	}
    	catch(HibernateException hbe){
     	   if(hbe.getCause() != null)
 		      throw new DatabaseOperationException("Error occured during updateItem, due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
 		   else
 		  	  throw new DatabaseOperationException("Error occured during updateItem, due to: " +  hbe.getMessage());	
 	    }	
    	catch(Exception e){
		   if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			  throw new DatabaseOperationException("Invalid vendor number (ID), since it cannot be found in the vendor table");
		   if(e.getCause() != null)
		      throw new DatabaseOperationException("Error occured during updateItem(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		   else
			  throw new DatabaseOperationException("Error occured during updateItem(), due to: " +  e.getMessage());	
		}
	}
	
	//--------------------------------Deletion DB Operation --------------------------//
	@Loggable(value = LogLevel.INFO)
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	synchronized public void deleteItemByItemCode(String itemCode){
	    if(itemCode == null || itemCode.length() == 0)
	    	 throw new InputParamException("Item code should not be empty");		
		try{
			Session session = getSession();
			//Ims item = imsDao.loadItemByItemCode(session, itemCode);
			Ims itemProxy = imsDao.loadById(session, itemCode);
			imsDao.deleteItem(session, itemProxy);
		}
		catch(HibernateException hbe){
			if(hbe.getCause() != null)
			   System.out.println("Error occured during deleteItemByItemCode(), due to: " +  hbe.getMessage() + ". Root cause -- " + hbe.getCause().getMessage());	
			else
			   System.out.println("Error occured during deleteItemByItemCode(), due to: " +  hbe.getMessage());
			throw new DatabaseOperationException("Error occured during deleteItemByItemCode(). ", hbe);
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
			   System.out.println("Error occured during deleteItemByItemCode(), due to: " +  e.getMessage() + ". Root cause -- " + e.getCause().getMessage());	
			else
			   System.out.println("Error occured during deleteItemByItemCode(), due to: " +  e.getMessage());
			throw new DatabaseOperationException("Error occured during deleteItemByItemCode(). ", e);
		}
	}
	
	@Loggable(value = LogLevel.INFO)
	@Override
	synchronized public void deleteItem(JSONObject jsonObj){
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		deleteItemByItemCode(itemCode);
	}
	
	@Loggable(value = LogLevel.INFO)
	@Override
	synchronized public void deleteItem(Ims item){
		 if(item.getItemcode() == null || item.getItemcode().length() == 0)
	    	 throw new InputParamException("Item code should not be empty");
		imsDao.deleteItem(item);
	}

	//--------------------------------Deactivate Item DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) 
	public synchronized void deactivateItem(Ims itemFromInput){
		Ims itemToUpdate = null;
		Session session = getSession();
		try{
			itemToUpdate = imsDao.getItemByItemCode(session, itemFromInput.getItemcode().trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new DatabaseOperationException("Error occured during updateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DatabaseOperationException("Error occured during updateItem(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new DatabaseOperationException("Error occured during updateItem(), due to: " +  e.getMessage());	
		}
		if(itemToUpdate == null)
	       throw new DataNotFoundException("No data found for the given item code: " + itemFromInput.getItemcode());	 
		
		itemToUpdate.setInactivecode("Y");
  	    ImsValidator.validateNewItem(itemToUpdate);
    	try{
		    imsDao.updateItem(session,itemToUpdate);
	 	}
    	catch(HibernateException hbe){
     	      if(hbe.getCause() != null)
 		         throw new DatabaseOperationException("Error occured during updateItem, due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage(), hbe);	
 		      else
 		  	     throw new DatabaseOperationException("Error occured during updateItem, due to: " +  hbe.getMessage(), hbe);	
 	    }	
    	catch(Exception e){
			  if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			     throw new InputParamException("Invalid vendor number (ID), since it cannot be found in the vendor table", e);
			  if(e.getCause() != null)
		         throw new DatabaseOperationException("Error occured during updateItem(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage(), e);	
		  	  else
			     throw new DatabaseOperationException("Error occured during updateItem(), due to: " +  e.getMessage(), e);	
	    }	   
	}

	/************************* Keymark Vender DB Operation ************************/
	@Loggable(value = LogLevel.INFO)
	@Override
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public KeymarkVendor getKeymarkVendorByVendorNumber(Integer vendorId){
	     KeymarkVendor vendor = null;
	    	if(vendorId == null)
	    	   throw new InputParamException("Please enter a valid Item Code !");	
			try{
				Session session = getSession();
				session.setCacheMode(CacheMode.NORMAL);
		  	    vendor = keymarkVendorDao.getKeymarkVendorByVendorNumber(session, vendorId);
			}
			catch(HibernateException hbe){
				hbe.printStackTrace();
				if(hbe.getCause() != null)
			  	   throw new DatabaseOperationException("Error occured during getKeymarkVendorByVendorNumber, due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage(), hbe);	
			  	else
			  	   throw new DatabaseOperationException("Error occured during getKeymarkVendorByVendorNumber, due to: " +  hbe.getMessage());	
			}
			catch(RuntimeException e){
				if(e.getCause() != null)
			  	   throw new DatabaseOperationException("Error occured during getKeymarkVendorByVendorNumber, due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage(), e);	
			  	else
			  	   throw new DatabaseOperationException("Error occured during getKeymarkVendorByVendorNumber, due to: " +  e.getMessage(), e);	
			}
			return vendor;
	}
	
	/************************* Util Methods ************************/
	
	private synchronized Session getSession(){
		try {
		    return sessionFactory.getCurrentSession();
		}
		catch(Exception e) {
		   return sessionFactory.openSession();		
		}
	}

	public List<Vendor> getNewVendorSystem(){
		Ims item = new Ims();
		item.initVendors(3);
		return item.getNewVendorSystem();
	}

	private void processNewFeature(Ims item, DBOperation dBOperation){
	   ImsNewFeature newFeature = item.getNewFeature();
 	   if(newFeature != null && !newFeature.isEmpty()){
 		  if(dBOperation.equals(DBOperation.CLONE))
 		     newFeature.setItemCode(null); //remove original item code which is the new item object cloned from from this newFeature object
 		  if(dBOperation.equals(DBOperation.CREATE) || dBOperation.equals(DBOperation.CLONE))
 			 newFeature.setCreatedDate(new Date());
 	      else if(dBOperation.equals(DBOperation.UPDATE))
		        newFeature.setLastModifiedDate(new Date());
		  item.addNewFeature(newFeature);
 	   }   
       else
 	      item.setNewFeature(null);
    }

    private void processColorHues(Ims item, DBOperation dBOperation){
	   List<ColorHue> colorhues = item.getColorhues();
	   if(dBOperation.equals(DBOperation.CLONE) || dBOperation.equals(DBOperation.UPDATE)){
	      if(!ImsDataUtil.colorHuesAndColorsEquals(item.getColorhues(), item.getColors())){
		     if(dBOperation.equals(DBOperation.UPDATE)){ //For update, if colorhue changed, we need to remove old ones
		       //item.setColorhues(null);
		       //item.getColorhues().clear();
		       for(ColorHue colorHue: colorhues){
		    	  //if(!item.getColors().contains(colorHue.getColorHue())){
		    		  //colorhues.remove(colorHue);
		    		  colorHue.setItem(null);
		    		  colorHueDao.deleteColorHue(colorHue, true);
		    	  //}   
		       }
		    }
		    if(item.getColors() != null){ 
		       item.setColorhues(null);
		       colorhues = ImsDataUtil.convertColorListToColorHueObjects(item.getColors()); //For both clone and update, if colorhue changed, we need to get new values for colorhues
		    }   
		 }	
		 else 
		    return; // no colorhue changed for update/clone operation, therefore, no need to make change	
	  }
  	  if(colorhues != null && !colorhues.isEmpty()){
 	     item.setColorhues(null);
 	     for(ColorHue colorhue : colorhues){
 		    item.addColorhue(colorhue);
 	     }
 	     item.setColorcategory(ImsDataUtil.convertColorHuesToColorCategory(colorhues));
 	  }
   }

   private void processApplications(Ims item){
	  List<String> usage = item.getUsage();
      if(usage != null){
 		 item.setApplications(ImsDataUtil.convertUsageToApplications(usage));
 	   }   
    }

    private Ims processPackgeUnits(Ims item){
	   Units units = item.getUnits();
 	   if(units != null){
 	      units.setOrdunit(ImsDataUtil.getStandardOrderUnit(item));
 	      units.setOrdratio(ImsDataUtil.getBaseToOrderRatio(item));
 	      units.setStdunit(ImsDataUtil.getStandardSellUnit(item));
 	      units.setStdratio(ImsDataUtil.getBaseToSellRatio(item));
  	      item.setUnits(units);
 	   }
	   return item;
   } 	
    
   private void processVendor(Ims item, DBOperation dBOperation){
	  List<Vendor> vendors = item.getNewVendorSystem();
 	  item.setNewVendorSystem(null);
  	  if(vendors != null && !vendors.isEmpty()){
 		 VendorInfo lagancyVendor = new VendorInfo(); 
 		 for(Vendor vendor : vendors){
 			if(vendor.getId() != null){ //populated legacy vendor fields
 			   //if(vendor.getId().equals(item.getVendors().getVendornbr1()) && (DBOperation.UPDATE.equals(dBOperation) || DBOperation.CLONE.equals(dBOperation)))	
 			   vendor = ImsDataUtil.setCalculatedVendorData(item, vendor);
 			   item.addNewVendorSystem(vendor);
       		   if(vendor.getVendorOrder() == 1)
       			  lagancyVendor = ImsDataUtil.convertNewVendorToLegancyVendorInfo(vendor); 
       		   else if(vendor.getVendorOrder() == 2)
       			  lagancyVendor.setVendornbr2(vendor.getId()); 
 			}  
  		}	
       	item.setVendors(lagancyVendor);  
   	  }
   } 	

   private void processIcons(Ims item, DBOperation dBOperation){
	   IconCollection icons = item.getIconDescription();
       if(icons != null && !icons.isEmpty()){
 	      if(dBOperation.equals(DBOperation.CLONE))
 		     icons.setItemCode(null); //remove original item code from icons object
 	      item.addIconDescription(icons);	
 	      item.setIconsystem(ImsDataUtil.convertIconCollectionToLegancyIcons(icons));
 	   }   
 	   else
 	      item.setIconDescription(null);	
    }

   public boolean validateVendorId(Integer vendorId){
	   List<Integer> keymarkVendorIdList = null;
	   keymarkVendorIdList = keymarkVendorDao.getKeymarkVendorIdList();
	   for(Integer id : keymarkVendorIdList){
		   id = Integer.valueOf(String.valueOf(id).trim());
		   if(id.equals(vendorId))
		      return true;
	   }
	   return false;
   }
   
   private synchronized void transferColorHues(Ims itemToDB, Ims itemFromInput, DBOperation operation) {
	   List<ColorHue> inputColorHues = itemFromInput.getColorhues();
	   //if colorhues is not available in input data, then obtain it from colorCategory in input data
	   if((inputColorHues == null || inputColorHues.isEmpty()) && itemFromInput.getColorcategory() != null && !itemFromInput.getColorcategory().isEmpty()){
		  inputColorHues = ImsDataUtil.convertColorCategoryToColorHueObjects(itemFromInput.getColorcategory());
	   }
	   if(inputColorHues != null && !inputColorHues.isEmpty()){
		  /*** create new colorHue ***/ 
	 	 if(operation.equals(DBOperation.CREATE) || //Brand new item
		   (operation.equals(DBOperation.UPDATE) && (itemToDB.getColorhues() == null || itemToDB.getColorhues().isEmpty()))){ //existing item, but brand new ColorHue
		    for(ColorHue color : inputColorHues){	
 		        if(color != null && color.getColorHue() != null && !color.getColorHue().isEmpty())
		           itemToDB.addColorhue(color);	
	        }
		  }
	 	  /*** update existing colorHue ***/
		  else if(operation.equals(DBOperation.UPDATE)){
			  int inputColorHueSize = inputColorHues.size();
			  int existingColorHueSize = itemToDB.getColorhues().size();
			  List<ColorHue> cl = itemToDB.getColorhues();
			  if(inputColorHueSize < itemToDB.getColorhues().size()) {
				 for(int i=inputColorHueSize;  i< existingColorHueSize; i++){
			      	 ColorHue colorHue = cl.get(i);
			         colorHue.setItem(null);
		    	     colorHueDao.deleteColorHue(colorHue, true);
		    	 }   
		       }
			   for(int i = 0; i < inputColorHues.size(); i++){
			       ColorHue colorHue = inputColorHues.get(i);
			       if(i >= itemToDB.getColorhues().size())
				      itemToDB.getColorhues().add(i, new ColorHue(itemToDB.getItemcode(), itemToDB)); 
			       if(colorHue.getColorHue() != null) 
				      itemToDB.getColorhues().get(i).setColorHue(colorHue.getColorHue());
		       }	   
		  }
	  }
	}
}

