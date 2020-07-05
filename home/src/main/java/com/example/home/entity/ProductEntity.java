package com.example.home.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_product")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo
    private int id;
    @ColumnInfo
    private String productname;
    @ColumnInfo
    private String productdesc;
    @ColumnInfo
    private int producttype;
    @ColumnInfo
    private double yearrate;
    @ColumnInfo
    private double totalamount;
    @ColumnInfo
    private double saleamount;
    @ColumnInfo
    private String labels;
    @ColumnInfo
    private int lockdays;
    @ColumnInfo
    private double minbugamount;
    @ColumnInfo
    private int isnew;
    @ColumnInfo
    private double startlevel;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public double getSaleamount() {
        return saleamount;
    }

    public void setSaleamount(double saleamount) {
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

    public double getMinbugamount() {
        return minbugamount;
    }

    public void setMinbugamount(double minbugamount) {
        this.minbugamount = minbugamount;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public double getStartlevel() {
        return startlevel;
    }

    public void setStartlevel(double startlevel) {
        this.startlevel = startlevel;
    }
}
