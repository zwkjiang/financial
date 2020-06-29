package com.example.usercenter.view;

import android.text.TextUtils;
import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.example.core.view.BaseActivity;
import com.example.protocol.BasesRespEntity;
import com.example.usercenter.R;
import com.example.usercenter.databinding.LoginViewBinding;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.viewmodel.UserViewModel;

public class LoginActivity extends BaseActivity<LoginViewBinding,UserViewModel>{
    private final String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void initBinding() {
        binding.setLoginAct(this);
        binding.setVm(vm);
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_view;
    }

    public void loginClick(View view){
        String username = vm.userEntity.getUsername();
        String pwd = vm.userEntity.getPwd();
        if (TextUtils.isEmpty(username)){
            showMsg(getResourceString(R.string.user_hint_input_username));
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            showMsg(getResourceString(R.string.user_hint_input_pwd));
            return;
        }
        LiveData<BasesRespEntity<UserEntity>> login = vm.login();
        login.observe(this, new Observer<BasesRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BasesRespEntity<UserEntity> userEntityBasesRespEntity) {
                if (userEntityBasesRespEntity.getData()!=null){
                    showMsg(getResourceString(R.string.user_login_success));
                    return;
                }
                else{
                    showMsg(getResourceString(R.string.user_login_failed));
                }
            }
        });

    }
}
