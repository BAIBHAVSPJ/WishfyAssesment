package in.Wishfy.entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="EMPLOYEE")
@Entity
public class Employee{
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotBlank(message = "Employee name is required")
		private String name;
		
	    @Email(message = "Invalid email format")
	    @NotBlank(message = "Email is required")
		private String email;
		
		private Date joiningDate;
		
		private String createdBy;
		
		private String updatedBy;
		
		@Column(name ="CREATED_DATE",updatable=false)
		@CreationTimestamp
		private LocalDate createDate;
		
		@Column(name ="UPDATED_DATE",insertable=false)
		@UpdateTimestamp
		private LocalDate updateDate;
		

	    @ManyToOne
		@JoinColumn(name = "department_id")
	    private Department department;

}
