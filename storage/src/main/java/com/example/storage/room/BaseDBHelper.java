package com.example.storage.room;

import android.widget.BaseAdapter;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.conmon.app.BaseApplication;

public class BaseDBHelper<DB extends RoomDatabase> {

    private String DBNAME="";
    private static BaseDBHelper instance = new BaseDBHelper();
    private final DB db;

    public BaseDBHelper() {
        db = (DB) Room.databaseBuilder(BaseApplication.getApplication(),RoomDatabase.class,DBNAME).build();
    }

    public static BaseDBHelper getInstance(){
        return instance;
    }

    protected void setDbname(String dbname){
        DBNAME = dbname;
    }

    public DB getDB(){
        return db;
    }

    public void close(){
        if (db!=null&&db.isOpen()){
            db.close();
        }
    }
}
