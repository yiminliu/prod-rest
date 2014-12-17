package com.bedrosians.bedlogic.dao.ims;


import java.util.List;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;
import com.bedrosians.bedlogic.exception.BedDAOException;


public interface KeymarkVendorDao extends GenericDao<KeymarkVendor, String>{
  
   public List<Integer> getKeymarkVendorIdList(); 
  
}