package lk.ijse.DAO.custom;

import lk.ijse.DAO.SqlUtil;
import lk.ijse.entity.Order;
import lk.ijse.entity.Service;
import lk.ijse.entity.ServiceIds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ServiceDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean save(Service entity) throws SQLException, ClassNotFoundException;
    public boolean update(Service entity) throws SQLException, ClassNotFoundException;
    public Service searchById(String id) throws SQLException, ClassNotFoundException;
    public List<String> getIds() throws SQLException, ClassNotFoundException;
    public double getPrice(String servId) throws SQLException, ClassNotFoundException;
}
