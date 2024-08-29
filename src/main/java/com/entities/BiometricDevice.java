package com.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@Entity
@Table(name = "devices")
public class BiometricDevice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String device_id;
	
	private String location;
	
	private String deviceType;
	
	@OneToMany(mappedBy = "biometric_device")
	private List<AttendenceRecord> attendence_record;
	
}
