package com.example.usercenter.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import com.example.core.repository.Repository;
import com.example.core.viewmodel.BaseViewModel;
import com.example.protocol.BasesRespEntity;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.repository.RegisterRepository;

public class RegisterViewModel extends BaseViewModel {
    public ObservableField<String> code = new ObservableField<>();
    public UserEntity userEntity = new UserEntity();

    public RegisterViewModel() {
        registerRepository(RegisterViewModel.class.getSimpleName(),new RegisterRepository());
    }

    public LiveData<BasesRespEntity<String>> getYan(){
        code.set(" ");
        RegisterRepository repository = getRepository(RegisterViewModel.class.getSimpleName());
        LiveData<BasesRespEntity<String>> basesRespEntityLiveData = repository.registerYan(code);
        return basesRespEntityLiveData;
    }

    public LiveData<BasesRespEntity<UserEntity>> register(){
        RegisterRepository repository = getRepository(RegisterViewModel.class.getSimpleName());
        return repository.register(userEntity);
    }

}
