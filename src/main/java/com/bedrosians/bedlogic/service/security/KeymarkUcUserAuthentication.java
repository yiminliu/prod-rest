package com.bedrosians.bedlogic.service.security;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;


public interface KeymarkUcUserAuthentication {

	public boolean authenticate(KeymarkUcUser user, String password, boolean isPasswordBasedAuth);
	
}
