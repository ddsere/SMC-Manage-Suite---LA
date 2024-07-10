package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.CustomerDAO;
import lk.ijse.DAO.custom.impl.CustomerDAOImpl;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDAOImpl();

    public  boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(
                dto.getTel(),
                dto.getName(),
                dto.getAddress()
        ));
    }

    public  boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(
                dto.getName(),
                dto.getAddress(),
                dto.getTel()
        ));
    }

    public  CustomerDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchById(id);
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getName(),
                customer.getAddress(),
                customer.getTel()
        );
        return customerDTO;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<Customer> customersList = customerDAO.getAll();

        for (Customer customer : customersList){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getName(),
                    customer.getAddress(),
                    customer.getTel()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    public List<String> getTel() throws SQLException, ClassNotFoundException {
        return customerDAO.getTel();
    }

    public int getCusCount() throws SQLException, ClassNotFoundException {
        return customerDAO.getCusCount();
    }
}
