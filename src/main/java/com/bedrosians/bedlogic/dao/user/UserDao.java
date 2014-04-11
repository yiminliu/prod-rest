package com.bedrosians.bedlogic.dao.user;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.domain.user.User;


public interface UserDao extends GenericDao<User, String>{
  
  public User getUserByName(Session session, String userName);
  public User getUserByEmail(Session session, String email);
  public void updateUser(Session session, User user);
  public String createUser(Session session, User user);
  
}