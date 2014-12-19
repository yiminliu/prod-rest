package com.bedrosians.bedlogic.dao.ims;


import java.util.List;

import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;
import com.bedrosians.bedlogic.exception.BedDAOException;


public interface KeymarkVendorDao extends GenericDao<KeymarkVendor, String>{
  
   public List<Integer> getKeymarkVendorIdList(); 
   public KeymarkVendor getKeymarkVendorByVendorNumber(Session session, Integer vendorId);
}