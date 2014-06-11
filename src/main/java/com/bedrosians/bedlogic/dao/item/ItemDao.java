package com.bedrosians.bedlogic.dao.item;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedDAOException;


public interface ItemDao extends GenericDao<Item, String>{
   public Item getItemById(Session session, String itemId);
   public Item loadItemById(Session session, String itemId);
   public List<Item> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams) throws BedDAOException;
   public void updateItem(Session session, Item item);
   public String createItem(Item item);
   public void deleteItem(Item item);	

}