package in.Wishfy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.Wishfy.entity.Department;
import in.Wishfy.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	 @Autowired
	 private DepartmentService departmentService;

	    @PostMapping
	    public Department addDepartment(@RequestBody Department department) {
	        return departmentService.addDepartment(department);
	    }

	    @GetMapping
	    public List<Department> getAllDepartments() {
	        return departmentService.getAllDepartments();
	    }

	    @GetMapping("/{id}")
	    public Department getDepartmentById(@PathVariable Long id) {
	        return departmentService.getDepartmentById(id);
	    }

	    @PutMapping("/{id}")
	    public Department updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
	        return departmentService.updateDepartment(id, updatedDepartment);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteDepartment(@PathVariable Long id) {
	        departmentService.deleteDepartment(id);
	    }
	
	

}
