package com.example.usercenter.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class UserEntity extends BaseObservable {

    private int id;
    private String username;
    private String pwd;
    private String sex;
    private String birthday;
    private String headerimg;
    private String nick;
    private int utype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeaderimg() {
        return headerimg;
    }

    public void setHeaderimg(String headerimg) {
        this.headerimg = headerimg;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }
}
