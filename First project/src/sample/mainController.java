package sample;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;

public class mainController {
    @FXML
    GridPane paneM;
    @FXML
    ListView lst;
    @FXML
    MenuBar menuBar;
    @FXML
    MenuItem exitfrommain;
    @FXML
    MenuItem editprof;
    @FXML
    MenuItem deleteprof;
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    String log = "";
    FileInputStream fis;
    @FXML
    public void initialize(){
        lst.setId("lst");
        lst.getStylesheets().add(Main.class.getResource("vbox.css").toExternalForm());
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stt = con.createStatement();
            stt.execute("USE users");
            ResultSet res = stt.executeQuery("SELECT * FROM allusers");
            ObservableList<Profile> olist = FXCollections.observableArrayList();
            while(res.next()){
                Profile prof = new Profile(res.getString("Name"),res.getString("Surname"),res.getString("login"),res.getString("email"),
                        res.getString("city"),res.getInt("age"),res.getBinaryStream("image"));
                olist.add(prof);
            }
            lst.setItems(olist);
            lst.setCellFactory(new Callback<ListView<Profile>,ListCell<Profile>>(){
                @Override
                public ListCell<Profile> call(ListView<Profile> param) {
                    return new ProfileCell();
                }
            });
        }
        catch (Exception mk){}
        try {
            Scanner file = new Scanner(new File("file.txt"));
            log = file.nextLine();
            PrintWriter writer = new PrintWriter("file.txt");
            writer.print("");
            writer.close();
        }
        catch (Exception l){}
        exitfrommain.setOnAction(e->{
            Platform.exit();}
        );
        deleteprof.setOnAction(events -> {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);

                Statement stt = con.createStatement();
                stt.execute("USE users");
                stt.executeUpdate("DELETE FROM allusers where login='" + log + "'");
                Stage thisstage = (Stage) paneM.getScene().getWindow();
                thisstage.hide();
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                primaryStage.setTitle("Hello, Sign in");
                Scene scene = new Scene(root, 1280, 600);
                scene.getStylesheets().add
                        (Main.class.getResource("Login.css").toExternalForm());
                root.requestFocus();
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            catch (Exception xz){}
        });
        editprof.setOnAction(event -> {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Editer");
                alert.setHeaderText("Edit properties of your profile");
                alert.setContentText("Edit your account.");
                GridPane gridPane = new GridPane();
                Label lblname = new Label("Name: ");
                Label lblsurname = new Label("Surname: ");
                Label lblpassword = new Label("Password: ");
                Label lblage = new Label("Age: ");
                Label lblcity = new Label("City: ");
                Label lblimage = new Label("Image: ");
                TextField namefield = new TextField();
                TextField surnamefield = new TextField();
                TextField passwordfield = new TextField();
                TextField agefield = new TextField();
                TextField cityfield = new TextField();
                Button chooseimage = new Button("Choose an image");
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setPadding(new Insets(10,10,10,10));
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.add(lblname,0,0);
                gridPane.add(lblsurname,0,1);
                gridPane.add(lblpassword,0,2);
                gridPane.add(lblage,0,3);
                gridPane.add(lblcity,0,4);
                gridPane.add(lblimage,0,5);

                gridPane.add(namefield,1,0);
                gridPane.add(surnamefield,1,1);
                gridPane.add(passwordfield,1,2);
                gridPane.add(agefield,1,3);
                gridPane.add(cityfield,1,4);
                gridPane.add(chooseimage,1,5);
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);

                Statement stt = con.createStatement();
                stt.execute("USE users");

                ResultSet res = stt.executeQuery("SELECT * FROM allusers where login='"+log+"'");
                while (res.next()){
                    namefield.setText(res.getString("Name"));
                    surnamefield.setText(res.getString("Surname"));
                    passwordfield.setText(res.getString("password"));
                    agefield.setText(res.getString("age"));
                    cityfield.setText(res.getString("city"));
                    ImageView imgv = new ImageView(new Image(res.getBinaryStream("image")));
                    imgv.setFitHeight(128);
                    imgv.setFitWidth(128);
                    lblimage.setGraphic(imgv);
                }
                alert.getDialogPane().setExpandableContent(gridPane);
                alert.getDialogPane().setMinWidth(500);
                ButtonType buttonTypeOne = new ButtonType("Edit");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
                FileChooser chooser = new FileChooser();
                chooseimage.setOnAction(e->{
                    Stage stage = (Stage) gridPane.getScene().getWindow();
                    File file = chooser.showOpenDialog(stage);
                    if(file !=null){
                        try {
                            fis = new FileInputStream(file);
                        }
                        catch (Exception ex){}
                    }
                    if(file==null){
                        System.out.println("dont change a file");
                    }
                });
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {

                    String editInfo = "Update allusers SET Name = ?, Surname = ?, password = ?, city = ?, image = ?";
                    PreparedStatement statements = con.prepareStatement(editInfo);
                    statements.setString(1,namefield.getText());
                    statements.setString(2,surnamefield.getText());
                    statements.setString(3,passwordfield.getText());
                    statements.setString(4,cityfield.getText());
                    statements.setBinaryStream(5,fis);
                    statements.executeUpdate();
                }
                else{
                    alert.close();
                }
            }
            catch (Exception x){}
        });
    }
    public void showinfo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account information");
        alert.setHeaderText(null);
        alert.setContentText(null);
        alert.getDialogPane().getStyleClass().add("dialog");
        alert.getDialogPane().getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
        GridPane grd = new GridPane();
        Label picture = new Label();
        Label name = new Label();
        Label surname = new Label();
        Label Age = new Label();
        Label login = new Label();
        Label email = new Label();
        Label city = new Label();
        grd.add(picture,0,0);
        grd.add(name,0,1);
        grd.add(surname,0,2);
        grd.add(Age,0,3);
        grd.add(login,0,4);
        grd.add(city,0,5);
        grd.add(email,0,6);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stt = con.createStatement();
            stt.execute("USE users");

            String getInfo = "Select * from allusers";
            ResultSet res = stt.executeQuery(getInfo);
            while (res.next()){
                if(res.getString("login").equals(log)){
                    login.setText(log);
                    name.setText(res.getString("Name"));
                    surname.setText(res.getString("Surname"));
                    email.setText(res.getString("email"));
                    city.setText(res.getString("city"));
                    Age.setText(res.getString("age"));
                    InputStream binaryStream = res.getBinaryStream("image");
                    Image img = new Image(binaryStream);
                    ImageView imgview = new ImageView(img);
                    imgview.setFitHeight(128);
                    imgview.setFitWidth(128);
                    picture.setGraphic(imgview);
                    alert.getDialogPane().setExpandableContent(grd);
                }
            }
        }
        catch (Exception p){}
        alert.getDialogPane().setExpandableContent(grd);
        alert.showAndWait();
    }
}
class Profile{
    String name,surname,login,email,city;
    int age;
    ImageView viewimage;
    public Profile(String name,String surname,String login,String email,String city, int age, InputStream binaryStream){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.city = city;
        this.age = age;
        Image img = new Image(binaryStream);
        ImageView imgvview = new ImageView(img);
        imgvview.setFitHeight(128);
        imgvview.setFitWidth(128);
        this.viewimage = imgvview;
    }
    public String toString(){
        return viewimage+" "+name+" "+surname+" "+login+" "+email+" "+city+" "+age;
    }
}
class ProfileCell extends ListCell<Profile>{
    public void updateItem(Profile profile, boolean empty) {
        super.updateItem(profile, empty);
        if(profile!=null){
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(5,5,5,5));
            VBox vb = new VBox();
            vb.setAlignment(Pos.CENTER_LEFT);
            vb.setPadding(new Insets(5,5,5,150));
            Label img = new Label();
            img.setGraphic(profile.viewimage);
            img.setPadding(new Insets(5,5,5,5));
            Label name = new Label("Name: "+profile.name);
            Label surname = new Label("Surname: "+profile.surname);
            Label login = new Label("Login: "+profile.login);
            Label age = new Label("Age: "+String.valueOf(profile.age));
            Label email = new Label("Email: "+profile.name);
            Label city = new Label("City: "+profile.city);
            JSONParser parser = new JSONParser();
            long temp = 0;
            String humidity = "50%";
            long mph = 0;
            try {
                URL url = new URL("http://api.wunderground.com/api/32182ba52eabf50d/conditions/q/KZ/" + profile.city + ".json");
                InputStream inputStream = url.openStream();
                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                    String jsonText = readAll(rd);
                    try {
                        JSONObject json = (JSONObject) parser.parse(jsonText);
                        JSONObject response = (JSONObject) json.get("current_observation");

                        if (!(response.get(("temp_c")) == null)) {
                            temp = (long) response.get("temp_c");
                        }
                        if (!(response.get("relative_humidity") == null)) {
                            humidity = (String) response.get("relative_humidity");
                        }
                        if (!(response.get("wind_mph") == null))
                            mph = (long) response.get("wind_mph");
                        //System.out.println(temp);
                        //System.out.println(humidity);
                        //System.out.println(mph);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } finally {
                    inputStream.close();
                }
            }
            catch (Exception z){}
            VBox secondVbox = new VBox();
            secondVbox.setAlignment(Pos.BASELINE_CENTER);
            Label fortemp = new Label("Temperature: " + temp +" C");
            Label forwind = new Label("Wind speed: " + mph + " mph");
            Label forhum = new Label("Humidity: " + humidity);
            vb.getStylesheets().add(Main.class.getResource("alert.css").toExternalForm());
            vb.getChildren().addAll(name,surname,login,age,email,city);
            secondVbox.getStylesheets().add(Main.class.getResource("vbox.css").toExternalForm());
            secondVbox.getChildren().addAll(fortemp,forwind,forhum);
            hbox.getChildren().addAll(img,vb,secondVbox);
            hbox.setAlignment(Pos.BOTTOM_CENTER);
            setGraphic(hbox);
        }
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}