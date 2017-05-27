import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
/**
 * Created by Hazardinho on 03.01.2017.
 */
public class insta {
    public static void main(String[] args) throws Exception{
        String token = "2118686786.64b167b.9305ef8928534ce687a2d07940e71b06";
        String mediastr = "https://api.instagram.com/v1/users/self/media/recent/?access_token="+token;
        String followerSTR = "https://api.instagram.com/v1/users/self/followed-by?access_token="+token;
        String asd = "https://api.instagram.com/v1/tags/SDU/media/recent?access_token="+token;
        String follow = "https://api.instagram.com/v1/users/self/follows?access_token="+token;
        JSONParser parser = new JSONParser();
        URL url = new URL(follow);
        InputStream inputStream = url.openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String txt = readjson(rd);
        JSONObject obj = (JSONObject) parser.parse(txt);
        System.out.println(obj);
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
