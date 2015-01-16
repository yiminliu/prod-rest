package com.bedrosians.bedlogic.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.InputParamException;
import com.bedrosians.bedlogic.exception.UnauthorizedException;
import com.bedrosians.bedlogic.service.user.KeymarkUcUserService;


@Service("keymarkUcUserAuthentication")
@Scope("singleton")
public class KeymarkUcUserAuthenticationImpl implements KeymarkUcUserAuthentication{

	@Autowired 
	KeymarkUcUserService keymarkUcUserService;
	
	@Override
	public boolean authenticate(KeymarkUcUser user, String password, boolean isPasswordBasedAuth){
		boolean result = false;
		if(isPasswordBasedAuth && password == null)
		   throw new InputParamException("Password should not be empty with \"keymark\" as user type");
		if(isPasswordBasedAuth)
			result = (password.equals(user.getPasswd()) && (user.getActive() == 'Y' || user.getActive() == 'P')) ? true : false;
		else 
			result = (user.getActive() == 'Y' || user.getActive() == 'P')? true : false;
		
		if(!result)
			throw new UnauthorizedException("Authentication failed for the given user code " + user.getUserCode());
		else
			return result;
	}
	
}
