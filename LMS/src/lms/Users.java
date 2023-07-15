/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.sql.Date;

/**
 *
 * @author Bilal
 */
public class Users {
    private static int set=1;
    private String name;
    private int id;
    private String address;
    private Date DOB;
    private String Type;

    public Users(String name,Date Dob,String address,String Type) {
        this.name = name;
        this.id = set;
        set++;
        this.address = address;
        this.DOB=Dob;
        this.Type=Type;
    }
    
    public Users(int id,String name,Date Dob,String address,String Type) {
        this.name = name;
        this.id = id;
        set++;
        this.address = address;
        this.DOB=Dob;
        this.Type=Type;
    }

    public static void setSet(int set) {
        Users.set = set;
    }
       
    public Users() {
        this.name = "";
        this.id = -1;
        this.address = "";
        this.DOB=null;
        this.Type="";
    }
//    
//    ArrayList hasBook()
//    {
//        return new database().displaybooks(this);
//    }
//    
    
    boolean isNull()
    {
        if(id==-1)
            return true;
        else
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String userInfo(){
        return name+" id: "+id+" "+address;
    }
}