package com.bedrosians.bedlogic.service.security;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.util.enums.ApiName;
import com.bedrosians.bedlogic.util.enums.DBOperation;

@Service("keymarkUcUserAuthorization")
@Scope("singleton")
public class KeymarkUcUserAuthorizationImpl implements KeymarkUcUserAuthorization, Serializable{

	public boolean authorize(KeymarkUcUser user, ApiName apiName, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		switch(dBOperation) {
		   case SEARCH:
			   return true;
		   case CREATE:
			   if(apiName.IMS.equals(apiName))
			       return user.getImallowcreateitem() == 'Y';
			   else if(apiName.ACCOUNT.equals(apiName))
			       return user.getArcreatecustcd() == 'Y';
		   case UPDATE:	
			   if(apiName.IMS.equals(apiName))
			      return user.getImallowchgims() == 'Y';
			   else if(apiName.ACCOUNT.equals(apiName))
			       return user.getArcreatecustcd() == 'Y';
		   case DELETE:	
			   return user.getImallowchgims() == 'Y';	   
		   default:
			   throw new BedDAOBadParamException ("Unsupported user type");	   
		}	
	}
}
