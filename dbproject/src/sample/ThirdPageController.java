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
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Hazardinho on 20.04.2017.
 */
public class ThirdPageController {
    String url =  "jdbc:sqlserver://127.0.0.1:1434;databaseName=4_project;integratedSecurity=true;";
    ObservableList<Object> itemsInTableView = FXCollections.observableArrayList();
    ObservableList<Object> itemsInSecondTableView = FXCollections.observableArrayList();


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
    private TableView firstTBL;
    @FXML
    private TableView secondTBL;
    @FXML
    public void initialize(){
        initializeFirstTBL();
        firstItem.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage firstWindow = new Stage();
            firstWindow.setTitle("First page");
            Stage stageFirst = (Stage) ((Node) menuBar).getScene().getWindow();
            stageFirst.hide();
            Scene scene = new Scene(root, 600, 480);
            firstWindow.setScene(scene);
            firstWindow.show();
        });
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

    }
    public void initializeFirstTBL(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url);
            Statement stt = con.createStatement();
            ResultSet res = stt.executeQuery("select * from CustomerDetails");
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
            firstTBL.getColumns().addAll(eightcl, firstcl, secondcl, thirdcl, fourcl, fivecl, sixcl, sevencl);


            while (res.next()) {
                Image img = new Image(res.getBinaryStream("photo"));
                ImageView imgView = new ImageView(img);
                CustomerDetails details = new CustomerDetails(res.getInt("Customer_ID"), res.getString("name"), res.getString("company"), res.getString("address"), res.getString("country"), res.getString("phone"), res.getInt("CreditCard"), imgView);
                itemsInTableView.add(details);
            }
        }catch (Exception e){e.printStackTrace();}
        firstTBL.setItems(itemsInTableView);
        firstTBL.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerDetails>()  {
            @Override
            public void changed(ObservableValue observable, CustomerDetails oldValue, CustomerDetails newValue) {
                itemsInSecondTableView.clear();
                secondTBL.getColumns().clear();
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url);
                    Statement stt = con.createStatement();
                    ResultSet res = stt.executeQuery("select * from OrderProcessing where Customer_ID ="+newValue.getIDC());
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

                    secondTBL.getColumns().addAll(firstcl,secondcl,thirdcl,fourcl,ninecl,fivecl,sixcl,sevencl,eightcl);

                    while (res.next()){
                        Order_Processing order_processing = new Order_Processing(res.getInt("OrderInformationID"),res.getInt("Order_ID"),res.getInt("Customer_ID"),
                                res.getInt("Product_ID"),res.getInt("quantity"),res.getDate("order_date"),res.getInt("discount"),res.getString("Order_description"),res.getString("Product_name"));
                        itemsInSecondTableView.add(order_processing);
                    }

                }catch (Exception e){e.printStackTrace();}
                secondTBL.setItems(itemsInSecondTableView);
            }
        });
    }
}
