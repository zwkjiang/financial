package com.example.finalce.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import com.example.core.repository.Repository;
import com.example.core.viewmodel.BaseViewModel;
import com.example.finalce.entity.FinalceEntity;
import com.example.finalce.respository.FinalceRepository;
import com.example.protocol.BasesRespEntity;

import java.util.List;

public class FinalceViewModel extends BaseViewModel {

    private final String TAG = FinalceViewModel.class.getSimpleName();

    public FinalceViewModel(LifecycleOwner _owner) {
        super(_owner);
        registerRepository(TAG,new FinalceRepository(owner));
    }

    public LiveData<BasesRespEntity<List<FinalceEntity>>> getFinalce(int producttype,int currentpage,int pagesize){
        FinalceRepository repository = getRepository(TAG);
        LiveData<BasesRespEntity<List<FinalceEntity>>> finalce = repository.getFinalce(producttype, currentpage, pagesize);
        return finalce;
    }
}
