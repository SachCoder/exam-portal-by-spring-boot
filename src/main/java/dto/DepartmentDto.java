package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDto {
	
	private String depart_Id;
	
    @NotBlank(message = "Department Name cannot be blank")
    @Size(max = 100, message = "Department Name cannot be more than 100 characters")
	private String departmentName;

	private EmployeeDto employee;
//	@OneToMany(mappedBy = "department")
//	private List<AttendenceRecord> attendence_record;
//	
}
