package lk.ijse.DAO.custom;

import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    public  boolean save(Customer entity) throws SQLException, ClassNotFoundException;
    public  boolean update(Customer entity) throws SQLException, ClassNotFoundException;
    public  Customer searchById(String id) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public List<Customer> getAll() throws SQLException, ClassNotFoundException;
    public List<String> getTel() throws SQLException, ClassNotFoundException;
    public int getCusCount() throws SQLException, ClassNotFoundException;
}
