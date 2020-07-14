package com.example.home.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import com.example.core.repository.Repository;
import com.example.core.viewmodel.BaseViewModel;
import com.example.home.entity.PayEntity;
import com.example.home.repository.PaymentRepository;
import com.example.protocol.BasesRespEntity;

public class PaymentViewModel extends BaseViewModel {
    private final String TAG = PaymentViewModel.class.getSimpleName();
    public PaymentViewModel(LifecycleOwner _owner) {
        super(_owner);
        registerRepository(TAG,new PaymentRepository());
    }

    public LiveData<BasesRespEntity<Boolean>> getBuy(PayEntity entity){
        PaymentRepository repository = getRepository(TAG);
        LiveData<BasesRespEntity<Boolean>> buy = repository.getBuy(entity);
        return buy;
    }
}
