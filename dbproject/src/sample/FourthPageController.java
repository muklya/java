package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Hazardinho on 27.04.2017.
 */
public class FourthPageController {
    String url =  "jdbc:sqlserver://127.0.0.1:1434;databaseName=4_project;integratedSecurity=true;";
    ObservableList<Object> itemsInTableView = FXCollections.observableArrayList();
    @FXML
    private ComboBox box;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TableView tablezhe;
    @FXML
    public void initialize(){
        ObservableList listItems = FXCollections.observableArrayList();
        listItems.add("Free delivery");
        listItems.add("Paid delivery");
        box.setItems(listItems);
        box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                itemsInTableView.clear();
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url);
                    Statement stt = con.createStatement();
                    ResultSet res = null;
                    Boolean checked = checkBox.selectedProperty().getValue();
                    if(checked){
                        if (newValue.equals("Free delivery")) {
                            res = stt.executeQuery("select * from DeliveryInformation WHERE Shipping_cost=0 and year(Delivery_date)=2017");

                        } else {
                            res = stt.executeQuery("select * from DeliveryInformation WHERE Shipping_cost>0 and year(Delivery_date)=2017");
                        }
                    }
                    else{
                        if (newValue.equals("Free delivery")) {
                            res = stt.executeQuery("select * from DeliveryInformation WHERE Shipping_cost=0");

                        } else {
                            res = stt.executeQuery("select * from DeliveryInformation WHERE Shipping_cost>0");
                        }
                    }

                    TableColumn firstcl = new TableColumn("DeliveryMethod_ID");
                    TableColumn secondcl = new TableColumn("Delivery_Name");
                    TableColumn thirdcl = new TableColumn("Delivery_country");
                    TableColumn fourcl = new TableColumn("Delivery_method");
                    TableColumn fivecl = new TableColumn("Delivery_address");
                    TableColumn sixcl = new TableColumn("phone");
                    TableColumn sevencl = new TableColumn("shipping_cost");
                    TableColumn eightcl = new TableColumn("tax");
                    TableColumn ninecl = new TableColumn("Delivery_Date");

                    firstcl.setCellValueFactory(
                            new PropertyValueFactory<>("idD"));
                    secondcl.setCellValueFactory(
                            new PropertyValueFactory<>("nameD"));
                    thirdcl.setCellValueFactory(
                            new PropertyValueFactory<>("countryD"));
                    fourcl.setCellValueFactory(
                            new PropertyValueFactory<>("methodD"));
                    fivecl.setCellValueFactory(
                            new PropertyValueFactory<>("addressD"));
                    sixcl.setCellValueFactory(
                            new PropertyValueFactory<>("phoneD"));
                    sevencl.setCellValueFactory(
                            new PropertyValueFactory<>("shippingCostD"));
                    eightcl.setCellValueFactory(
                            new PropertyValueFactory<>("taxD"));
                    ninecl.setCellValueFactory(
                            new PropertyValueFactory<>("dateD"));

                    tablezhe.getColumns().addAll(firstcl, secondcl, thirdcl, fourcl, ninecl, fivecl, sixcl, sevencl, eightcl);

                    while (res.next()) {
                        DeliveryInformation deliveryInformation = new DeliveryInformation(res.getInt("DeliveryMethodID"), res.getString("Delivery_name"), res.getString("Delivery_country"),
                                res.getDate("Delivery_date"), res.getString("Delivery_method"), res.getString("Delivery_address"), res.getInt("phone"), res.getInt("Shipping_cost"), res.getInt("tax"));
                        itemsInTableView.add(deliveryInformation);
                    }
                }
                catch (Exception e){e.printStackTrace();}
                tablezhe.setItems(itemsInTableView);
            }
        });
    }
}
