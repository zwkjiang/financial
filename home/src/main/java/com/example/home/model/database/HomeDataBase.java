package com.example.home.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SysMsgEntity;
import com.example.home.model.dao.HomeDao;

@Database(entities = {BannerEntity.class, SysMsgEntity.class, ProductEntity.class},version = 1,exportSchema = false)
public abstract class HomeDataBase extends RoomDatabase {
    public abstract HomeDao homeDao();
}
