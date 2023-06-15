package org.example;

import org.shredzone.commons.suncalc.SunPosition;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.Duration;
import java.util.Date;

public class Main {

    public final double lat = 55.386964;
    public final double lng = 10.491895;

    public final String zone = "Europe/Copenhagen";

    public double getAltitude(Date date)
    {
        SunPosition sunPosition = SunPosition.compute()
                .on(date)
                .timezone(zone)
                .at(lat, lng)
                .execute();
        return sunPosition.getAltitude();
    }

    public double getAzimuth(Date date)
    {
        SunPosition sunPosition = SunPosition.compute()
                .on(date)
                .timezone(zone)
                .at(lat, lng)
                .execute();
        return sunPosition.getAzimuth();
    }

    public double getDayLength(Date date)
    {
        SunTimes time = SunTimes.compute()
                .on(date)
                .timezone(zone)
                .at(lat, lng)
                .execute();
        return Duration.between(time.getRise().toLocalDateTime(), time.getSet().toLocalDateTime()).getSeconds();
    }

    public static void main(String[] args) {

        Main main = new Main();
        for (int i = 0; i < 12; i++) { //days
            for (int j = 0; j < 24; j++) { //hours
                Date date=new Date(2018, 12, 11 + i, j ,0 , 0);
                System.out.print(main.getAltitude(date) + " " + main.getAzimuth(date));
                System.out.println(main.getDayLength(date));
            }
        }
    }
}