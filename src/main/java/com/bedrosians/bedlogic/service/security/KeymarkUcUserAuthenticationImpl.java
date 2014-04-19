package com.bedrosians.bedlogic.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;
import com.bedrosians.bedlogic.service.user.KeymarkUcUserService;


@Service("keymarkUcUserAuthentication")
public class KeymarkUcUserAuthenticationImpl implements KeymarkUcUserAuthentication{

	@Autowired 
	KeymarkUcUserService keymarkUcUserService;
	
	public boolean authenticate(String userType, String userCode) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException {
		switch(userType) {
		   case "guest": case "Guest":
			   return true;
		   case "keymark":	
			   return authenticateUsesrCode(userCode);
		   default:
			   throw new BedDAOBadParamException ("Unsupported user type");	   
		}
	}
	
	private boolean authenticateUsesrCode(String userCode) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException{
		if(userCode == null || userCode.length() == 0)
		   throw new BedDAOBadParamException("Empty user code");	
		KeymarkUcUser user = null;
		user = keymarkUcUserService.getUserByUserCode(userCode);
		if(user == null)
			throw new BedResUnAuthorizedException("No user found for the given user code " + userCode);
		else if(user.getActive() == 'Y' || user.getActive() == 'P')
		   return true;
		else
			throw new BedResUnAuthorizedException("Authentication failed for the user: " + userCode);	
	}
	
	/*
	 
* Authenticates access token
*
* @param string $accessToken access token
* @return UserRec returns NULL if unable to authenticate

public static function userFromAuthenticatedUser($authUserType, $authUserCode)
{
$user = NULL;

if ($authUserType == 'guest')
{
$user = new UserRec;
$user->username = 'Guest';
}
else if ($authUserType == 'keymark')
{
$userAccess = new UserAccess;
$userCodeRec = $userAccess->readUserCodeRec($authUserCode, '', FALSE);
if (!empty($userCodeRec))
{
$user = self::userRecForUserCodeRec($userCodeRec);
}
}
return $user;
}
private static function userRecForUserCodeRec($userCodeRec)
{
$user = new UserRec;

$user->username = trim($userCodeRec['username']);
$user->storenumber = (int)$userCodeRec['storenbr'];
$user->defaultstorenumber = (int)$userCodeRec['obdefaultstorenbr'];
$user->setAllowKeymarkAccess(TRUE);
$user->setAllowViewAvgCost($userCodeRec['imchgavgcost'] != 'N');
$user->setAllowViewPOPrice($userCodeRec['podisplayprice'] != 'N');
$user->setAllowViewOBCost($userCodeRec['obdisplaycost'] != 'N');
return $user;
}
	 */
}
