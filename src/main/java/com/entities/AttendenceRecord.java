package com.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="records")
public class AttendenceRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String record_id;
	
	@ManyToOne
	@JoinColumn(name = "employee_emp_id", referencedColumnName = "emp_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "devices_device_id", referencedColumnName = "device_id")
	private BiometricDevice biometric_device;
	
	@ManyToOne
	@JoinColumn(name = "shift_shift_id", referencedColumnName = "shift_id")
	private Shift shift;
	
	private LocalDate date;
	
	private LocalTime timeIn;
	
	private LocalTime timeOut;
	
	private String status;
	
}
