package com.bedrosians.bedlogic.service.user;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.domain.user.User;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResException;

public interface UserService {
	
	public User getUserById(String id) throws BedDAOException;
		
	public User getUserByName(String userName);
	
	public User getUserByEmail(String email);
	
	public String createUser(User user)throws BedDAOBadParamException, BedDAOException, BedResException;
	
	public String createUser(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException, BedDAOException, BedResException;
	
	public void updateUser(User user) throws BedDAOBadParamException, BedDAOException, BedResException;
	
	public void updateUser(String userName, User user) throws BedDAOBadParamException, BedDAOException, BedResException;
	
}
