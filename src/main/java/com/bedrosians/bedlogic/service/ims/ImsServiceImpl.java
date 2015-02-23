package com.bedrosians.bedlogic.service.ims;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
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
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.domain.ims.embeddable.Units;
import com.bedrosians.bedlogic.domain.ims.embeddable.VendorInfo;
import com.bedrosians.bedlogic.exception.DataNotFoundException;
import com.bedrosians.bedlogic.exception.DatabaseOperationException;
import com.bedrosians.bedlogic.exception.InputParamException;
import com.bedrosians.bedlogic.exception.UnauthorizedException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.JsonUtil;
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
	public Ims getItem(String itemCode){
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
		if(item == null)
		       throw new DataNotFoundException("No data found for the given item code: " + itemCode);	
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
	public List<?> getItems(MultivaluedMap<String, String> queryParams, boolean wrappedData){
		if(queryParams == null || queryParams.isEmpty()){
			throw new InputParamException("Query criteria is empty");
		}
		List<Ims> itemList = null;
		try{
			itemList = imsDao.getItemsByQueryParameters(queryParams);
		}
		catch(HibernateException hbe){
			if(hbe.getCause() != null) {
			   if(hbe.getCause().getMessage() != null && hbe.getCause().getMessage().contains("permission denied for relation"))	
			      throw new UnauthorizedException("Permission denied error occured during getItems(). Cause: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());  
			   else	
			      throw new DatabaseOperationException("Error occured during getItems(). Cause: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage(), hbe);	
			}
			else
		  	   throw new DatabaseOperationException("Error occured during getItems(). Cause: " +  hbe.getMessage(), hbe);
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DatabaseOperationException("Error occured during getItems(). Cause: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage(), e);	
		  	else
		  	   throw new DatabaseOperationException("Error occured during getItems(). Cause: " +  e.getMessage(), e);	
		}
		List<Object> list = new ArrayList<Object>(itemList.size());
		for(Ims item : itemList){
		    //if(wrappedData) 
		    //	list.add(new ItemWrapper(FormatUtil.process(item)));	
		    //else 
		    	list.add(FormatUtil.process(item));				
		}
		return list;
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
	public String createItem(String jsonString) {  	
        return createOrUpdateItem(JsonUtil.jsonStringToPOJO(jsonString), DBOperation.CREATE);
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
     	if(DBOperation.CREATE.equals(operation) || DBOperation.CLONE.equals(operation))
   	       ImsValidator.validateNewItem(item);
 	  	try{
     	   id = imsDao.createItem(item);
		}
		catch(HibernateException hbe){
			if(hbe.getCause() != null) {
			   if(hbe.getCause().getMessage() != null && hbe.getCause().getMessage().contains("permission denied for relation"))	
		    	  throw new UnauthorizedException("Permission denied error occured during createOrUpdateItem(). Cause: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());  
		       else	
			  	  throw new DatabaseOperationException("Error occured during createOrUpdateItem(). Cause:  " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
			}
			else
				throw new DatabaseOperationException("Error occured during createOrUpdateItem(). Cause:  " +  hbe.getMessage());
	    }	
   	    catch(Exception e){
	       if(e != null && e.getMessage() != null){
			  if(e.getMessage().contains("constraint [item_code]") || e.getMessage().contains("constraint [ims_code]") || e.getMessage().contains("constraint [ims_id]"))
				  throw new InputParamException("Invalid item code, since it is already existing in the database", e);
		      else if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			      throw new InputParamException("Invalid vendor number (ID), since it cannot be found in the vendor table", e);
		      else
		    	  throw new DatabaseOperationException("Error occured during createItem(). Cause: " + e.getMessage(), e);
		  }
		  if(e.getCause() != null)
			  throw new DatabaseOperationException("Error occured during createOrUpdateItem(). Cause: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  else
			  throw new DatabaseOperationException("Error occured during createOrUpdateItem(). Cause: " +  e.getMessage());
      }
	  return id;	
   }
	
	//--------------------------------Update DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	public Ims updateItem(String jsonString) {  	
        return updateItem(JsonUtil.jsonStringToPOJO(jsonString));
    }
	
	@Loggable(value = LogLevel.INFO)
	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ) 
	public synchronized Ims updateItem(Ims itemFromInput){
		itemFromInput.setItemcode(itemFromInput.getItemcode().toUpperCase());
     	Ims itemToUpdate = null;
		Session session = getSession();	
		try{
			itemToUpdate = imsDao.getItemByItemCode(session, itemFromInput.getItemcode().trim().toUpperCase());
		}
	    catch(HibernateException hbe){
	     	if(hbe.getCause() != null) {
	    	  if(hbe.getCause().getMessage() != null && hbe.getCause().getMessage().contains("permission denied for relation"))	
	    		  throw new UnauthorizedException("Permission denied error occured during updateItem(). Cause: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());  
	    	  else
	    		  throw new DatabaseOperationException("Error occured during updateItem(). Cause: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
	    	}
	    	else
				throw new DatabaseOperationException("Error occured during updateItem(). Cause : " +  hbe.getMessage());

	    }
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DatabaseOperationException("Error occured during updateItem(). Cause :  " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
		  	else
		  	   throw new DatabaseOperationException("Error occured during updateItem(). Cause : " +  e.getMessage());	
		}
		if(itemToUpdate == null)
	       throw new DataNotFoundException("No data found for the given item code: " + itemFromInput.getItemcode());	
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
    	return itemToUpdate;
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
		catch(ObjectNotFoundException e){
			if(e.getMessage().contains("No row with the given identifier exists: [com.bedrosians.bedlogic.domain.ims.KeymarkVendor#0]")){
			   //swallow it, because the vendor number in ims table was not entered
			} 
			else {
			   if(e.getCause() != null)
				  throw new DataNotFoundException("Error occured during deleteItemByItemCode(), due to: " +  e.getMessage() + ". Root cause -- " + e.getCause().getMessage());	
			   else
				  throw new DataNotFoundException("Error occured during deleteItemByItemCode(), due to: " +  e.getMessage());
			}	   
		}
		catch(HibernateException hbe){
			if(hbe.getCause() != null) {
				if(hbe.getCause().getMessage() != null && hbe.getCause().getMessage().contains("permission denied for relation"))	
		    		  throw new UnauthorizedException("Permission denied error occured during deleteItemByItemCode(). Cause: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());  
		    	  else
			
				throw new DatabaseOperationException("Error occured during deleteItemByItemCode(). Cause:  " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage());	
			}
			else
				throw new DatabaseOperationException("Error occured during deleteItemByItemCode(). Cause:  " +  hbe.getMessage());
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
				throw new DatabaseOperationException("Error occured during deleteItemByItemCode(). Cause:  " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage());	
			else
				throw new DatabaseOperationException("Error occured during deleteItemByItemCode(). Cause:  " +  e.getMessage());
		}
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
	   if(dBOperation.equals(DBOperation.CLONE) || dBOperation.equals(DBOperation.UPDATE)){ //this section may be seldom used, because this method is only called by "create item" currently 
	      if(item.getColors() != null && !ImsDataUtil.colorHuesAndColorsEquals(item.getColorhues(), item.getColors())){
		     //For clone or update, if colorhue changed, we need to remove extra ones to let they have the same number of records and then to update color hues
		     int newColorHueSize = item.getColors().size();
			 int existingColorHueSize = item.getColorhues().size();
		     //when the number of existing color hues > that of the new color hues, we need to remove the extra number of color hues 
			 if(item.getColors().size() < item.getColorhues().size()) {
				for(int i=existingColorHueSize-1;  i>=newColorHueSize; i--){
				    ColorHue colorHue = item.getColorhues().get(i);
				    item.getColorhues().remove(i);
				    colorHueDao.deleteColorHue(colorHue, true);
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
 			if(vendor.getVendorId().getId() != null){ //populated legacy vendor fields
 			   //if(vendor.getId().equals(item.getVendors().getVendornbr1()) && (DBOperation.UPDATE.equals(dBOperation) || DBOperation.CLONE.equals(dBOperation)))	
 			   vendor = ImsDataUtil.setCalculatedVendorData(item, vendor);
 			   item.addNewVendorSystem(vendor);
       		   if(vendor.getVendorOrder() == null || vendor.getVendorOrder() == 1)
       			  lagancyVendor = ImsDataUtil.convertNewVendorToLegancyVendorInfo(vendor); 
       		   else if(vendor.getVendorOrder() == 2)
       			  lagancyVendor.setVendornbr2(vendor.getVendorId().getId()); 
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
	   if((inputColorHues == null || inputColorHues.isEmpty()) && itemFromInput.getColorcategory() != null && !(itemFromInput.getColorcategory().isEmpty())){
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
			  //when the number of existing color hues > that of the new color hues, we need to remove the extra number of color hues 
			  if(inputColorHueSize < existingColorHueSize) {
				 for(int i=existingColorHueSize-1;  i>=inputColorHueSize; i--){
			      	 ColorHue colorHue = cl.get(i);
			      	 itemToDB.getColorhues().remove(i);
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
	  if(itemToDB.getColorhues() != null && !itemToDB.getColorhues().isEmpty())
		 itemToDB.setColorcategory(ImsDataUtil.convertColorHuesToColorCategory(itemToDB.getColorhues()));
	}
   
   private static synchronized void transferNewVendorSystem(Ims itemToDB, Ims itemFromInput, DBOperation operation) {
   	List<Vendor> inputItemVendors = itemFromInput.getNewVendorSystem();
		VendorInfo legancyVendorInfo = itemFromInput.getVendors();
		/****** newVendorSystem data is included in input ******/
 		if(inputItemVendors != null && !inputItemVendors.isEmpty()){
 		   //// create new vendor record /////	
 		   if(operation.equals(DBOperation.CREATE)|| //brand new item
 		     (operation.equals(DBOperation.UPDATE) && (itemToDB.getNewVendorSystem() == null || itemToDB.getNewVendorSystem().isEmpty()))){ //existing item, but brand new ItemVendors
 			  for(Vendor vendor : inputItemVendors){
 			      if(vendor != null && !vendor.isEmpty()){
 			    	 vendor = ImsDataUtil.setCalculatedVendorData(itemFromInput, vendor); 
 				     itemToDB.addNewVendorSystem(vendor);	
 				     //Populate vendor info in ims
 				     if((legancyVendorInfo == null || legancyVendorInfo.getVendornbr1() == null || legancyVendorInfo.getVendornbr1() == 0) && (vendor.getVendorOrder() != null && vendor.getVendorOrder() == 1)){
 				         itemToDB.setVendors(ImsDataUtil.convertNewVendorToLegancyVendorInfo(vendor));	
 				     }
 				  }   
 			   }    
 		   }
 		   ///// update existing item vendor record /////
          else if(operation.equals(DBOperation.UPDATE)){ //update existing item vendor
     		   int sizeOfItemVendors = itemToDB.getNewVendorSystem().size();
 			   for(int i = 0; i < inputItemVendors.size(); i++){
 				   Vendor vendor = inputItemVendors.get(i);
 				   ImsDataUtil.setCalculatedVendorData(itemFromInput, vendor);
 				   //if((vendor.getVendorOrder() != null && vendor.getVendorOrder() == 1) && (vendor.getVendorId() == null || vendor.getVendorId().getId() == null || vendor.getVendorId().getId() == 0))
 				   //  throw new InputParamException("Error occured during transferNewVendorSystem(): No vendor ID is provided."); //commented out, due to the bad existing vendor data in ims table 
 				   //Populate vendor info in ims table
 				   if((legancyVendorInfo == null || legancyVendorInfo.getVendornbr1() == null || legancyVendorInfo.getVendornbr1() == 0) && (vendor.getVendorOrder() != null && vendor.getVendorOrder() == 1))
 				       itemToDB.setVendors(ImsDataUtil.convertNewVendorToLegancyVendorInfo(vendor));				 
 				   if(sizeOfItemVendors <= i){
 					  itemToDB.addNewVendorSystem(vendor); //there more vendor in the new item than the current one
 					  continue;
 				   }	  
 				   if(itemToDB.getNewVendorSystem().get(i).getVendorId() == null)
 					   itemToDB.getNewVendorSystem().get(i).setVendorId(vendor.getVendorId());
 				   if(vendor.getDutyPct() != null) itemToDB.getNewVendorSystem().get(i).setDutyPct(vendor.getDutyPct());
 				   if(vendor.getLeadTime() != null) itemToDB.getNewVendorSystem().get(i).setLeadTime(vendor.getLeadTime());
 				   if(vendor.getVendorDiscountPct() != null) itemToDB.getNewVendorSystem().get(i).setVendorDiscountPct(vendor.getVendorDiscountPct());
 				   if(vendor.getVendorFob() != null) itemToDB.getNewVendorSystem().get(i).setVendorFob(vendor.getVendorFob().trim());
 				   if(vendor.getVendorFreightRateCwt() != null)itemToDB.getNewVendorSystem().get(i).setVendorFreightRateCwt(vendor.getVendorFreightRateCwt());
 				   if(vendor.getVendorLandedBaseCost() != null) itemToDB.getNewVendorSystem().get(i).setVendorLandedBaseCost(vendor.getVendorLandedBaseCost());
 				   if(vendor.getVendorListPrice() != null) itemToDB.getNewVendorSystem().get(i).setVendorListPrice(vendor.getVendorListPrice());
 				   if(vendor.getVendorMarkupPct() != null) itemToDB.getNewVendorSystem().get(i).setVendorMarkupPct(vendor.getVendorMarkupPct());
 				   if(vendor.getVendorName() != null) itemToDB.getNewVendorSystem().get(i).setVendorName(vendor.getVendorName().trim());
 				   if(vendor.getVendorName2() != null) itemToDB.getNewVendorSystem().get(i).setVendorName2(vendor.getVendorName2().trim());
 				   if(vendor.getVendorNetPrice() != null) itemToDB.getNewVendorSystem().get(i).setVendorNetPrice(vendor.getVendorNetPrice());
 				   if(vendor.getVendorOrder() != null) itemToDB.getNewVendorSystem().get(i).setVendorOrder(vendor.getVendorOrder());
 				   if(vendor.getVendorPriceRoundAccuracy() != null) itemToDB.getNewVendorSystem().get(i).setVendorPriceRoundAccuracy(vendor.getVendorPriceRoundAccuracy());
 				   if(vendor.getVendorPriceUnit() != null) itemToDB.getNewVendorSystem().get(i).setVendorPriceUnit(vendor.getVendorPriceUnit());
 				   if(vendor.getVendorXrefId() != null) itemToDB.getNewVendorSystem().get(i).setVendorXrefId(vendor.getVendorXrefId());
 				   //Populate vendor info in ims
			       if((legancyVendorInfo == null || legancyVendorInfo.getVendornbr1() == null || legancyVendorInfo.getVendornbr1() == 0) && vendor.getVendorOrder() == 1){
				       itemToDB.setVendors(ImsDataUtil.convertNewVendorToLegancyVendorInfo(vendor));	
				   }
 		        }
 		   }	   
 		}
 		/**** No newVendorSystem data is included in input. Populate Ims_Item_Vendor table with the ims vendors info ****/ 
 		else if((legancyVendorInfo != null && legancyVendorInfo.getVendornbr1() != null) && (inputItemVendors == null || inputItemVendors.isEmpty())){
 			//ProductVendor vendor = convertImslegancyVendorInfoToItemVendor(legancyVendorInfo);
 			if(itemToDB.getNewVendorSystem() == null || itemToDB.getNewVendorSystem().isEmpty())
 			   itemToDB.addNewVendorSystem(new Vendor());
 			if(legancyVendorInfo.getVendornbr1() != null) itemToDB.getNewVendorSystem().get(0).getVendorId().setId(legancyVendorInfo.getVendornbr1());
 			if(legancyVendorInfo.getVendorxrefcd() != null) itemToDB.getNewVendorSystem().get(0).setVendorXrefId(legancyVendorInfo.getVendorxrefcd());
 			if(legancyVendorInfo.getVendorfob() != null) itemToDB.getNewVendorSystem().get(0).setVendorFob(legancyVendorInfo.getVendorfob());
 			if(legancyVendorInfo.getDutypct() != null) itemToDB.getNewVendorSystem().get(0).setDutyPct(legancyVendorInfo.getDutypct());
 			if(legancyVendorInfo.getVendorlistprice() != null) itemToDB.getNewVendorSystem().get(0).setLeadTime(legancyVendorInfo.getLeadtime());
 			if(legancyVendorInfo.getVendorlistprice() != null) itemToDB.getNewVendorSystem().get(0).setVendorListPrice(legancyVendorInfo.getVendorlistprice());
 			if(legancyVendorInfo.getVendorpriceunit() != null) itemToDB.getNewVendorSystem().get(0).setVendorPriceUnit(legancyVendorInfo.getVendorpriceunit());
 			if(legancyVendorInfo.getVendordiscpct() != null) itemToDB.getNewVendorSystem().get(0).setVendorDiscountPct(legancyVendorInfo.getVendordiscpct());
 		    if(legancyVendorInfo.getVendorroundaccuracy() != null) itemToDB.getNewVendorSystem().get(0).setVendorPriceRoundAccuracy(legancyVendorInfo.getVendorroundaccuracy());
 		    if(legancyVendorInfo.getVendormarkuppct() != null) itemToDB.getNewVendorSystem().get(0).setVendorMarkupPct(legancyVendorInfo.getVendormarkuppct());
 		    if(legancyVendorInfo.getVendorfreightratecwt() != null) itemToDB.getNewVendorSystem().get(0).setVendorFreightRateCwt(legancyVendorInfo.getVendorfreightratecwt());
 		    itemToDB.getNewVendorSystem().get(0).setVendorOrder(1);
 		    ImsDataUtil.setCalculatedVendorData(itemFromInput, legancyVendorInfo);
 			//vendor.setVendorOrder(1);
 			//itemToDB.addNewVendorSystem(vendor);
 		}
   }	
}

