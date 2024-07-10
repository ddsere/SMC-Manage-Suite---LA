package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ServiceDAO;
import lk.ijse.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Service WHERE S_Id = ?", id);
    }

    public boolean save(Service entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Service VALUES(?, ?, ?, ?)",
                entity.getServiceId(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getEmpId());
    }

    public boolean update(Service entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Service SET Emp_Id = ?, Name = ?, Price = ? WHERE S_Id = ?",
                entity.getEmpId(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getServiceId());
    }

    public Service searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Service WHERE S_Id = ?", id);

        Service service = null;

        if (resultSet.next()) {
            String sId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String empId = resultSet.getString(4);

            service = new Service(sId, description, price, empId);
        }
        return service;
    }

    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT S_Id FROM Service");

        List<String> idList = new ArrayList<>();

        while(resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public double getPrice(String servId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT Price FROM Service WHERE S_Id = ?", servId);

        double price = 0;
        while (resultSet.next()) {
            price = resultSet.getDouble(1);
        }
        return price;
    }
}
