package in.Wishfy.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.Wishfy.entity.Employee;
import in.Wishfy.exception.ResourceNotFoundException;
import in.Wishfy.service.EmployeeService;


class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void testSaveEmployee() throws Exception {
        Employee employee = new Employee();
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(true);

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Employee Saved"));

        verify(employeeService, times(1)).saveEmployee(any(Employee.class));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployee()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(employeeService, times(1)).getAllEmployee();
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testGetEmployeeByIdNotFound() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenThrow(new ResourceNotFoundException("Employee not found"));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isNotFound());

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testDeleteEmployeeById() throws Exception {
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).deleteEmployeeById(1L);
    }

    @Test
    void testUpdateEmployeeById() throws Exception {
        Employee updatedEmployee = new Employee();
        when(employeeService.updateEmployeeById(any(Employee.class), anyLong())).thenReturn(updatedEmployee);

        mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));

        verify(employeeService, times(1)).updateEmployeeById(any(Employee.class), anyLong());
    }

}
