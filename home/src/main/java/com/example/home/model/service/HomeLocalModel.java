package com.example.home.model.service;

import com.example.core.model.IModel;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SysMsgEntity;
import com.example.home.model.database.HomeDBHelper;

import java.util.List;

public class HomeLocalModel implements IModel {

    public List<BannerEntity> getBanner(){
        List<BannerEntity> banners = HomeDBHelper.getInstance()
                .getDB()
                .homeDao()
                .getBannerAll();
        return banners;

    }

    public void insertBanner(List<BannerEntity> banners){
        HomeDBHelper.getInstance()
                .getDB()
                .homeDao()
                .insertBannerAll(banners);
    }
    public List<SysMsgEntity> getSysMsg(){
        List<SysMsgEntity> sysMsg = HomeDBHelper.getInstance()
                .getDB()
                .homeDao()
                .getSysMsgAll();
        return sysMsg;

    }

    public void insertSysMsg(List<SysMsgEntity> sysMsg){
        HomeDBHelper.getInstance()
                .getDB()
                .homeDao()
                .insertSysMsgAll(sysMsg);
    }
    public List<ProductEntity> getProduct(){
        List<ProductEntity> product = HomeDBHelper.getInstance()
                .getDB()
                .homeDao()
                .getProductAll();
        return product;

    }

    public void insertProduct(List<ProductEntity> products){
        HomeDBHelper.getInstance()
                .getDB()
                .homeDao()
                .insertProductAll(products);
    }
    public List<ProductEntity> getNewProduct(){
        List<ProductEntity> product = HomeDBHelper.getInstance()
                .getDB()
                .homeDao()
                .getNewProductAll();
        return product;

    }

    public void insertNewProduct(List<ProductEntity> products){
        HomeDBHelper.getInstance()
                .getDB()
                .homeDao()
                .insertNewProductAll(products);
    }
}
