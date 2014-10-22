package com.bedrosians.bedlogic.service.product;

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

import com.bedrosians.bedlogic.dao.product.ProductDao;
import com.bedrosians.bedlogic.domain.product.Product;
import com.bedrosians.bedlogic.domain.product.enums.DBOperation;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.util.FormatUtil;
import com.bedrosians.bedlogic.util.JsonUtil;
import com.bedrosians.bedlogic.util.JsonWrapper.ProductWrapper;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;
import com.bedrosians.bedlogic.util.product.ImsDataUtil;
import com.bedrosians.bedlogic.util.product.ImsValidator;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
	private ProductDao productDao;  
    
    @Autowired
	private SessionFactory sessionFactory;
      	    	  
    //--------------------------------Retrieval DB Operation --------------------------//
    
    @Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public Product getProductById(String id) throws BedDAOBadParamException, BedDAOException{
    	Product product = null;
    	if(id == null || id.length() < 1)
    	   throw new BedDAOBadParamException("Please enter valid product code !");	
		try{
			Session session = getSession();
			session.setCacheMode(CacheMode.NORMAL);
	  	    product = productDao.getItemById(session, id);
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
		return FormatUtil.process(product);
	}

    @Loggable(value = LogLevel.INFO)
    @Override
    @Transactional(readOnly = true)
	public List<Product> getActiveAndShownOnWebsiteProducts() throws BedDAOBadParamException, BedDAOException{
    	return productDao.getActiveAndShownOnWebsiteItems();
	}
    
	@Loggable(value = LogLevel.INFO)
	@Override
	public List<Product> getProducts(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException{
		if(queryParams == null || queryParams.isEmpty()){
			queryParams = new MultivaluedMapImpl();
			queryParams.put("inactivecode", Arrays.asList(new String[]{"N"}));
		}
		List<Product> products = null;
		try{
			products = productDao.getItemsByQueryParameters(queryParams);
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
		List<Product> processedItems = new ArrayList<>();
		for(Product product : products){
			processedItems.add(FormatUtil.process(product));
		}
		return processedItems;
	}
	

	@Loggable(value = LogLevel.INFO)
	@Override
	public List<ProductWrapper> getWrappedProducts(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException{
		if(queryParams == null || queryParams.isEmpty()){
			queryParams = new MultivaluedMapImpl();
			queryParams.put("inactivecode", Arrays.asList(new String[]{"N"}));
		}
		List<Product> products = null;
		try{
			products = productDao.getItemsByQueryParameters(queryParams);
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
		List<ProductWrapper> productWrapperList = new ArrayList<ProductWrapper>(products.size());
		for(Product product : products){
			productWrapperList.add(new ProductWrapper(FormatUtil.process(product)));
		}
		return productWrapperList;
	}
	
    
	//--------------------------------Creation DB Operation --------------------------//
	
	@Loggable(value = LogLevel.INFO)
	@Override
	public String createProduct(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException{  	
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		String id;
		Product itemToCreate = new Product(itemCode);
     	Product itemFromInput = (Product)JsonUtil.jsonObjectToPOJO(jsonObj, new Product());
     	itemToCreate = ImsDataUtil.transformItem(itemToCreate, itemFromInput, DBOperation.CREATE);
     	ImsValidator.validateNewItem(itemToCreate);
   	    try{
		   id = productDao.createItem(itemToCreate);
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
	public synchronized void updateProduct(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException{
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		Product itemFromInput = (Product)JsonUtil.jsonObjectToPOJO(jsonObj, new Product());
		Product itemToUpdate = null;
		Session session = getSession();
		try{
			itemToUpdate = productDao.getItemById(session, itemCode.trim());
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
		      productDao.updateItem(session,itemToUpdate);
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
	synchronized public void deleteProductById(String id) throws BedDAOBadParamException, BedDAOException{
	    if(id == null || id.length() == 0)
	    	 throw new BedDAOBadParamException("Product code should not be empty");		
		Product product = new Product(id);
		try{
			productDao.deleteItem(product);
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
	synchronized public void deleteProduct(JSONObject jsonObj) throws BedDAOBadParamException, BedDAOException{
		String itemCode = JsonUtil.validateItemCode(jsonObj);
		deleteProductById(itemCode);
	}

	private synchronized Session getSession(){
	    	return sessionFactory.getCurrentSession();
	}
}
