package com.bedrosians.bedlogic.service.mvc.ims;

import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;

public interface ImsServiceMVC {	
	public Ims getItemByItemCode(String itemCode) throws BedDAOBadParamException, BedDAOException;	
	public boolean itemCodeIsTaken(String itemCode) throws BedDAOException;	
	
	public List<Ims> getActiveAndShownOnWebsiteItems() throws BedDAOBadParamException, BedDAOException;
	public List<Ims> getItems(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException;	
	
	public List<Ims> getItems(LinkedHashMap<String, List<String>> queryParams) throws BedDAOBadParamException, BedDAOException;
	public String createItem(Ims ims) throws BedDAOBadParamException, BedDAOException;
	public void updateItem(Ims ims) throws BedDAOBadParamException, BedDAOException;	
	public void deactivateItem(Ims itemFromInput) throws BedDAOBadParamException, BedDAOException;
	public void deleteItemByItemCode(String id) throws BedDAOBadParamException, BedDAOException;
	public void deleteItem(Ims ims) throws BedDAOBadParamException, BedDAOException;
	public void initVendors(int n);
	public List<Vendor> getNewVendorSystem();
	public boolean validateVendorId(Integer vendorId);
}
