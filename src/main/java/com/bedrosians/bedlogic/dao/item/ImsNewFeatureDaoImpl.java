package com.bedrosians.bedlogic.dao.item;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.item.ImsNewFeature;
import com.bedrosians.bedlogic.domain.item.Item;


@Repository("imsNewFeatureDao")
public class ImsNewFeatureDaoImpl extends GenericDaoImpl<ImsNewFeature, String> implements ImsNewFeatureDao {
     
	@Override
	public ImsNewFeature getImsNewFeatureById(String itemId) {
      return findById(itemId);   
	}
}
