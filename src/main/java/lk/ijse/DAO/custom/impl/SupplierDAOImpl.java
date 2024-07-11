package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.SupplierDAO;
import lk.ijse.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Supplier VALUES(?, ?, ?)",
                entity.getSupId(),
                entity.getTel(),
                entity.getName());
    }

    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Supplier");

        List<Supplier> supplierList = new ArrayList<>();
        while (resultSet.next()) {
            String supId = resultSet.getString(1);
            String tel = resultSet.getString(2);
            String name = resultSet.getString(3);

            Supplier supplier = new Supplier(supId, name, tel);
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Supplier WHERE Sup_Id = ?", id);
    }

    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Supplier SET Name = ?, Phone = ? WHERE Sup_Id = ?",
                entity.getName(),
                entity.getTel(),
                entity.getSupId());
    }

    public Supplier searchById(String supId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Supplier WHERE Sup_Id = ?", supId);

        Supplier supplier = null;

        if (resultSet.next()) {
            String Id = resultSet.getString(1);
            String tel = resultSet.getString(2);
            String name = resultSet.getString(3);

            supplier = new Supplier(Id, name, tel);
        }
        return supplier;
    }

    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT Sup_Id FROM Supplier");

        List<String> idList = new ArrayList<>();

        while(resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
