package com.bedrosians.bedlogic.service.security;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.util.enums.ApiName;
import com.bedrosians.bedlogic.util.enums.DBOperation;

public interface KeymarkUcUserAuthorization {

	public boolean authorize(KeymarkUcUser user, ApiName apiName, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
}
