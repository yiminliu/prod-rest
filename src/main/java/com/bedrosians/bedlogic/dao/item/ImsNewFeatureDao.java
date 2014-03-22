package com.bedrosians.bedlogic.dao.item;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;


public interface ImsNewFeatureDao extends GenericDao<ImsNewFeature, String>{
  
  
  public ImsNewFeature getImsNewFeatureById(String itemId);
  
}