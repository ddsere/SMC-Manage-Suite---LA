package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AppointmentTmDTO {
    private String appId;
    private String cusName;
    private String sName;
    private Date date;
    private String timeSlot;
    private String empName;
}
