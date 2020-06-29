package com.example.usercenter.repository;

import androidx.lifecycle.LiveData;
import com.example.core.repository.Repository;
import com.example.protocol.BasesRespEntity;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.model.UserModel;

public class UserRepository extends Repository<UserModel> {
    @Override
    protected UserModel createModel() {
        return new UserModel();
    }

    public LiveData<BasesRespEntity<UserEntity>> login(UserEntity userEntity){
        return mModel.login(userEntity);
    }


}
