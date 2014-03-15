package com.bedrosians.bedlogic.dao.item;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.item.Item;


public interface ItemDao extends GenericDao<Item, String>{
  
  public Item getItemById(String itemId);
  public List<Item> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams);
  public void updateItem(Item item);
  public String createItem(Item item);
  
}