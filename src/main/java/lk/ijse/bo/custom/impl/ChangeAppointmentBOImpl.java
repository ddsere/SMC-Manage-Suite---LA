package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.custom.AppointmentDAO;
import lk.ijse.DAO.custom.OrderDAO;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ChangeAppointmentBO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ChangeAppointmentDTO;
import lk.ijse.entity.AppointmentStatus;
import lk.ijse.entity.ChangeAppointment;
import lk.ijse.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public class ChangeAppointmentBOImpl implements ChangeAppointmentBO {
    OrderDAO orderDAO = (OrderDAO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ORDER);
    AppointmentDAO appointmentDAO = (AppointmentDAO) BOFactory.getBoFactory().getBO(BOFactory.BOType.APPOINTMENT);


    public boolean changeAppointment(ChangeAppointmentDTO po) throws SQLException {
        Order order = new Order(
                po.getOrder().getOrderId(),
                po.getOrder().getDate(),
                po.getOrder().getAmount(),
                po.getOrder().getCusPhone(),
                po.getOrder().getCusName()
        );

        AppointmentStatus appointmentStatus = new AppointmentStatus(
                po.getAppoStatus().getStatus(),
                po.getAppoStatus().getId()
        );

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDAO.save(order);
            if (isOrderSaved) {
                System.out.println("order saved");
                boolean isUpdateAppStatus = appointmentDAO.save(appointmentStatus);
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
