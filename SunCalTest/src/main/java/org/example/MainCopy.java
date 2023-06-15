package org.example;

import org.shredzone.commons.suncalc.SunPosition;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.Duration;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MainCopy {

    static String getGMTTimeZoneName(String gmt) {
        for (int i = 0; i < TimeZone.getAvailableIDs().length; i++) {
            TimeZone tz = TimeZone.getTimeZone(TimeZone.getAvailableIDs()[i]);

            long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
            long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) - TimeUnit.HOURS.toMinutes(hours);
            minutes = Math.abs(minutes);

            String result = "";
            if (hours > 0) {
                result = String.format("GMT+%d:%02d", hours, minutes);
            } else {
                result = String.format("GMT%d:%02d", hours, minutes);
            }
            System.out.println(tz.getID() + " " + gmt);
            if (result.equals(gmt)) {
//                System.out.println(tz.getID());
                return tz.getID();
            }
        }
        return null;
    }

    public static void main(String[] args) {

//        getGMTTimeZoneName("");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Copenhagen"));
        calendar.set(2018, 12, 11);
        long length = ca.rmen.sunrisesunset.SunriseSunset.getDayLength(calendar, 55.386964, 10.491895);
        System.out.println("Day Length: "+  length);

        SunPosition sun1 = SunPosition.compute()
                .at(28.6, 77.2)
                .timezone("Asia/Kolkata")
                .on(2018, 5, 23, 12,59 , 59)
                .execute();
        System.out.println(sun1.getAltitude() + " " + sun1.getAzimuth());


        for (int i = 0; i < 12; i++) { //days
            for (int j = 0; j < 24; j++) { //hours
                SunTimes paris = SunTimes.compute()
                        .on(2018, 12, 11 + i)
                        .timezone("Europe/Copenhagen")
//                        .timezone(getGMTTimeZoneName("GMT+2:00"))
                        .at(55.386964, 10.491895)
                        .execute();

                SunPosition sun = SunPosition.compute()
                        .on(2018, 12, 11 + i, j ,00, 00)
                        .timezone("Europe/Copenhagen")
//                        .timezone(getGMTTimeZoneName("GMT+2:00"))
                        .at(55.386964, 10.491895)
                        .execute();

                System.out.print(sun.getAltitude() + " " + sun.getAzimuth());
                System.out.println(" Day Lengths: " + (Duration.between(paris.getRise().toLocalDateTime(), paris.getSet().toLocalDateTime()).getSeconds()));

            }
        }
    }
}