package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ChangeAppointmentDTO {
    private OrderDTO order;
    private AppointmentStatusDTO appoStatus;
}
