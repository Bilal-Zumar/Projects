import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class utf8ToString {

    public static void main(String[] args) throws IOException, ParseException {


        String original = "NzQxZTc0OTA4YjMxOWQ5YjhjZDc3ZjE2YTQ1MDViMzc%3D";

        byte[] utf8Bytes = original.getBytes("UTF-8");
        String roundTrip = new String(utf8Bytes, "UTF-8");

        System.out.println(roundTrip);
    }

}
