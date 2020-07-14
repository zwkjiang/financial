package com.example.accout.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import com.example.accout.entity.PaySelectEntity;
import com.example.accout.repository.PaySelectRepository;
import com.example.core.repository.Repository;
import com.example.core.viewmodel.BaseViewModel;
import com.example.protocol.BasesRespEntity;

import java.util.List;

public class PaySelectViewModel extends BaseViewModel {
    private final String TAG = PaySelectViewModel.class.getSimpleName();
    public PaySelectViewModel(LifecycleOwner _owner) {
        super(_owner);
        registerRepository(TAG,new PaySelectRepository());
    }

    public LiveData<BasesRespEntity<List<PaySelectEntity>>> getYin(){
        PaySelectRepository repository = getRepository(TAG);
        LiveData<BasesRespEntity<List<PaySelectEntity>>> yin = repository.getYin();
        return yin;
    }

}
