package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

/**
 * Created by Hazardinho on 18.04.2017.
 */
public class DeliveryInformation{
    private SimpleIntegerProperty idD;
    private SimpleStringProperty nameD;
    private SimpleStringProperty countryD;
    private SimpleStringProperty methodD;
    private SimpleStringProperty addressD;
    private SimpleIntegerProperty phoneD;
    private SimpleIntegerProperty shippingCostD;
    private SimpleIntegerProperty taxD;
    private SimpleObjectProperty dateD;

    public String getCountryD() {
        return countryD.get();
    }

    public SimpleStringProperty countryDProperty() {
        return countryD;
    }

    public void setCountryD(String countryD) {
        this.countryD.set(countryD);
    }

    public String getMethodD() {
        return methodD.get();
    }

    public SimpleStringProperty methodDProperty() {
        return methodD;
    }

    public void setMethodD(String methodD) {
        this.methodD.set(methodD);
    }

    public String getAddressD() {
        return addressD.get();
    }

    public SimpleStringProperty addressDProperty() {
        return addressD;
    }

    public void setAddressD(String addressD) {
        this.addressD.set(addressD);
    }

    public int getPhoneD() {
        return phoneD.get();
    }

    public SimpleIntegerProperty phoneDProperty() {
        return phoneD;
    }

    public void setPhoneD(int phoneD) {
        this.phoneD.set(phoneD);
    }

    public int getShippingCostD() {
        return shippingCostD.get();
    }

    public SimpleIntegerProperty shippingCostDProperty() {
        return shippingCostD;
    }

    public void setShippingCostD(int shippingCostD) {
        this.shippingCostD.set(shippingCostD);
    }

    public int getTaxD() {
        return taxD.get();
    }

    public SimpleIntegerProperty taxDProperty() {
        return taxD;
    }

    public void setTaxD(int taxD) {
        this.taxD.set(taxD);
    }

    public Object getDateD() {
        return dateD.get();
    }

    public SimpleObjectProperty dateDProperty() {
        return dateD;
    }

    public void setDateD(Object dateD) {
        this.dateD.set(dateD);
    }
    public DeliveryInformation(int id, String name, String country, Date date, String method, String address, int phone, int shipCost, int tax){
        this.idD = new SimpleIntegerProperty(id);
        this.nameD = new SimpleStringProperty(name);
        this.addressD = new SimpleStringProperty(address);
        this.countryD = new SimpleStringProperty(country);
        this.phoneD = new SimpleIntegerProperty(phone);;
        this.dateD = new SimpleObjectProperty(date);
        this.methodD = new SimpleStringProperty(method);
        this.shippingCostD = new SimpleIntegerProperty(shipCost);;
        this.taxD = new SimpleIntegerProperty(tax);
    }

    public int getIdD() {
        return idD.get();
    }

    public SimpleIntegerProperty idDProperty() {
        return idD;
    }

    public void setIdD(int idD) {
        this.idD.set(idD);
    }

    public String getNameD() {
        return nameD.get();
    }

    public SimpleStringProperty nameDProperty() {
        return nameD;
    }

    public void setNameD(String nameD) {
        this.nameD.set(nameD);
    }
}
