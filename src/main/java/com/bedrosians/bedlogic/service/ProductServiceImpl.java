package com.bedrosians.bedlogic.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.item.ImsNewFeatureDao;
import com.bedrosians.bedlogic.dao.item.ItemDao;
import com.bedrosians.bedlogic.dao.item.VendorDao;
import com.bedrosians.bedlogic.domain.item.Icon;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.Note;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;
import com.bedrosians.bedlogic.util.ImsQueryUtil;
import com.bedrosians.bedlogic.util.ImsValidation;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;


@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
	ItemDao itemDao;  
    
    @Autowired
    ImsNewFeatureDao imsNewFeatureDao;  
    
    @Autowired
    VendorDao vendorDao;  
 
    	    	
    @Loggable(value = LogLevel.TRACE)
    @Override
	@Transactional(readOnly=true)
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
		return item;
	}

	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(readOnly=true)
	public List<Item> getProductsByQueryParameters(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException{
		
		ImsValidation.validateQueryParams(queryParams);	
		List<Item> items = null;
		try{
			items = itemDao.getItemsByQueryParameters(queryParams);
		}
		catch(HibernateException hbe){
			hbe.printStackTrace();
			throw new BedDAOException("Error occured during getProductsByQueryParameters() due to: " + hbe.getMessage(), hbe);
		}
	    /*for(Item item : items){
			if(item.getImsNewFeature() == null){
				item.setImsNewFeature(imsNewFeatureDao.getImsNewFeatureById(item.getItemcd().trim()));
			}	
			if(item.getVendors() == null || item.getVendors().isEmpty()){
				item.setVendors(vendorDao.getVendorsByItemId(item.getItemcd().trim()));
			}
		}*/
		return items;
	}
		
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public String createProduct(Item item) throws BedDAOBadParamException, BedDAOException{
		String id;
		ImsValidation.validateNewItem(item);
		if(item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null) {
			   ImsNewFeature imsNewFeature = new ImsNewFeature();	
			   imsNewFeature.setCreatedDate(new Date());
			   item.addImsNewFeature(imsNewFeature);	
		}	
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
	@Transactional
	public String createProduct(MultivaluedMap<String, String> queryParams)throws BedDAOBadParamException, BedDAOException{
		String id;
		ImsValidation.validateInsertParams(queryParams);
		Item item = new Item();
		if(item.getImsNewFeature() == null || item.getImsNewFeature().getItem() == null) {
		   ImsNewFeature imsNewFeature = new ImsNewFeature();	
		   imsNewFeature.setCreatedDate(new Date());
		   item.addImsNewFeature(imsNewFeature);	
		}
		if(item.getVendors() == null || item.getVendors().isEmpty()){
		   item.initVendors(ImsQueryUtil.determineNumberOfVendors(queryParams));
		}
		if(ImsQueryUtil.containsAnyKey(queryParams, Icon.allPropertis()))
		   item.addIcon(new Icon());	
/*		if(ImsQueryUtil.containsKey(queryParams, "poNote")) {
		   Note poNote = new Note("po_note");
		   poNote.setCreatedDate(new Date());
		   item.addPoNote(poNote);
		}  
		if(ImsQueryUtil.containsKey(queryParams, "buyerNote")) {
		   Note buyerNote = new Note("buyer_note");
		   buyerNote.setCreatedDate(new Date());
		   item.addBuyerNote(buyerNote);
	    }
		if(ImsQueryUtil.containsKey(queryParams, "invoiceNote")) {
		   Note invoiceNote = new Note("invoice_note");
		   invoiceNote.setCreatedDate(new Date());
		   item.addInvoiceNote(invoiceNote);
	    }
		if(ImsQueryUtil.containsKey(queryParams, "internalNote")) {
		   Icon icon = new Icon();
    	   item.addIcon(icon);
		}
	*/						
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
		return id;
		
	}
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public void updateProduct(String itemId, Item item) throws BedDAOBadParamException, BedDAOException, BedResException{
		if(itemId == null || itemId.length() < 1)
	    	   throw new BedDAOBadParamException("Please enter valid item code !");	
		
		Item retrievedItem = itemDao.loadItemById(itemId);
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
	@Transactional
	public void updateProduct(Item item){
		itemDao.updateItem(item); 
	}	
	
	@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public void updateProduct(MultivaluedMap<String, String> queryParams) throws BedDAOException, BedResException{
		
		String itemcd = ImsQueryUtil.getValue(queryParams, "itemcd");
		if(itemcd == null || itemcd.trim().length() == 0)
			itemcd = ImsQueryUtil.getValue(queryParams, "itemcode");	
		Item item = null;
		//item = getProductById(itemcd.trim());
		try{
		   item = itemDao.loadItemById(itemcd.trim());
		}
	    catch(HibernateException hbe){
		    hbe.printStackTrace();
		    throw new BedDAOException("Error occured during gupdateProduct() due to: " + hbe.getMessage(), hbe);
	    }
		if(item == null)
	       throw new BedResException("No data found for the given item code");	
		
		item = ImsQueryUtil.buildItemForUpdate(item,queryParams);
		
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
	
}
