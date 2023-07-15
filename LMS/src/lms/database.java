/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bilal Zumar
 */
public class database {
    Connection con;
    Statement stmt;
    
    database() //cons
    {
        try
        {
             String s = "jdbc:sqlserver://localhost:1433;databaseName=LMS";
             con=DriverManager.getConnection(s,"root","123");


            stmt = con.createStatement(); 
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    ArrayList<Users> getUsers()
    {
        new Users().setSet(1);
        ArrayList<Users> user=new ArrayList<Users>();
        try
        {
            String st="select * from persons";
            PreparedStatement stat=con.prepareStatement(st);
            
            ResultSet rs=stat.executeQuery();
            
            while(rs.next())
            {
                Users u=null;
                if(rs.getString(5).equalsIgnoreCase("Librarian"))
                {
                    u=new Librarian_1(rs.getInt(1),rs.getString(2), rs.getDate(3), rs.getString(4));
                }
                else if(rs.getString(5).equalsIgnoreCase("Clerk"))
                {
                    u=new Clerk_1(rs.getInt(1),rs.getString(2), rs.getDate(3), rs.getString(4));
                }
                else if(rs.getString(5).equalsIgnoreCase("Borrower"))
                {
                    u=new Borrower_1(rs.getInt(1),rs.getString(2), rs.getDate(3), rs.getString(4));
                }
                else
                {
                    System.out.println("Error Reading");
                }
                user.add(u);
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return user;
    }
    
    ArrayList<Items> getItems()
    {
        new Items().setSet(1);
        ArrayList<Items> item=new ArrayList<Items>();
        try
        {
            String st="select * from items";
            PreparedStatement stat=con.prepareStatement(st);
            
            ResultSet rs=stat.executeQuery();
            
            while(rs.next())
            {
                Items u=null;
                if(rs.getString(4).equalsIgnoreCase("Book"))
                {
                    u=new Books(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(5));
                }
                else if(rs.getString(4).equalsIgnoreCase("DVD"))
                {
                    u=new DVD(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(5));
                }
                else
                {
                    System.out.println("Error Reading");
                }
                item.add(u);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return item;
    }
    
    ArrayList<Loginooad> getLogin()
    {
        ArrayList<Loginooad> login=new ArrayList<Loginooad>();
        try
        {
            String st="select * from logins";
            PreparedStatement stat=con.prepareStatement(st);
            
            ResultSet rs=stat.executeQuery();
            
            while(rs.next())
            {
                Loginooad u=null;
                u=new Loginooad(rs.getString(2), rs.getString(3));
                login.add(u);
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return login;
    }
    
    ArrayList<Loan> getLoan()
    {
        ArrayList<Loan> lo=new ArrayList<Loan>();
        try
        {
            String st="select * from loans";
            PreparedStatement stat=con.prepareStatement(st);
            ResultSet rs=stat.executeQuery();
            
            while(rs.next())
            {
                Loan u=null;
                u=new Loan(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4), rs.getInt(5));
                lo.add(u);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return lo;
    }
    
    int getId(String name,String pass)
    {
        try
        {
            String st="select pid from logins where username = ? and pass =?";
            PreparedStatement stat=con.prepareStatement(st);
            stat.setString(1, name);
            stat.setString(2, pass);
            
            ResultSet rs=stat.executeQuery();
            
            if(rs.next())
            {
                return rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return -1;

    }

    void writeloan(ArrayList<Loan> l){
        for (int i = 0; i < l.size(); i++) {
            try
            {
                String str1="delete from loans";
                stmt.execute(str1);
                String str="insert into loans VALUES (?,?,?,?,?)";
                PreparedStatement stat=con.prepareStatement(str);
                stat.setInt(1, l.get(i).getId_borrower());
                stat.setInt(2, l.get(i).getId_item());
                java.sql.Date d=new java.sql.Date(l.get(i).getIssuedate().getDate());
                stat.setDate(3, d);
                java.sql.Date e=new java.sql.Date(l.get(i).getReturndate().getDate());
                stat.setDate(4, e);
                stat.setInt(5, 0);
                if(stat.executeUpdate()!=0)
                {
                    String st="UPDATE items SET availibility = availibility - 1  WHERE bid=? ";
                    PreparedStatement stt=con.prepareStatement(st);
                    stt.setInt(1, l.get(i).getId_item());
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }            
        }
    }    
    
    int setSubmit(Loan l)
    {
        try{
            String str="update loans Set has_return = 1 where pid= ? and bid= ? and has_return=0";
            PreparedStatement stat=con.prepareStatement(str);
            stat.setInt(1, l.getId_borrower());
            stat.setInt(2, l.getId_item());
            if(stat.executeUpdate()!=0)
            {
                String st="UPDATE items SET availibility = availibility + 1  WHERE bid=?";
                PreparedStatement stt=con.prepareStatement(st);
                stt.setInt(1, l.getId_item());
                return stt.executeUpdate();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    int already(Loan l)
    {
        try{
            String str="select * from loans where pid=? and bid=? and has_return = 0";
            PreparedStatement stat=con.prepareStatement(str);
            stat.setInt(1, l.getId_borrower());
            stat.setInt(2, l.getId_item());
            ResultSet rs=stat.executeQuery();
            if(rs.next())
                return 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 1;
    }

    int createLoan(Loan l)
    {
        if(already(l)==0)
            return -1;
        try{
            String str="insert into loans VALUES (?,?,?,?,?)";
            PreparedStatement stat=con.prepareStatement(str);
            stat.setInt(1, l.getId_borrower());
            stat.setInt(2, l.getId_item());
            java.sql.Date d=new java.sql.Date(l.getIssuedate().getDate());
            stat.setDate(3, d);
            java.sql.Date e=new java.sql.Date(l.getReturndate().getDate());
            stat.setDate(4, e);
            stat.setInt(5, 0);
            if(stat.executeUpdate()!=0)
            {
                String st="UPDATE items SET availibility = availibility - 1  WHERE bid=? ";
                PreparedStatement stt=con.prepareStatement(st);
                stt.setInt(1, l.getId_item());
                return stt.executeUpdate();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    int alreadyhold(Loan l)
    {
        try{
            String str="select * from loans where pid=? and bid=? and has_return = -1";
            PreparedStatement stat=con.prepareStatement(str);
            stat.setInt(1, l.getId_borrower());
            stat.setInt(2, l.getId_item());
            ResultSet rs=stat.executeQuery();
            if(rs.next())
                return 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 1;
    }
    
    int holdLoan(Loan l)
    {
        if(alreadyhold(l)==0)
            return -3;
        try{
            String str="insert into loans VALUES (?,?,?,?,?)";
            PreparedStatement stat=con.prepareStatement(str);
            stat.setInt(1, l.getId_borrower());
            stat.setInt(2, l.getId_item());
            java.sql.Date d=new java.sql.Date(l.getIssuedate().getDate());
            stat.setDate(3, d);
            java.sql.Date e=new java.sql.Date(l.getReturndate().getDate());
            stat.setDate(4, e);
            stat.setInt(5, -1);
            stat.execute();
            return -2;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    int addUser(Users u)
    {
        try{
            String str="insert into persons VALUES (?,?,?,?,?)";
            PreparedStatement stat=con.prepareStatement(str);
            stat.setInt(1, u.getId());
            stat.setString(2, u.getName());
            stat.setDate(3, u.getDOB());
            stat.setString(4, u.getAddress());
            stat.setString(5, u.getType());
            
            if(stat.executeUpdate()!=0)
            {
                String st="insert into logins VALUES (?,?,?)";
                PreparedStatement stt=con.prepareStatement(st);
                stt.setInt(1, u.getId());
                stt.setString(2, u.getName());
                stt.setString(3, u.getName()+"123");
                return stt.executeUpdate();
            }
        }
        catch(Exception e)
        {
            try {
                String st="insert into logins VALUES (?,?,?)";
                PreparedStatement stt=con.prepareStatement(st);
                stt.setInt(1, u.getId());
                stt.setString(2, u.getName());
                stt.setString(3, u.getName()+"123");
                return stt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    int addBook(Items item) 
    {
        try{
            String str="insert into items VALUES (?,?,?,?,?)";
            PreparedStatement stat=con.prepareStatement(str);
            stat.setInt(1, item.getId());
            stat.setString(2, item.getName());
            stat.setString(3, item.getPublisher());
            stat.setString(4, item.getType());
            stat.setInt(5, item.getQuantity());
            
            return stat.executeUpdate(); 
        }
        catch(Exception e)
        {
            try{
                String str="UPDATE items SET availibility = availibility + ?  WHERE bname=? ";
                PreparedStatement stat=con.prepareStatement(str);
                stat.setInt(1, item.getQuantity());
                stat.setString(2, item.getName());
                stat.executeUpdate();
                return 0;
            }
            catch(Exception f)
            {
                System.out.println(f);
            }
        }
        return -1;
    }

    int deleteItem(String name)
    {
        try{
            String b="select * from items where bname= '"+name+"' ";
            ResultSet rs=stmt.executeQuery(b);
            if(rs.next())
            {
                String a="delete from items where bid= " + rs.getInt(1) + " ";
                return stmt.executeUpdate(a);
            }
        }
        catch(Exception f)
        {
            System.out.println(f);
        }
        return 0;
    }
    
    int deleteUser(String name)
    {
        try{
            String b="select * from persons where pname= '"+name+"' ";
            ResultSet rs=stmt.executeQuery(b);
            if(rs.next())
            {
                String a="delete from logins where pid= " + rs.getInt(1) + " ";
                return stmt.executeUpdate(a);
            }
        }
        catch(Exception f)
        {
            System.out.println(f);
        }
        return 0;
    }
}
