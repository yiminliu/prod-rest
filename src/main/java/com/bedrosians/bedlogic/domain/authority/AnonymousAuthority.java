package com.bedrosians.bedlogic.domain.authority;

import com.bedrosians.bedlogic.domain.user.User;


public class AnonymousAuthority extends Authority {
  private static final long serialVersionUID = 1L;

  public AnonymousAuthority(User user) {
    setUser(user);
    setRole(Role.ROLE_ANONYMOUS);
  }
}