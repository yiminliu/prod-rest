package com.bedrosians.bedlogic.service.user;

import java.util.List;

import org.hibernate.Session;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;

public interface KeymarkUcUserService {
	
	public List<KeymarkUcUser> getAllUsers(Session session);
		
	public KeymarkUcUser getUserByUserCode(String userCode);

}
