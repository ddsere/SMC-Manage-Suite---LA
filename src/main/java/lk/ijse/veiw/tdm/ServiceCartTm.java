package lk.ijse.veiw.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ServiceCartTm {
    private String sId;
    private String sDesc;
    private Double sPrice;
    private JFXButton btnRemove;
}
