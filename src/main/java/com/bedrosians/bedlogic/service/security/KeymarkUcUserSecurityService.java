package com.bedrosians.bedlogic.service.security;

import javax.ws.rs.core.HttpHeaders;

import com.bedrosians.bedlogic.util.enums.ApiName;
import com.bedrosians.bedlogic.util.enums.DBOperation;


public interface KeymarkUcUserSecurityService {
		
	public void doSecurityCheck(String userType, String userCode, String password, boolean passwordBased, ApiName apiName, DBOperation dBOperation);
	
	public void doUserSecurityCheck(HttpHeaders requestHeaders, ApiName apiName, DBOperation dBOperation);
		
	public void doSecurityCheck(String userType, String userCode, ApiName apiName, DBOperation dBOperation);
	
}
