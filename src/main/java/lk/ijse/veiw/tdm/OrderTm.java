package lk.ijse.veiw.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderTm {
    private String orderId;
    private Date date;
    private String cusName;
    private String cusPhone;
    private double amount;
}
