/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.util.Date;

/**
 *
 * @autheor Bilal
 */
public class Loan {
    private Date issuedate;
    private Date returndate;
    private int id_borrower;
    private int id_item;
    private int has_return;
    
    public Loan(int id_b,int id_i, Date issue, Date ret, int has) {
        this.issuedate = issue;
        this.returndate = ret;
        this.id_borrower = id_b;
        this.id_item = id_i;
        this.has_return=has;
        
    }    
    
    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public int getId_borrower() {
        return id_borrower;
    }

    public void setId_borrower(int id_borrower) {
        this.id_borrower = id_borrower;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getHas_return() {
        return has_return;
    }

    public void setHas_return(int has_return) {
        this.has_return = has_return;
    }

}
