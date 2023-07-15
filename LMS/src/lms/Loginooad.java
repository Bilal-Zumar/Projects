/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

/**
 *
 * @author Bilal Zumar
 */
public class Loginooad {
    int id;
    private String username;
    private String password;

    public Loginooad(String username, String password) {
        this.username = username;
        this.password = password;
        id=new database().getId(username, password);
    }
    
//    Users Valid(){
//    //    System.out.println(username + password);
//        return new database().validuser(this);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
