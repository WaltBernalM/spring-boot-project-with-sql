package com.walterbernal.projectwithsql.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.walterbernal.projectwithsql.models.Account;
import com.walterbernal.projectwithsql.models.User;

public class AuthServiceImp implements AuthService {
  private UserService userService;
  private static final String SECRET_KEY = "keyboad cat";

  @Override
  public User signup(User user) {
    return userService.saveEmployee(user);
  }

  @Override
  public String login(Account account) {
    if (account.authenticate(account.getPassword(), account.getEmail())) {
      return "JWT";
    }
    else {
      return "";
    }
  }

  @Override
  public void logout(User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'logout'");
  }

  @Override
  public void authenticate(User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
  }
  

  private String generateJWT(Account account) {
    byte[] keyBytes = SECRET_KEY.getBytes();
    //  key = Keys.hmacShaKeyFor(keyBytes);
    return "";
  }
}
