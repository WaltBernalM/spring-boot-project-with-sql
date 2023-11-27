package com.walterbernal.projectwithsql.models;

public interface Account {
  String getEmail();
  String getPassword();
  boolean getIsAdmin();
  boolean authenticate(String password, String email);
}
