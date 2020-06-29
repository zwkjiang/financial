package com.example.usercenter.view;

import android.content.Intent;
import android.view.View;
import com.example.core.view.BaseActivity;
import com.example.core.viewmodel.BaseViewModel;
import com.example.usercenter.R;
import com.example.usercenter.databinding.ActivityLoginMainBinding;
import com.example.usercenter.viewmodel.MainLoginViewModel;

public class MainLoginActivity extends BaseActivity<ActivityLoginMainBinding, MainLoginViewModel> {
    @Override
    protected void initBinding() {
        binding.setLogin(this);
    }

    @Override
    protected MainLoginViewModel createVM() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_main;
    }

    public void goRegister(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }
    public void goLogin(View view){
        startActivity(new Intent(this,LoginActivity.class));
    }
}
