package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ServiceDetailDAO;
import lk.ijse.entity.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceDetailDAOImpl implements ServiceDetailDAO {
    public boolean save(Order order, List<ServiceIds> serviceIds) throws SQLException, ClassNotFoundException {
            for (ServiceIds od : serviceIds) {
                if(!save(od,order)) {
                    return false;
                }
            }
            return true;
    }

    public boolean save(ServiceIds od, Order order) throws SQLException, ClassNotFoundException {
            return SqlUtil.execute("INSERT INTO service_orders VALUES(?, ?)",
                    od.getId(),
                    order.getOrderId()
            );
    }
}
