package com.example.conmon.app;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

public abstract class BaseApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();  // 初始化MultiDex
        MultiDex.install(this);
        context = this;
        initOtherConfig();
    }

    protected abstract void initOtherConfig();
    public static Context getApplication(){
        return context;
    }
}
