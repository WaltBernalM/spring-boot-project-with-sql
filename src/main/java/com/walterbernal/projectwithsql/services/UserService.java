package com.walterbernal.projectwithsql.services;

import java.util.List;

import com.walterbernal.projectwithsql.models.User;

// Blueprint for the 
public interface UserService {
  User saveEmployee(User employee);
  List<User> getAllEmployees();
  User getEmployeeById(long employeeId);
  User updatEmployee(User employee, long id);
  void deleteEmployee(long id);
}

