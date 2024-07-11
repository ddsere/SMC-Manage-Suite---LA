package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ServiceDAO;
import lk.ijse.DAO.custom.ServiceDetailDAO;
import lk.ijse.DAO.custom.impl.ServiceDAOImpl;
import lk.ijse.DAO.custom.impl.ServiceDetailDAOImpl;
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
    ServiceDetailDAO serviceDetailDAO = new ServiceDetailDAOImpl();

    public boolean save(OrderDTO orderDTO, List<ServiceIdsDTO> serviceIdsDTOList) throws SQLException, ClassNotFoundException {
        for (ServiceIdsDTO od : serviceIdsDTOList) {
            if(!save(od,orderDTO)) {
                return false;
            }
        }
        return true;
    }

    public boolean save(ServiceIdsDTO serviceIdsDTO, OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        ServiceIds serviceIds = new ServiceIds(
                serviceIdsDTO.getId());
        Order order = new Order(
                orderDTO.getOrderId(),
                orderDTO.getDate(),
                orderDTO.getAmount(),
                orderDTO.getCusPhone(),
                orderDTO.getCusName());
        return serviceDetailDAO.save(serviceIds,order);
    }
}
