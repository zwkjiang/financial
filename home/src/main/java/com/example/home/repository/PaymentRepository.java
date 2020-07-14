package com.example.home.repository;

import androidx.lifecycle.LiveData;
import com.example.core.repository.Repository;
import com.example.home.entity.PayEntity;
import com.example.home.model.service.PaymentModel;
import com.example.protocol.BasesRespEntity;

public class PaymentRepository extends Repository<PaymentModel> {
    @Override
    protected PaymentModel createModel() {
        return new PaymentModel();
    }

    public LiveData<BasesRespEntity<Boolean>> getBuy(PayEntity payEntity){
        LiveData<BasesRespEntity<Boolean>> buy = mModel.getBuy(payEntity);
        return buy;
    }
}
