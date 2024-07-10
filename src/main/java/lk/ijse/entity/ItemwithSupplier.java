package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemwithSupplier {
    private String itemId;
    private String description;
    private String price;
    private String qty;
    private String supId;
    private String supName;
}
