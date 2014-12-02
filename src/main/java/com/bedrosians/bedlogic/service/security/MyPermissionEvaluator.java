package com.bedrosians.bedlogic.service.security;


	import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

	import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.bedrosians.bedlogic.service.security.Permission;
	

	public class MyPermissionEvaluator implements PermissionEvaluator {
	private Map<String, Permission> permissionNameToPermissionMap = new HashMap<String, Permission>();

	protected MyPermissionEvaluator() {
	// prevent instantiation
	}

	public MyPermissionEvaluator(Map<String, Permission> permissionNameToPermissionMap) {
	this.permissionNameToPermissionMap = permissionNameToPermissionMap;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permissionName) {

	boolean hasPermission = false;

	if (authentication != null && permissionName instanceof String) {
	    Permission p = getPermission((String) permissionName);
	    hasPermission = p.isAllowed(authentication, targetDomainObject);
	}
	return hasPermission;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permissionName) {
	// not yet implemented
		return false;
	}

	private Permission getPermission(String permissionKey) {
       Permission permission = permissionNameToPermissionMap.get(permissionKey);
       if (permission == null)
	      throw new IllegalArgumentException("No permission with key " + permissionKey + " is defined in " + this.getClass().toString());
     	return permission;
	}
	
}
