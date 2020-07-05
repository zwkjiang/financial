package com.example.home.model.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SysMsgEntity;

import java.util.List;

@Dao
public interface HomeDao {

    /**
     * banner信息储存和查询
     * @return
     */
    @Query("select * from ta_banner")
    List<BannerEntity> getBannerAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBannerAll(List<BannerEntity> banners);

    /**
     * 系统消息保存和查询
     * @return
     */
    @Query("select * from tb_sysmsg")
    List<SysMsgEntity> getSysMsgAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSysMsgAll(List<SysMsgEntity> sysmsgs);

    @Query("select * from tb_product")
    List<ProductEntity> getProductAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductAll(List<ProductEntity> products);

    @Query("select * from tb_product")
    List<ProductEntity> getNewProductAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewProductAll(List<ProductEntity> products);
}
