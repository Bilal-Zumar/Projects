/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

/**
 *
 * @author Bilal
 */
public class Books extends Items{

    public Books(String name,String Pub,int quantity) {
        super(name,Pub,"Book",quantity);
    }
    
    public Books(int id,String name,String Pub,int quantity) {
        super(id,name,Pub,"Book",quantity);
    }
}
