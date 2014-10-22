package com.bedrosians.bedlogic.dao.product;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.product.Product;

public interface ProductDao extends GenericDao<Product, String>{
   public Product getItemById(Session session, String itemId);
   public Product loadItemById(Session session, String itemId);
   public List<Product> getActiveAndShownOnWebsiteItems();
   public List<Product> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams);
   public String createItem(Product product);
   public void updateItem(Session session, Product product);
   public void deleteItem(Product product);	

}