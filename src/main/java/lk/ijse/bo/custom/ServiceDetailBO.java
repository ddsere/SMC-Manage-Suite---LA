package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.ServiceIdsDTO;
import lk.ijse.entity.ServiceIds;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDetailBO extends SuperBO {
    public boolean save(OrderDTO orderDTO, List<ServiceIdsDTO> serviceIdsDTOList) throws SQLException, ClassNotFoundException;
    public boolean save(ServiceIdsDTO serviceIdsDTO, OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
}
