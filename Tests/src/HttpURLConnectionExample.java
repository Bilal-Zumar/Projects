
import com.sun.deploy.net.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpURLConnectionExample {

    public static void main(String[] args) throws IOException {
        sendGET();
        System.out.println("GET DONE");
    }

    private static void sendGET() throws IOException {
        URL obj = new URL("http://131.226.95.6:8083/api/oauth2/authorize?app_id=206&app_secret=f0fc64b5eda8f534&response_type=token&redirect_uri=1");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("Response: "+ con.getURL().getQuery());
        System.out.println("GET Response Code :: " + responseCode);

        String[] params = con.getURL().getQuery().split("&");
        System.out.println(params[0].split("=")[1]);

        System.out.println(con.getURL().getQuery().split("&")[0].split("=")[1]);

        String url = con.getURL().toString();
        System.out.println(new String(url.getBytes("UTF-8"),"ASCII"));
        System.out.println(java.net.URLDecoder.decode(con.getURL().getQuery().split("&")[0].split("=")[1], StandardCharsets.UTF_8.name()));

    }
}