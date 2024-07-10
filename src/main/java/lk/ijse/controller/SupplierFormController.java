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
import lk.ijse.entity.*;
import lk.ijse.smcmanagesuite.model.tm.SupplierTm;
import lk.ijse.smcmanagesuite.repository.SupplierRepo;
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
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTel;

    private List<Supplier> supplierList = new ArrayList<>();

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
        ObservableList<SupplierTm> tmList = FXCollections.observableArrayList();

        for (Supplier supplier : supplierList) {
            SupplierTm supplierTm = new SupplierTm(
                    supplier.getSupId(),
                    supplier.getName(),
                    supplier.getTel()
            );

            tmList.add(supplierTm);
        }
        tblSupplier.setItems(tmList);
    }

    private List<Supplier> getAllSuppliers() {
        List<Supplier> supplierList = null;
        try {
            supplierList = SupplierRepo.getAll();
        } catch (SQLException e) {
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
            boolean isDeleted = SupplierRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();
            }
        } catch (SQLException e) {
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

            Supplier supplier = new Supplier(id, name, tel);

            try {
                boolean isSaved = SupplierRepo.save(supplier);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Saved!").show();
                }
            } catch (SQLException e) {
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

            Supplier supplier = new Supplier(id, name, tel);

            try {
                boolean isUpdated = SupplierRepo.update(supplier);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            initialize();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            Supplier supplier = SupplierRepo.searchById(id);

            if (supplier != null) {
                txtTel.setText(supplier.getTel());
                txtName.setText(supplier.getName());
                txtId.setText(supplier.getSupId());
            }
        } catch (SQLException e) {
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
