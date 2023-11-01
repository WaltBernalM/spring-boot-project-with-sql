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

import com.walterbernal.projectwithsql.models.Employee;
import com.walterbernal.projectwithsql.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
  private EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    super();
    this.employeeService = employeeService;
  }

  @PostMapping()
  public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
    return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
  }

  @GetMapping()
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  // GET Employee by Id
  @GetMapping("{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
    return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
  }

  // PUT Update Employee by Id
  @PutMapping("{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee) {
    return new ResponseEntity<Employee>(employeeService.updatEmployee(employee, employeeId), HttpStatus.OK);
  }

  // DELETE Employee by Id
  @DeleteMapping("{id}")
  public ResponseEntity<String> updateEmployee(@PathVariable("id") long id) {
    employeeService.deleteEmployee(id);
    return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
  }
}
