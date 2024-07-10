package lk.ijse.DAO.custom;

import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAll() throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException;
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException;
    public Employee searchById(String id) throws SQLException, ClassNotFoundException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;
}
