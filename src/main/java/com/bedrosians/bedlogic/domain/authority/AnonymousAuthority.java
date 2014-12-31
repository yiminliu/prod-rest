package com.bedrosians.bedlogic.domain.authority;

import com.bedrosians.bedlogic.domain.user.User;


public class AnonymousAuthority extends Authority {
   public AnonymousAuthority(User user) {
     setUser(user);
     setRole(Role.ROLE_ANONYMOUS);
   }
}
