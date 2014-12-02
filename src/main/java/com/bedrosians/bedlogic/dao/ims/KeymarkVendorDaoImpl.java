package com.bedrosians.bedlogic.dao.ims;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;
import com.bedrosians.bedlogic.exception.BedDAOException;


@Repository("keymarkVendorDao")
public class KeymarkVendorDaoImpl extends GenericDaoImpl<KeymarkVendor, String> implements KeymarkVendorDao {
	
    @Autowired
	private SessionFactory sessionFactory;
	
    //--------------------------- retrieval DB operation -----------------------//
    
	
	@Override
	@Transactional(readOnly=true)
	public List<Integer> getKeymarkVedorIdList() throws BedDAOException{
		Query query = getSession().createQuery("Select vendorId From KeymarkVendor where vendorId Is Not Null");
		return (List<Integer>)query.setCacheable(true).list();
	}
	
	
   private synchronized Session getSession(){
	   Session session = sessionFactory.getCurrentSession();
	   if (session == null)
		   session = sessionFactory.openSession();
       return session;
   }
}
