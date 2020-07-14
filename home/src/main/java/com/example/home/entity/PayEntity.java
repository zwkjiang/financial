package com.example.home.entity;

public class PayEntity {

    /**
     * id : 1
     * transactiontype : 0
     * transactionvalue : 3.0
     * transactiondate : sample string 4
     * paytypeid : 5
     * userid : 6
     * paytype : 7
     * paytypeaccount : sample string 8
     */

    private int id;
    private int transactiontype;
    private double transactionvalue;
    private String transactiondate;
    private int paytypeid;
    private int userid;
    private int paytype;
    private String paytypeaccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(int transactiontype) {
        this.transactiontype = transactiontype;
    }

    public double getTransactionvalue() {
        return transactionvalue;
    }

    public void setTransactionvalue(double transactionvalue) {
        this.transactionvalue = transactionvalue;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate;
    }

    public int getPaytypeid() {
        return paytypeid;
    }

    public void setPaytypeid(int paytypeid) {
        this.paytypeid = paytypeid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getPaytypeaccount() {
        return paytypeaccount;
    }

    public void setPaytypeaccount(String paytypeaccount) {
        this.paytypeaccount = paytypeaccount;
    }
}
