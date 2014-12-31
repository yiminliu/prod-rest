package com.bedrosians.bedlogic.service.mvc.ims;

import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;
import com.bedrosians.bedlogic.domain.ims.Vendor;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.DataOperationException;
import com.bedrosians.bedlogic.util.enums.DBOperation;

public interface ImsServiceMVC {	
	public Ims getItemByItemCode(String itemCode);	
    public Ims getItemByItemCodeForClone(String itemCode);
	public boolean itemCodeIsTaken(String itemCode) throws DataOperationException;	
	public List<Ims> getActiveAndShownOnWebsiteItems() throws BedDAOBadParamException, BedDAOException;
	public List<Ims> getItems(LinkedHashMap<String, List<String>> queryParams);
	public String createItem(Ims ims, DBOperation createOrClone);
	public void updateItem(Ims ims);	
	public void deactivateItem(Ims itemFromInput) throws BedDAOBadParamException, BedDAOException;
	public void deleteItemByItemCode(String id);
	public void initVendors(int n);
	public List<Vendor> getNewVendorSystem();
	public boolean validateVendorId(Integer vendorId);
	public KeymarkVendor getKeymarkVendorByVendorNumber(Integer vendorId);
}
