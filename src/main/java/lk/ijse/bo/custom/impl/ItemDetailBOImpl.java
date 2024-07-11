package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ItemDetailDAO;
import lk.ijse.DAO.custom.impl.ItemDetailDAOImpl;
import lk.ijse.bo.custom.ItemDetailBO;
import lk.ijse.dto.ItemQtyDTO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.entity.ItemQty;
import lk.ijse.entity.Order;

import java.sql.SQLException;
import java.util.List;

public class ItemDetailBOImpl implements ItemDetailBO {
    ItemDetailDAO itemDetailDAO = new ItemDetailDAOImpl();
    public boolean save(List<ItemQtyDTO> itemQties , OrderDTO entity) throws SQLException, ClassNotFoundException {
        for (ItemQtyDTO od : itemQties) {
            if(!save(od,entity)) {
                return false;
            }
        }
        return true;
    }

    public boolean save(ItemQtyDTO od, OrderDTO ord) throws SQLException, ClassNotFoundException {
        ItemQty itemQty = new ItemQty(
                od.getQty(),
                od.getItemCode()
        );

        Order order = new Order(
                ord.getOrderId(),
                ord.getDate(),
                ord.getAmount(),
                ord.getCusPhone(),
                ord.getCusName()
        );
        return itemDetailDAO.save(itemQty,order);
    }
}
