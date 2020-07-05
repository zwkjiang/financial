package com.example.accout.view;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.accout.R;
import com.example.core.view.BaseActivity;
import com.example.core.viewmodel.BaseViewModel;
import com.example.router.RouterPath;

@Route(path = RouterPath.TEXT)
public class BuyActivity extends BaseActivity {
    @Override
    protected void initBinding() {

    }

    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_buy;
    }
}
