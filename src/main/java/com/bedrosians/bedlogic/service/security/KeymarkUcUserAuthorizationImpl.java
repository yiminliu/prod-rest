package com.bedrosians.bedlogic.service.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.product.enums.DBOperation;
import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;

@Service("keymarkUcUserAuthorization")
@Scope("singleton")
public class KeymarkUcUserAuthorizationImpl implements KeymarkUcUserAuthorization{

	public boolean authorize(KeymarkUcUser user, String domain, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		switch(dBOperation) {
		   case SEARCH:
			   return true;
		   case CREATE:
			   if("Product".equalsIgnoreCase(domain))
			       return user.getImallowcreateitem() == 'Y';
			   else if("Account".equalsIgnoreCase(domain))
			       return user.getArcreatecustcd() == 'Y';
		   case UPDATE:	
			   if("Product".equalsIgnoreCase(domain))
			      return user.getImallowchgims() == 'Y';
			   else if("Account".equalsIgnoreCase(domain))
			       return user.getArcreatecustcd() == 'Y';
		   case DELETE:	
			   return user.getImallowchgims() == 'Y';	   
		   default:
			   throw new BedDAOBadParamException ("Unsupported user type");	   
		}	
	}
}
