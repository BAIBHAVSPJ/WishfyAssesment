package in.Wishfy.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import in.Wishfy.entity.Employee;
import in.Wishfy.exception.ResourceNotFoundException;
import in.Wishfy.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
	private EmployeeRepository empRepo;
    
    
	@Override
	public boolean saveEmployee(Employee empl) {
		Employee save = empRepo.save(empl);
		return save.getId()!=null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		
		return empRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with given id not found !!"));
		
	}

	@Override
	public Employee updateEmployeeById(Employee employee, Long id) {
		Employee emp = empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with given id not found !!"));
		emp.setName(employee.getName());
		emp.setEmail(employee.getEmail());
		emp.setDepartment(employee.getDepartment());
		emp.setJoiningDate(employee.getJoiningDate());
		return empRepo.save(emp);
	}

	@Override
	public void deleteEmployeeById(Long id) {

		Employee emp = empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with given id not found !!"));
		empRepo.delete(emp);
	}

}
