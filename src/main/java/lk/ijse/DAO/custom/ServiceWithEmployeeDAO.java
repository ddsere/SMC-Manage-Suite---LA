package lk.ijse.DAO.custom;

import lk.ijse.entity.ServicewithEmployee;

import java.sql.SQLException;
import java.util.List;

public interface ServiceWithEmployeeDAO {
    public List<ServicewithEmployee> getAll() throws SQLException, ClassNotFoundException;
    public ServicewithEmployee searchById(String id) throws SQLException, ClassNotFoundException;
}
