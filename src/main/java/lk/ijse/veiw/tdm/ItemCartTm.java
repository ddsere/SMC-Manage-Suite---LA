package lk.ijse.veiw.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemCartTm {
    private String itemId;
    private String itemDesc;
    private Double itemUnitPrice;
    private int itemQty;
    private Double itemTotal;
    private JFXButton btnRemove;
}
