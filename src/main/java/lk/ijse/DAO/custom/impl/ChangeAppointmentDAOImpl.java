package lk.ijse.DAO.custom.impl;

import lk.ijse.smcmanagesuite.db.DbConnection;
import lk.ijse.smcmanagesuite.model.ChangeAppointment;

import java.sql.Connection;
import java.sql.SQLException;

public class ChangeAppointmentDAOImpl {
    public static boolean changeAppointment(ChangeAppointment po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderDAOImpl.save(po.getOrder());
            if (isOrderSaved) {
                System.out.println("order saved");
                boolean isUpdateAppStatus = AppointmentDAOImpl.save(po.getAppoStatus());
                if (isUpdateAppStatus) {
                    System.out.println("isupdatstatue " + isUpdateAppStatus);
                        connection.commit();
                        return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
