package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Controller {
    @FXML
    private TextField loginput;
    @FXML
    private TextField pasinput;
    @FXML
    private Button btnsign;
    @FXML
    private Button btnreg;
    @FXML
    private void initialize(){
        btnsign.setOnAction(event ->{
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "";
            String login = loginput.getText();
            String userpass = pasinput.getText();
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);

                Statement stt = con.createStatement();
                stt.execute("USE users");

                ResultSet res = stt.executeQuery("SELECT login,password,id FROM allusers");
                ArrayList<String> checker = new ArrayList<String>();
                while (res.next()){
                    if(res.getString("login").equals(login) & res.getString("password").equals(userpass)) {
                        checker.add("ok");
                        try{
                            PrintWriter write = new PrintWriter("file.txt","UTF-8");
                            write.println(res.getString("login"));
                            write.close();
                        }
                        catch (Exception l){}
                    }
                }
                if(checker.contains("ok")){
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                    Stage mainwindow = new Stage();
                    mainwindow.setTitle("MAIN");

                    Scene scene = new Scene(root, 1280, 600);
                    mainwindow.setScene(scene);
                    mainwindow.show();
                }
                else
                    System.out.println("wrong");

                res.close();
                stt.close();
                con.close();

            }
            catch (Exception d){
                d.printStackTrace();
            }
        });
        btnreg.setOnAction(event ->{
            Parent root2 = null;
            try {
                root2 = FXMLLoader.load(getClass().getResource("reg.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage regwindow = new Stage();
            regwindow.setTitle("Registration page");
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Scene scene = new Scene(root2, 800, 500);
            regwindow.setScene(scene);
            regwindow.show();
        });
    }
}