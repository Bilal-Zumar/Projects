/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MineSweeperMine.Game.src.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author BilalZumar
 */
public class Board {
    private int width;
    private int height;
    int size;
    ArrayList<BoardSquareButton> array;

    public Board(int width, int height, int size) {
        this.width = width;
        this.height = height;
        this.size = size;
        array = new ArrayList<BoardSquareButton>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                BoardSquareButton temp = new BoardSquareButton(size, size, j * size, i * size);
                array.add(temp);
            }
        }
    }

    BoardSquareButton getButton(int x, int y) {
        return array.get((x * width) + y);
    }

    void storeButton(int x, int y, BoardSquareButton btn) {
        array.set((x * width) + y, btn);
    }

    void initialiseAll() {
        for (int i = 0; i < width * height; i++) {
            array.get(i).initialise();
        }
    }

    void createMines(int n) {
        while (n > 0) {
            Random r = new Random();
            int range = width * height - 1;
            int rand = r.nextInt((range - 0) + 1) + 0;
            if (!array.get(rand).hasMine()) {
                array.get(rand).setMine(Boolean.TRUE);
                n--;
//                System.err.println(rand);
            }
        }
    }

    void mark(int x, int y) {
        array.get((y * width) + x).setLabbeled(!array.get((y * width) + x).isLabbeled());
    }

    void finished() {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).hasMine()) {
                array.get(i).setString("X");
                array.get(i).setColor(Color.YELLOW);
            }
        }
    }

    Boolean hasWon() {
        Boolean check1 = true, check2 = true;
        for (int i = 0; i < array.size(); i++) {
            if (!array.get(i).hasMine() && !array.get(i).getInvestigated())
                return false;
        }
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).hasMine()) {
                array.get(i).setString("X");
                array.get(i).setColor(Color.RED);
            }
        }
        return true;
    }

    int countSurrounding(int x, int y) {
        int count = 0;

        if (array.get((y * width) + x).hasMine()) {
            finished();
            return 0;
        } else {
            if (x + 1 < width && array.get((y * width) + x + 1).hasMine()) {
                count++;
            }
            if (x - 1 >= 0 && array.get((y * width) + x - 1).hasMine()) {
                count++;
            }
            if (y + 1 < height && array.get(((y + 1) * width) + x).hasMine()) {
                count++;
            }
            if (y - 1 >= 0 && array.get(((y - 1) * width) + x).hasMine()) {
                count++;
            }
            if (x + 1 < width && y + 1 < height && array.get(((y + 1) * height) + x + 1).hasMine()) {
                count++;
            }
            if (x - 1 >= 0 && y - 1 >= 0 && array.get(((y - 1) * height) + x - 1).hasMine()) {
                count++;
            }
            if (x + 1 < width && y - 1 >= 0 && array.get(((y - 1) * height) + x + 1).hasMine()) {
                count++;
            }
            if (x - 1 >= 0 && y + 1 < height && array.get(((y + 1) * height) + x - 1).hasMine()) {
                count++;
            }
        }

        array.get((y * width) + x).setInvestigated(Boolean.TRUE, count);
        if (count == 0) {
            explore(x, y);
        }
        if (hasWon()) {
            return 1;
        }

        return 2;
    }

    void explore(int x, int y) {
        if (x + 1 < width && array.get((y * width) + x + 1).getInvestigated() != true) {
            countSurrounding(x + 1, y);
        }

        if (x - 1 >= 0 && array.get((y * width) + x - 1).getInvestigated() != true) {
            countSurrounding(x - 1, y);
        }

        if (y + 1 < height && array.get(((y + 1) * width) + x).getInvestigated() != true) {
            countSurrounding(x, y + 1);
        }

        if (y - 1 >= 0 && array.get(((y - 1) * width) + x).getInvestigated() != true) {
            countSurrounding(x, y - 1);
        }

        if (x + 1 < width && y + 1 < height && array.get(((y + 1) * width) + x + 1).getInvestigated() != true) {
            countSurrounding(x + 1, y + 1);
        }

        if (x - 1 >= 0 && y - 1 >= 0 && array.get(((y - 1) * width) + x - 1).getInvestigated() != true) {
            countSurrounding(x - 1, y - 1);
        }

        if (x + 1 < width && y - 1 >= 0 && array.get(((y - 1) * width) + x + 1).getInvestigated() != true) {
            countSurrounding(x + 1, y - 1);
        }

        if (x - 1 >= 0 && y + 1 < height && array.get(((y + 1) * width) + x - 1).getInvestigated() != true) {
            countSurrounding(x - 1, y + 1);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<BoardSquareButton> getArray() {
        return array;
    }

    public void setArray(ArrayList<BoardSquareButton> array) {
        this.array = array;
    }


}
