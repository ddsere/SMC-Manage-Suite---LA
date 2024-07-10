package lk.ijse.veiw.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ServicewithEmployeeTm {
    private String serviceId;
    private String description;
    private String price;
    private String empId;
    private String empName;
}
