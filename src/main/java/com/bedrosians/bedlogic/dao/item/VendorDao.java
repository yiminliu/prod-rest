package com.bedrosians.bedlogic.dao.item;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.item.Vendor;


public interface VendorDao extends GenericDao<Vendor, String>{
  
  public List<Vendor> getVendorsByItemId(String itemId);
  
}