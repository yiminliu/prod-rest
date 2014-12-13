package com.bedrosians.bedlogic.service.mvc.ims;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


















import java.util.concurrent.CopyOnWriteArrayList;

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
import com.bedrosians.bedlogic.domain.ims.embeddable.Units;
import com.bedrosians.bedlogic.domain.ims.embeddable.VendorInfo;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.DataNotFoundException;
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
    	Ims item = null;
    	if(itemCode == null || itemCode.length() < 1)
    	   throw new InputParamException("Please enter valid Item Code !");	
		try{
			Session session = getSession();
			session.setCacheMode(CacheMode.NORMAL);
	  	    item = imsDao.getItemByItemCode(session, itemCode.toUpperCase());
	  	    //session.refresh(ims);
	  	   // if(item.getColorhues() == null || item.getColorhues().isEmpty())
	  	    //   item.setColorhues(ImsDataUtil.convertColorCategoryToColorHueObjects(item.getColorcategory()));	
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
		return FormatUtil.process(item);
	}
    
    @Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public Ims getItemByItemCodeForClone(String itemCode){
    	Ims item = null;
    	if(itemCode == null || itemCode.length() < 1)
    	   throw new InputParamException("Please enter valid Item Code!");	
		try{
			Session session = getSession();
	  	    item = imsDao.getItemByItemCode(session, itemCode.toUpperCase());
	  	    if(item == null)
	  	       throw new DataNotFoundException();
	  	    session.evict(item);
	  	    session.clear();
		}
		catch(DataNotFoundException dnfe){
			throw dnfe;
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
		return FormatUtil.process(item);
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
		for(Ims item : itemList){
			if(item.getColorhues() == null || item.getColorhues().isEmpty())
		  	   item.setColorhues(ImsDataUtil.convertColorCategoryToColorHueObjects(item.getColorcategory()));	
			processedItems.add(FormatUtil.process(item));
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
		for(Ims item : itemList){
			//if(item.getColorhues() == null || item.getColorhues().isEmpty())
		  	//   item.setColorhues(ImsDataUtil.convertColorCategoryToColorHueObjects(item.getColorcategory()));	
			processedItems.add(FormatUtil.process(item));
		}
		return processedItems;
	}
	
    
	//--------------------------------Creation DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	public String createItem(Ims item, DBOperation createOrClone){  	
		String id = "";
		//take care of associations and components
		processNewFeature(item, createOrClone);
		processColorHues(item);
       	processApplications(item);
     	processIcons(item, createOrClone); 
     	processPackgeUnits(item);
     	processVendor(item);
     	try{
      	   ImsValidator.validateNewItem(item);
 		}
 		catch(Exception e){
 			throw new InputParamException("Input valiation error: "+e.getMessage(), e);
 		}
     	try{
		   id = imsDao.createItem(item);
		}
		catch(HibernateException hbe){
		   hbe.printStackTrace();
		   if(hbe.getCause() != null)
		      throw new DataOperationException("Error occured during createItem(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage(), hbe);	
		   else
		  	  throw new DataOperationException("Error occured during createItem(), due to: " +  hbe.getMessage(), hbe);	
	    }	
   	    catch(Exception e){
		  e.printStackTrace();
		  if(e != null && e.getMessage() != null){
			  if(e.getMessage().contains("constraint [item_code]") || e.getMessage().contains("constraint [ims_code]"))
				  throw new InputParamException("Invalid item code, since it is already existing in the database", e);
		      else if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			      throw new InputParamException("Invalid vendor number (ID), since it cannot be found in the vendor table", e);
		      else
		    	  throw new DataOperationException("Error occured during createItem(), due to: " + e.getMessage(), e);
		  }
		  else if(e.getCause() != null)
	  	     throw new DataOperationException("Error occured during createItem(), due to: " +  " Root cause: " + e.getCause().getMessage(), e);	
	  	  else
	  	     throw new DataOperationException("Error occured during createItem().", e);	
      }
	  return id;		 	
    }
	
	@Loggable(value = LogLevel.INFO)
	@Override
	public String cloneItem(Ims item){  	
		String id = "";
		//take care of associations
     	ImsNewFeature newFeature = item.getNewFeature();
     	if(newFeature != null && !newFeature.isEmpty()){
           item.setNewFeature(null);	
           newFeature.setItemCode(null);
     	   item.addNewFeature(newFeature);
     	}
     	else
     	   item.setNewFeature(null);
     	List<ColorHue> colorhues = item.getColorhues();
     	if(colorhues != null && !colorhues.isEmpty()){
     	   item.setColorhues(null);
     	   for(ColorHue colorhue : colorhues){
     		   item.addColorhue(colorhue);
     	   }
     	   item.setColorcategory(ImsDataUtil.convertColorHuesToColorCategory(colorhues));
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
     	item = processPackgeUnits(item);
     	try{
      	   ImsValidator.validateNewItem(item);
 		}
 		catch(Exception e){
 			throw new InputParamException("Input valiation error: "+e.getMessage(), e);
 		}
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
			  if(e.getMessage().contains("constraint [item_code]") || e.getMessage().contains("constraint [ims_code]"))
				  throw new InputParamException("Invalid item code, since it is already existing in the database", e);
		      else if(e.getMessage().contains("constraint [vendor_apv_fkey]"))
			      throw new InputParamException("Invalid vendor number (ID), since it cannot be found in the vendor table", e);
		      else
		    	  throw new DataOperationException("Error occured during createItem(), due to: " + e.getMessage(), e);
		  }
		  else if(e.getCause() != null)
	  	     throw new DataOperationException("Error occured during createItem(), due to: " +  " Root cause: " + e.getCause().getMessage(), e);	
	  	  else
	  	     throw new DataOperationException("Error occured during createItem().", e);	
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
		  	   throw new DataOperationException("Error occured during updateItem(), due to: " +  e.getMessage(), e);	
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
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	synchronized public void deleteItemByItemCode(String itemCode){
	    if(itemCode == null || itemCode.length() == 0)
	    	 throw new InputParamException("Item code should not be empty");		
		try{
			Ims ims = imsDao.loadItemByItemCode(getSession(), itemCode);
			imsDao.deleteItem(ims);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			if(hbe.getCause() != null)
		       throw new DataOperationException("Error occured during deleteItemByItemCode(), due to: " +  hbe.getMessage() + ". Root cause: " + hbe.getCause().getMessage(), hbe);	
		  	else
		  	   throw new DataOperationException("Error occured during deleteItemByItemCode(), due to: " +  hbe.getMessage(), hbe);
		
		}
		catch(RuntimeException e){
			if(e.getCause() != null)
		  	   throw new DataOperationException("Error occured during deleteItemByItemCode(), due to: " +  e.getMessage() + ". Root cause: " + e.getCause().getMessage(), e);	
		  	else
		  	   throw new DataOperationException("Error occured during deleteItemByItemCode(), due to: " +  e.getMessage(), e);	
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
	
	private void processNewFeature(Ims item, DBOperation dBOperation){
		ImsNewFeature newFeature = item.getNewFeature();
     	if(newFeature != null && !newFeature.isEmpty()){
     		if(dBOperation.equals(DBOperation.CLONE))
     		   newFeature.setItemCode(null); //remove original item code which is the new item object cloned from from this newFeature object
     		newFeature.setCreatedDate(new Date());
     	    item.addNewFeature(newFeature);
     	}   
     	else
     	   item.setNewFeature(null);
	}
	
	private void processColorHues(Ims item){
		List<ColorHue> colorhues = item.getColorhues();
     	if(colorhues != null && !colorhues.isEmpty()){
     	   item.setColorhues(null);
     	   for(ColorHue colorhue : colorhues){
     		   item.addColorhue(colorhue);
     	   }
     	   item.setColorcategory(ImsDataUtil.convertColorHuesToColorCategory(colorhues));
     	}
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
	//NEW.vendorlandedbasecost := (NEW.vendornetprice * (100.00 + NEW.vendormarkuppct) / 100.00 / tUnitRatio) + (NEW.vendorfreightratecwt * NEW.basewgtperunit / 100);
	private Ims processVendor(Ims item){
		List<Vendor> vendors = item.getNewVendorSystem();
     	item.setNewVendorSystem(null);
     	if(vendors != null && !vendors.isEmpty()){
     		VendorInfo lagancyVendor = new VendorInfo(); 
     		for(Vendor vendor : vendors){
     			if(vendor.getId() != null){ //populated legacy vendor fields
           		   item.addNewVendorSystem(vendor);
           		   if(vendor.getVendorOrder() == 1)
           			  lagancyVendor = ImsDataUtil.convertNewVendorToLegancyVendorInfo(vendor); 
           		   else if(vendor.getVendorOrder() == 2)
           			  lagancyVendor.setVendornbr2(vendor.getId()); 
     			}  
     			if(vendor.getVendorListPrice() != null){ //calculate net price
    				if(vendor.getVendorDiscountPct() != null){
    			       BigDecimal netPrice = new BigDecimal(vendor.getVendorListPrice().floatValue() * ((100 - vendor.getVendorDiscountPct())/100.00));
    			       vendor.setVendorNetPrice(netPrice);
    			       if(item.getUnits() != null && item.getUnits().getStdratio() != null && item.getUnits().getBasewgtperunit() != null){
    			          BigDecimal landedBaseCost = new BigDecimal(netPrice.floatValue() * 
    			    		                                         ((100 + vendor.getVendorMarkupPct())/100.00/item.getUnits().getStdratio()) + 
    			    		                                         vendor.getVendorFreightRateCwt() *
    			    		                                         item.getUnits().getBasewgtperunit().floatValue()/100.00);
    			          vendor.setVendorLandedBaseCost(landedBaseCost);
    			       }   
    				}   
    				else 
    				   vendor.setVendorNetPrice(vendor.getVendorListPrice());
    			} //NEW.vendornetprice := NEW.vendorlistprice * (100.00 - NEW.vendordiscpct1) * (100.00 - NEW.vendordiscpct2) * (100.00 - NEW.vendordiscpct3) / 1000000;
     			  //NEW.vendornetprice := round(NEW.vendornetprice, CAST (NEW.vendorroundaccuracy AS INTEGER));
     		}	
           	item.setVendors(lagancyVendor);  
       	}
     	
		/*List<Vendor> vendors = item.getNewVendorSystem();
		for(Vendor vendor : vendors){
			if(vendor.getVendorListPrice() != null){
				if(vendor.getVendorDiscountPct() != null){
			       BigDecimal netPrice = new BigDecimal(vendor.getVendorListPrice().floatValue() * ((100 - vendor.getVendorDiscountPct())/100));
			       vendor.setVendorNetPrice(netPrice);
				}   
				else 
					vendor.setVendorNetPrice(vendor.getVendorListPrice());
			}	
			  //BigDecimal netPrice = new BigDecimal(vendor.getVendorListPrice().floatValue() * vendor.getVendorFreightRateCwt());
			  //vendor.setVendorNetPrice(netPrice);
			//vendors.add(vendor);
		}*/
     
    	return item;
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
