package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ServicewithEmployee {
    private String serviceId;
    private String description;
    private String price;
    private String empId;
    private String empName;
}
