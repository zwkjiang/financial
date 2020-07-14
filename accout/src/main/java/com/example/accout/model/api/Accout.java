package com.example.accout.model.api;

import androidx.lifecycle.LiveData;
import com.example.accout.entity.PaySelectEntity;
import com.example.protocol.BasesRespEntity;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface Accout {

    @GET("api/PayType/getPayTypes?")
    LiveData<BasesRespEntity<List<PaySelectEntity>>> getYin(@Query("userid") String userid);

    @POST("api/PayType/addPayType")
    LiveData<BasesRespEntity<Boolean>> addYin(@Body PaySelectEntity entity);

}
