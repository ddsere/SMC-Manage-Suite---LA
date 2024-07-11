package lk.ijse.bo.custom.impl;

import lk.ijse.dto.PlaceOrderDTO;
import lk.ijse.entity.*;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBOImpl {
    public boolean placeOrder(PlaceOrderDTO po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderRepo.save(po.getOrder());

            if (isOrderSaved) {
                boolean isItemDetailsUpdate = ItemDetailRepo.save(po.getItemQties(),po.getOrder());

                if (isItemDetailsUpdate){
                    boolean isItemQtyUpdate = ItemRepo.updateQty(po.getItemQties());

                    if (isItemQtyUpdate){
                        boolean isServiceDetailsUpdated =ServiceDetailRepo.save(po.getOrder(),po.getServiceIds());

                    }
                    if (isItemQtyUpdate) {
                        connection.commit();
                        return true;
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
