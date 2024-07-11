package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.OrderBO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderTmDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderFormController {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCusName;

    @FXML
    private TableColumn<?, ?> colCusPhone;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<OrderTmDTO> tblOrder;

    private List<OrderDTO> orderList = new ArrayList<>();
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ORDER);

    public void initialize() {
        this.orderList = getAllOrders();
        setCellValueFactory();
        loadOrderTable();
    }

    private List<OrderDTO> getAllOrders() {
        List<OrderDTO> orderList = null;
        try {
            orderList = orderBO.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colCusPhone.setCellValueFactory(new PropertyValueFactory<>("cusPhone"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void loadOrderTable() {
        ObservableList<OrderTmDTO> tmList = FXCollections.observableArrayList();

        for (OrderDTO order : orderList) {
            OrderTmDTO orderTm = new OrderTmDTO(
                    order.getOrderId(),
                    order.getDate(),
                    order.getCusName(),
                    order.getCusPhone(),
                    order.getAmount()
            );

            tmList.add(orderTm);
        }
        tblOrder.setItems(tmList);
    }

}
