package com.entities;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Shift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String shift_id;

	private String shiftName;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	@OneToMany(mappedBy = "shift")
	private List<AttendenceRecord> attendence_record;
	
	@OneToMany(mappedBy = "shift")
	private List<Employee> employee;
}
