package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.custom.ServiceBO;
import lk.ijse.bo.custom.ServiceWithEmployeeBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.ServiceDTO;
import lk.ijse.dto.ServicewithEmployeeDTO;
import lk.ijse.dto.ServicewithEmployeeTmDTO;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceFormController {

    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private Label lblEmpName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ServicewithEmployeeTmDTO> tblService;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtPrice;

    private List<ServicewithEmployeeDTO> servicewithEmployeeList = new ArrayList<>();
    ServiceWithEmployeeBO serviceWithEmployeeBO = (ServiceWithEmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.SERVICE_WITH_EMPLOYEE);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE);
    ServiceBO serviceBO = (ServiceBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.SERVICE);

    public void initialize() {
        this.servicewithEmployeeList = getAllItems();
        setCellValueFactory();
        loadServiceTable();
        getEmpIds();
    }

    private List<ServicewithEmployeeDTO> getAllItems() {
        List<ServicewithEmployeeDTO> servicewithEmployeeList = null;
        try {
            servicewithEmployeeList = serviceWithEmployeeBO.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return servicewithEmployeeList;
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("ServiceId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("EmpId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("EmpName"));
    }

    private void loadServiceTable() {
        ObservableList<ServicewithEmployeeTmDTO> tmList = FXCollections.observableArrayList();

        for (ServicewithEmployeeDTO servicewithEmployee : servicewithEmployeeList) {
            ServicewithEmployeeTmDTO servicewithEmployeeTm = new ServicewithEmployeeTmDTO(
                    servicewithEmployee.getServiceId(),
                    servicewithEmployee.getDescription(),
                    servicewithEmployee.getPrice(),
                    servicewithEmployee.getEmpId(),
                    servicewithEmployee.getEmpName()
            );

            tmList.add(servicewithEmployeeTm);
        }
        tblService.setItems(tmList);
    }

    private void getEmpIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = employeeBO.getCodes();
            for (String id : idList) {
                obList.add(id);
            }

            cmbEmpId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        lblEmpName.setText("");
        cmbEmpId.getSelectionModel().clearSelection();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = serviceBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Service Deleted!").show();
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
            String description = txtDescription.getText();
            String price = txtPrice.getText();
            String empId = cmbEmpId.getValue();

            ServiceDTO service = new ServiceDTO(id, description, price, empId);

            try {
                boolean isSaved = serviceBO.save(service);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Service Saved!").show();
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
            String description = txtDescription.getText();
            String price = txtPrice.getText();
            String empId = cmbEmpId.getValue();

            ServiceDTO service = new ServiceDTO(id, description, price, empId);

            try {
                boolean isUpdated = serviceBO.update(service);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Service Updated!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            initialize();
        }
    }

    @FXML
    void cmbEmployeeOnAction(ActionEvent event) {
        String empId = cmbEmpId.getValue();

        try {
            EmployeeDTO employee = employeeBO.searchById(empId);
            if (employee != null) {
                lblEmpName.setText(employee.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            ServicewithEmployeeDTO servicewithEmployee = serviceWithEmployeeBO.searchById(id);

            if (servicewithEmployee != null) {
                txtId.setText(servicewithEmployee.getServiceId());
                txtDescription.setText(servicewithEmployee.getDescription());
                txtPrice.setText(servicewithEmployee.getPrice());
                lblEmpName.setText(servicewithEmployee.getEmpName());

                String sId = servicewithEmployee.getEmpId();
                cmbEmpId.setValue(sId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public boolean isValid(){
        if (!Regex.setTextColor(TextFields.SID,txtId)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtDescription)) return false;
        if (!Regex.setTextColor(TextFields.PRICE,txtPrice)) return false;
        return true;
    }

    public void txtIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SID,txtId);
    }

    public void txtDescCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtDescription);
    }

    public void txtPriceCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PRICE,txtPrice);
    }
}
