package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.CustomerDAO;
import lk.ijse.entity.Customer;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    public  boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Customer VALUES(?, ?, ?)",
                entity.getTel(),
                entity.getName(),
                entity.getAddress());
    }

    public  boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Customer SET Name = ?, Address = ? WHERE Phone = ?",
                entity.getName(),
                entity.getAddress(),
                entity.getTel());
    }

    public  Customer searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Customer WHERE Phone = ?",
                id);

        Customer customer = null;

        if (resultSet.next()) {
            String tel = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);

            customer = new Customer(name, address, tel);
        }
        return customer;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Customer WHERE Phone = ?", id);
    }

    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Customer");

        List<Customer> customersList = new ArrayList<>();
        while (resultSet.next()) {
            String tel = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);

            Customer customer = new Customer(name, address, tel);
            customersList.add(customer);
        }
        return customersList;
    }

    public List<String> getTel() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT Phone FROM Customer");

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public int getCusCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT COUNT(*) AS cusCount FROM Customer");

        int cusCount = 0;
        if(resultSet.next()) {
            cusCount = resultSet.getInt("cusCount");
        }
        return cusCount;
    }
}
