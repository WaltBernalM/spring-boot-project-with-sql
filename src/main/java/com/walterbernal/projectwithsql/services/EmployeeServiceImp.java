package com.walterbernal.projectwithsql.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walterbernal.projectwithsql.exceptions.ResourceNotFoundException;
import com.walterbernal.projectwithsql.models.Employee;
import com.walterbernal.projectwithsql.repository.EmployeeRepository;

// Implementation of the EmployeeService interface
@Service
public class EmployeeServiceImp implements EmployeeService {
  private EmployeeRepository employeeRepository;
  
  public EmployeeServiceImp(EmployeeRepository employeeRepository) {
    super();
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Employee saveEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee getEmployeeById(long id) {
    // Optional<Employee> foundEmployee = employeeRepository.findById(id);
    // if (foundEmployee.isPresent()) {
    //   return foundEmployee.get();
    // } else {
      // throw new ResourceNotFoundException("Employee", "Id", id);
    //   return null;
    // }

    return employeeRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
  }

  @Override
  public Employee updatEmployee(Employee employee, long id) {
    // Optional<Employee> foundEmployee = employeeRepository.findById(id);
    // if (foundEmployee.isPresent()) {
    //   Employee existingEmployee = foundEmployee.get();
    //   existingEmployee.setFirstName(employee.getfirstName());
    //   existingEmployee.setLastName(employee.getLastName());
    //   existingEmployee.setEmail(employee.getEmail());
    //   employeeRepository.save(existingEmployee);
    //   return existingEmployee;
    // } else {
    //   return null;
    // }
    
    Employee existingEmployee = employeeRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    
    existingEmployee.setFirstName(employee.getfirstName());
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
