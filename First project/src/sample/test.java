package sample;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Executable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by Hazardinho on 06.12.2016.
 */
public class test {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser();
        Scanner in = new Scanner(System.in);
        String city = in.nextLine();
        URL url = new URL("http://api.wunderground.com/api/5cbb53b39d590b53/conditions/q/KZ/"+city+".json");
        InputStream inputStream = url.openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            try {
                JSONObject json = (JSONObject) parser.parse(jsonText);
                JSONObject response = (JSONObject) json.get("current_observation");
                long temp = 0;
                String humidity = "";
                long mph = 0;
                if(!(response.get(("temp_c"))==null)){
                    temp = (long)response.get("temp_c");    
                }
                if(!(response.get("relative_humidity")==null)){
                    humidity = (String) response.get("relative_humidity");
                }
                if(!(response.get("wind_mph")==null))
                    mph = (long) response.get("wind_mph");
                System.out.println(temp);
                System.out.println(humidity);
                System.out.println(mph);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } finally {
            inputStream.close();
        }

        /*String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "";
        try {
            Object obj = parser.parse(new FileReader("c:\\\\movies.json"));
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stt = con.createStatement();
            stt.execute("USE test");

            String insertTableSQL = "INSERT INTO jsons"
                    + "(jsonobj) VALUES"
                    + "(?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, (String) obj.toString());
            preparedStatement.executeUpdate();
            ResultSet res = stt.executeQuery("SELECT jsonobj FROM jsons");
            while (res.next()){
                System.out.println(res.getString("jsonobj"));
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","DEN");
            jsonObject.put("surname","asd");
            jsonObject.put("age",18);
            jsonObject.put("email","lala");
            System.out.println(jsonObject.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
