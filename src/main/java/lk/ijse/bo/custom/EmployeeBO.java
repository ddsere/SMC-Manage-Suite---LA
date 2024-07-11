package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    public EmployeeDTO searchById(String id) throws SQLException, ClassNotFoundException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;
}
