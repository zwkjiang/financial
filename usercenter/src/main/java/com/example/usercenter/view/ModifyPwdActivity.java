package com.example.usercenter.view;

import com.example.core.view.BaseActivity;
import com.example.core.viewmodel.BaseViewModel;
import com.example.usercenter.R;
import com.example.usercenter.databinding.ActivityModifypwdBinding;
import com.example.usercenter.viewmodel.ModifyPwdViewModel;

public class ModifyPwdActivity extends BaseActivity<ActivityModifypwdBinding, ModifyPwdViewModel> {
    @Override
    protected void initBinding() {

    }

    @Override
    protected ModifyPwdViewModel createVM() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modifypwd;
    }
}
