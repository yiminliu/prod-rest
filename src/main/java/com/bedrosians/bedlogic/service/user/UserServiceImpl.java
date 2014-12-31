package com.bedrosians.bedlogic.service.user;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.dao.user.UserDao;
import com.bedrosians.bedlogic.domain.user.User;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.DataOperationException;

@Service("userService")
public class UserServiceImpl implements UserService{
					
	 @Autowired
	 private UserDao userDao;  
	    
	 @Autowired
	 private SessionFactory sessionFactory;
     
	@Override
	public User getUserByName(String userName){
		User user = null;
		Session session = sessionFactory.openSession();
	    try{
	    	user = userDao.getUserByName(session, userName);
	    }
	    catch(HibernateException e){
	    	e.printStackTrace();
	    	throw new DataOperationException("Error occured while retrieve user from database. Reason: " + e.getMessage(), e);
	    }
	    catch(Exception e){
	    	throw new DataOperationException("Error occured while retrieve user from database. Reason: " + e.getMessage(), e);
	    }
	    return user;
	}
	
	@Override
	public User getUserById(String id) throws BedDAOException{
		return null;
	}

	@Override
	  public User getUserByEmail(String email){ 
		  return null;
	  }
	 
	  @Override 
	  public void updateUser(User user){};
	  
	  @Override
	  public void updateUser(String userName, User user) throws BedDAOBadParamException, BedDAOException{
		  
	  }
	  
	  @Override
	  public String createUser(User user){ 
		  return null;
	  }
	  
	  @Override
	  public String createUser(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException{
		  return null;
	  }

	  
	
	
	
}
