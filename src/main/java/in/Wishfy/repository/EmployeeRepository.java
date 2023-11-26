package in.Wishfy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.Wishfy.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
