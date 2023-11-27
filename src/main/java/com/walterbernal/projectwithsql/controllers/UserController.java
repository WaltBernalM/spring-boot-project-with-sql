package com.walterbernal.projectwithsql.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walterbernal.projectwithsql.models.User;
import com.walterbernal.projectwithsql.services.UserService;

import java.util.List;

@RestController // Assigns the RestController Annotation
@RequestMapping("/api/employees") // Englobes the /api/emmployees endpoint responses
public class UserController {
  private UserService employeeService;

   // Constructs an instance of the EmployeeController class with the provided
   // EmployeeService.
  public UserController(UserService employeeService) {
    super();
    this.employeeService = employeeService;
  }

  // Create and save a new employee by processing the provided request body.
  @PostMapping()
  public ResponseEntity<User> saveEmployee(@RequestBody User employee) {
    return new ResponseEntity<User>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
  }

  // Retrieves a List of all employees in the database
  @GetMapping()
  public List<User> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  // Retrieves an employee by its ID from the List of all employees in the database
  @GetMapping("{id}")
  public ResponseEntity<User> getEmployeeById(@PathVariable("id") long employeeId) {
    return new ResponseEntity<User>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
  }

  // Updates an employee by its ID from the List of all employees in the database
  @PutMapping("{id}")
  public ResponseEntity<User> updateEmployee(@PathVariable("id") long employeeId, @RequestBody User employee) {
    return new ResponseEntity<User>(employeeService.updatEmployee(employee, employeeId), HttpStatus.OK);
  }

  // Deletes an employee by its ID from the List of all employees in the database
  @DeleteMapping("{id}")
  public ResponseEntity<String> updateEmployee(@PathVariable("id") long id) {
    employeeService.deleteEmployee(id);
    return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
  }
}
