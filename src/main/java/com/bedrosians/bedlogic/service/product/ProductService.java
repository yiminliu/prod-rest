package com.bedrosians.bedlogic.service.product;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import com.bedrosians.bedlogic.domain.product.Product;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.util.JsonWrapper.ProductWrapper;

public interface ProductService {	
	public Product getProductById(String id) throws BedDAOBadParamException, BedDAOException;	
	public List<Product> getActiveAndShownOnWebsiteProducts() throws BedDAOBadParamException, BedDAOException;
	public List<Product> getProducts(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException;	
	public List<ProductWrapper> getWrappedProducts(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException;		   	
	public String createProduct(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException;
	public void updateProduct(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException;			
	public void deleteProductById(String id) throws BedDAOBadParamException, BedDAOException;
	public void deleteProduct(JSONObject inputJsonObj) throws BedDAOBadParamException, BedDAOException;
}
