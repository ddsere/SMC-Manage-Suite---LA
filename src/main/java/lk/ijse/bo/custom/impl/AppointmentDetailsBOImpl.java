package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.AppointmentDetailsDAO;
import lk.ijse.DAO.custom.impl.AppointmentDetailsDAOImpl;
import lk.ijse.bo.custom.AppointmentDetailsBO;
import lk.ijse.dto.AppointmentDetailsDTO;
import lk.ijse.dto.AppointmentSearchDTO;
import lk.ijse.entity.AppointmentDetails;
import lk.ijse.entity.AppointmentSearch;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDetailsBOImpl implements AppointmentDetailsBO {
    AppointmentDetailsDAO appointmentDetailsDAO = new AppointmentDetailsDAOImpl();

    public List<AppointmentDetailsDTO> getAll() throws SQLException, ClassNotFoundException {
        List<AppointmentDetailsDTO> appointmentDetailsDTOList = new ArrayList<>();
        List<AppointmentDetails> appointmentDetailsList = appointmentDetailsDAO.getAll();

        for (AppointmentDetails appointmentDetails : appointmentDetailsList){
            AppointmentDetailsDTO appointmentDetailsDTO = new AppointmentDetailsDTO(
                    appointmentDetails.getAppId(),
                    appointmentDetails.getCusName(),
                    appointmentDetails.getSName(),
                    appointmentDetails.getDate(),
                    appointmentDetails.getTimeSlot(),
                    appointmentDetails.getEmpName());
        }
        return appointmentDetailsDTOList;
    }

    public AppointmentSearchDTO searchById(String cusPhone) throws SQLException, ClassNotFoundException {
        AppointmentSearch appointmentSearch = appointmentDetailsDAO.searchById(cusPhone);
        AppointmentSearchDTO appointmentSearchDTO = new AppointmentSearchDTO(
                appointmentSearch.getAppId(),
                appointmentSearch.getDate(),
                appointmentSearch.getSId(),
                appointmentSearch.getEmpId(),
                appointmentSearch.getTs(),
                appointmentSearch.getCusName(),
                appointmentSearch.getEmpName(),
                appointmentSearch.getSName());

        return appointmentSearchDTO;
    }
}
