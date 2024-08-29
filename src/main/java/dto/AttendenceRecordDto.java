package dto;

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
public class AttendenceRecordDto {
	
	
	private String record_id;
	
	private EmployeeDto employee;
	
	private BiometricDeviceDto biometric_device;
	
	private ShiftDto shift;
	
	private LocalDate date;
	
	private LocalTime timeIn;
	
	private LocalTime timeOut;
	
	private String status;
	
}
