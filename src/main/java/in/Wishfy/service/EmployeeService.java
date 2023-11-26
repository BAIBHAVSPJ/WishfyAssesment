package in.Wishfy.service;

import java.util.List;

import in.Wishfy.entity.Employee;

public interface EmployeeService {
	
    public boolean  saveEmployee(Employee empl);
	
	public List<Employee> getAllEmployee();
	
	public Employee getEmployeeById(Long id);
	
	public Employee updateEmployeeById(Employee employee,Long id);
	
	public void deleteEmployeeById(Long id);


}
