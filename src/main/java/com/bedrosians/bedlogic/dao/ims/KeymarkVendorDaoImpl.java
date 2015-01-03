package com.bedrosians.bedlogic.dao.ims;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;

@Repository("keymarkVendorDao")
public class KeymarkVendorDaoImpl extends GenericDaoImpl<KeymarkVendor, String> implements KeymarkVendorDao {
	
    //--------------------------- retrieval DB operation -----------------------//
    
	@Override
	@Transactional(readOnly=true)
	public List<Integer> getKeymarkVendorIdList(){
		Query query = getSession().createQuery("Select vendorNumber From KeymarkVendor where vendorNumber Is Not Null");
		return (List<Integer>)query.setCacheable(true).list();
	}
	
   public KeymarkVendor getKeymarkVendorByVendorNumber(Session session, Integer vendorId){
	   return (KeymarkVendor)session.get(KeymarkVendor.class, vendorId);
   }
}
