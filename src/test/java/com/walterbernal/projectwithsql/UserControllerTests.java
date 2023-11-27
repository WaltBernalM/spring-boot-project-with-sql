package com.walterbernal.projectwithsql;

import com.walterbernal.projectwithsql.models.User;
import com.walterbernal.projectwithsql.services.UserService;
import com.walterbernal.projectwithsql.controllers.UserController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTests {
  private UserController employeeController;
  private UserService employeeService;
  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    employeeService = Mockito.mock(UserService.class);
    employeeController = new UserController(employeeService);
    mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
  }

  @Test
  public void testSaveEmployee() throws Exception {
    User employee = new User();
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
    List<User> employees = Arrays.asList(new User(), new User());
    when(employeeService.getAllEmployees()).thenReturn(employees);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2));
  }

  @Test
  public void testGetEmployeeById() throws Exception {
    User employee = new User();
    employee.setId(1L);
    employee.setFirstName("John");
    employee.setLastName("Doe");
    employee.setEmail("jdoe@gmail.com");

    when(employeeService.getEmployeeById(anyLong())).thenReturn(employee);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"))
        .andExpect(jsonPath("$.email").value("jdoe@gmail.com"));
  }

  @Test
  public void testUpdateEmployee() throws Exception {
    User employee = new User();
    employee.setId(1L);
    employee.setFirstName("John");
    employee.setLastName("Doe");
    employee.setEmail("jdoe@gmail.com");

    when(employeeService.updatEmployee(any(), anyLong())).thenReturn(employee);

    mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"jdoe@gmail.com\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"))
        .andExpect(jsonPath("$.email").value("jdoe@gmail.com"));
  }

  @Test
  public void testDeleteEmployee() throws Exception {
    // Define the ID of the employee to be deleted
    Long employeeId = 1L;
    
    // Mock the behavior of the employeeService to do nothing when
    // deleteEmployee(employeeId) is called
    doNothing().when(employeeService).deleteEmployee(anyLong());

    // Perform a DELETE request to the "/api/employees/{id}" endpoint, providing the
    // employee ID and content type
    mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/employees/{id}", employeeId)
        .contentType(MediaType.APPLICATION_JSON))
        // Verify that the HTTP status code of the response is 200 (OK)
        .andExpect(status().isOk());

    // Verify that the deleteEmployee(employeeId) method was called once in the
    // employeeService.
    verify(employeeService, times(1)).deleteEmployee(anyLong());
  }
}
