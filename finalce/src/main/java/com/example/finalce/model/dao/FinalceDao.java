package com.example.finalce.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.finalce.entity.FinalceEntity;

import java.util.List;

@Dao
public interface FinalceDao {

    @Query("select * from tb_finalce where producttype =:producttype limit (:currentpage*:pagesize),:pagesize")
    List<FinalceEntity> getFinalceAll(int producttype,int currentpage,int pagesize);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFinalce(List<FinalceEntity> entities);

}
