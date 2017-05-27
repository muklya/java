package app.first;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Hazardinho on 04.01.2017.
 */
public class firstpageCtrl{
    @FXML
    private Label profilePhoto;
    @FXML
    private Label profileName;
    @FXML
    private Label profileSurname;
    @FXML
    private Label age;
    @FXML
    private Label country;
    @FXML
    private Label citylbl;
    @FXML
    private Button deleteDialogs;
    @FXML
    public void initialize() throws Exception{
        //read file with info about token and id of Account
        Scanner in = new Scanner(new File("file.txt"));
        String[] tokenANDid = in.nextLine().split(":");
        String token = tokenANDid[0];
        String id = tokenANDid[1];

        //Create request for get info about users with photo,nickname,city,country
        String address  = "https://api.vk.com/method/users.get?user_ids="+id+"&fields=photo_100,city,nickname,country,bdate&access_token="+token;
        JSONParser parser = new JSONParser();
        URL url = new URL(address);
        InputStream inputStream = url.openStream();
        Thread.sleep(2000);
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String txt = ReadJson(rd);
        JSONObject obj = (JSONObject) parser.parse(txt);
        JSONArray response = (JSONArray) obj.get("response");
        JSONObject info = (JSONObject)response.get(0);
        //Set info into Labels(photo,name,surname)
        ImageView imgview = new ImageView(new Image((String)info.get("photo_100")));
        profilePhoto.setGraphic(imgview);
        profileName.setText(info.get("first_name")+"");
        profileSurname.setText(info.get("last_name")+"");
        age.setText(info.get("bdate")+"");
        //from info we have only number of country and city we need to get name of this city and country, for get it we send request

        //Firstly we send request about country
        if(!(info.get("country").toString().equals("0") && info.get("city").toString().equals("0"))){
            String getCountryAddress = "https://api.vk.com/method/database.getCountriesById?country_ids="+info.get("country")+"&access_token="+token;
            URL countryAddr = new URL(getCountryAddress);
            InputStream countryStream = countryAddr.openStream();
            Thread.sleep(2000);
            BufferedReader rdCountry = new BufferedReader(new InputStreamReader(countryStream, Charset.forName("UTF-8")));
            String txtCountry = ReadJson(rdCountry);
            JSONObject objCountry = (JSONObject) parser.parse(txtCountry);
            JSONArray responseCountry = (JSONArray) objCountry.get("response");
            JSONObject infoCountry = (JSONObject)responseCountry.get(0);
            country.setText(infoCountry.get("name")+"");

            //Secondly we send request about city
            String getCityAddress = "https://api.vk.com/method/database.getCitiesById?city_ids="+info.get("city")+"&access_token="+token;
            URL cityAddr = new URL(getCityAddress);
            InputStream cityStream = cityAddr.openStream();
            BufferedReader rdCity = new BufferedReader(new InputStreamReader(cityStream, Charset.forName("UTF-8")));
            String txtCity = ReadJson(rdCity);
            JSONObject objCity = (JSONObject) parser.parse(txtCity);
            JSONArray responseCity = (JSONArray) objCity.get("response");
            JSONObject infoCity = (JSONObject)responseCity.get(0);
            citylbl.setText(infoCity.get("name")+"");
        }
    }
    private static String ReadJson(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
