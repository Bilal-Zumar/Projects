/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MineSweeperMine.Game.src.game;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 *
 * @author BilalZumar
 */
public final class GameMain {

    public static int WIDTH=4;
    public static int HEIGHT=4;
    public static int NUM_MINES=1;
    public static int TILE_SIZE=50;
    
    public GameMain() {
        Board board=new Board(WIDTH, HEIGHT,TILE_SIZE);
        JFrame f=new JFrame("Button Example"); 
        
        storeButton(board.getArray(),f);        

//        Add listeners
        for (int i = 0; i < WIDTH*HEIGHT; i++) {
            board.getArray().get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {  
                    if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0)
                    {
                        System.out.println("RIGHT CLICK");
                        BoardSquareButton btn=(BoardSquareButton)e.getSource();
                        board.mark(btn.getX()/TILE_SIZE, btn.getY()/TILE_SIZE);
                    }
                    else 
                    {
                        System.out.println("LEFT CLICK");                    
                        BoardSquareButton btn=(BoardSquareButton)e.getSource();
                        int result=board.countSurrounding(btn.getX()/TILE_SIZE, btn.getY()/TILE_SIZE);
                        if(result==0)
                        {
                            JOptionPane.showMessageDialog(f, "You Lost");
                            board.initialiseAll();
                            board.createMines(NUM_MINES);
                        }
                        else if(result==1)
                        {
                            JOptionPane.showMessageDialog(f, "You Win");
                            board.initialiseAll();
                            board.createMines(NUM_MINES);
                        }
                    }
                } 
            });
        }
        
        board.createMines(NUM_MINES);
    }
    
    void storeButton(ArrayList<BoardSquareButton> list, JFrame f){
        for (int i = 0; i < WIDTH*HEIGHT; i++) {
            f.add(list.get(i));      
        }
        f.setSize(TILE_SIZE*WIDTH+17,TILE_SIZE*HEIGHT+40);  
        f.setLayout(null);  
        f.setVisible(true);
    }

    public static void main(String[] args) {
      GameMain game=new GameMain();
    }
    
}
