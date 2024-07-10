package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.AppointmentDAO;
import lk.ijse.DAO.custom.impl.AppointmentDAOImpl;
import lk.ijse.bo.custom.AppointmentBO;
import lk.ijse.dto.AppointmentDTO;
import lk.ijse.dto.AppointmentDetailsDTO;
import lk.ijse.dto.AppointmentStatusDTO;
import lk.ijse.entity.Appointment;
import lk.ijse.entity.AppointmentStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentBOImpl implements AppointmentBO {

    AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

    public boolean save(AppointmentStatusDTO dto) throws SQLException, ClassNotFoundException {
        return appointmentDAO.save(new AppointmentStatus(
                dto.getStatus(),
                dto.getId()));
    }

    public boolean save(AppointmentDTO dto) throws SQLException, ClassNotFoundException {
        return appointmentDAO.save(new Appointment(
                dto.getAppId(),
                dto.getDate(),
                dto.getCusPhone(),
                dto.getServId(),
                dto.getEmpId(),
                dto.getTimeSlot(),
                dto.getPrice()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return appointmentDAO.delete(id);
    }

    public boolean update(AppointmentDTO dto) throws SQLException, ClassNotFoundException {
        return appointmentDAO.update(new Appointment(
                dto.getAppId(),
                dto.getDate(),
                dto.getCusPhone(),
                dto.getServId(),
                dto.getEmpId(),
                dto.getTimeSlot(),
                dto.getPrice()
        ));
    }

    public int getAppCount() throws SQLException, ClassNotFoundException {
        return appointmentDAO.getAppCount();
    }
}
