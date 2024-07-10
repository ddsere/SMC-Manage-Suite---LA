package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ItemWithSupplierDAO;
import lk.ijse.DAO.custom.impl.ItemwithSupplierDAOImpl;
import lk.ijse.bo.custom.ItemWithSupplierBO;
import lk.ijse.dto.ItemwithSupplierDTO;
import lk.ijse.entity.ItemwithSupplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemWithSupplierBOImpl implements ItemWithSupplierBO {
    ItemWithSupplierDAO itemWithSupplierDAO = new ItemwithSupplierDAOImpl();

    public List<ItemwithSupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        List<ItemwithSupplierDTO> itemwithSupplierDTOList = new ArrayList<>();
        List<ItemwithSupplier> itemwithSupplierList = itemWithSupplierDAO.getAll();

        for (ItemwithSupplier itemwithSupplier : itemwithSupplierList){
            ItemwithSupplierDTO itemwithSupplierDTO = new ItemwithSupplierDTO(
                    itemwithSupplier.getItemId(),
                    itemwithSupplier.getDescription(),
                    itemwithSupplier.getPrice(),
                    itemwithSupplier.getQty(),
                    itemwithSupplier.getSupId(),
                    itemwithSupplier.getSupName());
            itemwithSupplierDTOList.add(itemwithSupplierDTO);
        }
        return itemwithSupplierDTOList;
    }

    public ItemwithSupplierDTO searchById(String code) throws SQLException, ClassNotFoundException {
        ItemwithSupplier itemwithSupplier = itemWithSupplierDAO.searchById(code);
        ItemwithSupplierDTO itemwithSupplierDTO = new ItemwithSupplierDTO(
                itemwithSupplier.getItemId(),
                itemwithSupplier.getDescription(),
                itemwithSupplier.getPrice(),
                itemwithSupplier.getQty(),
                itemwithSupplier.getSupId(),
                itemwithSupplier.getSupName());

        return itemwithSupplierDTO;
    }
}
