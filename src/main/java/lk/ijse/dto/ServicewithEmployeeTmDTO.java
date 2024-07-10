package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ServicewithEmployeeTmDTO {
    private String serviceId;
    private String description;
    private String price;
    private String empId;
    private String empName;
}
