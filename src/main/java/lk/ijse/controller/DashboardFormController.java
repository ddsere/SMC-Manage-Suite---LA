package lk.ijse.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.DAO.custom.AppointmentDAO;
import lk.ijse.DAO.custom.CustomerDAO;
import lk.ijse.DAO.custom.OrderDAO;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AppointmentBO;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.OrderBO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashboardFormController {

    @FXML
    private Label lblBooking;

    @FXML
    private Label lblCusCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblSales;

    @FXML
    private Label lblTime;

    private int appCount;
    private int salesCount;
    private int cusCount;

    private volatile boolean stop = false;

    AppointmentBO appointmentBO = (AppointmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.APPOINTMENT);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER);

    public void initialize(){
        timeNow();

        LocalDate date = LocalDate.now();
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("EEEE, MMM dd");
        String formattedDate = date.format(dateformatter);
        lblDate.setText(formattedDate);

        try {
            appCount = appointmentBO.getAppCount();
            salesCount = orderBO.getOrderCount();
            cusCount = customerBO.getCusCount();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        setAppCount(appCount);
        setSalesCount(salesCount);
        setCusCount(cusCount);
    }

    private void setCusCount(int cusCount) {
        lblCusCount.setText(String.valueOf(cusCount));
    }

    private void setSalesCount(int salesCount) {
        lblSales.setText(String.valueOf(salesCount));
    }

    private void setAppCount(int appCount) {
        lblBooking.setText(String.valueOf(appCount));
    }


    public void timeNow(){
        Thread thread = new Thread(()->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            while (!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(()->{
                    lblTime.setText(timenow);
                });
            }
        });
        thread.start();
    }
}
