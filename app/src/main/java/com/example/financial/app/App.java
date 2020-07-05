package com.example.financial.app;

import androidx.multidex.MultiDex;
import com.example.conmon.app.BaseApplication;
import com.example.router.RouterManager;
import com.example.router.RouterPath;

public class App extends BaseApplication {
    @Override
    protected void initOtherConfig() {
        RouterManager.getInstance().init(this,true);
    }
}
