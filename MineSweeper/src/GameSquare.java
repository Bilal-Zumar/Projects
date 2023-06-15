/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author BilalZumar
 */
public class GameSquare {

    private String[] images = {null, "num1.jpg", "num2.jpg", "num3.jpg",
            "num4.jpg", "num5.jpg", "num6.jpg",
            "num7.jpg", "num8.jpg", "bomb.jpg"};

    private boolean uncovered;
    private int x;
    private int y;
    private int number;

    public GameSquare(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.uncovered = false;
    }

    public GameSquare()
    {
        this.x=0;
        this.y=1;
    }

    public void draw(Graphics g, int recWidth, int recHeight) {
        int ulcX = (x * recWidth);
        int ulcY = (y * recHeight);
        if (uncovered && number == 0) {
            g.setColor(new Color(235, 235, 235));
            g.fillRect(ulcX, ulcY, recWidth, recHeight);
        } else if (uncovered && number != -1) {

            if (GameSquare.class.getResource("/" + images[number]) != null) {
                ImageIcon i = new ImageIcon(GameSquare.class.getResource("/" + images[number]));
                g.drawImage(i.getImage(), ulcX, ulcY, recWidth, recHeight, null);
            }
        } else if (uncovered && number == -1) {
            ImageIcon i = new ImageIcon(GameSquare.class.getResource("/bomb.jpg"));
            g.drawImage(i.getImage(), ulcX, ulcY, recWidth, recHeight, null);

        }
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public boolean isUncovered() {
        return uncovered;
    }

    public void setUncovered(boolean uncovered) {
        this.uncovered = uncovered;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "GameSquare{" +
                "images=" + Arrays.toString(images) +
                ", uncovered=" + uncovered +
                ", x=" + x +
                ", y=" + y +
                ", number=" + number +
                '}';
    }
}
