package com.example.finalce.model.api;

import androidx.lifecycle.LiveData;
import com.example.finalce.entity.FinalceEntity;
import com.example.protocol.BasesRespEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface FinalceApi {

    @GET("api/Product/getProcductsForType?")
    LiveData<BasesRespEntity<List<FinalceEntity>>> getFinalc(@Query("type") int type, @Query("currentPage")int currentPage, @Query("pageSize") int pageSize);
}
