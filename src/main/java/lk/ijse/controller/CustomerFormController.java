package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.CustomerTmDTO;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTmDTO> tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTel;

    private List<CustomerDTO> customerList = new ArrayList<>();

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER);

    public void initialize() {
        this.customerList = getAllCustomers();
        setCellValueFactory();
        loadCustomerTable();
    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTmDTO> tmList = FXCollections.observableArrayList();

        for (CustomerDTO customer : customerList) {
            CustomerTmDTO customerTmDTO = new CustomerTmDTO(
                    customer.getName(),
                    customer.getAddress(),
                    customer.getTel()
            );

            tmList.add(customerTmDTO);
        }
        tblCustomer.setItems(tmList);
        CustomerTmDTO selectedItem = (CustomerTmDTO) tblCustomer.getSelectionModel().getSelectedItem();
        //System.out.println("selectedItem = " + selectedItem);
    }

    private List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerList = null;
        try {
            customerList = customerBO.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    private void clearFields() {
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtTel.getText();

        try {
            boolean isDeleted = customerBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        clearFields();
        initialize();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (isValid()) {
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();

            CustomerDTO customer = new CustomerDTO(name, address, tel);

            try {
                boolean isSaved = customerBO.save(customer);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            initialize();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (isValid()) {
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();

            CustomerDTO customer = new CustomerDTO(name, address, tel);

            try {
                boolean isUpdated = customerBO.update(customer);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            initialize();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtTel.getText();

        try {
            CustomerDTO customer = customerBO.searchById(id);

            if (customer != null) {
                txtTel.setText(customer.getTel());
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValid(){
        if (!Regex.setTextColor(TextFields.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtAddress)) return false;
        if (!Regex.setTextColor(TextFields.PHONE,txtTel)) return false;
        return true;
    }

    public void txtCusNameCheckOnAciton(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
    }

    public void txtCusAddressCheckOnAciton(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtAddress);
    }

    public void txtCusTelCheckOnAciton(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE,txtTel);
    }
}
