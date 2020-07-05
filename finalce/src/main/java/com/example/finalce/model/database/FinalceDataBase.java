package com.example.finalce.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.finalce.entity.FinalceEntity;
import com.example.finalce.model.dao.FinalceDao;

@Database(entities = {FinalceEntity.class},version = 1,exportSchema = false)
public abstract class FinalceDataBase extends RoomDatabase {

    public abstract FinalceDao finalceDao();
}
