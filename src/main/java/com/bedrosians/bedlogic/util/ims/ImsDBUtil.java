package com.bedrosians.bedlogic.util.ims;

import java.util.Collections;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.exception.DatabaseOperationException;
import com.bedrosians.bedlogic.exception.InputParamException;
import com.bedrosians.bedlogic.util.logger.aspect.LogLevel;
import com.bedrosians.bedlogic.util.logger.aspect.Loggable;

@Service("imsDBUtil")
public class ImsDBUtil{
     
    @Autowired
	private SessionFactory sessionFactory;
         	    	  
    //--------------------------------Retrieval DB Operation --------------------------//
   
   
	@SuppressWarnings("unchecked")
    @Loggable(value = LogLevel.INFO)
	@Transactional(readOnly=true)
	public List<String> getItemCodeList(){
		Query query = getSession().createQuery("Select itemcode From Ims");
		query.setCacheable(true);
		return Collections.checkedList(query.list(), String.class);
	}
    

   public boolean itemCodeIsTaken(String itemCode){
	List<String> itemCodeList = null;
	try{
 	  itemCodeList = getItemCodeList();
	}
	catch(Exception e){
		 throw new DatabaseOperationException("Error occured while retriving item code. " + e);
	}
	for(String s : itemCodeList){
		if(s.trim().equalsIgnoreCase(itemCode))
		   return true;
	}
	return false;
}

	/************************* Keymark Vender DB Operation ************************/
	@Loggable(value = LogLevel.INFO)
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public KeymarkVendor getKeymarkVendorByVendorNumber(Integer vendorId){
	     KeymarkVendor vendor = null;
	    	if(vendorId == null)
	    	   throw new InputParamException("Please enter a valid Item Code !");	
			try{
				Session session = getSession();
				session.setCacheMode(CacheMode.NORMAL);
		  	    vendor = (KeymarkVendor)session.get(KeymarkVendor.class, vendorId);
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
	
    public boolean validateVendorId(Integer vendorId){
	   List<Integer> keymarkVendorIdList = null;
	   keymarkVendorIdList = getKeymarkVendorIdList();
	   for(Integer id : keymarkVendorIdList){
		   id = Integer.valueOf(String.valueOf(id).trim());
		   if(id.equals(vendorId))
		      return true;
	   }
	   return false;
   }

	@Transactional(readOnly=true)
	public List<Integer> getKeymarkVendorIdList(){
		Query query = getSession().createQuery("Select vendorNumber From KeymarkVendor where vendorNumber Is Not Null");
		return (List<Integer>)query.setCacheable(true).list();
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

}

