package com.example.home.model.api;

import androidx.lifecycle.LiveData;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SysMsgEntity;
import com.example.protocol.BasesRespEntity;
import retrofit2.http.GET;

import java.util.List;

public interface HomeApi {
    /**
     * 获取Banner实体信息
     * @return
     */
    @GET("api/Img/getBannerImg")
    LiveData<BasesRespEntity<List<BannerEntity>>> getBanner();

    /**
     * 获取系统消息
     * @return
     */
    @GET("api/SystemMsg/getSystemMsg")
    LiveData<BasesRespEntity<List<SysMsgEntity>>> getSystemMsgs();

    /**
     * 获取新用户的推荐产品
     * @return
     */
    @GET("api/Product/getNewUserProcducts")
    LiveData<BasesRespEntity<List<ProductEntity>>> getNewUserProduct();

    /**
     * 获取推荐核心产品数据
     * @return
     */
    @GET("api/Product/getProcducts")
    LiveData<BasesRespEntity<List<ProductEntity>>> getProduct();
}
