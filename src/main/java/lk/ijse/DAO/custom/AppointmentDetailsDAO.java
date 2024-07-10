package lk.ijse.DAO.custom;

import lk.ijse.entity.AppointmentDetails;
import lk.ijse.entity.AppointmentSearch;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDetailsDAO {
    public List<AppointmentDetails> getAll() throws SQLException, ClassNotFoundException;
    public AppointmentSearch searchById(String cusPhone) throws SQLException, ClassNotFoundException;
}
