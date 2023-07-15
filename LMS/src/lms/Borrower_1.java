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
public class Borrower_1 extends Users{
    
    public Borrower_1(String name,Date Dob,String address) {
        super(name,Dob,address,"Borrower");
    }
    
    public Borrower_1(int id,String name,Date Dob,String address) {
        super(id,name,Dob,address,"Borrower");
    }
//    String ViewAllitems()
//    {
//        StringBuffer str=new StringBuffer();
////        for (int i = 0; i < getItem().length; i++) {
////            str.append(getItem()[i].toString());
////        }
//        return str.toString();
//    }
    
//    int SearchItem(String name)
//    {
////        for (int i = 0; i < getItem().length; i++) {
////            if(temp==getItem()[i])
////                return i;
////        }
//        return -1;
//    }
    
//    public void borrow() {
////        int search=SearchItem(item);
////        if(search!=-1)
////        {
////            if(this.getItem()[search].quantity>0)
////            {
////                this.getItem()[search].quantity--;
////                System.out.println("Hurry! you got it...");
////            }
////            else{
////                System.out.println("Book is not available...");
////            }
////        }
////        else
////        {
////            System.out.println("Sorry! Item not found");
////        }
//    }
//    
        
}
