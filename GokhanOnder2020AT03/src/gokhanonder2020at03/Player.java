/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gokhanonder2020at03;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gokhan
 */
public class Player {
    
    private String userName;
    private String tagName;
    public List<Achievement> ac = null;
    
 public Player(String userName, String tagName, List<Achievement> ac){
     
    this.userName = userName;
    this.tagName = tagName;
    if (ac==null){
        createac();
    }
    else{
        this.ac = ac;        
    }
 } 
 Player() {
        
    }

  private void createac(){
      
        this.ac = new ArrayList<Achievement>();
    }
    public void add_achievement(Achievement ach){
        this.ac.add(ach);
    }
  
   
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTagName() {
        return tagName;
    }
    public List<Achievement> getAchievementList(){
        return this.ac;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
    @Override
    public String toString(){
    return "Player [username=" + userName + ", tagname= "+tagName +"]";
    }
}
