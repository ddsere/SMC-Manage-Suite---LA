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
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.EmployeeTmDTO;
import lk.ijse.entity.*;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFormController {

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmployeeTmDTO> tblEmployee;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtAddress;

    private List<EmployeeDTO> employeeList = new ArrayList<>();
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE);

    public void initialize() {
        this.employeeList = getAllEmployees();
        setCellValueFactory();
        loadEmployeeTable();
    }

    private List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeList = null;
        try {
            employeeList = employeeBO.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTmDTO> tmList = FXCollections.observableArrayList();

        for (EmployeeDTO employee : employeeList) {
            EmployeeTmDTO employeeTm = new EmployeeTmDTO(
                    employee.getId(),
                    employee.getName(),
                    employee.getSalary(),
                    employee.getAddress(),
                    employee.getPhone()
            );

            tmList.add(employeeTm);
        }
        tblEmployee.setItems(tmList);
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = employeeBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted!").show();
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
            String salary = txtSalary.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();

            EmployeeDTO employee = new EmployeeDTO(id, name, salary, address, phone);

            try {
                boolean isSaved = employeeBO.save(employee);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved!").show();
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
            String salary = txtSalary.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();

            EmployeeDTO employee = new EmployeeDTO(id, name, salary, address, phone);

            try {
                boolean isUpdated = employeeBO.update(employee);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            initialize();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        if (isValid()) {
            String id = txtId.getText();

            try {
                EmployeeDTO employee = employeeBO.searchById(id);

                if (employee != null) {
                    txtId.setText(employee.getId());
                    txtName.setText(employee.getName());
                    txtSalary.setText(employee.getSalary());
                    txtAddress.setText(employee.getAddress());
                    txtPhone.setText(employee.getPhone());
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public boolean isValid(){
        if (!Regex.setTextColor(TextFields.EID,txtId)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtAddress)) return false;
        if (!Regex.setTextColor(TextFields.PHONE,txtPhone)) return false;
        if (!Regex.setTextColor(TextFields.PRICE,txtSalary)) return false;
        return true;
    }

    public void txtIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EID,txtId);
    }

    public void txtNameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
    }

    public void txtAddressCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtAddress);
    }

    public void txtPhoneCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE,txtPhone);
    }

    public void txtSalaryCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PRICE,txtSalary);
    }
}
