package com.walterbernal.projectwithsql;

import com.walterbernal.projectwithsql.models.Employee;
import com.walterbernal.projectwithsql.services.EmployeeService;
import com.walterbernal.projectwithsql.controllers.EmployeeController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTests {
  private EmployeeController employeeController;
  private EmployeeService employeeService;
  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    employeeService = Mockito.mock(EmployeeService.class);
    employeeController = new EmployeeController(employeeService);
    mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
  }

  @Test
  public void testSaveEmployee() throws Exception {
    Employee employee = new Employee();
    employee.setId(1L);
    employee.setFirstName("John");
    employee.setLastName("Doe");
    employee.setEmail("jdoe@example.com");
    when(employeeService.saveEmployee(employee)).thenReturn(employee);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"jdoe@example.com\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"))
        .andExpect(jsonPath("$.email").value("jdoe@example.com"));
  }
  
  @Test
  public void testGetAllEmployees() throws Exception {
    List<Employee> employees = Arrays.asList(new Employee(), new Employee());
    when(employeeService.getAllEmployees()).thenReturn(employees);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2));
  }

  @Test
  public void testGetEmployeeById() throws Exception {
    Employee employee = new Employee();
    employee.setId(1L);
    employee.setFirstName("John");
    employee.setLastName("Doe");
    employee.setEmail("jdoe@gmail.com");

    when(employeeService.getEmployeeById(1L)).thenReturn(employee);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.firstName").value("John"));
  }
}