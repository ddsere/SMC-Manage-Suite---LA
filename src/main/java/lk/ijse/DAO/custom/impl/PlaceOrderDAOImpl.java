package lk.ijse.DAO.custom.impl;

import lk.ijse.entity.*;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderDAOImpl {
    public boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderDAOImpl.save(po.getOrder());

            if (isOrderSaved) {
                boolean isItemDetailsUpdate = ItemDetailDAOImpl.save(po.getItemQties(),po.getOrder());

                if (isItemDetailsUpdate){
                    boolean isItemQtyUpdate = ItemDAOImpl.updateQty(po.getItemQties());

                    if (isItemQtyUpdate){
                        boolean isServiceDetailsUpdated = ServiceDetailDAOImpl.save(po.getOrder(),po.getServiceIds());

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
