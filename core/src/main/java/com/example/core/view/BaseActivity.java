package com.example.core.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.conmon.utils.MsgUtils;
import com.example.core.viewmodel.BaseViewModel;

public abstract class BaseActivity <Binding extends ViewDataBinding,VM extends BaseViewModel> extends AppCompatActivity {
    protected Binding binding;
    protected VM vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getLayoutId());
        vm = createVM();
        initBinding();
    }

    protected abstract void initBinding();

    protected abstract VM createVM();

    protected abstract int getLayoutId();

    protected void showMsg(String msg){
        MsgUtils.INSTANCE.show(this,msg);
    }

    protected String getResourceString(int strId){
        return getResources().getString(strId);
    }
}
