package lk.ijse.bo.custom;

import lk.ijse.dto.ServiceDTO;
import lk.ijse.entity.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean save(ServiceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean update(ServiceDTO dto) throws SQLException, ClassNotFoundException;
    public ServiceDTO searchById(String id) throws SQLException, ClassNotFoundException;
    public List<String> getIds() throws SQLException, ClassNotFoundException;
    public double getPrice(String servId) throws SQLException, ClassNotFoundException;
}
