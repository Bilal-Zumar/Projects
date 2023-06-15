import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class Datesetting {
    public static void main(String[] args) throws IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
        System.out.println(format.format(Timestamp.from(Instant.now())));

        System.out.println(Instant.now());
        System.out.println(Timestamp.from(Instant.now()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH24mmssSS");
//        System.out.println(formatter.parse(Timestamp.from(Instant.now()).toString()));

//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(LocalDateTime.now().toString()));
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format1.format(Timestamp.from(Instant.now())));
    }
}
