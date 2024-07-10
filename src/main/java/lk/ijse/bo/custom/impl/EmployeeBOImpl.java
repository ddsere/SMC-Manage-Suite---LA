package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.CustomerDAO;
import lk.ijse.DAO.custom.EmployeeDAO;
import lk.ijse.DAO.custom.impl.CustomerDAOImpl;
import lk.ijse.DAO.custom.impl.EmployeeDAOImpl;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<Employee> employeeList = employeeDAO.getAll();

        for (Employee employee : employeeList){
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getId(),
                    employee.getName(),
                    employee.getSalary(),
                    employee.getAddress(),
                    employee.getPhone());
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    public boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(
                dto.getId(),
                dto.getName(),
                dto.getSalary(),
                dto.getAddress(),
                dto.getPhone()));
    }

    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                dto.getName(),
                dto.getSalary(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getId()));
    }

    public EmployeeDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getSalary(),
                employee.getAddress(),
                employee.getPhone());

        return employeeDTO;
    }

    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return employeeDAO.getCodes();
    }
}
