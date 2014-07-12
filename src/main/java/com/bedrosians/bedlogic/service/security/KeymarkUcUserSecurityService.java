package com.bedrosians.bedlogic.service.security;

import javax.ws.rs.core.HttpHeaders;

import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.domain.product.enums.DBOperation;

public interface KeymarkUcUserSecurityService {
		
	public void doSecurityCheck(String userType, String userCode, String password, boolean passwordBased, String domain,DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
	public void doUserSecurityCheck(HttpHeaders requestHeaders, String domain, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
		
	public void doSecurityCheck(String userType, String userCode, String domain, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
}
