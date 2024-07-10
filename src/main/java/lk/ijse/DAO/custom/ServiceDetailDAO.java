package lk.ijse.DAO.custom;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.entity.Order;
import lk.ijse.entity.ServiceIds;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDetailDAO {
    public boolean save(Order order, List<ServiceIds> serviceIds) throws SQLException, ClassNotFoundException;
    public boolean save( ServiceIds od, Order order) throws SQLException, ClassNotFoundException;
}
