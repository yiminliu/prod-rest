package com.bedrosians.bedlogic.dao.user;

import java.util.List;

import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;

public interface KeymarkUcUserDao extends GenericDao<KeymarkUcUser, String>{
  
  public KeymarkUcUser getUserByUserCode(Session session, String userCode);
  public List<KeymarkUcUser> getAllUsers(Session session);  
}