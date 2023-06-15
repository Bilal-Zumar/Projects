//Programmer:  

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HeatMapPanel extends JPanel implements MouseListener {

    private static final Color BACKGROUND = new Color(204, 204, 204);

    private double[][] tempGrid = new double[100][100];
    private double maxTemperature = 150;
    private double minTemperature = -150;

    //for extension
    int gRow = -1;
    int gCol = -1;
    int gTemp = 0;


    public HeatMapPanel() {
        //initialize heat map, half cold, half hot
        for (int r = 0; r < tempGrid.length; r++)
            for (int c = 0; c < tempGrid[r].length; c++) {
                if (c < tempGrid[r].length / 2)
                    tempGrid[r][c] = minTemperature;
                else
                    tempGrid[r][c] = maxTemperature;
            }

        //timer
        Timer t = new Timer(40, new Listener());
        t.start();

        //add MouseListener to this panel
        addMouseListener(this);

        //test your temperature to color conversions, see table in the lab document
        for (int temp = (int) minTemperature; temp <= (int) maxTemperature; temp += 10)
            System.out.printf("%5d: %4d, %4d, %4d\n", temp, getRed(temp), getGreen(temp), getBlue(temp));
    }

    //you can hard code ranges from -150 to 150
    public int getRed(double temp) {
        if (temp > 0) {
            return 255;
        }
        if (temp <= 0 && temp >= -74) {
            return 255 - (int) (temp * -3.4);
        }
        if (temp < -74) {
            return 0;
        }
        return 0;
    }

    public int getGreen(double temp) {
        if(temp>=76 && temp<=150)
        {
            return 510-(int)(temp*3.4);
        }
        if(temp >-75 && temp < 76)
        {
            return 255;
        }
        if(temp <=-75 && temp >=-150)
        {
            return 510-(int)(temp*-3.4);
        }
        return 255;
    }

    public int getBlue(double temp) {
        if (temp < 1) {
            return 255;
        } else if (temp >= 1 && temp <= 75) {
            return 255-(int) (temp * 3.4);
        } else if (temp > 75) {
            return 0;
        }

        return 255;
    }


    public void paintComponent(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());

        int blockHeight = 1005 / tempGrid.length; //todo
        int blockWidth = 1400 / tempGrid[0].length;  //todo
        for (int r = 0; r < tempGrid.length; r++)
            for (int c = 0; c < tempGrid[r].length; c++) {

                double tVal = tempGrid[r][c];
                g.setColor(new Color(getRed(tVal), getGreen(tVal), getBlue(tVal)));

                //(x,y) is the upper left hand corner of the rectangle
                int x = (c * blockWidth);  //todo
                int y = (r * blockHeight);  //todo
                g.fillRect(x, y, blockWidth, blockHeight);
            }

        //Display temperatures of both sides
        String avgLeftTempStr = String.format("%7.2f", tempGrid[tempGrid.length / 2][0]);
        String avgRightTempStr = String.format("%7.2f", tempGrid[tempGrid.length / 2][tempGrid[0].length - 1]);

        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 60));
        g.drawString("Left Side Temp", 10, 60);
        g.drawString(avgLeftTempStr, 100, 120);
        g.drawString("Right Side Temp", getWidth() - 450, 60);  //TODO: you might need to adjust location
        g.drawString(avgRightTempStr, getWidth() - 350, 120);

        if (gRow > 0) {
            tempGrid[gRow][gCol] = gTemp;
//            System.out.println(gTemp);
//            System.out.printf(tempGrid[gRow][gCol] +""+getRed(tempGrid[gRow][gCol])+""+getGreen(tempGrid[gRow][gCol])+""+getBlue(tempGrid[gRow][gCol]));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    //called when user presses down on a mouse button
    public void mousePressed(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();

        gCol = x / (1400 / tempGrid.length);
        gRow = y / (1005 / tempGrid[0].length);
//        System.out.println(x + " " + y);
//        System.out.println(gCol + " " + gRow);
        int button = event.getButton();  //use BUTTON1 and BUTTON3

        if (button == 1) {
            gTemp = 750;
        } else {
            gTemp = -750;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //todo - extension
        gRow = -1;
//        System.out.println("mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //todo: update the temperature values in tempGrid[][]

            for (int r = 0; r < tempGrid.length; r++) {
                for (int c = tempGrid[r].length / 2; c < tempGrid[r].length; c++) {
                    double sum = 0;
                    int count = 0;
                    int r1 = r - 1;
                    int r2 = r + 1;
                    int c1 = c - 1;
                    int c2 = c + 1;

                    if (r1 >= 0) {
                        sum += tempGrid[r1][c];
                        count++;
                    }
                    if (r2 < tempGrid.length) {
                        sum += tempGrid[r2][c];
                        count++;
                    }
                    if (c1 >= 0) {
                        sum += tempGrid[r][c1];
                        count++;
                    }
                    if (c2 < tempGrid[r].length) {
                        sum += tempGrid[r][c2];
                        count++;
                    }

//                    tempGrid[r][c] += sum / count;
//                    if(sum>150)
//                    {
//                        System.out.println(sum);
//                        System.out.println(tempGrid[r][c]);
//                    }
                    tempGrid[r][c] = sum / count;
                }

                for (int c = tempGrid[r].length / 2; c >= 0; c--) {
                    double sum = 0;
                    int count = 0;
                    int r1 = r - 1;
                    int r2 = r + 1;
                    int c1 = c - 1;
                    int c2 = c + 1;

                    if (r1 >= 0) {
                        sum += tempGrid[r1][c];
                        count++;
                    }
                    if (r2 < tempGrid.length) {
                        sum += tempGrid[r2][c];
                        count++;
                    }
                    if (c1 >= 0) {
                        sum += tempGrid[r][c1];
                        count++;
                    }
                    if (c2 < tempGrid[r].length) {
                        sum += tempGrid[r][c2];
                        count++;
                    }

//                    if(sum>100)
//                    {
//                        System.out.println(tempGrid[r][c]);
//                    }
                    tempGrid[r][c] = sum / count;
                }
            }
            repaint();
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Heat Map");
        frame.setSize(1400, 1005);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new HeatMapPanel());
        frame.setVisible(true);
    }
}