package lk.ijse.DAO.custom;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDAO {
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException;
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException;
    public Supplier searchById(String supId) throws SQLException, ClassNotFoundException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;
}
