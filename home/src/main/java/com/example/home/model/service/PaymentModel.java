package com.example.home.model.service;

import androidx.lifecycle.LiveData;
import com.example.core.model.IModel;
import com.example.home.entity.PayEntity;
import com.example.home.model.api.HomeApi;
import com.example.net.RetrofitFactory;
import com.example.protocol.BasesRespEntity;

public class PaymentModel implements IModel {

    public LiveData<BasesRespEntity<Boolean>> getBuy(PayEntity entity){
        LiveData<BasesRespEntity<Boolean>> buy = RetrofitFactory.getInstance().getRetrofit().create(HomeApi.class)
                .getBuy(entity);
        return buy;
    }
}
