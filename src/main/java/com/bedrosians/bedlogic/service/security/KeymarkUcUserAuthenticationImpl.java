package com.bedrosians.bedlogic.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.service.user.KeymarkUcUserService;


@Service("keymarkUcUserAuthentication")
@Scope("singleton")
public class KeymarkUcUserAuthenticationImpl implements KeymarkUcUserAuthentication{

	@Autowired 
	KeymarkUcUserService keymarkUcUserService;
	
	@Override
	public boolean authenticate(KeymarkUcUser user, String password, boolean isPasswordBasedAuth) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		boolean result = false;
		if(isPasswordBasedAuth && password == null)
		   throw new BedDAOBadParamException("Password should not be empty with \"keymark\" as user type");
		if(isPasswordBasedAuth)
			result = (password.equals(user.getPasswd()) && (user.getActive() == 'Y' || user.getActive() == 'P')) ? true : false;
		else 
			result = (user.getActive() == 'Y' || user.getActive() == 'P')? true : false;
		
		if(!result)
			throw new BedResUnAuthorizedException("Authentication failed for the given user code " + user.getUserCode());
		else
			return result;
	}
	
}
