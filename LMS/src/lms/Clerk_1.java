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
public class Clerk_1 extends Users{
    
    public Clerk_1(String name,Date Dob,String address) {
        super(name,Dob,address,"Clerk");
    }
    
    public Clerk_1(int id,String name,Date Dob,String address) {
        super(id,name,Dob,address,"Clerk");
    }
}
