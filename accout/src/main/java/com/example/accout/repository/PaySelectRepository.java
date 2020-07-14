package com.example.accout.repository;

import androidx.lifecycle.LiveData;
import com.example.accout.entity.PaySelectEntity;
import com.example.accout.model.service.PaySelectModel;
import com.example.core.repository.Repository;
import com.example.protocol.BasesRespEntity;

import java.util.List;

public class PaySelectRepository extends Repository<PaySelectModel> {
    @Override
    protected PaySelectModel createModel() {
        return new PaySelectModel();
    }

    public LiveData<BasesRespEntity<List<PaySelectEntity>>> getYin(){
        LiveData<BasesRespEntity<List<PaySelectEntity>>> yin = mModel.getYin();
        return yin;
    }

}
