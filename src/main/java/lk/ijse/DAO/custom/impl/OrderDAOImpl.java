package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.OrderDAO;
import lk.ijse.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT Order_Id FROM Orders ORDER BY Order_Id desc LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Orders VALUES(?, ?, ?, ?, ?)",
                entity.getOrderId(),
                entity.getDate(),
                entity.getAmount(),
                entity.getCusPhone(),
                entity.getCusName());
    }

    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Orders");

        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            Date date = resultSet.getDate(2);
            double amout = resultSet.getDouble(3);
            String phone = resultSet.getString(4);
            String name = resultSet.getString(5);

            Order order = new Order(id, date, amout, phone, name);
            orderList.add(order);
        }
        return orderList;
    }

    public int getOrderCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT COUNT(*) AS orderCount FROM Orders");

        int orderCount = 0;
        if(resultSet.next()) {
            orderCount = resultSet.getInt("orderCount");
        }
        return orderCount;
    }
}
