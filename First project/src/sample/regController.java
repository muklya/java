package sample;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class regController {
    @FXML
    private Button regbutton;
    @FXML
    private TextField nameinp;
    @FXML
    private TextField surnameinp;
    @FXML
    private TextField usernameinp;
    @FXML
    private PasswordField passwordinp;
    @FXML
    private TextField ageinp;
    @FXML
    private TextField emailInp;
    @FXML
    private Button chooseimage;
    @FXML
    private ProgressIndicator pb;
    @FXML
    private TextField cityinp;
    @FXML
    private GridPane gridReg;
    FileInputStream fis;
    @FXML
    public void initialize(){
        pb.setVisible(false);
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(nameinp.textProperty(),
                        surnameinp.textProperty(),
                        usernameinp.textProperty(),
                        ageinp.textProperty(),
                        passwordinp.textProperty(),
                        emailInp.textProperty(),
                        cityinp.textProperty()
                        );
            }

            @Override
            protected boolean computeValue() {
                return (nameinp.getText().isEmpty()
                        || surnameinp.getText().isEmpty()
                        || usernameinp.getText().isEmpty()
                        || ageinp.getText().isEmpty()
                        || passwordinp.getText().isEmpty()
                        || emailInp.getText().isEmpty()
                        || cityinp.getText().isEmpty()
                );
            }
        };
        regbutton.disableProperty().bind(bb);

        FileChooser chooser = new FileChooser();
        chooseimage.setOnAction(e->{
            Stage stage = (Stage) gridReg.getScene().getWindow();
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
        regbutton.setOnAction(e->{
            pb.setVisible(true);
            String nameinput = nameinp.getText();
            String surnameinput = surnameinp.getText();
            String usernameinput = usernameinp.getText();
            String passwordinput = passwordinp.getText();
            String cityinput = cityinp.getText();
            int ageinput = Integer.parseInt(ageinp.getText());
            String emailinput = emailInp.getText();
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "";
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, user, password);

                Statement stt = con.createStatement();
                stt.execute("USE users");
                String insertTableSQL = "INSERT INTO allusers"
                        + "(Name, Surname, login, password, age, email, city, image) VALUES"
                        + "(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.setString(1,nameinput);
                preparedStatement.setString(2,surnameinput);
                preparedStatement.setString(3,usernameinput);
                preparedStatement.setString(4,passwordinput);
                preparedStatement.setInt(5,ageinput);
                preparedStatement.setString(6,emailinput);
                preparedStatement.setString(7,cityinput);
                preparedStatement.setBinaryStream(8,fis);
                preparedStatement.executeUpdate();
                String adminEmail = "java.application@yandex.ru";
                String adminPassword = "jevbspuzhvrfevkc";
                Properties p = new Properties();
                p.put("mail.smtp.host","smtp.yandex.ru");
                p.put("mail.smtp.socketFactory.port",465);
                p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                p.put("mail.smtp.auth","true");
                p.put("mail.smtp.port",465);

                Session s = Session.getDefaultInstance(p,
                        new javax.mail.Authenticator(){
                            protected PasswordAuthentication getPasswordAuthentication(){
                                return new PasswordAuthentication(adminEmail,adminPassword);
                            }
                        }
                );
                try{
                    Message mess = new MimeMessage(s);
                    Multipart multipart = new MimeMultipart();
                    MimeBodyPart secondPart = new MimeBodyPart();
                    secondPart.setText("You can sign in with your username: "+usernameinput+" and password: "+passwordinput +"\n"+
                            "We congratulated you for registration in Application");
                    multipart.addBodyPart(secondPart);
                    mess.setFrom(new InternetAddress(adminEmail));
                    mess.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailinput));
                    mess.setSubject("Application java");
                    mess.setContent(multipart);
                    Transport.send(mess);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Registration");
                alert.setHeaderText(null);
                alert.setContentText("You are registred, check your email, you have message!");
                stt.close();
                con.close();
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()==ButtonType.OK){
                    pb.setVisible(false);
                    alert.close();
                    Stage thisstage = (Stage) gridReg.getScene().getWindow();
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
            }
            catch (Exception d){
                d.printStackTrace();
            }
        });
    }
}
