package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ServiceDAO;
import lk.ijse.DAO.custom.impl.ServiceDAOImpl;
import lk.ijse.bo.custom.ServiceBO;
import lk.ijse.dto.ServiceDTO;
import lk.ijse.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO = new ServiceDAOImpl();

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return serviceDAO.delete(id);
    }

    public boolean save(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.save(new Service(
                dto.getServiceId(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getEmpId()));
    }

    public boolean update(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.update(new Service(
                dto.getEmpId(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getServiceId()));
    }

    public ServiceDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Service service = serviceDAO.searchById(id);
        ServiceDTO serviceDTO = new ServiceDTO(
                service.getServiceId(),
                service.getDescription(),
                service.getPrice(),
                service.getEmpId());

        return serviceDTO;
    }

    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return serviceDAO.getIds();
    }

    public double getPrice(String servId) throws SQLException, ClassNotFoundException {
        return serviceDAO.getPrice(servId);
    }
}
