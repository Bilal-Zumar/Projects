/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gokhanonder2020at03;

/**
 *
 * @author gokhan
 */
public class Achievement {
    private String description;
    private int level;
    private int maximum;
    
   public Achievement (String description, int level, int maximum){
    this.description = description;
    this.level = level;
    this.maximum = maximum;
    
   }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
        
    @Override
    public String toString(){
    return "Achievement [description=" + description + ", level= "+level + ", maximum="+ maximum + "]";
    }
}
