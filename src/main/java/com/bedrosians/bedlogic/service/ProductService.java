package com.bedrosians.bedlogic.service;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.item.Item;


@Service
public interface ProductService {
	
    //public List<Item> getProducts();
	
	public Item getProductById(String id);
		
	public List<Item> getByQueryParameters(MultivaluedMap<String, String> queryParams);
	
	public String createProduct(Item item);
	
	public String createProduct(MultivaluedMap<String, String> queryParams);
	
	public void updateProduct(Item item);
	
	public void updateProduct(String itemId, Item item);
	
	public void updateProduct(MultivaluedMap<String, String> queryParams);

}
