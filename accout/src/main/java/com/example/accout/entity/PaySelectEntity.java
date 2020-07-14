package com.example.accout.entity;

public class PaySelectEntity {

    /**
     * id : 33
     * userid : 50
     * paytype : 0
     * paytypeaccount : sample string 4
     */

    private String id;
    private String userid;
    private String paytype;
    private String paytypeaccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPaytypeaccount() {
        return paytypeaccount;
    }

    public void setPaytypeaccount(String paytypeaccount) {
        this.paytypeaccount = paytypeaccount;
    }
}
