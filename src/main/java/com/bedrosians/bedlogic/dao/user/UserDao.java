package com.bedrosians.bedlogic.dao.user;

import java.util.List;

import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.user.User;


public interface UserDao extends GenericDao<User, String>{
  
  public User getUserByName(Session session, String userName);
  public User getUserByEmail(Session session, String email);
  public List<User> getAllUsers(Session session);
  public void updateUser(Session session, User user);
  public Long createUser(Session session, User user);
  
}