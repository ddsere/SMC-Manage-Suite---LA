package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AppointmentDTO;
import lk.ijse.dto.AppointmentStatusDTO;
import lk.ijse.entity.Appointment;
import lk.ijse.entity.AppointmentStatus;

import java.sql.SQLException;

public interface AppointmentBO extends SuperBO {
    public boolean save(AppointmentStatusDTO dto) throws SQLException, ClassNotFoundException;
    public boolean save(AppointmentDTO dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean update(AppointmentDTO dto) throws SQLException, ClassNotFoundException;
    public int getAppCount() throws SQLException, ClassNotFoundException;
}
