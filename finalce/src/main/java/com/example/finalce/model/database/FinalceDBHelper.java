package com.example.finalce.model.database;

import androidx.room.Room;
import com.example.conmon.app.BaseApplication;
import com.example.conmon.utils.LogUtils;

public class FinalceDBHelper {

    private final  String DBNAMA = "finalce.db";
    private static FinalceDBHelper instance = new FinalceDBHelper();
    private final FinalceDataBase db;

    public FinalceDBHelper() {
        LogUtils.INSTANCE.i("ZWK","123");
        db = Room.databaseBuilder(BaseApplication.getApplication(),FinalceDataBase.class,DBNAMA).build();
    }

    public static FinalceDBHelper getInstance(){
        return instance;
    }

    public FinalceDataBase getDB(){
        return db;
    }
    public void closeDB(){
        if (db!=null&&db.isOpen()){
            db.close();
        }
    }
}
