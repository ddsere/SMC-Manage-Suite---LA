package lk.ijse.DAO.custom;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.entity.Item;
import lk.ijse.entity.ItemQty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean save(Item entity) throws SQLException, ClassNotFoundException;
    public boolean update(Item entity) throws SQLException, ClassNotFoundException;
    public Item searchById(String code) throws SQLException, ClassNotFoundException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;
    public boolean updateQty(List<ItemQty> itemQties) throws SQLException, ClassNotFoundException;
    public boolean updateQty(ItemQty entity) throws SQLException, ClassNotFoundException;
}
