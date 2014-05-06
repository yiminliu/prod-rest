package com.bedrosians.bedlogic.dao.user;


import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.domain.user.User;
import com.bedrosians.bedlogic.exception.BedDAOException;

@Repository("keymarkUcUserDao")
public class KeymarkUcUserDaoImpl extends GenericDaoImpl<KeymarkUcUser, String> implements KeymarkUcUserDao {
     
	@Override
	public KeymarkUcUser getUserByUserCode(Session session, String userCode){
		KeymarkUcUser user = null;
	    String queryString = "From KeymarkUcUser u where u.userCode = :userCode";
	    Criteria criteria = session.createCriteria(KeymarkUcUser.class);
	    criteria.setReadOnly(true);
	    criteria.add(Restrictions.eq("userCode", userCode.trim()).ignoreCase());
	    user = (KeymarkUcUser)criteria.uniqueResult();
		  return user;
	}
	
	@Override
	public List<KeymarkUcUser> getAllUsers(Session session){
		  Query query = session.createQuery("FROM KeymarkUcUser ORDER BY userCode ASC");
		  query.setReadOnly(true);
		  query.setCacheable(true);
		  query.setCacheMode(CacheMode.NORMAL);
		  return (List<KeymarkUcUser>)query.list();
	}
	
}
