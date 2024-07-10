package lk.ijse.dto;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemCartTmDTO {
    private String itemId;
    private String itemDesc;
    private Double itemUnitPrice;
    private int itemQty;
    private Double itemTotal;
    private JFXButton btnRemove;
}
