package lk.ijse.bo.custom.impl;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.DAO.custom.ItemDAO;
import lk.ijse.DAO.custom.impl.ItemDAOImpl;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.ItemQtyDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.ItemQty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = new ItemDAOImpl();

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(
                dto.getItemId(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getQty(),
                dto.getSupId()));
    }

    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(
                dto.getSupId(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getQty(),
                dto.getItemId()));
    }

    public ItemDTO searchById(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.searchById(code);
        ItemDTO itemDTO = new ItemDTO(
                item.getItemId(),
                item.getDescription(),
                item.getPrice(),
                item.getQty(),
                item.getSupId());

        return itemDTO;
    }

    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return itemDAO.getCodes();
    }

    public boolean updateQty(List<ItemQty> itemQties) throws SQLException, ClassNotFoundException {
        for (ItemQty od : itemQties) {
            if(!updateQty(od)) {
                return false;
            }
        }
        return true;
    }
    public boolean updateQty(ItemQtyDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQty(new ItemQty(
                dto.getQty(),
                dto.getItemCode()));
    }
}
