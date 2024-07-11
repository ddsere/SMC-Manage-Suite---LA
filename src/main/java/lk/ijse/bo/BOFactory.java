package lk.ijse.bo;

import lk.ijse.DAO.custom.impl.OrderDAOImpl;
import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        CHANGE_APPOINTMENT, APPOINTMENT_DETAIL, APPOINTMENT, CUSTOMER, ITEM, ITEM_DETAIL, PLACE_ORDER, SUPPLIER, EMPLOYEE, SERVICE_WITH_EMPLOYEE, SERVICE_DETAIL, SERVICE, ORDER, ITEM_WITH_SUPPLIER
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case APPOINTMENT:
                return new AppointmentBOImpl();

            case CUSTOMER:
                return new CustomerBOImpl();

            case ITEM:
                return new ItemBOImpl();

            case PLACE_ORDER:
                return new PlaceOrderBOImpl();

            case ORDER:
                return new OrderBOImpl();

            case SERVICE:
                return new ServiceBOImpl();

            case EMPLOYEE:
                return new EmployeeBOImpl();

            case SUPPLIER:
                return new SupplierBOImpl();

            case ITEM_DETAIL:
                return new ItemDetailBOImpl();

            case SERVICE_DETAIL:
                return new ServiceDetailBOImpl();

            case APPOINTMENT_DETAIL:
                return new AppointmentDetailsBOImpl();

            case CHANGE_APPOINTMENT:
                return new ChangeAppointmentBOImpl();

            case ITEM_WITH_SUPPLIER:
                return new ItemWithSupplierBOImpl();

            case SERVICE_WITH_EMPLOYEE:
                return new ServiceWithEmployeeBOImpl();
        }
        return null;
    }
}
