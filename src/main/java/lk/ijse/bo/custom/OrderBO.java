package lk.ijse.bo.custom;

import lk.ijse.dto.OrderDTO;
import lk.ijse.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderBO {
    public String currentId() throws SQLException, ClassNotFoundException;
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException;
    public List<OrderDTO> getAll() throws SQLException, ClassNotFoundException;
    public int getOrderCount() throws SQLException, ClassNotFoundException;
}
