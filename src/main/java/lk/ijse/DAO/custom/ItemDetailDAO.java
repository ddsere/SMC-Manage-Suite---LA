package lk.ijse.DAO.custom;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.entity.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ItemDetailDAO {
    public boolean save(List<ItemQty> itemQties , Order entity) throws SQLException, ClassNotFoundException;
    public boolean save( ItemQty od, Order order) throws SQLException, ClassNotFoundException;
}
