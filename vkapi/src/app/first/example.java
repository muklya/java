package app.first;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;

public class example extends Application{
    public static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";
    public static final String VK_AUTH_URL = "https://oauth.vk.com/authorize?client_id=5806465&display=page&redirect_uri=&scope=friends,messages,groups,wall,offline&response_type=token&v=5.59"; //TODO!!!
    public static String tokenUrl;
    public static String token;
    public static String id;

    public static void main(String[] args){
        launch(example.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final WebView view = new WebView();
        final WebEngine engine = view.getEngine();
        engine.load(VK_AUTH_URL);
        primaryStage.setScene(new Scene(view,660,370));
        primaryStage.show();

        engine.locationProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.startsWith(REDIRECT_URL)){
                    tokenUrl=newValue;
                    id = tokenUrl.substring(tokenUrl.lastIndexOf('&')+9,tokenUrl.length());
                    token = tokenUrl.substring(45,tokenUrl.indexOf('&'));
                    try {
                        PrintWriter write = new PrintWriter("file.txt", "UTF-8");
                        write.println(token+":"+id);
                        write.close();
                    }
                    catch (Exception x){x.printStackTrace();}
                    primaryStage.close();

                    Stage stage = new Stage();
                    Parent main = null;
                    try {
                        main = FXMLLoader.load(getClass().getResource("firstpage.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage.setScene(new Scene(main,600,400));
                    stage.show();
                }
            }

        });

    }

}