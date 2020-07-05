package com.example.usercenter.model.api;

import androidx.lifecycle.LiveData;
import com.example.conmon.Config;
import com.example.protocol.BasesRespEntity;
import com.example.usercenter.entity.UserEntity;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserApi {

    @POST("api/User/login")
    LiveData<BasesRespEntity<UserEntity>> login(@Body UserEntity userEntity);

    @GET("api/User/getAuthCode?")
    LiveData<BasesRespEntity<String>> getCode(@Query("phoneNumber") String phoneNumber);

    @POST("api/User/register")
    LiveData<BasesRespEntity<UserEntity>> register(@Body UserEntity userEntity);

}
