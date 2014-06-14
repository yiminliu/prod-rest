package com.bedrosians.bedlogic.dao.user;


import java.util.List;


import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bedrosians.bedlogic.dao.GenericDaoImpl;
import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;

@Repository("keymarkUcUserDao")
public class KeymarkUcUserDaoImpl extends GenericDaoImpl<KeymarkUcUser, String> implements KeymarkUcUserDao {
     
	@Override
	public KeymarkUcUser getUserByUserCode(Session session, String userCode){
		KeymarkUcUser user = null;
	    //String queryString = "From KeymarkUcUser u where u.userCode = :userCode";
	    Criteria criteria = session.createCriteria(KeymarkUcUser.class);
	    criteria.setReadOnly(true);
	    criteria.setCacheable(true);
	    criteria.add(Restrictions.eq("userCode", userCode.trim()).ignoreCase());
	    user = (KeymarkUcUser)criteria.uniqueResult();
		  return user;
	}
	
	@Override
	public List<KeymarkUcUser> getAllUsers(Session session){
		  Query query = session.createQuery("FROM KeymarkUcUser ORDER BY userCode ASC");
		  query.setReadOnly(true);
		  query.setCacheable(true);
		  return (List<KeymarkUcUser>)query.list();
	}
	
}
