package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.PlaceOrderDTO;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    public boolean placeOrder(PlaceOrderDTO po) throws SQLException;
}
