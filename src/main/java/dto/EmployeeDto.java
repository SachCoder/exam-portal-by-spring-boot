package dto;

import java.util.List;
import java.util.Optional;

import com.entities.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	@NotNull(message = "Employee Id cannot be null")
    private int emp_id;

    @NotNull(message = "Employee name cannot be null")
    @Size(min = 2, max = 100, message = "Employee name must be between 2 and 100 characters")
    private String emp_name;

    private DepartmentDto department;

    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;
     
    
    private UserCreateDto user;
    
    private ShiftDto shift;
    
    private List<AttendenceRecordDto> attendence_record;
}
