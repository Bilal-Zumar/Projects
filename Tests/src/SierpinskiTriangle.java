import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SierpinskiTriangle extends JFrame {

    JTextField box;
    TrianglePanel sierpPanel;
    JButton jbt1, jbt2;
    int numIterations = 1;
    Color[] colors = {null, Color.blue, Color.orange, Color.green,
            Color.black, Color.pink, Color.white,
            Color.magenta, Color.yellow, Color.red, Color.gray,
            Color.cyan, Color.lightGray, Color.darkGray};

    public SierpinskiTriangle() {
        TrianglePanel sierpPanel = new TrianglePanel();
        add(sierpPanel, BorderLayout.CENTER);

        jbt1 = new JButton("iterations");
        JPanel panel2 = new JPanel();
        panel2.add(jbt1);

        jbt1.addActionListener(new Listener());
        box = new JTextField("" + numIterations, 5);
        panel2.add(box);

        jbt2 = new JButton("next");
        jbt2.addActionListener(new Listener());
        panel2.add(jbt2);

        add(panel2, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SierpinskiTriangle frame = new SierpinskiTriangle();
        frame.setTitle("SierpinskiTriangle");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private class TrianglePanel extends JPanel {
        public TrianglePanel() {
            //no code necessary
        }

        //create a new point with the midpoint of p1 and p2
        private Point midpoint(Point p1, Point p2) {
            int x = (int) ((p1.x + p2.x) / 2);
            int y = (int) ((p1.y + p2.y) / 2);
            return new Point(x, y);
        }

        //recursive method to draw iterations of triangles
        public void draw(Graphics g, Point p1, Point p2, Point p3, int iterations) {

            if (iterations >= 1) {
                Point mp1 = midpoint(p1, p2);
                Point mp2 = midpoint(p1, p3);
                Point mp3 = midpoint(p2, p3);

                /*
                 * TO DO: recursive calls go here to draw the 3 triangles within this triangle!
                 */

                draw(g, p1, mp2, mp1, iterations - 1);
                draw(g, p2, mp1, mp3, iterations - 1);
                draw(g, p3, mp2, mp3, iterations - 1);


                if (iterations < colors.length)
                    g.setColor(colors[iterations]);

                /*
                 * TO DO: draw the lines of your triangle
                 */

                int[] x = {p1.x, p2.x, p3.x};
                int[] y = {p1.y, p2.y, p3.y};

//                if(iterations!=1)
                    g.drawPolygon(new Polygon(x, y, 3));
//                else
//                    g.fillPolygon(new Polygon(x, y, 3));
            }
        }

        //paintComponent is automatically called by JPanel whenever the Frame is resized or repaint() is called.
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Point p1 = new Point(getWidth() / 2, 10);            //getWidth()  - width of JPanel
            Point p2 = new Point(10, getHeight() - 10);          //getHeight() - height of JPanel
            Point p3 = new Point(getWidth() - 10, getHeight() - 10);
            draw(g, p1, p2, p3, numIterations);
        }
    }

    //Listener class to support the JButtons.
    //Note ActionListener is an interface, consisting of 1 method: actionPerformed()
    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbt1)
                numIterations = Integer.parseInt(box.getText());
            else {
                numIterations++;
                box.setText("" + numIterations);
            }
            System.out.println("iterations: " + numIterations);
            repaint();    //calls paintComponent(Graphics g)
        }
    }

}
