package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

/**
 * Created by Hazardinho on 18.04.2017.
 */
public class Payments{
    private SimpleStringProperty Pmethod_of_payment;
    private SimpleIntegerProperty Ppayment_id,Porder_id,Ppayment_amount,Ppayment_method_id;
    private SimpleObjectProperty Ppayment_date;

    public Payments(int payment_id,int order_id, int payment_amount, int payment_method_id,String method_of_payment,Date payment_date){
        this.Pmethod_of_payment = new SimpleStringProperty(method_of_payment);
        this.Ppayment_id = new SimpleIntegerProperty(payment_id);
        this.Porder_id = new SimpleIntegerProperty(order_id);
        this.Ppayment_amount = new SimpleIntegerProperty(payment_amount);
        this.Ppayment_method_id = new SimpleIntegerProperty(payment_method_id);
        this.Ppayment_date = new SimpleObjectProperty(payment_date);
    }

    public int getPpayment_id() {
        return Ppayment_id.get();
    }

    public SimpleIntegerProperty ppayment_idProperty() {
        return Ppayment_id;
    }

    public void setPpayment_id(int ppayment_id) {
        this.Ppayment_id.set(ppayment_id);
    }

    public int getPorder_id() {
        return Porder_id.get();
    }

    public SimpleIntegerProperty porder_idProperty() {
        return Porder_id;
    }

    public void setPorder_id(int porder_id) {
        this.Porder_id.set(porder_id);
    }

    public int getPpayment_amount() {
        return Ppayment_amount.get();
    }

    public SimpleIntegerProperty ppayment_amountProperty() {
        return Ppayment_amount;
    }

    public void setPpayment_amount(int ppayment_amount) {
        this.Ppayment_amount.set(ppayment_amount);
    }

    public int getPpayment_method_id() {
        return Ppayment_method_id.get();
    }

    public SimpleIntegerProperty ppayment_method_idProperty() {
        return Ppayment_method_id;
    }

    public void setPpayment_method_id(int ppayment_method_id) {
        this.Ppayment_method_id.set(ppayment_method_id);
    }

    public Object getPpayment_date() {
        return Ppayment_date.get();
    }

    public SimpleObjectProperty ppayment_dateProperty() {
        return Ppayment_date;
    }

    public void setPpayment_date(Object ppayment_date) {
        this.Ppayment_date.set(ppayment_date);
    }

    public String getPmethod_of_payment() {
        return Pmethod_of_payment.get();
    }

    public SimpleStringProperty pmethod_of_paymentProperty() {
        return Pmethod_of_payment;
    }

    public void setPmethod_of_payment(String pmethod_of_payment) {
        this.Pmethod_of_payment.set(pmethod_of_payment);
    }
}
