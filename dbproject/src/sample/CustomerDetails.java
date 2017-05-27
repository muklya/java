package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class CustomerDetails{
    private SimpleIntegerProperty IDC;
    private SimpleStringProperty nameC;
    private SimpleStringProperty companyC;
    public SimpleStringProperty addressC;
    public SimpleStringProperty countryC;
    public SimpleStringProperty phoneC;
    public SimpleIntegerProperty creditcardC;

    public Object getPhotoC() {
        return photoC.get();
    }

    public SimpleObjectProperty photoCProperty() {
        return photoC;
    }

    public void setPhotoC(Object photoC) {
        this.photoC.set(photoC);
    }

    public SimpleObjectProperty photoC;

    public String getAddressC() {
        return addressC.get();
    }

    public SimpleStringProperty addressCProperty() {
        return addressC;
    }

    public void setAddressC(String addressC) {
        this.addressC.set(addressC);
    }

    public String getCountryC() {
        return countryC.get();
    }

    public SimpleStringProperty countryCProperty() {
        return countryC;
    }

    public void setCountryC(String countryC) {
        this.countryC.set(countryC);
    }

    public String getPhoneC() {
        return phoneC.get();
    }

    public SimpleStringProperty phoneCProperty() {
        return phoneC;
    }

    public void setPhoneC(String phoneC) {
        this.phoneC.set(phoneC);
    }

    public int getCreditcardC() {
        return creditcardC.get();
    }

    public SimpleIntegerProperty creditcardCProperty() {
        return creditcardC;
    }

    public void setCreditcardC(int creditcardC) {
        this.creditcardC.set(creditcardC);
    }

    public CustomerDetails(int id, String name, String company, String address, String country, String phone, int creditCard, ImageView imgview){
        this.IDC = new SimpleIntegerProperty(id);
        this.nameC = new SimpleStringProperty(name);
        this.companyC = new SimpleStringProperty(company);
        this.addressC = new SimpleStringProperty(address);
        this.countryC = new SimpleStringProperty(country);
        this.phoneC = new SimpleStringProperty(phone);
        this.creditcardC = new SimpleIntegerProperty(creditCard);
        ImageView correctImg = imgview;
        correctImg.setFitWidth(128);
        correctImg.setFitHeight(72);
        this.photoC = new SimpleObjectProperty(correctImg);
    }

    public int getIDC() {
        return IDC.get();
    }

    public SimpleIntegerProperty IDCProperty() {
        return IDC;
    }

    public void setIDC(int IDC) {
        this.IDC.set(IDC);
    }

    public String getNameC() {
        return nameC.get();
    }

    public SimpleStringProperty nameCProperty() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC.set(nameC);
    }

    public String getCompanyC() {
        return companyC.get();
    }

    public SimpleStringProperty companyCProperty() {
        return companyC;
    }

    public void setCompanyC(String companyC) {
        this.companyC.set(companyC);
    }
}
