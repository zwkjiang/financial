package com.example.home.model.service;

import androidx.lifecycle.LiveData;
import com.example.core.model.IModel;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SysMsgEntity;
import com.example.home.model.api.HomeApi;
import com.example.net.RetrofitFactory;
import com.example.protocol.BasesRespEntity;

import java.util.List;

public class HomeRemoteModel implements IModel {
    private final HomeApi homeApi;

    public HomeRemoteModel(){
        homeApi = RetrofitFactory.getInstance().getRetrofit().create(HomeApi.class);
    }

    /**
     * 获取banner实体信息
     * @return
     */
    public LiveData<BasesRespEntity<List<BannerEntity>>> getBanner(){
        LiveData<BasesRespEntity<List<BannerEntity>>> banner = homeApi.getBanner();
        return banner;
    }

    public LiveData<BasesRespEntity<List<SysMsgEntity>>> getSysMsg(){
        LiveData<BasesRespEntity<List<SysMsgEntity>>> systemMsgs = homeApi.getSystemMsgs();
        return systemMsgs;
    }

    public LiveData<BasesRespEntity<List<ProductEntity>>> getProduct(){
        LiveData<BasesRespEntity<List<ProductEntity>>> product = homeApi.getProduct();
        return product;
    }

    public LiveData<BasesRespEntity<List<ProductEntity>>> getNewProduct(){
        LiveData<BasesRespEntity<List<ProductEntity>>> newUserProduct = homeApi.getNewUserProduct();
        return newUserProduct;
    }



}
