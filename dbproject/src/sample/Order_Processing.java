package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

/**
 * Created by Hazardinho on 18.04.2017.
 */
public class Order_Processing {
    private SimpleIntegerProperty orderinfoID_process,orderId_process,Customer_id_process,product_id_process,quantity_process,discount_process;
    private SimpleStringProperty description_process,product_name_process;
    private SimpleObjectProperty date_process;
    public Order_Processing(int orderinfoID, int orderId, int customer_id, int product_id, int quantity, Date date, int discount, String description, String product_name){
        this.orderinfoID_process = new SimpleIntegerProperty(orderinfoID);
        this.orderId_process = new SimpleIntegerProperty(orderId);
        this.Customer_id_process = new SimpleIntegerProperty(customer_id);
        this.product_id_process = new SimpleIntegerProperty(product_id);
        this.quantity_process = new SimpleIntegerProperty(quantity);
        this.date_process = new SimpleObjectProperty(date);
        this.discount_process = new SimpleIntegerProperty(discount);
        this.description_process = new SimpleStringProperty(description);
        this.product_name_process = new SimpleStringProperty(product_name);
    }

    public int getOrderinfoID_process() {
        return orderinfoID_process.get();
    }

    public SimpleIntegerProperty orderinfoID_processProperty() {
        return orderinfoID_process;
    }

    public void setOrderinfoID_process(int orderinfoID_process) {
        this.orderinfoID_process.set(orderinfoID_process);
    }

    public int getOrderId_process() {
        return orderId_process.get();
    }

    public SimpleIntegerProperty orderId_processProperty() {
        return orderId_process;
    }

    public void setOrderId_process(int orderId_process) {
        this.orderId_process.set(orderId_process);
    }

    public int getCustomer_id_process() {
        return Customer_id_process.get();
    }

    public SimpleIntegerProperty customer_id_processProperty() {
        return Customer_id_process;
    }

    public void setCustomer_id_process(int customer_id_process) {
        this.Customer_id_process.set(customer_id_process);
    }

    public int getProduct_id_process() {
        return product_id_process.get();
    }

    public SimpleIntegerProperty product_id_processProperty() {
        return product_id_process;
    }

    public void setProduct_id_process(int product_id_process) {
        this.product_id_process.set(product_id_process);
    }

    public int getQuantity_process() {
        return quantity_process.get();
    }

    public SimpleIntegerProperty quantity_processProperty() {
        return quantity_process;
    }

    public void setQuantity_process(int quantity_process) {
        this.quantity_process.set(quantity_process);
    }

    public int getDiscount_process() {
        return discount_process.get();
    }

    public SimpleIntegerProperty discount_processProperty() {
        return discount_process;
    }

    public void setDiscount_process(int discount_process) {
        this.discount_process.set(discount_process);
    }

    public String getDescription_process() {
        return description_process.get();
    }

    public SimpleStringProperty description_processProperty() {
        return description_process;
    }

    public void setDescription_process(String description_process) {
        this.description_process.set(description_process);
    }

    public String getProduct_name_process() {
        return product_name_process.get();
    }

    public SimpleStringProperty product_name_processProperty() {
        return product_name_process;
    }

    public void setProduct_name_process(String product_name_process) {
        this.product_name_process.set(product_name_process);
    }

    public Object getDate_process() {
        return date_process.get();
    }

    public SimpleObjectProperty date_processProperty() {
        return date_process;
    }

    public void setDate_process(Object date_process) {
        this.date_process.set(date_process);
    }
}