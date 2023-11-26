package in.Wishfy.entity;


import java.time.LocalDate;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;
@Data
@Table(name="DEPARTMENT")
@Entity
public class Department   {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String name;
    
    private String createdBy;
    
	private String updatedBy;
	
	@Column(name ="CREATED_DATE",updatable=false)
	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name ="UPDATED_DATE",insertable=false)
	@UpdateTimestamp
	private LocalDate updateDate;
	

    //@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  //  private Set<Employee> employees;


}
