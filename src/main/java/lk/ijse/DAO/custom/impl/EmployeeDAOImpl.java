package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.EmployeeDAO;
import lk.ijse.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Employee");

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String salary = resultSet.getString(3);
            String address = resultSet.getString(4);
            String phone = resultSet.getString(5);

            Employee employee = new Employee(id, name, salary, address, phone);
            employeeList.add(employee);
        }
        return employeeList;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Employee WHERE Emp_Id = ?", id);
    }

    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Employee VALUES(?, ?, ?, ?, ?)",
                entity.getId(),
                entity.getName(),
                entity.getSalary(),
                entity.getAddress(),
                entity.getPhone());
    }

    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Employee SET Name = ?, Salary = ?, Address = ?, Phone = ? WHERE Emp_Id = ?",
                entity.getName(),
                entity.getSalary(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getId());
    }

    public Employee searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Employee WHERE Emp_Id = ?", id);

        Employee employee = null;

        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String salary = resultSet.getString(3);
            String address = resultSet.getString(4);
            String phone = resultSet.getString(5);

            employee = new Employee(empId, name, salary, address, phone);
        }
        return employee;
    }

    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT Emp_Id FROM Employee");

        List<String> idList = new ArrayList<>();

        while(resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
