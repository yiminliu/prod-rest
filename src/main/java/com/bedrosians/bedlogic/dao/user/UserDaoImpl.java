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
import com.bedrosians.bedlogic.domain.user.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, String> implements UserDao {
     
	@Override
	public User getUserByName(Session session, String userName){
		  User user = null;
		  String queryString = "From User u where u.username = :userName";
		  Criteria criteria = session.createCriteria(User.class);
		  criteria.setReadOnly(true);
		  criteria.add(Restrictions.eq("username", userName.trim()).ignoreCase());
		  System.out.println("Criteria = " + criteria.toString());
		  user = (User)criteria.uniqueResult();
		  return user;
	}
	
	@Override
	public List<User> getAllUsers(Session session){
		  Query query = session.createQuery("From User");
		  query.setReadOnly(true);
		  query.setCacheable(true);
		  query.setCacheMode(CacheMode.NORMAL);
		  return (List<User>)query.list();
	}
	
	@Override
	  public User getUserByEmail(Session session, String email){ 
		  return null;
	  }
	 
	  @Override
	 
	  public void updateUser(Session session, User user){};
	  
	  @Override
	  public Long createUser(Session session, User user){ 
		  return (Long)session.save(user);
	  }
	
}