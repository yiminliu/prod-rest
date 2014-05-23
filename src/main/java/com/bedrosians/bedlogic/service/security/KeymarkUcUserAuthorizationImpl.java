package com.bedrosians.bedlogic.service.security;

import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.item.enums.DBOperation;
import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;

@Service("keymarkUcUserAuthorization")
public class KeymarkUcUserAuthorizationImpl implements KeymarkUcUserAuthorization{

	public boolean authorize(KeymarkUcUser user, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		switch(dBOperation) {
		   case SEARCH:
			   return true;
		   case CREATE:
			   return user.getImallowcreateitem() == 'Y';
		   case UPDATE:	
			   return user.getImallowchgims() == 'Y';
		   case DELETE:	
			   return user.getImallowchgims() == 'Y';	   
		   default:
			   throw new BedDAOBadParamException ("Unsupported user type");	   
		}	
	}
}
