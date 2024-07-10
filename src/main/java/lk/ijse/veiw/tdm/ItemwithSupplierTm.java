package lk.ijse.veiw.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemwithSupplierTm {
    private String itemId;
    private String description;
    private String price;
    private String qty;
    private String supId;
    private String supName;
}
