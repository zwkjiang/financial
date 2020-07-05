package com.example.usercenter.view;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.example.core.view.BaseActivity;
import com.example.core.viewmodel.BaseViewModel;
import com.example.protocol.BasesRespEntity;
import com.example.usercenter.R;
import com.example.usercenter.databinding.ActivityRegisterBinding;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.viewmodel.RegisterViewModel;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {
    public ObservableField<String> rePassword = new ObservableField<>();
    @Override
    protected void initBinding() {
        binding.setRegister(this);
        binding.setVm(vm);
    }

    @Override
    protected RegisterViewModel createVM() {
        return new RegisterViewModel();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    public void goYan(View view){
        LiveData<BasesRespEntity<String>> yan = vm.getYan();
        yan.observe(this, new Observer<BasesRespEntity<String>>() {
            @Override
            public void onChanged(BasesRespEntity<String> stringBasesRespEntity) {
                String data = stringBasesRespEntity.getData();
                vm.code.set(data);
            }
        });
    }

    public void goRegister(View view){
        if (!(vm.userEntity.getUsername()!=null&&vm.userEntity.getPwd()!=null&&rePassword.get()!=null&&vm.code.get()!=null)){
            Toast.makeText(this, "不得为NULL", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!vm.userEntity.getPwd().equals(rePassword.get())){
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        LiveData<BasesRespEntity<UserEntity>> register = vm.register();
        register.observe(this, new Observer<BasesRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BasesRespEntity<UserEntity> userEntityBasesRespEntity) {
                if (userEntityBasesRespEntity!=null){
                    if (userEntityBasesRespEntity.getData().getUsername().equals(vm.userEntity.getUsername())){
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
