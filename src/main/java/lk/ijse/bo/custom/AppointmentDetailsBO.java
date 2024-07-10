package lk.ijse.bo.custom;

import lk.ijse.dto.AppointmentDetailsDTO;
import lk.ijse.dto.AppointmentSearchDTO;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDetailsBO {
    public List<AppointmentDetailsDTO> getAll() throws SQLException, ClassNotFoundException;
    public AppointmentSearchDTO searchById(String cusPhone) throws SQLException, ClassNotFoundException;
}
