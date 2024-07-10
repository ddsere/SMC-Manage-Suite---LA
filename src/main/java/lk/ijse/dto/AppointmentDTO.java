package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AppointmentDTO {
    private String appId;
    private LocalDate date;
    private String cusPhone;
    private String servId;
    private String empId;
    private String timeSlot;
    private double price;
}
