package com.example.usercenter.repository;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import com.example.core.repository.Repository;
import com.example.protocol.BasesRespEntity;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.model.RegisterModel;

public class RegisterRepository extends Repository<RegisterModel> {



    @Override
    protected RegisterModel createModel() {
        return new RegisterModel();
    }

    public LiveData<BasesRespEntity<String>> registerYan(ObservableField<String> str){
        return mModel.registerYan(str);
    }

    public LiveData<BasesRespEntity<UserEntity>> register(UserEntity userEntity){
        return mModel.register(userEntity);
    };
}
