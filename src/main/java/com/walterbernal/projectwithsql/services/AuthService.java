package com.walterbernal.projectwithsql.services;

import com.walterbernal.projectwithsql.models.Account;
import com.walterbernal.projectwithsql.models.User;

public interface AuthService {
  User signup(User user);
  String login(Account account);
  void logout(User user);
  void authenticate(User user);
}
