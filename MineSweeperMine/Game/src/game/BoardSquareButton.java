/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MineSweeperMine.Game.src.game;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author BilalZumar
 */
public class BoardSquareButton extends JButton{
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private String text;
    private Boolean mine;
    private Boolean investigated;
    private Boolean labbeled;

    public BoardSquareButton(int width,int height,int x,int y) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.setBounds(x,y,width,height);
        initialise();
    }
    
    public void initialise(){
        text="?";
        color=Color.GRAY;
        mine=false;
        investigated=false;
        labbeled=false;
//        this.setText(text);
        this.setBackground(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.setBackground(color);
    }

    public String getText() {
        return text;
    }

    public void setString(String text) {
        this.text = text;
        this.setText(text);
    }

    public Boolean hasMine() {
        return mine;
    }

    public void setMine(Boolean mine) {
        this.mine = mine;
    }

    public Boolean getInvestigated() {
        return investigated;
    }

    public void setInvestigated(Boolean investigated,int count) {
        this.investigated = investigated;
        setString(count+"");
        setColor(Color.GREEN);  
    }

    public Boolean isLabbeled() {
        return labbeled;
    }

    public void setLabbeled(Boolean labbeled) {
        this.labbeled = labbeled;
        if(labbeled==Boolean.TRUE)
            setColor(Color.RED);
        else
            setColor(Color.GRAY);
    }
    
    
    
}
