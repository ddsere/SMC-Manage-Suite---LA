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
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.dto.SupplierTmDTO;
import lk.ijse.entity.*;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierFormController {

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupplierTmDTO> tblSupplier;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTel;

    private List<SupplierDTO> supplierList = new ArrayList<>();
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.SUPPLIER);

    public void initialize() {
        this.supplierList = getAllSuppliers();
        setCellValueFactory();
        loadSupplierTable();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("SupId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
    }

    private void loadSupplierTable() {
        ObservableList<SupplierTmDTO> tmList = FXCollections.observableArrayList();

        for (SupplierDTO supplier : supplierList) {
            SupplierTmDTO supplierTm = new SupplierTmDTO(
                    supplier.getSupId(),
                    supplier.getName(),
                    supplier.getTel()
            );

            tmList.add(supplierTm);
        }
        tblSupplier.setItems(tmList);
    }

    private List<SupplierDTO> getAllSuppliers() {
        List<SupplierDTO> supplierList = null;
        try {
            supplierList = supplierBO.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return supplierList;
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtTel.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = supplierBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
        initialize();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (isValid()) {
            String id = txtId.getText();
            String name = txtName.getText();
            String tel = txtTel.getText();

            SupplierDTO supplier = new SupplierDTO(id, name, tel);

            try {
                boolean isSaved = supplierBO.save(supplier);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Saved!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            initialize();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (isValid()) {
            String id = txtId.getText();
            String name = txtName.getText();
            String tel = txtTel.getText();

            SupplierDTO supplier = new SupplierDTO(id, name, tel);

            try {
                boolean isUpdated = supplierBO.update(supplier);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            initialize();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            SupplierDTO supplier = supplierBO.searchById(id);

            if (supplier != null) {
                txtTel.setText(supplier.getTel());
                txtName.setText(supplier.getName());
                txtId.setText(supplier.getSupId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public boolean isValid(){
        if (!Regex.setTextColor(TextFields.SID,txtId)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFields.PHONE,txtTel)) return false;
        return true;
    }

    public void txtIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SID,txtId);
    }

    public void txtNameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
    }

    public void txtTelCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE,txtTel);
    }
}
