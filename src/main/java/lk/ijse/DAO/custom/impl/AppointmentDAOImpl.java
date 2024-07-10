package lk.ijse.DAO.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.AppointmentDAO;
import lk.ijse.DAO.custom.CustomerDAO;
import lk.ijse.dto.AppointmentDTO;
import lk.ijse.dto.AppointmentStatusDTO;
import lk.ijse.entity.Appointment;
import lk.ijse.entity.AppointmentStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAOImpl implements AppointmentDAO {

    public boolean save(AppointmentStatus entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Appointment SET Status = ? WHERE Appointment_id = ?",
                entity.getStatus(),
                entity.getId());
    }

    public boolean save(Appointment entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Appointment VALUES(?, ?, ?, ?, ?, ?, ?,'Pending')",
                entity.getAppId(),
                entity.getDate(),
                entity.getCusPhone(),
                entity.getServId(),
                entity.getEmpId(),
                entity.getTimeSlot(),
                entity.getPrice());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Appointment WHERE Appointment_Id = ?", id);
    }

    public boolean update(Appointment entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Appointment\n" +
                "SET Date = ?,\n" +
                "    Service_Id = ?,\n" +
                "    Employee_Id = ?,\n" +
                "    Time_Slot = ?\n" +
                "    Price = ?\n" +
                "WHERE Appointment_Id = ?;\n",
                entity.getDate(),
                entity.getServId(),
                entity.getEmpId(),
                entity.getTimeSlot(),
                entity.getAppId(),
                entity.getPrice());
    }

    public int getAppCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT COUNT(*) AS appCount FROM Appointment");

        int appCount = 0;
        if(resultSet.next()) {
            appCount = resultSet.getInt("appCount");
        }
        return appCount;
    }
}
