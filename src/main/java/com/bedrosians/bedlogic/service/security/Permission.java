package com.bedrosians.bedlogic.service.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bedrosians.bedlogic.domain.authority.Authority;
import com.bedrosians.bedlogic.domain.authority.Role;
import com.bedrosians.bedlogic.domain.user.User;


public abstract class Permission {
   protected final Set<Role> roleRepository = new HashSet<Role>();
   public boolean isAllowed(Authentication authentication, Object targetDomainObject) {
      boolean hasPermission = false;
      if (isAuthenticated(authentication)) {
         User user = (User) authentication.getPrincipal();
         hasPermission = isSelf(user, targetDomainObject);// ? true : isUserOwnedObject(user, targetDomainObject);
}

return hasPermission;
}

   protected String getLogin(Authentication authentication) {
	   return ((UserDetails) authentication.getPrincipal()).getUsername();
   }

   protected Collection<GrantedAuthority> getAuthorities(Authentication authentication) {
      return ((User) authentication.getPrincipal()).getAuthorities();
   }

   protected boolean isAuthenticated(Authentication authentication) {
      return authentication != null && authentication.getPrincipal() instanceof UserDetails;
   }

   protected boolean isRoleGrantedPermission(User user) {
      for (Authority authority : user.getRoles())
          if (roleRepository.contains(authority.getRole()))
              return true;
      return false;
   }
   
	//protected boolean isUserOwnedObject(User user, Object targetDomainObject) {
      //return targetDomainObject instanceof UserOwnedObject && ((UserOwnedObject) targetDomainObject).getOwnerId() == user.getUserId();
   //}

   protected boolean isSelf(User user, Object targetDomainObject) {
    return targetDomainObject instanceof User && ((User) targetDomainObject).getUserId() == user.getUserId();
   }
}
