package lk.ijse.DAO.custom;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.entity.Order;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDAO {
    public String currentId() throws SQLException, ClassNotFoundException;
    public boolean save(Order entity) throws SQLException, ClassNotFoundException;
    public List<Order> getAll() throws SQLException, ClassNotFoundException;
    public int getOrderCount() throws SQLException, ClassNotFoundException;
}
