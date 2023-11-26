package in.Wishfy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.Wishfy.entity.Employee;
import in.Wishfy.payloads.ApiResponse;
import in.Wishfy.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	
	@PostMapping()
	public ResponseEntity<String> saveEmployee( @Validated @RequestBody Employee empl){
		String responseMsg = "";
		boolean saveEmployee = empService.saveEmployee(empl);
		if(saveEmployee) {
			
			responseMsg ="Employee Saved";
		}
		else {
			
			responseMsg ="Employee Not Saved";
		}
		return new ResponseEntity<>(responseMsg,HttpStatus.CREATED);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<Employee>> Employee(){
		
		 List<Employee> allEmployee = empService.getAllEmployee();
		return new ResponseEntity<>(allEmployee,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee>getEmployeeById(@PathVariable Long id){
		
		Employee employee = empService.getEmployeeById(id);
		
		 if (employee != null) {
	            return new ResponseEntity<>(employee, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		
		//return new ResponseEntity<>(employee,HttpStatus.OK);
		// return empService.getEmployeeById(id)
//	                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
//	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}
	
	 @DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse>deleteEmployeeById(@PathVariable Long id){
		empService.deleteEmployeeById(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("message",true),HttpStatus.NO_CONTENT);
		
	}
	 @PutMapping("/{id}")
	 public ResponseEntity<Employee> updateEmployeeById( @Validated @RequestBody Employee employee,@PathVariable Long id){
		 
		 Employee updateEmployee = empService.updateEmployeeById(employee, id);
		return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
		 
	 }

}
