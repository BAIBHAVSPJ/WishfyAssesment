package in.Wishfy.service;

import java.util.List;

import in.Wishfy.entity.Department;

public interface DepartmentService {
	
	 public Department addDepartment(Department department);
	 
	 
	 public List<Department> getAllDepartments();
	 
	 
	 public Department getDepartmentById(Long id);
	 
	 public Department updateDepartment(Long id, Department updatedDepartment);
	 
	 public void deleteDepartment(Long id);
	

}
