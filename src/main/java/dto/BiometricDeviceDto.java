package dto;

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
public class BiometricDeviceDto {
	
	private String device_id;
	
	private String location;
	
	private String deviceType;
	
	private List<AttendenceRecordDto> attendence_record;
	
}
