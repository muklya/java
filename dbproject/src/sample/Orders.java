package sample;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Hazardinho on 18.04.2017.
 */
public class Orders{
    private SimpleIntegerProperty orderID_orders,customer_id_orders,deliveryMethodId_orders,Order_InformationID_orders,Payment_id_orders;
    public Orders(int orderID, int customer_id, int deliveryMethodId, int order_InformationID, int payment_id){
        this.orderID_orders = new SimpleIntegerProperty(orderID);
        this.customer_id_orders = new SimpleIntegerProperty(customer_id);
        this.deliveryMethodId_orders = new SimpleIntegerProperty(deliveryMethodId);
        this.Order_InformationID_orders = new SimpleIntegerProperty(order_InformationID);
        this.Payment_id_orders = new SimpleIntegerProperty(payment_id);
    }

    public int getOrderID_orders() {
        return orderID_orders.get();
    }

    public SimpleIntegerProperty orderID_ordersProperty() {
        return orderID_orders;
    }

    public void setOrderID_orders(int orderID_orders) {
        this.orderID_orders.set(orderID_orders);
    }

    public int getCustomer_id_orders() {
        return customer_id_orders.get();
    }

    public SimpleIntegerProperty customer_id_ordersProperty() {
        return customer_id_orders;
    }

    public void setCustomer_id_orders(int customer_id_orders) {
        this.customer_id_orders.set(customer_id_orders);
    }

    public int getDeliveryMethodId_orders() {
        return deliveryMethodId_orders.get();
    }

    public SimpleIntegerProperty deliveryMethodId_ordersProperty() {
        return deliveryMethodId_orders;
    }

    public void setDeliveryMethodId_orders(int deliveryMethodId_orders) {
        this.deliveryMethodId_orders.set(deliveryMethodId_orders);
    }

    public int getOrder_InformationID_orders() {
        return Order_InformationID_orders.get();
    }

    public SimpleIntegerProperty order_InformationID_ordersProperty() {
        return Order_InformationID_orders;
    }

    public void setOrder_InformationID_orders(int order_InformationID_orders) {
        this.Order_InformationID_orders.set(order_InformationID_orders);
    }

    public int getPayment_id_orders() {
        return Payment_id_orders.get();
    }

    public SimpleIntegerProperty payment_id_ordersProperty() {
        return Payment_id_orders;
    }

    public void setPayment_id_orders(int payment_id_orders) {
        this.Payment_id_orders.set(payment_id_orders);
    }
}
