import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
/**
 * Created by Hazardinho on 03.01.2017.
 */
public class vk{
    public static void main(String[] args) throws Exception {
        String token = "082e9d77ecea7e6b3b1c211eb96fc132812dba67224075083699e6bd097ebcc2d0f15057f33a6b35a8c95";
        String address  = "https://api.vk.com/method/friends.get?access_token="+token;
        JSONParser parser = new JSONParser();
        URL url = new URL(address);
        InputStream inputStream = url.openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String txt = readjson(rd);
        JSONObject obj = (JSONObject) parser.parse(txt);
        JSONArray arrOfFriends = (JSONArray) obj.get("response");
        System.out.println(arrOfFriends);
        /*SEND MESSAGE FOR ALL FRIENDS
        for(int i = 0;i<arrOfFriends.size();i++){
            String mes = "https://api.vk.com/method/messages.send?access_token="+token+"&user_id="+arrOfFriends.get(i)+"&message=lolka&title=asd";
            URL sendurl = new URL(mes);
            InputStream res = sendurl.openStream();
            BufferedReader readers = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));
            String txt2 = readjson(readers);
            System.out.println(txt2);
            Thread.sleep(2);
        }*/

    }
    private static String readjson(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}