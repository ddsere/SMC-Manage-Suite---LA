package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ItemWithSupplierDAO;
import lk.ijse.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemwithSupplierDAOImpl implements ItemWithSupplierDAO {
    public List<ItemwithSupplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute( "SELECT i.*, s.Name AS SupplierName FROM Item i INNER JOIN Supplier s ON i.Sup_Id = s.Sup_Id;");

        List<ItemwithSupplier> itemwithSupplierList = new ArrayList<>();
        while (resultSet.next()) {
            String itemId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String supId = resultSet.getString(5);
            String supName = resultSet.getString(6);

            ItemwithSupplier itemwithSupplier = new ItemwithSupplier(itemId,description,price,qty,supId,supName);
            itemwithSupplierList.add(itemwithSupplier);
        }
        return itemwithSupplierList;
    }

    public ItemwithSupplier searchById(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT i.*, s.Name AS SupplierName FROM Item i INNER JOIN Supplier s ON i.Sup_Id = s.Sup_Id WHERE Item_Id = ?");

        ItemwithSupplier itemwithSupplier = null;

        if (resultSet.next()) {
            String itemCode = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String supId = resultSet.getString(5);
            String supName = resultSet.getString(6);

            itemwithSupplier = new ItemwithSupplier(itemCode, description, price, qty, supId, supName);
        }
        return itemwithSupplier;
    }
}
