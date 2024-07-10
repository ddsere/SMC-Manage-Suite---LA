package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.entity.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemDetailDAOImpl {
    public boolean save(List<ItemQty> itemQties , Order entity) throws SQLException, ClassNotFoundException {
        for (ItemQty od : itemQties) {
            if(!save(od,entity)) {
                return false;
            }
        }
        return true;
    }

    public boolean save( ItemQty od, Order order) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO item_orders VALUES(?, ?)",
                order.getOrderId(),
                od.getItemCode());
    }
}
