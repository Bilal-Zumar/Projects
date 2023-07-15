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
public class DVD extends Items{

    public DVD(String name,String Pub,int quantity) {
        super(name,Pub,"DVD",quantity);
    }
    
    public DVD(int id,String name,String Pub,int quantity) {
        super(id,name,Pub,"DVD",quantity);
    }
    
}
