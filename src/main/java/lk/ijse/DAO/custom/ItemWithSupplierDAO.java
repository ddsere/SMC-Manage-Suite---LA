package lk.ijse.DAO.custom;

import lk.ijse.entity.ItemwithSupplier;

import java.sql.SQLException;
import java.util.List;

public interface ItemWithSupplierDAO {
    public List<ItemwithSupplier> getAll() throws SQLException, ClassNotFoundException;
    public ItemwithSupplier searchById(String code) throws SQLException, ClassNotFoundException;
}
