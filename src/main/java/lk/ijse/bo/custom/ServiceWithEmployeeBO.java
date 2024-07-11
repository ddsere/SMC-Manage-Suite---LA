package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ServicewithEmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface ServiceWithEmployeeBO extends SuperBO {
    public List<ServicewithEmployeeDTO> getAll() throws SQLException, ClassNotFoundException;
    public ServicewithEmployeeDTO searchById(String id) throws SQLException, ClassNotFoundException;
}
