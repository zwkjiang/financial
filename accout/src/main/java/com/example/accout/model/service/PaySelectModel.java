package com.example.accout.model.service;

import androidx.lifecycle.LiveData;
import com.example.accout.entity.PaySelectEntity;
import com.example.accout.model.api.Accout;
import com.example.core.model.IModel;
import com.example.net.RetrofitFactory;
import com.example.protocol.BasesRespEntity;

import java.util.List;

public class PaySelectModel implements IModel {

    public  LiveData<BasesRespEntity<List<PaySelectEntity>>> getYin(){
        LiveData<BasesRespEntity<List<PaySelectEntity>>> yin = RetrofitFactory.getInstance().getRetrofit().create(Accout.class).getYin("50");
        return yin;
    }
}
