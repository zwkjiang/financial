package com.example.accout.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import com.example.accout.entity.PaySelectEntity;
import com.example.accout.repository.AddYinRepository;
import com.example.core.repository.Repository;
import com.example.core.viewmodel.BaseViewModel;
import com.example.protocol.BasesRespEntity;

public class AddYinViewModel extends BaseViewModel {
    private final String TAG = AddYinViewModel.class.getSimpleName();

    public AddYinViewModel(LifecycleOwner _owner) {
        super(_owner);
        registerRepository(TAG,new AddYinRepository());
    }
    public LiveData<BasesRespEntity<Boolean>> addYin(PaySelectEntity entity){
        AddYinRepository repository = getRepository(TAG);
        LiveData<BasesRespEntity<Boolean>> basesRespEntityLiveData = repository.addYin(entity);
        return basesRespEntityLiveData;
    }

}
