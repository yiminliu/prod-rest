package com.bedrosians.bedlogic.dao.item;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.item.Vendor;


@Repository("vendorDao")
public class VendorDaoImpl extends GenericDaoImpl<Vendor, String> implements VendorDao {
					
   public List<Vendor> getVendorsByItemId(String itemId){
	   return findByParameter("itemcd", itemId);	  
	}
}
