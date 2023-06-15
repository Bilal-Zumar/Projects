import java.awt.*;

public class RotatingPoint {

    private double angle;
    private double radius;

    public RotatingPoint(Point point) {
//        System.out.println((400 - point.y) + " bhbhb " + (point.x - 500));
        angle = (Math.atan((double) (400 - point.y) / (point.x - 500)) * 180 / Math.PI);
        radius = calculateDistanceBetweenPoints(point.x, point.y, 500, 400);
//        angle = 0;
//        System.out.println(angle);
//        System.out.println(radius);
    }

    public Point move(Point point, boolean rotate) {
        if(rotate)
            angle++;
        else
            angle--;
//        System.out.println(angle);
//        System.out.println(Math.cos(angle * Math.PI / 180)+" cos "+Math.sin(angle * Math.PI / 180));
//        System.out.println((radius * Math.cos(angle * Math.PI / 180))+" cos "+(radius * Math.sin(angle * Math.PI / 180)));
        if((point.x-500)<0)
        {
            int x = (int) (500-radius * Math.cos(angle * Math.PI / 180));
            int y = (int) (400+radius * Math.sin(angle * Math.PI / 180));
//            System.out.println(point.x + " " + point.y);
//            System.out.println(x + " " + y);
            return new Point(x, y);
        }
        else
        {
            int x = (int) (500 + radius * Math.cos(angle * Math.PI / 180));
            int y = (int) (400 - radius * Math.sin(angle * Math.PI / 180));
//            System.out.println(point.x + " " + point.y);
//            System.out.println(x + " " + y);
            return new Point(x, y);
        }
    }

    public double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
