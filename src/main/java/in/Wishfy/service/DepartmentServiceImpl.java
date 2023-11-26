package in.Wishfy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.Wishfy.entity.Department;
import in.Wishfy.exception.ResourceNotFoundException;
import in.Wishfy.repository.DepartmentRepository;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	 @Autowired
	 private DepartmentRepository departmentRepository;

	@Override
	public Department addDepartment(Department department) {
		
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		 return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(Long id) {
		return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department with given id not found !!"));
	}

	@Override
	public Department updateDepartment(Long id, Department updatedDepartment) {
		 Department existingDepartment = getDepartmentById(id);

	        existingDepartment.setName(updatedDepartment.getName());

	        return departmentRepository.save(existingDepartment);
	}

	@Override
	public void deleteDepartment(Long id) {
		 departmentRepository.deleteById(id);
		
	}

}
