package lk.ijse.DAO.custom;

import lk.ijse.dto.AppointmentDTO;
import lk.ijse.dto.AppointmentStatusDTO;
import lk.ijse.entity.Appointment;
import lk.ijse.entity.AppointmentStatus;

import java.sql.SQLException;

public interface AppointmentDAO {
    public boolean save(AppointmentStatus entity) throws SQLException, ClassNotFoundException;
    public boolean save(Appointment entity) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean update(Appointment entity) throws SQLException, ClassNotFoundException;
    public int getAppCount() throws SQLException, ClassNotFoundException;
}
