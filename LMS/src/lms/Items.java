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
public class Items{
    static int set=1;
    String name;
    int id;
    int quantity;
    String publisher;
    String type;

    public Items() {
        this.name = "";
        this.id = -1;
        this.type = "";
        this.publisher="";
        this.quantity=-1;
    }
    
    public Items(String name,String pub,String type,int quantity) {
        this.name = name;
        this.id = set;
        set++;
        this.type = type;
        this.publisher=pub;
        this.quantity=quantity;
    }

    public Items(int id,String name,String pub,String type,int quantity) {
        this.name = name;
        this.id = id;
        set++;
        this.type = type;
        this.publisher=pub;
        this.quantity=quantity;
    }

    public static void setSet(int set) {
        Items.set = set;
    }
    
    
    
    boolean isEmpty()
    {
        if(quantity==-1)
            return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
