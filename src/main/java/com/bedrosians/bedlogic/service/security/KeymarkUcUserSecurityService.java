package com.bedrosians.bedlogic.service.security;

import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.domain.item.enums.ProductOperation;

public interface KeymarkUcUserSecurityService {

	public void doSecurityCheck(String userType, String userCode, String password, boolean passwordBased, ProductOperation productOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
	public void doSecurityCheck(String userType, String userCode, ProductOperation productOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
}
