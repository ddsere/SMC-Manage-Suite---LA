package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.custom.OrderDAO;
import lk.ijse.DAO.custom.impl.OrderDAOImpl;
import lk.ijse.bo.custom.OrderBO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = new OrderDAOImpl();

    public String currentId() throws SQLException, ClassNotFoundException {
        return orderDAO.currentId();
    }

    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(
                dto.getOrderId(),
                dto.getDate(),
                dto.getAmount(),
                dto.getCusPhone(),
                dto.getCusName()));
    }

    public List<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<Order> orderList = orderDAO.getAll();

        for (Order order : orderList){
            OrderDTO orderDTO = new OrderDTO(
                    order.getOrderId(),
                    order.getDate(),
                    order.getAmount(),
                    order.getCusPhone(),
                    order.getCusName());
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    public int getOrderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderCount();
    }
}
