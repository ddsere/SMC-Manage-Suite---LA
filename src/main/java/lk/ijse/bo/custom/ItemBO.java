package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.ItemQtyDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.ItemQty;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException;
    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException;
    public ItemDTO searchById(String code) throws SQLException, ClassNotFoundException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;
    public boolean updateQty(List<ItemQtyDTO> itemQties) throws SQLException, ClassNotFoundException;
    public boolean updateQty(ItemQtyDTO dto) throws SQLException, ClassNotFoundException;
}
