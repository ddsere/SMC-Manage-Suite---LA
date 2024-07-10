package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ServiceDTO {
    private String serviceId;
    private String description;
    private String price;
    private String empId;
}
