package com.bedrosians.bedlogic.service.ims;

import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.util.enums.DBOperation;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ImsService {	
	public Ims getItem(String itemCode);
	public List<Ims> getActiveAndShownOnWebsiteItems();
	public List<Ims> getItems(LinkedHashMap<String, List<String>> queryParams);
	public List<?> getItems(MultivaluedMap<String, String> queryParams, boolean wrappedData);	
	public String createItem(ObjectNode inputJsonObj);
	public String createOrUpdateItem(Ims item, DBOperation operation);
	public Ims updateItem(Ims itemFromInput);			
	public void deleteItemByItemCode(String itemCode);
	public void deleteItem(Ims ims);
	public void deactivateItem(Ims itemFromInput);
    public boolean validateVendorId(Integer vendorId);
	public KeymarkVendor getKeymarkVendorByVendorNumber(Integer vendorId);
	public List<Vendor> getNewVendorSystem();
}
