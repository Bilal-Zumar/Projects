/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.sql.Date;
import javafx.scene.input.KeyCode;

/**
 
 * @author Bilal
 */
public class Librarian_1 extends Users{

    public Librarian_1(String name,Date Dob,String address) {
        super(name,Dob,address,"Librarian");
    }
    
    public Librarian_1(int id,String name,Date Dob,String address) {
        super(id,name,Dob,address,"Librarian");
    }
    
}
