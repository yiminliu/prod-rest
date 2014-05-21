package com.bedrosians.bedlogic.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.item.enums.ProductOperation;
import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.service.user.KeymarkUcUserService;

@Service("keymarkUcUserSecurityService")
@Scope(value = "singleton")
public class KeymarkUcUserSecurityServiceImpl implements KeymarkUcUserSecurityService{

	@Autowired
	KeymarkUcUserService keymarkUcUserService;
	
	@Autowired
	KeymarkUcUserAuthentication keymarkUcUserAuthentication;
	
	@Autowired
	KeymarkUcUserAuthorization keymarkUcUserAuthorization;
	
	KeymarkUcUser keymarkUcUser = null;
	
	@Override
	public void doSecurityCheck(String userType, String userCode, String password, boolean isPasswordBasedAuth, ProductOperation permission) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		switch(userType) {
		   case "guest": case "Guest":
			   return;
		   case "keymark":	
			   validateUserInfo(userCode, password, isPasswordBasedAuth, permission);
		   default:
			   throw new BedDAOBadParamException ("Unsupported user type");	   
		}
	}
	
	@Override
	public void doSecurityCheck(String userType, String userCode, ProductOperation permission) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		switch(userType) {
		   case "guest": case "Guest":
			   break;
		   case "keymark":	
			   validateUserInfo(userCode, "", false, permission);
			   break;
		   default:
			   throw new BedDAOBadParamException ("Unsupported user type");	   
		}
	}
	
	private void validateUserInfo(String userCode, String password, boolean isPasswordBased, ProductOperation permission) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		keymarkUcUser = getUser(userCode);        
		keymarkUcUserAuthentication.authenticate(keymarkUcUser, password, isPasswordBased);
		keymarkUcUserAuthorization.authorize(keymarkUcUser, permission);
	}
	
	private KeymarkUcUser getUser(String userCode) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		if(userCode == null || userCode.length() == 0)
		   throw new BedDAOBadParamException("User code should not be empty with \"keymark\" as user type");	
		KeymarkUcUser user = keymarkUcUserService.getUserByUserCode(userCode);  
		if(user == null)
			throw new BedResUnAuthorizedException("No user found for the given user code " + userCode);
		return user;
	}
	
}
