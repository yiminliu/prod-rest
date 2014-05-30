package com.bedrosians.bedlogic.service.product;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;

public interface ProductService {
	
	public Item getProductById(String id) throws BedDAOBadParamException, BedDAOException;
		
	public List<Item> getProducts(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException, BedResException;
	
	public List<Item> getProducts(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException;
	
	public String createProduct(Item item)throws BedDAOBadParamException, BedDAOException, BedResException;
			   	
	public String createProduct(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException;
	
	public void updateProduct(Item item) throws BedDAOBadParamException, BedDAOException, BedResException;
	
	public void updateProduct(String itemId, Item item) throws BedDAOBadParamException, BedDAOException, BedResException;
	
	public void updateProduct(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException, BedResException;
			
	public void deleteProductById(String id) throws BedDAOBadParamException, BedDAOException, BedResException;
	
	public void deleteProduct(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException, BedResException;

	
}
