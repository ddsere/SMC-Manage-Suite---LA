package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.SupplierDAO;
import lk.ijse.DAO.custom.impl.SupplierDAOImpl;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = new SupplierDAOImpl();
    public boolean save(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(
                dto.getSupId(),
                dto.getTel(),
                dto.getName()));
    }

    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        List<Supplier> supplierList = supplierDAO.getAll();

        for (Supplier supplier : supplierList){
            SupplierDTO supplierDTO = new SupplierDTO(
                    supplier.getSupId(),
                    supplier.getTel(),
                    supplier.getName());
            supplierDTOList.add(supplierDTO);
        }
        return supplierDTOList;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    public boolean update(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(
                dto.getName(),
                dto.getTel(),
                dto.getSupId()));
    }

    public SupplierDTO searchById(String supId) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchById(supId);
        SupplierDTO supplierDTO = new SupplierDTO(
                supplier.getSupId(),
                supplier.getTel(),
                supplier.getName());

        return supplierDTO;
    }

    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return supplierDAO.getCodes();
    }
}
