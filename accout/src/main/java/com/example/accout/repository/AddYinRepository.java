package com.example.accout.repository;

import androidx.lifecycle.LiveData;
import com.example.accout.entity.PaySelectEntity;
import com.example.accout.model.service.AddYinModel;
import com.example.core.repository.Repository;
import com.example.protocol.BasesRespEntity;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public class AddYinRepository extends Repository<AddYinModel> {
    @Override
    protected AddYinModel createModel() {
        return new AddYinModel();
    }
    public LiveData<BasesRespEntity<Boolean>> addYin( PaySelectEntity entity){
        LiveData<BasesRespEntity<Boolean>> basesRespEntityLiveData = mModel.addYin(entity);
        return basesRespEntityLiveData;
    }
}
