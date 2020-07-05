package com.example.finalce.model.service;

import androidx.lifecycle.LiveData;
import com.example.core.model.IModel;
import com.example.finalce.entity.FinalceEntity;
import com.example.finalce.model.api.FinalceApi;
import com.example.net.RetrofitFactory;
import com.example.protocol.BasesRespEntity;

import java.util.List;

public class FinalceRemoteService implements IModel {

    public LiveData<BasesRespEntity<List<FinalceEntity>>> getFinacel(int producttype,int currentpage,int pagesize){
        LiveData<BasesRespEntity<List<FinalceEntity>>> finalc = RetrofitFactory.getInstance().getRetrofit()
                .create(FinalceApi.class).
                        getFinalc(producttype, currentpage, pagesize);
        return finalc;
    }
}
