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
public class LMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Library lib=new Library("Fast Library");
        Login log=new Login();
        log.setter(lib);
        log.setVisible(true);
    }
    
}
