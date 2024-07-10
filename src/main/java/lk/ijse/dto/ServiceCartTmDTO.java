package lk.ijse.dto;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ServiceCartTmDTO {
    private String sId;
    private String sDesc;
    private Double sPrice;
    private JFXButton btnRemove;
}
