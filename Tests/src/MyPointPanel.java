import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class MyPointPanel extends JPanel implements MouseListener {
    //constants

    private static final Color BACKGROUND = new Color(242, 43, 43);
    //fields
    private static final Color BACKGROUND_WHITE = new Color(255, 255, 255);

    private ArrayList<Point> myPoints = new ArrayList<Point>();
    Color myColor;
    private boolean rotate;


    public MyPointPanel(JFrame frame, int maxX, int maxY) {
        myColor = BACKGROUND;

        Timer t = new Timer(20, new MyPointPanel.Listener());
        t.start();
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {

        g.setColor(myColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        /*
         * todo: traverse the ArrayList of Points, connecting each with each other.
         *       fill a circle with the Point.
         *       Note: if Point1 is connected to Point2, it's not necessary to connect Point2 to Point 1...
         */
        for (Point point : myPoints) {
            g.setColor(Color.BLUE);
            g.fillOval(point.x, point.y, 5, 5);

            for (int i = 0; i < myPoints.size(); i++) {
                double maxDistance = calculateDistanceBetweenPoints(0, 0, getWidth(), getHeight());
                double currentDistance = calculateDistanceBetweenPoints(point.x, point.y, myPoints.get(i).x, myPoints.get(i).y);

                int color = (int) (currentDistance / maxDistance * 255);
//                System.out.println(color);
                g.setColor(new Color(color, color, color));
                g.drawLine(point.x, point.y, myPoints.get(i).x, myPoints.get(i).y);
            }
        }


        /*
         * todo: extension: the color of the connecting line should reflect the distance between the 2 points.
         */

    }

    public double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public void mouseClicked(MouseEvent e) {
        saySomething("Mouse pressed; # of clicks: "
                + e.getClickCount(), e);
    }

    public void mouseReleased(MouseEvent e) {
        saySomething("Mouse released; # of clicks: "
                + e.getClickCount(), e);
    }

    public void mouseEntered(MouseEvent e) {
        saySomething("Mouse entered", e);
        /*
         * todo: change the background and clear out the ArrayList, myPoints
         */
        myColor = BACKGROUND;
        myPoints = new ArrayList<>();
        repaint();
    }

    public void mouseExited(MouseEvent e) {

        saySomething("Mouse exited", e);
        /*
         * todo: change the background
         */
        myColor = BACKGROUND_WHITE;
        repaint();
    }

    /*
     * When the user presses the mouse, create a Point object with the (x,y) coordinates and add to the
     * ArrayList<Point> myPoints
     */
    public void mousePressed(MouseEvent e) {
        saySomething("Mouse pressed (# of clicks: "
                + e.getClickCount() + ")", e);

        int x = e.getX();
        int y = e.getY();

        Point point = new Point(x, y);
        myPoints.add(point);

        //extension
        int button = e.getButton();
        if (button == 1) {
            rotate = true;
        } else {
            rotate = false;
        }
        repaint();
    }

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on "
                + e.getComponent().getClass().getName()
                + "." + "\n");
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Point> modifiedPoints = new ArrayList<>();
            for (Point point : myPoints) {
                RotatingPoint rotatingPoint = new RotatingPoint(point);
//                modifiedPoints.add(rotatingPoint.move(point, rotate));
            }
//            System.out.println(modifiedPoints);
            myPoints = modifiedPoints;
            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("What is the Point");
        frame.setSize(1000, 800);
        frame.setLocation(50, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MyPointPanel(frame, 800, 400));
        frame.setVisible(true);
    }
}