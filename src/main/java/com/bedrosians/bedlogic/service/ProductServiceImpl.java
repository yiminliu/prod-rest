package com.bedrosians.bedlogic.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.item.ImsNewFeatureDao;
import com.bedrosians.bedlogic.dao.item.ItemDao;
import com.bedrosians.bedlogic.dao.item.VendorDao;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.util.ImsQueryUtil;


@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
	ItemDao itemDao;  
    
    @Autowired
    ImsNewFeatureDao imsNewFeatureDao;  
    
    @Autowired
    VendorDao vendorDao;  
 
    	    	
   // @Loggable(value = LogLevel.TRACE)
    @Override
	@Transactional(readOnly=true)
	public Item getProductById(String id) {
    	return itemDao.getItemById(id);
	}

	
	//@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(readOnly=true)
	public List<Item> getByQueryParameters(MultivaluedMap<String, String> queryParams){
		List<Item> items = itemDao.getItemsByQueryParameters(queryParams);
	/*	for(Item item : items){
			if(item.getImsNewFeature() == null){
				item.setImsNewFeature(imsNewFeatureDao.getImsNewFeatureById(item.getItemcd().trim()));
			}	
			if(item.getVendors() == null || item.getVendors().isEmpty()){
				item.setVendors(vendorDao.getVendorsByItemId(item.getItemcd().trim()));
			}
		}*/
		return items;
	}
		
	//@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public String createProduct(Item item){
	   //ImsNewFeature imsNewFeature = item.getImsNewFeature();
		if(item.getImsNewFeature().getItem() == null)
			item.getImsNewFeature().setItem(item);
		item.getImsNewFeature().setCreatedDate(new Date());
	
		return itemDao.createItem(item); 
	}
	
	//@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public String createProduct(MultivaluedMap<String, String> queryParams){
		Item item = null;
		try{
		    item = ImsQueryUtil.buildItemForInsert(queryParams);
		    if(item.getImsNewFeature().getItem() == null)
			   item.getImsNewFeature().setItem(item);
		    item.getImsNewFeature().setCreatedDate(new Date());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return itemDao.createItem(item);
	}
	
	
	//@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public void updateProduct(String itemId, Item item){
		Item retrievedItem = null;
		//try{
		   retrievedItem = getProductById(itemId);
		   if(retrievedItem == null)
			  //throw new DataNotFoundException("No data found"); 
			  // throw new BedDAOException("No data found"); 
		//}
		//catch(Exception e){
			//throw e;
			
		//}
	   itemDao.updateItem(item); 
	 
	}
	
	//@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public void updateProduct(Item item){
		itemDao.updateItem(item); 
	}	
	
	//@Loggable(value = LogLevel.TRACE)
	@Override
	@Transactional
	public void updateProduct(MultivaluedMap<String, String> queryParams){
		
		String itemcd = ImsQueryUtil.getValue(queryParams, "itemcd");
		if(itemcd == null || itemcd.trim().length() == 0)
			itemcd = ImsQueryUtil.getValue(queryParams, "itemcode");	
		Item item = getProductById(itemcd.trim());
		//item.setItemcd(itemcd.trim()); //avoid padding
		item = ImsQueryUtil.buildItemForUpdate(item,queryParams);
		item.getImsNewFeature().setLastModifiedDate(new Date());
		itemDao.updateItem(item);
	}
	
}
