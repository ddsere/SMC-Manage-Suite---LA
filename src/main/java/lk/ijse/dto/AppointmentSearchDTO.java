package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AppointmentSearchDTO {
    private String appId;
    private LocalDate date;
    private String sId;
    private String empId;
    private String ts;
    private String cusName;
    private String empName;
    private String sName;

}
