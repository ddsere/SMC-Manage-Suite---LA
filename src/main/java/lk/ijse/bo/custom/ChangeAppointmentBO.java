package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ChangeAppointmentDTO;

import java.sql.SQLException;

public interface ChangeAppointmentBO extends SuperBO {
    public boolean changeAppointment(ChangeAppointmentDTO po) throws SQLException;
}
