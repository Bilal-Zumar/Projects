import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.TimeZone;

import static java.lang.Math.*;

public class PathOfSun {
    static LocalTime localTime = LocalTime.now();
    static double dcLat = 28.6;
    static double dcLong = 77.2;
    static DecimalFormat df = new DecimalFormat("#.0");

    public static void main(String [] args) {
        int day = dayOfYear();
        double equationOfTime = equationOfTime(day);
        double lstm = localTimeMeridian();
        double lst = localSolarTime(equationOfTime, dcLong, lstm);
        double declination = declination(day);
        double hourAngle = hourAngle(lst);

        double zenith = zenith(dcLat, declination, hourAngle);
        double azimuth = azimuth(dcLong, declination, zenith, hourAngle);

    }

    //Longitude of timezone meridian
    public static double localTimeMeridian() {
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        TimeZone est = TimeZone.getTimeZone("EST");
        int td = gmt.getRawOffset() - est.getRawOffset();
        double localStandardTimeMeridian = 15 * (td/(1000*60*60)); //convert td to hours
        //System.out.println("Local Time Meridian: " + localStandardTimeMeridian);
        return localStandardTimeMeridian;
    }

    //Get the number of days since Jan. 1
    public static int dayOfYear() {
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int dayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);
        //System.out.println("Day: " + dayOfYear);
        return dayOfYear;
    }

    //Emperical equation to correct the eccentricity of Earth's orbit and axial tilt
    public static double equationOfTime (double day) {
        double d =(360.0/365.0)*(day - 81);
        d = Math.toRadians(d);
        double equationTime = 9.87*sin(2*d)-7.53*cos(d)-1.54*sin(d);
        //System.out.println("Equation Of Time: " + equationTime);
        return equationTime;
    }
    //The angle between the equator and a line drawn from the center of the Sun(degrees)
    public static double declination(int dayOfYear) {
        double declination = 23.5*sin((Math.toRadians(360.0/365.0))*(dayOfYear - 81));
        //System.out.println("Declination: " + df.format(declination));
        return declination;
    }

    //Add the number of minutes past midnight localtime//
    public static double hourAngle(double localSolarTime) {
        double hourAngle = 15 * (localSolarTime - 13);
        System.out.println("Hour Angle: " + df.format(hourAngle)); //(degrees)
        return hourAngle;
    }

    //Account for the variation within timezone - increases accuracy
    public static double localSolarTime(double equationOfTime, double longitude, double lstm) {
        //LocalSolarTime = 4min * (longitude + localStandardTimeMeridian) + equationOfTime
        //Time Correction is time variation within given time zone (minutes)
        //longitude = longitude/60; //convert degrees to arcminutes
        double localStandardTimeMeridian = lstm;
        double timeCorrection = (4 * (longitude + localStandardTimeMeridian) + equationOfTime);
        System.out.println("Time Correction: " + timeCorrection); //(in minutes)
        //localSolarTime represents solar time where noon represents sun's is highest position
        // in sky and the hour angle is 0 -- hour angle is negative in morning, and positive after solar noon.
        double localSolarTime = (localTime.toSecondOfDay() + (timeCorrection*60)); //(seconds)
        localSolarTime = localSolarTime/(60*60);  //convert from seconds to hours
        //Convert double to Time (HH:mm:ss) for console output
        int hours = (int) Math.floor(localSolarTime);
        int minutes = (int) ((localSolarTime - hours) * 60);
        //-1 for the daylight savings
        Time solarTime = new Time((hours-1), minutes, 0);
        System.out.println("Local Solar Time: " + solarTime); //hours

        return localSolarTime;
    }

    public static double azimuth(double lat, double declination, double zenith, double hourAngle)
    {
        double elevation = Math.toRadians(90 - zenith);
        lat = Math.toRadians(lat);
        declination = Math.toRadians(declination);
        hourAngle = Math.toRadians(hourAngle);
        double azimuthRadian = acos(((sin(declination) * cos(lat)) - (cos(hourAngle) * cos(declination) * sin(lat))) / cos(elevation));
        double azimuthDegree = Math.toDegrees(azimuthRadian);
        if (hourAngle > 0)
            azimuthDegree = 360 - azimuthDegree;
        System.out.println("Azimuth: " + df.format(azimuthDegree));
        return azimuthDegree;
    }

    public static double zenith(double lat, double declination, double hourAngle) {
        lat = Math.toRadians(lat);
        declination = Math.toRadians(declination);
        hourAngle = Math.round(hourAngle);
        hourAngle = Math.toRadians(hourAngle);
        //Solar Zenith Angle
        double zenith = Math.toDegrees(acos(sin(lat)*sin(declination) + (cos(lat)*cos(declination)*cos(hourAngle))));
        //Solar Elevation Angle
        double elevation = Math.toDegrees(asin(sin(lat)*sin(declination) + (cos(lat)*cos(declination)*cos(hourAngle))));
        System.out.println("Elevation: " + df.format(elevation));
        System.out.println("Zenith: " + df.format(zenith));
        return zenith;
    }
}