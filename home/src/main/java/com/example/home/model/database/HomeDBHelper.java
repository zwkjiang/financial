package com.example.home.model.database;

import androidx.room.Room;
import com.example.conmon.app.BaseApplication;
import com.example.conmon.utils.LogUtils;

public class HomeDBHelper {
    private final static String DBNAME = "home.db";
    private static HomeDBHelper instance = new HomeDBHelper();
    private final HomeDataBase db;

    private HomeDBHelper() {
        db = Room.databaseBuilder(BaseApplication.getApplication(),HomeDataBase.class,DBNAME).build();
    }

    public static HomeDBHelper getInstance(){
        return instance;
    }
    public HomeDataBase getDB(){
        return db;
    }

    public void closeDB(){
        if (db!=null&&db.isOpen()){
            db.close();
        }
    }
}
