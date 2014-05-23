package com.bedrosians.bedlogic.service.user;

import java.util.List;

import org.hibernate.Session;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOException;

public interface KeymarkUcUserService {
	
	public List<KeymarkUcUser> getAllUsers(Session session) throws BedDAOException;
		
	public KeymarkUcUser getUserByUserCode(String userCode) throws BedDAOException;

}
