package com.bedrosians.bedlogic.dao.ims;

import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.ims.Ims;

public interface ImsDao extends GenericDao<Ims, String>{
   public Ims getItemByItemCode(Session session, String itemCode);
   public Ims loadItemByItemCode(Session session, String itemCode);
   public List<String> getItemCodeList();	
   public List<Ims> getActiveAndShownOnWebsiteItems();
   public List<Ims> getItemsByQueryParameters(MultivaluedMap<String, String> queryParams);
   public List<Ims> getItems(LinkedHashMap<String,List<String>> queryParams);
   public String createItem(Ims ims);
   public void updateItem(Session session, Ims ims);
   public void deleteItem(Ims ims);	
   public void deleteItem(Session session, Ims ims);	

}