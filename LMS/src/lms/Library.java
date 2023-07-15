/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Bilal
 */
public class Library {
    private database d;
    private String Name;
    private ArrayList<Users> user;
    private ArrayList<Items> item;
    private ArrayList<Loginooad> l;
    private ArrayList<Loan> loan;
    
    public Library(String Name) {
        this.d=new database();
        this.Name = Name;
        this.user = d.getUsers();
        this.item = d.getItems();
        this.l = d.getLogin();
        this.loan = d.getLoan();
        
    }
    
    Users validuser(Loginooad l)
    {
        try
        {
            for (int i = 0; i < user.size(); i++) {
                if(user.get(i).getId()==l.getId())
                {
                    return user.get(i);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return new Users();
    }

    ArrayList hasbooks(Users u)
    {
        ArrayList temp=new ArrayList();
        try{
            for (int i = 0; i < loan.size(); i++) {
                if(loan.get(i).getId_borrower()==u.getId() && loan.get(i).getHas_return()==0)
                {
                    temp.add(searchBook(loan.get(i).getId_item()).getName());
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return temp;
    }
    
    Items searchBook(int id)
    {
     try
        {
            for (int i = 0; i < item.size(); i++) {
                if(item.get(i).getId()==id)
                    return item.get(i);
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROr");
        }
        return new Items();   
    }
    
    Items searchBook(String name)
    {
     try
        {
            for (int i = 0; i < item.size(); i++) {
                if(item.get(i).getName().equalsIgnoreCase(name))
                    return item.get(i);
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROr");
        }
        return new Items();   
    }
    
    Users searchUser(String name)
    {
        try
           {
               for (int i = 0; i < user.size(); i++) {
                   if(user.get(i).getName().equalsIgnoreCase(name))
                       return user.get(i);
               }
           }
           catch(Exception e)
           {
               System.out.println(e);
           }
           return new Users();   
    }

    int addUser(Users u)
    {
        try{
            int i=d.addUser(u);
            this.user = d.getUsers();
            return i;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    int addBook(Items it) 
    {
        try{
            int i=d.addBook(it);
            this.item = d.getItems();
            releasehold();
            return i;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }

    int deleteItem(String name)
    {
        int i=d.deleteItem(name);
        this.item = d.getItems();
        return i;
    }
    
    int deleteUser(String name)
    {
        int i=d.deleteUser(name);
        this.user = d.getUsers();
        return i;
    }
    
    int setSubmit(Loan l)
    {
        int i=d.setSubmit(l);
        this.loan=d.getLoan();
        this.item=d.getItems();
        releasehold();
        return i;
    }
    
    void releasehold()
    {
        ArrayList<Loan> arr=new ArrayList<Loan>();
        for (int i = 0; i < loan.size(); i++) {
            if(loan.get(i).getHas_return()==-1)
                arr.add(loan.get(i));
        }
        
        for (int i = 0; i < arr.size(); i++) {
            Items It=searchBook(arr.get(i).getId_item());
            if(It.getQuantity()>0)
            {
                arr.get(i).setHas_return(0);
                It.setQuantity(It.getQuantity()-1);
            }
        }
        d.writeloan(loan);
    }
    
    int createLoan(Loan l)
    {
        if(getquantity(l)==-2)
        {
            int i=d.holdLoan(l);
            this.loan=d.getLoan();
            this.item=d.getItems();
            return i;
        }
        int i=d.createLoan(l);
        this.loan=d.getLoan();
        this.item=d.getItems();
        return i;
    }
    
    int getquantity(Loan l)
    {
        Items i=searchBook(l.getId_item());
        if(i.getQuantity()<=0)
        {
            return -2;
        }
        return 0;
    }
}
