package com.example.usercenter.model;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.api.Api;
import com.example.core.model.IModel;
import com.example.net.RetrofitFactory;
import com.example.protocol.BasesRespEntity;
import com.example.usercenter.model.api.UserApi;

public class RegisterModel implements IModel {
    private final  String TAG= UserModel.class.getSimpleName();

    public LiveData<BasesRespEntity<String >> registerYan(ObservableField<String> str){
        LiveData<BasesRespEntity<String>> code = RetrofitFactory.getInstance().getRetrofit().create(UserApi.class)
                .getCode(str.get());
        return code;
    }
}
