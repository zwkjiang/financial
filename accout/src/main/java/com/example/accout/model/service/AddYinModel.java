package com.example.accout.model.service;

import androidx.lifecycle.LiveData;
import com.example.accout.entity.PaySelectEntity;
import com.example.accout.model.api.Accout;
import com.example.core.model.IModel;
import com.example.net.RetrofitFactory;
import com.example.protocol.BasesRespEntity;
import retrofit2.http.Body;

public class AddYinModel implements IModel {

    public LiveData<BasesRespEntity<Boolean>> addYin( PaySelectEntity entity){
        LiveData<BasesRespEntity<Boolean>> basesRespEntityLiveData = RetrofitFactory.getInstance().getRetrofit().create(Accout.class).addYin(entity);
        return basesRespEntityLiveData;
    }
}
