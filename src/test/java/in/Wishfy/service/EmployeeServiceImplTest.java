package in.Wishfy.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.Wishfy.entity.Employee;
import in.Wishfy.repository.EmployeeRepository;

class EmployeeServiceImplTest {
	 @Mock
	 private EmployeeRepository employeeRepository;

	 @InjectMocks
	 private EmployeeService employeeService = new EmployeeServiceImpl();

	 @BeforeEach
	 void init() {
	 MockitoAnnotations.openMocks(this);
	    }


	@Test
	void testSaveEmployee() {
		
		 Employee employee = new Employee();
	     when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());

	     boolean result = employeeService.saveEmployee(employee);

	     assertTrue(result);
	     verify(employeeRepository, times(1)).save(any(Employee.class));
		
	}

	@Test
	void testGetAllEmployee() {
		 List<Employee> employees = new ArrayList<>();
	     when(employeeRepository.findAll()).thenReturn(employees);

	     List<Employee> result = employeeService.getAllEmployee();

	     assertEquals(employees, result);
	     verify(employeeRepository, times(1)).findAll();
	}

	@Test
	void testGetEmployeeById() {
		 Employee employee = new Employee();
	     when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

	     Employee result = employeeService.getEmployeeById(1L);

	     assertEquals(employee, result);
	     verify(employeeRepository, times(1)).findById(anyLong());
	}

	@Test
	void testUpdateEmployeeById() {
		 Employee existingEmployee = new Employee();
	     Employee updatedEmployee = new Employee();
	     when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(existingEmployee));
	     when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

	     Employee result = employeeService.updateEmployeeById(updatedEmployee, 1L);

	     assertEquals(updatedEmployee, result);
	     verify(employeeRepository, times(1)).findById(anyLong());
	     verify(employeeRepository, times(1)).save(any(Employee.class));
	}

	@Test
	void testDeleteEmployeeById() {
		 when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(new Employee()));

	     employeeService.deleteEmployeeById(1L);

	     verify(employeeRepository, times(1)).findById(anyLong());
	     verify(employeeRepository, times(1)).delete(any(Employee.class));
	}

}
