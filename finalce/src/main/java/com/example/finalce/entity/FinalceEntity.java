package com.example.finalce.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_finalce")
public class FinalceEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String productname;
    private String productdesc;
    private int producttype;
    private double yearrate;
    private double totalamount;
    private int saleamount;
    private String labels;
    private int lockdays;
    private int minbugamount;
    private int isnew;
    private int startlevel;

    public FinalceEntity(){}


    @Ignore
    public FinalceEntity(int id, String productname, String productdesc, int producttype, double yearrate, double totalamount, int saleamount, String labels, int lockdays, int minbugamount, int isnew, int startlevel) {
        this.id = id;
        this.productname = productname;
        this.productdesc = productdesc;
        this.producttype = producttype;
        this.yearrate = yearrate;
        this.totalamount = totalamount;
        this.saleamount = saleamount;
        this.labels = labels;
        this.lockdays = lockdays;
        this.minbugamount = minbugamount;
        this.isnew = isnew;
        this.startlevel = startlevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public int getProducttype() {
        return producttype;
    }

    public void setProducttype(int producttype) {
        this.producttype = producttype;
    }

    public double getYearrate() {
        return yearrate;
    }

    public void setYearrate(double yearrate) {
        this.yearrate = yearrate;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public int getSaleamount() {
        return saleamount;
    }

    public void setSaleamount(int saleamount) {
        this.saleamount = saleamount;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public int getLockdays() {
        return lockdays;
    }

    public void setLockdays(int lockdays) {
        this.lockdays = lockdays;
    }

    public int getMinbugamount() {
        return minbugamount;
    }

    public void setMinbugamount(int minbugamount) {
        this.minbugamount = minbugamount;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public int getStartlevel() {
        return startlevel;
    }

    public void setStartlevel(int startlevel) {
        this.startlevel = startlevel;
    }
}
