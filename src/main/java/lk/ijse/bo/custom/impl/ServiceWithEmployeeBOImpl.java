package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ServiceWithEmployeeDAO;
import lk.ijse.DAO.custom.impl.ServicewithEmployeeDAOImpl;
import lk.ijse.bo.custom.ServiceWithEmployeeBO;
import lk.ijse.dto.ServicewithEmployeeDTO;
import lk.ijse.entity.ServicewithEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceWithEmployeeBOImpl implements ServiceWithEmployeeBO {
    ServiceWithEmployeeDAO serviceWithEmployeeDAO = new ServicewithEmployeeDAOImpl();
    public List<ServicewithEmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        List<ServicewithEmployeeDTO> servicewithEmployeeDTOList = new ArrayList<>();
        List<ServicewithEmployee> servicewithEmployeeList = serviceWithEmployeeDAO.getAll();

        for (ServicewithEmployee servicewithEmployee : servicewithEmployeeList){
            ServicewithEmployeeDTO servicewithEmployeeDTO = new ServicewithEmployeeDTO(
                    servicewithEmployee.getServiceId(),
                    servicewithEmployee.getDescription(),
                    servicewithEmployee.getPrice(),
                    servicewithEmployee.getEmpId(),
                    servicewithEmployee.getEmpName());
            servicewithEmployeeDTOList.add(servicewithEmployeeDTO);
        }
        return servicewithEmployeeDTOList;
    }

    public ServicewithEmployeeDTO searchById(String id) throws SQLException, ClassNotFoundException {
        ServicewithEmployee servicewithEmployee = serviceWithEmployeeDAO.searchById(id);
        ServicewithEmployeeDTO servicewithEmployeeDTO = new ServicewithEmployeeDTO(
                servicewithEmployee.getServiceId(),
                servicewithEmployee.getDescription(),
                servicewithEmployee.getPrice(),
                servicewithEmployee.getEmpId(),
                servicewithEmployee.getEmpName());

        return servicewithEmployeeDTO;
    }
}
