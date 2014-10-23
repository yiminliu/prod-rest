package com.bedrosians.bedlogic.service.security;

import javax.ws.rs.core.HttpHeaders;

import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.util.enums.ApiName;
import com.bedrosians.bedlogic.util.enums.DBOperation;


public interface KeymarkUcUserSecurityService {
		
	public void doSecurityCheck(String userType, String userCode, String password, boolean passwordBased, ApiName apiName, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
	public void doUserSecurityCheck(HttpHeaders requestHeaders, ApiName apiName, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
		
	public void doSecurityCheck(String userType, String userCode, ApiName apiName, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
}
