package com.walterbernal.projectwithsql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.walterbernal.projectwithsql.exceptions.ResourceNotFoundException;
import com.walterbernal.projectwithsql.models.Employee;
import com.walterbernal.projectwithsql.repository.EmployeeRepository;
import com.walterbernal.projectwithsql.services.EmployeeService;
import com.walterbernal.projectwithsql.services.EmployeeServiceImp;

public class EmployeeServiceTests {
  // Declares the service to be tested
  private EmployeeService employeeService;

  @Mock // delcare of mock EmployeeRepository
  private EmployeeRepository employeeRepository;

  @BeforeEach // before each and every test this is done
  void setUp() {
    // Initializes the mocks in the test class.
    // It's a part of Mockito's setup for test classes.
    MockitoAnnotations.openMocks(this);

    // Instantiatte and implementation of the EmployeeService 
    // with the use of the mock employeeRepository
    employeeService = new EmployeeServiceImp(employeeRepository);
  }

  @Test
  public void testSaveEmployee() {
    // Instatiates a new employee
    Employee employee = new Employee();

    // Configures the behavior of the mock employeeRepository when its called its save() method
    // and returns the employee instance previously created
    when(employeeRepository.save(employee)).thenReturn(employee);

    // Invokes the saveemployee method of the mock employeeRepository and stores the returned employee object in savedEmployee instance
    Employee savedEmployee = employeeService.saveEmployee(employee);

    // Assersts that the savedEmployee instance is not null
    assertNotNull(savedEmployee);

    // Uses Mockito's verify method to check taht the save method  of the employeeRepository was called once with the employee object as an argument
    verify(employeeRepository, times(1)).save(employee);
  }

  @Test 
  public void testGetAllEmployees() {
    // Set of employee mock list of objects
    List<Employee> employeeMockList = List.of(new Employee(), new Employee());

    // When employeeRepository.findAll() is called, returns a list
    when(employeeRepository.findAll()).thenReturn(employeeMockList);

    // Calls the employeeSercvice getAllEmployees method and assigns it to employees List instance
    List<Employee> employees = employeeService.getAllEmployees();

    assertNotNull(employees);
    assertEquals(2, employees.size());
  }

  @Test
  public void testGetEmployeeById() {
    // Create a new employee instance
    Employee employee = new Employee();
    
    // Define the ID of the employee to be retrieved
    long employeeId = 1L;

    // Mock the behavior of the employeeRepository to return an Optional containing
    // the employee when findById(employeeId) is called.
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
    
    // Call the getEmployeeById(employeeId) method of the employeeService.
    Employee employeeFound = employeeService.getEmployeeById(employeeId);

    // Assert that the retrieved employee is not null.
    assertNotNull(employeeFound);

    // Assert that the retrieved employee matches the expected employee (the one
    // provided by the mock).
    assertEquals(employee, employeeFound);
  }

  @Test
  public void testGetEmployeeByIdNotFound() {
    // Define the ID of the employee to be retrieved
    long employeeId = 1L;

    // Mock the behavior of the employeeRepository to return an empty Optional 
    // when findById(employeeId) is called
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

    // Assert that the employee was not found, confirming that throws a ResourceNotFoundException
    assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(employeeId));
  }
  
  @Test
  public void testUpdateEmployee() {
    // Define the ID of the employee to be updated
    long employeeId = 1; 

    // Define the current employee to be updated
    Employee currentEmployee = new Employee();
    currentEmployee.setFirstName("current");

    // define the updated employee
    Employee updatedEmployee = new Employee();
    updatedEmployee.setFirstName("updated");

    // Put method is divided into two parts, find and save
    // Return of Optional currentEmployee when the findById method of employeeRepository is called
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(currentEmployee));

    // Return of Optional currentEmployee when the save method of the employeeRepository is called
    when(employeeRepository.save(currentEmployee)).thenReturn(currentEmployee);

    // Retrieve the employee from the updateEmployee method to verify the result
    Employee result = employeeService.updatEmployee(updatedEmployee, employeeId);

    // assertion that the result is not null
    assertNotNull(result);

    // assertion that the update method of the employeeRepository is actully updating
    assertEquals(updatedEmployee.getfirstName(), result.getfirstName());

    // verification that the save method of the employeeRepository is called 1 time
    verify(employeeRepository, times(1)).save(currentEmployee);
  }
  
  @Test
  public void testUpdateEmployeeNotFound() {
    // Define the ID of the employee to be found
    long employeeId = 2;
    
    // Define the employee data to be updated
    Employee updatedEmployee = new Employee();

    // Mock the behaivor of employeeRepository to return an empty optional
    // when the findById method is called
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

    // Assert that the update method of the employeeRepository is called and returns
    // a ResourceNotFoundException 
    assertThrows(ResourceNotFoundException.class, () -> employeeService.updatEmployee(updatedEmployee, employeeId));
  }

  @Test
  public void testDeleteEmployee() {
    long employeeId = 1;
    Employee employee = new Employee();
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
    employeeService.deleteEmployee(employeeId);
    verify(employeeRepository, times(1)).deleteById(employeeId);
  }

  @Test
  public void testDeleteEmployeeNotFound() {
    long employeeId = 1;
    when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(employeeId));
  }

}
