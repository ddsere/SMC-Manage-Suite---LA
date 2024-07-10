package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ServiceDAO;
import lk.ijse.DAO.custom.impl.ServiceDAOImpl;
import lk.ijse.bo.custom.ServiceDetailBO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.ServiceIdsDTO;
import lk.ijse.entity.Order;
import lk.ijse.entity.Service;
import lk.ijse.entity.ServiceIds;

import java.sql.SQLException;
import java.util.List;

public class ServiceDetailBOImpl implements ServiceDetailBO {
    ServiceDAO serviceDAO = new ServiceDAOImpl();

    public boolean save(OrderDTO orderDTO, List<ServiceIdsDTO> serviceIdsDTOList) throws SQLException, ClassNotFoundException {
        for (ServiceIdsDTO od : serviceIdsDTOList) {
            if(!save(od,orderDTO)) {
                return false;
            }
        }
        return true;
    }

    public boolean save(ServiceIdsDTO serviceIdsDTO, OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return serviceDAO.save(new ServiceIds(
                serviceIdsDTO.getId(),
                orderDTO.getOrderId());
    }
}
