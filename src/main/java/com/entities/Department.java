package com.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String depart_Id;

	private String departmentName;
	
	@OneToOne
	@JoinColumn(name = "employee_emp_id", referencedColumnName = "emp_id")
	private Employee employee;
//	@OneToMany(mappedBy = "department")
//	private List<AttendenceRecord> attendence_record;
//	
}
