package Balls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class MouseKeyInput extends JPanel implements MouseWheelListener, MouseListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color BALL_COLOR = Color.BLUE;
    private static final Color JUMPING_BALL_COLOR = Color.RED;
    private static final int BALL_SIZE = 50;
    private static final int JUMPING_BALL_SIZE = 30;
    private static final int MOVE_AMOUNT = 10;
    private static final int MAX_SPEED = 20;
    private static final int MAX_SPEED_CHANGE = 5;
    private Ball ball;
    private JumpingBall jumpingBall;
    private boolean shiftPressed;
    private boolean mouseExited;
    private ArrayList<Ball> balls;

    public MouseKeyInput() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(BACKGROUND_COLOR);
        ball = new Ball(0, 0, BALL_SIZE, BALL_COLOR);
        jumpingBall = new JumpingBall(0, 0, JUMPING_BALL_SIZE, JUMPING_BALL_COLOR);
        balls = new ArrayList<Ball>();
        addMouseListener(this);
        addMouseWheelListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        jumpingBall.draw(g);
        for (Ball b : balls) {
            b.draw(g);
        }
    }

    private void moveBallTo(MouseEvent e) {
        ball.setX(e.getX() - ball.getRadius());
        ball.setY(e.getY() - ball.getRadius());
        repaint();
    }

    private void moveJumpingBallTo(MouseEvent e) {
        jumpingBall.setX(e.getX() - jumpingBall.getRadius());
        jumpingBall.setY(e.getY() - jumpingBall.getRadius());
        repaint();
    }

    private void randomlyChangeBallSpeed() {
        int dx = (int) (Math.random() * MAX_SPEED_CHANGE * 2) - MAX_SPEED_CHANGE;
        int dy = (int) (Math.random() * MAX_SPEED_CHANGE * 2) - MAX_SPEED_CHANGE;
        ball.setX(Math.min(Math.max(ball.getX() + dx, -MAX_SPEED), MAX_SPEED));
        ball.setY(Math.min(Math.max(ball.getY() + dy, -MAX_SPEED), MAX_SPEED));
    }

    private void changeColor() {
        if (mouseExited) {
            ball.setColor(Color.YELLOW);
            jumpingBall.setColor(Color.ORANGE);
        } else {
            ball.setColor(BALL_COLOR);
            jumpingBall.setColor(JUMPING_BALL_COLOR);
        }
        repaint();
    }

    private void increaseBallSize() {
        ball.setDiameter(Math.min(ball.getDiameter() + 10, 100));
        repaint();
    }

    private void decreaseBallSize() {
        ball.setDiameter(Math.max(ball.getDiameter() - 10, 10));
        repaint();
    }

    private void addBall() {
        balls.add(new Ball(Math.random() * (WIDTH - BALL_SIZE), Math.random() * (HEIGHT - BALL_SIZE), BALL_SIZE,
                Color.GREEN));
        repaint();
    }

    private void removeBall() {
        if (!balls.isEmpty()) {
            balls.remove(balls.size() - 1);
            repaint();
        }
    }

    private void moveJumpingBall(int dx, int dy) {
        double x = jumpingBall.getX() + dx;
        double y = jumpingBall.getY() + dy;
        if (x < 0 || x + jumpingBall.getDiameter() > WIDTH) {
            dx = -dx;
        }
        if (y < 0 || y + jumpingBall.getDiameter() > HEIGHT) {
            dy = -dy;
        }
        jumpingBall.setX(dx);
        jumpingBall.setY(dy);
        jumpingBall.setX(jumpingBall.getX() + dx);
        jumpingBall.setY(jumpingBall.getY() + dy);
        repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            ball.setRadius(ball.getRadius() + 10);
        } else {
            jumpingBall.setRadius(jumpingBall.getRadius() - 10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MouseKeyInput");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.getContentPane().add(new MouseKeyInput());
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            ball.setX(e.getX());
            ball.setY(e.getY());
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            jumpingBall.setX(e.getX());
            jumpingBall.setY(e.getY());
        } else if (e.getModifiersEx() == MouseEvent.SHIFT_DOWN_MASK && e.getButton() == MouseEvent.BUTTON1) {
            ball.setRandomSpeed(Math.random() * 10);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        ball.setColor(Color.GREEN);
        jumpingBall.setColor(Color.YELLOW);
    }
}



