package lk.ijse.bo.custom;

import lk.ijse.dto.ItemwithSupplierDTO;
import lk.ijse.entity.ItemwithSupplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemWithSupplierBO {
    public List<ItemwithSupplierDTO> getAll() throws SQLException, ClassNotFoundException;
    public ItemwithSupplierDTO searchById(String code) throws SQLException, ClassNotFoundException;
}
