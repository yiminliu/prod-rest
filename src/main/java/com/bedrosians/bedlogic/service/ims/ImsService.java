package com.bedrosians.bedlogic.service.ims;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.util.JsonWrapper.ItemWrapper;

public interface ImsService {	
	public Ims getItemByItemCode(String itemCode) throws BedDAOBadParamException, BedDAOException;	
	public List<Ims> getActiveAndShownOnWebsiteItems() throws BedDAOBadParamException, BedDAOException;
	public List<Ims> getItems(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException;	
	public List<ItemWrapper> getWrappedItems(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException;		   	
	public String createItem(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException;
	public void updateItem(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException;			
	public void deleteItemByItemCode(String id) throws BedDAOBadParamException, BedDAOException;
	public void deleteItem(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException;
}
