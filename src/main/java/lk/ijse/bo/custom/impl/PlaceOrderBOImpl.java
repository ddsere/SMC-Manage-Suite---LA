package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.custom.ItemDAO;
import lk.ijse.DAO.custom.ItemDetailDAO;
import lk.ijse.DAO.custom.OrderDAO;
import lk.ijse.DAO.custom.ServiceDetailDAO;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PlaceOrderBO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemQtyDTO;
import lk.ijse.dto.PlaceOrderDTO;
import lk.ijse.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDAO = (OrderDAO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ORDER);
    ItemDetailDAO itemDetailDAO = (ItemDetailDAO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM_DETAIL);
    ItemDAO itemDAO = (ItemDAO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM);
    ServiceDetailDAO serviceDetailDAO = (ServiceDetailDAO) BOFactory.getBoFactory().getBO(BOFactory.BOType.SERVICE_DETAIL);

    public boolean placeOrder(PlaceOrderDTO po) throws SQLException {
        Order order = new Order(
                po.getOrder().getOrderId(),
                po.getOrder().getDate(),
                po.getOrder().getAmount(),
                po.getOrder().getCusPhone(),
                po.getOrder().getCusName()
        );

        List<ItemQty> itemQtyList = new ArrayList<>(
                po.getItemQties().stream().map(pd -> new ItemQty(
                        pd.getQty(),
                        pd.getItemCode()
                )).toList()
        );

        List<ServiceIds> serviceList = new ArrayList<>(
                po.getServiceIdDTOS().stream().map(sl -> new ServiceIds(
                        sl.getId()
                )).toList()
        );

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDAO.save(order);

            if (isOrderSaved) {
                boolean isItemDetailsUpdate = itemDetailDAO.save(itemQtyList,order);

                if (isItemDetailsUpdate){
                    boolean isItemQtyUpdate = itemDAO.updateQty(itemQtyList);

                    if (isItemQtyUpdate){
                        boolean isServiceDetailsUpdated = serviceDetailDAO.save(order,serviceList);

                        if (isServiceDetailsUpdated) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
