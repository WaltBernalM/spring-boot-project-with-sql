package com.walterbernal.projectwithsql.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walterbernal.projectwithsql.exceptions.ResourceNotFoundException;
import com.walterbernal.projectwithsql.models.User;
import com.walterbernal.projectwithsql.repository.UserRepository;

// Implementation of the EmployeeService interface
// And interaction with the EmployeeRepository (repository) that extends the Java Persistence API
@Service
public class UserServiceImp implements UserService {
  private UserRepository employeeRepository;
  
  // Constructor for EmployeeServiceImp
  // Injects the dependency ...
  public UserServiceImp(UserRepository employeeRepository) {
    super();
    this.employeeRepository = employeeRepository;
  }

  // Service method to saveEmployee data
  @Override
  public User saveEmployee(User employee) {
    // call of save() method extended from the Java Persistence API, to save the employee data
    // return is never null
    return employeeRepository.save(employee);
  }

  // Service method to retrieve all the Employee in a List
  @Override
  public List<User> getAllEmployees() {
    // call of findAll() method extended from the Java Persistence API 
    // to retrieve all the Employee as List of Employee
    return employeeRepository.findAll();
  }

  @Override
  public User getEmployeeById(long id) {
    // Optional<Employee> foundEmployee = employeeRepository.findById(id);
    // if (foundEmployee.isPresent()) {
    //   return foundEmployee.get();
    // } else {
    //   throw new ResourceNotFoundException("Employee", "Id", id);
    //   return null;
    // }

    return employeeRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
  }

  @Override
  public User updatEmployee(User employee, long id) {
    // Optional<Employee> foundEmployee = employeeRepository.findById(id);
    // if (foundEmployee.isPresent()) {
    //   Employee existingEmployee = foundEmployee.get();
    //   existingEmployee.setFirstName(employee.getfirstName());
    //   existingEmployee.setLastName(employee.getLastName());
    //   existingEmployee.setEmail(employee.getEmail());
    //   employeeRepository.save(existingEmployee);
    //   return existingEmployee;
    // } else {
    //   return null; // Avoid to utilize ( can cause null pointer exception)
    // }
    
    User existingEmployee = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

    existingEmployee.setFirstName(employee.getFirstName());
    existingEmployee.setLastName(employee.getLastName());
    existingEmployee.setEmail(employee.getEmail());

    employeeRepository.save(existingEmployee);
    return existingEmployee;
  }

  @Override
  public void deleteEmployee(long id) {
    // Optional<Employee> foundEmployee = employeeRepository.findById(id);
    // if (foundEmployee.isPresent()) {
    //   employeeRepository.delete(foundEmployee.get());
    // }

    employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    employeeRepository.deleteById(id);
  }
}
