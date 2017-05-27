package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Controller {
    String url =  "jdbc:sqlserver://127.0.0.1:1434;databaseName=4_project;integratedSecurity=true;";
    ObservableList<Object> itemsInTableView = FXCollections.observableArrayList();
    ArrayList<String> tablesNames = new ArrayList<>();
    @FXML
    private Button showButton;
    @FXML
    private TableView tableResults;
    @FXML
    private ComboBox boxTables;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem firstItem;
    @FXML
    private MenuItem secondItem;
    @FXML
    private MenuItem thirdItem;
    @FXML
    private MenuItem fourthItem;
    @FXML
    private MenuItem closeItem;
    @FXML
    public void initialize(){
        closeItem.setOnAction(e->{
            Stage stage = (Stage) ((Node) menuBar).getScene().getWindow();
            stage.hide();
        });
        secondItem.setOnAction(event -> {
            Parent root2 = null;
            try {
                root2 = FXMLLoader.load(getClass().getResource("second.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage secondWindow = new Stage();
            secondWindow.setTitle("Second page");
            Stage stageFirst = (Stage) ((Node) menuBar).getScene().getWindow();
            stageFirst.hide();
            Scene scene = new Scene(root2, 600, 400);
            secondWindow.setScene(scene);
            secondWindow.show();
        });
        thirdItem.setOnAction(event->{
            Parent root3 = null;
            try {
                root3 = FXMLLoader.load(getClass().getResource("third.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage thirdWindow = new Stage();
            thirdWindow.setTitle("Third page");
            Stage stage = (Stage) ((Node) menuBar).getScene().getWindow();
            stage.hide();
            Scene scene = new Scene(root3, 680, 520);
            thirdWindow.setScene(scene);
            thirdWindow.show();

        });
        fourthItem.setOnAction(event->{
            Parent root4 = null;
            try {
                root4 = FXMLLoader.load(getClass().getResource("fourth.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage fourthWindow = new Stage();
            fourthWindow.setTitle("Fourth page");
            Stage stage = (Stage) ((Node) menuBar).getScene().getWindow();
            stage.hide();
            Scene scene = new Scene(root4, 600, 400);
            fourthWindow.setScene(scene);
            fourthWindow.show();

        });
        initializeComboBox();
        showButton.setOnAction(event -> {
            initializeResults();
        });
    }
    public void initializeComboBox(){
        ObservableList<String> listOfTables = FXCollections.observableArrayList();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url);
            Statement stt = con.createStatement();
            ResultSet res = stt.executeQuery("select * from information_schema.tables WHERE table_type='BASE TABLE'");
            while(res.next()){
                if(res.getString(3).equals("sysdiagrams")) continue;
                listOfTables.add(res.getString(3));
                tablesNames.add(res.getString(3));
            }
            boxTables.setItems(listOfTables);
        }
        catch (Exception e ){e.printStackTrace();}
    }
    public void initializeResults(){
        tableResults.getColumns().clear();
        itemsInTableView.clear();
        if(boxTables.getSelectionModel().selectedItemProperty().getValue()==null) System.out.println("select item please");
        else {
            String selectedItem = boxTables.getSelectionModel().selectedItemProperty().getValue().toString();
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(url);
                Statement stt = con.createStatement();
                ResultSet res = stt.executeQuery("select * from " + selectedItem);
                if(selectedItem.equals("CustomerDetails")){
                    TableColumn firstcl = new TableColumn("ID");
                    TableColumn secondcl = new TableColumn("Name");
                    TableColumn thirdcl = new TableColumn("Company");
                    TableColumn fourcl = new TableColumn("Address");
                    TableColumn fivecl = new TableColumn("Country");
                    TableColumn sixcl = new TableColumn("Phone");
                    TableColumn sevencl = new TableColumn("CreditCard");
                    TableColumn eightcl = new TableColumn("photo");
                    firstcl.setCellValueFactory(
                            new PropertyValueFactory<>("IDC"));
                    secondcl.setCellValueFactory(
                            new PropertyValueFactory<>("nameC"));
                    thirdcl.setCellValueFactory(
                            new PropertyValueFactory<>("companyC"));
                    fourcl.setCellValueFactory(
                            new PropertyValueFactory<>("addressC"));
                    fivecl.setCellValueFactory(
                            new PropertyValueFactory<>("countryC"));
                    sixcl.setCellValueFactory(
                            new PropertyValueFactory<>("phoneC"));
                    sevencl.setCellValueFactory(
                            new PropertyValueFactory<>("creditcardC"));
                    eightcl.setCellValueFactory(
                            new PropertyValueFactory<>("photoC"));
                    tableResults.getColumns().addAll(eightcl,firstcl,secondcl,thirdcl,fourcl,fivecl,sixcl,sevencl);


                    while (res.next()){
                        Image img = new Image(res.getBinaryStream("photo"));
                        ImageView imgView = new ImageView(img);
                        CustomerDetails details = new CustomerDetails(res.getInt("Customer_ID"), res.getString("name"),res.getString("company"),res.getString("address"),res.getString("country"),res.getString("phone"),res.getInt("CreditCard"),imgView);
                        itemsInTableView.add(details);
                    }

                }
                else if(selectedItem.equals("DeliveryInformation")){
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

                    tableResults.getColumns().addAll(firstcl,secondcl,thirdcl,fourcl,ninecl,fivecl,sixcl,sevencl,eightcl);

                    while (res.next()){
                        DeliveryInformation deliveryInformation = new DeliveryInformation(res.getInt("DeliveryMethodID"),res.getString("Delivery_name"),res.getString("Delivery_country"),
                                res.getDate("Delivery_date"),res.getString("Delivery_method"),res.getString("Delivery_address"),res.getInt("phone"),res.getInt("Shipping_cost"),res.getInt("tax"));
                        itemsInTableView.add(deliveryInformation);
                    }
                }
                else if(selectedItem.equals("Products")){
                    TableColumn firstcl = new TableColumn("Product_ID");
                    TableColumn secondcl = new TableColumn("Discount");
                    TableColumn thirdcl = new TableColumn("Product_Name");
                    TableColumn fourcl = new TableColumn("PricePerUnit");

                    firstcl.setCellValueFactory(
                            new PropertyValueFactory<>("product_idP"));
                    secondcl.setCellValueFactory(
                            new PropertyValueFactory<>("product_nameP"));
                    thirdcl.setCellValueFactory(
                            new PropertyValueFactory<>("discountP"));
                    fourcl.setCellValueFactory(
                            new PropertyValueFactory<>("priceperUnitP"));

                    tableResults.getColumns().addAll(firstcl,secondcl,thirdcl,fourcl);

                    while (res.next()){
                        Product product = new Product(res.getInt("Product_ID"),res.getInt("Discount"),res.getInt("PricePerUnit"),res.getString("product_name"));
                        itemsInTableView.add(product);
                    }
                }
                else if(selectedItem.equals("Payments")){
                    TableColumn firstcl = new TableColumn("Payment_ID");
                    TableColumn secondcl = new TableColumn("Order_ID");
                    TableColumn thirdcl = new TableColumn("Payment_amount");
                    TableColumn fourcl = new TableColumn("Payment_date");
                    TableColumn fivecl = new TableColumn("Payment_method_ID");
                    TableColumn sixcl = new TableColumn("Method_of_payment");

                    firstcl.setCellValueFactory(
                            new PropertyValueFactory<>("Ppayment_id"));
                    secondcl.setCellValueFactory(
                            new PropertyValueFactory<>("Porder_id"));
                    thirdcl.setCellValueFactory(
                            new PropertyValueFactory<>("Ppayment_amount"));
                    fourcl.setCellValueFactory(
                            new PropertyValueFactory<>("Ppayment_date"));
                    fivecl.setCellValueFactory(
                            new PropertyValueFactory<>("Ppayment_method_id"));
                    sixcl.setCellValueFactory(
                            new PropertyValueFactory<>("Pmethod_of_payment"));

                    tableResults.getColumns().addAll(firstcl,secondcl,thirdcl,fourcl,fivecl,sixcl);

                    while (res.next()){
                        Payments payment = new Payments(res.getInt("Payment_ID"),res.getInt("Order_ID"),res.getInt("Payment_amount"),
                                res.getInt("payment_method_ID"),res.getString("method_of_payment"),res.getDate("payment_date"));
                        itemsInTableView.add(payment);
                    }
                }
                else if(selectedItem.equals("Orders")){
                    TableColumn firstcl = new TableColumn("Order_ID");
                    TableColumn secondcl = new TableColumn("Customer_ID");
                    TableColumn thirdcl = new TableColumn("DeliveryMethodID");
                    TableColumn fourcl = new TableColumn("OrderInformationID");
                    TableColumn fivecl = new TableColumn("Payment_ID");

                    firstcl.setCellValueFactory(
                            new PropertyValueFactory<>("orderID_orders"));
                    secondcl.setCellValueFactory(
                            new PropertyValueFactory<>("customer_id_orders"));
                    thirdcl.setCellValueFactory(
                            new PropertyValueFactory<>("deliveryMethodId_orders"));
                    fourcl.setCellValueFactory(
                            new PropertyValueFactory<>("Order_InformationID_orders"));
                    fivecl.setCellValueFactory(
                            new PropertyValueFactory<>("Payment_id_orders"));

                    tableResults.getColumns().addAll(firstcl,secondcl,thirdcl,fourcl,fivecl);

                    while (res.next()){
                        Orders order = new Orders(res.getInt("Order_ID"),res.getInt("Customer_ID"),res.getInt("DeliveryMethodID"),res.getInt("Order_informationID"),res.getInt("Payment_ID"));
                        itemsInTableView.add(order);
                    }
                }
                else if(selectedItem.equals("OrderProcessing")){
                    TableColumn firstcl = new TableColumn("OrderInformationID");
                    TableColumn secondcl = new TableColumn("Order_ID");
                    TableColumn thirdcl = new TableColumn("Customer_ID");
                    TableColumn fourcl = new TableColumn("Product_ID");
                    TableColumn fivecl = new TableColumn("quantity");
                    TableColumn sixcl = new TableColumn("Order_description");
                    TableColumn sevencl = new TableColumn("Product_name");
                    TableColumn eightcl = new TableColumn("Discount");
                    TableColumn ninecl = new TableColumn("order_date");

                    firstcl.setCellValueFactory(
                            new PropertyValueFactory<>("orderinfoID_process"));
                    secondcl.setCellValueFactory(
                            new PropertyValueFactory<>("orderId_process"));
                    thirdcl.setCellValueFactory(
                            new PropertyValueFactory<>("Customer_id_process"));
                    fourcl.setCellValueFactory(
                            new PropertyValueFactory<>("product_id_process"));
                    fivecl.setCellValueFactory(
                            new PropertyValueFactory<>("quantity_process"));
                    sixcl.setCellValueFactory(
                            new PropertyValueFactory<>("description_process"));
                    sevencl.setCellValueFactory(
                            new PropertyValueFactory<>("product_name_process"));
                    eightcl.setCellValueFactory(
                            new PropertyValueFactory<>("discount_process"));
                    ninecl.setCellValueFactory(
                            new PropertyValueFactory<>("date_process"));

                    tableResults.getColumns().addAll(firstcl,secondcl,thirdcl,fourcl,ninecl,fivecl,sixcl,sevencl,eightcl);

                    while (res.next()){
                        Order_Processing order_processing = new Order_Processing(res.getInt("OrderInformationID"),res.getInt("Order_ID"),res.getInt("Customer_ID"),
                                res.getInt("Product_ID"),res.getInt("quantity"),res.getDate("order_date"),res.getInt("discount"),res.getString("Order_description"),res.getString("Product_name"));
                        itemsInTableView.add(order_processing);
                    }
                }
                tableResults.setItems(itemsInTableView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}