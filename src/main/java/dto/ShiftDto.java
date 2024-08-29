package dto;

import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShiftDto {

    private String shift_id;

    @NotBlank(message = "Shift Name cannot be blank")
    @Size(max = 100, message = "Shift Name cannot be more than 100 characters")
    private String shiftName;

    @NotNull(message = "Start Time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End Time cannot be null")
    private LocalTime endTime;

    private List<AttendenceRecordDto> attendence_record;

    private List<EmployeeDto> employee;
}
