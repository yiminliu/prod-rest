package com.bedrosians.bedlogic.service.user;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.hibernate.Session;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;

public interface KeymarkUcUserService {
	
	public List<KeymarkUcUser> getAllUsers(Session session) throws BedDAOException;
		
	public KeymarkUcUser getUserByUserCode(String userCode) throws BedDAOException;

}
