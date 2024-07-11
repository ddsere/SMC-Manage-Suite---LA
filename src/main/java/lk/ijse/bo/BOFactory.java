package lk.ijse.bo;

import lk.ijse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.bo.custom.impl.ItemBOImpl;
import lk.ijse.bo.custom.impl.PlaceOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        CUSTOMER, ITEM, PLACE_ORDER
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case CUSTOMER:
                return new CustomerBOImpl();

            case ITEM:
                return new ItemBOImpl();

            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
        }
        return null;
    }
}
