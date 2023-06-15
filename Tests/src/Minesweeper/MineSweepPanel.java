package Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MineSweepPanel extends JPanel implements MouseListener {
    //constants
    static final Color BACKGROUND = new Color(192, 192, 192);

    //define variables for the board of GameSquares
    private GameSquare[][] myGrid;

    /**
     * Panel's constructor
     * initialize all the grid and the board of GameSquares
     */
    public MineSweepPanel(int size) {
        initializeGrid(size);
        addMouseListener(this);
    }

    /**
     * creates and populates the grid.
     */
    public void initializeGrid(int gridSize) {
        int[][] grid = Minesweeper.create_init_grid(gridSize);
        Minesweeper.printGrid(grid);

        /* TODO:
         * initialize myGrid by traversing grid and creating new GameSquare objects.
         */
        myGrid = new GameSquare[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            myGrid[i] = new GameSquare[gridSize];
        }

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                myGrid[i][j] = new GameSquare(i, j, grid[i][j]);
            }
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());

        /* TODO:
         * draw the GameSquares
         * note: reference the heatmap lab
         */
        int gameSquareWidth = getWidth() / myGrid.length;
        int gameSquareHeight = getHeight() / myGrid.length;
        for (int r = 0; r < myGrid.length; r++)
            for (int c = 0; c < myGrid[r].length; c++) {
                myGrid[r][c].draw(g, gameSquareWidth, gameSquareHeight);
            }

        /* TODO:
         * draw horizontal and vertical lines to separate the squares
         *    one loop for the horizontal lines
         *    one loop for the vertical lines.
         */
        g.setColor(Color.black);

        gameSquareHeight = getHeight() / myGrid.length;
        for (int r = 0; r < myGrid.length; r++) {
            g.drawLine(0, (r * gameSquareHeight), getWidth(), (r * gameSquareHeight));
        }

        gameSquareWidth = getWidth() / myGrid.length;
        for (int c = 0; c < myGrid.length; c++) {
            g.drawLine((c * gameSquareWidth), 0, (c * gameSquareWidth), getHeight());
        }
    }


    //Add methods you need to implement the MouseListener interface

    /**
     * mousePressed
     * call e.getX() and e.getY()to get the (x,y) coordinates of where the mouse was pressed.
     * use getWidth() and getHeight to determine the dimensions of the panel
     * <p>
     * Hint: use getHeight with the number of rows to determine which row was selected.
     */

    public void mousePressed(MouseEvent e) {
        saySomething("Mouse pressed; # of clicks: "
                + e.getClickCount(), e);

        int x = e.getX();
        int y = e.getY();


        /*
         * e.getButton() == MouseEvent.BUTTON1 when event is trigger with the left button
         * e.getButton() == MouseEvent.BUTTON3 when event is trigger with the right button
         */

        /*
         * if the GameSquare's number:
         *     Minesweeper.MINE and BUTTON3 => then uncover GameSquare
         *     Minesweeper.MINE and BUTTON1 => game over, uncover all squares
         *     0: call recursive routine to uncover all connected 0's and neighbors
         *     1-8: uncover the GameSquare
         */


        //repaint() will cause paintComponent() to get invoked, which will redraw myGrid
        repaint();
    }

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on "
                + e.getComponent().getClass().getName()
                + "." + "\n");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /*======================================================
     *  Code to create the Panel, you do not need to modify
     *====================================================== */
    static int myWidth = 1500;
    static int myHeight = 900;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MineSweeper");
        frame.setSize(myWidth, myHeight);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MineSweepPanel(10));
        frame.setVisible(true);
    }


}