package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public  boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    public  boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    public  CustomerDTO searchById(String id) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    public List<String> getTel() throws SQLException, ClassNotFoundException;
    public int getCusCount() throws SQLException, ClassNotFoundException;
}
