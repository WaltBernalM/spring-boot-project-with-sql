package com.walterbernal.projectwithsql.services;

import java.util.List;

import com.walterbernal.projectwithsql.models.Employee;

// Blueprint for the 
public interface EmployeeService {
  Employee saveEmployee(Employee employee);
  List<Employee> getAllEmployees();
  Employee getEmployeeById(long employeeId);
  Employee updatEmployee(Employee employee, long id);
  void deleteEmployee(long id);
}

