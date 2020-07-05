package com.example.home.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ta_banner")
public class BannerEntity {

    /**
     * imgurl : http://49.233.93.35:7077/img/banner1.jpg
     * desc :
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String imgurl;
    @ColumnInfo
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
