package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ItemDAO;
import lk.ijse.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute( "DELETE FROM Item WHERE Item_Id = ?", id);
    }

    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Item VALUES(?, ?, ?, ?, ?)",
                entity.getItemId(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getQty(),
                entity.getSupId());
    }

    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Item SET Sup_Id = ?, Description = ?, Price = ?, Qty = ? WHERE Item_Id = ?",
                entity.getSupId(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getQty(),
                entity.getItemId());
    }

    public Item searchById(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Item WHERE Item_Id = ?", code);

        Item item = null;

        if (resultSet.next()) {
            String itemCode = resultSet.getString(1);
            String description = resultSet.getString(2);
            Double price = Double.valueOf(resultSet.getString(3));
            String qty = resultSet.getString(4);
            String supId = resultSet.getString(5);

            item = new Item(itemCode, description, price, qty, supId);
        }
        return item;
    }

    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT Item_Id FROM Item");

        List<String> idList = new ArrayList<>();

        while(resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public boolean updateQty(List<ItemQty> itemQties) throws SQLException, ClassNotFoundException {
        for (ItemQty od : itemQties) {
            if(!updateQty(od)) {
                return false;
            }
        }
        return true;
    }
    public boolean updateQty(ItemQty entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE item SET Qty = Qty - ? WHERE Item_Id = ?",
                entity.getQty(),
                entity.getItemCode());
    }
}
