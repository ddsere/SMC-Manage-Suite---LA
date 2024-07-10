package lk.ijse.dto;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemTmDTO {
    private String itemId;
    private String description;
    private Double price;
    private int qty;
    private JFXButton btnItemCartRemove;
}
