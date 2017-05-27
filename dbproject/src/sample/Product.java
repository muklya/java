package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Hazardinho on 18.04.2017.
 */
public class Product{
    private SimpleIntegerProperty product_idP,discountP, priceperUnitP;
    private SimpleStringProperty product_nameP;
    public int getProduct_idP() {
        return product_idP.get();
    }

    public SimpleIntegerProperty product_idPProperty() {
        return product_idP;
    }

    public void setProduct_idP(int product_idP) {
        this.product_idP.set(product_idP);
    }

    public int getDiscountP() {
        return discountP.get();
    }

    public SimpleIntegerProperty discountPProperty() {
        return discountP;
    }

    public void setDiscountP(int discountP) {
        this.discountP.set(discountP);
    }

    public int getPriceperUnitP() {
        return priceperUnitP.get();
    }

    public SimpleIntegerProperty priceperUnitPProperty() {
        return priceperUnitP;
    }

    public void setPriceperUnitP(int priceperUnitP) {
        this.priceperUnitP.set(priceperUnitP);
    }

    public String getProduct_nameP() {
        return product_nameP.get();
    }

    public SimpleStringProperty product_namePProperty() {
        return product_nameP;
    }

    public void setProduct_nameP(String product_nameP) {
        this.product_nameP.set(product_nameP);
    }
    public Product(int product_id, int discount, int priceperUnit, String product_name){
        this.product_idP = new SimpleIntegerProperty(product_id);
        this.discountP = new SimpleIntegerProperty(discount);;
        this.priceperUnitP = new SimpleIntegerProperty(priceperUnit);;
        this.product_nameP = new SimpleStringProperty(product_name);;
    }
}