package com.bedrosians.bedlogic.service.security;

import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;

public interface KeymarkUcUserAuthentication {

	public boolean authenticate(String userType, String userCode) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
}
