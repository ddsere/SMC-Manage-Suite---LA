package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ServiceWithEmployeeDAO;
import lk.ijse.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicewithEmployeeDAOImpl implements ServiceWithEmployeeDAO {
    public List<ServicewithEmployee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT s.*, e.Name FROM Service s INNER JOIN Employee e ON s.Emp_Id = e.Emp_Id;");

        List<ServicewithEmployee> servicewithEmployeeList = new ArrayList<>();
        while (resultSet.next()) {
            String serviceId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String empId = resultSet.getString(4);
            String empName = resultSet.getString(5);

            ServicewithEmployee servicewithEmployee = new ServicewithEmployee(serviceId,description,price,empId,empName);
            servicewithEmployeeList.add(servicewithEmployee);
        }
        return servicewithEmployeeList;
    }

    public ServicewithEmployee searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT s.*, e.Name FROM Service s INNER JOIN Employee e ON s.Emp_Id = e.Emp_Id WHERE S_Id = ?");

        ServicewithEmployee servicewithEmployee = null;

        if (resultSet.next()) {
            String sId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String price = resultSet.getString(3);
            String empId = resultSet.getString(4);
            String empName = resultSet.getString(5);

            servicewithEmployee = new ServicewithEmployee(sId, description, price, empId,empName);
        }
        return servicewithEmployee;
    }
}
